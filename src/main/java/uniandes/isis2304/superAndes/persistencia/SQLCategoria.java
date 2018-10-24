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
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CATEGORIA a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - El nombre de la categoria.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCategoria() + " (nombre) VALUES (?)");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una CATEGORIA de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - Nombre de la categoria a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de CATEGORIA de la base de datos, por su nombre.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - Nombre de la categoria.
	 * @return El objeto Categoria con el nombre dado.
	 */
	public Categoria darCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria() + "WHERE nombre = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(nombre);
		return (Categoria) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar al información de las CATEGORIAS de lla base de datos.
	 * @param pm - EL manejador de persistencia.
	 * @return Una lista de objetos CATEGORIA.
	 */
	public List<Categoria> darCategorias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}	
}