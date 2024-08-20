package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.dao.ClienteDao;
import com.mycompany.crud_concessionaria.modelo.dao.FuncionarioDao;
import com.mycompany.crud_concessionaria.modelo.dao.VendaDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
import com.mycompany.crud_concessionaria.modelo.entidade.Cliente;
import com.mycompany.crud_concessionaria.modelo.entidade.Funcionario;
import com.mycompany.crud_concessionaria.modelo.entidade.Venda;
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

@WebServlet(WebConstantes.BASE_PATH + "/VendaControlador")
public class VendaControlador extends HttpServlet {
    private CarroDao carroDao;
    private ClienteDao clienteDao;
    private FuncionarioDao funcionarioDao;
    private VendaDao vendaDao;
    private Venda venda;
    String venda_id = "";
    String carroVenda = "";
    String clienteVenda = "";
    String funcionarioVenda = "";
    String data_venda = "";
    String valor_venda = "";
    String desconto_carro = "";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        clienteDao = new ClienteDao();
        funcionarioDao = new FuncionarioDao();
        vendaDao = new VendaDao();
        venda = new Venda();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            venda_id = request.getParameter("venda_id");
            carroVenda = request.getParameter("carroVenda");
            clienteVenda = request.getParameter("clienteVenda");
            funcionarioVenda = request.getParameter("funcionarioVenda");
            data_venda = request.getParameter("data_venda");
            valor_venda = request.getParameter("valor_venda");
            desconto_carro = request.getParameter("desconto_carro");

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
        venda.getCarroVenda().setCarro_id(Integer.valueOf(carroVenda));
        venda.setClienteVenda(clienteDao.buscarPorId(Integer.valueOf(clienteVenda)));
        venda.setFuncionarioVenda(funcionarioDao.buscarPorId(Integer.valueOf(funcionarioVenda)));
        venda.setData_venda(converte.converteCalendario(data_venda));
        venda.setValor_venda(Double.valueOf(valor_venda));
        venda.setDesconto_carro(Double.valueOf(desconto_carro));

        vendaDao.salvar(venda);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("venda_id", venda_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("carroVenda", carroVenda);
        request.setAttribute("clienteVenda", clienteVenda);
        request.setAttribute("funcionarioVenda", funcionarioVenda);
        request.setAttribute("data_venda", ConverteData.convertDateFormat(data_venda));
        request.setAttribute("valor_venda", valor_venda);
        request.setAttribute("desconto_carro", desconto_carro);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("venda_id", venda_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("carroVenda", carroVenda);
        request.setAttribute("clienteVenda", clienteVenda);
        request.setAttribute("funcionarioVenda", funcionarioVenda);
        request.setAttribute("data_venda", ConverteData.convertDateFormat(data_venda));
        request.setAttribute("valor_venda", valor_venda);
        request.setAttribute("desconto_carro", desconto_carro);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        venda.setVenda_id(Integer.valueOf(venda_id));
        venda.getCarroVenda().setCarro_id(Integer.valueOf(carroVenda));
        venda.setClienteVenda(clienteDao.buscarPorId(Integer.valueOf(clienteVenda)));
        venda.setFuncionarioVenda(funcionarioDao.buscarPorId(Integer.valueOf(funcionarioVenda)));
        venda.setData_venda(converte.converteCalendario(data_venda));
        venda.setValor_venda(Double.valueOf(valor_venda));
        venda.setDesconto_carro(Double.valueOf(desconto_carro));
        vendaDao.alterar(venda);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        venda.setVenda_id(Integer.valueOf(venda_id));
        venda.getCarroVenda().setCarro_id(Integer.valueOf(carroVenda));
        venda.setClienteVenda(clienteDao.buscarPorId(Integer.valueOf(clienteVenda)));
        venda.setFuncionarioVenda(funcionarioDao.buscarPorId(Integer.valueOf(funcionarioVenda)));
        venda.setData_venda(converte.converteCalendario(data_venda));
        venda.setValor_venda(Double.valueOf(valor_venda));
        venda.setDesconto_carro(Double.valueOf(desconto_carro));
        vendaDao.excluir(venda);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("venda_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("carroVenda", "");
        request.setAttribute("clienteVenda", "");
        request.setAttribute("funcionarioVenda", "");
        request.setAttribute("data_venda", "");
        request.setAttribute("valor_venda", "");
        request.setAttribute("desconto_carro", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);

        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        request.setAttribute("funcionarios", funcionarios);

        List<Venda> vendas = vendaDao.buscarTodas();
        request.setAttribute("vendas", vendas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroVenda.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (carroVenda == null || carroVenda.isEmpty()
                || clienteVenda == null || clienteVenda.isEmpty()
                || funcionarioVenda == null || funcionarioVenda.isEmpty()
                || data_venda == null || data_venda.isEmpty()
                || valor_venda == null || valor_venda.isEmpty()
                || desconto_carro == null || desconto_carro.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
