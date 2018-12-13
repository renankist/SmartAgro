-- -----------------------------------------------------
-- Saida de produtos
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION function_atualiza_saida_produto()
RETURNS trigger AS $BODY$

BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque - NEW.quantidade
WHERE id = NEW.produto;

-- Aqui temos um bloco IF que confirmará o tipo de operação UPDATE.
ELSIF (TG_OP = 'UPDATE') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque + OLD.quantidade
WHERE id = OLD.produto;
UPDATE produto SET quantidadeestoque = quantidadeestoque - NEW.quantidade
WHERE id = NEW.produto;

-- Aqui temos um bloco IF que confirmará o tipo de operação DELETE
ELSIF (TG_OP = 'DELETE') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque + OLD.quantidade
WHERE id = OLD.produto;

END IF;
RETURN NULL;
END;
$BODY$ LANGUAGE plpgsql;

-- -----------------------------------------------------
-- Entrada de produtos
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION function_atualiza_entrada_produto()
RETURNS trigger AS $BODY$

BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque + NEW.quantidade
WHERE id = NEW.produto;

-- Aqui temos um bloco IF que confirmará o tipo de operação UPDATE.
ELSIF (TG_OP = 'UPDATE') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque - OLD.quantidade
WHERE id = OLD.produto;
UPDATE produto SET quantidadeestoque = quantidadeestoque + NEW.quantidade
WHERE id = NEW.produto;

-- Aqui temos um bloco IF que confirmará o tipo de operação DELETE
ELSIF (TG_OP = 'DELETE') THEN
UPDATE produto SET quantidadeestoque = quantidadeestoque - OLD.quantidade
WHERE id = OLD.produto;

END IF;
RETURN NULL;
END;
$BODY$ LANGUAGE plpgsql;

-- -----------------------------------------------------
-- Trigger venda
-- -----------------------------------------------------
DROP TRIGGER if exists  trigger_ajusta_estoque_produto_venda ON itemvenda;

CREATE TRIGGER trigger_ajusta_estoque_produto_venda
-- Saida
AFTER INSERT OR UPDATE OR DELETE ON itemvenda
FOR EACH ROW
EXECUTE PROCEDURE function_atualiza_saida_produto();


-- -----------------------------------------------------
-- Trigger compra
-- -----------------------------------------------------
DROP TRIGGER if exists trigger_ajusta_estoque_produto_compra ON itemcompra;

CREATE TRIGGER trigger_ajusta_estoque_produto_compra
-- Entrada
AFTER INSERT OR UPDATE OR DELETE ON itemcompra
FOR EACH ROW
EXECUTE PROCEDURE function_atualiza_entrada_produto();

-- -----------------------------------------------------
-- Atualiza as permissoes de todos usuarios
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION function_atualiza_permissoes_usuarios()
RETURNS trigger AS $BODY$

DECLARE
	id_usuario int;

BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
	FOR id_usuario IN 
		SELECT DISTINCT permissaoacesso.usuario FROM permissaoacesso 
	LOOP
		INSERT INTO permissaoacesso VALUES (id_usuario, NEW.id, false);
	END LOOP;
END IF;

RETURN NULL;

END;
$BODY$ LANGUAGE plpgsql;

-- -----------------------------------------------------
-- Trigger permissoes
-- -----------------------------------------------------
DROP TRIGGER if exists  trigger_ajusta_permissoes_usuarios ON operacoesmodulo;

CREATE TRIGGER trigger_ajusta_permissoes_usuarios

AFTER INSERT ON operacoesmodulo
FOR EACH ROW
EXECUTE PROCEDURE function_atualiza_permissoes_usuarios();

-- -----------------------------------------------------
-- Insere todas as permissoes para o usuario
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION function_insere_permissoes_usuario()
RETURNS trigger AS $BODY$

DECLARE
	id_op int;

BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
	FOR id_op IN 
		SELECT DISTINCT operacoesmodulo.id FROM operacoesmodulo 
	LOOP
		INSERT INTO permissaoacesso VALUES (NEW.id, id_op, false);
	END LOOP;
END IF;

RETURN NULL;

END;
$BODY$ LANGUAGE plpgsql;

-- -----------------------------------------------------
-- Insere todas as releases para o usuario
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION function_insere_release_usuario()
RETURNS trigger AS $BODY$

DECLARE
	id_op int;

BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
	    insert into visualizacaorelease
		select release.id, colaborador.id, false
	      from release, colaborador
		 where release.id = NEW.id;
END IF;

RETURN NULL;

END;
$BODY$ LANGUAGE plpgsql;

-- -----------------------------------------------------
-- Trigger permissoes
-- -----------------------------------------------------
DROP TRIGGER if exists trigger_insere_permissoes_usuario ON colaborador;

CREATE TRIGGER trigger_insere_permissoes_usuario

AFTER INSERT ON colaborador
FOR EACH ROW
EXECUTE PROCEDURE function_insere_permissoes_usuario();

-- -----------------------------------------------------
-- Trigger releases
-- -----------------------------------------------------
DROP TRIGGER if exists trigger_insere_release_usuario ON release;

CREATE TRIGGER trigger_insere_release_usuario

AFTER INSERT ON release
FOR EACH ROW
EXECUTE PROCEDURE function_insere_release_usuario();

-- -----------------------------------------------------
-- View venda mensal por produto
-- -----------------------------------------------------
CREATE VIEW view_venda_mensal_produto AS 
 SELECT date_part('year', venda.dia)||'-'||date_part('month', venda.dia) as mes,
	    produto.id as produto,
	    produto.descricao,
	    sum(itemvenda.valorTotal) as valor,
	    sum(quantidade)  as quantidade
   FROM venda 
        INNER JOIN itemvenda ON itemvenda.venda = venda.id
  	    INNER JOIN produto ON itemvenda.produto = produto.id
  GROUP BY mes, produto.id, descricao
  ORDER BY mes, valor desc;
 
 -- -----------------------------------------------------
