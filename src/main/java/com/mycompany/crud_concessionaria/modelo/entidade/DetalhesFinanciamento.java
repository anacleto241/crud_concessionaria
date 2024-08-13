/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.entidade;

/**
 *
 * @author fabri
 */
public class DetalhesFinanciamento {
    private Integer financiamento_id;
    private Pagamento pagamentoFinanciamento = new Pagamento();
    private double entrada;
    private int parcelas;
    private double valor_parcela;

    public Integer getFinanciamento_id() {
        return financiamento_id;
    }

    public void setFinanciamento_id(Integer financiamento_id) {
        this.financiamento_id = financiamento_id;
    }

    public Pagamento getPagamentoFinanciamento() {
        return pagamentoFinanciamento;
    }

    public void setPagamentoFinanciamento(Pagamento pagamentoFinanciamento) {
        this.pagamentoFinanciamento = pagamentoFinanciamento;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

   
    
}
