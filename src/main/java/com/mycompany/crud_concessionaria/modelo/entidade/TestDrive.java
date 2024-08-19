/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author fabri
 */
public class TestDrive {
    private Integer test_drive_id;
    private Cliente cliente = new Cliente();
    private Carro carro = new Carro();
    private Calendar data_test_drive;
    private String resultado;

    public Integer getTest_drive_id() {
        return test_drive_id;
    }

    public void setTest_drive_id(Integer test_drive_id) {
        this.test_drive_id = test_drive_id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Calendar getData_test_drive() {
        return data_test_drive;
    }

    public void setData_test_drive(Calendar data_test_drive) {
        this.data_test_drive = data_test_drive;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String getData_test_drive_formatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_test_drive.getTime());
}
    
} 