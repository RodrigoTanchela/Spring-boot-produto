CREATE TABLE tbproduto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    value NUMERIC,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES tbcategoria(id)
);
