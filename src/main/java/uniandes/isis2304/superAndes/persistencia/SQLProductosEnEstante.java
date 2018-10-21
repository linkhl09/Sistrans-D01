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
	
	
	public long adicionarProductosEnEstante(PersistenceManager pm, long idEstante, int cantidad, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnEstante() + " (idEstante, cantidad, codigoBarrasProducto) VALUES (?, ?, ?)");
		q.setParameters(idEstante, cantidad, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	
	public long eliminarProductosEnEstante(PersistenceManager pm, long idEstante, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnEstante() + " WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	

	public List<ProductosEnEstante> darProductosEnEstante(PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE idEstante = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(idEstante);
		return (List<ProductosEnEstante>) q.executeList();
	}
	
	public ProductosEnEstante darProductoEnEstante(PersistenceManager pm, long idEstante, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(idEstante, codigoBarrasProducto);
		return (ProductosEnEstante) q.executeUnique();
	}
	
	public List<ProductosEnEstante> darTodosProductosEnEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante());
		q.setResultClass(ProductosEnEstante.class);
		return (List<ProductosEnEstante>) q.executeList();
	}


	public long traerDeBodega(PersistenceManager pm, long idEstante, int productosTraidos, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad + ? WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(productosTraidos, idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	
	public long quitarProductosEstante(PersistenceManager pm, long idEstante, int productosVendidos, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad - ? WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(productosVendidos, idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
}