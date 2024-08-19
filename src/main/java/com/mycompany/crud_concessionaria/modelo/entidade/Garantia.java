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
public class Garantia {
    private Integer garantia_id;
    
    private Carro carroGarantia = new Carro();
    
    private Calendar data_inicio;
    
    private Calendar data_fim;
    
    private String descricao;

    public Integer getGarantia_id() {
        return garantia_id;
    }

    public void setGarantia_id(Integer garantia_id) {
        this.garantia_id = garantia_id;
    }

    public Carro getCarroGarantia() {
        return carroGarantia;
    }

    public void setCarroGarantia(Carro carroGarantia) {
        this.carroGarantia = carroGarantia;
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
    
    public String getData_fim_formatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_fim.getTime());
    }
     
    public String getData_inicio_formatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_inicio.getTime());
}
}
