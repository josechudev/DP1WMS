--agrupado por productos
select
p.nombreproducto,
sum(dm.cantidad),
tm.descripcion,
p.precio,
p.preciocompra,
(p.precio * dm.cantidad) valortotal,
tm.esingreso
from movimiento m
left join detallemovimiento as dm on dm.iddetallemovimiento = m.idmovimiento
left join tipomovimiento as tm on tm.idtipomovimiento = m.idtipomovimiento
left join producto as p on p.idproducto = dm.idproducto
where m.idtipomovimiento is not null and p.idcategoria=1 and m.idtipomovimiento >1
group by m.idtipomovimiento,dm.idproducto,p.nombreproducto,dm.cantidad,tm.descripcion,p.preciocompra,p.precio,tm.esingreso

select * from movimiento

select * from almacen
select * from producto
select * from seccion

select * from rack
seleCt * from INFORMATION_SCHEMA.TABLES

select * from pedido

select R.TABLE_NAME
from INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE u
inner join INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS FK
    on U.CONSTRAINT_CATALOG = FK.UNIQUE_CONSTRAINT_CATALOG
    and U.CONSTRAINT_SCHEMA = FK.UNIQUE_CONSTRAINT_SCHEMA
    and U.CONSTRAINT_NAME = FK.UNIQUE_CONSTRAINT_NAME
inner join INFORMATION_SCHEMA.KEY_COLUMN_USAGE R
    ON R.CONSTRAINT_CATALOG = FK.CONSTRAINT_CATALOG
    AND R.CONSTRAINT_SCHEMA = FK.CONSTRAINT_SCHEMA
    AND R.CONSTRAINT_NAME = FK.CONSTRAINT_NAME
WHERE U.COLUMN_NAME = 'idmovimiento'
  AND U.TABLE_NAME = 'movimiento'


select * from detallemovimiento
select * from producto
select * from detallecomprobante
select * from comprobantepago
select * from lote


select p.nombreproducto,cp.idcomprobante from producto p
left join detallemovimiento as dm on p.idproducto = dm.idproducto
left join detallecomprobante as dc on p.idproducto = dc.idproducto
left join comprobantepago as cp on dc.idcomprobante = cp.idcomprobante

--reporte de almacen
select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,
sum(coalesce(dp.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(dp.cantidad,0))) stock_logico
from producto as p
left join detallepedido as dp on dp.idproducto = p.idproducto
left join pedido as pd on pd.idpedido = dp.idpedido
where (not pd.esdevolucion) and pd.idestadopedido = 1 and pd.fechacreacion < ? and pd.fechacreacion> ?
group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra;


select * from envio
select * from detalleenvio

select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,
sum(coalesce(de.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(de.cantidad,0))) stock_logico
from producto as p
left join detallepedido as dp on dp.idproducto = p.idproducto
left join pedido as pd on pd.idpedido = dp.idpedido
left join envio as e on e.idpedido = pd.idpedido
left join detalleenvio as de on de.idenvio = e.idenvio
where (not pd.esdevolucion) and pd.idestadopedido in (1,5) and not(e.realizado)
group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra;
