package com.felipe.soc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository //Indica que a classe vai ser usada para se comunicar com o DB
public class AlertaDAO {

    @Autowired //Injeção de dependencia (Conexão com o PostgreSQL)
    DataSource dataSource; //conexão física com o DB
    
    JdbcTemplate jdbc; //Ferramenta do Spring responsável por simplificar as queries

    @PostConstruct //Roda o método abaixo automaticamente assim que o spring cria a classe
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    //Create
    public void inserirChamado(Alerta ale){
        String sql = "INSERT INTO Alerta(descricao, status) VALUES(?,?)";
        Object obj[] = new Object[2];
        obj[0] = ale.getDescricao();
        obj[1] = ale.getStatus().name();
        jdbc.update(sql, obj);
    }


    //Update
    public void atualizarChamado(int id, Alerta aler){
        String sql = "UPDATE alerta SET descricao = ?, status = ? where id = ?";
        Object obj[] = new Object[3];
        obj[0] = aler.getDescricao();
        obj[1] = aler.getStatus().name(); //Extrai a enum em formato String
        obj[2] = id; // id do where para atualizar apenas o registro correto
        jdbc.update(sql, obj); //Dispara a query e o vetor de dados no DB
    }

    //Read
    public Alerta obterChamado(int id){
        String sql = "SELECT * FROM alerta where id = ?"; //Busca um alerta especifico

        //queryforMap executa o SELECT e retorna uma linha do banco como um Map(chave, valor)
        //.converter transforma o Map em um objeto do tipo Alerta
        return Alerta.converter((Map<String,Object>) jdbc.queryForMap(sql,id));
    }

    //Read
    public List<Alerta> obterTodosChamados(){
        String sql = "SELECT * FROM alerta";

        //Cada linha da tabela vira um Map dentro da Lista
        List<Map<String,Object>> listaChamados = jdbc.queryForList(sql);
        //Cria uma lista vazia do tipo Alerta
        ArrayList<Alerta> aux = new ArrayList<>();

        //Varre a lista que veio do DB, converte cada linha em um obj do tipo ALerta e
        // preenche o ArrayList aux
        for(Map<String, Object> chamado : listaChamados){
            aux.add(Alerta.converter(chamado));
        }

        return aux;
    }

    //Delete
    public void deletarAlerta(int id){
        try {

            //Busca o chamado no banco antes de disparar a exceção
        Alerta deletar = obterChamado(id);

        String sql = "DELETE FROM alerta where id = ?";
        jdbc.update(sql, id);

        System.out.println("Alerta " + deletar.getDescricao() + " foi deletado!");
        } catch (EmptyResultDataAccessException e) {
            //Mensagem amigável caso o obterChamado nao encontre o ID
            System.out.println("ERRO: Alerta " + id + " NÃO existe!");
        }
      

    }


}
