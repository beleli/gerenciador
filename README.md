# 🛍️ Gerenciador de Produtos

> Aplicação Java para gerenciamento de produtos com validação.

## ✨ Destaques

- ✅ **Clean Code** - Código limpo e bem documentado
- ✅ **SOLID Principles** - Arquitetura profissional

---

## 🚀 Início Rápido

### Pré-requisitos
- Java 8+
- Maven 3.6+
- MySQL 8.0+

### Compilar
```bash
mvn clean compile
```

### Executar
```bash
mvn exec:java -Dexec.mainClass="com.gerenciador.Main"
```

---

## 📋 Funcionalidades

| # | Funcionalidade |
|---|---|
| 1 | Adicionar Produto |
| 2 | Listar Produtos |
| 3 | Atualizar Produto |
| 4 | Remover Produto |
| 5 | Sair |

---

## 🔍 Validações Implementadas

### Strings (Nome, Descrição)
```
✓ Trim automático de espaços
✓ 3 tentativas antes de cancelar
```

### Numbers (Preço, Quantidade, ID)
```
✓ Formato válido (Double/Integer)
✓ Valores positivos (validação ≥ 0)
✓ 3 tentativas antes de cancelar
```

---

## 📁 Estrutura do Projeto

```
gerenciador/
├── src/main/java/com/gerenciador/
│   ├── Main.java                      [Refatorado]
│   ├── dao/
│   │   └── ProdutoDAO.java            [Persistência]
│   ├── modelo/
│   │   └── Produto.java               [Entidade]
│   └── util/
│       ├── HibernateUtil.java         [Configuração DB]
├── src/main/resources/
│   └── hibernate.cfg.xml              [Config Hibernate]
├── pom.xml                            [Dependências Maven]
└── docker-compose.yml                 [Container MySQL]
```

---


## 🎯 Exemplo de Uso

### Adicionar Produto (Válido)
```
Opção: 1
Nome: Notebook Dell
Descrição: Laptop de alta performance
Preço: 3500.50
Quantidade: 5
Produto salvo com sucesso! ID: 1
```

### Adicionar Produto (Inválido - Tentativa 1/3)
```
Opção: 1
Nome: [ENTER - vazio]
Erro: O campo não pode estar vazio. Tentativa 1/3
Nome: Notebook
Continuando...
```

### Resultado após 3 Tentativas Inválidas
```
Nome: [ENTER - vazio]
Erro: O campo não pode estar vazio. Tentativa 1/3
Nome: [ENTER - vazio]
Erro: O campo não pode estar vazio. Tentativa 2/3
Nome: [ENTER - vazio]
Erro: O campo não pode estar vazio. Tentativa 3/3
Operação cancelada: Número de tentativas excedido.
```

## 🗂️ Banco de Dados

O projeto usa **MySQL** com **Hibernate** para persistência.

### Tabela de Produtos
```sql
CREATE DATABASE IF NOT EXISTS DBprodutos;

USE DBprodutos;

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10,2),
    quantidade INT
    );
```

---

## 🔗 Dependências

- **Hibernate 6.3.1** - ORM para persistência
- **MySQL Connector Java 8.0.33** - Driver MySQL
- **Jakarta Persistence 3.1.0** - Especificação JPA

---