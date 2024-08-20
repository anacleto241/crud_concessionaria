package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Garantia;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GarantiaDao extends GenericoDAO<Garantia> {

    public void salvar(Garantia g) {
        String insert = "INSERT INTO GARANTIA(CARRO_ID, DATA_INICIO, DATA_FIM, DESCRICAO) VALUES (?,?,?,?)";
        save(insert, g.getCarroGarantia().getCarro_id(), g.getData_inicio(), g.getData_fim(), g.getDescricao());
    }

    public void alterar(Garantia g) {
        String update = "UPDATE GARANTIA SET CARRO_ID=?, DATA_INICIO=?, DATA_FIM=?, DESCRICAO=? WHERE GARANTIA_ID=?";
        save(update, g.getCarroGarantia().getCarro_id(), g.getData_inicio(), g.getData_fim(), g.getDescricao(), g.getGarantia_id());
    }

    public void excluir(Garantia g) {
        String delete = "DELETE FROM GARANTIA WHERE GARANTIA_ID=?";
        save(delete, g.getGarantia_id());
    }

    public Garantia buscarPorId(int id) {
        String select = "SELECT * FROM GARANTIA WHERE GARANTIA_ID=?";
        return buscarPorId(select, new GarantiaRowMapper(), id);
    }

    public List<Garantia> buscarTodas() {
        String select = "SELECT * FROM GARANTIA";
        return buscarTodos(select, new GarantiaRowMapper());
    }

    public static class GarantiaRowMapper implements RowMapper<Garantia> {
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();

        @Override
        public Garantia mapRow(ResultSet rs) throws SQLException {
            Garantia garantia = new Garantia();
            garantia.setGarantia_id(rs.getInt("GARANTIA_ID"));
            garantia.setCarroGarantia(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            garantia.setData_inicio(converte.converteCalendario(rs.getDate("DATA_INICIO")));
            garantia.setData_fim(converte.converteCalendario(rs.getDate("DATA_FIM")));
            garantia.setDescricao(rs.getString("DESCRICAO"));
            return garantia;
        }
    }
}
