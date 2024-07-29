/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fabri
 */
public class ClienteDao extends GenericoDAO<Cliente>{
      public void salvar(Cliente c){
        String insert = "INSERT INTO CLIENTE(NOME, ENDERECO, TELEFONE, EMAIL) VALUES (?,?,?,?)";
        save(insert, c.getNome(), c.getEndereco(), c.getTelefone(), c.getEmail());
    }
    
    public void alterar(Cliente c){
        String update = "UPDATE CLIENTE SET NOME=?, ENDERECO=?, TELEFONE=?, EMAIL=? WHERE CLIENTE_ID=?";
        save(update, c.getNome(), c.getEndereco(), c.getTelefone(), c.getEmail(), c.getCliente_id());
    }
    
    public void excluir(Cliente c){
        String delete="DELETE FROM CLIENTE WHERE CLIENTE_ID=?";
        save(delete, c.getCliente_id());
    }
    
    public Cliente buscarPorId(int id){
        String select = "SELECT * FROM CLIENTE WHERE CLIENTE_ID=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }
    
    public List<Cliente> buscarTodas(){
         String select = "SELECT * FROM CLIENTE";
        return buscarTodos(select, new ClienteRowMapper());
    }
    
    public static class ClienteRowMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setCliente_id(rs.getInt("CLIENTE_ID"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setEndereco(rs.getString("ENDERECO"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEmail(rs.getString("EMAIL"));
            return cliente;
        }
        
    }
    
}
