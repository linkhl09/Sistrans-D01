package uniandes.isis2304.superAndes.persistencia;


import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.*;

/**
 * Clase para el manejador de persistencia del proyecto SuperAndes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta con la base de datos
 * 
 * 
 * Se apoya en las clases SQLcategoria, SQLTipo, SQLTipoCategoria, SQLproducto, SQLsucursal, SQLSucursalProducto
 * SQLbodega, SQLestante, SQLproductosEnBodega, SQLProductosEnEstante, SQLpersonaNatural, SQLempresa, SQLcliente, 
 * SQLcarritoCompas, SQLProducto_CarritoCompras, SQLfactura, SQLfactura_producto, SQLproveedor, SQLproveedores_producto,
 * SQLordenPedido, SQLproducto_ordenPedido, SQLProm_Descuento, SQLProm_Pag_Lleve_Unid , SQLProm_Desc_Seg_Unid y SQLProm_Pag_lleve_cantidad.
 * que son las que realizan el acceso a la base de datos.
 * 
 * @author Jenifer Rodriguez y Andrés Herández
 */
public class PersistenciaSuperAndes {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, Tipo, categoria, proveedor, promocion, producto, personaNatural,
	 * empresa, cliente, factura, sucursal, ordenPedido, bodega, estante, proveedores_producto,
	 * producto_ordenPedido, factura_producto, cliente_sucursal, productosEnBodega, ProductosEnEstante,
	 * SucursalProducto y HistoriaPromociones.
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a la tabla "Categoria" de la base de datos
	 */
	private SQLCategoria sqlCategoria;

	/**
	 * Atributo para el acceso a la tabla "Tipo" de la base de datos
	 */
	private SQLTipo sqlTipo;	

	/**
	 * Atributo para el acceso a la tabla "TipoCategoria" de la base de datos
	 */
	private SQLTipoProducto sqlTipoProducto;

	/**
	 * Atributo para el acceso a la tabla "Producto" de la base de datos
	 */
	private SQLProducto sqlProducto;

	/**
	 * Atributo para el acceso a la tabla "Sucursal" de la base de datos
	 */
	private SQLSucursal sqlSucursal;

	/**
	 * Atributo para el acceso a la tabla "SucursalProducto" de la base de datos
	 */
	private SQLSucursalProducto sqlSucursalProducto;

	/**
	 * Atributo para el acceso a la tabla "Bodega" de la base de datos
	 */
	private SQLBodega sqlBodega; 

	/**
	 * Atributo para el acceso a la tabla "Estante" de la base de datos
	 */
	private SQLEstante sqlEstante;

	/**
	 * Atributo para el acceso a la tabla "ProductosEnBodega" de la base de datos
	 */
	private SQLProductosEnBodega sqlProductosEnBodega;

	/**
	 * Atributo para el acceso a la tabla "ProductosEnEstante" de la base de datos
	 */
	private SQLProductosEnEstante sqlProductosEnEstante;	

	/**
	 * Atributo para el acceso a la tabla "PersonaNatural" de la base de datos
	 */
	private SQLPersonaNatural sqlPersonaNatural;

	/**
	 * Atributo para el acceso a la tabla "Empresa" de la base de datos
	 */
	private SQLEmpresa sqlEmpresa;

	/**
	 * Atributo para el acceso a la tabla "Cliente" de la base de datos
	 */
	private SQLCliente sqlCliente;

	/**
	 * Atributo para el acceso a la tabla "CarritoCompras" de la base de datos.
	 */
	private SQLCarritoCompras sqlCarritoCompras;

	/**
	 * Atributo para el acceso a la tabla "Producto_CarritoCompras" de la base de datos.
	 */
	private SQLProductoCarritoCompras sqlProductoCarritoCompras;

	/**
	 * Atributo para el acceso a la tabla "Factura" de la base de datos
	 */
	private SQLFactura sqlFactura;

	/**
	 * Atributo para el acceso a la tabla "FacturaProducto" de la base de datos
	 */
	private SQLFacturaProducto sqlFacturaProducto;

	/**
	 * Atributo para el acceso a la tabla "Proveedor" de la base de datos
	 */
	private SQLProveedor sqlProveedor;

	/**
	 * Atributo para el acceso a la tabla "ProveedoresProducto" de la base de datos
	 */
	private SQLProveedoresProducto sqlProveedoresProducto;

	/**
	 * Atributo para el acceso a la tabla "OrdenPedido" de la base de datos
	 */
	private SQLOrdenPedido sqlOrdenPedido;

	/**
	 * Atributo para el acceso a la tabla "ProductoOrdenPedido" de la base de datos
	 */
	private SQLProductoOrdenPedido sqlProductoOrdenPedido;

	/**
	 * Atributo para el acceso a la tabla "PomDescuento" de la base de datos
	 */
	private SQLPromDescuento sqlPromDescuento;

	/**
	 * Atributo para el acceso a la tabla "PromPagLlevUnidad" de la base de datos
	 */
	private SQLPromPagLlevUnidad sqlPromPagLlevUnidad;

	/**
	 * Atributo para el acceso a la tabla "PromDescSegUnid" de la base de datos
	 */
	private SQLPromDescSegUnid sqlPromDescSegUnid;

	/**
	 * Atributo para el acceso a la tabla "PromPagLleveCatidad" de la base de datos
	 */
	private SQLPromPagLleveCatidad sqlPromPagLleveCatidad;

	
	/**
	 * Atributo con métodos utiles para el manejo de la base de datos.
	 */
	private SQLUtil sqlUtil;

	// -----------------------------------------------------------------
	// Métodos Manejador de persistencia.
	// -----------------------------------------------------------------

