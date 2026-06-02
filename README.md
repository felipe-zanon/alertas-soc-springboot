# Gerenciador de chamados

![Status](https://img.shields.io/badge/Status-Concluído-success)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Nuvem-blue)
![Docker](https://img.shields.io/badge/Docker-Pronto-2496ED)

Um sistema de gerenciamento de incidentes e alertas de segurança desenvolvido com foco em arquitetura defensiva, integridade de dados e deploy contínuo. 

🔗 **Acesse a aplicação rodando na nuvem:** https://alertas-soc-springboot.onrender.com

##  Visão Geral
Este projeto foi construído para simular a fila de atendimento de um Centro de Operações de Segurança (SOC). Ele permite o registro, atualização, listagem e exclusão de alertas de segurança em tempo real, utilizando arquitetura MVC  e um banco de dados relacional hospedado na nuvem.

## Destaques de Segurança (Security-First)
Diferente de CRUDS tradicionais, este projeto implementa práticas de desenvolvimento seguro desde a sua concepção:
* **Prevenção contra SQL Injection (SQLi):** Utilização estrita de *Prepared Statements* via `JdbcTemplate` do Spring, garantindo que inputs de usuários sejam tratados como dados literais e nunca como comandos executáveis.
* **Validação de Integridade (Fail-Safe):** Operações críticas, como a exclusão de alertas, possuem camadas de validação prévia (`EmptyResultDataAccessException`) para evitar a execução de comandos fantasmas no banco de dados.
* **Segurança de Credenciais:** As chaves de acesso ao banco de dados não estão expostas no código-fonte. O sistema foi arquitetado para consumir essas credenciais dinamicamente via **Variáveis de Ambiente** durante o deploy na nuvem.

## Tecnologias Utilizadas
* **Backend:** Java 21, Spring Boot, Spring Data JDBC
* **Banco de Dados:** PostgreSQL (Hospedado no Render)
* **Frontend:** HTML5, Thymeleaf, Bootstrap 5
* **Infraestrutura e Deploy:** Docker, Render (CI/CD)

## Como executar o projeto localmente

1. Clone este repositório:
   ```bash
   git clone https://github.com/felipe-zanon/alertas-soc-springboot