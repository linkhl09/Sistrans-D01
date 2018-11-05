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
INSERT INTO PRODUCTO values('AAAAAAAAAAA22', 'Falda Nike Tenis',            'Nike',             175000, 'Unidad',       175000, 1, NULL,NULL, NULL, NULL,   4,      15,     NULL,           'Ropa', 0);
               
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


--Poblando Tabla SUCURSAL.
INSERT INTO SUCURSAL values (superAndes_sequence.NEXTVAL, 'Calle 35S #31-42', 'Medallo', 'Sucursal 1', 'Ni idea que es esto', 100);
INSERT INTO SUCURSAL values (superAndes_sequence.NEXTVAL, 'Cra. 45 #32-15', 'Bogotá', 'Sucursal 2', 'Ni idea que es esto', 1000);
INSERT INTO SUCURSAL values (superAndes_sequence.NEXTVAL, 'Calle 91 #11-11', 'Bogotá', 'Sucursal 3', 'Ni idea que es esto', 500);
INSERT INTO SUCURSAL values (superAndes_sequence.NEXTVAL, 'Cra. 1 #18a-12', 'Bogotá', 'Sucursal 4', 'Ni idea que es esto', 750);


--Poblando Tabla SUCURSAL_PRODUCTO. AAAAAAAAAAAA1
INSERT INTO SUCURSAL_PRODUCTO values('1','AAAAAAAAAAAA1');
INSERT INTO SUCURSAL_PRODUCTO values('1','AAAAAAAAAAAA3');
INSERT INTO SUCURSAL_PRODUCTO values('1','AAAAAAAAAAAA8');
INSERT INTO SUCURSAL_PRODUCTO values('1','AAAAAAAAAAA11');
INSERT INTO SUCURSAL_PRODUCTO values('1','AAAAAAAAAAA12');

INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA1');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA2');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA3');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA4');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA5');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA6');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA7');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA8');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAAA9');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA10');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA11');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA12');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA13');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA14');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA15');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA16');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA17');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA18');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA19');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA20');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA21');
INSERT INTO SUCURSAL_PRODUCTO values('2','AAAAAAAAAAA22');

INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAAA3');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAAA5');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAAA6');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAAA9');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAA13');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAA16');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAA17');
INSERT INTO SUCURSAL_PRODUCTO values('3','AAAAAAAAAAA18');

INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA1');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA2');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA3');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA4');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA5');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA6');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA7');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA8');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAAA9');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAA10');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAA17');
INSERT INTO SUCURSAL_PRODUCTO values('4','AAAAAAAAAAA18');


--Poblando Tabla BODEGA.
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Vegetales',            1);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Frutas y verduras',    1);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Lipidos',              1);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Aseo',                 1); 

INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Vegetales',            2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Frutas y verduras',    2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Cereales',             2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Lipidos',              2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Congelados',           2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Aseo',                 2);    
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Cuidado Personal',     2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Mascotas',             2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Bebidas',              2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Juguetes',             2);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Ropa',                 2);

INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Vegetales',            3);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Cereales',             3);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Congelados',           3);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Cuidado Personal',     3);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Mascotas',             3);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Bebidas',              3);

INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Vegetales',            4);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Frutas y verduras',    4);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Cereales',             4);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Lipidos',              4);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Congelados',           4);
INSERT INTO BODEGA values(superAndes_sequence.NEXTVAL, 1000000, 10000, 'Bebidas',              4);


--Poblando Tabla ESTANTE.
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Vegetales',            1);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Frutas y verduras',    1);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Lipidos',              1);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100, 1000, 'Aseo',                 1); 

INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Vegetales',            2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Frutas y verduras',    2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Cereales',             2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Lipidos',              2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Congelados',           2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Aseo',                 2);    
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Cuidado Personal',     2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Mascotas',             2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Bebidas',              2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Juguetes',             2);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Ropa',                 2);

INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Vegetales',            3);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Cereales',             3);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Congelados',           3);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Cuidado Personal',     3);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Mascotas',             3);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Bebidas',              3);

INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Vegetales',            4);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Frutas y verduras',    4);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Cereales',             4);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Lipidos',              4);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Congelados',           4);
INSERT INTO ESTANTE values(superAndes_sequence.NEXTVAL, 100000, 1000, 'Bebidas',              4);

