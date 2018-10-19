package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;

class SQLCategoria {

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
	public SQLCategoria(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCategoria() + " (nombre) VALUES (?)");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	public long eliminarCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	public Categoria darCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria() + "WHERE nombre = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(nombre);
		return (Categoria) q.executeUnique();
	}
	
	public List<Categoria> darCategorias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}	
}