--Poblando Tabla CATEGORIA.
INSERT INTO CATEGORIA values('Vegetales');
INSERT INTO CATEGORIA values('Frutas y verduras');
INSERT INTO CATEGORIA values('Cereales');
INSERT INTO CATEGORIA values('Lipidos');
INSERT INTO CATEGORIA values('Congelados');
INSERT INTO CATEGORIA values('Aseo');
INSERT INTO CATEGORIA values('Cuidado Personal');
INSERT INTO CATEGORIA values('Mascotas');
INSERT INTO CATEGORIA values('Bebidas');
INSERT INTO CATEGORIA values('Juguetes');
INSERT INTO CATEGORIA values('Ropa');

--Poblando Tabla TIPO.
--Generales
INSERT INTO TIPO values('Perecedero');
INSERT INTO TIPO values('No perecedero');
--Vegetales
INSERT INTO TIPO values('Raiz');
INSERT INTO TIPO values('Bulbo');
INSERT INTO TIPO values('Tallo');
INSERT INTO TIPO values('Hoja');
INSERT INTO TIPO values('Flor');

--Frutas y verduras
INSERT INTO TIPO values('Frutas de grano');
INSERT INTO TIPO values('Cítricos');
INSERT INTO TIPO values('Exóticas');
INSERT INTO TIPO values('Otras');

--Cereales
INSERT INTO TIPO values('Granos');
INSERT INTO TIPO values('Harina');
INSERT INTO TIPO values('Semóla');
INSERT INTO TIPO values('Gachas');
INSERT INTO TIPO values('Copos');
INSERT INTO TIPO values('Pasta');

--Lipidos
INSERT INTO TIPO values('Aceites');
INSERT INTO TIPO values('Margarinas');
INSERT INTO TIPO values('Embutidos');

--Congelados
INSERT INTO TIPO values('Helado');
INSERT INTO TIPO values('Fritos');
INSERT INTO TIPO values('Carne');
INSERT INTO TIPO values('Pescado');
INSERT INTO TIPO values('Pollo');
INSERT INTO TIPO values('Ready to eat');

--Aseo
INSERT INTO TIPO values('Limpieza de suelo');
INSERT INTO TIPO values('Con cloro');
INSERT INTO TIPO values('Muebles');
INSERT INTO TIPO values('Cocina');
INSERT INTO TIPO values('Baño');
INSERT INTO TIPO values('Varios');

--Cuidado Personal
INSERT INTO TIPO values('Bebes');
INSERT INTO TIPO values('Dental');

--Mascotas
INSERT INTO TIPO values('Alimentos');
INSERT INTO TIPO values('Perros');
INSERT INTO TIPO values('Gatos');
INSERT INTO TIPO values('Pajaros');

--Bebidas
INSERT INTO TIPO values('Gaseosas');
INSERT INTO TIPO values('Te');
INSERT INTO TIPO values('Energizantes');
INSERT INTO TIPO values('Bebidas Lacteas');
INSERT INTO TIPO values('Jugos');
INSERT INTO TIPO values('Jugos Naturales');
INSERT INTO TIPO values('Refrescos');
INSERT INTO TIPO values('Aguas Minerales');

--Juguetes
INSERT INTO TIPO values('Mesa');
INSERT INTO TIPO values('Exteriores');
INSERT INTO TIPO values('Interiores');
INSERT INTO TIPO values('Para armar');
INSERT INTO TIPO values('Muñecos');
INSERT INTO TIPO values('Electronicos');
INSERT INTO TIPO values('Disparos');

--Ropa
INSERT INTO TIPO values('Niños');
INSERT INTO TIPO values('Niñas');
INSERT INTO TIPO values('Hombre');
INSERT INTO TIPO values('Mujer');

