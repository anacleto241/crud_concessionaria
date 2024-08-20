package com.mycompany.crud_concessionaria.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Venda {

    private Integer venda_id;
    private Carro carroVenda = new Carro();
    private Cliente clienteVenda = new Cliente();
    private Funcionario funcionarioVenda = new Funcionario();
    private Calendar data_venda;
    private double valor_venda;
    private double desconto_carro;

    // Getters and Setters
    public Integer getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Integer venda_id) {
        this.venda_id = venda_id;
    }

    public Carro getCarroVenda() {
        return carroVenda;
    }

    public void setCarroVenda(Carro carroVenda) {
        this.carroVenda = carroVenda;
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public void setClienteVenda(Cliente clienteVenda) {
        this.clienteVenda = clienteVenda;
    }

    public Funcionario getFuncionarioVenda() {
        return funcionarioVenda;
    }

    public void setFuncionarioVenda(Funcionario funcionarioVenda) {
        this.funcionarioVenda = funcionarioVenda;
    }

    public Calendar getData_venda() {
        return data_venda;
    }

    public void setData_venda(Calendar data_venda) {
        this.data_venda = data_venda;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public double getDesconto_carro() {
        return desconto_carro;
    }

    public void setDesconto_carro(double desconto_carro) {
        this.desconto_carro = desconto_carro;
    }

    public String getData_venda_formatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_venda.getTime());
    }
}
