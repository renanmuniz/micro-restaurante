use restaurantedb;
INSERT INTO usuario(nome,senha,dthrcriacao,cpf) VALUES ('admin','$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu',NOW(),'00000000000'); /*admin*/

insert into produto(nome, descricao, tamanho, preco, dthrcriacao)
values('calabresa', 'calabresa, mussarela, cebola, azeitona', 'Grande (8 pedaços)', 45.50 ,now());

insert into produto(nome, descricao, tamanho, preco, dthrcriacao)
values('bacon', 'bacon, mussarela, cebola, azeitona', 'Grande (8 pedaços)', 55.50, now());

insert into produto(nome, descricao, tamanho, preco, dthrcriacao)
values('portuguesa', 'ovo, ervilha, presunto, mussarela, cebola, azeitona', 'Grande (8 pedaços)', 58.00, now());

insert into produto(nome, descricao, tamanho, preco, dthrcriacao)
values('frango com catupiry', 'frango, catupiry, mussarela, azeitona', 'Grande (8 pedaços)', 59.50, now());

-- ---------------------------------------------------------

use creditcarddb;
INSERT INTO usuario(nome,senha,dthrcriacao) VALUES ('admin','$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu',NOW()); /*admin*/
INSERT INTO cartao(numero_cartao,nome,valor_credito_disp,cartao_bloqueado) values ('1234-5678-9012-3456', 'RENAN MUNIZ MERLO', 2000.0, false);