package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.CarritoCompras;
import uniandes.isis2304.superAndes.negocio.Estante;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CarritoCompras de SuperAndes.
 * Nótese que es una clase que es solo conocida en el paquete de persistencia. 
 * 
 * @author Andrés Hernández
 */
class SQLCarritoCompras {

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
	public SQLCarritoCompras(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CARRITOCOMPRAS a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del carrito de compras.
	 * @param cliente - El identificador del cliente dueño del carrito de comrpas.
	 *  @param idSucursal - El identificador de la sucursal donde se encuentra el carrito de comrpas.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarCarritoCompras(PersistenceManager pm, long id, String cliente, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCarritoCompras() + " (id, cliente,Sucursal) values (?,?,?)");
		q.setParameters(id, cliente, idSucursal);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CARRITOCOMPRAS de la base de datos, por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del carrito de compras.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarCarritoCompras(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCarritoCompras() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un CARRITOCOMPRAS por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del carrito de compras.
	 * @return El objeto tipo CarritoCompras que tiene el identificador dado.
	 */
	public CarritoCompras darCarritoComprasPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarritoCompras() + " WHERE id = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(id);
		return (CarritoCompras) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un CARRITOCOMPRAS por su cliente.
	 * @param pm - El manejador de persistencia.
	 * @param cliente - El identificador del cliente dueño del carrito de comrpas.
	 * @return El objeto tipo Carritocompras que tiene el cliente dado.
	 */
	public CarritoCompras darCarritoComprasPorCliente(PersistenceManager pm, String cliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarritoCompras() + " WHERE cliente = ?");
		q.setResultClass(CarritoCompras.class);
		q.setParameters(cliente);
		return (CarritoCompras) q.executeUnique();
		
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para "abandonar" el CARRITOCOMPRAS de la base de datos, por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del carrito de compras.
	 * @return El número de tuplas actualizadas.
	 */
	public long abandonarCarrito(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCarritoCompras() + " SET cliente = null WHERE id = ? ");
		q.setParameters(id);
		return (long) q.executeUnique();
	}	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información los CARRITOCOMPRAS de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista de objetos CarritoCompras.
	 */
	public List<CarritoCompras> darTodosCarritosCompras(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarritoCompras());

		q.setResultClass(CarritoCompras.class);
		return (List<CarritoCompras>) q.executeList();
	}
}