-- View valor a pagar por fornecedor por mes
-- -----------------------------------------------------
CREATE VIEW view_valor_pendente_fornecedor AS 
 SELECT date_part('year', contapagar.vencimento)||'-'||date_part('month', contapagar.vencimento) as mes,
	   compra.fornecedor,
	   fornecedor.nome,
	   sum(contapagar.valorDevido - contapagar.valorPago) as valor
  FROM contapagar 
       INNER JOIN compra ON contapagar.compra = compra.id
  	   INNER JOIN fornecedor ON fornecedor.id = compra.fornecedor
 WHERE contapagar.status <> 'P'
 GROUP BY mes, fornecedor, nome
 ORDER BY mes, valor desc;

 -- Funções e tabelas realacionadas a auditoria: 
-- Tabela de Auditoria
CREATE TABLE auditoria(
  id SERIAL NOT NULL, 
  tabela VARCHAR(40),
  acao VARCHAR(10),
  usuario VARCHAR(15),
  data_hora TIMESTAMP,
  chave VARCHAR(255),
  conteudo_anterior VARCHAR(4000),
  conteudo_depois VARCHAR(40000),

  primary key(id)
);

--Tabela para arquivar auditoria
CREATE TABLE  auditoria_arq(
  id INTEGER NOT NULL, 
  tabela VARCHAR(40),
  acao VARCHAR(10),
  usuario VARCHAR(15),
  data_hora timestamp,
  chave VARCHAR(255),
  conteudo_anterior VARCHAR(4000),
  conteudo_depois VARCHAR(4000),

  primary key(id)
);

--Function para inserir dados
--Function
CREATE OR REPLACE FUNCTION fn_log_audit() RETURNS trigger AS
$$
DECLARE
ativo boolean; 
c_user text; 
BEGIN

  c_user = (SELECT current_setting('smartagro.user')); 	
	 
  IF(c_user = '') THEN 	
   c_user = 'postgres'; 
  ENd IF;	

  ativo = (SELECT auditorialigada from config where config.id = 1);	

  IF (TG_OP = 'DELETE' AND ativo) THEN
    INSERT INTO auditoria ("tabela", "acao", "usuario", "data_hora", "chave", "conteudo_anterior", "conteudo_depois")
           VALUES (TG_TABLE_NAME, 'Exclusão', c_user, now(),'', old, null);
    RETURN OLD;
  ELSIF (TG_OP = 'UPDATE' AND ativo) THEN
     INSERT INTO auditoria ("tabela", "acao", "usuario", "data_hora", "chave", "conteudo_anterior", "conteudo_depois")
           VALUES (TG_TABLE_NAME, 'Edição', c_user, now(), '', old, new);
    RETURN NEW;
  ELSIF (TG_OP = 'INSERT' AND ativo) THEN
    INSERT INTO auditoria ("tabela", "acao", "usuario", "data_hora", "chave", "conteudo_anterior", "conteudo_depois")
           VALUES (TG_TABLE_NAME, 'Inserção', c_user, now(), '', null, new);
    RETURN NEW;
  END IF;
  RETURN NULL;
END;
$$
LANGUAGE 'plpgsql';


--triggers: 
DROP TRIGGER if exists cliente_audit ON cliente;
CREATE TRIGGER cliente_audit AFTER INSERT OR UPDATE OR DELETE
ON cliente FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists produto_audit ON produto;
CREATE TRIGGER produto_audit AFTER INSERT OR UPDATE OR DELETE
ON produto FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists unidademedida_audit ON unidademedida;
CREATE TRIGGER unidademedida_audit AFTER INSERT OR UPDATE OR DELETE
ON unidademedida FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists colaborador_audit ON colaborador;
CREATE TRIGGER colaborador_audit AFTER INSERT OR UPDATE OR DELETE
ON colaborador FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists venda_audit ON venda;
CREATE TRIGGER venda_audit AFTER INSERT OR UPDATE OR DELETE
ON venda FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists compra_audit ON compra;
CREATE TRIGGER compra_audit AFTER INSERT OR UPDATE OR DELETE
ON compra FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists permissaoacesso_audit ON permissaoacesso;
CREATE TRIGGER permissaoacesso_audit AFTER INSERT OR UPDATE OR DELETE
ON permissaoacesso FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists fornecedor_audit ON fornecedor;
CREATE TRIGGER fornecedor_audit AFTER INSERT OR UPDATE OR DELETE
ON fornecedor FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists config_audit ON config;
CREATE TRIGGER config_audit AFTER INSERT OR UPDATE OR DELETE
ON config FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists endereco_audit ON endereco;
CREATE TRIGGER endereco_audit AFTER INSERT OR UPDATE OR DELETE
ON endereco FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

DROP TRIGGER if exists formapagamento_audit ON formapagamento;
CREATE TRIGGER formapagamento_audit AFTER INSERT OR UPDATE OR DELETE
ON formapagamento FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();



--Funcao para arquivar
CREATE OR REPLACE FUNCTION fn_arq_audit(ini timestamp, fim timestamp) RETURNS 
INTEGER AS
$$

BEGIN
	insert into auditoria_arq(select * from auditoria where auditoria.data_hora between ini AND fim);
	delete from auditoria where auditoria.data_hora between ini AND fim;
		
	RETURN null;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION set_session_user(text) RETURNS 
INTEGER AS
$$
BEGIN

    PERFORM set_config('smartagro.user', $1, true);
    return null;
END;
$$
LANGUAGE 'plpgsql';