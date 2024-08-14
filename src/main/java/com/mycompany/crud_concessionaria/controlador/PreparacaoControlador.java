/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.controlador;

import com.mycompany.crud_concessionaria.modelo.dao.CarroDao;
import com.mycompany.crud_concessionaria.modelo.dao.PreparacaoDao;
import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
import com.mycompany.crud_concessionaria.modelo.entidade.Preparacao;
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

/**
 *
 * @author fabri
 */

@WebServlet(WebConstantes.BASE_PATH + "/PreparacaoControlador")
public class PreparacaoControlador extends HttpServlet{
    private CarroDao carroDao;
    private Carro carro;
    private PreparacaoDao preparacaoDao;
    private Preparacao preparacao;
    String preparacao_id = "";
    String carroPreparacao = "";
    String data_inicio = "";
    String data_fim = "";
    String descricao = "";
    String custo="";
    String opcao = "";
    private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        carroDao = new CarroDao();
        carro = new Carro();
        preparacaoDao = new PreparacaoDao();
        preparacao = new Preparacao();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            preparacao_id = request.getParameter("preparacao_id");
            carroPreparacao = request.getParameter("carroPreparacao");
            data_inicio = request.getParameter("data_inicio");
            data_fim = request.getParameter("data_fim");
            descricao = request.getParameter("descricao");
            custo = request.getParameter("custo");
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
        preparacao.getCarroPreparacao().setCarro_id(Integer.valueOf(carroPreparacao));
        preparacao.setData_inicio(converte.converteCalendario(data_inicio));
        preparacao.setData_fim(converte.converteCalendario(data_fim));
        preparacao.setDescricao(descricao);
        preparacao.setCusto(Double.parseDouble(custo));
        
        
        preparacaoDao.salvar(preparacao);
        encaminharParaPagina(request, response);
    }

    
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("preparacao_id", preparacao_id);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("carroPreparacao", carroPreparacao);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("descricao", descricao);
        request.setAttribute("custo", custo);
        
        
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    
    
    
   private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("preparacao_id", preparacao_id);
        request.setAttribute("opcao", "confirmarExcluir");
         request.setAttribute("carroPreparacao", carroPreparacao);
        request.setAttribute("data_inicio", ConverteData.convertDateFormat(data_inicio));
        request.setAttribute("data_fim", ConverteData.convertDateFormat(data_fim));
        request.setAttribute("descricao", descricao);
        request.setAttribute("custo", custo);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        preparacao.setPreparacao_id(Integer.valueOf(preparacao_id));
        preparacao.getCarroPreparacao().setCarro_id(Integer.valueOf(carroPreparacao));
        preparacao.setData_inicio(converte.converteCalendario(data_inicio));
        preparacao.setData_fim(converte.converteCalendario(data_fim));
        preparacao.setDescricao(descricao);
        preparacao.setCusto(Double.parseDouble(custo));
        preparacaoDao.alterar(preparacao);
        cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        preparacao.setPreparacao_id(Integer.valueOf(preparacao_id));
        preparacao.getCarroPreparacao().setCarro_id(Integer.valueOf(carroPreparacao));
        preparacao.setData_inicio(converte.converteCalendario(data_inicio));
        preparacao.setData_fim(converte.converteCalendario(data_fim));
        preparacao.setDescricao(descricao);
        preparacao.setCusto(Double.parseDouble(custo));
        preparacaoDao.excluir(preparacao);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("preparacao_id", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("carroPreparacao", "");
        request.setAttribute("data_inicio", "");
        request.setAttribute("data_fim", "");
        request.setAttribute("descricao", "");
        request.setAttribute("custo", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List<Carro> carros = carroDao.buscarTodas();
        request.setAttribute("carros", carros);

        List<Preparacao> preparacaos = preparacaoDao.buscarTodas();
        request.setAttribute("preparacaos", preparacaos);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPreparacao.jsp");
        dispatcher.forward(request, response);

    }
    
     public void validaCampos() {
        if (carroPreparacao == null || carroPreparacao.isEmpty()
                || data_inicio == null || data_inicio.isEmpty()
                || data_fim == null || data_fim.isEmpty()
                || descricao == null || descricao.isEmpty()
                || custo == null || custo.isEmpty()
                ) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }

    
}
