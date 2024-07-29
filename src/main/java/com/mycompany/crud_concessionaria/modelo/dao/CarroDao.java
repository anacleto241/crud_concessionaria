/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Carro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tulio
 */
public class CarroDao extends GenericoDAO<Carro>{
    
   public void salvar(Carro c){
        String insert = "INSERT INTO CARRO(MARCA, MODELO, ANO, COR, PRECO, STATUS, PERCENTUAL_COMISSAO) VALUES (?,?,?,?,?,?,?)";
        save(insert, c.getMarca(), c.getModelo(), c.getAno(), c.getCor(), c.getPreco(), c.getStatus(),
                c.getPercentual_comissao());
    }
    
    public void alterar(Carro c){
        String update = "UPDATE CARRO SET MARCA=?, MODELO=?, ANO=?, COR=?, PRECO=?, STATUS=?, PERCENTUAL_COMISSAO=? WHERE carro_id=?";
        save(update, c.getMarca(), c.getModelo(), c.getAno(), c.getCor(), c.getPreco(), c.getStatus(),
                c.getPercentual_comissao(), c.getCarro_id());
    }
    
    public void excluir(Carro c){
        String delete="DELETE FROM CARRO WHERE CARRO_ID=?";
        save(delete, c.getCarro_id());
    }
    
    public Carro buscarPorId(int id){
        String select = "SELECT * FROM CARRO WHERE CARRO_ID=?";
        return buscarPorId(select, new CarroRowMapper(), id);
    }
    
    public List<Carro> buscarTodas(){
         String select = "SELECT * FROM CARRO";
        return buscarTodos(select, new CarroRowMapper());
    }
    
    public static class CarroRowMapper implements RowMapper<Carro>{

        @Override
        public Carro mapRow(ResultSet rs) throws SQLException {
            Carro carro = new Carro();
            carro.setCarro_id(rs.getInt("CARRO_ID"));
            carro.setMarca(rs.getString("MARCA"));
            carro.setModelo(rs.getString("MODELO"));
            carro.setAno(rs.getString("ANO"));
            carro.setCor(rs.getString("COR"));
            carro.setPreco(rs.getDouble("PRECO"));
            carro.setStatus(rs.getString("STATUS"));
            carro.setPercentual_comissao(rs.getDouble("PERCENTUAL_COMISSAO"));
            return carro;
        }
        
    }
    
}
