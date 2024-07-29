/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.FuncionarioDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Funcionario;
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

@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet{
      
    private FuncionarioDao funcionarioDao;
    private Funcionario funcionario;
    String funcionario_id = "";
    String nome = "";
    String cargo = "";
    String telefone = "";
    String email = "";
    String salario_base = "";
    String opcao = "";

     @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        funcionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            funcionario_id = request.getParameter("funcionario_id");
            nome = request.getParameter("nome");
            cargo = request.getParameter("cargo");
            telefone = request.getParameter("telefone");
            email = request.getParameter("email");
            salario_base = request.getParameter("salario_base");
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
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.setSalario_base(Double.parseDouble(salario_base));
        funcionarioDao.salvar(funcionario);
        encaminharParaPagina(request, response);
        System.out.println("Cadastro efetuado com sucesso!");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("funcionario_id", funcionario_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("cargo", cargo);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("salario_base",salario_base);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("funcionario_id", funcionario_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("cargo", cargo);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("salario_base",salario_base);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        funcionario.setFuncionario_id(Integer.valueOf(funcionario_id));
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.setSalario_base(Double.parseDouble(salario_base));
    funcionarioDao.alterar(funcionario);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        funcionario.setFuncionario_id(Integer.valueOf(funcionario_id));
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.setSalario_base(Double.parseDouble(salario_base));
        funcionarioDao.excluir(funcionario);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("funcionario_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("cargo", "");
        request.setAttribute("telefone", "");
        request.setAttribute("email", "");
        request.setAttribute("salario_base","");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionario = funcionarioDao.buscarTodas();
        request.setAttribute("funcionario", funcionario);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nome==null || nome.isEmpty()|| cargo==null || cargo.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
