CREATE OR REPLACE FUNCTION actualizarStock()
  RETURNS trigger AS
$BODY$
DECLARE
v_esingreso BOOL;
v_cantidad INT;
v_estockParcialLote INT;
v_stockProducto INT;
v_idempleadoauditado INT;
BEGIN
	v_cantidad := new.cantidad;
	SELECT t.esIngreso INTO v_esingreso FROM public.movimiento m, public.tipomovimiento t
	WHERE idmovimiento = NEW.idmovimiento and m.idtipomovimiento = t.idtipomovimiento;
    raise notice 'Value: %', v_esingreso;
    raise notice 'Value: %', v_cantidad;
    
    SELECT stockparcial INTO v_estockParcialLote FROM warehouse.public.lote WHERE idlote = NEW.IDLOTE;
    SELECT stock INTO v_stockProducto FROM warehouse.public.producto WHERE idproducto = NEW.idproducto;
    SELECT idempleadoauditado INTO v_idempleadoauditado FROM warehouse.public.movimiento WHERE idmovimiento = NEW.idmovimiento;
    
    if(v_esingreso = true) then
    	UPDATE	warehouse.public.lote SET stockparcial = v_estockParcialLote + v_cantidad, idempleadoauditado = v_idempleadoauditado WHERE idlote = NEW.IDLOTE;    	
    	UPDATE	warehouse.public.producto SET stock = v_stockProducto + v_cantidad, idempleadoauditado = v_idempleadoauditado WHERE idProducto = NEW.idproducto;
    end if;
    
    if(v_esingreso = false)then
    UPDATE	warehouse.public.lote SET stockparcial =  v_estockParcialLote - v_cantidad, idempleadoauditado = v_idempleadoauditado WHERE idlote = NEW.IDLOTE;
    UPDATE	warehouse.public.producto SET stock = v_stockProducto - v_cantidad, idempleadoauditado = v_idempleadoauditado WHERE idProducto = NEW.idproducto;
    end if;
 	RETURN NEW;
END;

$BODY$ LANGUAGE plpgsql;


CREATE TRIGGER actualizarStockProductoLote
  AFTER INSERT
  ON public.detallemovimiento
  FOR EACH ROW
  EXECUTE PROCEDURE actualizarStock();