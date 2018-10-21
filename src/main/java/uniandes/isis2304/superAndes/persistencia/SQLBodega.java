package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BODEGA de SuperAndes
 * @author Andrés Hernández
 */
class SQLBodega {

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
	public SQLBodega(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarBodega(PersistenceManager pm, long id, double capacidadVol, 
			double capacidadPeso, String tipo, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaBodega() + " (id, capacidadvol, capacidadpeso, tipo, idsucursal) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(id, capacidadVol, capacidadPeso, tipo, idSucursal);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarBodega(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaBodega() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	

	public Bodega darBodega(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega() + "WHERE id = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(id);
		return (Bodega) q.executeUnique();
	}
	
	public List<Bodega> darBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	
	public List<Bodega> darBodegasSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega() + "WHERE idsucursal = ?");
		q.setParameters(idSucursal);
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
}
