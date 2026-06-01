package com.felipe.soc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository //Indica que a classe vai ser usada para se comunicar com o DB
public class AlertaDAO {

    @Autowired //Injeção de dependencia
    DataSource dataSource; //conexão física com o DB
    
    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirChamado(Alerta ale){
        String sql = "INSERT INTO Alerta(descricao, status) VALUES(?,?)";
        Object obj[] = new Object[2];
        obj[0] = ale.getDescricao();
        obj[1] = ale.getStatus().name();
        jdbc.update(sql, obj);
    }

    public void atualizarChamado(int id, Alerta aler){
        String sql = "UPDATE alerta SET descricao = ?, status = ? where id = ?";
        Object obj[] = new Object[3];
        obj[0] = aler.getDescricao();
        obj[1] = aler.getStatus().name();
        obj[2] = id;
        jdbc.update(sql, obj);
    }

    public Alerta obterChamado(int id){
        String sql = "SELECT * FROM alerta where id = ?";
        return Alerta.converter((Map<String,Object>) jdbc.queryForMap(sql,id));
    }

    public List<Alerta> obterTodosChamados(){
        String sql = "SELECT * FROM alerta";
        List<Map<String,Object>> listaChamados = jdbc.queryForList(sql);
        ArrayList<Alerta> aux = new ArrayList<>();
        for(Map<String, Object> chamado : listaChamados){
            aux.add(Alerta.converter(chamado));
        }

        return aux;
    }

    public void deletarChamado(int id, Alerta al){
        String sql = "DELETE * FROM alertA WHERE id = ?";
        
    }

}
