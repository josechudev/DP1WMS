CREATE EXTENSION PGCRYPTO;

#Master User
INSERT INTO tipoempleado (descripcion) VALUES ('Master');
INSERT INTO usuario (nombreusuario, password) VALUES ('master', crypt('master', gen_salt('md5')));
INSERT INTO empleado (idusuario, nombre, apellidos, idtipoempleado, numdoc) VALUES (1, 'Master', '', 1, '314159');

#Estados de pedido
INSERT INTO estadopedido (descripcion) VALUES ('No atendido');
INSERT INTO estadopedido (descripcion) VALUES ('Atendido');
INSERT INTO estadopedido (descripcion) VALUES ('Rechazado');
INSERT INTO estadopedido (descripcion) VALUES ('Cancelado por cliente');
INSERT INTO estadopedido (descripcion) VALUES ('Cancelado por no pagar');

