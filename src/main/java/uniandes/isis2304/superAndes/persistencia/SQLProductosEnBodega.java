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
	

	public long adicionarProductosEnBodega(PersistenceManager pm, long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnBodega() + " (idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto) VALUES (?, ?, ?, ?)");
		q.setParameters(idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductoEnBodega(PersistenceManager pm, long idBodega, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnBodega() + " WHERE idBodega = ? AND codigoBarrasProducto = ?");
		q.setParameters(idBodega, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	

	public List<ProductosEnBodega> darProductosEnBodega(PersistenceManager pm, long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE idBodega = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(idBodega);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	public List<ProductosEnBodega> darTodosProductosBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega());
		q.setResultClass(ProductosEnBodega.class);
		return (List<ProductosEnBodega>) q.executeList();
	}
	
	
	public List<ProductosEnBodega> darBodegasProducto(PersistenceManager pm, String codigoBarrasproducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnBodega() + "WHERE codigoBarrasProducto = ?");
		q.setResultClass(ProductosEnBodega.class);
		q.setParameters(codigoBarrasproducto);
		return (List<ProductosEnBodega>) q.executeList();
	}

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