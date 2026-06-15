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

// A anotação @Controller registra esta classe no Spring Context como um controller.
// Sua função é interceptar requisições HTTP, processá-las (através de chamadas a serviços) e retornar o nome da View correspondente.
@Controller
public class MainController {

    // @Autowired realiza a injeção de dependência automática do ApplicationContext do Spring.
    @Autowired
    ApplicationContext context;

    // @GetMapping mapeia requisições HTTP do tipo GET na rota raiz ("/").
    @GetMapping("/")
    public String index(){
        // Retorna o arquivo "index.html" localizado na pasta de templates.
        return "index";
    }

    // Mapeia a rota GET para exibir o formulário de cadastro.
    @GetMapping("/alerta")
    public String formAlerta(Model model){
        // A interface Model é utilizada para transferir dados do Controller para a View (Thymeleaf).
        // Aqui, instanciamos um objeto Alerta vazio para que o formulário HTML possa fazer o bind dos campos.
        model.addAttribute("alerta", new Alerta());
        return "formAlerta";
    }

    // Mapeia a rota GET para exibir a tabela com todos os registros.
    @GetMapping("/listar/alertas")
    public String listarAlertas(Model model){
        // Instancia o AlertaService a partir do contexto do Spring e executa o método de leitura no banco.
        AlertaService as =  context.getBean(AlertaService.class);
        List<Alerta> lista = as.obterTodosChamados();
        
        // Adiciona a lista de resultados ao Model para ser iterada pelo HTML usando o 'th:each'.
        model.addAttribute("alertas", lista);
        return "formlista";
    }

    // @PostMapping mapeia requisições HTTP do tipo POST,
    //  utilizadas para submissão de dados via formulário.
    @PostMapping("/alerta/salvar")
    public String formAlerta(@ModelAttribute Alerta al, Model model){
        // @ModelAttribute recebe os dados preenchidos no formulário e já os converte automaticamente 
        // em um objeto da classe Alerta.
        
        // Define o status padrão da regra de negócio para novos registros.
        al.setStatus(StatusAlerta.EM_ABERTO);
        
        // Chama a camada de serviço para persistir o objeto no banco de dados.
        AlertaService as = context.getBean(AlertaService.class);
        as.criarChamado(al);
        
        // Retorna a página "sucesso.html".
        return "sucesso";
    }

    // Mapeia a rota GET de edição, interceptando a variável dinâmica da URL.
    @GetMapping("/alertas/{id}/atualizar")
    public String formupdalerta(Model model, @PathVariable int id){
        // @PathVariable extrai o valor do '{id}' presente na URI e o injeta no parâmetro do método.
        model.addAttribute("id", id );
        
        // Executa uma busca por ID no banco de dados.
        AlertaService as = context.getBean(AlertaService.class);
        Alerta alertaCriado = as.obterChamado(id);
        
        // Envia o objeto populado para a View, permitindo que os campos do formulário já venham preenchidos.
        model.addAttribute("alerta", alertaCriado);
        return "formupdalerta";
    }

    // Mapeia o POST para submissão do formulário de atualização.
    @PostMapping("/alertas/{id}/atualizar")
    public String atualizarCliente(Model model, @PathVariable int id, @ModelAttribute Alerta alerta){
        // Chama o método de UPDATE na camada de serviço, passando o ID da URL e o objeto com os novos dados.
        AlertaService as = context.getBean(AlertaService.class);
        as.atualizarChamado(id, alerta);
        
        // O prefixo "redirect:" instrui o Spring a retornar um status HTTP 302, forçando o navegador
        // a fazer uma nova requisição GET para a rota especificada, evitando a re-submissão de formulários.
        return "redirect:/listar/alertas";
    }

    
    @PostMapping("/alertas/{id}/deletar")
    public String deletarChamado(Model model, @PathVariable int id){
        // Aciona o método de DELETE passando o ID extraído da URL.
        AlertaService as = context.getBean(AlertaService.class);
        as.deleterChamado(id);
        
        // Redireciona a requisição para recarregar a lista atualizada sem o registro recém-deletado.
        return "redirect:/listar/alertas";
    }

}