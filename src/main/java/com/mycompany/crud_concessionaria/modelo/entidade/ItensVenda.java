/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_concessionaria.modelo.entidade;


/**
 *
 * @author Fabrício A. Ferreira
 */

public class ItensVenda {
    private Carro carroItensVenda = new Carro();
    private Venda vendaItensVenda = new Venda();
    private Integer venda_id;
    private Integer quantidade;

    public Carro getCarroItensVenda() {
        return carroItensVenda;
    }

    public void setCarroItensVenda(Carro carroItensVenda) {
        this.carroItensVenda = carroItensVenda;
    }

    public Venda getVendaItensVenda() {
        return vendaItensVenda;
    }

    public void setVendaItensVenda(Venda vendaItensVenda) {
        this.vendaItensVenda = vendaItensVenda;
    }

    public Integer getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Integer venda_id) {
        this.venda_id = venda_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
}

