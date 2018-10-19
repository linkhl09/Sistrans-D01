package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosEnEstante;

class SQLProductosEnEstante {

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
	public SQLProductosEnEstante(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	
	public long adicionarProductosEnEstante(PersistenceManager pm, long estante, int cantidad, String producto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnEstante() + " (estante, cantidad, producto) VALUES (?, ?, ?)");
		q.setParameters(estante, cantidad, producto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductosEnEstante(PersistenceManager pm, long estante, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnEstante() + " WHERE estante = ? AND producto = ?");
		q.setParameters(estante, producto);
		return (long) q.executeUnique();
	}
	

	public List<ProductosEnEstante> darProductosEnEstante(PersistenceManager pm, long estante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE estante = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(estante);
		return (List<ProductosEnEstante>) q.executeList();
	}
	
	public ProductosEnEstante darProductoEnEstante(PersistenceManager pm, long estante, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE estante = ? AND producto = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(estante, producto);
		return (ProductosEnEstante) q.executeUnique();
	}
	
	public List<ProductosEnEstante> darTodosProductosEnEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante());
		q.setResultClass(ProductosEnEstante.class);
		return (List<ProductosEnEstante>) q.executeList();
	}


	public long traerDeBodega(PersistenceManager pm, long estante, int productosTraidos, String producto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad + ? WHERE estante = ? AND producto = ?");
		q.setParameters(productosTraidos, estante, producto);
		return (long) q.executeUnique();
	}
	
	
	public long venderProductos(PersistenceManager pm, long estante, int productosVendidos, String producto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad - ? WHERE estante = ? AND producto = ?");
		q.setParameters(productosVendidos, estante, producto);
		return (long) q.executeUnique();
	}
}