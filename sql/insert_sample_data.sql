-- Script de inserção de dados de exemplo para teste

-- Inserir usuários de exemplo
INSERT INTO usuarios (nome, email) VALUES
('João Silva', 'joao.silva@email.com'),
('Maria Santos', 'maria.santos@email.com'),
('Pedro Oliveira', 'pedro.oliveira@email.com'),
('Ana Costa', 'ana.costa@email.com');

-- Inserir produtos de exemplo
INSERT INTO produto (codigo, descricao, data_entrada, data_validade, quantidade) VALUES
('PROD001', 'Notebook Dell Inspiron 15', '2024-01-15', '2025-12-31', 50),
('PROD002', 'Mouse Logitech MX Master', '2024-02-10', '2025-06-30', 100),
('PROD003', 'Teclado Mecânico Corsair', '2024-01-20', '2024-12-31', 30),
('PROD004', 'Monitor LG 24 polegadas', '2024-03-05', '2026-03-05', 25),
('PROD005', 'Headset Gamer HyperX', '2024-02-28', '2025-08-15', 75),
('PROD006', 'Webcam Logitech C920', '2024-01-10', '2024-09-30', 40),
('PROD007', 'SSD Samsung 1TB', '2024-03-15', '2027-03-15', 60),
('PROD008', 'Memória RAM DDR4 16GB', '2024-02-20', '2026-02-20', 80);

-- Inserir alguns pagamentos de exemplo
INSERT INTO pagamentos (usuario_id, produto_id, quantidade, data_pagamento, data_entrega) VALUES
(1, 1, 2, '2024-07-15', '2024-07-17'),
(2, 2, 5, '2024-07-20', '2024-07-22'),
(3, 4, 1, '2024-07-25', '2024-07-27'),
(1, 5, 3, '2024-08-01', '2024-08-03'),
(4, 7, 2, '2024-08-05', '2024-08-07');
