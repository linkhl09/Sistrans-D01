--RFC5
-- -  MOSTRAR LAS COMPRAS HECHAS POR SUPERANDES A LOS PROVEEDORES
SELECT *
FROM ORDENPEDIDO
WHERE ESTADO = 'Entregado'
ORDER BY PROVEEDOR;