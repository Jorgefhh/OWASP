-- INSERCIÓN DE UN CLIENTE
INSERT INTO usuario(nombre, apellido, clave, correo, activo) VALUES ('cliente', 'cliente', '$2a$10$XCMlC4vWn9xlD43M8ipXzOwBGE9QjSyaRbClILN9dgEc9MUm1l6CK', 'cliente@ejemplo.com', true);
INSERT INTO cliente(id, puntos) VALUES (1,0);

INSERT INTO usuario(nombre, apellido, clave, correo, activo) VALUES ('cliente2', 'cliente2', '$2a$10$XCMlC4vWn9xlD43M8ipXzOwBGE9QjSyaRbClILN9dgEc9MUm1l6CK', 'cliente2@ejemplo.com', true);
INSERT INTO cliente(id, puntos) VALUES (2,0);

-- INSERCIÓN DE UN ADMIN:
INSERT INTO usuario(nombre, apellido, clave, correo, activo)
VALUES ('admin', 'admin', '$2a$10$XCMlC4vWn9xlD43M8ipXzOwBGE9QjSyaRbClILN9dgEc9MUm1l6CK', 'admin@ejemplo.com', true);
INSERT INTO administrador(id) VALUES (3);