package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.SeguroCarroDao;
import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.entidade.SeguroCarro;
import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import com.mycompany.crud_concessionaria.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/SeguroCarroControlador")
public class SeguroCarroControlador extends HttpServlet {
    private CarroDao carroDao;
    private SeguroCarroDao seguroCarroDao;
    private SeguroCarro seguroCarro;
    String seguro_id = "";
    String carroSeguro = "";
    String numero_polis = "";
    String data_inicio = "";
    String data_fim = "";
    String nome_seguradora = "";
    String valor_premio = "";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        seguroCarroDao = new SeguroCarroDao();
        seguroCarro = new SeguroCarro();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            seguro_id = request.getParameter("seguro_id");
            carroSeguro = request.getParameter("carroSeguro");
            numero_polis = request.getParameter("numero_polis");
            data_inicio = request.getParameter("data_inicio");
            data_fim = request.getParameter("data_fim");
            nome_seguradora = request.getParameter("nome_seguradora");
            valor_premio = request.getParameter("valor_premio");

            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            switch (opcao) {
                case "cadastrar":  cadastrar(request, response); break;
                case "editar":  editar(request, response); break;
                case "confirmarEditar":  confirmarEditar(request, response); break;
                case "excluir":  excluir(request, response); break;
                case "confirmarExcluir":  confirmarExcluir(request, response); break;
                case "cancelar":  cancelar(request, response); break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        seguroCarro.getCarroSeguro().setCarro_id(Integer.valueOf(carroSeguro));
        seguroCarro.setNumero_polis(numero_polis);
        seguroCarro.setData_inicio(converte.converteCalendario(data_inicio));
        seguroCarro.setData_fim(converte.converteCalendario(data_fim));
        seguroCarro.setNome_seguradora(nome_seguradora);
        seguroCarro.setValor_premio(Double.valueOf(valor_premio));

        seguroCarroDao.salvar(seguroCarro);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("seguro_id", seguro_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("carroSeguro", carroSeguro);
        request.setAttribute("numero_polis", numero_polis);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("nome_seguradora", nome_seguradora);
        request.setAttribute("valor_premio", valor_premio);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("seguro_id", seguro_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("carroSeguro", carroSeguro);
        request.setAttribute("numero_polis", numero_polis);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("nome_seguradora", nome_seguradora);
        request.setAttribute("valor_premio", valor_premio);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        seguroCarro.setSeguro_id(Integer.valueOf(seguro_id));
        seguroCarro.getCarroSeguro().setCarro_id(Integer.valueOf(carroSeguro));
        seguroCarro.setNumero_polis(numero_polis);
        seguroCarro.setData_inicio(converte.converteCalendario(data_inicio));
        seguroCarro.setData_fim(converte.converteCalendario(data_fim));
        seguroCarro.setNome_seguradora(nome_seguradora);
        seguroCarro.setValor_premio(Double.valueOf(valor_premio));
        seguroCarroDao.alterar(seguroCarro);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        seguroCarro.setSeguro_id(Integer.valueOf(seguro_id));
        seguroCarro.getCarroSeguro().setCarro_id(Integer.valueOf(carroSeguro));
        seguroCarro.setNumero_polis(numero_polis);
        seguroCarro.setData_inicio(converte.converteCalendario(data_inicio));
        seguroCarro.setData_fim(converte.converteCalendario(data_fim));
        seguroCarro.setNome_seguradora(nome_seguradora);
        seguroCarro.setValor_premio(Double.valueOf(valor_premio));
        seguroCarroDao.excluir(seguroCarro);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("seguro_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("carroSeguro", "");
        request.setAttribute("numero_polis", "");
        request.setAttribute("data_inicio", "");
        request.setAttribute("data_fim", "");
        request.setAttribute("nome_seguradora", "");
        request.setAttribute("valor_premio", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<SeguroCarro> seguros = seguroCarroDao.buscarTodas();
        request.setAttribute("seguros", seguros);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroSeguroCarro.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (carroSeguro == null || carroSeguro.isEmpty()
                || numero_polis == null || numero_polis.isEmpty()
                || data_inicio == null || data_inicio.isEmpty()
                || data_fim == null || data_fim.isEmpty()
                || nome_seguradora == null || nome_seguradora.isEmpty()
                || valor_premio == null || valor_premio.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
