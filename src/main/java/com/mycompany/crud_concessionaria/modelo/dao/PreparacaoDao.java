/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Preparacao;
import com.mycompany.crud_concessionaria.modelo.entidade.Preparacao;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author fabri
 */
public class PreparacaoDao extends GenericoDAO<Preparacao>{
      public void salvar(Preparacao p){
        String insert = "INSERT INTO PREPARACAO(CARRO_ID, DATA_INICIO, DATA_FIM, DESCRICAO, CUSTO) VALUES (?,?,?,?,?)";
        save(insert, p.getCarroPreparacao().getCarro_id(), p.getData_inicio(), p.getData_fim(), p.getDescricao(), p.getCusto());
    }
    
    public void alterar(Preparacao p){
        String update = "UPDATE PREPARACAO SET CARRO_ID=?, DATA_INICIO=?, DATA_FIM=?, DESCRICAO=?, CUSTO=? WHERE PREPARACAO_ID=?";
        save(update, p.getCarroPreparacao().getCarro_id(), p.getData_inicio(), p.getData_fim(), p.getDescricao(), p.getCusto(), p.getPreparacao_id());
    }
    
    public void excluir(Preparacao p){
        String delete="DELETE FROM PREPARACAO WHERE PREPARACAO_ID=?";
        save(delete, p.getPreparacao_id());
    }
    
    public Preparacao buscarPorId(int id){
        String select = "SELECT * FROM PREPARACAO WHERE PREPARACAO_ID=?";
        return buscarPorId(select, new PreparacaoRowMapper(), id);
    }
    
    public List<Preparacao> buscarTodas(){
         String select = "SELECT * FROM PREPARACAO";
        return buscarTodos(select, new PreparacaoRowMapper());
    }
    
    public static class PreparacaoRowMapper implements RowMapper<Preparacao>{
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();
        @Override
        public Preparacao mapRow(ResultSet rs) throws SQLException {
            Preparacao preparacao = new Preparacao();
            preparacao.setPreparacao_id(rs.getInt("PREPARACAO_ID"));
            preparacao.setCarroPreparacao(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            preparacao.setData_inicio(converte.converteCalendario(rs.getDate("DATA_INICIO")));
            preparacao.setData_fim(converte.converteCalendario(rs.getDate("DATA_FIM")));
            preparacao.setDescricao(rs.getString("DESCRICAO"));
            preparacao.setCusto(rs.getDouble("CUSTO"));
            return preparacao;
        }
        
    }
    
}
