CREATE DATABASE jogo_adivinha;

USE jogo_adivinha;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE resultados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50),
    nivel VARCHAR(50),
    tentativas INT,
    sucesso BOOLEAN,
    data_jogo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario) REFERENCES usuarios(usuario)
);
