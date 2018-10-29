package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.net.aso.v;
import uniandes.isis2304.superAndes.negocio.Factura;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto FACTURA. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
class SQLFactura 
{

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
	
	/**
	 * Adiciona una nueva factura a la tabla "Facturas" con la informacion
	 * pasada por parametro
	 * @param numero - El Numero de factura , identificador unico
	 * @param direccion - La direccion de la factura
	 * @param facha - fecha de generacion de la factura
	 * @param nombreCajero - Nombre del cajero q expide la factura
	 * @param valorTotal - Valor total  de la factura 
	 * @param pagoExitoso - boolen que dice si se pudo realizar o no el pago
	 * @param puntosCompra - numero de puntos asignados por la compra
	 * @param cliente - correo electronico del cluente que realiza la compra,
	 *  identificador unico del cliente en la tabla "clientes"
	 */
	public long adicionarFactura(PersistenceManager pm, long numero, String direccion, 
			Date fecha, String nombreCajero, double valorTotal, boolean pagoExitoso, 
			int puntosCompra, String cliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFactura() + " (numero, direccion, fecha, nombrecajero, valortotal, pagoexitoso, puntoscompra, cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(numero, direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, cliente);
		return (long) q.executeUnique();
	}
	
	/**
	 * elimica una factura por el numero de factura, su identificador unico
	 * @param numero -numero de la factura a eliminar
	 */
	public long eliminarFactura(PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura() + " WHERE numero = ?");
		q.setParameters(numero);
		return (long) q.executeUnique();
	}
	
	/**
	 * devuelve la factura cuyo numero es igual al ingresado por parametro
	 * @param numero -numero de la factura 
	 */
	public Factura darFactura(PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura() + "WHERE numero = ?");
		q.setResultClass(Factura.class);
		q.setParameters(numero);
		return (Factura) q.executeUnique();
	}
	
	/**
	 * devuelve todas las facturas 
	 */
	public List<Factura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura());
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}
}