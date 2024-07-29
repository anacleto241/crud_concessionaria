/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
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
@WebServlet(WebConstantes.BASE_PATH + "/CarroControlador")
public class CarroControlador extends HttpServlet{
    
    private CarroDao carroDao;
    private Carro carro;
    String carro_id = "";
    String marca = "";
    String modelo = "";
    String ano = "";
    String cor = "";
    String preco = "";
    String status = "";
    String percentual_comissao = "";
    String opcao = "";

     @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        carro = new Carro();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            carro_id = request.getParameter("carro_id");
            marca = request.getParameter("marca");
            modelo = request.getParameter("modelo");
            ano = request.getParameter("ano");
            cor = request.getParameter("cor");
            preco = request.getParameter("preco");
            status = request.getParameter("status");
            percentual_comissao = request.getParameter("percentual_comissao");
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
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setAno(ano);
        carro.setCor(cor);
        carro.setPreco(Double.parseDouble(preco));
        carro.setStatus(status);
        carro.setPercentual_comissao(Double.parseDouble(percentual_comissao));
        carroDao.salvar(carro);
        encaminharParaPagina(request, response);
        System.out.println("Cadastro efetuado com sucesso!");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carro_id", carro_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("marca", marca);
        request.setAttribute("modelo", modelo);
        request.setAttribute("ano", ano);
        request.setAttribute("cor", cor);
        request.setAttribute("preco", preco);
        request.setAttribute("status", status);
        request.setAttribute("percentual_comissao", percentual_comissao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carro_id", carro_id);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("marca", marca);
        request.setAttribute("modelo", modelo);
        request.setAttribute("ano", ano);
        request.setAttribute("cor", cor);
        request.setAttribute("preco", preco);
        request.setAttribute("status", status);
        request.setAttribute("percentual_comissao", percentual_comissao);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        carro.setCarro_id(Integer.valueOf(carro_id));
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setAno(ano);
        carro.setCor(cor);
        carro.setPreco(Double.parseDouble(preco));
        carro.setStatus(status);
        carro.setPercentual_comissao(Double.parseDouble(percentual_comissao));
        carroDao.alterar(carro);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carro.setCarro_id(Integer.valueOf(carro_id));
        carro.setMarca(marca);
        carro.setModelo(modelo);
        carro.setAno(ano);
        carro.setCor(cor);
        carro.setPreco(Double.parseDouble(preco));
        carro.setStatus(status);
        carro.setPercentual_comissao(Double.parseDouble(percentual_comissao));
        carroDao.excluir(carro);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carro_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("marca", "");
        request.setAttribute("modelo", "");
        request.setAttribute("ano", "");
        request.setAttribute("cor", "");
        request.setAttribute("preco", "");
        request.setAttribute("status", "");
        request.setAttribute("percentual_comissao", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Carro> carro = carroDao.buscarTodas();
        request.setAttribute("carro", carro);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCarro.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(marca==null || marca.isEmpty()|| modelo==null || modelo.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
