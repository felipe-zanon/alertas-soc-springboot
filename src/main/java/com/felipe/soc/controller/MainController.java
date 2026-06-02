package com.felipe.soc.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.felipe.soc.model.Alerta;
import com.felipe.soc.model.AlertaService;
import com.felipe.soc.model.StatusAlerta;

@Controller
public class MainController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/alerta")
    public String formAlerta(Model model){
        model.addAttribute("alerta", new Alerta());
        return "formAlerta";
    }

    @GetMapping("/listar/alertas")
    public String listarAlertas(Model model){
        AlertaService as =  context.getBean(AlertaService.class);
        List<Alerta> lista = as.obterTodosChamados();
        
        model.addAttribute("alertas", lista);
        return "formlista";
    }

    @PostMapping("/alerta/salvar")
    public String formAlerta(@ModelAttribute Alerta al, Model model){
        al.setStatus(StatusAlerta.EM_ABERTO);
        AlertaService as = context.getBean(AlertaService.class);
        as.criarChamado(al);
        return "sucesso";

    }

    @GetMapping("/alertas/{id}/atualizar")
    public String formupdalerta(Model model, @PathVariable int id){
        model.addAttribute("id", id );
        AlertaService as = context.getBean(AlertaService.class);
        Alerta alertaCriado = as.obterChamado(id);
        model.addAttribute("alerta", alertaCriado);
        return "formupdalerta";
    }

    @PostMapping("/alertas/{id}/atualizar")
    public String atualizarCliente(Model model, @PathVariable int id, @ModelAttribute Alerta alerta){
        AlertaService as = context.getBean(AlertaService.class);
        as.atualizarChamado(id, alerta);
        return "redirect:/listar/alertas";
    }

    @PostMapping("/alertas/{id}/deletar")
    public String deletarChamado(Model model, @PathVariable int id){
        AlertaService as = context.getBean(AlertaService.class);
        as.deleterChamado(id);
        return "redirect:/listar/alertas";
    }




}