--Poblando Tabla PRODUCTOSENBODEGA.
INSERT INTO PRODUCTOSENBODEGA values ( 5,   100,    50,     'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENBODEGA values ( 6,   75,     50,     'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENBODEGA values ( 7,   150,    100,    'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENBODEGA values ( 8,   100,    50,     'AAAAAAAAAAA11' );
INSERT INTO PRODUCTOSENBODEGA values ( 8,   300,    200,    'AAAAAAAAAAA12' );

INSERT INTO PRODUCTOSENBODEGA values ( 9,   100,    50 ,    'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENBODEGA values ( 9,   75,     50 ,    'AAAAAAAAAAAA2' );
INSERT INTO PRODUCTOSENBODEGA values ( 10,  75,     50 ,    'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENBODEGA values ( 10,  75,     50 ,    'AAAAAAAAAAAA4' );
INSERT INTO PRODUCTOSENBODEGA values ( 11,  150,    50 ,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENBODEGA values ( 11,  100,    50 ,    'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENBODEGA values ( 12,  100,    75 ,    'AAAAAAAAAAAA7' );
INSERT INTO PRODUCTOSENBODEGA values ( 12,  200,    100 ,   'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENBODEGA values ( 13,  50,     25 ,    'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENBODEGA values ( 13,  50,     25 ,    'AAAAAAAAAAA10' );
INSERT INTO PRODUCTOSENBODEGA values ( 14,  75,     20 ,    'AAAAAAAAAAA11' );
INSERT INTO PRODUCTOSENBODEGA values ( 14,  300,    200 ,   'AAAAAAAAAAA12' );
INSERT INTO PRODUCTOSENBODEGA values ( 15,  200,    100 ,   'AAAAAAAAAAA13' );
INSERT INTO PRODUCTOSENBODEGA values ( 15,  200,    100 ,   'AAAAAAAAAAA14' );
INSERT INTO PRODUCTOSENBODEGA values ( 16,  100,    50 ,    'AAAAAAAAAAA15' );
INSERT INTO PRODUCTOSENBODEGA values ( 16,  50,     20 ,    'AAAAAAAAAAA16' );
INSERT INTO PRODUCTOSENBODEGA values ( 17,  200,    100 ,   'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENBODEGA values ( 17,  300,    100 ,   'AAAAAAAAAAA18' );
INSERT INTO PRODUCTOSENBODEGA values ( 18,  10,     5 ,     'AAAAAAAAAAA19' );
INSERT INTO PRODUCTOSENBODEGA values ( 18,  10,     5 ,     'AAAAAAAAAAA20' );
INSERT INTO PRODUCTOSENBODEGA values ( 19,  50,     8 ,     'AAAAAAAAAAA21' );
INSERT INTO PRODUCTOSENBODEGA values ( 19,  75,     30 ,    'AAAAAAAAAAA22' );

INSERT INTO PRODUCTOSENBODEGA values ( 20,  75,     50 ,    'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENBODEGA values ( 21,  150,    50 ,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENBODEGA values ( 21,  100,    50 ,    'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENBODEGA values ( 22,  50,     25 ,    'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENBODEGA values ( 23,  200,    100 ,   'AAAAAAAAAAA13');
INSERT INTO PRODUCTOSENBODEGA values ( 24,  50,     20 ,    'AAAAAAAAAAA16' );
INSERT INTO PRODUCTOSENBODEGA values ( 25,  200,    100 ,   'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENBODEGA values ( 25,  300,    100 ,   'AAAAAAAAAAA18' );

INSERT INTO PRODUCTOSENBODEGA values ( 26,  100,    50,     'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENBODEGA values ( 26,  75,     50 ,    'AAAAAAAAAAAA2' );
INSERT INTO PRODUCTOSENBODEGA values ( 27,  75,     50 ,    'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENBODEGA values ( 27,  75,     50 ,    'AAAAAAAAAAAA4' );
INSERT INTO PRODUCTOSENBODEGA values ( 28,  150,    50 ,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENBODEGA values ( 28,  100,    50 ,    'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENBODEGA values ( 29,  100,    75 ,    'AAAAAAAAAAAA7' );
INSERT INTO PRODUCTOSENBODEGA values ( 29,  200,    100 ,   'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENBODEGA values ( 30,  50,     25 ,    'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENBODEGA values ( 30,  50,     25 ,    'AAAAAAAAAAA10' );
INSERT INTO PRODUCTOSENBODEGA values ( 31,  200,    100 ,   'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENBODEGA values ( 31,  300,    100 ,   'AAAAAAAAAAA18' );


--Poblando Tabla PRODUCTOSENESTANTE.
INSERT INTO PRODUCTOSENESTANTE values ( 32, 150,    'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENESTANTE values ( 33, 75,     'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENESTANTE values ( 34, 100,    'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENESTANTE values ( 35, 20,     'AAAAAAAAAAA11' );
INSERT INTO PRODUCTOSENESTANTE values ( 35, 40,     'AAAAAAAAAAA12' );

INSERT INTO PRODUCTOSENESTANTE values ( 36, 150,    'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENESTANTE values ( 36, 70,     'AAAAAAAAAAAA2' );
INSERT INTO PRODUCTOSENESTANTE values ( 37, 75,     'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENESTANTE values ( 37, 75,     'AAAAAAAAAAAA4' );
INSERT INTO PRODUCTOSENESTANTE values ( 38, 100,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENESTANTE values ( 38, 50,     'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENESTANTE values ( 39, 75,     'AAAAAAAAAAAA7' );
INSERT INTO PRODUCTOSENESTANTE values ( 39, 100,    'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENESTANTE values ( 40, 25,     'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENESTANTE values ( 40, 30,     'AAAAAAAAAAA10' );
INSERT INTO PRODUCTOSENESTANTE values ( 41, 20,     'AAAAAAAAAAA11' );
INSERT INTO PRODUCTOSENESTANTE values ( 41, 50,     'AAAAAAAAAAA12' );
INSERT INTO PRODUCTOSENESTANTE values ( 42, 40,     'AAAAAAAAAAA13' );
INSERT INTO PRODUCTOSENESTANTE values ( 42, 35,     'AAAAAAAAAAA14' );
INSERT INTO PRODUCTOSENESTANTE values ( 43, 25,     'AAAAAAAAAAA15' );
INSERT INTO PRODUCTOSENESTANTE values ( 43, 30,     'AAAAAAAAAAA16' );
INSERT INTO PRODUCTOSENESTANTE values ( 44, 50,     'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENESTANTE values ( 44, 100,    'AAAAAAAAAAA18' );
INSERT INTO PRODUCTOSENESTANTE values ( 45, 10,     'AAAAAAAAAAA19' );
INSERT INTO PRODUCTOSENESTANTE values ( 45, 10,     'AAAAAAAAAAA20' );
INSERT INTO PRODUCTOSENESTANTE values ( 46, 10,     'AAAAAAAAAAA21' );
INSERT INTO PRODUCTOSENESTANTE values ( 46, 10,     'AAAAAAAAAAA22' );

INSERT INTO PRODUCTOSENESTANTE values ( 47, 75,     'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENESTANTE values ( 48, 100,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENESTANTE values ( 48, 50,     'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENESTANTE values ( 49, 25,     'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENESTANTE values ( 50, 40,     'AAAAAAAAAAA13' );
INSERT INTO PRODUCTOSENESTANTE values ( 51, 30,     'AAAAAAAAAAA16' );
INSERT INTO PRODUCTOSENESTANTE values ( 52, 50,     'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENESTANTE values ( 52, 100,    'AAAAAAAAAAA18' );

INSERT INTO PRODUCTOSENESTANTE values ( 53, 150,    'AAAAAAAAAAAA1' );
INSERT INTO PRODUCTOSENESTANTE values ( 53, 70,     'AAAAAAAAAAAA2' );
INSERT INTO PRODUCTOSENESTANTE values ( 54, 75,     'AAAAAAAAAAAA3' );
INSERT INTO PRODUCTOSENESTANTE values ( 54, 75,     'AAAAAAAAAAAA4' );
INSERT INTO PRODUCTOSENESTANTE values ( 55, 100,    'AAAAAAAAAAAA5' );
INSERT INTO PRODUCTOSENESTANTE values ( 55, 50,     'AAAAAAAAAAAA6' );
INSERT INTO PRODUCTOSENESTANTE values ( 56, 75,     'AAAAAAAAAAAA7' );
INSERT INTO PRODUCTOSENESTANTE values ( 56, 100,    'AAAAAAAAAAAA8' );
INSERT INTO PRODUCTOSENESTANTE values ( 57, 25,     'AAAAAAAAAAAA9' );
INSERT INTO PRODUCTOSENESTANTE values ( 57, 30,     'AAAAAAAAAAA10' );
INSERT INTO PRODUCTOSENESTANTE values ( 58, 50,     'AAAAAAAAAAA17' );
INSERT INTO PRODUCTOSENESTANTE values ( 58, 100,    'AAAAAAAAAAA18' );

--Poblando Tabla PERSONANATURAL.
INSERT INTO PERSONANATURAL values('1007784099','CC');
INSERT INTO PERSONANATURAL values('1098825442','CC');
INSERT INTO PERSONANATURAL values('1010247329','CC');
INSERT INTO PERSONANATURAL values('99083007504','TI');
INSERT INTO PERSONANATURAL values('20001017532','TI');
INSERT INTO PERSONANATURAL values('1233906026','CC');
INSERT INTO PERSONANATURAL values('11223445566','TI');
INSERT INTO PERSONANATURAL values('1000234423','CC');
INSERT INTO PERSONANATURAL values('1000320320','CC');
INSERT INTO PERSONANATURAL values('99040202095','TI');

--Poblando Tabla EMPRESA.
INSERT INTO EMPRESA values('860005396','Calle 93 #11-11');--PHILIPS
INSERT INTO EMPRESA values('860025900','KM. 3 VÍA BRICEÑO - SOPÓ');--ALPINA
INSERT INTO EMPRESA values('800184925-9','CARRERA 72 #80-94');--ELECTROLUX
INSERT INTO EMPRESA values('830507278-9','Km 3.5 Vía Bogotá-Siberia.');--ALGARRA S.A.
INSERT INTO EMPRESA values('830028931-5','Carrera 7 # 113 - 43 Of 607 (Torre Samsung)');--Samsung
INSERT INTO EMPRESA values('830077570','Calle 108 # 62-47');--Huawei
INSERT INTO EMPRESA values('830016046','Calle 15S #19-23');--Avantel 
INSERT INTO EMPRESA values('860017005','Dg. 25g #94-55');--Challenger S.A
INSERT INTO EMPRESA values('890301884','Cra. 27 #1F');--Colombina
INSERT INTO EMPRESA values('890903939','Calle 17a #35-75');--Postobon

--Poblando Tabla CLIENTE.
INSERT INTO CLIENTE values('af.hernandezl@hotmail.com', 'Le yo',                0 ,NULL, '1007784099');
INSERT INTO CLIENTE values('mj.ocampov@hotmail.com',    'Mari Ocampo',          0 ,NULL, '1098825442');
INSERT INTO CLIENTE values('lj.romero@hotmail.com',     'Leidy Romero',         0 ,NULL, '1010247329');
INSERT INTO CLIENTE values('de.saavedra@hotmail.com',   'David Saavedra',       0 ,NULL, '99083007504');
INSERT INTO CLIENTE values('j.prieto@hotmail.com',      'Juli Prieto',          0 ,NULL, '20001017532');
INSERT INTO CLIENTE values('jp.rodriguez@hotmail.com',  'Jeni Rodriguez',       0 ,NULL, '1233906026');
INSERT INTO CLIENTE values('gbravo@hotmail.com',        'Germán Bravo',         0 ,NULL, '11223445566');
INSERT INTO CLIENTE values('cjimenez@hotmail.com',      'Claudia Jimenez',      0 ,NULL, '1000234423');
INSERT INTO CLIENTE values('cc.aparicio@hotmail.com',   'Christian Aparicio',   0 ,NULL, '1000320320');
INSERT INTO CLIENTE values('lc.cruz@hotmail.com',       'Laura Sue',            0 ,NULL, '99040202095');
INSERT INTO CLIENTE values('empresa1@hotmail.com',      'Philips',              0 ,'860005396', NULL);
INSERT INTO CLIENTE values('empresa2@hotmail.com',      'Alpina',               0 ,'860025900', NULL);
INSERT INTO CLIENTE values('empresa3@hotmail.com',      'Electrolux',           0 ,'800184925-9', NULL);
INSERT INTO CLIENTE values('empresa4@hotmail.com',      'Algarra S.A',          0 ,'830507278-9', NULL);
INSERT INTO CLIENTE values('empresa5@hotmail.com',      'Samsung',              0 ,'830028931-5', NULL);
INSERT INTO CLIENTE values('empresa6@hotmail.com',      'Huawei',               0 ,'830077570', NULL);
INSERT INTO CLIENTE values('empresa7@hotmail.com',      'Avantel',              0 ,'830016046', NULL);
INSERT INTO CLIENTE values('empresa8@hotmail.com',      'Challenger',           0 ,'860017005', NULL);
INSERT INTO CLIENTE values('empresa9@hotmail.com',      'Colombina',            0 ,'890301884', NULL);
INSERT INTO CLIENTE values('empresa10@hotmail.com',     'Postobon',             0 ,'890903939', NULL);

--Poblando Tabla FACTURA.

--Poblando Tabla FACTURA_PRODUCTO.

--Poblando Tabla PROVEEDOR.
INSERT INTO PROVEEDOR values('987654321','Hasbro',4.5);
INSERT INTO PROVEEDOR values('830047819','Coca Cola',4.7);
INSERT INTO PROVEEDOR values('890903939','Postobon',4.5);
INSERT INTO PROVEEDOR values('123456789','Nike',4.6);
INSERT INTO PROVEEDOR values('900460312-0','Vans',5);
INSERT INTO PROVEEDOR values('890300546-6','Colgate',4);
INSERT INTO PROVEEDOR values('830050346','Purina',4);
INSERT INTO PROVEEDOR values('111111111','Plantas', 3.5);
INSERT INTO PROVEEDOR values('222222222', 'Zenu', 3);
INSERT INTO PROVEEDOR values('333333333', 'Limpieza', 3);
INSERT INTO PROVEEDOR values('444444444', 'Alpina', 4);
INSERT INTO PROVEEDOR values('555555555', 'Arroz Diana', 4.3);
INSERT INTO PROVEEDOR values('666666666', 'Haz de oros', 4);
INSERT INTO PROVEEDOR values('777777777', 'Olivetto',4.4);
INSERT INTO PROVEEDOR values('888888888', 'Huggies',3.5);

--Poblando Tabla PROVEEDORES_PRODUCTO.
INSERT INTO PROVEEDORES_PRODUCTO values ('111111111','AAAAAAAAAAAA1');
INSERT INTO PROVEEDORES_PRODUCTO values ('111111111','AAAAAAAAAAAA2');
INSERT INTO PROVEEDORES_PRODUCTO values ('111111111','AAAAAAAAAAAA3');
INSERT INTO PROVEEDORES_PRODUCTO values ('111111111','AAAAAAAAAAAA4');
INSERT INTO PROVEEDORES_PRODUCTO values ('555555555','AAAAAAAAAAAA5');
INSERT INTO PROVEEDORES_PRODUCTO values ('666666666','AAAAAAAAAAAA6');
INSERT INTO PROVEEDORES_PRODUCTO values ('777777777','AAAAAAAAAAAA7');
INSERT INTO PROVEEDORES_PRODUCTO values ('444444444','AAAAAAAAAAAA8');
INSERT INTO PROVEEDORES_PRODUCTO values ('222222222','AAAAAAAAAAAA9');
INSERT INTO PROVEEDORES_PRODUCTO values ('222222222','AAAAAAAAAAA10');
INSERT INTO PROVEEDORES_PRODUCTO values ('333333333','AAAAAAAAAAA11');
INSERT INTO PROVEEDORES_PRODUCTO values ('333333333','AAAAAAAAAAA12');
INSERT INTO PROVEEDORES_PRODUCTO values ('890300546-6','AAAAAAAAAAA13');
INSERT INTO PROVEEDORES_PRODUCTO values ('888888888','AAAAAAAAAAA14');
INSERT INTO PROVEEDORES_PRODUCTO values ('830050346','AAAAAAAAAAA15');
INSERT INTO PROVEEDORES_PRODUCTO values ('830050346','AAAAAAAAAAA16');
INSERT INTO PROVEEDORES_PRODUCTO values ('830047819','AAAAAAAAAAA17');
INSERT INTO PROVEEDORES_PRODUCTO values ('890903939','AAAAAAAAAAA18');
INSERT INTO PROVEEDORES_PRODUCTO values ('987654321','AAAAAAAAAAA19');
INSERT INTO PROVEEDORES_PRODUCTO values ('987654321','AAAAAAAAAAA20');
INSERT INTO PROVEEDORES_PRODUCTO values ('900460312-0','AAAAAAAAAAA21');
INSERT INTO PROVEEDORES_PRODUCTO values ('123456789','AAAAAAAAAAA22');

--Poblando Tabla ORDENPEDIDO.
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '30/10/2018', '31/10/2018' , 4, 'Entregado', '111111111', 1);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '30/10/2018', '31/10/2018' , 4.5, 'Entregado', '111111111', 2);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '30/10/2018', '31/10/2018' , 4.2, 'Entregado', '111111111', 4);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '31/10/2018' , 4.4, 'Entregado', '222222222', 2);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '20/10/2018' , 1.5, 'Entregado', '333333333', 2);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '27/10/2018' , 2.7, 'Entregado', '444444444', 1);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '01/11/2018' , 4, 'Entregado', '555555555', 1);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '24/11/2018' , 5, 'Entregado', '666666666', 2);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '26/10/2018' , 4.8, 'Entregado', '777777777', 3);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '28/10/2018' ,4.7, 'Entregado', '888888888', 4);
INSERT INTO ORDENPEDIDO values (superAndes_sequence.NEXTVAL, '31/10/2018', '07/11/2018' , 3.7, 'Entregado', '830047819', 2);

--Poblando Tabla PRODUCTO_ORDENPEDIDO.

INSERT INTO PRODUCTO_ORDENPEDIDO values(59, 100, 4.0, '23/10/2018', 'AAAAAAAAAAAA1');
INSERT INTO PRODUCTO_ORDENPEDIDO values(59,30, 4.5, '24/10/2018', 'AAAAAAAAAAAA2');
INSERT INTO PRODUCTO_ORDENPEDIDO values(59, 15, 3.0, '26/10/2018', 'AAAAAAAAAAAA3');
INSERT INTO PRODUCTO_ORDENPEDIDO values(59, 25, 4.5, '27/10/2018', 'AAAAAAAAAAAA4');
INSERT INTO PRODUCTO_ORDENPEDIDO values(60, 100, 4.0, '23/10/2018', 'AAAAAAAAAAAA1');
INSERT INTO PRODUCTO_ORDENPEDIDO values(60, 15, 5.0, '27/10/2018', 'AAAAAAAAAAAA3');
INSERT INTO PRODUCTO_ORDENPEDIDO values(61, 30, 4.2, '26/10/2018', 'AAAAAAAAAAAA2');
INSERT INTO PRODUCTO_ORDENPEDIDO values(61, 25, 4.2, '26/10/2018', 'AAAAAAAAAAAA4');
INSERT INTO PRODUCTO_ORDENPEDIDO values(61, 100, 4.2, '27/10/2018', 'AAAAAAAAAAAA1');
INSERT INTO PRODUCTO_ORDENPEDIDO values(62, 5,4.6, '27/10/2018', 'AAAAAAAAAAAA9');
INSERT INTO PRODUCTO_ORDENPEDIDO values(62, 5,4.2, '26/10/2018', 'AAAAAAAAAAA10');
INSERT INTO PRODUCTO_ORDENPEDIDO values(63, 7, 1.0, '15/10/2018', 'AAAAAAAAAAA11');
INSERT INTO PRODUCTO_ORDENPEDIDO values(63, 30, 2.0, '17/10/2018', 'AAAAAAAAAAA12');
INSERT INTO PRODUCTO_ORDENPEDIDO values(65, 20, 2.7, '21/10/2018', 'AAAAAAAAAAAA8');
INSERT INTO PRODUCTO_ORDENPEDIDO values(64, 70, 3.7,'27/10/2018' , 'AAAAAAAAAAA17');
INSERT INTO PRODUCTO_ORDENPEDIDO values(66, 70, 4, '27/10/2018', 'AAAAAAAAAAAA5');
INSERT INTO PRODUCTO_ORDENPEDIDO values(67, 25, 5, '15/10/2018', 'AAAAAAAAAAAA6');
INSERT INTO PRODUCTO_ORDENPEDIDO values(68, 5, 4.8, '16/10/2018', 'AAAAAAAAAAAA7');
INSERT INTO PRODUCTO_ORDENPEDIDO values(69, 20, 4.7, '20/10/2018', 'AAAAAAAAAAA14');
--INSERT INTO PRODUCTO_ORDENPEDIDO values(70, 70, 3.7, '16/10/2018', 'AAAAAAAAAAA17');

--Poblando Tabla PROM_DESCUENTO.

--Poblando Tabla PROM_SEGUNIDESCUENTO.

--Poblando Tabla PROM_PAGLLEVEUNID.

--Poblando Tabla PROM_PAGLLEVECANT.

Commit;