package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PersonaNatural;

class SQLPersonaNatural {

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
	public SQLPersonaNatural(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarPersonaNatural(PersistenceManager pm, String documento, String tipoDocumento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPersonaNatural() + " (documento, tipodocumento) VALUES (?, ?)");
		q.setParameters(documento, tipoDocumento);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarPersonaNatural(PersistenceManager pm, String documento, String tipoDocumento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPersonaNatural() + " WHERE documento = ? AND tipodocumento = ?");
		q.setParameters(documento, tipoDocumento);
		return (long) q.executeUnique();
	}
	

	public PersonaNatural darPersonaNatural(PersistenceManager pm, String documento, String tipodocumento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersonaNatural() + "WHERE documento = ? AND tipodocumento = ?");
		q.setResultClass(PersonaNatural.class);
		q.setParameters(documento, tipodocumento);
		return (PersonaNatural) q.executeUnique();
	}
	
	public List<PersonaNatural> darPersonasNaturales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersonaNatural());
		q.setResultClass(PersonaNatural.class);
		return (List<PersonaNatural>) q.executeList();
	}
}