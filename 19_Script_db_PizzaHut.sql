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

-- Relación entre PERSONA-UBIGEO
ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Relación entre SUCURSALES-PERSONA
ALTER TABLE SUCURSALES ADD CONSTRAINT SUCURSALES_PERSONA
    FOREIGN KEY (IDPER)
    REFERENCES PERSONA (IDPER);

-- Relación entre SUCURSALES-UBIGEO
ALTER TABLE SUCURSALES ADD CONSTRAINT SUCURSALES_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Relación entre VENTADETALLE-PRODUCTO
ALTER TABLE VENTADETALLE ADD CONSTRAINT VENTADETALLE_PRODUCTO
    FOREIGN KEY (IDPRO)
    REFERENCES PRODUCTO (IDPRO);

-- Relación entre VENTADETALLE-VENTA
ALTER TABLE VENTADETALLE ADD CONSTRAINT VENTADETALLE_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN);

-- Relación entre VENTA-PERSONA
ALTER TABLE VENTA ADD CONSTRAINT VENTA_PERSONA
    FOREIGN KEY (IDPER)
    REFERENCES PERSONA (IDPER);
