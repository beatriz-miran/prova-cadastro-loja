create database compras;
use compras;

CREATE TABLE produto (
  pro_id INT AUTO_INCREMENT PRIMARY KEY,
  pro_nome VARCHAR(150) NOT NULL,
  pro_descricao TEXT,
  pro_preco DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  pro_estoque INT NOT NULL
);

CREATE TABLE cliente (
  cli_id INT AUTO_INCREMENT PRIMARY KEY,
  cli_nome VARCHAR(150) NOT NULL,
  cli_email VARCHAR(120),
  cli_tel VARCHAR(50),
  cli_rua varchar(50),
  cli_cep varchar(15),
  cli_bairro varchar(50),
  cli_uf varchar(50),
  cli_numero int,
  cli_cidade varchar(50)
);

drop table cliente;
select * from cliente;

CREATE TABLE fornecedor (
  for_id INT AUTO_INCREMENT PRIMARY KEY,
  for_nome VARCHAR(150) NOT NULL,
  for_nome_fantasia VARCHAR(150),
  for_cnpj VARCHAR(20),
  for_email VARCHAR(120),
  for_tel VARCHAR(50),
  for_rua varchar(50),
  for_cep varchar(15),
  for_bairro varchar(50),
  for_uf varchar(50),
  for_numero int,
  for_cidade varchar(50)
);

CREATE TABLE nota (
  not_id INT AUTO_INCREMENT PRIMARY KEY,
  not_tipo ENUM('E','S') NOT NULL,
  not_cli_id INT NULL,      -- usado em vendas
  not_for_id INT NULL,      -- usado em entradas
  not_data DATE NOT NULL,
  not_valor_total DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  not_observacao TEXT,
  FOREIGN KEY (not_cli_id) REFERENCES cli_cliente(cli_id) ON DELETE SET NULL,
  FOREIGN KEY (not_for_id) REFERENCES for_fornecedor(for_id) ON DELETE SET NULL
);

CREATE TABLE nit_item_nota (
  nit_id INT AUTO_INCREMENT PRIMARY KEY,
  nit_not_id INT NOT NULL,
  nit_pro_id INT NOT NULL,
  nit_qtde INT NOT NULL,
  nit_preco_unit DECIMAL(10,2) NOT NULL,
  nit_subtotal DECIMAL(12,2) NOT NULL,
  FOREIGN KEY (nit_not_id) REFERENCES not_nota(not_id) ON DELETE CASCADE,
  FOREIGN KEY (nit_pro_id) REFERENCES pro_produto(pro_id) ON DELETE RESTRICT
);


