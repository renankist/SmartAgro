CREATE DATABASE smartagro;

-- -----------------------------------------------------
--                 Tabela config                    --
-- -----------------------------------------------------
CREATE TABLE config
(
  id serial NOT NULL , 
  auditoriaLigada boolean NOT NULL,
PRIMARY KEY (id));



-- -----------------------------------------------------
--                 Tabela logs                    --
-- -----------------------------------------------------
CREATE TABLE logs
(
  message character varying(2000),
  class character varying(255),
  priority character varying(64),
  line_number integer,
  log_date timestamp without time zone
);

-- -----------------------------------------------------
--                 Tabela estado                    --
-- -----------------------------------------------------
CREATE TABLE estado ( 
 id serial NOT NULL , 
 nome varchar(45) NOT NULL,
 sigla varchar(2) NOT NULL,
 unique(nome),
 unique(sigla), 
PRIMARY KEY (id));


-- -----------------------------------------------------
--                 Tabela cidade                    --
-- -----------------------------------------------------
CREATE TABLE cidade(
 id serial NOT NULL, 
 estado int NOT NUll, 
 nome varchar(45) NOT NUll, 


PRIMARY KEY(id),
FOREIGN KEY(estado) REFERENCES estado(id));


-- -----------------------------------------------------
--                 Tabela endereco                  --
-- -----------------------------------------------------
CREATE TABLE endereco(
 id serial NOT NULL, 
 cidade int NOT NULL, 	
 rua varchar(255) NOT NULL,
 numero varchar(15) NOT NULL,
 bairro varchar(45) NOT NULL,  
 cep varchar(9) NOT NULL, 
 complemento varchar(45),


 PRIMARY KEY(id),
 FOREIGN KEY (cidade) REFERENCES cidade(id));


-- -----------------------------------------------------
--                 Tabela fornecedor                --
-- -----------------------------------------------------
CREATE TABLE fornecedor(
 id serial NOT NULL, 
 endereco int NOT NULL, 
 nome varchar(45) NOT NULL,
 cnpj varchar(18),
 cpf varchar(14),
 razaoSocial varchar(80),


 PRIMARY KEY(id),
 FOREIGN KEY (endereco) REFERENCES endereco(id)); 


-- -----------------------------------------------------
--                 Tabela colaborador               --
-- -----------------------------------------------------
CREATE TABLE colaborador(
 id serial NOT NULL, 
 endereco int NOT NULL, 
 nomeCompleto varchar(80) NOT NULL,
 email varchar(80) NOT NULL, 
 celular varchar(45) NOT NULL, 
 funcao varchar(80) NOT NULL, 
 usuario varchar(15) NOT NULL,
 senhaUsuario varchar(245) NOT NULL,
 tipoUsuario CHAR NOT NULL, 


 unique(email),
 unique(usuario),
 unique(celular), 


 PRIMARY KEY(id),
 FOREIGN KEY(endereco) REFERENCES endereco(id));


-- -----------------------------------------------------
--                 Tabela cliente                   --
-- -----------------------------------------------------
CREATE TABLE cliente(
 id serial NOT NULL, 
 endereco int NOT NULL, 
 nome varchar(80) NOT NULL, 
 cnpj varchar(18), 
 cpf varchar(14), 
 sexo char, 
 celular varchar(45) NOT NULL, 
 email varchar(45),
 dataNascimento date,
 dataCadastro date, 


 PRIMARY KEY(id),
 FOREIGN KEY(endereco) REFERENCES endereco(id)); 


-- -----------------------------------------------------
--                 Tabela unidademedida             --
-- -----------------------------------------------------
CREATE TABLE unidademedida(
	id serial NOT NULL, 
	unidade varchar(45) NOT NULL,
	descricao varchar(45) NOT NULL, 


    unique(unidade), 


PRIMARY KEY (id)); 


-- -----------------------------------------------------
--                 Tabela produto                   --
-- -----------------------------------------------------
CREATE TABLE produto(
 	id serial NOT NULL, 
 	unidademedida int NOT NULL,
 	codigo varchar(45) NOT NULL, 
 	descricao varchar(45) NOT NULL, 
    codigoBarras varchar(14),
    valorCompra decimal(11,2) NOT NULL, 
    valorVenda decimal(11,2) NOT NULL, 
    quantidadeEstoque decimal(11,2) NOT NULL,
	imagem bytea,


 PRIMARY KEY(id),
 FOREIGN KEY(unidademedida) REFERENCES unidademedida(id)); 

-- -----------------------------------------------------
--                 Tabela formapagamento            --
-- -----------------------------------------------------
CREATE TABLE formapagamento(
	id serial NOT NULL, 
	descricao varchar(45) NOT NULL, 


PRIMARY KEY(id));


-- -----------------------------------------------------
--                 Tabela venda                     --
-- -----------------------------------------------------
CREATE TABLE venda(
	id serial NOT NULL,
	cliente int NOT NULL,
	vendedor int NOT NULL, 
	formaPagamento int NOT NULL, 
	dia date NOT NULL, 
	hora time NOT NULL, 
	valor decimal(11,2) NOT NULL, 
	desconto decimal(11,2),
	descricaoDesconto varchar(45),
	valorTotal decimal(11,2) NOT NULL,
	observacao varchar(45),
	status char(1) NOT NULL, 
	pago boolean NOT NULL, 


PRIMARY KEY(id),
FOREIGN KEY(cliente) REFERENCES cliente(id),
FOREIGN KEY(vendedor) REFERENCES colaborador(id),
FOREIGN KEY(formaPagamento) REFERENCES formapagamento(id));


