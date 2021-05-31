Select que traz o ultimo preco de um produto:

SELECT produto.id, produto.nome, produto.descricao, preco.id, preco.preco
FROM produto 
JOIN preco_produto on (produto.id=preco_produto.id_produto)
JOIN preco on (preco.id=preco_produto.id_preco)
where produto.id=2
order by preco.dthrcriacao DESC
limit 1;