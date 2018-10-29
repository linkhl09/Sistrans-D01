package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PersonaNatural;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PERSONA NATURAL. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
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
	
	/**
	 * Agrega una nueva  persona natural  a la base de datos
	 * @param documento -  el numero de documento de la persona , es el identficador unico de la persona
	 * @param tipoDocumento -  el tipo de documento de la persona
	 */
	public long adicionarPersonaNatural(PersistenceManager pm, String documento, String tipoDocumento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPersonaNatural() + " (documento, tipodocumento) VALUES (?, ?)");
		q.setParameters(documento, tipoDocumento);
		return (long) q.executeUnique();
	}
	
	/**
	 * elimina una  persona natural  de la base de datos
	 * @param documento -  el numero de documento de la persona a eliminar , es el identficador unico de la persona
	 * @param tipoDocumento -  el tipo de documento de la persona
	 */
	public long eliminarPersonaNatural(PersistenceManager pm, String documento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPersonaNatural() + " WHERE documento = ?");
		q.setParameters(documento);
		return (long) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion de la persona con el documento dado
	 * @param documento -  el numero de documento de la persona a eliminar , es el identficador unico de la persona
	 * @param tipoDocumento -  el tipo de documento de la persona
	 */
	public PersonaNatural darPersonaNatural(PersistenceManager pm, String documento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersonaNatural() + "WHERE documento = ?");
		q.setResultClass(PersonaNatural.class);
		q.setParameters(documento);
		return (PersonaNatural) q.executeUnique();
	}
	
	/**
	 * devuelvela informacion de todas las personas naturales 
	 */
	public List<PersonaNatural> darPersonasNaturales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersonaNatural());
		q.setResultClass(PersonaNatural.class);
		return (List<PersonaNatural>) q.executeList();
	}
}