package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.InspecaoCarro;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InspecaoCarroDao extends GenericoDAO<InspecaoCarro> {

    public void salvar(InspecaoCarro i) {
        String insert = "INSERT INTO INSPECAO_CARRO(CARRO_ID, DATA_INSPECAO, CONDICAO_GERAL, OBSERVACOES) VALUES (?,?,?,?)";
        save(insert, i.getCarroInspecao().getCarro_id(), i.getData_inspecao(), i.getCondicao_geral(), i.getObservacoes());
    }

    public void alterar(InspecaoCarro i) {
        String update = "UPDATE INSPECAO_CARRO SET CARRO_ID=?, DATA_INSPECAO=?, CONDICAO_GERAL=?, OBSERVACOES=? WHERE INSPECAO_ID=?";
        save(update, i.getCarroInspecao().getCarro_id(), i.getData_inspecao(), i.getCondicao_geral(), i.getObservacoes(), i.getInspecao_id());
    }

    public void excluir(InspecaoCarro i) {
        String delete = "DELETE FROM INSPECAO_CARRO WHERE INSPECAO_ID=?";
        save(delete, i.getInspecao_id());
    }

    public InspecaoCarro buscarPorId(int id) {
        String select = "SELECT * FROM INSPECAO_CARRO WHERE INSPECAO_ID=?";
        return buscarPorId(select, new InspecaoCarroRowMapper(), id);
    }

    public List<InspecaoCarro> buscarTodas() {
        String select = "SELECT * FROM INSPECAO_CARRO";
        return buscarTodos(select, new InspecaoCarroRowMapper());
    }

    public static class InspecaoCarroRowMapper implements RowMapper<InspecaoCarro> {
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();

        @Override
        public InspecaoCarro mapRow(ResultSet rs) throws SQLException {
            InspecaoCarro inspecaoCarro = new InspecaoCarro();
            inspecaoCarro.setInspecao_id(rs.getInt("INSPECAO_ID"));
            inspecaoCarro.setCarroInspecao(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            inspecaoCarro.setData_inspecao(converte.converteCalendario(rs.getDate("DATA_INSPECAO")));
            inspecaoCarro.setCondicao_geral(rs.getString("CONDICAO_GERAL"));
            inspecaoCarro.setObservacoes(rs.getString("OBSERVACOES"));
            return inspecaoCarro;
        }
    }
}
