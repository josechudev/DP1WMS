CREATE OR REPLACE FUNCTION public.if_modified_func() RETURNS TRIGGER AS $body$
DECLARE
    v_old_data TEXT;
    v_new_data TEXT;
BEGIN
    /*  If this actually for real auditing (where you need to log EVERY action),
        then you would need to use something like dblink or plperl that could log outside the transaction,
        regardless of whether the transaction committed or rolled back.
    */ 
    /* This dance with casting the NEW and OLD values to a ROW is not necessary in pg 9.0+ */
    IF (TG_OP = 'UPDATE') THEN
        v_old_data := ROW(OLD.*);
        v_new_data := ROW(NEW.*);
        INSERT INTO public.auditoria (idempleado,tabla,accion,dataoriginal,datanueva,query) 
        VALUES (NEW.idempleado,TG_TABLE_NAME::TEXT,substring(TG_OP,1,1),v_old_data,v_new_data, current_query());
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        v_old_data := ROW(OLD.*);
        INSERT INTO public.auditoria (idempleado,tabla,accion,dataoriginal,query) 
        VALUES (OLD.idempleado,TG_TABLE_NAME::TEXT,substring(TG_OP,1,1),v_old_data, current_query());
        RETURN OLD;
    ELSIF (TG_OP = 'INSERT') THEN
        v_new_data := ROW(NEW.*);
        INSERT INTO public.auditoria (idempleado,tabla,accion,datanueva,query) 
        VALUES (NEW.idempleado,TG_TABLE_NAME::TEXT,substring(TG_OP,1,1),v_new_data, current_query());
        RETURN NEW;
    ELSE
        RAISE WARNING '[AUDIT.IF_MODIFIED_FUNC] - Other action occurred: %, at %',TG_OP,now();
        RETURN NULL;
    END IF;
 
EXCEPTION
    WHEN data_exception THEN
        RAISE WARNING '[AUDIT.IF_MODIFIED_FUNC] - UDF ERROR [DATA EXCEPTION] - SQLSTATE: %, SQLERRM: %',SQLSTATE,SQLERRM;
        RETURN NULL;
    WHEN unique_violation THEN
        RAISE WARNING '[AUDIT.IF_MODIFIED_FUNC] - UDF ERROR [UNIQUE] - SQLSTATE: %, SQLERRM: %',SQLSTATE,SQLERRM;
        RETURN NULL;
    WHEN OTHERS THEN
        RAISE WARNING '[AUDIT.IF_MODIFIED_FUNC] - UDF ERROR [OTHER] - SQLSTATE: %, SQLERRM: %',SQLSTATE,SQLERRM;
        RETURN NULL;
END;
$body$
LANGUAGE plpgsql
SECURITY DEFINER
SET search_path = public;

--
-- To add this trigger to a table, use:
-- CREATE TRIGGER tablename_audit
-- AFTER INSERT OR UPDATE OR DELETE ON tablename
-- FOR EACH ROW EXECUTE PROCEDURE audit.if_modified_func();
--
-- Tables:
-- comprobantepago
-- notacredito
-- proforma
-- tipoempleadoxseccion
-- tipoempleado
-- empleado
-- producto
-- almacen
-- area
-- rack
-- movimiento
-- lote
-- ruta
-- condicion
-- cliente
-- guiaremision
-- pedido
CREATE TRIGGER comprobrantepago_audit
AFTER INSERT OR UPDATE OR DELETE ON comprobantepago
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER notacredito_audit
AFTER INSERT OR UPDATE OR DELETE ON notacredito
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER proforma_audit
AFTER INSERT OR UPDATE OR DELETE ON proforma
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER tipoempleadoxseccion_audit
AFTER INSERT OR UPDATE OR DELETE ON tipoempleadoxseccion
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER tipoempleado_audit
AFTER INSERT OR UPDATE OR DELETE ON tipoempleado
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER empleado_audit
AFTER INSERT OR UPDATE OR DELETE ON empleado
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER producto_audit
AFTER INSERT OR UPDATE OR DELETE ON producto
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER almacen_audit
AFTER INSERT OR UPDATE OR DELETE ON almacen
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER area_audit
AFTER INSERT OR UPDATE OR DELETE ON area
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER rack_audit
AFTER INSERT OR UPDATE OR DELETE ON rack
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER movimiento_audit
AFTER INSERT OR UPDATE OR DELETE ON movimiento
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER lote_audit
AFTER INSERT OR UPDATE OR DELETE ON lote
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER ruta_audit
AFTER INSERT OR UPDATE OR DELETE ON ruta
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER condicion_audit
AFTER INSERT OR UPDATE OR DELETE ON condicion
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER cliente_audit
AFTER INSERT OR UPDATE OR DELETE ON cliente
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER guiaremision_audit
AFTER INSERT OR UPDATE OR DELETE ON guiaremision
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();

CREATE TRIGGER pedido_audit
AFTER INSERT OR UPDATE OR DELETE ON pedido
FOR EACH ROW EXECUTE PROCEDURE public.if_modified_func();