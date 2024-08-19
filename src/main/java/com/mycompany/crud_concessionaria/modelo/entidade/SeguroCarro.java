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
public class SeguroCarro {
  
   private Integer seguro_id;
    
  private Carro carroSeguro = new Carro();
    
    private String numero_polis;
    
    private Calendar data_inicio;
    
    private Calendar data_fim;
    
    private String nome_seguradora;
    
    private Double valor_premio;

    public Integer getSeguro_id() {
        return seguro_id;
    }

    public void setSeguro_id(Integer seguro_id) {
        this.seguro_id = seguro_id;
    }

    public Carro getCarroSeguro() {
        return carroSeguro;
    }

    public void setCarroSeguro(Carro carroSeguro) {
        this.carroSeguro = carroSeguro;
    }

    public String getNumero_polis() {
        return numero_polis;
    }

    public void setNumero_polis(String numero_polis) {
        this.numero_polis = numero_polis;
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

    public String getNome_seguradora() {
        return nome_seguradora;
    }

    public void setNome_seguradora(String nome_seguradora) {
        this.nome_seguradora = nome_seguradora;
    }

    public Double getValor_premio() {
        return valor_premio;
    }

    public void setValor_premio(Double valor_premio) {
        this.valor_premio = valor_premio;
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
