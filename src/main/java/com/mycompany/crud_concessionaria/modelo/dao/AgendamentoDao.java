/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.dao;
import com.mycompany.crud_concessionaria.modelo.entidade.Agendamento;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fabri
 */
public class AgendamentoDao extends GenericoDAO<Agendamento>{
    
    public void salvar(Agendamento a){
        String insert = "INSERT INTO AGENDAMENTO(CLIENTE_ID, CARRO_ID, DATA_AGENDAMENTO, DESCRICAO, STATUS) VALUES (?,?,?,?,?)";
        save(insert, a.getClienteAgendamento().getCliente_id(), a.getCarroAgendamento().getCarro_id(), a.getData_agendamento(), a.getDescricao(), a.getStatus());
    }
    
    public void alterar(Agendamento a){
        String update = "UPDATE AGENDAMENTO SET CLIENTE_ID=?, CARRO_ID=?, DATA_AGENDAMENTO=?, DESCRICAO=?, STATUS=? WHERE AGENDAMENTO_ID=?";
        save(update, a.getClienteAgendamento().getCliente_id(), a.getCarroAgendamento().getCarro_id(), a.getData_agendamento(), a.getDescricao(), a.getStatus(), a.getAgendamento_id());
    }
    
    public void excluir(Agendamento a){
        String delete="DELETE FROM AGENDAMENTO WHERE AGENDAMENTO_ID=?";
        save(delete, a.getAgendamento_id());
    }
    
    public Agendamento buscarPorId(int id){
        String select = "SELECT * FROM AGENDAMENTO WHERE AGENDAMENTO_ID=?";
        return buscarPorId(select, new AgendamentoRowMapper(), id);
    }
    
    public List<Agendamento> buscarTodas(){
         String select = "SELECT * FROM AGENDAMENTO";
        return buscarTodos(select, new AgendamentoRowMapper());
    }
    
    public static class AgendamentoRowMapper implements RowMapper<Agendamento>{
        ClienteDao clienteDao = new ClienteDao();
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();
        
        @Override
        public Agendamento mapRow(ResultSet rs) throws SQLException {
            Agendamento agendamento = new Agendamento();
            agendamento.setAgendamento_id(rs.getInt("AGENDAMENTO_ID"));
            agendamento.setClienteAgendamento(clienteDao.buscarPorId(rs.getInt("CLIENTE_ID")));
            agendamento.setCarroAgendamento(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            agendamento.setData_agendamento(converte.converteCalendario(rs.getDate("DATA_AGENDAMENTO")));
            agendamento.setDescricao(rs.getString("DESCRICAO"));
            agendamento.setStatus(rs.getString("STATUS"));
            return agendamento;
        }
    }
}
