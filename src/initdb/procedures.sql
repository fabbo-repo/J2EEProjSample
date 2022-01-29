-- SINTAXIS:
--
-- CREATE [OR REPLACE] PROCEDURE procedure_name(parameter_list)
-- LANGUAGE plpgsql
-- AS 
-- $BODY$
-- DECLARE
-- -- variable declaration
-- BEGIN
-- -- stored procedure body
-- END
-- $BODY$
-------------------------------------------------------------
-- NOTA:
-- 
-- Los procedures en postgresql no estan implementados para 
-- devolver datos
-------------------------------------------------------------
CREATE OR REPLACE PROCEDURE update_prod(n_precio int, n_art varchar(20))
LANGUAGE plpgsql
AS 
$BODY$
BEGIN
    UPDATE productos
    SET precio=n_precio
    WHERE nombrearticulo=n_art; 
END
$BODY$
