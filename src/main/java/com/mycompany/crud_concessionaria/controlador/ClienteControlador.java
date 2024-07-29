/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.controlador;


import com.mycompany.crud_concessionaria.modelo.dao.ClienteDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Cliente;
import com.mycompany.crud_concessionaria.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author fabri
 */

@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet{
      
    private ClienteDao clienteDao;
    private Cliente cliente;
    String cliente_id = "";
    String nome = "";
    String endereco = "";
    String telefone = "";
    String email = "";
    String opcao = "";

     @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        cliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            cliente_id = request.getParameter("cliente_id");
            nome = request.getParameter("nome");
            endereco = request.getParameter("endereco");
            telefone = request.getParameter("telefone");
            email = request.getParameter("email");
            opcao = request.getParameter("opcao");
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
                    throw new IllegalArgumentException("Opção inválida"+opcao);
            }
          

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        clienteDao.salvar(cliente);
        encaminharParaPagina(request, response);
        System.out.println("Cadastro efetuado com sucesso!");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cliente_id", cliente_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("endereco", endereco);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cliente_id", cliente_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("endereco", endereco);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setCliente_id(Integer.valueOf(cliente_id));
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        clienteDao.alterar(cliente);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente.setCliente_id(Integer.valueOf(cliente_id));
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        clienteDao.excluir(cliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cliente_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("endereco", "");
        request.setAttribute("telefone", "");
        request.setAttribute("email", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> cliente = clienteDao.buscarTodas();
        request.setAttribute("cliente", cliente);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nome==null || nome.isEmpty()|| endereco==null || endereco.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
