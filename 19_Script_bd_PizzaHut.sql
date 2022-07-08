-- Creamos la Base de Datos
CREATE DATABASE db_PizzaHut;

-- Ponemos el uso la Base de Datos
USE db_PizzaHut;

-- Creamos la tabla PERSONA
CREATE TABLE PERSONA (
    IDPER int  NOT NULL IDENTITY(1,1),
    TIPPER char(1)  NOT NULL,
    NOMCOMPER varchar(100)  NOT NULL,
    DNIPER char(8)  NOT NULL UNIQUE,
    CELPER char(9)  NULL UNIQUE,
    EMAILPER varchar(200)  NULL UNIQUE,
    DIRPER varchar(100)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    ESTPER char(1)  NOT NULL,
    CONSTRAINT PERSONA_pk PRIMARY KEY  (IDPER)
);

-- Creamos la tabla PRODUCTO
CREATE TABLE PRODUCTO (
    IDPRO int  NOT NULL IDENTITY(1,1),
    TIPPRO char(1)  NOT NULL,
    NOMPRO varchar(100)  NOT NULL,
    DESPRO varchar(200)  NULL,
    PREPRO decimal(5,2)  NOT NULL,
    ESTPRO char(1)  NOT NULL,
    CONSTRAINT PRODUCTO_pk PRIMARY KEY  (IDPRO)
);

-- Creamos la tabla SUCURSALES
CREATE TABLE SUCURSALES (
    IDSUCUR int  NOT NULL IDENTITY(1,1),
    IDPER int  NOT NULL,
    DIRSUCUR varchar(100)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    ESTSUCUR char(1)  NOT NULL,
    CONSTRAINT SUCURSALES_pk PRIMARY KEY  (IDSUCUR)
);

-- Creamos la tabla UBIGEO
CREATE TABLE UBIGEO (
    CODUBI char(6)  NOT NULL,
    DEPUBI varchar(100)  NOT NULL,
    PROUBI varchar(100)  NOT NULL,
    DISUBI varchar(100)  NOT NULL,
    ESTUBI char(1)  NOT NULL,
    CONSTRAINT UBIGEO_pk PRIMARY KEY  (CODUBI)
);

-- Creamos la tabla VENTA
CREATE TABLE VENTA (
    IDVEN int  NOT NULL IDENTITY(1,1),
    TIPVEN char(1)  NOT NULL,
    IDPER int  NOT NULL,
    FECVEN date  NOT NULL,
    ESTVEN char(1)  NOT NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY  (IDVEN)
);

-- Creamos la tabla VENTADETALLE
CREATE TABLE VENTADETALLE (
    IDVENDET int  NOT NULL IDENTITY(1,1),
    IDVEN int  NOT NULL,
    IDPRO int  NOT NULL,
    CANTPRO int  NOT NULL,
    SUBTOTAVEN decimal(8,2)  NOT NULL,
    CONSTRAINT VENTADETALLE_pk PRIMARY KEY  (IDVENDET)
);

-- Relaci�n entre PERSONA-UBIGEO
ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Relaci�n entre SUCURSALES-PERSONA
ALTER TABLE SUCURSALES ADD CONSTRAINT SUCURSALES_PERSONA
    FOREIGN KEY (IDPER)
    REFERENCES PERSONA (IDPER);

-- Relaci�n entre SUCURSALES-UBIGEO
ALTER TABLE SUCURSALES ADD CONSTRAINT SUCURSALES_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Relaci�n entre VENTADETALLE-PRODUCTO
ALTER TABLE VENTADETALLE ADD CONSTRAINT VENTADETALLE_PRODUCTO
    FOREIGN KEY (IDPRO)
    REFERENCES PRODUCTO (IDPRO);

-- Relaci�n entre VENTADETALLE-VENTA
ALTER TABLE VENTADETALLE ADD CONSTRAINT VENTADETALLE_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN);

-- Relaci�n entre VENTA-PERSONA
ALTER TABLE VENTA ADD CONSTRAINT VENTA_PERSONA
    FOREIGN KEY (IDPER)
    REFERENCES PERSONA (IDPER);

-- Ingresamos los registros de la tabla PERSONA
INSERT INTO PERSONA
		(TIPPER		,NOMCOMPER							,DNIPER			,CELPER			,EMAILPER						,DIRPER						,CODUBI		,ESTPER)
VALUES	('A'		,'Jose Alberto Lazaro Ruiz'			,'45235487'		,'963582104'	,'josealberto@hotmail.com'		,'Av. Jirasoles sin Flor'	,'150501'	,'A'),
		('J'		,'Marco Jose Campos Pusi'			,'56325874'		,'900000001'	,'marcojose@hotmail.com'		,'Av. Paul Walker'			,'150503'	,'A'),
		('J'		,'Elias Tobias Sanchez Campos'		,'96587548'		,'965458741'	,'eliastobias@hotmail.com'		,'Jr. SiempreVivas'			,'150505'	,'A'),
		('J'		,'Sabrina Shakira Campos Francia'	,'21458996'		,'964156544'	,'sabrinashaki@hotmail.com'		,'Av. Las Ma�anitas'		,'150502'	,'A'),
		('V'		,'Carlos Edidson Sanchez ALcala'	,'54545895'		,'964564687'	,'carlossanchez@hotmail.com'	,'Av. Mariscal Benavides'	,'150504'	,'A'),
		('V'		,'Jesus Canales Guando Arias'		,'51424474'		,'931646454'	,'jesuscanales@hotmail.com'		,'Av. Campos Jesus		'	,'150502'	,'A'),
		('V'		,'Jonatan Maicelo Zu�iga Campos'	,'51512151'		,'931354654'	,'jonatanzu�iga@hotmail.com'	,'Av. Alfonso Ugarte	'	,'150504'	,'A'),
		('D'		,'Hugo Jose Meneses Campos'			,'16546546'		,'931232134'	,'hugomeneses@hotmail.com'		,'Calle Hipolito Unanue'	,'150504'	,'A'),
		('D'		,'Jesus Matias Alzas Campos'		,'23546561'		,'900031234'	,'jesusmatias@hotmail.com'		,'Calle Valle Grande'		,'150501'	,'A'),
		('D'		,'Nicoll Stefania Campos Alcala'	,'66516516'		,'900031445'	,'nicollalcala@hotmail.com'		,'Jr. Alcides Carrion'		,'150503'	,'A')
