--Los clientes que realizan una compra por lo menos una vez al mes.
Select z.cliente, NumMesesCompra, productoCaro, productoCategorias 
from (
Select cliente, NumMesesCompra 
from (
    select cliente, count(distinct(month)) as NumMesesCompra 
    from (
        select cliente, EXTRACT(MONTH FROM fecha) as month 
        from factura 
        where EXTRACT(YEAR FROM FECHA) = 2018
    ) group by cliente
) where NumMesesCompra = 12)z,(SELECT a.cliente, productoCaro, productoCategorias from
(SELECT cliente, count(numero) as numFacturas from factura group by cliente)a, 
(SELECT cliente, count(distinct(factura))as numFacturasCaras, nombre as productoCaro FROM FACTURA, FACTURA_PRODUCTO, PRODUCTO WHERE PRODUCTO.PRECIOUNITARIO > 100000 group by cliente,nombre), 
(SELECT cliente, count(distinct(factura))as numFacturasCat, nombre as productoCategorias FROM FACTURA, FACTURA_PRODUCTO , PRODUCTO WHERE PRODUCTO.CATEGORIA ='Technology' OR PRODUCTO.CATEGORIA = 'Tools' group by cliente, nombre)
where numFacturas = numFacturasCaras OR numFacturas = numFacturasCat);