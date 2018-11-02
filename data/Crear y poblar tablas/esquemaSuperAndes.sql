create sequence superAndes_sequence;

--Creación Tabla CATEGORIA y restricciónes
CREATE TABLE CATEGORIA
    (
     NOMBRE VARCHAR2(100 BYTE),
     CONSTRAINT CATEGORIA_PK PRIMARY KEY (NOMBRE)
    );

--Creación Tabla TIPO
CREATE TABLE TIPO
    (
     NOMBRE VARCHAR2(100 BYTE),
     CONSTRAINT TIPO_PK PRIMARY KEY (NOMBRE)
    );
    
--Creación Tabla PRODUCTO y restricciónes
CREATE TABLE PRODUCTO
    (CODIGOBARRAS VARCHAR2(13 BYTE) ,
     NOMBRE VARCHAR2(100 BYTE) NOT NULL,
     MARCA VARCHAR2(100 BYTE) NOT NULL,
     PRECIOUNITARIO NUMBER NOT NULL,
     PRESENTACION VARCHAR2(255 BYTE) NOT NULL ,
     PRECIOUNIDADMEDIDA NUMBER NOT NULL,
     CANTIDADPRESENTACION NUMBER NOT NULL,
     PESO NUMBER NOT NULL,
     UNIDADMEDIDAPESO VARCHAR2(10 BYTE) NOT NULL,
     VOLUMEN NUMBER NOT NULL,
     UNIDADMEDIDAVOLUMEN VARCHAR2(10 BYTE) NOT NULL,
     CALIDAD NUMBER NOT NULL,
     NIVELREORDEN NUMBER NOT NULL,
     FECHAVENCIMIENTO DATE, 
     CATEGORIA VARCHAR2(100 BYTE) NOT NULL,
     ESTAPROMOCION NUMBER,
     CONSTRAINT PRODUCTO_PK PRIMARY KEY(CODIGOBARRAS)
    );

ALTER TABLE PRODUCTO
    ADD CONSTRAINT CK_CodBarras
    CHECK (LENGTH(CODIGOBARRAS) = 13)
    ENABLE;

ALTER TABLE PRODUCTO
    ADD CONSTRAINT fk_categoria_producto
    FOREIGN KEY (CATEGORIA) REFERENCES CATEGORIA(NOMBRE)
    ENABLE;

--Creación Tabla TIPO_PRODUCTO
CREATE TABLE TIPO_PRODUCTO
    (
     NOMBRETIPO VARCHAR2(100 BYTE),
     CODIGOBARRASPRODUCTO VARCHAR2(100 BYTE),

     CONSTRAINT TIPO_PRODUCTO_PK PRIMARY KEY (NOMBRETIPO,CODIGOBARRASPRODUCTO)
    );

