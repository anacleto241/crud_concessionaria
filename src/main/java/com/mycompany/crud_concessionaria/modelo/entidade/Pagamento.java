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
public class Pagamento {
    private Integer pagamento_id;
    private Venda venda = new Venda();
    private String tipo_pagamento;
    private double valor;
    private Calendar data_pagamento;
    private String metodo_pagamento;

    public Integer getPagamento_id() {
        return pagamento_id;
    }

    public void setPagamento_id(Integer pagamento_id) {
        this.pagamento_id = pagamento_id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Calendar data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }
   
    
}
