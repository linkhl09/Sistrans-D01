package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEDOR. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
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
	
	/**
	 * Adiciona un nuevo proveedor con toda la informacion necesaria a la tabla "proveedores"
	 * @param nit - numero de identificacion unico del proveedor
	 * @param nombre - nombre del proveedor 
	 * @param calificacion - calificacion del proveedor
	 */
	public long adicionarProveedor(PersistenceManager pm, String nit, String nombre, double calificacion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedor() + " (nit, nombre, calificacionCalidad) VALUES (?, ? , ?)");
		q.setParameters(nit, nombre, calificacion);
		return (long) q.executeUnique();
	}
	
	/**
	 * Elimina de la tabla al proveedor cuyo numero de identificacion es igual al ingresado por parametro 
	 * @param nit - numero de identificacion unico del proveedor
	 * */
	public long eliminarProveedorPorNit(PersistenceManager pm, String nit)
	{ 
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor() + " WHERE nit = ?");
		q.setParameters(nit);
		return (long) q.executeUnique();
	}
	
	/**
	 * Elimina de la tabla al proveedor cuyo nombre es igual al ingresado por parametro 
	 * @param nombre - nombre del proveedor 
	  */
	public long eliminarProveedorPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	/**
	 * Devuelve toda la informacion del proveedor cuyo numero de identificacion es igual al ingresado por parametro 
	 * @param nit - numero de identificacion unico del proveedor
	 * */
	public Proveedor darProveedor(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedor() + "WHERE nit = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nit);
		return (Proveedor) q.executeUnique();
	}
	
	/**
	 *Devuelve una lista de todos los proveedores en la tabla
	 */
	public List<Proveedor> darProveedores(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}
	
	/**
	 * cambia la calificacion de calidad del proveedor cuyo nit es igual al 
	 * ingresado por parametro por la nueva calificacion tambien ingresada por parametro
	 * @param nit -  numero de identificacion del proveedor al que se le cambiara la calificacion 
	 * @param calificacion - calificacion nueva
	 */
	public long updateCalificacion(PersistenceManager pm, String nit, double calificacion)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProveedor() + "SET calificacionCalidad = ? WHERE nit = ?");
		q.setParameters(calificacion, nit);
		return (long) q.executeUnique();
	}
}