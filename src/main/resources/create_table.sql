CREATE DATABASE IF NOT EXISTS DBprodutos;

USE DBprodutos;

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco DECIMAL(10,2),
    quantidade INT
);