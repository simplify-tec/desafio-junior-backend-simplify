# Desafio sistema de gerenciamento de tarefas (To-Do List)
Repositório para ser usado pelos candidatos à vaga de Desenvolvedor Júnior Backend Liferay da Simplify

## Descrição
- Desenvolva uma aplicação web utilizando uma linguagem de programação e um framework de sua escolha. A aplicação deve consistir em um sistema de gerenciamento de tarefas, onde os usuários podem criar, visualizar, editar e excluir tarefas.

## Requisitos
- Usar banco de dados
- Campos mínimos da entidade de tarefa
    - Nome
    - Descrição
    - Realizado
    - Prioridade
- Criar CRUD de tarefas

## Instruções
- Fazer um fork do repositório para sua conta pessoal do git
- Trabalhar utilizando commits
- Documentar como executar sua aplicação
- Descrever as funcionalidades do software

 ## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/Desafio-todolist-0.0.1-SNAPSHOT.jar
```
A API, apos a confirmação de ok no console poderá ser acessada em [localhost:8080/todos](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

### Imagens APIs Rest no [Postman](https://en.wikipedia.org/wiki/Postman_(software))
#### Post
![api_post](https://github.com/jonathnawill/desafio-junior-backend-simplify/assets/104990020/1c88af75-16a0-42aa-a10e-9ee9a5a977c6)




#### Get
![api_get](https://github.com/jonathnawill/desafio-junior-backend-simplify/assets/104990020/a614c373-18ec-4a86-83fc-3c704d992bc6)





#### Put
![api_put](https://github.com/jonathnawill/desafio-junior-backend-simplify/assets/104990020/593d1a40-8fc4-4c5a-adb4-e588b484b595)




#### Delete
![api_delete](https://github.com/jonathnawill/desafio-junior-backend-simplify/assets/104990020/ca2e1e2f-c475-408b-8570-95ac54bc1187)

### Tecnologias empregadas
- [x] Spring boot
- [x] Spring web
- [x] Spring DevTools
- [x] Speing Reactive Web
- [x] Spring JPA/Hibernate
- [x] MySQL
- [x] Postman 
- [x] API Rest
- [x] Banco de dados H2

