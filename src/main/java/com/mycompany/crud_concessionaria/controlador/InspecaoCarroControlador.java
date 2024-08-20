package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.InspecaoCarroDao;
import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.entidade.InspecaoCarro;
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

@WebServlet(WebConstantes.BASE_PATH + "/InspecaoCarroControlador")
public class InspecaoCarroControlador extends HttpServlet {
    private CarroDao carroDao;
    private InspecaoCarroDao inspecaoCarroDao;
    private InspecaoCarro inspecaoCarro;
    String inspecao_id = "";
    String carroInspecao = "";
    String data_inspecao = "";
    String condicao_geral = "";
    String observacoes = "";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        inspecaoCarroDao = new InspecaoCarroDao();
        inspecaoCarro = new InspecaoCarro();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            inspecao_id = request.getParameter("inspecao_id");
            carroInspecao = request.getParameter("carroInspecao");
            data_inspecao = request.getParameter("data_inspecao");
            condicao_geral = request.getParameter("condicao_geral");
            observacoes = request.getParameter("observacoes");

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
        inspecaoCarro.getCarroInspecao().setCarro_id(Integer.valueOf(carroInspecao));
        inspecaoCarro.setData_inspecao(converte.converteCalendario(data_inspecao));
        inspecaoCarro.setCondicao_geral(condicao_geral);
        inspecaoCarro.setObservacoes(observacoes);

        inspecaoCarroDao.salvar(inspecaoCarro);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("inspecao_id", inspecao_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("carroInspecao", carroInspecao);
        request.setAttribute("data_inspecao", ConverteData.convertDateFormat(data_inspecao));
        request.setAttribute("condicao_geral", condicao_geral);
        request.setAttribute("observacoes", observacoes);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("inspecao_id", inspecao_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("carroInspecao", carroInspecao);
        request.setAttribute("data_inspecao", ConverteData.convertDateFormat(data_inspecao));
        request.setAttribute("condicao_geral", condicao_geral);
        request.setAttribute("observacoes", observacoes);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        inspecaoCarro.setInspecao_id(Integer.valueOf(inspecao_id));
        inspecaoCarro.getCarroInspecao().setCarro_id(Integer.valueOf(carroInspecao));
        inspecaoCarro.setData_inspecao(converte.converteCalendario(data_inspecao));
        inspecaoCarro.setCondicao_geral(condicao_geral);
        inspecaoCarro.setObservacoes(observacoes);
        inspecaoCarroDao.alterar(inspecaoCarro);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        inspecaoCarro.setInspecao_id(Integer.valueOf(inspecao_id));
        inspecaoCarro.getCarroInspecao().setCarro_id(Integer.valueOf(carroInspecao));
        inspecaoCarro.setData_inspecao(converte.converteCalendario(data_inspecao));
        inspecaoCarro.setCondicao_geral(condicao_geral);
        inspecaoCarro.setObservacoes(observacoes);
        inspecaoCarroDao.excluir(inspecaoCarro);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("inspecao_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("carroInspecao", "");
        request.setAttribute("data_inspecao", "");
        request.setAttribute("condicao_geral", "");
        request.setAttribute("observacoes", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<InspecaoCarro> inspecoes = inspecaoCarroDao.buscarTodas();
        request.setAttribute("inspecoes", inspecoes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroInspecaoCarro.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (carroInspecao == null || carroInspecao.isEmpty()
                || data_inspecao == null || data_inspecao.isEmpty()
                || condicao_geral == null || condicao_geral.isEmpty()
                || observacoes == null || observacoes.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
