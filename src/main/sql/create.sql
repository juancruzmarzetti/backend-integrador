DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS(
    MATRICULA INT PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL,
    APELLIDO VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    APELLIDO VARCHAR(50) NOT NULL,
    DOMICILIO VARCHAR(100) NOT NULL,
    DNI INT NOT NULL,
    FECHA_DE_ALTA DATE NOT NULL,
    USUARIO VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CALLE VARCHAR(100) NOT NULL,
    NUMERO INT NOT NULL,
    LOCALIDAD VARCHAR(100) NOT NULL,
    PROVINCIA VARCHAR(100) NOT NULL);

DROP TABLE IF EXISTS LOGS;
CREATE TABLE LOGS(
    USER_ID VARCHAR(20) NOT NULL,
    DATED DATETIME NOT NULL,
    LOGGER VARCHAR(50) NOT NULL,
    LEVEL VARCHAR(10) NOT NULL,
    MESSAGE VARCHAR(1000) NOT NULL);
