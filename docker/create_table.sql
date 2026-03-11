CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(100),
    preco DECIMAL(10,2),
    quantidade INT,
    categoria VARCHAR(100)
);