--Poblando Tabla PRODUCTO.
--superAndes_sequence.NEXTVAL PARA USAR EL SECUENCIADOR DESDE DEVELOPER.
INSERT INTO PRODUCTO values('AAAAAAAAAAAA1', 'Zanahoria',                   'Delta',            4710, 'Por gramos',     9.4,    1, 500, 'G', 0.05,  'm3',   4.3,    100,    NULL,           'Vegetales', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA2', 'Espinaca',                    'Delta',            1290, 'Por gramos',     4.3,    1, 300, 'G', 0.05,  'm3',   3.5,    30,     NULL,           'Vegetales', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA3', 'Mora',                        'BlueBerry',        5340, 'Por gramos',     10.7,   1, 500, 'G', 0.06,  'm3',   4,      15,     NULL,           'Frutas y verduras', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA4', 'Naranja',                     'BlueBerry',        4170, 'Por Kilogramos', 1.3,    1, 3,   'KG', 0.35, 'm3',   3.5,    25,     NULL,           'Frutas y verduras', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA5', 'Arroz',                       'Diana',            9430, 'Premium',        3.77,   1, 2500,'G', 0.6,   'm3',   4.7,    70,     NULL,           'Cereales', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA6', 'Trigo',                       'Haz de oros',      1860, 'Paquete',        3.7,    1, 500, 'G', 0.1,   'm3',   4.7,    25,     '30/11/2018',   'Cereales', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA7', 'Aceite de oliva',             'Olivetto',         51000, 'Botella',       51,     1, 200, 'G', 1000,  'cm3',  4,      5,      NULL,           'Lipidos', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA8', 'Mantequilla',                 'La fina',          8010, 'Unidad',         16.02,  1, 500, 'G', 0.01,  'm3',   3.8,    20,     '30/11/2018',   'Lipidos', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAAA9', 'Carne de res molida',         'SIN MARCA',        8950, 'En bandeja',     17.9,   1, 500, 'G', 0.004, 'm3',   3.9,    5,      '30/11/2018',   'Congelados', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA10', 'Pizza',                       'Zenú',             10880, 'Unidad',        10880,  1, 250, 'G', 0.002, 'm3',   3,      5,      '30/11/2018',   'Congelados', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA11', 'Trapero',                     'Dekoratto',        19210, 'Unidad',        19210,  1, 250, 'G', NULL,  NULL,   4.1,    7,      NULL,           'Aseo', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA12', 'Quitagrasa Verde Pistola',    'Mr Musculo',       9070, 'CBL',            9070,   1, NULL,NULL, 500, 'cm3',   4,      30,     NULL,           'Aseo', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA13', 'Cepillo dental 360',          'Colgate',          21200, 'BTR',           10600,  2, NULL,NULL, NULL, NULL,   4.2,    20,     NULL,           'Cuidado Personal', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA14', 'Pañales',                     'Huggies',          14150, 'Paquete',       14150,  1, NULL,NULL, NULL, NULL,   4.6,    20,     NULL,           'Cuidado Personal', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA15', 'Croquetas',                   'Dog Chow',         13300, 'Unidad',        13.3,   1, 1000,'G', 1,     'm3',   4.8,    15,     '30/11/2018',   'Mascotas', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA16', 'Mix para aves',               'VITURA',           3120, 'Unidad',         10.4,   1, 300, 'G', NULL,  NULL,   3,      10,     NULL,           'Mascotas', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA17', 'Coca Cola Mega',              'Coca-Cola',        5170, 'Unidad',         2.1,    1, NULL,NULL, 2.5,  'L',    4,      70,     '30/11/2018',   'Bebidas', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA18', 'Jugo Hit pet',                'Postobon',         1800, 'Unidad',         3.6,    1, NULL,NULL, 500,  'ML',   3.7,    100,    '30/11/2018',   'Bebidas', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA19', 'Jenga',                       'Hasbro Gaming',    66200, 'Unidad',        66200,  1, NULL,NULL, NULL, NULL,   4,      4,      NULL,           'Juguetes', 0);               
INSERT INTO PRODUCTO values('AAAAAAAAAAA20', 'Nerf Dual N-Strike',          'Hasbro',           180000, 'Unidad',       18000,  1, NULL,NULL, NULL, NULL,   4,      3,      NULL,           'Juguetes', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA21', 'Buzo',                        'Vans',             65000, 'Unidad',        65000,  1, NULL,NULL, NULL, NULL,   4.7,    4,      NULL,           'Ropa', 0);
INSERT INTO PRODUCTO values('AAAAAAAAAAA22', 'Falda Nike Tenis',            'Nike',             175000, 'Unidad',       175000, 1, NULL,NULL, NULL, NULL,   4,      20,     NULL,           'Ropa', 0);
               
--Poblando Tabla TIPO_PRODUCTO.
INSERT INTO TIPO_PRODUCTO values('Raiz','AAAAAAAAAAAA1');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA1');

INSERT INTO TIPO_PRODUCTO values('Hoja','AAAAAAAAAAAA2');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA2');

INSERT INTO TIPO_PRODUCTO values('Otras','AAAAAAAAAAAA3');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA3');

INSERT INTO TIPO_PRODUCTO values('Cítricos','AAAAAAAAAAAA4');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA4');

INSERT INTO TIPO_PRODUCTO values('Granos','AAAAAAAAAAAA5');
INSERT INTO TIPO_PRODUCTO values('No perecedero','AAAAAAAAAAAA5');

INSERT INTO TIPO_PRODUCTO values('Harina','AAAAAAAAAAAA6');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA6');

INSERT INTO TIPO_PRODUCTO values('Aceites','AAAAAAAAAAAA7');
INSERT INTO TIPO_PRODUCTO values('No perecedero','AAAAAAAAAAAA7');

INSERT INTO TIPO_PRODUCTO values('Margarinas','AAAAAAAAAAAA8');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA8');

INSERT INTO TIPO_PRODUCTO values('Carne','AAAAAAAAAAAA9');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAAA9');
INSERT INTO TIPO_PRODUCTO values('Fritos','AAAAAAAAAAAA9');

INSERT INTO TIPO_PRODUCTO values('Carne','AAAAAAAAAAA10');
INSERT INTO TIPO_PRODUCTO values('Perecedero','AAAAAAAAAAA10');
INSERT INTO TIPO_PRODUCTO values('Ready to eat','AAAAAAAAAAA10');

INSERT INTO TIPO_PRODUCTO values('Limpieza de suelo','AAAAAAAAAAA11');

INSERT INTO TIPO_PRODUCTO values('Cocina','AAAAAAAAAAA12');

INSERT INTO TIPO_PRODUCTO values('Dental','AAAAAAAAAAA13');

INSERT INTO TIPO_PRODUCTO values('Bebes','AAAAAAAAAAA14');

INSERT INTO TIPO_PRODUCTO values('Perros','AAAAAAAAAAA15');
INSERT INTO TIPO_PRODUCTO values('Alimentos','AAAAAAAAAAA15');

INSERT INTO TIPO_PRODUCTO values('Pajaros','AAAAAAAAAAA16');

INSERT INTO TIPO_PRODUCTO values('Gaseosas','AAAAAAAAAAA17');

INSERT INTO TIPO_PRODUCTO values('Jugos','AAAAAAAAAAA18');
INSERT INTO TIPO_PRODUCTO values('Refrescos','AAAAAAAAAAA18');

INSERT INTO TIPO_PRODUCTO values('Mesa','AAAAAAAAAAA19');
INSERT INTO TIPO_PRODUCTO values('Interiores','AAAAAAAAAAA19');

INSERT INTO TIPO_PRODUCTO values('Exteriores','AAAAAAAAAAA20');
INSERT INTO TIPO_PRODUCTO values('Disparos','AAAAAAAAAAA20');

INSERT INTO TIPO_PRODUCTO values('Hombre','AAAAAAAAAAA21');

INSERT INTO TIPO_PRODUCTO values('Mujer','AAAAAAAAAAA22');
INSERT INTO TIPO_PRODUCTO values('Niña','AAAAAAAAAAA22');
commit;

--Poblando Tabla SUCURSAL.
INSERT INTO T values();

--Poblando Tabla SUCURSAL_PRODUCTO.
INSERT INTO T values();

--Poblando Tabla BODEGA.
INSERT INTO T values();

--Poblando Tabla ESTANTE.
INSERT INTO T values();

--Poblando Tabla PRODUCTOSENBODEGA.
INSERT INTO T values();

--Poblando Tabla PRODUCTOSENESTANTE.
INSERT INTO T values();

--Poblando Tabla PERSONANATURAL.
--Poblando Tabla EMPRESA.
--Poblando Tabla CLIENTE.
--Poblando Tabla CARRITOCOMPRAS.
--Poblando Tabla PRODUCTO_CARRITOCOMPRAS.
--Poblando Tabla FACTURA.
--Poblando Tabla FACTURA_PRODUCTO.
--Poblando Tabla PROVEEDOR.
--Poblando Tabla PROVEEDORES_PRODUCTO.
--Poblando Tabla ORDENPEDIDO.
--Poblando Tabla PRODUCTO_ORDENPEDIDO.
--Poblando Tabla PROM_DESCUENTO.
--Poblando Tabla PROM_SEGUNIDESCUENTO.
--Poblando Tabla PROM_PAGLLEVEUNID.

--Poblando Tabla PROM_PAGLLEVECANT.

--FIN DE POBLACIÓN DE LAS 26 TABLAS, COMMIT
Commit; 