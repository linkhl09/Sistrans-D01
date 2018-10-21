package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Estante;

class SQLEstante {

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
	public SQLEstante(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarEstante(PersistenceManager pm, long id, double capacidadVol, 
			double capacidadPeso, String tipo, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaEstante() + " (id, capacidadvol, capacidadpeso, tipo, idsucursal) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(id, capacidadVol, capacidadPeso, tipo, idSucursal);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarEstante(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEstante() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	

	public Estante darEstante(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante() + "WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(id);
		return (Estante) q.executeUnique();
	}
	
	public List<Estante> darEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	
	public List<Estante> darEstantesSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante() + "WHERE idSucursal = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idSucursal);
		return (List<Estante>) q.executeList();
	}
}