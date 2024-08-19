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
public class Agendamento {
    private Integer agendamento_id;
    private Cliente clienteAgendamento = new Cliente();
    private Carro CarroAgendamento = new Carro();
    private Calendar data_agendamento;
    private String descricao, status;

    public Integer getAgendamento_id() {
        return agendamento_id;
    }

    public void setAgendamento_id(Integer agendamento_id) {
        this.agendamento_id = agendamento_id;
    }

    public Cliente getClienteAgendamento() {
        return clienteAgendamento;
    }

    public void setClienteAgendamento(Cliente clienteAgendamento) {
        this.clienteAgendamento = clienteAgendamento;
    }

    public Carro getCarroAgendamento() {
        return CarroAgendamento;
    }

    public void setCarroAgendamento(Carro CarroAgendamento) {
        this.CarroAgendamento = CarroAgendamento;
    }

    public Calendar getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(Calendar data_agendamento) {
        this.data_agendamento = data_agendamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataAgendamentoFormatada(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_agendamento.getTime()); 
    }
    
}
