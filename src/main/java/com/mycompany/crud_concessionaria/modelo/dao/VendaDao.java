package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.Venda;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendaDao extends GenericoDAO<Venda> {

    public void salvar(Venda v) {
        String insert = "INSERT INTO Vendas(CARROVENDA_ID, CLIENTEVENDA_ID, FUNCIONARIOVENDA_ID, DATA_VENDA, VALOR_VENDA, DESCONTO_CARRO) VALUES (?, ?, ?, ?, ?, ?)";
        save(insert, v.getCarroVenda().getCarro_id(), v.getClienteVenda().getCliente_id(), v.getFuncionarioVenda().getFuncionario_id(), v.getData_venda(), v.getValor_venda(), v.getDesconto_carro());
    }

    public void alterar(Venda v) {
        String update = "UPDATE Vendas SET CARROVENDA_ID=?, CLIENTEVENDA_ID=?, FUNCIONARIOVENDA_ID=?, DATA_VENDA=?, VALOR_VENDA=?, DESCONTO_CARRO=? WHERE VENDA_ID=?";
        save(update, v.getCarroVenda().getCarro_id(), v.getClienteVenda().getCliente_id(), v.getFuncionarioVenda().getFuncionario_id(), v.getData_venda(), v.getValor_venda(), v.getDesconto_carro(), v.getVenda_id());
    }

    public void excluir(Venda v) {
        String delete = "DELETE FROM Vendas WHERE VENDA_ID=?";
        save(delete, v.getVenda_id());
    }

    public Venda buscarPorId(int id) {
        String select = "SELECT * FROM Vendas WHERE VENDA_ID=?";
        return buscarPorId(select, new VendaRowMapper(), id);
    }

    public List<Venda> buscarTodas() {
        String select = "SELECT * FROM Vendas";
        return buscarTodos(select, new VendaRowMapper());
    }

    public static class VendaRowMapper implements RowMapper<Venda> {
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        CarroDao carroDao = new CarroDao();
        ConverteData converte = new ConverteData();

        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda venda = new Venda();
            venda.setVenda_id(rs.getInt("VENDA_ID"));
            venda.setCarroVenda(carroDao.buscarPorId(rs.getInt("CARROVENDA_ID")));
            venda.setClienteVenda(clienteDao.buscarPorId(rs.getInt("CLIENTEVENDA_ID")));
            venda.setFuncionarioVenda(funcionarioDao.buscarPorId(rs.getInt("FUNCIONARIOVENDA_ID")));
            venda.setData_venda(converte.converteCalendario(rs.getDate("DATA_VENDA")));
            venda.setValor_venda(rs.getDouble("VALOR_VENDA"));
            venda.setDesconto_carro(rs.getDouble("DESCONTO_CARRO"));
            return venda;
        }
    }
}
