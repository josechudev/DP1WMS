
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

--- post eliminacion

ALTER SEQUENCE producto_idproducto_seq RESTART;
DELETE FROM warehouse.public.producto; 
UPDATE public.producto  SET idproducto = DEFAULT;
   
   
ALTER SEQUENCE categoriaproducto_idcategoria_seq RESTART;
DELETE FROM warehouse.public.categoriaproducto; 
UPDATE public.categoriaproducto  SET idcategoria = DEFAULT;