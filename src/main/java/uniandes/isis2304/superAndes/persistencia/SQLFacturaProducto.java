package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.FacturaProducto;

class SQLFacturaProducto {

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
	
	public long adicionarFacturaProducto(PersistenceManager pm, long factura, int cantidad, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFacturaProducto() + " (factura, cantidad, producto) VALUES (?, ?, ?)");
		q.setParameters(factura, cantidad, producto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductosDeFactura(PersistenceManager pm, long factura)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFacturaProducto() + " WHERE factura = ?");
		q.setParameters(factura);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductoDeFactura(PersistenceManager pm, long factura, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFacturaProducto() + " WHERE factura = ? AND producto = ?");
		q.setParameters(factura, producto);
		return (long) q.executeUnique();
	}
	

	public FacturaProducto darProductoDeFactura(PersistenceManager pm, long factura, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto() + "WHERE factura = ? AND producto = ?");
		q.setResultClass(FacturaProducto.class);
		q.setParameters(factura, producto);
		return (FacturaProducto) q.executeUnique();
	}
	
	public List<FacturaProducto> darProductosFactura(PersistenceManager pm, long factura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto() + "WHERE factura = ?");
		q.setParameters(factura);
		q.setResultClass(FacturaProducto.class);
		return (List<FacturaProducto>) q.executeList();
	}
	
	public List<FacturaProducto> darProductosFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFacturaProducto());
		q.setResultClass(FacturaProducto.class);
		return (List<FacturaProducto>) q.executeList();
	}
}
