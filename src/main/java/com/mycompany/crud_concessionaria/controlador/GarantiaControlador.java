package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.GarantiaDao;
import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Garantia;
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

@WebServlet(WebConstantes.BASE_PATH + "/GarantiaControlador")
public class GarantiaControlador extends HttpServlet {
    private CarroDao carroDao;
    private GarantiaDao garantiaDao;
    private Garantia garantia;
    String garantia_id = "";
    String carroGarantia = "";
    String data_inicio = "";
    String data_fim = "";
    String descricao = "";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        garantiaDao = new GarantiaDao();
        garantia = new Garantia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            garantia_id = request.getParameter("garantia_id");
            carroGarantia = request.getParameter("carroGarantia");
            data_inicio = request.getParameter("data_inicio");
            data_fim = request.getParameter("data_fim");
            descricao = request.getParameter("descricao");
            
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
        garantia.getCarroGarantia().setCarro_id(Integer.valueOf(carroGarantia));
        garantia.setData_inicio(converte.converteCalendario(data_inicio));
        garantia.setData_fim(converte.converteCalendario(data_fim));
        garantia.setDescricao(descricao);

        garantiaDao.salvar(garantia);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("garantia_id", garantia_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("carroGarantia", carroGarantia);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("descricao", descricao);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("garantia_id", garantia_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("carroGarantia", carroGarantia);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("descricao", descricao);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        garantia.setGarantia_id(Integer.valueOf(garantia_id));
        garantia.getCarroGarantia().setCarro_id(Integer.valueOf(carroGarantia));
        garantia.setData_inicio(converte.converteCalendario(data_inicio));
        garantia.setData_fim(converte.converteCalendario(data_fim));
        garantia.setDescricao(descricao);
        garantiaDao.alterar(garantia);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        garantia.setGarantia_id(Integer.valueOf(garantia_id));
        garantia.getCarroGarantia().setCarro_id(Integer.valueOf(carroGarantia));
        garantia.setData_inicio(converte.converteCalendario(data_inicio));
        garantia.setData_fim(converte.converteCalendario(data_fim));
        garantia.setDescricao(descricao);
        garantiaDao.excluir(garantia);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("garantia_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("carroGarantia", "");
        request.setAttribute("data_inicio", "");
        request.setAttribute("data_fim", "");
        request.setAttribute("descricao", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<Garantia> garantias = garantiaDao.buscarTodas();
        request.setAttribute("garantias", garantias);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroGarantia.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (carroGarantia == null || carroGarantia.isEmpty()
                || data_inicio == null || data_inicio.isEmpty()
                || data_fim == null || data_fim.isEmpty()
                || descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