-- -----------------------------------------------------
--                 Tabela contareceber              --
-- -----------------------------------------------------
CREATE TABLE contareceber(
	id serial NOT NULL, 
	venda int NOT NULL,
	vencimento date NOT NULL, 
	valorDevido decimal(11,2) NOT NULL, 
	valorPago decimal(11,2) NOT NULL, 
	dataPagamento timestamp,
	parcela int NOT NULL, 
	status char NOT NULL, 


PRIMARY KEY(id),
FOREIGN KEY(venda) REFERENCES venda(id)); 


-- -----------------------------------------------------
--                 Tabela itemvenda                 --
-- -----------------------------------------------------
CREATE TABLE itemvenda(
	produto int NOT NULL,
	venda int NOT NULL, 
	valor decimal(11,2) NOT NULL, 
	desconto decimal(11,2),
	quantidade decimal(11,2) NOT NULL, 
	valorTotal decimal(11,2) NOT NULL,
PRIMARY KEY(produto, venda),
FOREIGN KEY(produto) REFERENCES produto(id),
FOREIGN KEY(venda) REFERENCES venda(id));  


-- -----------------------------------------------------
--                 Tabela compra                     --
-- -----------------------------------------------------
CREATE TABLE compra(
	id serial NOT NULL,
	fornecedor int NOT NULL,
	colaborador int NOT NULL, 
	formaPagamento int NOT NULL, 
	data date NOT NULL, 
	hora time NOT NULL, 
	valor decimal(11,2) NOT NULL, 
	desconto decimal(11,2),
	descricaoDesconto varchar(45),
	valorTotal decimal(11,2) NOT NULL,
	observacao varchar(45),
	status char(1) NOT NULL, 
	pago boolean NOT NULL, 


PRIMARY KEY(id),
FOREIGN KEY(fornecedor) REFERENCES fornecedor(id),
FOREIGN KEY(colaborador) REFERENCES colaborador(id),
FOREIGN KEY(formaPagamento) REFERENCES formapagamento(id));


-- -----------------------------------------------------
--                 Tabela contapagar                --
-- -----------------------------------------------------
CREATE TABLE contapagar(
	id serial NOT NULL, 
	compra int NOT NULL,
	vencimento date NOT NULL, 
	valorDevido decimal(11,2) NOT NULL, 
	valorPago decimal(11,2) NOT NULL, 
	dataPagamento timestamp,
	parcela int NOT NULL, 
	status char NOT NULL, 


PRIMARY KEY(id),
FOREIGN KEY(compra) REFERENCES compra(id)); 


-- -----------------------------------------------------
--                 Tabela itemcompra                --
-- -----------------------------------------------------
CREATE TABLE itemcompra(
	produto int NOT NULL,
	compra int NOT NULL, 
	valor decimal(11,2) NOT NULL, 
	desconto decimal(11,2),
	quantidade decimal(11,2) NOT NULL, 
	valorTotal decimal(11,2) NOT NULL,
PRIMARY KEY(produto, compra),
FOREIGN KEY(produto) REFERENCES produto(id),
FOREIGN KEY(compra) REFERENCES compra(id));  

-- -----------------------------------------------------
--                 Tabela auditoria                --
-- -----------------------------------------------------
CREATE TABLE revinfo(
rev SERIAL NOT NULL, 
revstmp INTEGER, 
PRIMARY KEY(rev));

-- -----------------------------------------------------
-- Tabela modulo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS modulo (
  id serial NOT NULL,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Tabela operacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS operacao (
  id serial NOT NULL,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Tabela operacoesmodulo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS operacoesmodulo (
  id serial NOT NULL,
  modulo INT NOT NULL,
  operacao INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (modulo) REFERENCES modulo (id),
  FOREIGN KEY (operacao) REFERENCES operacao (id) ON DELETE CASCADE);

-- -----------------------------------------------------
-- Tabela permissaoacesso
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissaoacesso (
  usuario INT NOT NULL,
  operacao INT NOT NULL,
  acesso BOOLEAN NOT NULL,
  PRIMARY KEY (usuario, operacao),
  FOREIGN KEY (usuario) REFERENCES colaborador (id) ON DELETE CASCADE,
  FOREIGN KEY (operacao) REFERENCES operacoesmodulo (id) ON DELETE CASCADE);
  
-- -----------------------------------------------------
-- Table release
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS release (
  id serial NOT NULL,
  versao VARCHAR(5),
  data DATE,
  descricao text,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table vizualizacaorelease
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS visualizacaorelease (
  release INT NOT NULL,
  usuario INT NOT NULL,
  visto boolean,
  PRIMARY KEY (release, usuario),
  FOREIGN KEY (usuario) REFERENCES colaborador (id),
  FOREIGN KEY (release) REFERENCES release (id));
