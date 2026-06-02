package com.felipe.soc.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {
    
    @Autowired
    AlertaDAO adao;

    public void criarChamado(Alerta a){
        adao.inserirChamado(a);
    }

    public Alerta obterChamado(int id){
        return adao.obterChamado(id);
    } 

    public List<Alerta> obterTodosChamados(){
        return adao.obterTodosChamados();
    }

    public void atualizarChamado(int id, Alerta a){
        adao.atualizarChamado(id, a);
    }

    public void deleterChamado(int id){
        adao.deletarAlerta(id);
    }
}
