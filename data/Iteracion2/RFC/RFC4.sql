--RFC4
--muestra los productos q cumplen cierta caracterstica.

--con un precio en un rango dado.
SELECT *
FROM PRODUCTOS
WHERE PRECIO BETWEEN ? AND ?;

--productos ofrecidos por un proveedor.
SELECT *
FROM PROVEEDORES_PRODUCTO
WHERE PROVEEDOR = '?';

--productos con fecha de vencimiento despues de cierta fecha.
SELECT *
FROM PRODUCTO
WHERE FECHAVENCIMIENTO> ?;

--productos con un peso en un rango dado.
SELECT *
FROM PRODUCTO
WHERE PRECIOUNITARIO BETWEEN ? AND ?;

--productos con un volumen en un rango dado.
SELECT *
FROM PRODUCTO
WHERE VOLUMEN BETWEEN ? AND ?;

--productos con un tipo dado.
SELECT *
FROM PRODUCTO
WHERE TIPO = ?;

--productos con una categoria dada.
SELECT *
FROM PRODUCTO
WHERE CATEGORIA = ?;

--productos ofrecidos por cierta sucursal.
SELECT *
FROM SUCURSAL_PRODUCTO
WHERE PRODUCTO= ?;

--productos ofrecidos en cierta ciudad.
SELECT ID, CIUDAD, CODIGOPRODUCTO
FROM (SELECT ID, CIUDAD
      FROM SUCURSAL
      WHERE CIUDAD=?)A JOIN (SELECT *
                             FROM SUCURSAL_PRODUCTO) B ON A.ID= B.IDSUCURSAL;