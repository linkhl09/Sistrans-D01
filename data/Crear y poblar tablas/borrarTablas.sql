DROP SEQUENCE superAndes_sequence;
DROP TABLE tipo_producto CASCADE CONSTRAINTS;
DROP TABLE categoria CASCADE CONSTRAINTS;
DROP TABLE tipo CASCADE CONSTRAINTS;
DROP TABLE sucursal_producto CASCADE CONSTRAINTS;
DROP TABLE producto CASCADE CONSTRAINTS;
DROP TABLE sucursal CASCADE CONSTRAINTS;
DROP TABLE productosenbodega CASCADE CONSTRAINTS;
DROP TABLE productosenestante CASCADE CONSTRAINTS;
DROP TABLE bodega CASCADE CONSTRAINTS;
DROP TABLE estante CASCADE CONSTRAINTS;
DROP TABLE cliente CASCADE CONSTRAINTS;
DROP TABLE personanatural CASCADE CONSTRAINTS;
DROP TABLE empresa CASCADE CONSTRAINTS;
DROP TABLE producto_carritocompras CASCADE CONSTRAINTS;
DROP TABLE carritocompras CASCADE CONSTRAINTS;
DROP TABLE factura_producto CASCADE CONSTRAINTS;
DROP TABLE factura CASCADE CONSTRAINTS;
DROP TABLE proveedores_producto CASCADE CONSTRAINTS;
DROP TABLE proveedor CASCADE CONSTRAINTS;
DROP TABLE producto_ordenpedido CASCADE CONSTRAINTS;
DROP TABLE ordenpedido CASCADE CONSTRAINTS;
DROP TABLE prom_descuento CASCADE CONSTRAINTS;
DROP TABLE prom_segunidescuento CASCADE CONSTRAINTS;
DROP TABLE prom_paglleveunid CASCADE CONSTRAINTS;
DROP TABLE prom_pagllevecant CASCADE CONSTRAINTS;

delete from CARRITOCOMPRAS;

commit;