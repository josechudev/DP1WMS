CREATE OR REPLACE FUNCTION public.actualizar_estado_pedido() RETURNS TRIGGER AS $body$
DECLARE
	envios_realizados integer;
	num_envios integer;
BEGIN
	SELECT SUM(CASE WHEN envio.realizado THEN 1 else 0 END)::integer INTO envios_realizados FROM envio WHERE envio.idpedido = NEW.idpedido;
	SELECT COUNT(envio.idenvio)::integer INTO num_envios FROM envio WHERE envio.idpedido = NEW.idpedido;
    IF envios_realizados = 0 THEN
    	--Pedido en espera por ser atendido
    	UPDATE pedido SET idestadopedido = 1 WHERE idpedido = NEW.idpedido;
    ELSIF (envios_realizados = num_envios) THEN
    	--Pedido atendido con exito
    	UPDATE pedido SET idestadopedido = 2 WHERE idpedido = NEW.idpedido;
    ELSE
    	--Pedido despachando los envios
    	UPDATE pedido SET idestadopedido = 5 WHERE idpedido = NEW.idpedido;
    END IF;
END;
$body$
LANGUAGE plpgsql
SECURITY DEFINER
SET search_path = public;


-- To add this trigger to a table, use:
CREATE TRIGGER envios_actualizar_estado_pedido
AFTER UPDATE ON envio
FOR EACH ROW EXECUTE PROCEDURE public.actualizar_estado_pedido();