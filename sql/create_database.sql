-- Script para criação do banco de dados PostgreSQL
-- Teste Técnico - Sistema de Controle de Estoque

-- Criação do banco de dados
CREATE DATABASE testetecnico;

-- Conectar ao banco de dados criado
\c testetecnico;

-- Tabela de usuários
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

-- Tabela de produtos
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL,
    data_entrada DATE NOT NULL,
    data_validade DATE NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT chk_quantidade_positiva CHECK (quantidade >= 0),
    CONSTRAINT chk_data_validade CHECK (data_validade >= data_entrada)
);

-- Tabela de pagamentos
CREATE TABLE pagamentos (
    id SERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    data_pagamento DATE NOT NULL DEFAULT CURRENT_DATE,
    data_entrega DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    CONSTRAINT chk_quantidade_pagamento_positiva CHECK (quantidade > 0)
);

-- Índices para melhor performance
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_produto_codigo ON produto(codigo);
CREATE INDEX idx_produto_validade ON produto(data_validade);
CREATE INDEX idx_pagamentos_usuario ON pagamentos(usuario_id);
CREATE INDEX idx_pagamentos_produto ON pagamentos(produto_id);
CREATE INDEX idx_pagamentos_data ON pagamentos(data_pagamento);
