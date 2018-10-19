package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.SucursalProducto;

class SQLSucursalProducto {

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
	public SQLSucursalProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarSucursalProducto(PersistenceManager pm, String direccionSucursal, String ciudadSucursal, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursalProducto() + " (direccionSucursal, ciudadsucursal, prodcuto) VALUES (?, ?, ?)");
		q.setParameters(direccionSucursal, ciudadSucursal, producto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarSucursalProducto(PersistenceManager pm, String direccionSucursal, String ciudadSucursal, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursalProducto() + " WHERE direccionSucursal = ? AND ciudadSucursal = ? AND producto = ?");
		q.setParameters(direccionSucursal, ciudadSucursal, producto);
		return (long) q.executeUnique();
	}


	public List<SucursalProducto> darProductosSucursal(PersistenceManager pm, String direccionSucursal, String ciudadSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto() + " WHERE direccionSucursal = ? AND ciudadSucursal = ?");
		q.setParameters(direccionSucursal, ciudadSucursal);
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}


	public List<SucursalProducto> darSucursalesProducto(PersistenceManager pm, String producto )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto() + " WHERE producto = ?");
		q.setParameters(producto);
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}
	
	
	public List<SucursalProducto> darTodosProductosSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto());
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}
}