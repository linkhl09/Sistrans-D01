package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Tipo;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO de SuperAndes
 * @author Andrés Hernández
 */
class SQLTipo {

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
	public SQLTipo(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	

	public long adicionarTipo(PersistenceManager pm, String nombre, String categoria)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaTipo() + "(nombre, categoria) values (?, ?)");
        q.setParameters(nombre, categoria);
        return (long) q.executeUnique();
	}
	
	public long eliminarTipo(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipo() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	public List<Tipo> darTipos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipo());
		q.setResultClass(Tipo.class);
		return (List<Tipo>) q.executeList();
	}

	public Tipo darTipoPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipo() + " WHERE nombre = ?");
		q.setParameters(nombre);
		q.setResultClass(Tipo.class);
		return (Tipo) q.executeUnique();
	}
}