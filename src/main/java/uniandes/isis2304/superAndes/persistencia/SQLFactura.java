package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.net.aso.v;
import uniandes.isis2304.superAndes.negocio.Factura;

class SQLFactura {

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
	public SQLFactura(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long adicionarFactura(PersistenceManager pm, long numero, String direccion, 
			Date fecha, String nombreCajero, double valorTotal, int pagoExitoso, 
			int puntosCompra, String cliente)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFactura() + " (numero, direccion, fecha, nombrecajero, valortotal, pagoexitoso, puntoscompra, cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, cliente);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarFactura(PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura() + " WHERE numero = ?");
		q.setParameters(numero);
		return (long) q.executeUnique();
	}
	

	public Factura darFactura(PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura() + "WHERE numero = ?");
		q.setResultClass(Factura.class);
		q.setParameters(numero);
		return (Factura) q.executeUnique();
	}
	
	public List<Factura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura());
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}
}