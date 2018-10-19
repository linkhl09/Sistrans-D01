package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

class SQLPromocion {

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
	public SQLPromocion(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarPromocion(PersistenceManager pm, long id, String descripcion, double precio, Date inicio, Date fin, int unidadesDisponibles, String proveedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromocion() + " (id, descripcion, precio, inicio, fin, unidadesdisponibles, unidadesvendidas, proveedor) VALUES (?, ?, ?, ?, ?, ?, 0, ?)");
		q.setParameters(id, descripcion, inicio, fin, unidadesDisponibles, proveedor);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarPromocion(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromocion() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	

	public Promocion darPromocion(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocion() + "WHERE id = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(id);
		return (Promocion) q.executeUnique();
	}
	
	public List<Promocion> darPromociones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocion());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}


	public long registrarVentas(PersistenceManager pm, long id, int unidadesVendidas)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPromocion() + "SET unidadesdisponibles = unidadesdisponibles - ? , unidadesvendidas = unidadesvendidas + ? WHERE id = ?");
		q.setParameters(unidadesVendidas, unidadesVendidas, id);
		return (long) q.executeUnique();
	}	
}