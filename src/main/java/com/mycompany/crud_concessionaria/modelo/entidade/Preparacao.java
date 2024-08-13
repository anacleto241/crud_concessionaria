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
public class Preparacao {
    private Integer preparacao_id;
    private Carro carroPreparacao = new Carro();
    private Calendar data_inicio, data_fim;
    private String descricao;
    private double custo;

    public Integer getPreparacao_id() {
        return preparacao_id;
    }

    public void setPreparacao_id(Integer preparacao_id) {
        this.preparacao_id = preparacao_id;
    }

    public Carro getCarroPreparacao() {
        return carroPreparacao;
    }

    public void setCarroPreparacao(Carro carroPreparacao) {
        this.carroPreparacao = carroPreparacao;
    }



    public Calendar getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }
    
   
    public Calendar getData_fim() {
        return data_fim;
    }

    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
}
