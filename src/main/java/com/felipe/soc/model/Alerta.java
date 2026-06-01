package com.felipe.soc.model;

import java.util.Map;

public class Alerta {
    private int id;
    private String descricao;
    private StatusAlerta status;

    public Alerta(){}

    //Construtor para o insert
    public Alerta(String descricao, StatusAlerta status) {
        this.descricao = descricao;
        this.status = status;
    }

    //Construtor para o select
    public Alerta(int id, String descricao, StatusAlerta status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusAlerta getStatus() {
        return status;
    }

    public void setStatus(StatusAlerta status) {
        this.status = status;
    }

    //Mapper (recebe um map do jdbc e instancia a classe)
    public static Alerta converter(Map<String,Object> registros){
        int id = (int) registros.get("id");
        String descricao = (String) registros.get("descricao");
        String statusTexto = (String) registros.get("status");
        //Converta a String em Enum
        StatusAlerta statusEnum = StatusAlerta.valueOf(statusTexto); 
        return new Alerta(id, descricao, statusEnum);
    }
    
}
