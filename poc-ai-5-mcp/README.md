# MCP Client & MCP Server

![Architecture](https://img.shields.io/badge/Architecture-MCP%20Client%20%26%20MCP%20Server-blue)

Este repositório demonstra uma **arquitetura moderna baseada no Model Context Protocol (MCP)**, integrando um **MCP Client** com um **MCP Server** que expõe *tools*, persiste dados em **PostgreSQL** e utiliza **LLMs (Large Language Models)** para enriquecer respostas e fluxos inteligentes.

O objetivo do projeto é servir como **referência prática** para construção de aplicações orientadas a contexto, ferramentas e IA.

---

## 🧠 Visão Geral da Arquitetura

### Componentes

* **MCP Client**
  Cliente responsável por descobrir e invocar *tools* expostas pelo servidor MCP.

* **MCP Server**
  Servidor central que:

  * expõe tools via MCP
  * executa regras de negócio
  * interage com o banco de dados
  * integra LLMs para geração de respostas contextualizadas

* **Server Tools**
  Conjunto de ferramentas (ex: `createTicket`, `findTicketsByUsername`, `deleteTicket`) responsáveis por orquestrar dados e ações.

* **Banco de Dados (PostgreSQL)**
  Persistência estruturada dos dados de negócio (tickets, usuários, etc.).

* **LLM (Large Language Model)**
  Responsável por interpretação semântica, enriquecimento de contexto e geração de respostas inteligentes.

---

## ⚙️ Tecnologias Utilizadas

| Camada       | Tecnologia / Ferramenta |
| ------------ | ----------------------- |
| Backend      | Java • Spring Boot      |
| MCP Protocol | Model Context Protocol  |
| Client       | MCP Client (Inspector)  |
| Database     | PostgreSQL              |
| AI / LLM     | Modelos de Linguagem    |
| Build        | Maven                   |
| Runtime      | Java 17+                |

---

## 🚀 Como Executar o Projeto

### 1️⃣ Compilar o MCP Server

Certifique-se de ter **Java 17+** e **Maven** instalados.

```bash
mvn clean install
```

Ao final, o JAR será gerado em:

```text
target/mcp-server-0.0.1-SNAPSHOT.jar
```

---

### 2️⃣ Executar o MCP Inspector (Client)

#### Pré-requisitos

* Node.js instalado
* MCP Inspector

```bash
npx @modelcontextprotocol/inspector
```

---

### 3️⃣ Conectar o Inspector ao MCP Server

No Inspector:

* **Command**:

  ```text
  java
  ```

* **Arguments**:

  ```text
  -jar "CAMINHO_COMPLETO/mcp-server-0.0.1-SNAPSHOT.jar"
  ```

**Exemplo (Windows):**

```text
-jar "C:\Users\...\mcp-server\target\mcp-server-0.0.1-SNAPSHOT.jar"
```

Clique em **Connect**.

---

## 🧪 Interagindo com o MCP Server

### 🔧 Descobrindo Tools

1. Acesse a aba **Tools** no Inspector
2. Visualize a lista de tools expostas pelo MCP Server
3. Selecione a tool desejada (ex: `createTicket`)

![Inspector - Tools](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/1.png)

---

### ✍️ Criando um Ticket

1. Preencha os campos definidos pela tool
2. Clique em **Run Tool**

![Create Ticket](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/2.png)

✅ O ticket é persistido no PostgreSQL.

![Ticket no Banco](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/3.png)

---

### 🔍 Consultando Tickets por Username

#### Exemplo: `alexandre@alexandreximenes.ai`

![Busca por Username](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/4.png)

#### Exemplo: `amanda@alexandreximenes.ai`

![Busca por Username](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/5.png)

---

### 🗑️ Deletando Tickets

#### Deletar ticket por ID

![Delete por ID](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/6.png)

#### Estado do banco após exclusão

![Banco após delete](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/8.png)

---

### 🧹 Deletar todos os tickets de um usuário

#### Exemplo: delete por `username`

![Delete por Username](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/7.png)

#### Banco de dados após a operação

![Banco limpo](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/9.png)

#### Consulta confirmando exclusão

![Consulta vazia](https://github.com/alexandreximenes/ai/blob/main/poc-ai-5-mcp/img/10.png)

---

## 🎯 Objetivos do Projeto

* Demonstrar o uso prático do **Model Context Protocol (MCP)**
* Criar *tools* reutilizáveis e orientadas a contexto
* Integrar LLMs a fluxos de negócio reais
* Servir como base para **POCs**, **experimentos** e **produtos AI-first**

---

## 🚧 Próximos Passos

* [ ] Autenticação e autorização
* [ ] Observabilidade (logs, métricas, tracing)
* [ ] Integração com Vector Store (RAG)
* [ ] Cache distribuído
* [ ] Deploy com Docker / Kubernetes

---

## 📌 Conclusão

Este projeto mostra como combinar **MCP + Tools + Banco de Dados + LLM** em uma arquitetura coesa, extensível e pronta para aplicações inteligentes modernas.

Sinta-se à vontade para explorar, adaptar e evoluir 🚀