ALTER TABLE TIPO_PRODUCTO
    ADD CONSTRAINT fk_T_producto
    FOREIGN KEY (CODIGOBARRASPRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

ALTER TABLE TIPO_PRODUCTO
    ADD CONSTRAINT fk_tipo
    FOREIGN KEY (NOMBRETIPO) REFERENCES TIPO(NOMBRE)
    ENABLE;

--Creación Tabla SUCURSAL y restricciónes
CREATE TABLE SUCURSAL
    ( ID NUMBER ,
    DIRECCION VARCHAR2(255 BYTE) NOT NULL,
    CIUDAD VARCHAR2(150 BYTE) NOT NULL,
    NOMBRE VARCHAR2(200 BYTE) NOT NULL,
    SEGMENTACIONMERCADO VARCHAR2(200) NOT NULL,
    TAMANIO NUMBER NOT NULL,
    CONSTRAINT SUCURSAL_PK PRIMARY KEY (ID)
    );
ALTER TABLE SUCURSAL
    ADD CONSTRAINT nombreSucu_unico
    UNIQUE(NOMBRE)
    ENABLE;

--Creación Tabla SUCURSAL_PRODUCTO y restricciónes
CREATE TABLE SUCURSAL_PRODUCTO
    (IDSUCURSAL NUMBER,
    CODIGOPRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT SUCURSAL_PRODUCTO_PK PRIMARY KEY (CODIGOPRODUCTO, IDSUCURSAL)
    );
ALTER TABLE SUCURSAL_PRODUCTO
    ADD CONSTRAINT fk_sucursal_producto
    FOREIGN KEY (IDSUCURSAL) REFERENCES SUCURSAL(ID)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE SUCURSAL_PRODUCTO
    ADD CONSTRAINT fk_producto_sucursal
    FOREIGN KEY (CODIGOPRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ON DELETE CASCADE
    ENABLE;

--Creación Tabla BODEGA y restricciónes
CREATE TABLE BODEGA
    (ID NUMBER,
    CAPACIDADVOL NUMBER NOT NULL,
    CAPACIDADPESO NUMBER NOT NULL,
    TIPO VARCHAR2(100 BYTE) NOT NULL,
    IDSUCURSAL NUMBER NOT NULL,
    CONSTRAINT BODEGA_PK PRIMARY KEY (ID)
    );
ALTER TABLE BODEGA
    ADD CONSTRAINT fk_sucursal_bodega
    FOREIGN KEY (IDSUCURSAL) REFERENCES SUCURSAL(ID)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE BODEGA
    ADD CONSTRAINT fk_categoria_bodega
    FOREIGN KEY (TIPO) REFERENCES CATEGORIA(NOMBRE)
    ENABLE;

--Creación Tabla ESTANTE y restricciónes
CREATE TABLE ESTANTE
    (ID NUMBER,
    CAPACIDADVOL NUMBER NOT NULL,
    CAPACIDADPESO NUMBER NOT NULL,
    TIPO VARCHAR2(100 BYTE) NOT NULL,
    IDSUCURSAL NUMBER NOT NULL,
    CONSTRAINT ESTANTE_PK PRIMARY KEY (ID)
    );
ALTER TABLE ESTANTE
    ADD CONSTRAINT fk_sucursal_estante
    FOREIGN KEY (IDSUCURSAL) REFERENCES SUCURSAL(ID)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE ESTANTE
    ADD CONSTRAINT fk_categoria_estante
    FOREIGN KEY (TIPO) REFERENCES CATEGORIA(NOMBRE)
    ENABLE;

--Creación Tabla PRODUCTOSENBODEGA y restricciónes
CREATE TABLE PRODUCTOSENBODEGA
    (IDBODEGA NUMBER,
    CANTIDAD NUMBER(*) NOT NULL,
    NIVELABASTECIMIENTO NUMBER(*) NOT NULL,
    CODIGOBARRASPRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT PRODUCTOSENBODEGA_PK PRIMARY KEY (IDBODEGA, CODIGOBARRASPRODUCTO)
    );
ALTER TABLE PRODUCTOSENBODEGA
    ADD CONSTRAINT fk_bodega
    FOREIGN KEY (IDBODEGA) REFERENCES BODEGA(ID)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE PRODUCTOSENBODEGA
    ADD CONSTRAINT fk_producto_bodega
    FOREIGN KEY (CODIGOBARRASPRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PRODUCTOSENESTANTE y restricciónes
CREATE TABLE PRODUCTOSENESTANTE
    (IDESTANTE NUMBER,
    CANTIDAD NUMBER(*) NOT NULL,
    CODIGOBARRASPRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT PRODUCTOSENESTANTE_PK PRIMARY KEY (IDESTANTE, CODIGOBARRASPRODUCTO)
    );
ALTER TABLE PRODUCTOSENESTANTE
    ADD CONSTRAINT fk_estante
    FOREIGN KEY (IDESTANTE) REFERENCES ESTANTE(ID)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE PRODUCTOSENESTANTE
    ADD CONSTRAINT fk_producto_estante 
    FOREIGN KEY (CODIGOBARRASPRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PERSONANATURAL y restricciónes
CREATE TABLE PERSONANATURAL
    (DOCUMENTO VARCHAR2(100 BYTE),
    TIPODOCUMENTO VARCHAR2(100 BYTE),
    CONSTRAINT PERSONANATURAL_PK PRIMARY KEY (DOCUMENTO)
    );

--Creación Tabla EMPRESA y restricciónes
CREATE TABLE EMPRESA
    (NIT VARCHAR2(100 BYTE),
    DIRECCION VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT EMPRESA_PK PRIMARY KEY(NIT)
    );
ALTER TABLE EMPRESA
    ADD CONSTRAINT direccion_unica
    UNIQUE (DIRECCION)
    ENABLE;

--Creación Tabla CLIENTE y restricciónes
CREATE TABLE CLIENTE
    (CORREOELECTRONICO VARCHAR2(255 BYTE),
    NOMBRE VARCHAR2(200 BYTE) NOT NULL,
    PUNTOS NUMBER(*) NOT NULL,
    EMPRESA VARCHAR2(100 BYTE),
    DOCUMENTOPN VARCHAR2(100 BYTE),
    CONSTRAINT CLIENTE_PK PRIMARY KEY (CORREOELECTRONICO)
    );
ALTER TABLE CLIENTE
    ADD CONSTRAINT doc_unico
    UNIQUE (DOCUMENTOPN)
    ENABLE;
ALTER TABLE CLIENTE
    ADD CONSTRAINT fk_personaNatural
    FOREIGN KEY (DOCUMENTOPN) REFERENCES PERSONANATURAL(DOCUMENTO)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE CLIENTE
    ADD CONSTRAINT empresa_unica
    UNIQUE (EMPRESA)
    ENABLE;
ALTER TABLE CLIENTE
    ADD CONSTRAINT fk_empresa
    FOREIGN KEY (EMPRESA) REFERENCES EMPRESA(NIT)
    ON DELETE CASCADE
    ENABLE;

--Creación Tabla CARRITOCOMPRAS y restricciónes
CREATE TABLE CARRITOCOMPRAS
    (ID NUMBER,
    CLIENTE VARCHAR2(255 BYTE),
    SUCURSAL NUMBER,
    CONSTRAINT CARRITOCOMPRAS_PK PRIMARY KEY (ID)
    );
ALTER TABLE CARRITOCOMPRAS
    ADD CONSTRAINT fk_cliente_carrito
    FOREIGN KEY (CLIENTE) REFERENCES CLIENTE(CORREOELECTRONICO)
    ON DELETE CASCADE
    ENABLE;
ALTER TABLE CARRITOCOMPRAS
    ADD CONSTRAINT fk_sucursal_carrito
    FOREIGN KEY (SUCURSAL) REFERENCES SUCURSAL(ID)
    ON DELETE CASCADE
    ENABLE;

--Creación Tabla PRODUCTO_CARRITOCOMPRAS y restricciónes
CREATE TABLE PRODUCTO_CARRITOCOMPRAS
    (CARRITO NUMBER,
    CANTIDAD NUMBER,
    CODIGOBARRASPRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT PRODUCTO_CARRITOCOMPRAS_PK PRIMARY KEY (CARRITO, CODIGOBARRASPRODUCTO)
    );

ALTER TABLE PRODUCTO_CARRITOCOMPRAS
    ADD CONSTRAINT fk_producto_productocarrito
    FOREIGN KEY (CODIGOBARRASPRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ON DELETE CASCADE
    ENABLE;

ALTER TABLE PRODUCTO_CARRITOCOMPRAS
    ADD CONSTRAINT fk_carrito_productocarrito
    FOREIGN KEY (CARRITO) REFERENCES CARRITOCOMPRAS(ID)
    ON  DELETE CASCADE
    ENABLE;

--Creación Tabla FACTURA y restricciónes
CREATE TABLE FACTURA
    (NUMERO NUMBER,
    DIRECCION VARCHAR2(255 BYTE) NOT NULL,
    FECHA DATE NOT NULL,
    NOMBRECAJERO VARCHAR2(200 BYTE) NOT NULL,
    VALORTOTAL NUMBER NOT NULL,
    PAGOEXITOSO NUMBER(1) NOT NULL,
    PUNTOSCOMPRA NUMBER(*) NOT NULL,
    CLIENTE VARCHAR2(255 BYTE),
    IDSUCURSAL NUMBER,
    CONSTRAINT FACTURA_PK PRIMARY KEY (NUMERO)
    );
ALTER TABLE FACTURA
    ADD CONSTRAINT rango_exito
    CHECK (PAGOEXITOSO BETWEEN 0 AND 1)
    ENABLE;
ALTER TABLE FACTURA
    ADD CONSTRAINT fk_cliente
    FOREIGN KEY (CLIENTE) REFERENCES CLIENTE(CORREOELECTRONICO)
    ENABLE;
ALTER TABLE FACTURA
    ADD CONSTRAINT fk_sucursal
    FOREIGN KEY (IDSUCURSAL) REFERENCES SUCURSAL(ID)
    ENABLE;

--Creación Tabla FACTURA_PRODUCTO y restricciónes
CREATE TABLE FACTURA_PRODUCTO
    (FACTURA NUMBER,
    CANTIDAD NUMBER(*) NOT NULL,
    PRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT FACTURA_PRODUCTO_PK PRIMARY KEY (FACTURA, PRODUCTO)
    );
ALTER TABLE FACTURA_PRODUCTO
    ADD CONSTRAINT fk_factura_producto
    FOREIGN KEY (FACTURA) REFERENCES FACTURA(NUMERO)
    ENABLE;
ALTER TABLE FACTURA_PRODUCTO
    ADD CONSTRAINT fk_producto_factura
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PROVEEDOR y restricciónes
CREATE TABLE PROVEEDOR
    (NIT VARCHAR2(100 BYTE),
    NOMBRE VARCHAR2(100) NOT NULL,
    CALIFICACIONCALIDAD NUMBER(2,1) NOT NULL,
    CONSTRAINT PROVEEDOR_PK PRIMARY KEY(NIT)
    );
ALTER TABLE PROVEEDOR
    ADD CONSTRAINT nombre_unico
    UNIQUE (NOMBRE)
    ENABLE;
ALTER TABLE PROVEEDOR
    ADD CONSTRAINT calificacion_valida
    CHECK (CALIFICACIONCALIDAD >= 0 AND CALIFICACIONCALIDAD <=5 )
    ENABLE;

--Creación Tabla PROVEEDORES_PRODUCTO y restricciónes
CREATE TABLE PROVEEDORES_PRODUCTO
    (PROVEEDOR VARCHAR2(100 BYTE),
    PRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT PROVEEDORES_PRODUCTO_PK PRIMARY KEY (PROVEEDOR, PRODUCTO)
    );
ALTER TABLE PROVEEDORES_PRODUCTO
    ADD CONSTRAINT fk_proveedor_producto
    FOREIGN KEY (PROVEEDOR) REFERENCES PROVEEDOR(NIT)
    ENABLE;
ALTER TABLE PROVEEDORES_PRODUCTO
    ADD CONSTRAINT fk_producto_proveedor
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla ORDENPEDIDO y restricciónes
CREATE TABLE ORDENPEDIDO
    (ID NUMBER,
    FECHAENTREGA DATE,
    FECHAESPERADAENTREGA DATE NOT NULL,
    CALIFICACIONPROVEEDOR NUMBER(1,2) NOT NULL,
    ESTADO VARCHAR2(255 BYTE) NOT NULL,
    PROVEEDOR VARCHAR2(100 BYTE) NOT NULL,
    IDSUCURSAL NUMBER,
    CONSTRAINT ORDENPEDIDO_PK PRIMARY KEY (ID)
    );
ALTER TABLE ORDENPEDIDO
    ADD CONSTRAINT calificacion_orden_valida
    CHECK (CALIFICACIONPROVEEDOR  >= 0 AND CALIFICACIONPROVEEDOR <=5)
    ENABLE;
ALTER TABLE ORDENPEDIDO
    ADD CONSTRAINT fk_sucursal_orden
    FOREIGN KEY (IDSUCURSAL) REFERENCES SUCURSAL(ID)
    ENABLE;
ALTER TABLE ORDENPEDIDO
    ADD CONSTRAINT fk_proveedor
    FOREIGN KEY (PROVEEDOR) REFERENCES PROVEEDOR(NIT)
    ENABLE;
       
--Creación Tabla PRODUCTO_ORDENPEDIDO y restricciónes
    CREATE TABLE PRODUCTO_ORDENPEDIDO
    (PEDIDO NUMBER,
    CANTIDAD NUMBER(*) NOT NULL,
    CALIDAD NUMBER(1,2) NOT NULL,
    FECHAAGREGADO DATE NOT NULL,
    PRODUCTO VARCHAR2(13 BYTE),
    CONSTRAINT PRODUCTO_ORDENPEDIDO_PK PRIMARY KEY (PEDIDO, PRODUCTO)
    );
ALTER TABLE PRODUCTO_ORDENPEDIDO
    ADD CONSTRAINT fk_pedido
    FOREIGN KEY (PEDIDO) REFERENCES ORDENPEDIDO(ID)
    ENABLE;
ALTER TABLE PRODUCTO_ORDENPEDIDO
    ADD CONSTRAINT fk_producto_orden
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PROM_DESCUENTO y restricciónes
CREATE TABLE PROM_DESCUENTO
    (ID NUMBER,
    DESCRIPCION VARCHAR2(250 BYTE) NOT NULL,
    UNIDADESDISPONIBLES NUMBER(*) NOT NULL,
    UNIDADESVENDIDAS NUMBER(*) NOT NULL,
    FECHAINICIO DATE NOT NULL,
    FECHAFIN DATE NOT NULL, 
    PRODUCTO VARCHAR2(13 BYTE) NOT NULL,
    DESCUENTO NUMBER NOT NULL,    
    CONSTRAINT PROMOCION_PK PRIMARY KEY (ID)
    );

ALTER TABLE PROM_DESCUENTO
    ADD CONSTRAINT fk_producto_promdescuento
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PROM_SEGUNIDESCUENTO y restricciónes
CREATE TABLE PROM_SEGUNIDESCUENTO
    (ID NUMBER,
    DESCRIPCION VARCHAR2(250 BYTE) NOT NULL,
    UNIDADESDISPONIBLES NUMBER(*) NOT NULL,
    UNIDADESVENDIDAS NUMBER(*) NOT NULL,
    FECHAINICIO DATE NOT NULL,
    FECHAFIN DATE NOT NULL, 
    PRODUCTO VARCHAR2(13 BYTE) NOT NULL,
    DESCUENTO NUMBER NOT NULL,    
    CONSTRAINT PROMSEGUNIDESCUENTO_PK PRIMARY KEY (ID)
    );

ALTER TABLE PROM_SEGUNIDESCUENTO
    ADD CONSTRAINT fk_producto_promseguni
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PROM_PAGLLEVEUNID y restricciónes
CREATE TABLE PROM_PAGLLEVEUNID
    (ID NUMBER,
    DESCRIPCION VARCHAR2(250 BYTE) NOT NULL,
    UNIDADESDISPONIBLES NUMBER(*) NOT NULL,
    UNIDADESVENDIDAS NUMBER(*) NOT NULL,
    FECHAINICIO DATE NOT NULL,
    FECHAFIN DATE NOT NULL, 
    PRODUCTO VARCHAR2(13 BYTE) NOT NULL,
    PAGUE NUMBER NOT NULL,
    LLEVE NUMBER NOT NULL,
    CONSTRAINT PROPAGLLEVEUNI_PK PRIMARY KEY (ID)
    );

ALTER TABLE PROM_PAGLLEVEUNID 
    ADD CONSTRAINT fk_producto_prompagllevuni
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--Creación Tabla PROM_PAGLLEVECANT y restricciónes
CREATE TABLE PROM_PAGLLEVECANT
    (ID NUMBER,
    DESCRIPCION VARCHAR2(250 BYTE) NOT NULL,
    UNIDADESDISPONIBLES NUMBER(*) NOT NULL,
    UNIDADESVENDIDAS NUMBER(*) NOT NULL,
    FECHAINICIO DATE NOT NULL,
    FECHAFIN DATE NOT NULL, 
    PRODUCTO VARCHAR2(13 BYTE) NOT NULL,
    PAGUE NUMBER NOT NULL,
    LLEVE NUMBER NOT NULL,
    CONSTRAINT PROMPAGLLEVECANT_PK PRIMARY KEY (ID)
    );

ALTER TABLE PROM_PAGLLEVECANT 
    ADD CONSTRAINT fk_producto_prompagllevcant
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTO(CODIGOBARRAS)
    ENABLE;

--FIN DE CREACIÓN DE LAS 26 TABLAS, COMMIT
COMMIT; 