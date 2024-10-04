DROP DATABASE IF EXISTS Tienda;
CREATE DATABASE Tienda;
Use Tienda;

CREATE TABLE Producto(
                         id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         precio DOUBLE NOT NULL,
                         cantidad INT UNSIGNED DEFAULT 0
);


CREATE TABLE Pedido (
                        numero INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        producto_id INT UNSIGNED,
                        cantidad INT UNSIGNED NOT NULL,
                        total DOUBLE NOT NULL,
                        FOREIGN KEY (producto_id) REFERENCES Producto(id)
);


INSERT INTO Producto VALUES
                          (1, "Pelota", 15, 10),
                          (2, "Aro", 10, 15),
                          (3, "Cinta", 20, 12),
                          (4, "Mazas", 18, 21),
                          (5, "Cuerda", 9, 8),
                          (6, "Maillot Rosa", 14, 10),
                          (7, "Maillot Morado", 14, 8),
                          (8, "Maillot Azul", 14, 13);




INSERT INTO Pedido VALUES
                        (1, 2, 3, 45),
                        (2, 8, 1, 14);
