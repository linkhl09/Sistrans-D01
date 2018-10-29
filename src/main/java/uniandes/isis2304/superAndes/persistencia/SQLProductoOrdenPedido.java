package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoOrdenPedido;

class SQLProductoOrdenPedido {

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
	public SQLProductoOrdenPedido(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Adiciona un nuevo producto-orden pedido a la tabla con toda la informacion necesaria
	 * @param pedido - identificador del pedido
	 * @param cantidad - cantidad del producto asociado a la orden
	 * @param calidad - calidad de los productos asociados 
	 * @param producto - codigo del producto
	 */
	public long adicionarProductoOrdenPedido(PersistenceManager pm, long pedido, int cantidad, double calidad, String producto, Date fechaAgregado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoOrdenPedido() + " (pedido, cantidad, calidad, producto, fechaAgregado) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(pedido, cantidad, calidad, producto, fechaAgregado);
		return (long) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion del producto-ordenPedido cuyo identificador de pedido y codigo de producto sen iguales a los ingresados
	 * por parametro
	 * @param pedido - identificador del pedido
	 * @param producto - codigo del producto
	 */
	public ProductoOrdenPedido darProductoOrdenPedido(PersistenceManager pm, long pedido, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + "WHERE pedido = ? AND producto = ?");
		q.setResultClass(ProductoOrdenPedido.class);
		q.setParameters(pedido, producto);
		return (ProductoOrdenPedido) q.executeUnique();
	}
	
	/**
	 * devuelve una lista con la informacion de todos los producto-ordenPedido 
	 */
	public List<ProductoOrdenPedido> darProductosOrdenPedidos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido());
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
	
	/**
	 * devuelve todos los productos vinculados a una orden de pedido
	 * @param pedido - identificador de la orden de pedido
	 */
	public List<ProductoOrdenPedido> darProductosDelPedido(PersistenceManager pm, long pedido)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + " WHERE pedido = ?");
		q.setParameters(pedido);
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
	
	/**
	 * devuelve el historial de todos las ordenes de pedido vinculadas al codigo de producto pasado
	 * por parametro
	 * @param producto - codigo del producto del cual se quiere saber el historial
	 */
	public List<ProductoOrdenPedido> darHistorialPedidosProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + " WHERE producto = ?");
		q.setParameters(producto);
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
}