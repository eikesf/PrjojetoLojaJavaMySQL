use projeto_loja;

insert into pessoa (CPF,senha,email,nome,sobrenome) 
values ("11155599987","eike1211","efranco@gmail.com","eike","scudellari franco");
select * from pessoa;

insert into endereco (CEP,Rua,Bairro,numero,complemento,cidade,estado,Cliente_Pessoa_CPF)
values("19470428","Av. dos ipês","Jardim Primavera","537","","são paulo","são paulo","46509233878");
select * from endereco;