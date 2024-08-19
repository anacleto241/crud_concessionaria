package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.AgendamentoDao;
import com.mycompany.crud_concessionaria.modelo.dao.ClienteDao;
import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Agendamento;
import com.mycompany.crud_concessionaria.modelo.entidade.Cliente;
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

@WebServlet(WebConstantes.BASE_PATH + "/AgendamentoControlador")
public class AgendamentoControlador extends HttpServlet {
    private ClienteDao clienteDao;
    private CarroDao carroDao;
    private AgendamentoDao agendamentoDao;
    private Agendamento agendamento;
    String agendamento_id = "";
    String clienteAgendamento = "";
    String carroAgendamento = "";
    String data_agendamento = "";
    String descricao = "";
    String status = "";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        carroDao = new CarroDao();
        agendamentoDao = new AgendamentoDao();
        agendamento = new Agendamento();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            agendamento_id = request.getParameter("agendamento_id");
            clienteAgendamento = request.getParameter("clienteAgendamento");
            carroAgendamento = request.getParameter("carroAgendamento");
            data_agendamento = request.getParameter("data_agendamento");
            descricao = request.getParameter("descricao");
            status = request.getParameter("status");
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
        agendamento.getClienteAgendamento().setCliente_id(Integer.valueOf(clienteAgendamento));
        agendamento.getCarroAgendamento().setCarro_id(Integer.valueOf(carroAgendamento));
        agendamento.setData_agendamento(converte.converteCalendario(data_agendamento));
        agendamento.setDescricao(descricao);
        agendamento.setStatus(status);

        agendamentoDao.salvar(agendamento);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("agendamento_id", agendamento_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("clienteAgendamento", clienteAgendamento);
        request.setAttribute("carroAgendamento", carroAgendamento);
        request.setAttribute("data_agendamento", ConverteData.convertDateFormat(data_agendamento));
        request.setAttribute("descricao", descricao);
        request.setAttribute("status", status);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("agendamento_id", agendamento_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("clienteAgendamento", clienteAgendamento);
        request.setAttribute("carroAgendamento", carroAgendamento);
        request.setAttribute("data_agendamento", ConverteData.convertDateFormat(data_agendamento));
        request.setAttribute("descricao", descricao);
        request.setAttribute("status", status);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        agendamento.setAgendamento_id(Integer.valueOf(agendamento_id));
        agendamento.getClienteAgendamento().setCliente_id(Integer.valueOf(clienteAgendamento));
        agendamento.getCarroAgendamento().setCarro_id(Integer.valueOf(carroAgendamento));
        agendamento.setData_agendamento(converte.converteCalendario(data_agendamento));
        agendamento.setDescricao(descricao);
        agendamento.setStatus(status);
        agendamentoDao.alterar(agendamento);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        agendamento.setAgendamento_id(Integer.valueOf(agendamento_id));
        agendamento.getClienteAgendamento().setCliente_id(Integer.valueOf(clienteAgendamento));
        agendamento.getCarroAgendamento().setCarro_id(Integer.valueOf(carroAgendamento));
        agendamento.setData_agendamento(converte.converteCalendario(data_agendamento));
        agendamento.setDescricao(descricao);
        agendamento.setStatus(status);
        agendamentoDao.excluir(agendamento);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("agendamento_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("clienteAgendamento", "");
        request.setAttribute("carroAgendamento", "");
        request.setAttribute("data_agendamento", "");
        request.setAttribute("descricao", "");
        request.setAttribute("status", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);

        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<Agendamento> agendamentos = agendamentoDao.buscarTodas();
        request.setAttribute("agendamentos", agendamentos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroAgendamento.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (clienteAgendamento == null || clienteAgendamento.isEmpty()
                || carroAgendamento == null || carroAgendamento.isEmpty()
                || data_agendamento == null || data_agendamento.isEmpty()
                || descricao == null || descricao.isEmpty()
                || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
