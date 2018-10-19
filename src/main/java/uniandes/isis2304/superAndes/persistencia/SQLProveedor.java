package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;


class SQLProveedor {
	
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
	public SQLProveedor(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarProveedor(PersistenceManager pm, String nit, String nombre, double calificacion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedor() + " (nit, nombre, calificacion) VALUES (?, ? , ?)");
		q.setParameters(nit, nombre, calificacion);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProveedorPorNit(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor() + " WHERE nit = ?");
		q.setParameters(nit);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProveedorPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	public Proveedor darProveedor(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedor() + "WHERE nit = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nit);
		return (Proveedor) q.executeUnique();
	}
	
	public List<Proveedor> darProveedores(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}
	
	
	public long updateCalificacion(PersistenceManager pm, String nit, double calificacion)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProveedor() + "SET valor = ? WHERE nit = ?");
		q.setParameters(calificacion, nit);
		return (long) q.executeUnique();
	}
}