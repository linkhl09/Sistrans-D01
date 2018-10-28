package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.FacturaProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto FACTURA PRODUCTO. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
class SQLFacturaProducto 
{

	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
		
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes psa;
	
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor
	 * @param psa - El Manejador de persistencia de la aplicación
	 */
	public SQLFacturaProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Adiciona una nueva factura-producto a a la tabla con toda la informacion necesaria
	 * @param factura - numero de la factura ,identificador unico de la factura
	 * @param cantidad - cantidada del producto
	 * @param producto - codigo de producto , identificador unico del producto
	 */
	public long adicionarFacturaProducto(PersistenceManager pm, long factura, int cantidad, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFacturaProducto() + " (factura, cantidad, producto) VALUES (?, ?, ?)");
		q.setParameters(factura, cantidad, producto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Elimina las factura-producto cuyo numero de factura es el ingresado por parametro
	 * @param factura - numero de la factura ,identificador unico de la factura
	 */
	public long eliminarProductosDeFactura(PersistenceManager pm, long factura)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFacturaProducto() + " WHERE factura = ?");
		q.setParameters(factura);
		return (long) q.executeUnique();
	}
	
	/**
	 * Eliminia las factura-producto cuyo numero de factura y codigo de producto son los ingresados por parametro
	 * @param factura - numero de la factura ,identificador unico de la factura
	 * @param producto - codigo de producto , identificador unico del producto
	 */
	public long eliminarProductoDeFactura(PersistenceManager pm, long factura, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFacturaProducto() + " WHERE factura = ? AND producto = ?");
		q.setParameters(factura, producto);
		return (long) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion de la  factura-producto cuyo numero de factura y codigo de producto es igual al ingresado por parametro
	 * @param factura - numero de la factura ,identificador unico de la factura
	 * @param producto - codigo de producto , identificador unico del producto
	 */
	public FacturaProducto darProductoDeFactura(PersistenceManager pm, long factura, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto() + "WHERE factura = ? AND producto = ?");
		q.setResultClass(FacturaProducto.class);
		q.setParameters(factura, producto);
		return (FacturaProducto) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion de todos los productos de una factura
	 */
	public List<FacturaProducto> darProductosFactura(PersistenceManager pm, long factura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto() + "WHERE factura = ?");
		q.setParameters(factura);
		q.setResultClass(FacturaProducto.class);
		return (List<FacturaProducto>) q.executeList();
	}
	
	/**
	 * devuelve todos los datos en al tabla factura-producto
	 */
	public List<FacturaProducto> darProductosFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto());
		q.setResultClass(FacturaProducto.class);
		return (List<FacturaProducto>) q.executeList();
	}
}
