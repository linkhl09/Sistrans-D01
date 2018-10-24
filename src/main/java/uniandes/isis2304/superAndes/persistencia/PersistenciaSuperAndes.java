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
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta con la base de datos
 * 
 * 
 * Se apoya en las clases SQLTipo, SQLcategoria, SQLproveedor, SQLpromocion, SQLproducto, SQLpersonaNatural,
 * SQLempresa, SQLcliente, SQLfactura, SQLsucursal, SQLordenPedido, SQLbodega, SQLestante, SQLproveedores_producto,
 * SQLproducto_ordenPedido, SQLfactura_producto, SQLcliente_sucursal, SQLproductosEnBodega, SQLProductosEnEstante,
 * SQLSucursalProducto y SQLHistoriaPromociones. 
 * que son las que realizan el acceso a la base de datos
 * 
 * @author Andrés Herández
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


	private SQLTipo sqlTipo ;


	private SQLCategoria sqlCategoria;


	private SQLProveedor sqlProveedor;
	

	private SQLProducto sqlProducto;


	private SQLPersonaNatural sqlPersonaNatural;


	private SQLEmpresa sqlEmpresa;


	private SQLCliente sqlCliente;


	private SQLFactura sqlFactura;


	private SQLSucursal sqlSucursal;


	private SQLOrdenPedido sqlOrdenPedido;


	private SQLBodega sqlBodega; 


	private SQLEstante sqlEstante;


	private SQLProveedoresProducto sqlProveedoresProducto;


	private SQLProductoOrdenPedido sqlProductoOrdenPedido;


	private SQLFacturaProducto sqlFacturaProducto;


	private SQLProductosEnBodega sqlProductosEnBodega;


	private SQLProductosEnEstante sqlProductosEnEstante;


	private SQLSucursalProducto sqlSucursalProducto;


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
		tablas.add("TIPO");
		tablas.add("CATEGORIA");
		tablas.add("PROVEEDOR");
		tablas.add("PROMOCION");
		tablas.add("PRODUCTO");
		tablas.add("PERSONANATURAL");
		tablas.add("EMPRESA");
		tablas.add("CLIENTE");
		tablas.add("FACTURA");
		tablas.add("SUCURSAL");
		tablas.add("ORDENPEDIDO");
		tablas.add("BODEGA");
		tablas.add("ESTANTE");
		tablas.add("PROVEEDORES_PRODUCTO");
		tablas.add("PRODUCTO_ORDENPEDIDO");
		tablas.add("FACTURA_PRODUCTO");
		tablas.add("CLIENTE_SUCURSAL");
		tablas.add("PRODUCTOSENBODEGA");
		tablas.add("PRODUCTOSENESTANTE");
		tablas.add("SUCURSAL_PRODUCTO");
		tablas.add("HISTORIAL_PROMOCIONES");
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
		sqlTipo = new SQLTipo(this);
		sqlCategoria = new SQLCategoria(this);
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
	 * @return La cadena de caracteres con el nombre de la tabla de Tipo de SuperAndes
	 */
	public String darTablaTipo()
	{
		return tablas.get(1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Categoria de SuperAndes
	 */
	public String darTablaCategoria()
	{
		return tablas.get(2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Proveedor de SuperAndes
	 */
	public String darTablaProveedor()
	{
		return tablas.get(3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Promocion de SuperAndes
	 */
	public String darTablaPromocion()
	{
		return tablas.get(4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Producto de SuperAndes
	 */
	public String darTablaProducto()
	{
		return tablas.get(5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de PersonaNatural de SuperAndes
	 */
	public String darTablaPersonaNatural()
	{
		return tablas.get(6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Empresa de SuperAndes
	 */
	public String darTablaEmpresa()
	{
		return tablas.get(7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Cliente de SuperAndes
	 */
	public String darTablaCliente()
	{
		return tablas.get(8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Factura de SuperAndes
	 */
	public String darTablaFactura()
	{
		return tablas.get(9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sucursal de SuperAndes
	 */
	public String darTablaSucursal()
	{
		return tablas.get(10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OrdenPedido de SuperAndes
	 */
	public String darTablaOrdenPedido()
	{
		return tablas.get(11);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bodega de SuperAndes
	 */
	public String darTablaBodega()
	{
		return tablas.get(12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Estante de SuperAndes
	 */
	public String darTablaEstante()
	{
		return tablas.get(13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProveedoresProducto de SuperAndes
	 */
	public String darTablaProveedoresProducto()
	{
		return tablas.get(14);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductoOrdenPedido de SuperAndes
	 */
	public String darTablaProductoOrdenPedido()
	{
		return tablas.get(15);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de FacturaProducto de SuperAndes
	 */
	public String darTablaFacturaProducto()
	{
		return tablas.get(16);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ClienteSucursal de SuperAndes
	 */
	public String darTablaClienteSucursal()
	{
		return tablas.get(17);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosEnBodega de SuperAndes
	 */
	public String darTablaProductosEnBodega()
	{
		return tablas.get(18);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductosEnEstante de SuperAndes
	 */
	public String darTablaProductosEnEstante()
	{
		return tablas.get(19);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de SucursalProducto de SuperAndes
	 */
	public String darTablaSucursalProducto()
	{
		return tablas.get(20);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HistorialPromociones de SuperAndes
	 */
	public String darTablaHistorialPromociones()
	{
		return tablas.get(21);
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
	// Métodos de tabla Tipo
	// -----------------------------------------------------------------

	public Tipo adicionarTipo(String nombre, String categoria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipo.adicionarTipo(pm, nombre, categoria);
			tx.commit();

			log.trace("Inserción de tipo producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Tipo(nombre, categoria);
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


	public Tipo darTipoPorNombre(String nombre)
	{
		return sqlTipo.darTipoPorNombre(pmf.getPersistenceManager(), nombre);
	}

	
	// -----------------------------------------------------------------
	// Métodos de tabla Categoria
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
	// Métodos de tabla Proveedor
	// -----------------------------------------------------------------

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


	public List<Proveedor> darProveedores()
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}


	public Proveedor darProveedor(String nit)
	{
		return sqlProveedor.darProveedor(pmf.getPersistenceManager(), nit);
	}

	
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
	// Métodos de tabla promoción
	// -----------------------------------------------------------------

	
	

	
	// -----------------------------------------------------------------
	// Métodos de tabla producto
	// -----------------------------------------------------------------

	
	public Producto adicionarProducto(String codigoBarras, String nombre, String marca, 
			double precioUnitario, String presentacion, double precioUnidadMedida, int cantidadPresentacion, 
			double peso, String unidadMedidaPeso, double volumen, String unidadMedidaVolumen, double calidad, 
			int nivelReorden, Date fechaVencimiento, String categoria, boolean promocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, 
																cantidadPresentacion, peso, unidadMedidaPeso, volumen, unidadMedidaVolumen, calidad, 
																nivelReorden, fechaVencimiento, categoria, promocion);
			tx.commit();

			log.trace("Inserción del producto con (nombre: " + nombre + " y marca: " + marca + " ): " + tuplasInsertadas + " tuplas insertadas."); 

			return new Producto(codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, peso, unidadMedidaPeso,
								volumen, unidadMedidaVolumen, calidad, nivelReorden, fechaVencimiento, categoria, promocion);
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


	public List<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}


	public Producto darProducto(String codigoBarras)
	{
		return sqlProducto.darProducto(pmf.getPersistenceManager(), codigoBarras);
	}


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
	// Métodos de tabla personaNatural
	// -----------------------------------------------------------------

	
	public PersonaNatural adicionarPersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre, String empresa)
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
	

	public long[] eliminarPersonaNatural(String documento, String tipoDocumento, String correoElectronico) 
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


	public List<PersonaNatural> darPersonasNaturales ()
	{
		return sqlPersonaNatural.darPersonasNaturales(pmf.getPersistenceManager());
	}


	public PersonaNatural darPersonaNatural (String documento, String tipodocumento)
	{
		return sqlPersonaNatural.darPersonaNatural(pmf.getPersistenceManager(), documento);
	}	
	
	
	// -----------------------------------------------------------------
	// Métodos de tabla Empresa
	// -----------------------------------------------------------------


	public Empresa adicionarEmpresa(String nit, String direccion, String correoElectronico, String nombre, String empresa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm, nit, direccion);
			long tuplasInsertadas2 = sqlCliente.adicionarClienteEmpresa(pm, correoElectronico, nombre, empresa);
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
	
	public List<Empresa> darEmpresas()
	{
		return sqlEmpresa.darEmpresas(pmf.getPersistenceManager());
	}
	
	public Empresa darEmpresa(String nit)
	{
		return sqlEmpresa.darEmpresa(pmf.getPersistenceManager(), nit);
	}
	
	// -----------------------------------------------------------------
	// Métodos de tabla Cliente
	// -----------------------------------------------------------------


	public List<Cliente> darClientes()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}


	public Cliente darCliente (String correoElectronico)
	{
		return sqlCliente.darCliente(pmf.getPersistenceManager(), correoElectronico);
	}
	
	
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
	// Métodos de tabla Factura
	// -----------------------------------------------------------------

	
	public Factura adicionarFactura( String direccion, 
			Date fecha, String nombreCajero, double valorTotal, boolean pagoExitoso, 
			int puntosCompra, String correoCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long numero = nextval();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, correoCliente);
			tx.commit();

			log.trace("Inserción de la factura con el numero: " + numero + ": " + tuplasInsertadas + " tuplas insertadas."); 

			return new Factura(numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, correoCliente);
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
	// Métodos de tabla sucursal
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
	// Métodos de tabla ordenPedido
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
	// Métodos de tabla bodega
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
	// Métodos de tabla estante
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

			log.trace("Inserción de: " + id + ": " + tuplasInsertadas + " tuplas insertadas."); 

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
	// Métodos de tabla proveedoresProducto 
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
	// Métodos de tabla ProductoOrdenPedido
	// -----------------------------------------------------------------

	
	public ProductoOrdenPedido adicionarProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoOrdenPedido.adicionarProductoOrdenPedido(pm, pedido, cantidad, calidad, producto);
			tx.commit();

			log.trace("Inserción de asociacion de producto: " + producto + " al pedido: "+ pedido+ " : " + tuplasInsertadas + " tuplas insertadas."); 

			return new ProductoOrdenPedido(pedido, cantidad, calidad, producto) ;
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
	// Métodos de tabla Factura_Prodcuto
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
	// Métodos de tabla productosEnBodega
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
	// Métodos de tabla productosEnEstante
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
	// Métodos de tabla SucursalProducto
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

			log.trace("Inserción de asociacion entre sucursal y producto: " + producto + ": " + tuplasInsertadas + " tuplas insertadas."); 

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
}