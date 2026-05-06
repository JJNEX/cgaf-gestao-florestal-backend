# API CGAF - Sistema de Controle e Gestão de Áreas Florestais

[](https://openjdk.org/)
[](https://spring.io/projects/spring-boot)
[](https://www.postgresql.org/)

A **API CGAF** é o coração do sistema de Controle e Gestão de Áreas Florestais. Ela centraliza a lógica de negócios para o gerenciamento de áreas, espécies, colaboradores e operações de campo, garantindo segurança e integridade no monitoramento ambiental.

-----

## 🛠 Tecnologias Utilizadas

* **Linguagem:** Java 25
* **Framework:** Spring Boot 3+ (Data JPA, Security)
* **Segurança:** JWT (JSON Web Token)
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Dependências:** Maven

-----

## 🚀 Como Começar

### Pré-Requisitos

Antes de iniciar, você precisará ter instalado:

* [JDK 25](https://jdk.java.net/25/)
* [Maven 3.8+](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/download/)

### 1\. Clonar e Instalar

```bash
# Clone o repositório
git clone <URL_DO_REPOSITORIO>

# Entre na pasta
cd <NOME_DO_PROJETO>

# Instale as dependências
mvn clean install
```

### 2\. Banco de Dados

Crie o banco de dados localmente:

```sql
CREATE DATABASE cgaf_db;
```

> [\!IMPORTANT]
> Certifique-se de ajustar as credenciais no arquivo `src/main/resources/application.properties` se o seu usuário/senha do Postgres for diferente de `postgres`.

### 3\. Execução

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080/api/v1`

-----

## 📂 Estrutura de Pacotes

A arquitetura segue o padrão de camadas do Spring, garantindo separação de responsabilidades:

| Pacote | Responsabilidade |
| :--- | :--- |
| **Controller** | Endpoints da API e tratamento de requisições. |
| **Service** | Regras de negócio e validações. |
| **Repository** | Interface de comunicação com o banco de dados. |
| **Entity** | Modelagem das tabelas do banco de dados. |
| **DTO** | Objetos de transferência de dados (Input/Output). |
| **Mapper** | Conversão entre Entidades e DTOs. |

## 🐳 Execução com Docker

Esta aplicação pode ser executada utilizando containers com Docker, facilitando a configuração do ambiente e evitando a necessidade de instalar dependências manualmente.

### Pré-requisitos

- Docker instalado (recomendado: Docker Desktop)

### 🚀 Como executar

Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute:

```bash
docker compose up --build

