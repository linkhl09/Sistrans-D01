package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProveedoresProducto;

class SQLProveedoresProducto {

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
	public SQLProveedoresProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarProveedoresProducto(PersistenceManager pm, String proveedor, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedoresProducto() + " (proveedor, producto) VALUES (?, ?)");
		q.setParameters(proveedor, producto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProveedoresProducto(PersistenceManager pm, String proveedor, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresProducto() + " WHERE proveedor = ? AND producto = ?");
		q.setParameters(proveedor, producto);
		return (long) q.executeUnique();
	}
	

	public List<ProveedoresProducto> darProveedoresProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedoresProducto() + " WHERE producto = ?");
		q.setResultClass(ProveedoresProducto.class);
		q.setParameters(producto);
		return (List<ProveedoresProducto>) q.executeUnique();
	}
	
	public List<ProveedoresProducto> darProductosProveedor(PersistenceManager pm, String proveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedoresProducto() + " WHERE proveedor = ?");
		q.setResultClass(ProveedoresProducto.class);
		q.setParameters(proveedor);
		return (List<ProveedoresProducto>) q.executeUnique();
	}
	
	public List<ProveedoresProducto> darTodosProveedoresProductos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedoresProducto());
		q.setResultClass(ProveedoresProducto.class);
		return (List<ProveedoresProducto>) q.executeList();
	}
}