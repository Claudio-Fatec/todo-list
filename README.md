# 📝 Todo List - API RESTful de Gestão de Tarefas

Aplicação Spring Boot para gestão de tarefas, desenvolvida como projeto académico.

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA** / Hibernate
* **PostgreSQL**
* **JUnit 5** e **Mockito** (Testes Unitários)
* **Maven**

## ⚙️ Funcionalidades e Endpoints (CRUD)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/tarefas` | Cria uma nova tarefa |
| `GET` | `/api/tarefas` | Lista todas as tarefas |
| `GET` | `/api/tarefas/{id}` | Busca uma tarefa específica por ID |
| `PUT` | `/api/tarefas/{id}` | Atualiza uma tarefa existente |
| `DELETE` | `/api/tarefas/{id}` | Elimina uma tarefa por ID |

## 🛠️ Como Executar o Projeto

1. Clona o repositório:
   ```bash
   git clone [https://github.com/Claudio-Fatec/todo-list.git](https://github.com/Claudio-Fatec/todo-list.git)

   ## 🗄️️ Banco de Dados

O projeto utiliza o **PostgreSQL** como banco de dados. O script de criação das tabelas está disponível no projeto em `src/main/resources/schema.sql`.

### Script SQL (`schema.sql`)

```sql
CREATE TABLE IF NOT EXISTS tb_tarefa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    status VARCHAR(20) NOT NULL,
    observacoes TEXT,
    data_criacao TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    data_atualizacao TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
