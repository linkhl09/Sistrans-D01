package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;

class SQLSucursal {

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
	public SQLSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarSucursal(PersistenceManager pm, long id, String direccion, String ciudad,
								 String nombre, String segmentacionMercado, int tamanio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursal() + " (id, direccion, ciudad, nombre, segmentacionmercado, tamanio) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, direccion, ciudad, nombre, segmentacionMercado, tamanio);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarSucursalPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	public long eliminarSucursalPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal()+ "WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	public Sucursal darSucursalPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal() +" WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		return (Sucursal) q.executeUnique();
	}
	
	public Sucursal darSucursalPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal() +" WHERE nombre = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(nombre);
		return (Sucursal) q.executeUnique();
	}
	
	public List<Sucursal> darSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
}
