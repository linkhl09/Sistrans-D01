--los usuarios que realizaron al menos una compra de un determinado
--producto en un rango de fechas

select *
from( select factura from factura_producto
      where producto = ? ) a join (select *
from (( select numero , cliente from factura
where fecha BETWEEN ? AND ?)  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero;

--Para los distintos tipos de ordenamiento según los gustos del usuario, se pueden utilizar las siguientes consultas:

--------------------------------------------------------
-- por los datos del cliente
--------------------------------------------------------
-- nombre (orden ortografico, ascendente, descendente ):
select *
from( select factura from factura_producto
      where producto = '' ) a join (select *
from (( select numero , cliente from factura
where fecha BETWEEN '' AND '')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero
order by nombre;

-- puntos (mayor a menor , menor a mayor):
select *
from( select factura from factura_producto
      where producto = '' ) a join (select *
from (( select numero , cliente from factura
where fecha BETWEEN '' AND '')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero
order by puntos;

-- por fecha (mayor a menor , menor a mayor):
select *
from( select factura from factura_producto
      where producto = '' ) a join (select *
from (( select numero , cliente,fecha from factura
where fecha BETWEEN '' AND '')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero
order by fecha;

--y número de unidades compradas del producto(ascendente, descendente).
select *
from( select factura, cantidad from factura_producto
      where producto = '' ) a join (select *
from (( select numero , cliente from factura
where fecha BETWEEN '' AND '')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero
order by cantidad;
