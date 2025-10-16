create database compras;
use compras;

CREATE TABLE produto (
  pro_id INT AUTO_INCREMENT PRIMARY KEY,
  pro_nome VARCHAR(150) NOT NULL,
  pro_descricao TEXT,
  pro_preco DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  pro_estoque INT NOT NULL,
  for_id int,
  foreign key (for_id) references fornecedor(for_id)
);

select * from produto;
SELECT * FROM cliente WHERE cli_id = 2;

CREATE TABLE cliente (
  cli_id INT AUTO_INCREMENT PRIMARY KEY,
  cli_nome VARCHAR(150) NOT NULL,
  cli_cpf varchar(20),
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



CREATE TABLE itensvendas (
  itm_id int(11) primary key auto_increment,
  itm_venda_id int(11),
  itm_produto_id int(11),
  itm_qtd int(11),
  itm_subtotal decimal(10,2) 
) ;
drop table itensvendas;
select * from itensvendas;

CREATE TABLE vendas (
  ven_id int primary key auto_increment,
  ven_cliente_id int,
  ven_data_venda datetime,
  ven_total_venda decimal(10,2),
  foreign key (ven_cliente_id) references cliente(cli_id)
);

select * from vendas;
INSERT INTO vendas (ven_cliente_id, ven_data_venda, ven_total_venda)
VALUES (1, '2025-10-13 10:00:00', 250.00);
select max(ven_id) from vendas;

CREATE TABLE ia_respostas (
  id INT PRIMARY KEY AUTO_INCREMENT,
  pergunta VARCHAR(255) NOT NULL,
  resposta TEXT NOT NULL
);

INSERT INTO ia_respostas (pergunta, resposta) VALUES
('como acessar a tabela de clientes', 'Vá até o menu principal e clique em "Cadastro de Clientes".'),
('como finalizar uma venda', 'Depois de adicionar os produtos, clique no botão "Finalizar Venda" na tela de pagamento.'),
('como cadastrar um produto', 'No menu principal, acesse "Cadastro de Produtos" e preencha os campos necessários.');

