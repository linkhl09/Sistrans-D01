
Select * from proveedor where nit = '830047819';
select * from proveedores_producto;
select * from sucursal_producto;
select * from cliente;
select * from empresa;
select * from personaNatural;
select * from producto;
select * from sucursal_producto;
select * from sucursal;
select * from bodega;
select * from estante;

select * from ordenPedido;
select * from PRODUCTO_ORDENPEDIDO;








select * from ordenPedido Where proveedor = '830047819' AND estado = 'En Espera' AND idSucursal = 1;