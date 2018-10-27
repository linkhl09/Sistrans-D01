package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosEnBodega;

class SQLProductosEnBodega {

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
	public SQLProductosEnBodega(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRODUCTOENBODEGA a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param idBodega - El id de la bodega donde se almacena el producto .
	 * @param cantidad - Cantidad de unidades del producto almacenadas en bodega.
	 * @param nivelAbastecimiento - Nivel de abastecimiento que debe tener la bodega.
	 * @param codigoBarrasProducto - Identificador del producto almacenado.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarProductosEnBodega(PersistenceManager pm, long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnBodega() + " (idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto) VALUES (?, ?, ?, ?)");
		q.setParameters(idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia sQL para eliminar definitivamente un PRODUCTOENBODEGA de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param idBodega - Id de la bodega donde se almacena el producto.
	 * @param codigoBarrasProducto - El identificador del producto a eliminar de la bodega.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarProductoEnBodega(PersistenceManager pm, long idBodega, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnBodega() + " WHERE idBodega = ? AND codigoBarrasProducto = ?");
		q.setParameters(idBodega, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los PRODUCTOSENBODEGA de una bodega.
	 * @param pm - El manejador de persistencia. 
	 * @param idBodega - El identificador de la bodega que se queiren revisar sus prodcutos.
	 * @return Una lista de objetos ProductosEnBodega de la bodega con el identificador dado.
	 */
	public List<ProductosEnBodega> darProductosEnBodega(PersistenceManager pm, long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE idBodega = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(idBodega);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos los PRODUCTOSENBODEGA de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Lista de objetos de tipo ProductosEnBodega.
	 */
	public List<ProductosEnBodega> darTodosProductosBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega());
		q.setResultClass(ProductosEnBodega.class);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de las bodegas donde se almacenan cierto producto.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarrasproducto - Codigo de barras del producto del que se desean conocer sus bodegas.
	 * @return Una lista de objetos ProductosEnBodega que almacenan el producto con el identificador dado.
	 */
	public List<ProductosEnBodega> darBodegasProducto(PersistenceManager pm, String codigoBarrasproducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE codigoBarrasProducto = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(codigoBarrasproducto);
		return (List<ProductosEnBodega>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para actualizar las existencias de algún producto en bodega.
	 * @param pm - Madrugare
	 * @param idBodega
	 * @param codigoBarrasProducto
	 * @param productosPedidos
	 * @return
	 */
	public long aumentarProductosEnBodega(PersistenceManager pm, long idBodega, String codigoBarrasProducto, int productosPedidos )
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnBodega() + "SET cantidad = cantidad + ? WHERE bodega = ? AND producto = ?");
		q.setParameters(productosPedidos , idBodega, codigoBarrasProducto );
		return (long) q.executeUnique();
	}
	
	public long disminuirProductosEnBodega(PersistenceManager pm, long idBodega, String codigoBarrasProducto, int pasadosAEstante)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnBodega() + "SET cantidad = cantidad - ? WHERE bodega = ? AND producto = ?");
		q.setParameters(pasadosAEstante , idBodega, codigoBarrasProducto );
		return (long) q.executeUnique();
	}
}