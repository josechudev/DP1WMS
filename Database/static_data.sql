CREATE EXTENSION PGCRYPTO;


--Estados de pedido
INSERT INTO estadopedido (descripcion) VALUES ('En espera');
INSERT INTO estadopedido (descripcion) VALUES ('Atendido');
INSERT INTO estadopedido (descripcion) VALUES ('Rechazado');
INSERT INTO estadopedido (descripcion) VALUES ('Cancelado por cliente');
INSERT INTO estadopedido (descripcion) VALUES ('Despachando envios');

--Secciones
--Administracion
INSERT INTO seccion (descripcion) VALUES ('Administración de Roles');
INSERT INTO seccion (descripcion) VALUES ('Administración de Usuarios');
INSERT INTO seccion (descripcion) VALUES ('Administración de Clientes');
INSERT INTO seccion (descripcion) VALUES ('Administración de Categorías');
INSERT INTO seccion (descripcion) VALUES ('Administración de Productos');
--Almacenes
INSERT INTO seccion (descripcion) VALUES ('Creación y Modificación');
INSERT INTO seccion (descripcion) VALUES ('Movimientos - Ingreso/Salida Producto');
INSERT INTO seccion (descripcion) VALUES ('Movimientos - Crear Lote');
INSERT INTO seccion (descripcion) VALUES ('Despacho de Envios');

--Ventas
INSERT INTO seccion (descripcion) VALUES ('Admninistración de Condiciones Comerciales');
INSERT INTO seccion (descripcion) VALUES ('Generación de Proforma');
INSERT INTO seccion (descripcion) VALUES ('Consultas de Pedidos');
INSERT INTO seccion (descripcion) VALUES ('Generación de Pedidos');
INSERT INTO seccion (descripcion) VALUES ('Administración de Guia de Remisión');

--Reporte
INSERT INTO seccion (descripcion) VALUES ('Reporte Kardex');
INSERT INTO seccion (descripcion) VALUES ('Reporte de Almacén');

--Master User
--tipo empleado
INSERT INTO tipoempleado (descripcion) VALUES ('Master');
-- usuario
INSERT INTO usuario (nombreusuario, password) VALUES ('master', crypt('master', gen_salt('md5')));
-- empleado
INSERT INTO empleado (idusuario, nombre, apellidos, idtipoempleado, numdoc) VALUES (1, 'Master', '', 1, '314159');
--permisos del master
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,1,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,2,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,3,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,4,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,5,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,6,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,7,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,8,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,9,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,10,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,11,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,12,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,13,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,14,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,15,1);
INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, idempleadoauditado)
VALUES (1,16,1);
