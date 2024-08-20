package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.SeguroCarro;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeguroCarroDao extends GenericoDAO<SeguroCarro> {

    public void salvar(SeguroCarro s) {
        String insert = "INSERT INTO SEGURO_CARRO(CARRO_ID, NUMERO_POLIS, DATA_INICIO, DATA_FIM, NOME_SEGURADORA, VALOR_PREMIO) VALUES (?,?,?,?,?,?)";
        save(insert, s.getCarroSeguro().getCarro_id(), s.getNumero_polis(), s.getData_inicio(), s.getData_fim(), s.getNome_seguradora(), s.getValor_premio());
    }

    public void alterar(SeguroCarro s) {
        String update = "UPDATE SEGURO_CARRO SET CARRO_ID=?, NUMERO_POLIS=?, DATA_INICIO=?, DATA_FIM=?, NOME_SEGURADORA=?, VALOR_PREMIO=? WHERE SEGURO_ID=?";
        save(update, s.getCarroSeguro().getCarro_id(), s.getNumero_polis(), s.getData_inicio(), s.getData_fim(), s.getNome_seguradora(), s.getValor_premio(), s.getSeguro_id());
    }

    public void excluir(SeguroCarro s) {
        String delete = "DELETE FROM SEGURO_CARRO WHERE SEGURO_ID=?";
        save(delete, s.getSeguro_id());
    }

    public SeguroCarro buscarPorId(int id) {
        String select = "SELECT * FROM SEGURO_CARRO WHERE SEGURO_ID=?";
        return buscarPorId(select, new SeguroCarroRowMapper(), id);
    }

    public List<SeguroCarro> buscarTodas() {
        String select = "SELECT * FROM SEGURO_CARRO";
        return buscarTodos(select, new SeguroCarroRowMapper());
    }

    public static class SeguroCarroRowMapper implements RowMapper<SeguroCarro> {
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();

        @Override
        public SeguroCarro mapRow(ResultSet rs) throws SQLException {
            SeguroCarro seguroCarro = new SeguroCarro();
            seguroCarro.setSeguro_id(rs.getInt("SEGURO_ID"));
            seguroCarro.setCarroSeguro(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            seguroCarro.setNumero_polis(rs.getString("NUMERO_POLIS"));
            seguroCarro.setData_inicio(converte.converteCalendario(rs.getDate("DATA_INICIO")));
            seguroCarro.setData_fim(converte.converteCalendario(rs.getDate("DATA_FIM")));
            seguroCarro.setNome_seguradora(rs.getString("NOME_SEGURADORA"));
            seguroCarro.setValor_premio(rs.getDouble("VALOR_PREMIO"));
            return seguroCarro;
        }
    }
}
