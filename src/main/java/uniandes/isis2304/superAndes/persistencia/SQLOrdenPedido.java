package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenPedido;

class SQLOrdenPedido {

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
	public SQLOrdenPedido(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarOrdenPedido(PersistenceManager pm, long id, Date fechaEsperadaEntrega
			, String proveedor, String direccionSucursal, String ciudadSUcursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaOrdenPedido() 
		+ " (id, fechaEsperadaEntrega, calificacionProveedor, proveedor, direccionSucursal, ciudadSucursal) VALUES (?, ?, 0, ?, ?, ?)");
		q.setParameters(id, fechaEsperadaEntrega, proveedor, direccionSucursal, ciudadSUcursal);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarOrdenPedido(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOrdenPedido() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	

	public OrdenPedido darOrdenPedido(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaOrdenPedido() + "WHERE id = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(id);
		return (OrdenPedido) q.executeUnique();
	}
	
	
	public List<OrdenPedido> darOrdenesPedidos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaOrdenPedido());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}


	public long registrarFechaLlegada(PersistenceManager pm, long id, Date fechaEntrega, double nuevaCalificacion)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaOrdenPedido() + "SET fechaentrega = ? , calificacionproveedor = ? WHERE id = ?");
		q.setParameters(fechaEntrega, nuevaCalificacion, id);
		return (long) q.executeUnique();
	}
}
