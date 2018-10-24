package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Empresa;

class SQLEmpresa {

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
	public SQLEmpresa(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Adiciona una nueva empresa a la tabla "Empresa"
	 * @param nit - identificador unico de la empresa
	 *  @param direccion - direccion de la empresa
	 */
	public long adicionarEmpresa(PersistenceManager pm, String nit, String direccion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaEmpresa() + " (nit, direccion) VALUES (?, ?)");
		q.setParameters(nit, direccion);
		return (long) q.executeUnique();
	}
	
	/**
	 Elimina la empresa de la tabla "Empresa" cuyo nit es igual al que entra 
	 * por parametro
	 * @param nit - identificador unico de la empresa a eliminar
	 */
	public long eliminarEmpresaPorNit(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEmpresa() + " WHERE nit = ?");
		q.setParameters(nit);
		return (long) q.executeUnique();
	}
	
	/**
	 * Elimina la empresa de la tabla "Empresa" cuya direccion es igual a la que entra 
	 * por parametro
     * @param nit - identificador unico de la empresa a eliminar
	 */
	public long eliminarEmpresaPorDireccion(PersistenceManager pm, String direccion)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEmpresa() + " WHERE direccion = ?");
		q.setParameters(direccion);
		return (long) q.executeUnique();
	}
	
	/**
	 * Devuelve la empresa de la tabla "Empresa" cuyo nit es igual al que entra 
	 * por parametro
	 *  * @param nit - identificador unico de la empresa a devolver
	 */
	public Empresa darEmpresa(PersistenceManager pm, String nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEmpresa() + "WHERE nit = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(nit);
		return (Empresa) q.executeUnique();
	}
	
	/**
	 * Devuelve todas laa empresa de la tabla "Empresa" 
	 */
	public List<Empresa> darEmpresas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEmpresa());
		q.setResultClass(Empresa.class);
		return (List<Empresa>) q.executeList();
	}
}