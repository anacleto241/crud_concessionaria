package com.mycompany.crud_concessionaria.modelo.dao;

import com.mycompany.crud_concessionaria.modelo.entidade.TestDrive;
import com.mycompany.crud_concessionaria.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fabri
 */
public class TestDriveDao extends GenericoDAO<TestDrive> {
    
    public void salvar(TestDrive td) {
        String insert = "INSERT INTO TEST_DRIVE(CLIENTE_ID, CARRO_ID, DATA_TEST_DRIVE, RESULTADO) VALUES (?,?,?,?)";
        save(insert, td.getCliente().getCliente_id(), td.getCarro().getCarro_id(), td.getData_test_drive(), td.getResultado());
    }
    
    public void alterar(TestDrive td) {
        String update = "UPDATE TEST_DRIVE SET CLIENTE_ID=?, CARRO_ID=?, DATA_TEST_DRIVE=?, RESULTADO=? WHERE TEST_DRIVE_ID=?";
        save(update, td.getCliente().getCliente_id(), td.getCarro().getCarro_id(), td.getData_test_drive(), td.getResultado(), td.getTest_drive_id());
    }
    
    public void excluir(TestDrive td) {
        String delete = "DELETE FROM TEST_DRIVE WHERE TEST_DRIVE_ID=?";
        save(delete, td.getTest_drive_id());
    }
    
    public TestDrive buscarPorId(int id) {
        String select = "SELECT * FROM TEST_DRIVE WHERE TEST_DRIVE_ID=?";
        return buscarPorId(select, new TestDriveRowMapper(), id);
    }
    
    public List<TestDrive> buscarTodos() {
        String select = "SELECT * FROM TEST_DRIVE";
        return buscarTodos(select, new TestDriveRowMapper());
    }
    
    public static class TestDriveRowMapper implements RowMapper<TestDrive> {
        CarroDao carroDao = new CarroDao();
        ClienteDao clienteDao = new ClienteDao();
        ConverteData converte = new ConverteData();
        
        @Override
        public TestDrive mapRow(ResultSet rs) throws SQLException {
            TestDrive testDrive = new TestDrive();
            testDrive.setTest_drive_id(rs.getInt("TEST_DRIVE_ID"));
            testDrive.setCliente(clienteDao.buscarPorId(rs.getInt("CLIENTE_ID")));
            testDrive.setCarro(carroDao.buscarPorId(rs.getInt("CARRO_ID")));
            testDrive.setData_test_drive(converte.converteCalendario(rs.getDate("DATA_TEST_DRIVE")));
            testDrive.setResultado(rs.getString("RESULTADO"));
            return testDrive;
        }
    }
}
