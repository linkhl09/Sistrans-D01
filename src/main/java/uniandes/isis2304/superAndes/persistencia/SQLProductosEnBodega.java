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
	

	public long adicionarProductosEnBodega(PersistenceManager pm, long bodega, int cantidad, int nivelAbastecimiento, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnBodega() + " (bodega, cantidad, nivelAbastecimiento, producto) VALUES (?, ?, ?, ?)");
		q.setParameters(bodega, cantidad, nivelAbastecimiento, producto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductoEnBodega(PersistenceManager pm, long bodega, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnBodega() + " WHERE bodega = ? AND producto = ?");
		q.setParameters(bodega, producto);
		return (long) q.executeUnique();
	}
	

	public List<ProductosEnBodega> darProductosEnBodega(PersistenceManager pm, long bodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE bodega = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(bodega);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	public List<ProductosEnBodega> darTodosProductosBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega());
		q.setResultClass(ProductosEnBodega.class);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	
	public List<ProductosEnBodega> darBodegasProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE producto = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(producto);
		return (List<ProductosEnBodega>) q.executeList();
	}

	public long aumentarProductosEnBodega(PersistenceManager pm, long bodega, String producto, int productosPedidos )
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnBodega() + "SET cantidad = cantidad + ? WHERE bodega = ? AND producto = ?");
		q.setParameters(productosPedidos , bodega, producto );
		return (long) q.executeUnique();
	}
	
	public long disminuirProductosEnBodega(PersistenceManager pm, long bodega, String producto, int pasadosAEstante )
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnBodega() + "SET cantidad = cantidad - ? WHERE bodega = ? AND producto = ?");
		q.setParameters(pasadosAEstante , bodega, producto );
		return (long) q.executeUnique();
	}
}
