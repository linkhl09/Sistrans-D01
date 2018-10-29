package uniandes.isis2304.superAndes.persistencia;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.*;

/**
 * Clase para el manejador de persistencia del proyecto SuperAndes
 * Traduce la informaci�n entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patr�n SINGLETON (S�lo puede haber UN objeto de esta clase) para comunicarse de manera correcta con la base de datos
 * 
 * 
 * Se apoya en las clases SQLTipo, SQLcategoria, SQLproveedor, SQLpromocion, SQLproducto, SQLpersonaNatural,
 * SQLempresa, SQLcliente, SQLfactura, SQLsucursal, SQLordenPedido, SQLbodega, SQLestante, SQLproveedores_producto,
 * SQLproducto_ordenPedido, SQLfactura_producto, SQLcliente_sucursal, SQLproductosEnBodega, SQLProductosEnEstante,
 * SQLSucursalProducto y SQLHistoriaPromociones. 
 * que son las que realizan el acceso a la base de datos
 * 
 * @author Andr�s Her�ndez
 */
public class PersistenciaSuperAndes {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Logger para escribir la traza de la ejecuci�n
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
	 * Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON
	 */
	private static PersistenciaSuperAndes instance;

	/**
	 * F�brica de Manejadores de persistencia, para el manejo correcto de las transacciones
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
	 * Atributo para el acceso a la tabla "Tipo" de la base de datos
	 */
	private SQLTipo sqlTipo ;

	/**
	 * Atributo para el acceso a la tabla "Categoria" de la base de datos
	 */
	private SQLCategoria sqlCategoria;

	/**
	 * Atributo para el acceso a la tabla "Proveedor" de la base de datos
	 */
	private SQLProveedor sqlProveedor;
	
	/**
	 * Atributo para el acceso a la tabla "TipoCategoria" de la base de datos
	 */
	private SQLTipoCategoria sqlTipoCategoria;
	
	/**
	 * Atributo para el acceso a la tabla "Producto" de la base de datos
	 */
	private SQLProducto sqlProducto;

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
	 * Atributo para el acceso a la tabla "Factura" de la base de datos
	 */
	private SQLFactura sqlFactura;

	/**
	 * Atributo para el acceso a la tabla "Sucursal" de la base de datos
	 */
	private SQLSucursal sqlSucursal;

	/**
	 * Atributo para el acceso a la tabla "OrdenPedido" de la base de datos
	 */
	private SQLOrdenPedido sqlOrdenPedido;

	/**
	 * Atributo para el acceso a la tabla "Bodega" de la base de datos
	 */
	private SQLBodega sqlBodega; 

	/**
	 * Atributo para el acceso a la tabla "Estante" de la base de datos
	 */
	private SQLEstante sqlEstante;

	/**
	 * Atributo para el acceso a la tabla "ProveedoresProducto" de la base de datos
	 */
	private SQLProveedoresProducto sqlProveedoresProducto;

	/**
	 * Atributo para el acceso a la tabla "ProductoOrdenPedido" de la base de datos
	 */
	private SQLProductoOrdenPedido sqlProductoOrdenPedido;

	/**
	 * Atributo para el acceso a la tabla "FacturaProducto" de la base de datos
	 */
	private SQLFacturaProducto sqlFacturaProducto;

	/**
	 * Atributo para el acceso a la tabla "ProductosEnBodega" de la base de datos
	 */
	private SQLProductosEnBodega sqlProductosEnBodega;

	/**
	 * Atributo para el acceso a la tabla "ProductosEnEstante" de la base de datos
	 */
	private SQLProductosEnEstante sqlProductosEnEstante;

	/**
	 * Atributo para el acceso a la tabla "SucursalProducto" de la base de datos
	 */
	private SQLSucursalProducto sqlSucursalProducto;


	private SQLUtil sqlUtil;

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

	// -----------------------------------------------------------------
	// M�todos Manejador de persistencia.
	// -----------------------------------------------------------------

