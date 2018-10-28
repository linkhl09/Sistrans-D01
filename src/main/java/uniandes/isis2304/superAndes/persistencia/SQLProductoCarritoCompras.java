package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoCarrito;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODUCTO CARRITO COMPRAS.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
class SQLProductoCarritoCompras 
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
	public SQLProductoCarritoCompras(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRODUCTOCARRITOCOMPRAS a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param carrito - Carrito al que se agrego un producto. 
	 * @param cantidad - Cantidad de productos puestos en el carrito.
	 * @param codigoBarrasProducto - Producto puesto en el carrito.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarPoductoCarrito(PersistenceManager pm, long carrito, int cantidad, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoCarritoCompras() + "(carrito, cantidad, codigoBarrasProducto) values (?, ?, ?)");
		q.setParameters(carrito, cantidad, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un PRODUCTOCARRITOCOMPRAS de la base de datos por su identificador. 
	 * @param pm - El manejador de persistencia. 
	 * @param carrito - El carrito del cual se desea borrar el producto.
	 * @param codigoBarrasProducto - El producto que se quiere eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarProductoCarrito(PersistenceManager pm, long carrito, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE * FROM " + psa.darTablaProductoCarritoCompras() + " WHERE carrito = ? AND codigoBarrasProducto =? ");
		q.setParameters(carrito, codigoBarrasProducto);
		return (long) q.executeUnique(); 
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un PRODUCTOCARRITOCOMPRAS en la base de datos, por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param carrito - El identificador del carrito con el producto buscado.
	 * @param codigoBarrasProducto - El codigo de barras del producto que esta en el carrito.
	 * @return El objeto de tipo ProductoCarrito que tiene el identificador dado.
	 */
	public ProductoCarrito darProductoCarrito(PersistenceManager pm, long carrito, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarritoCompras() + " WHERE carrito = ? AND codigoBarrasProducto =?");
		q.setParameters(carrito, codigoBarrasProducto);
		q.setResultClass(ProductoCarrito.class);
		return (ProductoCarrito) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos los PRODUCTOCARRITOCOMPRAS en la base de datos que pertenecen a un carrito.
	 * @param pm - El manejador de persistencia.
	 * @param carrito - El carrito que se quiere saber que productos lleva.
	 * @return Lista de objetos ProductosCarrito que pertenecen al carrito con el identificador dado.
	 */
	public List<ProductoCarrito> darTodosProductosDeUnCarrito(PersistenceManager pm, long carrito)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarritoCompras() + " WHERE carrito = ?");
		q.setParameters(carrito);
		q.setResultClass(ProductoCarrito.class);
		return (List<ProductoCarrito>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos los PRODUCTOCARRITOCOMPRAS en la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista con todos los productoCarrito de la base de datos.
	 */
	public List<ProductoCarrito> darTodosProductosCarrito(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoCarritoCompras());
		q.setResultClass(ProductoCarrito.class);
		return (List<ProductoCarrito>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para aumentar las unidades de un producto en el carrito de compras.
	 * @param pm - El manejador de persistencia.
	 * @param carrito - El identificador del carrito de compras.
	 * @param codigoBarrasProducto - El identificador del producto que el cliente añadio al carrito. 
	 * @param productosAgregados - La cantidad de unidades del  product que el cliente agrego al carrito. 
	 * @return El número de tuplas modificadas.
	 */
	public long aumentarUnidadesProductoCarritoCompras(PersistenceManager pm, long carrito, String codigoBarrasProducto, int productosAgregados)
	{
		Query q = pm.newQuery(SQL , "UPDATE " + psa.darTablaProductoCarritoCompras() + " SET cantidad = cantidad + ? WHERE carrito = ? AND codigoBarrasProducto = ?");
		q.setParameters(productosAgregados, carrito, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para disminuir las unidades de un producto en el carrito de compras.
	 * @param pm - El manejador de persistencia.
	 * @param carrito - El identificador del carrito de compras.
	 * @param codigoBarrasProducto - El identificador del producto que el cliente añadio al carrito. 
	 * @param productosDevueltos - La cantidad de unidades del  producto que se devolvieron al estante, o se vendieron. 
	 * @return El número de tuplas modificadas.
	 */
	public long disminuirUnidadesProductoCarritoCompras(PersistenceManager pm, long carrito, String codigoBarrasProducto, int productosDevueltos)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoCarritoCompras() + " SET cantidad = cantidad - ? WHERE carrito =? AND codigoBarrasProducto = ?");
		q.setParameters(productosDevueltos, carrito, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
}