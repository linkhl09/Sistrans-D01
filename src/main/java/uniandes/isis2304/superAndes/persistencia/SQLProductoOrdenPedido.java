package uniandes.isis2304.superAndes.persistencia;

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
	
	
	public long adicionarProductoOrdenPedido(PersistenceManager pm, long pedido, int cantidad, double calidad, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoOrdenPedido() + " (pedido, cantidad, calidad, producto) VALUES (?, ?, ?, ?)");
		q.setParameters(pedido, cantidad, calidad, producto);
		return (long) q.executeUnique();
	}
	
	public ProductoOrdenPedido darProductoOrdenPedido(PersistenceManager pm, long pedido, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + "WHERE pedido = ? AND producto = ?");
		q.setResultClass(ProductoOrdenPedido.class);
		q.setParameters(pedido, producto);
		return (ProductoOrdenPedido) q.executeUnique();
	}
	
	public List<ProductoOrdenPedido> darProductosOrdenPedidos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido());
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
	
	public List<ProductoOrdenPedido> darProductosDelPedido(PersistenceManager pm, long pedido)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + " WHERE pedido = ?");
		q.setParameters(pedido);
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
	
	public List<ProductoOrdenPedido> darHistorialPedidosProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoOrdenPedido() + " WHERE producto = ?");
		q.setParameters(producto);
		q.setResultClass(ProductoOrdenPedido.class);
		return (List<ProductoOrdenPedido>) q.executeList();
	}
}