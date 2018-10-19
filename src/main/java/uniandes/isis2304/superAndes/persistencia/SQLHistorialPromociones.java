package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.HistorialPromociones;

class SQLHistorialPromociones {

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
	public SQLHistorialPromociones(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarHistorialPromociones(PersistenceManager pm, String producto, long promocion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaHistorialPromociones() + " (producto, promocion) VALUES (?, ?)");
		q.setParameters(producto, promocion);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarHistorialPromociones(PersistenceManager pm, String producto, long promocion)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaHistorialPromociones() + " WHERE producto = ? AND promocion = ?");
		q.setParameters(producto, promocion);
		return (long) q.executeUnique();
	}
	

	public HistorialPromociones darHistorialPromocion(PersistenceManager pm, String producto, long promocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaHistorialPromociones() + "WHERE producto = ? AND promocion = ?");
		q.setResultClass(HistorialPromociones.class);
		q.setParameters(producto, promocion);
		return (HistorialPromociones) q.executeUnique();
	}
	
	public List<HistorialPromociones> darHistorialCompletoPromociones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaHistorialPromociones());
		q.setResultClass(HistorialPromociones.class);
		return (List<HistorialPromociones>) q.executeList();
	}
	
	public List<HistorialPromociones> darHistorialPromocionesProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaHistorialPromociones() + " WHERE producto = ?");
		q.setParameters(producto);
		q.setResultClass(HistorialPromociones.class);
		return (List<HistorialPromociones>) q.executeList();
	}
}
