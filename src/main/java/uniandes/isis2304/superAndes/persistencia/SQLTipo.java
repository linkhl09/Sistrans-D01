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
	 * Constructor.
	 * @param psa - El Manejador de persistencia de la aplicación
	 */
	public SQLTipo(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPO a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - Nombre del tipo.
	 * @return El número asociado a las tuplas insertadas.
	 */
	public long adicionarTipo(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaTipo() + "(nombre) values (?)");
        q.setParameters(nombre);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y efectua la sentencia SQL para eliminar un TIPO de la base de datos de SuperAndes.
	 * @param pm - El manejador de persitencia.
	 * @param nombre nombre del tipo a eliminar.
	 * @return EL número asociado a las tuplas eliminadas.
	 */
	public long eliminarTipo(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipo() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los tipos en la base de datos.
	 * @param pm - EL manejador de persistencia.
	 * @return Una lista de Objetos Tipo.
	 */
	public List<Tipo> darTipos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipo());
		q.setResultClass(Tipo.class);
		return (List<Tipo>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de TIPO de la base de datos por su nombre.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - Nombbre del tipo.
	 * @return El TIPO con el nombre dado.
	 */
	public Tipo darTipo(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipo() + " WHERE nombre = ?");
		q.setParameters(nombre);
		q.setResultClass(Tipo.class);
		return (Tipo) q.executeUnique();
	}
}