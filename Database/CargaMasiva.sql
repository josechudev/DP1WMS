    --- STORE PROCEDURES CARGA MASIVA
	
CREATE OR REPLACE FUNCTION INSERTAR_CATEGORIAPRODUCTOS() 
RETURNS void AS $$
BEGIN
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Construccion');
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Jardineria');
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Electricidad');
INSERT INTO warehouse.public.categoriaproducto(descripcion) VALUES ('Mueble');
END;
$$ LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION INSERTAR_PRODUCTOS() 
RETURNS void AS $$
BEGIN
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Manguera', 1, '2019-11-25 00:00:00', 'Producto de jardineria', 15, 0, 2, 'JAR21', '2017-11-13 00:00:00', 't', null, 12, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Guantes', 1, '2019-11-25 00:00:00', 'Producto de Jardineria', 7, 0, 2, 'JAR24', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Canaletas', 2, '2019-11-25 00:00:00', 'Producto de construccion', 12, 0, 1, 'CONS31', '2017-11-13 00:00:00', 't', null, 10, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Martillo', 7, '2019-11-25 00:00:00', 'Producto de construccion', 9, 0, 1, 'CONS41', '2017-11-13 00:00:00', 't', null, 7, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Clavo', 1, '2019-11-25 00:00:00', 'Producto de construccion', 2, 0, 1, 'CONS51', '2017-11-13 00:00:00', 't', null, 1, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Pala', 10, '2019-11-25 00:00:00', 'Producto de Jardineria', 12, 0, 2, 'JAR11', '2017-11-13 00:00:00', 't', null, 10, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Cemento', 15, '2019-11-25 00:00:00', 'Producto de construccion', 14, 0, 1, 'CONS12', '2017-11-13 00:00:00', 't', null, 10, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Florero', 8, '2019-11-25 00:00:00', 'Producto de jardineria', 9, 0, 2, 'JAR13', '2017-11-13 00:00:00', 't', null, 6, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Ladrillo', 6, '2019-11-25 00:00:00', 'Producto de construccion', 7, 0, 1, 'CONS15', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Ropero', 50, '2019-11-25 00:00:00', 'Producto de Muebles', 150, 0, 4, 'MUE12', '2017-11-13 00:00:00', 't', null, 100, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Estabilizador', 8, '2019-11-25 00:00:00', 'Producto de Electricidad', 20, 0, 3, 'ELE13', '2017-11-13 00:00:00', 't', null, 10, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('Linterna', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELE15', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca1', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD1', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca2', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD2', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca3', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD3', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca4', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD4', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca5', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD5', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca6', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD6', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca7', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD7', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca8', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD8', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca9', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD9', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca10', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD10', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca11', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD11', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca12', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD12', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca13', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD13', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca14', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD14', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca15', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD15', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca16', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD16', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca17', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD17', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca18', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD18', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca19', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD19', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca20', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD20', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca21', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD21', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca22', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD22', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca23', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD23', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca24', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD24', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca25', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD25', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca26', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD26', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca27', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD27', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca28', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD28', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca29', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD29', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca30', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD30', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca31', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD31', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca32', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD32', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca33', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD33', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca34', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD34', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca35', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD35', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca36', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD36', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca37', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD37', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca38', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD38', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca39', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD39', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca40', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD40', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca41', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD41', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca42', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD42', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca43', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD43', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca44', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD44', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca45', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD45', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca46', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD46', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca47', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD47', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca48', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD48', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca49', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD49', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca50', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD50', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca51', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD51', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca52', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD52', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca53', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD53', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca54', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD54', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca55', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD55', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca56', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD56', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca57', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD57', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca58', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD58', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca59', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD59', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca60', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD60', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca61', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD61', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca62', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD62', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca63', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD63', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca64', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD64', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca65', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD65', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca66', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD66', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca67', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD67', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca68', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD68', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca69', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD69', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca70', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD70', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca71', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD71', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca72', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD72', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca73', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD73', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca74', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD74', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca75', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD75', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca76', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD76', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca77', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD77', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca78', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD78', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca79', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD79', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca80', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD80', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca81', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD81', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca82', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD82', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca83', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD83', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca84', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD84', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca85', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD85', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca86', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD86', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca87', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD87', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca88', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD88', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca89', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD89', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca90', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD90', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca91', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD91', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca92', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD92', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca93', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD93', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca94', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD94', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca95', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD95', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca96', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD96', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca97', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD97', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca98', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD98', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca99', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD99', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
INSERT INTO warehouse.public.producto(nombreproducto, peso, fechavencimiento, descripcion, precio, stock, idcategoria, codigo, fechacreacion, activo, idempleadoauditado, preciocompra, unidades, stockminimo) VALUES ('FocoMarca100', 1, '2019-11-25 00:00:00', 'Producto de electricidad', 7, 0, 3, 'ELEAD100', '2017-11-13 00:00:00', 't', null, 5, 'Kilogramos', 50);
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
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (1, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:47:07.803461', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (2, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:47:48.450857', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (3, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:48:38.413971', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (4, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:49:09.632428', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (5, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:49:45.976721', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (6, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:50:36.751015', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (7, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:51:15.102326', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (8, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:53:12.070354', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (9, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:53:55.419114', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (10, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:54:35.486115', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (11, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:55:08.691174', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (12, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 21:55:44.913575', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (13, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:31:36.761943', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (14, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:32:40.182049', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (15, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:33:56.678185', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (16, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:34:49.214575', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (17, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:35:25.321048', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (18, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:36:11.878286', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (19, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:36:41.397733', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (20, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:38:37.640958', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (21, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:40:37.683628', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (22, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:41:16.023935', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (23, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:41:52.665682', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (24, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:42:39.358243', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (25, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:44:24.278522', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (26, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:45:22.954175', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (27, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:46:12.324779', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (28, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:48:37.081184', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (29, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:49:46.014913', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (30, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:50:15.023494', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (31, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:51:06.26731', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (32, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:52:41.109055', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (33, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:53:38.627168', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (34, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:55:20.92057', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (35, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:56:42.235424', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (36, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:57:11.501473', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (37, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:57:42.243259', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (38, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:58:14.618651', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (39, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:58:41.692659', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (40, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:59:18.891422', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (41, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-18 23:59:49.577834', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (42, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-19 00:00:23.919394', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (43, '2017-11-18 00:00:00', '2017-11-18 00:00:00', 0, '2017-11-19 00:01:15.927101', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (44, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:01:43.127431', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (45, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:02:34.437792', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (46, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:03:06.902521', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (47, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:04:20.774089', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (48, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:04:56.688765', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (49, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:05:26.16684', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (50, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:05:54.400508', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (51, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:06:32.041465', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (52, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:07:08.670106', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (53, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:07:48.542097', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (54, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:08:26.50269', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (55, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:09:08.09661', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (56, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:09:48.140979', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (57, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:20:02.611801', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (58, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:21:16.094331', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (59, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:22:00.738953', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (60, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:22:54.245246', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (61, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:24:07.718604', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (62, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:31:24.327733', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (63, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:31:58.227834', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (64, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:32:27.771199', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (65, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:33:03.614127', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (66, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:33:53.37547', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (67, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:34:24.349044', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (68, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:34:57.427509', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (69, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:36:01.443251', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (70, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:37:25.282886', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (71, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:38:09.347508', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (72, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:38:49.718012', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (73, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:39:27.010249', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (74, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:49:00.204092', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (75, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:49:48.632671', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (76, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:50:29.418685', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (77, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:51:19.015616', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (78, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:51:58.063533', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (79, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:52:35.645385', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (80, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:53:08.345136', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (81, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:53:41.352196', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (82, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:54:12.88548', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (83, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:54:47.842239', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (84, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:55:13.950426', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (85, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:56:40.46034', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (86, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:57:13.872151', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (87, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:57:47.149358', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (88, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:58:19.582675', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (89, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:59:01.563342', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (90, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 00:59:36.919954', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (91, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:00:07.9121', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (92, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:00:44.633563', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (93, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:01:24.435728', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (94, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:02:30.737985', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (95, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:03:05.477666', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (96, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:03:38.12363', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (97, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:04:20.169288', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (98, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:05:58.072247', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (99, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:06:55.233642', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (100, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:07:28.457274', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (101, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:08:06.157967', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (102, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:08:40.16291', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (103, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:10:14.279418', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (104, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:10:48.582981', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (105, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:11:21.169646', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (106, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:11:57.674839', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (107, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:13:01.955488', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (108, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:13:36.682229', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (109, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:14:17.743858', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (110, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:15:00.586379', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (111, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:15:32.8602', 1);
INSERT INTO warehouse.public.lote(idproducto, fechalote, fechaentrada, stockparcial, fechacreacion, idempleadoauditado) VALUES (112, '2017-11-19 00:00:00', '2017-11-19 00:00:00', 0, '2017-11-19 01:17:02.185535', 1);


INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:47:07.803461', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:47:48.450857', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:48:38.413971', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:49:09.632428', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:49:45.976721', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:50:36.751015', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:51:15.102326', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:53:12.070354', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:53:55.419114', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:54:35.486115', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:55:08.691174', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 21:55:44.913575', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:31:36.761943', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:32:40.182049', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:33:56.678185', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:34:49.214575', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:35:25.321048', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:36:11.878286', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:36:41.397733', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:38:37.640958', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:40:37.683628', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:41:16.023935', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:41:52.665682', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:42:39.358243', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:44:24.278522', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:45:22.954175', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:46:12.324779', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:48:37.081184', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:49:46.014913', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:50:15.023494', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:51:06.26731', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:52:41.109055', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:53:38.627168', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:55:20.92057', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:56:42.235424', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:57:11.501473', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:57:42.243259', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:58:14.618651', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:58:41.692659', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:59:18.891422', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-18 23:59:49.577834', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-19 00:00:23.919394', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-18 00:00:00', 1, '2017-11-19 00:01:15.927101', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:01:43.127431', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:02:34.437792', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:03:06.902521', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:04:20.774089', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:04:56.688765', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:05:26.16684', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:05:54.400508', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:06:32.041465', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:07:08.670106', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:07:48.542097', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:08:26.50269', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:09:08.09661', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:09:48.140979', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:20:02.611801', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:21:16.094331', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:22:00.738953', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:22:54.245246', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:24:07.718604', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:31:24.327733', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:31:58.227834', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:32:27.771199', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:33:03.614127', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:33:53.37547', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:34:24.349044', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:34:57.427509', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:36:01.443251', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:37:25.282886', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:38:09.347508', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:38:49.718012', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:39:27.010249', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:49:00.204092', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:49:48.632671', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:50:29.418685', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:51:19.015616', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:51:58.063533', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:52:35.645385', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:53:08.345136', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:53:41.352196', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:54:12.88548', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:54:47.842239', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:55:13.950426', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:56:40.46034', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:57:13.872151', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:57:47.149358', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:58:19.582675', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:59:01.563342', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 00:59:36.919954', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:00:07.9121', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:00:44.633563', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:01:24.435728', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:02:30.737985', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:03:05.477666', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:03:38.12363', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:04:20.169288', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:05:58.072247', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:06:55.233642', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:07:28.457274', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:08:06.157967', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:08:40.16291', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:10:14.279418', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:10:48.582981', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:11:21.169646', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:11:57.674839', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:13:01.955488', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:13:36.682229', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:14:17.743858', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:15:00.586379', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:15:32.8602', 1, null);
INSERT INTO warehouse.public.movimiento(totalproductos, observaciones, fechamovimiento, idtipomovimiento, fechacreacion, idempleadoauditado, idenvio) VALUES (1, '', '2017-11-19 00:00:00', 1, '2017-11-19 01:17:02.185535', 1, null);

INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (1, 1, 1, 400, 1);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (2, 2, 2, 450, 46);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (3, 3, 3, 380, 61);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (4, 4, 4, 500, 97);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (5, 5, 5, 800, 127);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (6, 6, 6, 340, 160);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (7, 7, 7, 400, 181);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (8, 8, 8, 370, 190);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (9, 9, 9, 500, 235);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (10, 10, 10, 250, 244);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (11, 11, 11, 480, 252);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (12, 12, 12, 340, 294);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (13, 13, 13, 100, 4);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (14, 14, 14, 100, 7);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (15, 15, 15, 100, 10);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (16, 16, 16, 100, 13);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (17, 17, 17, 100, 16);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (18, 18, 18, 100, 19);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (19, 19, 19, 100, 22);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (20, 20, 20, 100, 25);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (21, 21, 21, 100, 28);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (22, 22, 22, 100, 31);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (23, 23, 23, 100, 34);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (24, 24, 24, 100, 37);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (25, 25, 25, 100, 40);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (26, 26, 26, 100, 43);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (27, 27, 27, 100, 49);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (28, 28, 28, 100, 52);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (29, 29, 29, 100, 55);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (30, 30, 30, 100, 58);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (31, 31, 31, 100, 64);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (32, 32, 32, 100, 67);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (33, 33, 33, 100, 70);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (34, 34, 34, 100, 73);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (35, 35, 35, 100, 76);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (36, 36, 36, 100, 79);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (37, 37, 37, 100, 82);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (38, 38, 38, 100, 85);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (39, 39, 39, 100, 88);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (40, 40, 40, 100, 91);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (41, 41, 41, 100, 94);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (42, 42, 42, 100, 100);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (43, 43, 43, 100, 103);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (44, 44, 44, 100, 106);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (45, 45, 45, 100, 109);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (46, 46, 46, 100, 112);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (47, 47, 47, 100, 115);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (48, 48, 48, 100, 118);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (49, 49, 49, 100, 121);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (50, 50, 50, 100, 124);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (51, 51, 51, 100, 130);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (52, 52, 52, 100, 133);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (53, 53, 53, 100, 136);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (54, 54, 54, 100, 139);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (55, 55, 55, 100, 142);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (56, 56, 56, 100, 145);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (57, 57, 57, 100, 148);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (58, 58, 58, 100, 151);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (59, 59, 59, 100, 154);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (60, 60, 60, 100, 157);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (61, 61, 61, 100, 163);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (62, 62, 62, 100, 166);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (63, 63, 63, 100, 169);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (64, 64, 64, 100, 172);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (65, 65, 65, 100, 175);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (66, 66, 66, 100, 178);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (67, 67, 67, 100, 184);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (68, 68, 68, 100, 187);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (69, 69, 69, 100, 193);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (70, 70, 70, 100, 196);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (71, 71, 71, 100, 199);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (72, 72, 72, 100, 202);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (73, 73, 73, 100, 205);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (74, 74, 74, 100, 208);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (75, 75, 75, 100, 211);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (76, 76, 76, 100, 214);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (77, 77, 77, 100, 217);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (78, 78, 78, 100, 220);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (79, 79, 79, 100, 223);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (80, 80, 80, 100, 226);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (81, 81, 81, 100, 229);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (82, 82, 82, 100, 232);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (83, 83, 83, 100, 238);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (84, 84, 84, 100, 241);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (85, 85, 85, 100, 247);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (86, 86, 86, 100, 248);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (87, 87, 87, 100, 249);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (88, 88, 88, 100, 250);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (89, 89, 89, 100, 251);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (90, 90, 90, 100, 252);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (91, 91, 91, 100, 253);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (92, 92, 92, 100, 254);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (93, 93, 93, 100, 255);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (94, 94, 94, 100, 256);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (95, 95, 95, 100, 257);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (96, 96, 96, 100, 258);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (97, 97, 97, 100, 259);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (98, 98, 98, 100, 259);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (99, 99, 99, 100, 260);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (100, 100, 100, 100, 261);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (101, 101, 101, 100, 262);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (102, 102, 102, 100, 263);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (103, 103, 103, 100, 264);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (104, 104, 104, 100, 265);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (105, 105, 105, 100, 266);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (106, 106, 106, 100, 267);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (107, 107, 107, 100, 291);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (108, 108, 108, 100, 290);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (109, 109, 109, 100, 289);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (110, 110, 110, 100, 288);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (111, 111, 111, 100, 287);
INSERT INTO warehouse.public.detallemovimiento(idmovimiento, idproducto, idlote, cantidad, idcajon) VALUES (112, 112, 112, 100, 284);


INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (1, 1, 1, 400);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (2, 2, 46, 450);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (3, 3, 61, 380);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (4, 4, 97, 500);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (5, 5, 127, 800);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (6, 6, 160, 340);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (7, 7, 181, 400);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (8, 8, 190, 370);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (9, 9, 235, 500);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (10, 10, 244, 250);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (11, 11, 252, 480);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (12, 12, 294, 340);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (13, 13, 4, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (14, 14, 7, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (15, 15, 10, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (16, 16, 13, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (17, 17, 16, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (18, 18, 19, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (19, 19, 22, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (20, 20, 25, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (21, 21, 28, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (22, 22, 31, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (23, 23, 34, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (24, 24, 37, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (25, 25, 40, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (26, 26, 43, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (27, 27, 49, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (28, 28, 52, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (29, 29, 55, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (30, 30, 58, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (31, 31, 64, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (32, 32, 67, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (33, 33, 70, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (34, 34, 73, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (35, 35, 76, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (36, 36, 79, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (37, 37, 82, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (38, 38, 85, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (39, 39, 88, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (40, 40, 91, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (41, 41, 94, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (42, 42, 100, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (43, 43, 103, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (44, 44, 106, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (45, 45, 109, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (46, 46, 112, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (47, 47, 115, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (48, 48, 118, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (49, 49, 121, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (50, 50, 124, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (51, 51, 130, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (52, 52, 133, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (53, 53, 136, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (54, 54, 139, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (55, 55, 142, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (56, 56, 145, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (57, 57, 148, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (58, 58, 151, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (59, 59, 154, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (60, 60, 157, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (61, 61, 163, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (62, 62, 166, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (63, 63, 169, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (64, 64, 172, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (65, 65, 175, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (66, 66, 178, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (67, 67, 184, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (68, 68, 187, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (69, 69, 193, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (70, 70, 196, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (71, 71, 199, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (72, 72, 202, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (73, 73, 205, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (74, 74, 208, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (75, 75, 211, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (76, 76, 214, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (77, 77, 217, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (78, 78, 220, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (79, 79, 223, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (80, 80, 226, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (81, 81, 229, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (82, 82, 232, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (83, 83, 238, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (84, 84, 241, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (85, 85, 247, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (86, 86, 248, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (87, 87, 249, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (88, 88, 250, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (89, 89, 251, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (90, 90, 252, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (91, 91, 253, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (92, 92, 254, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (93, 93, 255, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (94, 94, 256, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (95, 95, 257, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (96, 96, 258, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (97, 97, 259, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (98, 98, 259, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (99, 99, 260, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (100, 100, 261, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (101, 101, 262, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (102, 102, 263, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (103, 103, 264, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (104, 104, 265, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (105, 105, 266, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (106, 106, 267, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (107, 107, 291, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (108, 108, 290, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (109, 109, 289, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (110, 110, 288, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (111, 111, 287, 100);
INSERT INTO warehouse.public.ubicacion(idlote, idproducto, idcajon, cantidad) VALUES (112, 112, 284, 100);


END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION LIMPIAR_DATA() 
RETURNS void AS $$
BEGIN

-- en ese orden ( me da miedo el de condiciones (descuentos) ) 

-- Para estas tablas no le borre data ni resetear su secuencia
-- No almacen 
-- No area 
-- No cajon
-- No categoriaproducto
-- No cliente
-- No empleado
-- No estado pedido
-- No producto
-- No Rack
-- No Ruta
-- No Seccion
-- No Tipo Comprobante
-- No Tipo Empleado
-- No Tipo Empleado x seccion
-- No Tipo Movimiento
-- No Usuario
------------------------------------------------


ALTER SEQUENCE auditoria_idauditoria_seq RESTART;
DELETE FROM warehouse.public.auditoria; 
UPDATE public.auditoria  SET idauditoria = DEFAULT;


ALTER SEQUENCE detallecomprobante_iddetallecomprobante_seq RESTART;
DELETE FROM warehouse.public.detallecomprobante; 
UPDATE public.detallecomprobante  SET iddetallecomprobante = DEFAULT;

ALTER SEQUENCE detalleenvio_iddetalleenvio_seq RESTART;
DELETE FROM warehouse.public.detalleenvio; 
UPDATE public.detalleenvio  SET iddetalleenvio = DEFAULT;


ALTER SEQUENCE detalleguia_iddetalleguia_seq RESTART;
DELETE FROM warehouse.public.detalleguia; 
UPDATE public.detalleguia  SET iddetalleguia = DEFAULT;


ALTER SEQUENCE detallemovimiento_iddetallemovimiento_seq RESTART;
DELETE FROM warehouse.public.detallemovimiento; 
UPDATE public.detallemovimiento  SET iddetallemovimiento = DEFAULT;

ALTER SEQUENCE detallepedido_iddetallepedido_seq RESTART;
DELETE FROM warehouse.public.detallepedido; 
UPDATE public.detallepedido  SET iddetallepedido = DEFAULT;

ALTER SEQUENCE detalleproforma_iddetalleproforma_seq RESTART;
DELETE FROM warehouse.public.detalleproforma; 
UPDATE public.detalleproforma  SET iddetalleproforma = DEFAULT;

ALTER SEQUENCE guiaremision_idguiaremision_seq RESTART;
DELETE FROM warehouse.public.guiaremision; 
UPDATE public.guiaremision  SET idguiaremision = DEFAULT;

ALTER SEQUENCE lote_idlote_seq RESTART;
DELETE FROM warehouse.public.lote; 
UPDATE public.lote  SET idlote = DEFAULT;

ALTER SEQUENCE movimiento_idmovimiento_seq RESTART;
DELETE FROM warehouse.public.movimiento; 
UPDATE public.movimiento  SET idmovimiento = DEFAULT;

ALTER SEQUENCE notacredito_idnotadecredito_seq RESTART;
DELETE FROM warehouse.public.notacredito; 
UPDATE public.notacredito  SET idnotadecredito = DEFAULT;


ALTER SEQUENCE envio_idenvio_seq RESTART;
DELETE FROM warehouse.public.envio; 
UPDATE public.envio  SET idenvio = DEFAULT;


ALTER SEQUENCE pedido_idpedido_seq RESTART;
DELETE FROM warehouse.public.pedido; 
UPDATE public.pedido  SET idpedido = DEFAULT;


ALTER SEQUENCE comprobantepago_idcomprobante_seq RESTART;
DELETE FROM warehouse.public.comprobantepago; 
UPDATE public.comprobantepago  SET idcomprobante = DEFAULT;


ALTER SEQUENCE descuento_iddescuento_seq RESTART;
DELETE FROM warehouse.public.condicion; 
UPDATE public.condicion  SET idcondicion = DEFAULT;

ALTER SEQUENCE proforma_idproforma_seq RESTART;
DELETE FROM warehouse.public.proforma; 
UPDATE public.proforma  SET idproforma = DEFAULT;

DELETE FROM warehouse.public.ubicacion; 

ALTER SEQUENCE producto_idproducto_seq RESTART;
DELETE FROM warehouse.public.producto; 
UPDATE public.producto  SET idproducto = DEFAULT;
    
ALTER SEQUENCE categoriaproducto_idcategoria_seq RESTART;
DELETE FROM warehouse.public.categoriaproducto; 
UPDATE public.categoriaproducto  SET idcategoria = DEFAULT;

ALTER SEQUENCE cliente_idcliente_seq RESTART;
DELETE FROM warehouse.public.cliente; 
UPDATE public.cliente  SET idcliente = DEFAULT;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION INSERTAR_PEDIDOS() 
RETURNS void AS $$
BEGIN
INSERT INTO warehouse.public.pedido(idestadopedido, esdevolucion, fechacreacion, observaciones, idempleadoauditado, idcliente, total, subtotal, costoflete) VALUES (1, 'f', '2017-11-19 02:35:13.46187', '', 1, 1, 3500, 3500, 0);

INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (111, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (110, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (109, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (108, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (107, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (106, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (105, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (104, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (103, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (102, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (101, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (100, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (112, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (99, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (98, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (97, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (96, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (95, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (94, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (93, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (92, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (91, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (90, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (89, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (88, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (87, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (86, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (85, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (84, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (83, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (82, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (81, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (80, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (79, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (78, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (77, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (76, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (75, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (74, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (73, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (72, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (71, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (70, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (69, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (68, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (67, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (66, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (65, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (64, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (63, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (62, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (61, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (60, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (59, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (58, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (57, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (56, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (55, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (54, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (53, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (52, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (51, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (50, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (49, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (48, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (47, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (46, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (45, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (44, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (43, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (42, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (41, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (40, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (39, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (38, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (37, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (36, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (35, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (34, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (33, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (32, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (31, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (30, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (29, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (28, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (27, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (26, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (25, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (24, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (23, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (22, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (21, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (20, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (19, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (18, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (17, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (16, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (15, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (14, 1, 5, 7, 0, 35);
INSERT INTO warehouse.public.detallepedido( idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) VALUES (13, 1, 5, 7, 0, 35);

INSERT INTO warehouse.public.envio(fechaenvio, destino, costoflete, idpedido, realizado, distancia) VALUES ('2017-11-19 00:00:00', 'EnvioMasivo', 0, 1, 'f', 0);

INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 111, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 110, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 109, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 108, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 107, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 106, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 105, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 104, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 103, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 102, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 101, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 100, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 112, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 99, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 98, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 97, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 96, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 95, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 94, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 93, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 92, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 91, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 90, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 89, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 88, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 87, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 86, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 85, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 84, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 83, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 82, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 81, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 80, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 79, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 78, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 77, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 76, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 75, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 74, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 73, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 72, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 71, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 70, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 69, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 68, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 67, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 66, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 65, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 64, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 63, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 62, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 31, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 61, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 60, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 59, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 58, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 57, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 56, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 55, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 54, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 53, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 52, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 51, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 50, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 49, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 48, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 47, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 46, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 45, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 44, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 43, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 42, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 41, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 40, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 39, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 38, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 37, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 36, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 35, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 34, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 33, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 32, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 30, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 29, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 28, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 27, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 26, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 25, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 24, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 23, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 22, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 21, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 20, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 19, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 18, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 17, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 16, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 15, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 14, 5);
INSERT INTO warehouse.public.detalleenvio( idenvio, idproducto, cantidad) VALUES (1, 13, 5);

END;
$$ LANGUAGE plpgsql;