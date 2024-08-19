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
public class InspecaoCarro {
    private Integer inspecao_id;
    private Carro carroInspecao = new Carro();
    private Calendar data_inspecao;
    private String condicao_geral;
    private String observacoes;

    public Integer getInspecao_id() {
        return inspecao_id;
    }

    public void setInspecao_id(Integer inspecao_id) {
        this.inspecao_id = inspecao_id;
    }

    public Carro getCarroInspecao() {
        return carroInspecao;
    }

    public void setCarroInspecao(Carro carroInspecao) {
        this.carroInspecao = carroInspecao;
    }

    public Calendar getData_inspecao() {
        return data_inspecao;
    }

    public void setData_inspecao(Calendar data_inspecao) {
        this.data_inspecao = data_inspecao;
    }

    public String getCondicao_geral() {
        return condicao_geral;
    }

    public void setCondicao_geral(String condicao_geral) {
        this.condicao_geral = condicao_geral;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public String getData_inspecao_formatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_inspecao.getTime());
    }
     
 
}
