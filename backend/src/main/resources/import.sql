CREATE TABLE TB_USUARIO (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            NOME VARCHAR(100) NOT NULL,
                            SENHA VARCHAR(100) NOT NULL,
                            EMAIL VARCHAR(100) UNIQUE NOT NULL,
                            TELEFONE INT(12),
                            PACOTE_MENSAL BOOLEAN,
                            NASCIMENTO DATE NOT NULL
);


INSERT INTO TB_USUARIO (NOME, SENHA, EMAIL, TELEFONE, PACOTE_MENSAL, NASCIMENTO)
VALUES ('Nome do usuário', 'senha123', 'usuario@exemplo.com', 1234567890, 'true', '2000-01-01');