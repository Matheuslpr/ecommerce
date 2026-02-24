
CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(80) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE tb_produtos (
    id SERIAL PRIMARY KEY,
    nome_produto VARCHAR(80) NOT NULL,
    descricao TEXT,
    preco NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL
);

CREATE TABLE tb_pedidos (
    id SERIAL PRIMARY KEY,
    preco_total NUMERIC(10, 2) NOT NULL
);

CREATE TABLE pedido_itens (
    id SERIAL PRIMARY KEY,
    produtos_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario NUMERIC(10, 2) NOT NULL,
    pedido_id INTEGER NOT NULL,
    FOREIGN KEY (produtos_id) REFERENCES tb_produtos(id),
    FOREIGN KEY (pedido_id) REFERENCES tb_pedidos(id)
);

CREATE TABLE tb_estoque (
    id SERIAL PRIMARY KEY,
    produtos_id INTEGER,
    movement_type VARCHAR(20) NOT NULL,
    quantidade INTEGER,
    criado_em TIMESTAMP,
    FOREIGN KEY (produtos_id) REFERENCES tb_produtos(id)
);


