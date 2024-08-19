package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.dao.ClienteDao;
import com.mycompany.crud_concessionaria.modelo.dao.TestDriveDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
import com.mycompany.crud_concessionaria.modelo.entidade.Cliente;
import com.mycompany.crud_concessionaria.modelo.entidade.TestDrive;
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

@WebServlet(WebConstantes.BASE_PATH + "/TestDriveControlador")
public class TestDriveControlador extends HttpServlet {
    private CarroDao carroDao;
    private ClienteDao clienteDao;
    private TestDriveDao testDriveDao;
    private TestDrive testDrive;
    private String testDrive_id = "";
    private String clienteTestDrive = "";
    private String carroTestDrive = "";
    private String data_test_drive = "";
    private String resultado = "";
    private String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        clienteDao = new ClienteDao();
        testDriveDao = new TestDriveDao();
        testDrive = new TestDrive();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            testDrive_id = request.getParameter("test_drive_id");
            clienteTestDrive = request.getParameter("clienteTestDrive");
            carroTestDrive = request.getParameter("carroTestDrive");
            data_test_drive = request.getParameter("data_test_drive");
            resultado = request.getParameter("resultado");
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
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos.");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        testDrive.getCliente().setCliente_id(Integer.valueOf(clienteTestDrive));
        testDrive.getCarro().setCarro_id(Integer.valueOf(carroTestDrive));
        testDrive.setData_test_drive(converte.converteCalendario(data_test_drive));
        testDrive.setResultado(resultado);

        testDriveDao.salvar(testDrive);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("test_drive_id", testDrive_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("clienteTestDrive", clienteTestDrive);
        request.setAttribute("carroTestDrive", carroTestDrive);
        request.setAttribute("data_test_drive", ConverteData.convertDateFormat(data_test_drive));
        request.setAttribute("resultado", resultado);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("test_drive_id", testDrive_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("clienteTestDrive", clienteTestDrive);
        request.setAttribute("carroTestDrive", carroTestDrive);
        request.setAttribute("data_test_drive", ConverteData.convertDateFormat(data_test_drive));
        request.setAttribute("resultado", resultado);

        request.setAttribute("mensagem", "Clique em salvar para excluir.");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        testDrive.setTest_drive_id(Integer.valueOf(testDrive_id));
        testDrive.getCliente().setCliente_id(Integer.valueOf(clienteTestDrive));
        testDrive.getCarro().setCarro_id(Integer.valueOf(carroTestDrive));
        testDrive.setData_test_drive(converte.converteCalendario(data_test_drive));
        testDrive.setResultado(resultado);

        testDriveDao.alterar(testDrive);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        testDrive.setTest_drive_id(Integer.valueOf(testDrive_id));
        testDrive.getCliente().setCliente_id(Integer.valueOf(clienteTestDrive));
        testDrive.getCarro().setCarro_id(Integer.valueOf(carroTestDrive));
        testDrive.setData_test_drive(converte.converteCalendario(data_test_drive));
        testDrive.setResultado(resultado);

        testDriveDao.excluir(testDrive);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("test_drive_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("clienteTestDrive", "");
        request.setAttribute("carroTestDrive", "");
        request.setAttribute("data_test_drive", "");
        request.setAttribute("resultado", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);


        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);

        List<TestDrive> testDrives = testDriveDao.buscarTodos();
        request.setAttribute("testDrives", testDrives);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroTestDrive.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (clienteTestDrive == null || clienteTestDrive.isEmpty()
                || carroTestDrive == null || carroTestDrive.isEmpty()
                || data_test_drive == null || data_test_drive.isEmpty()
                || resultado == null || resultado.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }
}
