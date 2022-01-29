-- SINTAXIS:
--
-- CREATE [OR REPLACE] FUNCTION function_name(parameter_list)
-- LANGUAGE plpgsql
-- AS 
-- $$
-- DECLARE
-- -- variable declaration
-- BEGIN
-- -- function body
-- END
-- $$
-------------------------------------------------------------
-- Mostrar todos los clientes
CREATE OR REPLACE FUNCTION muestra_clientes()
RETURNS SETOF cliente
AS
$FUNC$
	SELECT * 
	FROM cliente;
$FUNC$
LANGUAGE sql;