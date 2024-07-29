/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fabri
 */
public class FuncionarioDao extends GenericoDAO <Funcionario>{
          public void salvar(Funcionario f){
        String insert = "INSERT INTO FUNCIONARIO(NOME, CARGO, TELEFONE, EMAIL, SALARIO_BASE) VALUES (?,?,?,?,?)";
        save(insert, f.getNome(), f.getCargo(), f.getTelefone(), f.getEmail(), f.getSalario_base());
    }
    
    public void alterar(Funcionario f){
        String update = "UPDATE FUNCIONARIO SET NOME=?, CARGO=?, TELEFONE=?, EMAIL=?, SALARIO_BASE=? WHERE FUNCIONARIO_ID=?";
        save(update, f.getNome(), f.getCargo(), f.getTelefone(), f.getEmail(), f.getSalario_base(),f.getFuncionario_id());
    }
    
    public void excluir(Funcionario f){
        String delete="DELETE FROM FUNCIONARIO WHERE FUNCIONARIO_ID=?";
        save(delete, f.getFuncionario_id());
    }
    
    public Funcionario buscarPorId(int id){
        String select = "SELECT * FROM FUNCIONARIO WHERE FUNCIONARIO_ID=?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }
    
    public List<Funcionario> buscarTodas(){
         String select = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(select, new FuncionarioRowMapper());
    }
    
    public static class FuncionarioRowMapper implements RowMapper<Funcionario>{

        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setFuncionario_id(rs.getInt("FUNCIONARIO_ID"));
            funcionario.setNome(rs.getString("NOME"));
            funcionario.setCargo(rs.getString("CARGO"));
            funcionario.setTelefone(rs.getString("TELEFONE"));
            funcionario.setEmail(rs.getString("EMAIL"));
            funcionario.setSalario_base(rs.getDouble("SALARIO_BASE"));
            return funcionario;
        }
        
    }
    
}
