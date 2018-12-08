-- MOSTRAR LAS VENTAS DE SUPERANDES A UN USUARIO DADO, EN UN RANGO DE FECHAS INDICADO
select * from factura
where cliente =? and fecha between ? and ?;