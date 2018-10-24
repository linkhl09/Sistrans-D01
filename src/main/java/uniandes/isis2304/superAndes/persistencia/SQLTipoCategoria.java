package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.SucursalProducto;
import uniandes.isis2304.superAndes.negocio.TipoCategoria;



public class SQLTipoCategoria 
{
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
	public SQLTipoCategoria(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * 
	 * @param pm
	 * @param nombreCategoria
	 * @param nombreTipo
	 * @return
	 */
	public long adicionarTipoCategoria(PersistenceManager pm, String nombreCategoria, String nombreTipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaTipoCategoria() + " (nombreCategoria, nombreTipo) values (?, ?)");
		q.setParameters(nombreCategoria, nombreTipo);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarTipoCategoria(PersistenceManager pm, String nombreCategoria, String nombreTipo)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipoCategoria() + "WHERE nombreCategoria = ? AND nombreTipo = ?");
		q.setParameters(nombreCategoria, nombreTipo);
		return (long) q.executeUnique();
	}
	
	
	public List<TipoCategoria> darTiposCategoria(PersistenceManager pm, String nombreCategoria)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipoCategoria() + "WHERE nombreCategoria = ?");
		q.setParameters(nombreCategoria);
		q.setResultClass(TipoCategoria.class);
		return (List<TipoCategoria>) q.executeList();
	}
	
	
	public List<TipoCategoria> darTodosTipoCategoria(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipoCategoria());
		q.setResultClass(TipoCategoria.class);
		return (List<TipoCategoria>) q.executeList();
	}
}