	/**
	 * Constructor privado con los valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		tablas = new LinkedList<String>();
		tablas.add("superAndes_sequence");
		tablas.add("CATEGORIA");
		tablas.add("TIPO");
		tablas.add("PRODUCTO");
		tablas.add("TIPO_PRODUCTO");
		tablas.add("SUCURSAL");
		tablas.add("SUCURSAL_PRODUCTO");
		tablas.add("BODEGA");
		tablas.add("ESTANTE");
		tablas.add("PRODUCTOSENBODEGA");
		tablas.add("PRODUCTOSENESTANTE");
		tablas.add("PERSONANATURAL");
		tablas.add("EMPRESA");
		tablas.add("CLIENTE");
		tablas.add("CARRITOCOMPRAS");
		tablas.add("PRODUCTO_CARRITOCOMPRAS");
		tablas.add("FACTURA");
		tablas.add("FACTURA_PRODUCTO");
		tablas.add("PROVEEDOR");
		tablas.add("PROVEEDORES_PRODUCTO");
		tablas.add("ORDENPEDIDO");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		tablas.add("PROM_DESCUENTO");
		tablas.add("PROM_SEGUNIDESCUENTO");
		tablas.add("PROM_PAGLLEVEUNID");
		tablas.add("PROM_PAGLLEVECANT");

	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);

		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();

		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance()
	{
		if (instance == null)
			instance = new PersistenciaSuperAndes();
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance(JsonObject tableConfig)
	{
		if (instance == null)
			instance = new PersistenciaSuperAndes(tableConfig);
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas.
	 */
	private List<String> leerNombresTablas(JsonObject tableConfig) 
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL()
	{
		sqlCategoria = new SQLCategoria(this);
		sqlTipo = new SQLTipo(this);		
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlProducto = new SQLProducto(this);
		sqlSucursal = new SQLSucursal(this);
		sqlSucursalProducto  = new SQLSucursalProducto(this);
		sqlBodega = new SQLBodega(this); 
		sqlEstante = new SQLEstante(this);
		sqlProductosEnBodega = new SQLProductosEnBodega(this);
		sqlProductosEnEstante = new SQLProductosEnEstante(this);
		sqlPersonaNatural = new SQLPersonaNatural(this);
		sqlEmpresa = new SQLEmpresa(this);
		sqlCliente = new SQLCliente(this);
		sqlCarritoCompras = new SQLCarritoCompras(this);
		sqlProductoCarritoCompras = new SQLProductoCarritoCompras(this);
		sqlFactura = new SQLFactura(this);
		sqlFacturaProducto = new SQLFacturaProducto(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProveedoresProducto = new SQLProveedoresProducto(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlProductoOrdenPedido = new SQLProductoOrdenPedido(this);
		sqlPromDescuento = new SQLPromDescuento(this);
		sqlPromPagLlevUnidad = new SQLPromPagLlevUnidad(this);
		sqlPromDescSegUnid = new SQLPromDescSegUnid(this);
		sqlPromPagLleveCatidad = new SQLPromPagLleveCatidad(this);		
		sqlUtil  = new SQLUtil(this);		
	}

	/**
	 * @return La cadena de caracteres con el nombre del sequenciador de SuperAndes
	 */
	public String darSeqSuperAndes() 
	{
		return tablas.get(0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Categoria de SuperAndes
	 */
	public String darTablaCategoria()
	{
		return tablas.get(1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Tipo de SuperAndes
	 */
	public String darTablaTipo()
	{
		return tablas.get(2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Producto de SuperAndes
	 */
	public String darTablaProducto()
	{
		return tablas.get(3);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoProducto de SuperAndes.
	 */
	public String darTablaTipoProducto() 
	{
		return tablas.get(4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sucursal de SuperAndes
	 */
	public String darTablaSucursal()
	{
		return tablas.get(5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de SucursalProducto de SuperAndes
	 */
	public String darTablaSucursalProducto()
	{
		return tablas.get(6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bodega de SuperAndes
	 */
	public String darTablaBodega()
	{
		return tablas.get(7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Estante de SuperAndes
	 */
	public String darTablaEstante()
	{
		return tablas.get(8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosEnBodega de SuperAndes
	 */
	public String darTablaProductosEnBodega()
	{
		return tablas.get(9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosEnEstante de SuperAndes
	 */
	public String darTablaProductosEnEstante()
	{
		return tablas.get(10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PersonaNatural de SuperAndes
	 */
	public String darTablaPersonaNatural()
	{
		return tablas.get(11);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de "Empresa" de SuperAndes
	 */
	public String darTablaEmpresa()
	{
		return tablas.get(12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de "Cliente" de SuperAndes
	 */
	public String darTablaCliente()
	{
		return tablas.get(13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de "Carrito de compras" de SuperAndes
	 */
	public String darTablaCarritoCompras()
	{
		return tablas.get(14);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de "Producto - Carrito de compras" de SuperAndes
	 */
	public String darTablaProductoCarritoCompras()
	{
		return tablas.get(15);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Factura de SuperAndes
	 */
	public String darTablaFactura()
	{
		return tablas.get(16);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de FacturaProducto de SuperAndes
	 */
	public String darTablaFacturaProducto()
	{
		return tablas.get(17);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Proveedor de SuperAndes
	 */
	public String darTablaProveedor()
	{
		return tablas.get(18);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProveedoresProducto de SuperAndes
	 */
	public String darTablaProveedoresProducto()
	{
		return tablas.get(19);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OrdenPedido de SuperAndes
	 */
	public String darTablaOrdenPedido()
	{
		return tablas.get(20);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductoOrdenPedido de SuperAndes
	 */
	public String darTablaProductoOrdenPedido()
	{
		return tablas.get(21);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromDescuento de parranderos
	 */
	public String darTablaPromDescuento()
	{
		return tablas.get (22);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromPagLleveUnida de parranderos
	 */
	public String darTablaPromPagLleveUnida()
	{
		return tablas.get (23);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromDescSegUnid de parranderos
	 */
	public String darTablaPromDescSegUnid()
	{
		return tablas.get (24);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromPagLleveCant de parranderos
	 */
	public String darTablaPromPagLleveCant()
	{
		return tablas.get (25);
	}


	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextval()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de SuperAndes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos 
	 * @return Un arreglo con 21 números que indican el número de tuplas borradas en las tablas ProveedoresProducto, ProductoOrdenPedido, FacturaProducto,
	 * ClienteSucursal, ProductosEnBodega, ProductosEnEstante, SucursalProducto, HistorialPromociones, Bodega, Estante, Promocion, Producto, Categoria, Tipo,
	 * Factura, PersonaNatural, Empresa, Cliente, OrdenPedido, Sucursal, Proveedor.
	 */
	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarSuperAndes(pm);
			tx.commit ();
			log.info ("Borrada la base de datos.");
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Tipo
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla de Tipo.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombre - nombre del nuevo tipo.
	 * @return El objeto de tipo Tipo adicionado. Null si ocurre alguna Exception.
	 */
	public Tipo adicionarTipo(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipo.adicionarTipo(pm, nombre);
			tx.commit();

			log.trace("Inserción de tipo producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Tipo(nombre);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla de la tabla TIPO.
	 * @param nombre - El nombre de la tupla a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarTipo(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlTipo.eliminarTipo(pm, nombre);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todos los Tipos de la tabla TIPO.
	 * @return Una lista de objetos Tipo, construidos con base en las tuplas de la tabla TIPO.
	 */
	public List<Tipo> darTipos()
	{
		return sqlTipo.darTipos(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TIPO con el identificador dado.
	 * @param nombre - El nombre del tipo buscado.
	 * @return El objeto tipo Tipo construido con base en las tuplas de la tabla TIPO.
	 */
	public Tipo darTipo(String nombre)
	{
		return sqlTipo.darTipo(pmf.getPersistenceManager(), nombre);
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Categoria
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla de Categoria.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombre - nombre de la nueva categoria.
	 * @return El objeto de tipo Categoria adicionado. Null si ocurre alguna Exception.
	 */
	public Categoria adicionarCategoria(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.adicionarCategoria(pm, nombre);
			tx.commit();

			log.trace("Inserción de Categoria: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Categoria(nombre);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla de la tabla CATEGORIA, dado su identificador.
	 * @param nombre - El nombre de la categoria a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarCategoria(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCategoria.eliminarCategoria(pm, nombre);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las categorias en la tabla CATEGORIA.
	 * @return Una lista de objetos Categoria, construidos con base en las tuplas de la tabla CATEGORIA.
	 */
	public List<Categoria> darCategorias()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CATEGORIA con el identificador dado.
	 * @param nombre - El nombre de la categoria buscada.
	 * @return El objeto tipo Categoria construido con base en las tuplas de la tabla CATEGORIA.
	 */
	public Categoria darCategoria(String nombre)
	{
		return sqlCategoria.darCategoria(pmf.getPersistenceManager(), nombre);
	}

	// -----------------------------------------------------------------
	// Métodos de tabla TipoCategoria
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera tradicional, una tupla de TipoProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombreTipo - El nombre del tipo.
	 * @param codigoBarrasProducto - El identificador del producto dueño del pragama.
	 * @return El objeto de tipo TipoProducto adicionado. Null si ocurre alguna Exception.
	 */
	public TipoProducto adicionarTipoProducto(String nombreTipo, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipoProducto.adicionarTipoProducto(pm, codigoBarrasProducto, nombreTipo);
			tx.commit();

			log.trace("Inserción de TipoCategoria con la categoria: " + codigoBarrasProducto + " y el tipo: " + nombreTipo+ ". Con " + tuplasInsertadas + " tuplas Insertadas");

			return new TipoProducto(codigoBarrasProducto, nombreTipo);
		}
		catch(Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla de la tabla TIPOPRODUCTO, dado su identificador.
	 * @param nombreTipo - El nombre del tipo a eliminar. 
	 * @param codigoBarrasProducto - El identificador del producto dueño del tipo.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarTipoProducto(String nombreTipo, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlTipoProducto.eliminarTipoProducto(pm, codigoBarrasProducto, nombreTipo);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TIPOPRODUCTO que pertenecen a un Producto.
	 * @param codigoBarrasProducto - Identificador del producto de interes..
	 * @return Una lista de objetos TipoProducto, construidos con base en las tuplas de la tablas TIPOPRODUCTO.
	 */
	public List<TipoProducto> darTiposDelProducto(String codigoBarrasProducto)
	{
		return sqlTipoProducto.darTiposDelProducto(pmf.getPersistenceManager(), codigoBarrasProducto);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TIPOPRODUCTO.
	 * @return Una lista de objetos TipoCategoria, construidos con base en las tuplas de la tabla TIPOPRODUCTO.
	 */
	public List<TipoProducto> darTodosTipoProducto()
	{
		return sqlTipoProducto.darTodosTipoProducto(pmf.getPersistenceManager());
	}

	// -----------------------------------------------------------------
	// Métodos de tabla producto
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla de Producto.
	 * Adiciona entradas al log de la aplicación.
	 * @param codigoBarras - Codigo de barras del producto.
	 * @param nombre - Nombre del producto.
	 * @param marca - Marca del producto.
	 * @param precioUnitario - Precio unitario del producto.
	 * @param presentacion - Presentación del producto.
	 * @param precioUnidadMedida - Precio por unidad de medida del producto.
	 * @param cantidadPresentacion - Cantidad en la presentación del producto.
	 * @param peso - Valor númerico del peso del producto.
	 * @param unidadMedidaPeso - Unidad de medida del peso del producto.
	 * @param volumen - Valor númerico del volumen del producto.
	 * @param unidadMedidaVolumen - Unidad de medida del volumen del producto.
	 * @param calidad - Calidad del producto.
	 * @param nivelReorden - Nivel de reorden del producto. 
	 * @param fechaVencimiento - Fecha de vencimiento del producto, null si no es un producto perecedero.
	 * @param categoria - Categoria del prodcuto
	 * @param estaEnPromocion - Booleano que indica si el producto esta en promoción.
	 * @return El objeto de tipo Producto adicionado. Null si ocurre alguna Exception.
	 */
	public Producto adicionarProducto(String codigoBarras, String nombre, String marca, 
			double precioUnitario, String presentacion, double precioUnidadMedida, int cantidadPresentacion, 
			double peso, String unidadMedidaPeso, double volumen, String unidadMedidaVolumen, double calidad, 
			int nivelReorden, Date fechaVencimiento, String categoria, boolean estaEnPromocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, 
					cantidadPresentacion, peso, unidadMedidaPeso, volumen, unidadMedidaVolumen, calidad, 
					nivelReorden, fechaVencimiento, categoria, estaEnPromocion);
			tx.commit();

			log.trace("Inserción del producto con (nombre: " + nombre + " y marca: " + marca + " ): " + tuplasInsertadas + " tuplas insertadas."); 

			return new Producto(codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, peso, unidadMedidaPeso,
					volumen, unidadMedidaVolumen, calidad, nivelReorden, fechaVencimiento, categoria, estaEnPromocion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PRODUCTO, dado el identificador del producto.
	 * Adiciona entradas al log de la aplicación.
	 * @param codigoBarras - codigo de barras del producto.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarProducto(String codigoBarras) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProducto.eliminarProductoCodigoBarras(pm, codigoBarras);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tupas de la tabla PRODCUTO.
	 * @return Una lista de objetos Producto, construidos con base en las tuplas de la tabla PRODUCTO.
	 */
	public List<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PRODUCTO con el identificador dado.
	 * @param codigoBarras - Código de barras del producto buscado.
	 * @return El objeto TipoProducto construido con base en las tuplas de la tabla PRODUCTO.
	 */
	public Producto darProducto(String codigoBarras)
	{
		return sqlProducto.darProducto(pmf.getPersistenceManager(), codigoBarras);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla PRODUCTO con el nombre dado.
	 * @param nombre - Nombre del producto buscado.
	 * @return El objeto Producto construido con base en las tuplas de la tabla PRODUCTO.
	 */
	public Producto darProductoPorNombre(String nombre)
	{
		return sqlProducto.darProducto(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que cambia el valor booleano de estaEnPromoción de un producto dado a verdadero.
	 * @param codigoBarras - Identificador del producto al que se le creo una promoción.
	 * @return El número de tuplas actualizadas. -1 Si ocurre alguna Exception.
	 */
	public long nuevaPromocion(String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProducto.nuevaPromocion(pm, codigoBarras);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que cambia el valor booleano de Esta en promoción de un producto dado a falso.
	 * @param codigoBarras - Identificador del producto al que se le quito la promoción.
	 * @return El número de tuplas actualizadas. -1 si ocurre alguna Exception.
	 */
	public long terminarPromocion(String codigoBarras)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProducto.terminarPromocion(pm, codigoBarras);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de tabla sucursal
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla de la tabla Sucursal.
	 * Adiciona entradas al log de la aplicación.
	 * @param direccion - Dirección de la sucursal.
	 * @param ciudad - Ciudad de la sucursal.
	 * @param nombre - Nombre de la sucursal.
	 * @param segmentacionMercado - Segmentación de mercado de la sucursal.
	 * @param tamanio - Tamaño de la sucursal.
	 * @return El objeto tipo Sucursal adicionado. Null si se encuentra alguna Exception.
	 */
	public Sucursal adicionarSucursal(String direccion, String ciudad,
			String nombre, String segmentacionMercado, int tamanio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, id, direccion, ciudad, nombre, segmentacionMercado, tamanio);
			tx.commit();

			log.trace("Inserción de la sucursal con nombre: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Sucursal(id, direccion, ciudad, nombre, segmentacionMercado, tamanio);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla SUCURSAL, dado su nombre.
	 * @param nombre - El nombre de la sucursal a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlSucursal.eliminarSucursalPorNombre(pm, nombre);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla SUCURSAL, dado su identificador.
	 * @param id - El identificador de la sucursal.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalPorId(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlSucursal.eliminarSucursalPorId(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla SUCURSAL.
	 * @return Una lista de objetos Sucursal, construidos con base en las tuplas de la tabla SUCURSAL.
	 */
	public List<Sucursal> darSucursales()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla SUCURSAL, con el nombre dado.
	 * @param nombre - El nombre de la sucursal buscada.
	 * @return El objeto Sucursal construido con base en las tuplas de la tabla SUCURSAL.
	 */
	public Sucursal darSucursalPorNombre(String nombre)
	{
		return sqlSucursal.darSucursalPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla SUCURSAL, con el identificador dado.
	 * @param id - Identificador de la sucursal buscada.
	 * @return El objeto tipo Sucursal construido con base en las tuplas de la tabla SUCURSAL.
	 */
	public Sucursal darSucursalPorId(long id)
	{
		return sqlSucursal.darSucursalPorId(pmf.getPersistenceManager(), id);
	}
	
	/**
	 * RFC1 MUESTRA EL DINERO RECOLECTADO POR VENTAS EN CADA SUCURSAL DURANTE UN PERIODO 
	  DE TIEMPO Y EN EL AÑO CORRIDO
	 * @return La lista de parejas de objetos, construidos con base en las tuplas de la tabla BEBEDOR y VISITAN. 
	 * El primer elemento de la pareja es un el valor total pagado por la sucursal; 
	 * el segundo elemento es el identificador de la sucursal
	 */
	 
	public  List<long[]>  darDineroRecolectadoSucursales(  Date fechaInicio, Date fechaFin){
	
		List<long []> respuesta = new LinkedList <long []> ();
		List<Object[]> tuplas = sqlSucursal.darDineroRecolectadoSucursales(pmf.getPersistenceManager(),fechaInicio, fechaFin);
		for ( Object [] tupla : tuplas)
        {
			long [] datos = new long [2] ;
		   
			long valorTotal = ((BigDecimal) tupla [0]).longValue ();
			long idSucursal = ((BigDecimal) tupla [1]).longValue ();
			
			datos [0] = valorTotal;
			datos [1] = idSucursal;	
			
			respuesta.add(datos);
        }

		return respuesta;
	}
		
	


	// -----------------------------------------------------------------
	// Métodos de tabla SucursalProducto
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla SUCURSAL PRODUCTO.
	 * Adiciona entradas al log de la aplicación.
	 * @param idSucursal - El id de la sucursal.
	 * @param codigoBarrasProducto - El identificador del producto.
	 * @return El objeto tipo SucursalProducto adicionado. Null si se encuentra alguna Exception.
	 */
	public SucursalProducto adicionarSucursalProducto(long idSucursal, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursalProducto.adicionarSucursalProducto(pm, idSucursal, codigoBarrasProducto);
			tx.commit();

			log.trace("Inserción de asociacion entre sucursal: " + idSucursal + " y producto: " + codigoBarrasProducto + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new SucursalProducto(idSucursal, codigoBarrasProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla SUCURSAL PRODUCTO, dado su identificador.
	 * @param idSucursal - El id de la sucursal.
	 * @param codigoBarrasProducto - El producto asociado a esa sucursal.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalProducto(long idSucursal, String codigoBarrasProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlSucursalProducto.eliminarSucursalProducto(pm, idSucursal, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas de la tabla SUCURSAL PRODUCTO que pertenecen a una sucursal dada.
	 * @param idSucursal - Identificador de la sucursal de la que se desean conocer sus productos.
	 * @return Una lista de objetos SucursalProducto construido con base en las tuplas de la tabla ProductosSucursal.
	 */
	public List<SucursalProducto> darProductosSucursal(long idSucursal)
	{
		return sqlSucursalProducto.darProductosSucursal(pmf.getPersistenceManager(),idSucursal);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla SUCURSAL PRODUCTO que tienen el producto.
	 * @param codigoBarrasProducto - El producto del que se desean averiguar en que sucursales esta.
	 * @return El objeto tipo SucursalProducto construido con base en las tuplas de la tabla SucursalProducto.
	 */
	public List<SucursalProducto> darSucursalesProducto(String codigoBarrasProducto )
	{
		return sqlSucursalProducto.darSucursalesProducto(pmf.getPersistenceManager(), codigoBarrasProducto);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla SUCURSALPRODUCTO.
	 * @return Una lista de objetos SucursalProducto, construidos con base en las tuplas de la tabla SUCURSALPRODUCTO.
	 */
	public List<SucursalProducto> darTodosProductosSucursales()
	{
		return sqlSucursalProducto.darTodosProductosSucursales(pmf.getPersistenceManager());
	}


	// -----------------------------------------------------------------
	// Métodos de tabla bodega
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla Bodega.
	 * Adiciona entradas al log de la aplicación.
	 * @param capacidadVol - El valor numerico de la capacidad en volumen de la bodega.
	 * @param capacidadPeso - El valor numerico de la capacidad en peso de la bodega.
	 * @param tipo - El tipo de producto que puede almacenar la bodega.
	 * @param idSucursal - La sucursal a la que pertenece la Bodega.
	 * @return El objeto tipo Bodega adicionado. Null si ocurre alguna Exception.
	 */
	public Bodega adicionarBodega(double capacidadVol, double capacidadPeso, String tipo, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval();
			long tuplasInsertadas = sqlBodega.adicionarBodega(pm, id, capacidadVol, capacidadPeso, tipo, idSucursal);
			tx.commit();

			log.trace("Inserción de la bodega con id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Bodega(id, capacidadPeso, capacidadVol, tipo, idSucursal);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional una tupla en la tabla BODEGA, dado su identificador.
	 * @param id - El identificador de la bodega.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarBodega(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBodega.eliminarBodega(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas de la tabla BODEGA de la base de datos.
	 * @return Una lista de objetos Bodega construido con base en las tuplas de la tabla BODEGA.
	 */
	public List<Bodega> darBodegas()
	{
		return sqlBodega.darBodegas(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas de la tabla BODEGA de la base de datos, que hacen parte de una sucursal.
	 * @param idSucursal - El identificador de la sucursal dueña de las bodegas.
	 * @return Una lista de objetos Bodega, construidos con base en las tuplas de la tabla BODEGA.
	 */
	public List<Bodega> darBodegasSucursal(long idSucursal)
	{
		return sqlBodega.darBodegasSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla BODEGA, con el identificador dado.
	 * @param id - El identificador de la bodega buscada.
	 * @return El objeto tipo Bodega construido con base en las tuplas de la tabla BODEGA.
	 */
	public Bodega darBodega(long id)
	{
		return sqlBodega.darBodega(pmf.getPersistenceManager(), id);
	}

	// -----------------------------------------------------------------
	// Métodos de tabla estante
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla ESTANTE.
	 * Adiciona entradas al log de la aplicación.
	 * @param capacidadVol - El valor numerico de la capacidad en volumen del estante.
	 * @param capacidadPeso - El valor numerico de la capacidad en peso del estante.
	 * @param tipo - El tipo de producto que puede almacenar el estante.
	 * @param idSucursal - La sucursal a la que pertenece el estante.
	 * @return El objeto tipo Estante adicionado. Null si ocurre alguna Exception.
	 */
	public Estante adicionarEstante(double capacidadVolumen, double capacidadPeso, String tipo, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval();
			long tuplasInsertadas = sqlEstante.adicionarEstante(pm, id, capacidadVolumen, capacidadPeso, tipo, idSucursal);
			tx.commit();

			log.trace("Inserción de estante con id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Estante(id, capacidadPeso, capacidadVolumen, tipo, idSucursal);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional una tupla en la tabla ESTANTE 
	 * @param id - El identificador del estante.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarEstante(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEstante.eliminarEstante(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas de la tabla ESTANTES de la base de datos.
	 * @return Una lista de objetos Estante construido con base en las tuplas de la tabla ESTANTE.
	 */
	public List<Estante> darEstantes ()
	{
		return sqlEstante.darEstantes(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas de la tabla ESTANTE de la base de datos, que hacen parte de una sucursal.
	 * @param idSucursal - El identificador de la sucursal dueña de los Estantes.
	 * @return Una lista de objetos Estante, construidos con base en las tuplas de la tabla ESTANTE.
	 */
	public List<Estante> darEstantesPorSucursal(long idSucursal)
	{
		return sqlEstante.darEstantesSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla ESTANTE, con el identificador dado.
	 * @param id - El identificador de la bodega estante.
	 * @return El objeto tipo Estante construido con base en las tuplas de la tabla Estante.
	 */
	public Estante darEstante(long id)
	{
		return sqlEstante.darEstante(pmf.getPersistenceManager(), id );
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnBodega
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla PRODUCTOSENBODEGA.
	 * Adiciona entradas al log de la aplicación.
	 * @param idBodega - Id de la bodega que almacena el producto.
	 * @param cantidad - La cantidad de unidades del producto almacenado en la bodega.
	 * @param nivelAbastecimiento - Nivel de abastecimiento de ese producto en bodega.
	 * @param codigoBarrasProducto - El identificador del tepmrafo
	 * @return El objeto tipo ProductosEnBodega adicionado. Null si ocurre alguna Exception.
	 */
	public ProductosEnBodega adicionarProductosEnBodega(long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosEnBodega.adicionarProductosEnBodega(pm, idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);
			tx.commit();

			log.trace("Inserción de producto: " + codigoBarrasProducto + "en la bodega: " + idBodega + " : " + tuplasInsertadas + " tuplas insertadas."); 

			return new ProductosEnBodega(idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional una tupla en la tabla PRODUCTOSENBODEGA.
	 * @param idBodega - Id de la bodega.
	 * @param codigoBarrasProducto - Código de barras del producto eliminado de la bodega.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarProductoEnBodega(long idBodega, String codigoBarrasProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnBodega.eliminarProductoEnBodega(pm, idBodega, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENBODEGA de la base de datos, que hacen parte de una bodega.
	 * @param idBodega - Identificador de la bodega.
	 * @return Una lista de Objetos ProductosEnBodega, construidos con base en las tuplas de la tabla PRODUCTOSENBODEGA.
	 */
	public List<ProductosEnBodega> darProductosEnBodega(long idBodega)
	{
		return sqlProductosEnBodega.darProductosEnBodega(pmf.getPersistenceManager(), idBodega);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENBODEGA de la base de datos.
	 * @return Una lista de objetos ProductosEnBodega construido con base en las tuplas de la tabla Estante.
	 */
	public List<ProductosEnBodega> darTodosProductosBodegas()
	{
		return sqlProductosEnBodega.darTodosProductosBodegas(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENBODEGA de la base de datos, que tienen almacenado un producto.
	 * @param codigoBarrasProducto - El identificador del producto del cual se quieren conocer las bodegas que lo almacenan.
	 * @return Una lista de objetos ProductosEnBodega, construidos con base en las tuplas de la tabla PRODUCTOSENBODEGA.
	 */
	public List<ProductosEnBodega> darBodegasProducto(String codigoBarrasProducto)
	{
		return sqlProductosEnBodega.darBodegasProducto(pmf.getPersistenceManager(), codigoBarrasProducto);
	}

	/**
	 * Método que aumenta la cantidad de productos en Bodega de todas las tuplas en la tabla con identificador dado.
	 * @param idBodega - Identificador de la bodega con el producto que se desean aumentar las unidades.
	 * @param codigoBarrasProducto - Producto del que se van a aumentar las unidades.
	 * @param productosPedidos - El número de unidades a aumentar del producto en la bodega dada.
	 * @return El numero de tuplas modificadas,-1 Si ocurre alguna Exception.
	 */
	public long aumentarProductosEnBodega( long idBodega, String codigoBarrasProducto, int productosPedidos )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnBodega.aumentarProductosEnBodega(pm, idBodega, codigoBarrasProducto, productosPedidos);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que disminuye la cantidad de productos en Bodega de todas las tuplas en la tabla con el identificador dado.
	 * @param idBodega - Identificador de la bodega con el producto que se desean disminuir las unidades.
	 * @param codigoBarrasProducto - Producto del que se van a disminuir las unidades.
	 * @param cantidadAQuitar - El número de unidades a disminuir del producto en la bodega dada.
	 * @return El numero de tuplas modificadas,-1 Si ocurre alguna Exception.
	 */
	public long disminuirProductosEnBodega(long idBodega, String codigoBarrasProducto, int cantidadAQuitar )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnBodega.disminuirProductosEnBodega(pm, idBodega, codigoBarrasProducto, cantidadAQuitar);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnEstante
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla PRODUCTOSENESTANTE.
	 * Adiciona entradas al log de la aplicación.
	 * @param idEstante - Identificador del estante que almacena el producto.
	 * @param cantidad - Cantidad de unidades del producto en el estante.
	 * @param codigoBarrasProducto - Identificador del producto almacenado en el estante.
	 * @return El objeto tipo ProductosEnEstante adicionado. Null si ocurre alguna Exception.
	 */
	public ProductosEnEstante adicionarProductosEnEstante(long idEstante, int cantidad, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosEnEstante.adicionarProductosEnEstante(pm, idEstante, cantidad, codigoBarrasProducto);
			tx.commit();

			log.trace("Inserción de producto : " + codigoBarrasProducto + " Al estante: "+ idEstante + " : " + tuplasInsertadas + " tuplas insertadas."); 

			return new ProductosEnEstante(idEstante, cantidad, codigoBarrasProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional una tupla en la tabla PRODUCTOSENESTANTE.
	 * @param idEstante - Identificador del estante que almacena el producto.
	 * @param codigoBarrasProducto - Identificador del producto almacenado en el estante que se desea eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarProductosEnEstante(long idEstante, String codigoBarrasProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnEstante.eliminarProductosEnEstante(pm, idEstante, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENESTANTE de la base de datos, que hacen parte de un estante.
	 * @param idEstante - Identificador del estante.
	 * @return Una lista de objetos ProductosEnEstante, construidos con base en las tuplas de la tabla PRODUCTOSENESTANTE.
	 */
	public List<ProductosEnEstante> darProductosEnEstante(long idEstante)
	{
		return sqlProductosEnEstante.darProductosEnEstante(pmf.getPersistenceManager(), idEstante);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENESTANTE, con el identificador dado.
	 * @param idEstante - Identificador del producto en estante.
	 * @param codigoBarrasProducto - Identificador del producto en estante.
	 * @return El objeto tipo ProductoEnEstante construido con base en las tuplas de la tabla ProductoEnEstante.
	 */
	public ProductosEnEstante darProductoEnEstante (long idEstante,  String codigoBarrasProducto) 
	{
		return sqlProductosEnEstante.darProductoEnEstante(pmf.getPersistenceManager(), idEstante, codigoBarrasProducto);
	}

	/**
	 * Método que consulta todas las tuplas de la tabla PRODUCTOSENESTANTE.
	 * @return Una lista de objetos ProductosEnEstante construido con base en las tuplas de la tabla Productos en estante.
	 */
	public List<ProductosEnEstante> darTodosProductosEnEstantes()
	{
		return sqlProductosEnEstante.darTodosProductosEnEstantes(pmf.getPersistenceManager());
	}

	/**
	 * Método que aumenta la cantidad de productos en el estante.
	 * @param idEstante - Identificador del estante que almacena el producto.
	 * @param productosAAumentar - Cantidad de productos a aumentar en el estante.
	 * @param codigoBarrasProducto - Identificador del producto del que se van a aumentar existencias.
	 * @return El número de tuplas modificadas, -1 si ocurre alguna Exception.
	 */
	public long aumentarCantidadProductosEnEstante(long idEstante, int productosAAumentar, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnEstante.aumentarCantidadProductoEnEstante(pm, idEstante, productosAAumentar, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que disminuye la cantidad de productos en el estante.
	 * @param idEstante - Identificador del estante que almacena el producto.
	 * @param productosADisminuir - Cantidad de productos a disminuir en el estante.
	 * @param codigoBarrasProducto - Identificador del producto del que se van a disminuir existencias.
	 * @return El número de tuplas modificadas, -1 si ocurre alguna Exception.
	 */
	public long quitarProductosEstante(long idEstante, int productosADisminuir, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductosEnEstante.disminuirProductoEnEstante(pm, idEstante, productosADisminuir, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}	

	// -----------------------------------------------------------------
	// Métodos de tabla personaNatural
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla PersonaNatural.
	 * Adiciona entradas al log de la aplicacion.
	 * @param documento - numero de identificacion de la persona.
	 * @param tipoDocumento - tipo de documento.
	 * @param correoElectronico - correo electronico de la persona.
	 * @param nombre - nombre de la persona. 
	 * @return El objeto PersonaNatural adicionado. null si ocurre alguna Exception.
	 */
	public PersonaNatural adicionarPersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlPersonaNatural.adicionarPersonaNatural(pm, documento, tipoDocumento);
			long tuplasInsertadas2 = sqlCliente.adicionarClientePersonaNatural(pm, correoElectronico, nombre,documento);
			tx.commit();			

			log.trace("Inserción de la persona natural con documento: " + documento + ": " + tuplasInsertadas + " tuplas insertadas.");
			log.trace("Inserción del cliente con correoElectronico: " + correoElectronico + ": " + tuplasInsertadas2 + " tuplas insertadas."); 


			return new PersonaNatural(documento, tipoDocumento, correoElectronico, nombre, 0);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional la tupla de la tabla PersonaNatural y Cliente.
	 * @param documento - numero de identificacion de la persona natural a eliminar.
	 * @param correoElectronico - correo electronico de la persona natural a eliminar.
	 * @return El número de tuplas eliminadas, -1 Si ocurre alguna Exception.
	 */
	public long[] eliminarPersonaNatural(String documento, String correoElectronico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCliente.eliminarCliente(pm, correoElectronico);
			long resp2 = sqlPersonaNatural.eliminarPersonaNatural(pm, documento); 
			tx.commit();
			return new long[] {resp, resp2};
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de PersonaNatural.
	 * @return Una lista de objetos PersonaNatural, construidos con base en las tuplas de la tabla PERSONA_NATURAL.
	 */
	public List<PersonaNatural> darPersonasNaturales ()
	{
		return sqlPersonaNatural.darPersonasNaturales(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla con un numero de documento dado.
	 * @param documento - numero de identificaciom de la  persona.
	 * @return El objeto PersonaNatural, construido con base en la tabla PERSONA_NATURAL.
	 */
	public PersonaNatural darPersonaNatural (String documento)
	{
		return sqlPersonaNatural.darPersonaNatural(pmf.getPersistenceManager(), documento);
	}	

	// -----------------------------------------------------------------
	// Métodos de tabla Empresa
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Empresa.
	 * Adiciona entradas al log de la aplicacion.
	 * @param nit - numero de identificacion de la empresa.
	 * @param correoElectronico - correo electronico de la persona, es la llave de la tabla cliente
	 * @param nombre - nombre de la empresa.  
	 * @return El objeto PersonaNatural adicionado. null si ocurre alguna Exception.
	 */
	public Empresa adicionarEmpresa(String nit, String direccion, String correoElectronico, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm, nit, direccion);
			long tuplasInsertadas2 = sqlCliente.adicionarClienteEmpresa(pm, correoElectronico, nombre, nit);
			tx.commit();

			log.trace("Inserción de: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 
			log.trace("Inserción del cliente con correoElectronico: " + correoElectronico + ": " + tuplasInsertadas2 + " tuplas insertadas.");
			return new Empresa(nit, direccion, correoElectronico, nombre, 0);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional la tupla de la empresa y Cliente.
	 * @param nit - numero de identificacion de la empresa a eliminar
	 * @param correoElectronico - correo electronico de la persona, es la llave de la tabla cliente
	 * @return El número de tuplas eliminadas, -1 Si ocurre alguna Exception.
	 */
	public long[] eliminarEmpresaPorNit(String nit, String correoElectronico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpresa.eliminarEmpresaPorNit(pm, nit);
			long resp2 = sqlCliente.eliminarCliente(pm, correoElectronico);
			tx.commit();
			return new long[] {resp, resp2};
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional la tupla de la tabla Empresa y Cliente.
	 * @param direccion - direccion de la empresa a eliminar.
	 * @param correoElectronico - correo electronico de la empresa a eliminar.
	 * @return El número de tuplas eliminadas, -1 Si ocurre alguna Exception.
	 */
	public long[] eliminarEmpresaPorDireccion(String direccion, String correoElectronico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpresa.eliminarEmpresaPorDireccion(pm, direccion);
			long resp2 = sqlCliente.eliminarCliente(pm, correoElectronico);
			tx.commit();
			return new long[] {resp, resp2};
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de Empresas.
	 * @return Una lista de objetos Empresa, construidos con base en las tuplas de la tabla EMPRESA.
	 */
	public List<Empresa> darEmpresas()
	{
		return sqlEmpresa.darEmpresas(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla con un numero de identificacion "nit" dado.
	 * @param nit - numero de identificaciom de la  empresa.
	 * @return El objeto Empresa, construido con base en la tabla EMPRESA.
	 */
	public Empresa darEmpresa(String nit)
	{
		return sqlEmpresa.darEmpresa(pmf.getPersistenceManager(), nit);
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Cliente
	// -----------------------------------------------------------------

	/**
	 * Método que consulta todas las tuplas en la tabla de Cliente.
	 * @return Una lista de objetos Cliente, construidos con base en las tuplas de la tabla CLIENTE	.
	 */
	public List<Cliente> darClientes()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla con un correo electronico dado.
	 * @param correoElectronco - correo electronico del cleinte.
	 * @return El objeto Cliente, construido con base en la tabla CLIENTE.
	 */
	public Cliente darCliente (String correoElectronico)
	{
		return sqlCliente.darCliente(pmf.getPersistenceManager(), correoElectronico);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Cliente, y retorna sus correos.
	 * @return Una lista de String con todos los correos de la base de datos.
	 */
	public List<String> darTodosLosCorreos()
	{
		return sqlCliente.darTodosLosCorreos(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que modifica todas las tuplas en la tabla con un correo electronico dado aumentado la
	 * cantidad de puntos .
	 * @param correoElectronco - correo electronico del cliente.
	 * @param puntos - puntos a agregar.
	 * @return El número de tuplas modificadas, -1 Si ocurre alguna Exception.
	 */
	public long aumentarPuntos(String correoElectronico, int puntos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCliente.aumentarPuntos(pm, correoElectronico, puntos);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de CarritoCompras 
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla CARRITOCOMPRAS.
	 * Adiciona entradas al log de la aplicación.
	 * @param cliente - Cliente del carrito de compras.
     * @param idSucursal - el identificador de la sucursal donde se encuentra el carrito de compras.
	 * @return El objeto CarritoCompras adicionado. Null si ocurre alguna Exception.
	 */
	public CarritoCompras adicionarCarritoCompras(String cliente, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval();
			long tuplasInsertadas = sqlCarritoCompras.adicionarCarritoCompras(pm, id, cliente, idSucursal);
			tx.commit();

			log.trace("Inserción del CarritoCompras con el id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new CarritoCompras(id, cliente, idSucursal);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CarritoCompras, con el identificador dado.
	 * @param id - El identificador del carrito de compras.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarCarritoCompras(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCarritoCompras.eliminarCarritoCompras(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e) 
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CarritoCompras con un identificador dado.
	 * @param id - El identificador del carrito de compras.
	 * @return El objeto CarritoCompras, construido con base en las tuplas de la tabla CARRITOCOMPRAS con el identificador dao.
	 */
	public CarritoCompras darCarritoComprasPorId(long id)
	{
		return sqlCarritoCompras.darCarritoComprasPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CarritoCompras con el cliente dado.
	 * @param cliente - El cliente del carrito.
	 * @return El Objeto CarritoCompras, construido con base en las tuplas de la tabla CARRITOCOMPRAS.
	 */
	public CarritoCompras darCarritoComprasPorCliente(String cliente)
	{
		return sqlCarritoCompras.darCarritoComprasPorCliente(pmf.getPersistenceManager(), cliente);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CarritoCompras.
	 * @return La lista de objetos CarritoCompras, construidos con base en las tuplas de la tabla CARRITOSCOMPRA.
	 */
	public List<CarritoCompras> darTodosCarritosCompras()
	{
		return sqlCarritoCompras.darTodosCarritosCompras(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que "abandona" el carrito de manera transaccional, de  una tupla en la tabla CarritoCompras, con el identificador dado.
	 * @param id - El identificador del carrito de compras.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long abandonarCarrito(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCarritoCompras.abandonarCarrito(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e) 
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de Producto Carrito Compras
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ProductoCarritoCompas.
	 * Adiciona entradas al log de la aplicación.
	 * @param carrito - Identificador del carrito al que se agrega un producto.
	 * @param cantidad - Cantidad de unidades del producto agregadas al carrito.
	 * @param codigoBarrasProducto - El producto agregado al carrito.
	 * @return El objeto ProductoCarritoCompras adicionado. Null si ocurre alguna Exception.
	 */
	public ProductoCarritoCompras adicionarProductoCarrito(long carrito, int cantidad, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoCarritoCompras.adicionarPoductoCarrito(pm, carrito, cantidad, codigoBarrasProducto);
			tx.commit();

			log.trace ("Inserción de productoCarritoCompras: carrito->" + carrito + " producto->" + codigoBarrasProducto + ": " + tuplasInsertadas + " tuplas insertadas");

			return new ProductoCarritoCompras(carrito, cantidad, codigoBarrasProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProductoCarritoCompras, dado su identificador.
	 * @param carrito - El identificador.
	 * @param codigoBarrasProducto - El identificador.
	 * @return Número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarProductoCarrito(long carrito, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductoCarritoCompras.eliminarProductoCarrito(pm, carrito, codigoBarrasProducto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoCarritoCompras con un identificador dado.
	 * @param carrito - Identificador de la tabla.
	 * @param codigoBarrasProducto - Identificador de la tabla.
	 * @return El objeto tipo ProductoCarritoCompras, construido con base en las tuplas de la tabla PRODUCTOCARRITOCOMPRAS con el identificador dado.
	 */
	public ProductoCarritoCompras darProductoCarrito(long carrito, String codigoBarrasProducto)
	{
		return sqlProductoCarritoCompras.darProductoCarrito(pmf.getPersistenceManager(), carrito, codigoBarrasProducto);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoCarritoCompras que pertenecen al carrito dado por identificador.
	 * @param carrito - El identificador del carrito dueño de los productos.
	 * @return La lista de objetos ProductoCarritoCompras, construidos con base en las tuplas de la tabla PRODUCTOSCARRITOCOMPRAS.
	 */
	public List<ProductoCarritoCompras> darTodosProductosDeUnCarrito(long carrito)
	{
		return sqlProductoCarritoCompras.darTodosProductosDeUnCarrito(pmf.getPersistenceManager(), carrito);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoCarritoCompras.
	 * @return La lista de objetos ProductosCarritoCompras, construidos con base en las tuplas de la tabla PRODUCTOSCARRITOCOMPRA.
	 */
	public List<ProductoCarritoCompras> darTodosProductosCarrito()
	{
		return sqlProductoCarritoCompras.darTodosProductosCarrito(pmf.getPersistenceManager());
	}

	/**
	 * Método que aumenta la cantidad del producto en el carrito de compras, de manera transaccional.
	 * @param carrito - Identificador del productoCarritoComras.
	 * @param codigoBarrasProducto - Identificador del productoCarritoCompras.
	 * @param productosAgregados - El número de unidades agregadas al carrito de compras.
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Exception.
	 */
	public long aumentarUnidadesProductoCarritoCompras(long carrito, String codigoBarrasProducto, int productosAgregados)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductoCarritoCompras.aumentarUnidadesProductoCarritoCompras(pm, carrito, codigoBarrasProducto, productosAgregados);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	/**
	 * Método que disminuye la cantidad del producto en el carrito de compras, de manera transaccional.
	 * @param carrito - Identificador del productoCarritoComras.
	 * @param codigoBarrasProducto - Identificador del productoCarritoCompras.
	 * @param productosDevueltos - El número de unidades disminuidos al carrito de compras.
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Exception.
	 */
	public long disminuirUnidadesProductoCarritoCompras(long carrito, String codigoBarrasProducto, int productosDevueltos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProductoCarritoCompras.disminuirUnidadesProductoCarritoCompras(pm, carrito, codigoBarrasProducto, productosDevueltos);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Factura
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Factura.
	 * Adiciona entradas al log de la aplicación.
	 * @param direccion - La dirección de la sucursal.
	 * @param fecha - La fecha de la compra.
	 * @param nombreCajero - El nombre del cajero que atendió la compra.
	 * @param valorTotal - el valor total de la compra.
	 * @param pagoExitoso - Si el pago fue exitoso o no.
	 * @param puntosCompra - Puntos que gana el cliente por esa compra.
	 * @param correoCliente - Correo electronico del cliente.
	 * @param idSucursal - Identificador de la sucursal a la que pertenece la factura.
	 * @return El objeto tipo Factura adicionado. Null si ocurre alguna Exception.
	 */
	public Factura adicionarFactura(  String direccion, 
			Date fecha, String nombreCajero, double valorTotal, boolean pagoExitoso, 
			int puntosCompra, String correoCliente, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numero = nextval();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, correoCliente, idSucursal);
			tx.commit();

			log.trace("Inserción de la factura con el numero: " + numero + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Factura(numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, correoCliente, idSucursal);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Factura, dado su identificador.
	 * @param numero - El identificador único de la factura.
	 * @return Número de tuplas eliminadas. -1 si ocurre algún error.
	 */
	public long eliminarFactura(long numero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFactura.eliminarFactura(pm, numero);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Factura.
	 * @return La lista de objetos Factura, construidos con base en las tuplas de la FACTURA.
	 */
	public List<Factura> darFacturas()
	{
		return sqlFactura.darFacturas(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Factura, con el identificador dado.
	 * @param numero - Identificador único de la factura.
	 * @return El objeto tipo Factura, construido con base en las tuplas de la tabla FACTURA.
	 */
	public Factura darFactura(long numero)
	{
		return sqlFactura.darFactura(pmf.getPersistenceManager(), numero);
	}


	// -----------------------------------------------------------------
	// Métodos de tabla Factura_Prodcuto
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla FACTURA_PRODUCTO.
	 * @param factura - Identificador único de la factura.
	 * @param cantidad - Cantidad de unidades vendidas del producto.
	 * @param producto - Identificador único del producto.
	 * @return El objeto tipo FacturaProducto adicionado. Null si ocurre alguna Exception.
	 */
	public FacturaProducto adicionarFacturaProducto(long factura, int cantidad, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlFacturaProducto.adicionarFacturaProducto(pm, factura, cantidad, producto);
			tx.commit();

			log.trace("Inserción de producto: " + producto + "en la factura: " + factura + " : " + tuplasInsertadas + " tuplas insertadas."); 

			return new FacturaProducto(factura, cantidad, producto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, las tuplas pertenecientes a una factura en la tabla FACTURA_PRODUCTO.
	 * @param factura - Identificador único de la factura.
	 * @return Número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarFacturaProductos(long factura) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFacturaProducto.eliminarFacturaProductos(pm, factura);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, las tuplas en la tabla FACTURA_PRODUCTO, con el identificador dado.
	 * @param factura - Identificador de la tabla.
	 * @param producto - Identificador de la tabla.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarProductoDeFactura(long factura, String producto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFacturaProducto.eliminarProductoDeFactura(pm, factura, producto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla FACTURA_PRODUCTO, con el identificador dado.
	 * @param factura - Identificador de la tabla.
	 * @param producto - Identificador de la tabla.
	 * @return El objeto tipo FacturaProducto, construido con base en las tuplas de la tabla FACTURA_PRODUCTO.
	 */
	public FacturaProducto darProductoDeFactura( long factura, String producto)
	{
		return sqlFacturaProducto.darProductoDeFactura(pmf.getPersistenceManager(), factura, producto );
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Factura_Producto que pertenecen a la factura dada.
	 * @param factura - Identificador de la factura.
	 * @return La lista de objetos tipo ProductoFactura, construidos con base en las tuplas de la tabla FACTURA_PRODUCTO.
	 */
	public List<FacturaProducto> darProductosFactura(long factura)
	{
		return sqlFacturaProducto.darProductosFactura(pmf.getPersistenceManager() , factura);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Factura_Producto.
	 * @return La lista de objetos FacturaProducto, construidos con base en las tuplas de Factura_Producto.
	 */
	public List<FacturaProducto> darProductosFacturas()
	{
		return sqlFacturaProducto.darProductosFacturas(pmf.getPersistenceManager());
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Proveedor
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Proveedor.
	 * Adiciona entradas al log de la aplicacion.
	 * @param nombre - El nombre del proveedor.
	 * @param nit - identificador unico del proveedor.
	 * @param calificacion - calificacion de calidad del proveedor.
	 * @return El objeto Proveedor adicionado. null si ocurre alguna Exception.
	 */
	public Proveedor adicionarProveedor(String nit, String nombre, double calificacion)

	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, nit, nombre, calificacion);

			tx.commit();

			log.trace("Inserción de Proveedor con nit: " + nit + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Proveedor(nit, nombre, calificacion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional la tupla de la tabla Proveedor.
	 * @param nit - identificador del proveedor a eliminar.
	 * @return El número de tuplas eliminadas, -1 Si ocurre alguna Exception.
	 */
	public long eliminarProveedorPorNit(String nit) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedor.eliminarProveedorPorNit(pm, nit);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional la tupla de la tabla Proveedor.
	 * @param nombre - nombre del proveedor a eliminar.
	 * @return El número de tuplas eliminadas, -1 Si ocurre alguna Exception.
	 */
	public long eliminarProveedorPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedor.eliminarProveedorPorNit(pm, nombre);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de Proveedores.
	 * @return Una lista de objetos Proveedor, construidos con base en las tuplas de la tabla PROVEEDOR.
	 */
	public List<Proveedor> darProveedores()
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla con un identificador dado.
	 * @param nit - identificador del proveedor.
	 * @return El objeto Proveedor, construido con base en la tabla PROVEEDOR.
	 */
	public Proveedor darProveedorPorNit(String nit)
	{
		return sqlProveedor.darProveedorPorNit(pmf.getPersistenceManager(), nit);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla con un nombre dado.
	 * @param nombre - El nombre del proveedor.
	 * @return El objeto Proveedor, construido con base en la tabla PROVEEDOR.
	 */
	public Proveedor darProveedorPorNombre(String nombre)
	{
		return sqlProveedor.darProveedorPorNombre(pmf.getPersistenceManager(), nombre);
	}
	
	/**
	 * Método que actualiza la calificacion de  todas las tuplas en la tabla con un identificador dado.
	 * @param nit - identificador del proveedor.
	 * @param nuevaCalificaion - nueva calificacion del proveedor.
	 * @return El numero de tuplas modificadas,-1 Si ocurre alguna Exception. .
	 */
	public long updateCalificacionProveedor(String nit, double nuevaCalificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedor.updateCalificacion(pm, nit, nuevaCalificacion);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}	

	// -----------------------------------------------------------------
	// Métodos de tabla proveedoresProducto 
	// -----------------------------------------------------------------

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ProveedoresProducto.
	 * @param proveedor - El proveedor del producto.
	 * @param producto - El producto asociado al proveedor
	 * @return El objeto tipo ProveedoresProducto adicionado. Null si ocurre alguna Exception.
	 */
	public ProveedoresProducto adicionarProveedoresProducto(String proveedor, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedoresProducto.adicionarProveedoresProducto(pm, proveedor, producto);
			tx.commit();

			log.trace("Inserción de asociacion entre proveedor: " + proveedor + "y producto " + producto + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new ProveedoresProducto(proveedor, producto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProveedorProducto.
	 * @param proveedor - El proveedor del producto. Identificador de la clase. 
	 * @param producto - El producto del proveedor. Identificador de la clase.
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarProveedoresProducto(String proveedor, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlProveedoresProducto.eliminarProveedoresProducto(pm, proveedor, producto);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consutla todas las tuplas en la tabla ProveedoresProducto que pertenecen al producto dado.
	 * @param codigoBarrasProducto - Identificador del producto del proveedor.
	 * @return Una lista de objetos ProveedoresProducto, construidos con base en las tuplas de la tabla PROVEEDORESPRODUCTO.
	 */
	public List<ProveedoresProducto> darProveedoresProducto(String codigoBarrasProducto)
	{
		return sqlProveedoresProducto.darProveedoresProducto(pmf.getPersistenceManager(), codigoBarrasProducto);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PROVEEDORESPRODUCTO, que pertenecen a un proveedor.
	 * @param proveedor - El identificador del proveedor de los productos.
	 * @return Una lista de objetos ProveedoresProducto, construidos con base en las tuplas de la tabla PROVEEDORESPRODUCTO.
	 */
	public List<ProveedoresProducto> darProductosProveedor(String proveedor)
	{
		return sqlProveedoresProducto.darProductosProveedor(pmf.getPersistenceManager(), proveedor);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PROVEEDORESPRODUCTO.
	 * @return Una lista de objetos ProveedoresProducto, onstruidos con base en las tuplas de la tabla PROVEEDOR.
	 */
	public List<ProveedoresProducto> darTodosProveedoresProductos()
	{
		return sqlProveedoresProducto.darTodosProveedoresProductos(pmf.getPersistenceManager());
	}

	// -----------------------------------------------------------------
	// Métodos de tabla ordenPedido
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla OrdenPedido.
	 * @param fechaEsperadaEntrega - FechaEsperada de entrega del pedido.
	 * @param proveedor -  El proveedor del pedido.
	 * @param idSucursal - La sucursal que realiza el pedido.
	 * @param estado - El estado del pedido.
	 * @return El objeto OrdenPedido adicionado. Null si ocurre alguna exception.
	 */
	public OrdenPedido adicionarOrdenPedido( Date fechaEsperadaEntrega
			, String proveedor, long idSucursal, String estado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval();
			long tuplasInsertadas = sqlOrdenPedido.adicionarOrdenPedido(pm, id, fechaEsperadaEntrega, proveedor, idSucursal, estado);
			tx.commit();

			log.trace("Inserción de ordenPedido con el id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new OrdenPedido(id, null, fechaEsperadaEntrega, 0, proveedor, idSucursal , estado);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ProveedoresProducto.
	 * @param id - El identificador del pedido a eliminar.
	 * @return Número de tuplas eliminadas. -1 si ocurre alguna Exception.
	 */
	public long eliminarOrdenPedido(long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOrdenPedido.eliminarOrdenPedido(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla OrdenPedido.
	 * @return Una lista de objetos OrdenesPedidos, construidos con base en las tuplas de la tabla OrdenPedido.
	 */
	public List<OrdenPedido> darOrdenesPedidos()
	{
		return sqlOrdenPedido.darOrdenesPedidos(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Ordenpedido, con un identificador dado.
	 * @param id - Identificador del pedido.
	 * @return El objeto OrdenPedido, construido con base en la tabla OrdenPedido.
	 */
	public OrdenPedido darOrdenPedido(long id)
	{
		return sqlOrdenPedido.darOrdenPedido(pmf.getPersistenceManager(), id);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla OrdenPedido, con un proveedor y que estan en estado "En Espera" para cierta sucursal.
	 * @param nit - Nit del proveedor. 
	 * @param idSucursal - Identificador de la sucursal.
	 * @return El objeto OrdenPedido, construido con base en la tabla OrdenPedido.
	 */
	public List<OrdenPedido> darOrdenPedidoEnEsperaPorProveedor(String nit, long idSucursal)
	{
		return sqlOrdenPedido.darOrdenPedidoEnEsperaPorProveedor(pmf.getPersistenceManager(), nit, idSucursal);
	}

	/**
	 * Método que actualiza la fecha de llegada de una tupla en la tabla OrdenPedido, con el identificador dado.
	 * @param id - Identificador de la OrdenPedido.
	 * @param fechaEntrega - La nueva fecha de entrega.
	 * @param nuevaCalificacion - La calificación de la OrdenPedido cálculada por el negocio.
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Exception.
	 */
	public long registrarFechaLlegada(long id, Date fechaEntrega, double nuevaCalificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOrdenPedido.registrarFechaLlegada(pm, id, fechaEntrega, nuevaCalificacion);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de tabla ProductoOrdenPedido
	// -----------------------------------------------------------------

	/**
	 * Método que adiciona, de manera transaccional, una tupla en la tabla ProductoOrdenPedido.
	 * @param pedido - El identificador del producto. 
	 * @param cantidad - Unidades pedidas del producto.
	 * @param calidad - la calidad del pproducto pedido.
	 * @param producto - El identificador del producto.
	 * @param fechaAgregado - Fecha en que se agrego el producto.
	 * @return Objet ProductoOrdenPedido adicionado.Null, si ocurre alguna Exception.
	 */
	public ProductoOrdenPedido adicionarProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto, Date fechaAgregado){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoOrdenPedido.adicionarProductoOrdenPedido(pm, pedido, cantidad, calidad, producto,fechaAgregado);
			tx.commit();

			log.trace("Inserción de asociacion de producto: " + producto + " al pedido: "+ pedido+ " : " + tuplasInsertadas + " tuplas insertadas."); 

			return new ProductoOrdenPedido(pedido, cantidad, calidad, producto, fechaAgregado) ;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoOrdenPedido
	 * @return UNa lista de objetos ProductoOrdenPedido, construidos con base en las tuplas de la tabla ProductoOrdenPedido.
	 */
	public List<ProductoOrdenPedido> darProductosOrdenPedidos()
	{
		return sqlProductoOrdenPedido.darProductosOrdenPedidos(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoOrdenPedido, que pertenecen a una orden de pedido.
	 * @param pedido - Identificador del pedido.
	 * @return Una lista de ProductoOrdenPedido, construidos con base en las tupas de la tabla ProductoOrdenPedido.
	 */
	public List<ProductoOrdenPedido> darProductosDelPedido(long pedido)
	{
		return sqlProductoOrdenPedido.darProductosDelPedido(pmf.getPersistenceManager(), pedido);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoOrdenPedido, que contienen al producto dado.
	 * @param producto - El identificador del producto del que se quieren consultar los pedidos.
	 * @return Una lista de objetos ProductoOrdenPedido, construidos con base en las tuplas de la tabla ProductoOrdenPedido.
	 */
	public List<ProductoOrdenPedido> darHistorialPedidosProducto(String producto)
	{
		return sqlProductoOrdenPedido.darHistorialPedidosProducto(pmf.getPersistenceManager(), producto);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ProductoOrdenPedido, con un identificador dado.
	 * @param pedido - El identificador de la tabla.
	 * @param producto - El identificador de la tabla.
	 * @return El objeto ProductoOrdenPedido, construido con base en la tabla ProductoOrdenPedido.
	 */
	public ProductoOrdenPedido darProductoOrdenPedido (long pedido, String producto)
	{
		return sqlProductoOrdenPedido.darProductoOrdenPedido(pmf.getPersistenceManager(), pedido, producto );
	}


	// -----------------------------------------------------------------
	// Métodos de tabla PromDescuento
	// -----------------------------------------------------------------

	/**@param pFechaInicio fecha en la cual se inicia la promocion
	/**@param fechaFin fecha en la cual se finaliza la promocion
	/**@param producto  producto que esta en promocion
	 * @param tipoProm tipo de promocion 1: PromPagLleveUni , 2: PronDesc , 3: PronSegunUnidDesc, 4 : PromPagueLleveCant
	 * @param   descuento   porcentaje del descuento a  realizar
	 **/
	public PromDesc adicionarPromocionDescuento( String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numeroPromo = nextval ();
			long tuplasInsertadas = sqlPromDescuento.adicionarPromDescuento(pm, numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento); 
			
			tx.commit();

			log.trace ("Inserción de promocion descuento: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new PromDesc(numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);


		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicación
	 * @param id - El numero de la promocion a eliinar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromDesc (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPromDescuento.eliminarPromDescuentoPorId(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta la PromDescuento con el id dado.
	 * @param id - El identificador del PromDescuento
	 * @return el objeto PromDesc con el identificador dado
	 */
	public PromDesc darPromDescuentoPorId(long id)
	{
		return sqlPromDescuento.darPromDescuentoPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de PromDescuento.
	 * @return Una lista de objetos ProDescuento, construidos con base en las tuplas de la tabla PROM_DESCUENTO.
	 */
	public List<PromDesc> darPromDescuento()
	{
		return sqlPromDescuento.darPromDescuento(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES (pague n lleve m unidades)
	 *****************************************************************/

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param pague -  unidades del producto que se debe pagar  
	 **@param lleve - unidades del producto que se llevara 
	 * @return El objeto promPagueLleveUnidad adicionado
	 */
	public PromPagueLleveUnid adicionarPromocionPagueLleveUnid ( String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve  )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numeroPromo = nextval ();
			long tuplasInsertadas = sqlPromPagLlevUnidad.adicionarPromPagueLleveUnid(pm, numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
			
			tx.commit();

			log.trace ("Inserción de promocion pague n lleve m unidades: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new PromPagueLleveUnid(numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);


		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicación
	 * @param id - El numero de la promocion a eliinar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromPagLleveUnidad (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPromPagLlevUnidad.eliminarPromPagLlevUnidadPorId(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta la PromPagueLleveUnid con el id dado.
	 * @param id - El identificador del PromPagueLleveUnid
	 * @return el objeto PromPagueLleveUnid con el identificador dado
	 */
	public PromPagueLleveUnid darPromPagLlevUnidadPorId(long id)
	{
		return sqlPromPagLlevUnidad.darPromPagLlevUnidadPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de PromPagueLleveUnid.
	 * @return Una lista de objetos PromPagueLleveUnid, construidos con base en las tuplas de la tabla PROM_PAGLLEVEUNID.
	 */
	public List<PromPagueLleveUnid> darPromPagueLleveUnid()
	{
		return sqlPromPagLlevUnidad.darPromPagueLleveUnid(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES (DESCUENTO SEGUNDA UNIDAD)
	 *                                  PromDescSegUnidad
	 *****************************************************************/
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param descuento -  porcentaje del descuento a  realizar   
	 * @return El nÃºmero de tuplas insertadas
	 */
	public PromSegUniDesc adicionarPromDescSegUnid( String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numeroPromo = nextval ();
			long tuplasInsertadas = sqlPromDescSegUnid.adicionarPromDescSegUnid(pm,numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
			
			tx.commit();

			log.trace ("Inserción de promocion segunda unidad descuento: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new PromSegUniDesc(numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);


		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}


	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicación
	 * @param id - El numero de la promocion a eliinar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromDescSegUnidPorId(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPromDescSegUnid.eliminarPromDescSegUnidPorId(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta la PromDescSegUnid con el id dado.
	 * @param id - El identificador del PromDescSegUnid
	 * @return el objeto PromDescSegUnid con el identificador dado
	 */
	public PromSegUniDesc darPromDescSegUnidPorId(long id)
	{
		return sqlPromDescSegUnid.darPromDescSegUnidPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de PromPagueLleveUnid.
	 * @return Una lista de objetos PromPagueLleveUnid, construidos con base en las tuplas de la tabla PROM_PAGLLEVEUNID.
	 */
	public List<PromSegUniDesc> darPromDescSegUnid()
	{
		return sqlPromDescSegUnid.darPromDescSegUnid(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES (pague n lleve m cantidad)
	 *                            PromPagLleveCantidad
	 *****************************************************************/

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param pague -  cantidad del producto que se debe pagar  
	 **@param lleve -  cantidad del producto que se llevara 
	 * @return El nÃºmero de tuplas insertadas
	 */
	public PromPagueLleveCant adicionarPromPagueLleveCant( String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numeroPromo = nextval ();
			long tuplasInsertadas = sqlPromPagLleveCatidad.adicionarPromPagueLleveCantidad(pm, numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
			tx.commit();

			log.trace ("Inserción de proveedor: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");

			return new PromPagueLleveCant(numeroPromo, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);   
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}    
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicación
	 * @param id - El numero de la promocion a eliinar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromPagLleveCatidadPorId(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPromPagLleveCatidad.eliminarPromPagLleveCatidadPorId(pm, id);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	} 

	/**
	 * Método que consulta la PromPagueLleveCant con el id dado.
	 * @param id - El identificador del PromPagueLleveCant
	 * @return el objeto PromPagueLleveCant con el identificador dado
	 */
	public PromPagueLleveCant darPromPagueLleveCantPorId(long id)
	{
		return sqlPromPagLleveCatidad.darPromPagueLleveCantPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla de darPromPagueLleveCantPorId.
	 * @return Una lista de objetos darPromPagueLleveCantPorId, construidos con base en las tuplas de la tabla PROM_PAGLLEVECANT.
	 */
	public List<PromPagueLleveCant> darPromPagLleveCatidad()
	{
		return sqlPromPagLleveCatidad.darPromPagLleveCatidad(pmf.getPersistenceManager());
	}


	
}