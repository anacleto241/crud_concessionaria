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
    String descricaoo = "";
    String custo;
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
            nomePreparacao = request.getParameter("nomePreparacao");
            salarioPreparacao = request.getParameter("salarioPreparacao");
            nascimentoPreparacao = request.getParameter("nascimentoPreparacao");
            carroPreparacao = request.getParameter("carroPreparacao");
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
        preparacao.setNomePreparacao(nomePreparacao);
        preparacao.setSalarioPreparacao(Double.valueOf(salarioPreparacao));
        preparacao.setNascimentoPreparacao(converte.converteCalendario(nascimentoPreparacao));
        preparacao.getCarroPreparacao().setCodigoCarro(Integer.valueOf(carroPreparacao));
        preparacaoDao.salvar(preparacao);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoPreparacao", codigoPreparacao);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomePreparacao", nomePreparacao);
        request.setAttribute("salarioPreparacao", salarioPreparacao);
        request.setAttribute("nascimentoPreparacao", ConverteData.convertDateFormat(nascimentoPreparacao));
        request.setAttribute("carroPreparacao", carroPreparacao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
   private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoPreparacao", codigoPreparacao);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomePreparacao", nomePreparacao);
        request.setAttribute("salarioPreparacao", salarioPreparacao);
        request.setAttribute("nascimentoPreparacao", ConverteData.convertDateFormat(nascimentoPreparacao));
        request.setAttribute("carroPreparacao", carroPreparacao);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        preparacao.setCodigoPreparacao(Integer.valueOf(codigoPreparacao));
        preparacao.setNomePreparacao(nomePreparacao);
        preparacao.setSalarioPreparacao(Double.valueOf(salarioPreparacao));
        preparacao.setNascimentoPreparacao(converte.converteCalendario(nascimentoPreparacao));
        preparacao.getCarroPreparacao().setCodigoCarro(Integer.valueOf(carroPreparacao));
        preparacaoDao.alterar(preparacao);
        cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        preparacao.setCodigoPreparacao(Integer.valueOf(codigoPreparacao));
        preparacao.setNomePreparacao(nomePreparacao);
        preparacao.setSalarioPreparacao(Double.valueOf(salarioPreparacao));
        preparacao.setNascimentoPreparacao(converte.converteCalendario(nascimentoPreparacao));
        preparacao.getCarroPreparacao().setCodigoCarro(Integer.valueOf(carroPreparacao));
        preparacaoDao.excluir(preparacao);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoPreparacao", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomePreparacao", "");
        request.setAttribute("salarioPreparacao", "");
        request.setAttribute("nascimentoPreparacao", "");
        request.setAttribute("carroPreparacao", "");
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
        if (nomePreparacao == null || nomePreparacao.isEmpty()
                || salarioPreparacao == null || salarioPreparacao.isEmpty()
                || nascimentoPreparacao == null || nascimentoPreparacao.isEmpty()
                || carroPreparacao == null || carroPreparacao.isEmpty()
                ) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");
        }
    }

    
}
