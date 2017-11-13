    --- STORE PROCEDURES CARGA MASIVA
	
CREATE OR REPLACE FUNCTION INSERTAR_CATEGORIAPRODUCTOS() 
RETURNS void AS $$
BEGIN
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Construccion');
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Jardineria');
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('DP1-2');
END;
$$ LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION INSERTAR_PRODUCTOS() 
RETURNS void AS $$
BEGIN
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Mouse R', 20, '2017-11-30 00:00:00', 'Mouse razer', 200, 0, 3, 'M001', '2017-11-07 00:00:00', 't', null, 100, 'GR', 0);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Laptop Lenovo', 100, '2017-11-30 00:00:00', 'laptop leovo i7 ', 100,0, 3, 'L001', '2017-11-07 00:00:00', 't', null, 80, 'KG', 0);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Cemento', 10, '2018-11-07 00:00:00', 'Producto de construccion', 12,0, 1, 'CONS123', '2017-11-07 15:43:03.091331', 't', 1, 9, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Mochila', 500, '2017-11-07 00:00:00', 'Mochila ASUS 17"', 50, 0, 3, 'M002', '2017-11-07 00:00:00', 't', 1, 25, 'GR', 0);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Pala', 5, '2019-11-12 00:00:00', 'Producto de Jardineria', 15,0, 2, 'JARD123', '2017-11-28 00:00:00', 't', 1, 10, 'Kilogramos', 0);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Florero', 5, '2019-11-07 00:00:00', 'ASD', 10, 0, 2, 'JARD234', '2017-11-07 00:00:00', 't', 1, 8, 'kILOGRAMOS', 0);

END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION INSERTAR_USUARIOS_EMPLEADOS() 
RETURNS void AS $$
BEGIN
-- usuarios
--INSERT INTO warehouse.public.usuario(nombreusuario, password, fechacreacion) VALUES ('master', '$1$KrwEX8Qp$ZYrMdh0xwBwAPxyT5i4tM1', '2017-10-25 11:12:43.589861');
INSERT INTO warehouse.public.usuario(nombreusuario, password, fechacreacion) VALUES ('Dp1', '$1$bptrXLDH$3QkOulxaUP0Svo8SeTqgM.', '2017-11-07 20:15:32.768607');
INSERT INTO warehouse.public.usuario(nombreusuario, password, fechacreacion) VALUES ('dp1', '$1$Bxd.PSbn$.K4HFhOuChqQfnRXwj77Z/', '2017-11-07 20:16:55.492775');

-- empleados
--INSERT INTO warehouse.public.empleado(idusuario, numdoc, nombre, apellidos, email, idtipoempleado, fechacreacion, activo, idempleadoauditado) VALUES (1, '11111111', 'Master', '', '', 1, '2017-10-25 11:58:00.308845', 't', 1);
INSERT INTO warehouse.public.empleado(idusuario, numdoc, nombre, apellidos, email, idtipoempleado, fechacreacion, activo, idempleadoauditado) VALUES (2, '43321170', 'Dp1', 'Dp1', 'dp1@gmail.com', 2, '2017-11-07 20:15:32.784594', 'f', 1);
INSERT INTO warehouse.public.empleado(idusuario, numdoc, nombre, apellidos, email, idtipoempleado, fechacreacion, activo, idempleadoauditado) VALUES (3, '43321171', 'dp1', 'dp1', 'dpq@gmail.com', 1, '2017-11-07 20:16:55.499618', 't', 2);

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION INSERTAR_CONDICIONESCOMERCIALES() 
RETURNS void AS $$
BEGIN

INSERT INTO warehouse.public.condicion(tipocondicion, idproductogenerador, idcategoriaprodgen, cantprodgen, idproductodescuento, idcategoriaproddesc, cantproddesc, valordescuento, fechainicio, fechafin, descripcion, idempleadoauditado, activo, factorflete) VALUES ('Flete por Peso', null, null, 0, null, null, 0, 20, '2017-11-12 00:00:00', '2018-11-30 00:00:00', 'Se cobra 20 soles por 100 Kilogramos ', 1, 't', 100);
INSERT INTO warehouse.public.condicion(tipocondicion, idproductogenerador, idcategoriaprodgen, cantprodgen, idproductodescuento, idcategoriaproddesc, cantproddesc, valordescuento, fechainicio, fechafin, descripcion, idempleadoauditado, activo, factorflete) VALUES ('Flete por Distancia', null, null, 0, null, null, 0, 50, '2017-11-12 00:00:00', '2018-11-30 00:00:00', 'Se cobra 50 soles por cada 100Km', 1, 't', 100);

END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION INSERTAR_CLIENTES() 
RETURNS void AS $$
BEGIN

INSERT INTO warehouse.public.cliente(numdoc, razonsocial, telefono, direccion, email, fechacreacion, idempleadoauditado, activo) VALUES ('84597628', 'Pedro Pablo', '986574325', 'San Miguel', 'pedro.pablo@gmail.com', '2017-11-07 16:22:38.979612', 1, 't');
INSERT INTO warehouse.public.cliente(numdoc, razonsocial, telefono, direccion, email, fechacreacion, idempleadoauditado, activo) VALUES ('10433211702', 'dp1', '123', 'dp1', 'dp1@gmail.com', '2017-11-07 20:18:44.851232', 1, 't');

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION INSERTAR_LOTES() 
RETURNS void AS $$
BEGIN

INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (3, '2017-11-12 00:00:00', '2017-11-12 00:00:00',0, '2017-11-12 17:53:43.899784', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (6, '2017-11-12 00:00:00', '2017-11-12 00:00:00',0, '2017-11-12 17:54:13.401527', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (5, '2017-11-12 00:00:00', '2017-11-12 00:00:00',0, '2017-11-12 17:54:34.453522', 1);

INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-12 00:00:00', 1, '2017-11-12 17:53:43.899784', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-12 00:00:00', 1, '2017-11-12 17:54:13.401527', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-12 00:00:00', 1, '2017-11-12 17:54:34.453522', 1, null);

INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (1, 3, 1, 800, null);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (2, 6, 2, 600, null);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (3, 5, 3, 500, null);

END;
$$ LANGUAGE plpgsql;