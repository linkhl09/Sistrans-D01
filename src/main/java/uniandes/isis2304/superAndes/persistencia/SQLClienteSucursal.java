package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ClienteSucursal;

class SQLClienteSucursal {
	
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
	public SQLClienteSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarClienteSucursal(PersistenceManager pm, String cliente, String direccionSucursal, String ciudadSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaClienteSucursal() + " (cliente, direccionsucursal, ciudadsucursal) VALUES (?, ?, ?)");
		q.setParameters(cliente, direccionSucursal, ciudadSucursal);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarClienteSucursal(PersistenceManager pm, String cliente, String direccionSucursal, String ciudadSucursal)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaClienteSucursal() + " WHERE cliente = ? AND direccionSucursal = ? AND ciudadSucursal = ?");
		q.setParameters(cliente, direccionSucursal, ciudadSucursal);
		return (long) q.executeUnique();
	}
	

	public List<ClienteSucursal> darSucursalesCliente(PersistenceManager pm, String cliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaClienteSucursal() + "WHERE cliente = ?");
		q.setResultClass(ClienteSucursal.class);
		q.setParameters(cliente);
		return (List<ClienteSucursal>) q.executeUnique();
	}
	
	
	public List<ClienteSucursal> darClientesSucursal(PersistenceManager pm, String direccionSucursal, String ciudadSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaClienteSucursal() + "WHERE direccionSucursal = ? AND ciudadsucursal = ?");
		q.setResultClass(ClienteSucursal.class);
		q.setParameters(direccionSucursal, ciudadSucursal);
		return (List<ClienteSucursal>) q.executeUnique();
	}
	
	
	public List<ClienteSucursal> darTodosClientesSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaClienteSucursal());
		q.setResultClass(ClienteSucursal.class);
		return (List<ClienteSucursal>) q.executeList();
	}
}