	/**
	 * Constructor privado con los valores por defecto - Patr�n SINGLETON
	 */
	private PersistenciaSuperAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		
		//TODO Completar despu�s de inicializar las tablas.
		
		tablas = new LinkedList<String>();
	    tablas.add("superAndes_sequence");
        tablas.add("CATEGORIA");
    	tablas.add("TIPO");
    	tablas.add("TIPO_CATEGORIA");
     	tablas.add("PRODUCTO");
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
		tablas.add("PROM_DESCUENTO");
		tablas.add("PROM_PAG_LLEVE_UNID");
		tablas.add("PROM_DESC_SEG_UNIDAD");
		tablas.add("PROM_PAG_LLEVE_CANT");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr�n SINGLETON
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
	 * @return Retorna el �nico objeto PersistenciaSuperAndes existente - Patr�n SINGLETON
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
	 * @return Retorna el �nico objeto PersistenciaSuperAndes existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance(JsonObject tableConfig)
	{
		if (instance == null)
			instance = new PersistenciaSuperAndes(tableConfig);
		return instance;
	}

	/**
	 * Cierra la conexi�n con la base de datos
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
		// TODO revisar que esten todas las clases SQL inicializadas.
		sqlTipo = new SQLTipo(this);
		sqlCategoria = new SQLCategoria(this);
		sqlTipoCategoria = new SQLTipoCategoria(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProducto = new SQLProducto(this);
		sqlPersonaNatural = new SQLPersonaNatural(this);
		sqlEmpresa = new SQLEmpresa(this);
		sqlCliente = new SQLCliente(this);
		sqlFactura = new SQLFactura(this);
		sqlSucursal = new SQLSucursal(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlBodega = new SQLBodega(this); 
		sqlEstante = new SQLEstante(this);
		sqlProveedoresProducto = new SQLProveedoresProducto(this);
		sqlProductoOrdenPedido = new SQLProductoOrdenPedido(this);
		sqlFacturaProducto = new SQLFacturaProducto(this);
		sqlProductosEnBodega = new SQLProductosEnBodega(this);
		sqlProductosEnEstante = new SQLProductosEnEstante(this);
		sqlSucursalProducto  = new SQLSucursalProducto(this);
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
	 * @return La cadena de caracteres con el nombre de la tabla de TipoCategoria de SuperAndes.
	 */
	public String darTablaTipoCategoria() 
	{
		// TODO Auto-generated method stub
		return tablas.get(3);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Producto de SuperAndes
	 */
	public String darTablaProducto()
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
	public String darTablaPromDescuento ()
	{
		return tablas.get (22);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromPagLleveUnida de parranderos
	 */
	public String darTablaPromPagLleveUnida ()
	{
		return tablas.get (23);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PromDescSegUnid de parranderos
	 */
	public String darTablaPromDescSegUnid ()
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
	 * Transacci�n para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de SuperAndes
	 */
	private long nextval()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
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
	 * @return Un arreglo con 21 n�meros que indican el n�mero de tuplas borradas en las tablas ProveedoresProducto, ProductoOrdenPedido, FacturaProducto,
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
			log.info ("Borrada la base de datos");
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
	// M�todos de tabla Tipo
	// -----------------------------------------------------------------

	
	public Tipo adicionarTipo(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipo.adicionarTipo(pm, nombre);
			tx.commit();

			log.trace("Inserci�n de tipo producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<Tipo> darTipos()
	{
		return sqlTipo.darTipos(pmf.getPersistenceManager());
	}

	
	public Tipo darTipo(String nombre)
	{
		return sqlTipo.darTipo(pmf.getPersistenceManager(), nombre);
	}

	
	// -----------------------------------------------------------------
	// M�todos de tabla Categoria
	// -----------------------------------------------------------------

	
	public Categoria adicionarCategoria(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.adicionarCategoria(pm, nombre);
			tx.commit();

			log.trace("Inserci�n de Categoria: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

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

	
	public List<Categoria> darCategorias()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}

	public Categoria darCategoria(String nombre)
	{
		return sqlCategoria.darCategoria(pmf.getPersistenceManager(), nombre);
	}


	// -----------------------------------------------------------------
	// M�todos de tabla Proveedor
	// -----------------------------------------------------------------


	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla Proveedor.
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

			log.trace("Inserci�n de Proveedor con nit: " + nit + ": " + tuplasInsertadas + " tuplas insertadas."); 

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
	 * M�todo que elimina, de manera transaccional la tupla de la tabla Proveedor.
	 * @param nit - identificador del proveedor a eliminar.
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
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
	 * M�todo que elimina, de manera transaccional la tupla de la tabla Proveedor.
	 * @param nombre - nombre del proveedor a eliminar.
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
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
	 * M�todo que consulta todas las tuplas en la tabla de Proveedores.
	 * @return Una lista de objetos Proveedor, construidos con base en las tuplas de la tabla PROVEEDOR.
	 */
	public List<Proveedor> darProveedores()
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}


	/**
	 * M�todo que consulta todas las tuplas en la tabla con un identificador dado.
	 * @param nit - identificador del proveedor.
	 * @return El objeto Proveedor, construido con base en la tabla PROVEEDOR.
	 */
	public Proveedor darProveedor(String nit)
	{
		return sqlProveedor.darProveedor(pmf.getPersistenceManager(), nit);
	}

	/**
	 * M�todo que actualiza la calificacion de  todas las tuplas en la tabla con un identificador dado.
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
	// M�todos de tabla promoci�n
	// -----------------------------------------------------------------

	
	

	
	// -----------------------------------------------------------------
	// M�todos de tabla producto
	// -----------------------------------------------------------------

	/**
	 * M�todo que inserta, de manera transaccional, una tupla de Producto.
	 * Adiciona entradas al log de la aplicaci�n.
	 * @param codigoBarras - Codigo de barras del producto.
	 * @param nombre - Nombre del producto.
	 * @param marca - Marca del producto.
	 * @param precioUnitario - Precio unitario del producto.
	 * @param presentacion - Presentaci�n del producto.
	 * @param precioUnidadMedida - Precio por unidad de medida del producto.
	 * @param cantidadPresentacion - Cantidad en la presentaci�n del producto.
	 * @param peso - Valor n�merico del peso del producto.
	 * @param unidadMedidaPeso - Unidad de medida del peso del producto.
	 * @param volumen - Valor n�merico del volumen del producto.
	 * @param unidadMedidaVolumen - Unidad de medida del volumen del producto.
	 * @param calidad - Calidad del producto.
	 * @param nivelReorden - Nivel de reorden del producto. 
	 * @param fechaVencimiento - Fecha de vencimiento del producto, null si no es un producto perecedero.
	 * @param categoria - Categoria del prodcuto
	 * @param estaEnPromocion - Booleano que indica si el producto esta en promoci�n.
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

			log.trace("Inserci�n del producto con (nombre: " + nombre + " y marca: " + marca + " ): " + tuplasInsertadas + " tuplas insertadas."); 

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
<<<<<<< HEAD
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla PRODUCTO, dado el identificador del producto.
	 * Adiciona entradas al log de la aplicaci�n.
	 * @param codigoBarras - codigo de barras del producto.
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Exception.
=======
	 * M�todo que elimina, de manera transaccional la tupla de la tabla Producto.
	 * @param codigoBarras - codigo de barras del producto a eliminar.
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
>>>>>>> 6bad8a60121a55937f258bc2c898b189635e1684
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
<<<<<<< HEAD
	 * M�todo que consulta todas las tupas de la tabla PRODCUTO.
	 * @return La lista de objetos Producto, construidos con base a las tuplas de la tabla PRODUCTO.
=======
	 * M�todo que consulta todas las tuplas en la tabla de Producto.
	 * @return Una lista de objetos Producto, construidos con base en las tuplas de la tabla PRODUCTO.
>>>>>>> 6bad8a60121a55937f258bc2c898b189635e1684
	 */
	public List<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}


	/**
<<<<<<< HEAD
	 * M�todo que consulta todas las tuplas en la tabla PRODUCTO con el identificador dado.
	 * @param codigoBarras - C�digo de barras del producto buscado.
	 * @return El objeto TipoProducto construido con base en las tuplas de la tabla PRODUCTO.
=======
	 * M�todo que consulta todas las tuplas en la tabla con un codigo de barras dado.
	 * @param codigoBarras - codigo de barras del producto.
	 * @return El objeto Producto, construido con base en la tabla PRODUCTO.
>>>>>>> 6bad8a60121a55937f258bc2c898b189635e1684
	 */
	public Producto darProducto(String codigoBarras)
	{
		return sqlProducto.darProducto(pmf.getPersistenceManager(), codigoBarras);
	}



	/**
	 * M�todo que cambia el valor booleano de estaEnPromoci�n de un producto dado a verdadero.
	 * @param codigoBarras - Identificador del producto al que se le creo una promoci�n.
	 * @return El n�mero de tuplas actualizadas. -1 Si ocurre alguna Exception.
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
	 * M�todo que cambia el valor booleano de Esta en promoci�n de un producto dado a falso.
	 * @param codigoBarras - Identificador del producto al que se le quito la promoci�n.
	 * @return El n�mero de tuplas actualizadas. -1 si ocurre alguna Exception.
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
	// M�todos de tabla personaNatural
	// -----------------------------------------------------------------


	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla PersonaNatural.
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

			log.trace("Inserci�n de la persona natural con documento: " + documento + ": " + tuplasInsertadas + " tuplas insertadas.");
			log.trace("Inserci�n del cliente con correoElectronico: " + correoElectronico + ": " + tuplasInsertadas2 + " tuplas insertadas."); 

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
	 * M�todo que elimina, de manera transaccional la tupla de la tabla PersonaNatural y Cliente.
	 * @param documento - numero de identificacion de la persona natural a eliminar.
	 * @param correoElectronico - correo electronico de la persona natural a eliminar.
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
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
	 * M�todo que consulta todas las tuplas en la tabla de PersonaNatural.
	 * @return Una lista de objetos PersonaNatural, construidos con base en las tuplas de la tabla PERSONA_NATURAL.
	 */
	public List<PersonaNatural> darPersonasNaturales ()
	{
		return sqlPersonaNatural.darPersonasNaturales(pmf.getPersistenceManager());
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla con un numero de documento dado.
	 * @param documento - numero de identificaciom de la  persona.
	 * @return El objeto PersonaNatural, construido con base en la tabla PERSONA_NATURAL.
	 */
	public PersonaNatural darPersonaNatural (String documento)
	{
		return sqlPersonaNatural.darPersonaNatural(pmf.getPersistenceManager(), documento);
	}	
	
	
	// -----------------------------------------------------------------
	// M�todos de tabla Empresa
	// -----------------------------------------------------------------

	/**
	 * M�todo que inserta, de manera transaccional, una tupla en la tabla Empresa.
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

			log.trace("Inserci�n de: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 
			log.trace("Inserci�n del cliente con correoElectronico: " + correoElectronico + ": " + tuplasInsertadas2 + " tuplas insertadas.");
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
	 * M�todo que elimina, de manera transaccional la tupla de la empresa y Cliente.
	 * @param nit - numero de identificacion de la empresa a eliminar
	 * @param correoElectronico - correo electronico de la persona, es la llave de la tabla cliente
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
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
	 * M�todo que elimina, de manera transaccional la tupla de la tabla Empresa y Cliente.
	 * @param direccion - direccion de la empresa a eliminar.
	 * @param correoElectronico - correo electronico de la empresa a eliminar.
	 * @return El n�mero de tuplas eliminadas, -1 Si ocurre alguna Exception.
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
	 * M�todo que consulta todas las tuplas en la tabla de Empresas.
	 * @return Una lista de objetos Empresa, construidos con base en las tuplas de la tabla EMPRESA.
	 */
	public List<Empresa> darEmpresas()
	{
		return sqlEmpresa.darEmpresas(pmf.getPersistenceManager());
	}
	
	/**
	 * M�todo que consulta todas las tuplas en la tabla con un numero de identificacion "nit" dado.
	 * @param nit - numero de identificaciom de la  empresa.
	 * @return El objeto Empresa, construido con base en la tabla EMPRESA.
	 */
	public Empresa darEmpresa(String nit)
	{
		return sqlEmpresa.darEmpresa(pmf.getPersistenceManager(), nit);
	}
	
	// -----------------------------------------------------------------
	// M�todos de tabla Cliente
	// -----------------------------------------------------------------

	/**
	 * M�todo que consulta todas las tuplas en la tabla de Cliente.
	 * @return Una lista de objetos Cliente, construidos con base en las tuplas de la tabla CLIENTE	.
	 */
	public List<Cliente> darClientes()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}

	/**
	 * M�todo que consulta todas las tuplas en la tabla con un correo electronico dado.
	 * @param correoElectronco - correo electronico del cleinte.
	 * @return El objeto Cliente, construido con base en la tabla CLIENTE.
	 */
	public Cliente darCliente (String correoElectronico)
	{
		return sqlCliente.darCliente(pmf.getPersistenceManager(), correoElectronico);
	}
	
	/**
	 * M�todo que modifica todas las tuplas en la tabla con un correo electronico dado aumentado la
	 * cantidad de puntos .
	 * @param correoElectronco - correo electronico del cliente.
	 * @param puntos - puntos a agregar.
	 * @return El n�mero de tuplas modificadas, -1 Si ocurre alguna Exception.
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
	// M�todos de tabla Factura
	// -----------------------------------------------------------------


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

			log.trace("Inserci�n de la factura con el numero: " + numero + ": " + tuplasInsertadas + " tuplas insertadas."); 

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

	public List<Factura> darFacturas()
	{
		return sqlFactura.darFacturas(pmf.getPersistenceManager());
	}

	
	public Factura darFactura(long numero)
	{
		return sqlFactura.darFactura(pmf.getPersistenceManager(), numero);
	}
	
	
	// -----------------------------------------------------------------
	// M�todos de tabla sucursal
	// -----------------------------------------------------------------

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

			log.trace("Inserci�n de la sucursal con nombre: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

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

	
	public List<Sucursal> darSucursales()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}
	

	public Sucursal darSucursalPorNombre(String nombre)
	{
		return sqlSucursal.darSucursalPorNombre(pmf.getPersistenceManager(), nombre);
	}
	
	
	public Sucursal darSucursalPorId(long id)
	{
		return sqlSucursal.darSucursalPorId(pmf.getPersistenceManager(), id);
	}
	

	// -----------------------------------------------------------------
	// M�todos de tabla ordenPedido
	// -----------------------------------------------------------------

	
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

			log.trace("Inserci�n de ordenPedido con el id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<OrdenPedido> darOrdenesPedidos()
	{
		return sqlOrdenPedido.darOrdenesPedidos(pmf.getPersistenceManager());
	}


	public OrdenPedido darOrdenPedido(long id)
	{
		return sqlOrdenPedido.darOrdenPedido(pmf.getPersistenceManager(), id);
	}
	
	
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
	// M�todos de tabla bodega
	// -----------------------------------------------------------------

	
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

			log.trace("Inserci�n de la bodega con id: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<Bodega> darBodegas()
	{
		return sqlBodega.darBodegas(pmf.getPersistenceManager());
	}


	public List<Bodega> darBodegasSucursal(long idSucursal)
	{
		return sqlBodega.darBodegasSucursal(pmf.getPersistenceManager(), idSucursal);
	}
	
	
	public Bodega darBodega(long id)
	{
		return sqlBodega.darBodega(pmf.getPersistenceManager(), id);
	}
	

	// -----------------------------------------------------------------
	// M�todos de tabla estante
	// -----------------------------------------------------------------

	
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

			log.trace("Inserci�n de: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<Estante> darEstantes ()
	{
		return sqlEstante.darEstantes(pmf.getPersistenceManager());
	}

	
	public List<Estante> darEstantesPorSucursal(long idSucursal)
	{
		return sqlEstante.darEstantesSucursal(pmf.getPersistenceManager(), idSucursal);
	}
	
	
	public Estante darEstante(long id)
	{
		return sqlEstante.darEstante(pmf.getPersistenceManager(), id );
	}
	

	// -----------------------------------------------------------------
	// M�todos de tabla proveedoresProducto 
	// -----------------------------------------------------------------

	
	public ProveedoresProducto adicionarProveedoresProducto(String proveedor, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedoresProducto.adicionarProveedoresProducto(pm, proveedor, producto);
			tx.commit();

			log.trace("Inserci�n de asociacion entre proveedor: " + proveedor + "y producto " + producto + ": " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<ProveedoresProducto> darProveedoresProducto(String producto)
	{
		return sqlProveedoresProducto.darProveedoresProducto(pmf.getPersistenceManager(), producto);
	}


	public List<ProveedoresProducto> darProductosProveedor(String proveedor)
	{
		return sqlProveedoresProducto.darProductosProveedor(pmf.getPersistenceManager(), proveedor);
	}
	
	
	public List<ProveedoresProducto> darTodosProveedoresProductos()
	{
		return sqlProveedoresProducto.darTodosProveedoresProductos(pmf.getPersistenceManager());
	}
	

	// -----------------------------------------------------------------
	// M�todos de tabla ProductoOrdenPedido
	// -----------------------------------------------------------------

	
	public ProductoOrdenPedido adicionarProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto, Date fechaAgregado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoOrdenPedido.adicionarProductoOrdenPedido(pm, pedido, cantidad, calidad, producto, fechaAgregado);
			tx.commit();

			log.trace("Inserci�n de asociacion de producto: " + producto + " al pedido: "+ pedido+ " : " + tuplasInsertadas + " tuplas insertadas."); 

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

	
	public List<ProductoOrdenPedido> darProductosOrdenPedidos()
	{
		return sqlProductoOrdenPedido.darProductosOrdenPedidos(pmf.getPersistenceManager());
	}


	public List<ProductoOrdenPedido> dardarProductosDelPedido(long pedido)
	{
		return sqlProductoOrdenPedido.darProductosDelPedido(pmf.getPersistenceManager(), pedido);
	}
	
	
	public List<ProductoOrdenPedido> darHistorialPedidosProducto(String producto)
	{
		return sqlProductoOrdenPedido.darHistorialPedidosProducto(pmf.getPersistenceManager(), producto);
	}
	
	
	public ProductoOrdenPedido darProductoOrdenPedido (long pedido, String producto)
	{
		return sqlProductoOrdenPedido.darProductoOrdenPedido(pmf.getPersistenceManager(), pedido, producto );
	}
	

	// -----------------------------------------------------------------
	// M�todos de tabla Factura_Prodcuto
	// -----------------------------------------------------------------

	
	public FacturaProducto adicionarFacturaProducto(long factura, int cantidad, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlFacturaProducto.adicionarFacturaProducto(pm, factura, cantidad, producto);
			tx.commit();

			log.trace("Inserci�n de producto: " + producto + "en la factura: " + factura + " : " + tuplasInsertadas + " tuplas insertadas."); 

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


	public long eliminarProductosDeFactura(long factura) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFacturaProducto.eliminarProductosDeFactura(pm, factura);
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
	
		
	public List<FacturaProducto> darProductosFactura(long factura)
	{
		return sqlFacturaProducto.darProductosFactura(pmf.getPersistenceManager() , factura);
	}

	public List<FacturaProducto> darProductosFacturas()
	{
		return sqlFacturaProducto.darProductosFacturas(pmf.getPersistenceManager());
	}

	public FacturaProducto darProductoDeFactura( long factura, String producto)
	{
		return sqlFacturaProducto.darProductoDeFactura(pmf.getPersistenceManager(), factura, producto );
	}
	
	// -----------------------------------------------------------------
	// M�todos de tabla productosEnBodega
	// -----------------------------------------------------------------

	
	public ProductosEnBodega adicionarProductosEnBodega(long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosEnBodega.adicionarProductosEnBodega(pm, idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);
			tx.commit();

			log.trace("Inserci�n de producto: " + codigoBarrasProducto + "en la bodega: " + idBodega + " : " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<ProductosEnBodega> darProductosEnBodega(long idBodega)
	{
		return sqlProductosEnBodega.darProductosEnBodega(pmf.getPersistenceManager(), idBodega);
	}

	
	public List<ProductosEnBodega> darTodosProductosBodegas()
	{
		return sqlProductosEnBodega.darTodosProductosBodegas(pmf.getPersistenceManager());
	}
	
	
	public List<ProductosEnBodega> darBodegasProducto(String codigoBarrasProducto)
	{
		return sqlProductosEnBodega.darBodegasProducto(pmf.getPersistenceManager(), codigoBarrasProducto);
	}
	
	
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
	
	
	public long disminuirProductosEnBodega(long bodega, String producto, int pasadosAEstante )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductosEnBodega.disminuirProductosEnBodega(pm, bodega, producto, pasadosAEstante);
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
	// M�todos de tabla productosEnEstante
	// -----------------------------------------------------------------

	
	public ProductosEnEstante adicionarProductosEnEstante(long idEstante, int cantidad, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosEnEstante.adicionarProductosEnEstante(pm, idEstante, cantidad, codigoBarrasProducto);
			tx.commit();

			log.trace("Inserci�n de producto : " + codigoBarrasProducto + " Al estante: "+ idEstante + " : " + tuplasInsertadas + " tuplas insertadas."); 

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


	public List<ProductosEnEstante> darProductosEnEstante(long idEstante)
	{
		return sqlProductosEnEstante.darProductosEnEstante(pmf.getPersistenceManager(), idEstante);
	}


	public ProductosEnEstante darProductoEnEstante (long idEstante,  String codigoBarrasProducto) 
	{
		return sqlProductosEnEstante.darProductoEnEstante(pmf.getPersistenceManager(), idEstante, codigoBarrasProducto);
	}
	
	
	public List<ProductosEnEstante> darTodosProductosEnEstantes()
	{
		return sqlProductosEnEstante.darTodosProductosEnEstantes(pmf.getPersistenceManager());
	}


	public long traerDeBodega(long idEstante, int productosTraidos, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductosEnEstante.traerDeBodega(pm, idEstante, productosTraidos, codigoBarrasProducto);
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
	
	
	public long quitarProductosEstante(long idEstante, int productosVendidos, String codigoBarrasProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductosEnEstante.quitarProductosEstante(pm, idEstante, productosVendidos, codigoBarrasProducto);
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
	// M�todos de tabla SucursalProducto
	// -----------------------------------------------------------------

	
	public SucursalProducto adicionarSucursalProducto(long idSucursal, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursalProducto.adicionarSucursalProducto(pm, idSucursal, producto);
			tx.commit();

			log.trace("Inserci�n de asociacion entre sucursal y producto: " + producto + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new SucursalProducto(idSucursal, producto);
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


	public long eliminarSucursalProducto(long idSucursal, String producto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlSucursalProducto.eliminarSucursalProducto(pm, idSucursal, producto);
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


	public List<SucursalProducto> darProductosSucursal(long idSucursal)
	{
		return sqlSucursalProducto.darProductosSucursal(pmf.getPersistenceManager(),idSucursal);
	}

	
	public List<SucursalProducto> darSucursalesProducto(String producto )
	{
		return sqlSucursalProducto.darSucursalesProducto(pmf.getPersistenceManager(), producto);
	}
	

	public List<SucursalProducto> darTodosProductosSucursales()
	{
		return sqlSucursalProducto.darTodosProductosSucursales(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			M�todos para manejar las PROMOCIONES (DESCUENTO)
	 *****************************************************************/
	/**@param pFechaInicio fecha en la cual se inicia la promocion
	/**@param fechaFin fecha en la cual se finaliza la promocion
	/**@param producto  producto que esta en promocion
	 * @param tipoProm tipo de promocion 1: PromPagLleveUni , 2: PronDesc , 3: PronSegunUnidDesc, 4 : PromPagueLleveCant
	 *   @param   descuento   porcentaje del descuento a  realizar
    **/
	
	public PromDesc adicionarPromocionDescuento(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long numeroPromo = nextval ();
            long tuplasInsertadas = sqlPromDescuento.adicionarPromDescuento(pm, id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento); 
            tx.commit();
            
            log.trace ("Inserci�n de promocion descuento: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromDesc(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
            
            
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
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicaci�n
	 * @param id - El numero de la promocion a eliinar
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
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


	/* ****************************************************************
	 * 			M�todos para manejar las PROMOCIONES (pague n lleve m unidades)
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
	
	public PromPagueLleveUnid adicionarPromocionPagueLleveUnid (long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
	, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve  )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long numeroPromo = nextval ();
            long tuplasInsertadas = sqlPromPagLlevUnidad.adicionarPromPagueLleveUnid(pm, id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
            tx.commit();
            
            log.trace ("Inserci�n de promocion pague n lleve m unidades: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromPagueLleveUnid(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
            
            
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
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicaci�n
	 * @param id - El numero de la promocion a eliinar
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarPromPagueLleveUnidad (long id) 
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


	
	/* ****************************************************************
	 * 			M�todos para manejar las PROMOCIONES (DESCUENTO SEGUNDA UNIDAD)
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
	 * @return El número de tuplas insertadas
	 */
	
	public PromSegUniDesc adicionarPromocionSegUnidDesc(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long numeroPromo = nextval ();
            long tuplasInsertadas = sqlPromDescSegUnid.adicionarPromDescSegUnid(pm, id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
            		
            tx.commit();
            
            log.trace ("Inserci�n de promocion segunda unidad descuento: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromSegUniDesc(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
            
            
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
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicaci�n
	 * @param id - El numero de la promocion a eliinar
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarPromSegUniDesc(long id) 
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


	
	/* ****************************************************************
	 * 			M�todos para manejar las PROMOCIONES (pague n lleve m cantidad)
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
	 * @return El número de tuplas insertadas
	 */
	
	public PromPagueLleveCant adicionarPromocionPagueLleveCant(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long numeroPromo = nextval ();
            long tuplasInsertadas = sqlPromPagLleveCatidad.adicionarPromPagueLleveCantidad(pm, id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
            tx.commit();
            
            log.trace ("Inserci�n de proveedor: " + numeroPromo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PromPagueLleveCant(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
            		
            
            
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
	 * M�todo que elimina, de manera transaccional, una tupla en la tabla PromDesc, dado el numero de la promocion
	 * Adiciona entradas al log de la aplicaci�n
	 * @param id - El numero de la promocion a eliinar
	 * @return El n�mero de tuplas eliminadas. -1 si ocurre alguna Excepci�n
	 */
	public long eliminarPromPagueLleveCant(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPromPagLleveCatidad.eliminarPromPagLleveCatidadPornumeroPromo(pm, id);
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
}
	

