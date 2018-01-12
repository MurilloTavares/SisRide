CREATE TABLE Usuario(
    email varchar(50) ,
    senha varchar(50) NOT NULL,
    nome varchar(50) NOT NULL,
    foto varchar(50) NOT NULL,
    
    cidade varchar(50),
    nascimento date,
    profissao varchar(50),
    sexo char(1),
    nota numeric(3,2),

    CONSTRAINT Usuario_PK PRIMARY KEY(email),
    
    CONSTRAINT Usuario_Foto_UNIQUE UNIQUE(foto)

);

CREATE TABLE Lugar(
    id serial,
    
    estado varchar(30),
    cidade varchar(50),
    rua varchar(50),
    numero varchar(8),    
    
    nome varchar(50),
    descricao varchar(250),
    
    CONSTRAINT Lugar_PK PRIMARY KEY(id),

    CONSTRAINT Lugar_Endereco_Distinto UNIQUE(estado, cidade, rua, numero)
);

CREATE TABLE Viagem(
    id serial,
    motorista varchar(50),
    
    saida integer,
    chegada integer,
    
    dataHorario timestamp,
    valor numeric(7,2),
    vagas smallint,
    
    descCarro varchar(250),
    descViagem varchar(250),

    CONSTRAINT Viagem_PK PRIMARY KEY(id),
    
    CONSTRAINT Viagem_Motorista_FK FOREIGN KEY(motorista) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Viagem_Saida_FK FOREIGN KEY(saida) REFERENCES Lugar(id)
        ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT Viagem_Chegada_FK FOREIGN KEY(chegada) REFERENCES Lugar(id)
        ON DELETE RESTRICT ON UPDATE RESTRICT,

    CONSTRAINT Viagem_SaidaChegada CHECK(saida!=chegada),
    CONSTRAINT Viagem_NumVagas CHECK(Vagas>0),
    CONSTRAINT Viagem_DataHorario CHECK(dataHorario>now())
);

CREATE TABLE Passageiros(
	passageiro varchar(50),
    viagem integer,
    
    CONSTRAINT Passageiros_PK PRIMARY KEY(passageiro,viagem),
    
    CONSTRAINT Passageiros_Passageiro_FK FOREIGN KEY(passageiro) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Passageiros_Viagem_FK FOREIGN KEY(viagem) REFERENCES Viagem(id)
    	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION NumPassageiros(i INTEGER)
RETURNS INTEGER AS '
	DECLARE
	    num INTEGER;
	BEGIN
	    SELECT INTO num COUNT(*)
	    FROM Viagem
            WHERE id = i;
	RETURN num;
	END
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION HaVagas(i INTEGER)
RETURNS BOOLEAN AS '
	DECLARE
    	numVagas INTEGER;
    BEGIN
        SELECT INTO numVagas vagas
        FROM Viagem
        WHERE id = i;
        
        RETURN numVagas<NumPassageiros(i);
    END
' LANGUAGE plpgsql;

CREATE TABLE Solicitacao_Viagem(
    passageiro varchar(50),
    viagem integer,
    
    CONSTRAINT Solicitacao_Viagem_PK PRIMARY KEY(passageiro,viagem),
    
    CONSTRAINT Solicitacao_Viagem_Passageiro_FK FOREIGN KEY(passageiro) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Solicitacao_Viagem_Viagem_FK FOREIGN KEY(viagem) REFERENCES Viagem(id)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Solicitacao_HaVagas CHECK(HaVagas(viagem))
);

CREATE TABLE Amigos(
    usuario varchar(50),
    amigo varchar(50),
    
    CONSTRAINT Amigos_PK PRIMARY KEY(usuario, amigo),
    
    CONSTRAINT Amigos_Usuario_FK FOREIGN KEY(usuario) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Amigos_Amigo_FK FOREIGN KEY(amigo) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Amigos_UsuarioAmigo CHECK(usuario!=amigo)
);

CREATE TABLE Solicitacao_Amigos(
    usuario varchar(50),
    amigo varchar(50),
    
    CONSTRAINT Solicitacao_Amigos_PK PRIMARY KEY(usuario, amigo),
    
    CONSTRAINT Solicitacao_Amigos_Usuario_FK FOREIGN KEY(usuario) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Solicitacao_Amigos_Amigo_FK FOREIGN KEY(amigo) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Solicitacao_Amigos_UsuarioAmigo CHECK(usuario!=amigo)
);

CREATE TABLE Seguir(
    usuario varchar(50),
    seguido varchar(50),
    
    CONSTRAINT Seguir_PK PRIMARY KEY(usuario, seguido),
    
    CONSTRAINT Seguir_Usuario_FK FOREIGN KEY(usuario) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Seguir_Amigo_FK FOREIGN KEY(seguido) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Seguir_UsuarioSeguido CHECK(usuario!=seguido)
);

CREATE TABLE Solicitacao_Seguir(
	usuario varchar(50),
    seguido varchar(50),
    
    CONSTRAINT Solicitacao_Seguir_PK PRIMARY KEY(usuario, seguido),
    
    CONSTRAINT Solicitacao_Seguir_Usuario_FK FOREIGN KEY(usuario) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Solicitacao_Seguir_Amigo_FK FOREIGN KEY(seguido) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Solicitacao_Seguir_UsuarioSeguido CHECK(usuario!=seguido)
);

CREATE TABLE Avaliacao(
    usuario varchar(50),
    
    avaliador varchar(50),    
    nota smallint,
    comentario varchar(250),
    
    CONSTRAINT Avaliacao_PK PRIMARY KEY(usuario, avaliador),
    
    CONSTRAINT Avaliacao_Usuario_FK FOREIGN KEY(usuario) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT Avaliacao_Avaliador_FK FOREIGN KEY(avaliador) REFERENCES Usuario(email)
    	ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT Avaliacao_UsuarioAvaliador CHECK(usuario!=avaliador),
    CONSTRAINT Avaliacao_NotaMaxima CHECK(nota>=0 and nota<=5)
);

CREATE OR REPLACE FUNCTION SegueAmigo()
RETURNS TRIGGER AS'
	BEGIN
	    INSERT INTO Seguir(usuario, seguido)
            VALUES(NEW.usuario, NEW.amigo);
	RETURN NEW;
	END
' LANGUAGE PLPGSQL;

CREATE TRIGGER TG_SeguirAmigo
AFTER INSERT OR UPDATE ON Amigos
FOR EACH ROW
EXECUTE PROCEDURE SegueAmigo();

CREATE OR REPLACE FUNCTION SetNotaMedia()
RETURNS TRIGGER AS'
	DECLARE
    	media numeric(3,2);
	BEGIN
	    SELECT INTO media AVG(nota)
        FROM Avaliacao
        WHERE usuario = NEW.usuario;
        
        UPDATE Usuario
        SET nota = media
        WHERE usuario = NEW.usuario;
	RETURN NEW;
	END
' LANGUAGE PLPGSQL;

CREATE TRIGGER TG_SetNotaMedia
AFTER INSERT OR UPDATE ON Avaliacao
FOR EACH ROW
EXECUTE PROCEDURE SetNotaMedia();