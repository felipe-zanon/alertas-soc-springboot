package com.felipe.soc.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// A anotação @Service registra esta classe no contêiner do Spring como um componente de serviço.
// Ela estabelece a camada de Regras de Negócio, isolando o Controller do acesso direto ao banco de dados.
@Service
public class AlertaService {
    
    // @Autowired realiza a injeção de dependência. O Spring instancia e fornece automaticamente
    // o  AlertaDAO.
    @Autowired
    AlertaDAO adao;

    // Método para operação de CREATE. Recebe um objeto Alerta 
    // e o delega para o método de inserção correspondente na camada DAO.
    public void criarChamado(Alerta a){
        adao.inserirChamado(a);
    }

    // Método para operação de READ (Específico). Recebe um ID do tipo inteiro, 
    // aciona a consulta no DAO e retorna a instância exata do Alerta encontrado.
    public Alerta obterChamado(int id){
        return adao.obterChamado(id);
    } 

    // Método para operação de READ. Solicita ao DAO a listagem completa 
    // dos registros na tabela e retorna uma coleção do tipo List<Alerta>.
    public List<Alerta> obterTodosChamados(){
        return adao.obterTodosChamados();
    }

    // Método para operação de UPDATE. Recebe o ID do registro que será alterado e 
    // o objeto Alerta com os novos valores, delegando a execução do comando SQL ao DAO.
    public void atualizarChamado(int id, Alerta a){
        adao.atualizarChamado(id, a);
    }

    // Método para operação de DELETE. Recebe o ID alvo da exclusão do Controller 
    // e aciona o método que contém as validações de exclusão segura na camada DAO.
    public void deleterChamado(int id){
        adao.deletarAlerta(id);
    }
}