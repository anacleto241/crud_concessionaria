/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.entidade;

import java.util.Calendar;

/**
 *
 * @author fabri
 */
public class Venda {
    private Integer venda_id;
    private Carro carro = new Carro();
    private Cliente cliente = new Cliente();
    private Funcionario funcionario = new Funcionario();
    private Calendar data_Venda;
    private double preco_venda;
    private double valor_comissao;

    public Integer getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Integer venda_id) {
        this.venda_id = venda_id;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Calendar getData_Venda() {
        return data_Venda;
    }

    public void setData_Venda(Calendar data_Venda) {
        this.data_Venda = data_Venda;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public double getValor_comissao() {
        return valor_comissao;
    }

    public void setValor_comissao(double valor_comissao) {
        this.valor_comissao = valor_comissao;
    }
    
    
    
}
