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
