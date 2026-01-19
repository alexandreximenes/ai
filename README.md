# 🚀 POC – Spring AI com RAG (Retrieval-Augmented Generation)

Este repositório apresenta uma **Prova de Conceito (POC)** para exploração de **IA Generativa com Spring AI**, evoluindo de uma interação simples com LLM até uma arquitetura completa com **RAG** e **Vector Store (Qdrant)**.

O objetivo é demonstrar, de forma incremental, como construir aplicações corporativas modernas utilizando **LLMs**, **memória conversacional**, **persistência relacional** e **busca vetorial**.

---

## 🧪 Evolução das Provas de Conceito

### 🔹 POC 1 – Interação Básica com LLM
- Primeira iteração com um **Large Language Model (LLM)**
- Envio de prompts simples
- Respostas diretas sem memória ou contexto persistente

---

### 🔹 POC 2 – Persistência de Histórico de Conversas
- Evolução para uma aplicação com **banco de dados relacional**
- Armazenamento do histórico de conversas
- Introdução de **memória conversacional**
- Base para auditoria e continuidade de diálogos

---

### 🔹 POC 3 – RAG com Vector Store (Qdrant)
- Implementação de **Retrieval-Augmented Generation (RAG)**
- Integração com **Qdrant** como banco vetorial
- Busca semântica baseada em embeddings
- Respostas enriquecidas com **contexto externo** relevante

---

## 🧠 Contexto – Spring AI

As POCs foram desenvolvidas e validadas utilizando os principais componentes do **Spring AI**, explorando:

- Abstração de provedores de IA (LLMs)
- Gerenciamento de prompts
- Parametrização de tokens e inferência
- Integração com bancos vetoriais
- Arquitetura desacoplada e extensível

![Spring AI Overview](https://github.com/alexandreximenes/ai/blob/main/img/spring-ai.png)

---

## 🔄 Fluxo de Interação com o AI Provider

A imagem abaixo demonstra o fluxo completo desde a **entrada do usuário**, passando pelo **processamento da aplicação**, até a **resposta do AI Provider**.

![Fluxo de Interação](https://github.com/alexandreximenes/ai/blob/main/img/use-case-ia-1.png)

---

## 🧩 Casos de Uso

### Visão Geral
#### Diagrama de Contexto do C4 (Nivel 1)

![Casos de Uso](https://github.com/alexandreximenes/ai/blob/main/img/use-case-ia-0.png)

---

### 🛠️ Aplicação Web Admin
Responsável pela **administração e governança da IA**, incluindo:
- Configuração de parâmetros de inferência (tokens, temperatura, etc.)
- Envio e ingestão de documentos
- Gerenciamento do banco vetorial
- Preparação de dados para RAG

---

### 💬 Aplicação Chat (Prompt)
Interface voltada ao **usuário final**, permitindo:
- Envio de prompts
- Interação direta com o LLM
- Respostas contextualizadas (RAG)
- Continuidade conversacional

---

### Visão Detalhe
#### Diagrama de Container do C4 (Nivel 2) do backend

![Casos de Uso](https://github.com/alexandreximenes/ai/blob/main/img/use-case-ia-2.png)

---

## 🔍 Visões Detalhadas

### 📌 Aplicação Web Admin – Visão Ampliada
![Admin App](https://github.com/alexandreximenes/ai/blob/main/img/use-case-ia-3.png)

### 📌 Aplicação Chat – Visão Ampliada
![Chat App](https://github.com/alexandreximenes/ai/blob/main/img/use-case-ia-4.png)

---

## 🧠 POC 3 – Implementação de RAG

### 📖 Visão Geral da Arquitetura RAG
Demonstra o fluxo completo de:
- Ingestão de documentos
- Geração de embeddings
- Armazenamento no Vector Store
- Recuperação de contexto relevante
- Geração de respostas enriquecidas

![RAG Overview](https://github.com/alexandreximenes/ai/blob/main/img/rag-1-a.png)

---

### 🔎 Visão Detalhada do Processo RAG
Fluxo detalhado da interação entre:
- Prompt do usuário
- Busca vetorial
- Contextualização
- Resposta final do LLM

![RAG Detailed](https://github.com/alexandreximenes/ai/blob/main/img/rag-2-b.png)

---

## 📌 Tecnologias Envolvidas
- **Java / Spring Boot**
- **Spring AI**
- **LLMs (via AI Provider)**
- **Qdrant (Vector Store)**
- **Banco de Dados Relacional**
- **Docker / Docker Compose**

---

## 🎯 Objetivo Final
Demonstrar como arquitetar soluções de **IA Generativa corporativas**, escaláveis e observáveis, utilizando **Spring AI**, com foco em:
- Qualidade de respostas
- Redução de alucinações
- Uso eficiente de contexto
- Evolução incremental da arquitetura