GO

-- Ingresamos los registros de la tabla UBIGEO
INSERT INTO UBIGEO
		(CODUBI		,DEPUBI		,PROUBI		,DISUBI						,ESTUBI)
VALUES	('150501'	,'Lima'		,'Ca�ete'	,'San Vicente de Ca�ete'	,'A'),
		('150502'	,'Lima'		,'Ca�ete'	,'Asia'						,'A'),
		('150503'	,'Lima'		,'Ca�ete'	,'Calango'					,'A'),
		('150504'	,'Lima'		,'Ca�ete'	,'Cerro Azul'				,'A'),
		('150505'	,'Lima'		,'Ca�ete'	,'Chilca'					,'A'),
		('150506'	,'Lima'		,'Ca�ete'	,'Coayllo'					,'A'),
		('150507'	,'Lima'		,'Ca�ete'	,'Imperial'					,'A'),
		('150508'	,'Lima'		,'Ca�ete'	,'Lunahuana'				,'A'),
		('150509'	,'Lima'		,'Ca�ete'	,'Mala'						,'A'),
		('150510'	,'Lima'		,'Ca�ete'	,'Nuevo Imperial'			,'A'),
		('150511'	,'Lima'		,'Ca�ete'	,'Pacaran'					,'A'),
		('150512'	,'Lima'		,'Ca�ete'	,'Quilmana'					,'A'),
		('150513'	,'Lima'		,'Ca�ete'	,'San Antonio'				,'A'),
		('150514'	,'Lima'		,'Ca�ete'	,'San Luis'					,'A'),
		('150515'	,'Lima'		,'Ca�ete'	,'Santa Cruz de Flores'		,'A'),
		('150516'	,'Lima'		,'Ca�ete'	,'Zu�iga'					,'A')
GO

-- Ingresamos los registros de la tabla SUCURSALES
INSERT INTO SUCURSALES
		(IDPER		,DIRSUCUR								,CODUBI		,ESTSUCUR)
VALUES	('2'		,'Calle ALfonso Ugarte Mz B Lote 13'	,'150501'	,'A'),
		('3'		,'Calle Hipolito Unanue Mz c Lote 15'	,'150502'	,'A'),
		('4'		,'Calle Los Angeles Mz H Lote 40'		,'150508'	,'A'),
		('11'		,'Calle Manzanares Mz T Lote 10'		,'150510'	,'A'),
		('12'		,'Jr. Alcantaras Mz B Lote 100'			,'150503'	,'A'),
		('13'		,'Jr. Campanares Mz B Lote 10'			,'150504'	,'A')
GO

-- Ingresamos los registros de la tabla PRODUCTO
INSERT INTO PRODUCTO
		(TIPPRO		,NOMPRO							,DESPRO																				,PREPRO		,ESTPRO)
VALUES	('C'		,'Banquete Grande'				,'1 Pizza Grande, 8 Hut Bread de Queso, 1 Gaseosa de 1L'							,'39.90'	,'A'),
		('C'		,'Banquete Familiar'			,'1 Pizza Familiar, 8 Hut Bread de Queso, 1 Gaseosa de 1L'							,'49.90'	,'A'),
		('C'		,'Banquete Hut Cheese'			,'1 Pizza Familiar Hut Cheese, 8 Hut Bread de Queso, 1 Gaseosa de 1L'				,'59.90'	,'A'),
		('P'		,'Pizza Hawaiana'				,'�El paraiso en la mesa! Se completa con jam�n, jugosa pi�a y queso mozzarella.'	,'15.90'	,'A'),
		('P'		,'Pizza Chicken BBQ'			,'Exquisita combinaci�n de trozos de pollo, pi�a, tocino y salsa BBQ.'				,'45.90'	,'A'),
		('P'		,'Pizza La Chili Hut'			,'Deliciosa combinaci�n de trozos de pollo, pi�a, tocino y salsa Chili thai.'		,'45.90'	,'A'),
		('E'		,'Alitas (9UN)'					,'Pack de 9 Alitas'																	,'20.90'	,'A'),
		('E'		,'Alitas (6UN)'					,'Pack de 6 Alitas'																	,'15.90'	,'A'),
		('E'		,'Rolls de Jamon y Queso'		,'6 Rolls de Jam�n & queso rociados con queso parmesano y tostados'					,'15.60'	,'A'),
		('B'		,'Coca Cola Sin Azucar 1.5 L'	,'*Para recojo en tienda se entregar�n 4 vasos de gaseosa seg�n aplique*'			,'11.00'	,'A')
GO
-- Ingresamos los registros de la tabla VENTA
-- Ingresamos los registros de la tabla VENTADETALLE