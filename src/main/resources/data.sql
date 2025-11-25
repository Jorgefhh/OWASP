-- INSERCIÓN DE UN CLIENTE
INSERT INTO usuario(nombre, apellido, clave, correo, activo) VALUES ('Jorge', 'Huarachi', '$2a$10$XCMlC4vWn9xlD43M8ipXzOwBGE9QjSyaRbClILN9dgEc9MUm1l6CK', 'jorge@ejemplo.com', true);
INSERT INTO cliente(id, puntos) VALUES (1,0);

-- INSERCIÓN DE UN ADMIN:
INSERT INTO usuario(nombre, apellido, clave, correo, activo)
VALUES ('fran', 'Russo', '$2a$10$XCMlC4vWn9xlD43M8ipXzOwBGE9QjSyaRbClILN9dgEc9MUm1l6CK', 'russo@admin.com', true);
INSERT INTO administrador(id) VALUES (2);