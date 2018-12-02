Select cliente, NumMesesCompra from (select cliente, count(distinct(month)) as NumMesesCompra from (select cliente, EXTRACT(MONTH FROM fecha) as month from factura where EXTRACT(YEAR FROM FECHA) = 2018) group by cliente)  where NumMesesCompra = 12;

select cliente, ;
(select cliente, count(numero) as numFacturas from factura group by cliente);

(SELECT factura FROM FACTURA_PRODUCTO , PRODUCTO WHERE PRODUCTO.CATEGORIA ='Technology' OR PRODUCTO.CATEGORIA = 'Tools');
 
SELECT * FROM FACTURA_PRODUCTO , PRODUCTO WHERE PRODUCTO.PRECIOUNITARIO > 100000;
