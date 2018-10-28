package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosEnEstante;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODUCTO EN ESTANTE. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
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
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRODUCTOENESTANTE a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param idEstante - Identificador del estante a agregar.
	 * @param cantidad - Cantidad de existencias de ese producto en el estante.
	 * @param codigoBarrasProducto - Identificador del producto.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarProductosEnEstante(PersistenceManager pm, long idEstante, int cantidad, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductosEnEstante() + " (idEstante, cantidad, codigoBarrasProducto) VALUES (?, ?, ?)");
		q.setParameters(idEstante, cantidad, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un PRODUCTOENESTANTE de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param idEstante - El identificador del estante.
	 * @param codigoBarrasProducto - El identificador del producto. 
	 * @return Número de tuplas eliminadas.
	 */
	public long eliminarProductosEnEstante(PersistenceManager pm, long idEstante, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnEstante() + " WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la setencia SQL para encontrar la información de los PRODUCTOSENESTANTE de cierto estante.
	 * @param idEstante - Estante al que pertenecen los productos.
	 * @return Lista de objetos ProductosEnEstante que pertenecen al estante buscado.
	 */
	public List<ProductosEnEstante> darProductosEnEstante(PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE idEstante = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(idEstante);
		return (List<ProductosEnEstante>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un PRODUCTOENESTANTE.
	 * @param pm - EL manejador de persistencia.
	 * @param idEstante - El identificador del estante buscado.
	 * @param codigoBarrasProducto - El identificador del producto buscado.
	 * @return El objeto ProductosEnEstante asociado a los id's brindados.
	 */
	public ProductosEnEstante darProductoEnEstante(PersistenceManager pm, long idEstante, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante() + "WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setResultClass(ProductosEnEstante.class);
		q.setParameters(idEstante, codigoBarrasProducto);
		return (ProductosEnEstante) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencias SQL para obtener todos los PRODUCTOSENESTANTE de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista de objetos ProductosEnEstante con todos los PRODUCTOSENESTANTE de la base de datos.
	 */
	public List<ProductosEnEstante> darTodosProductosEnEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductosEnEstante());
		q.setResultClass(ProductosEnEstante.class);
		return (List<ProductosEnEstante>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para aumentar las existencias de un producto en el estante.
	 * @param pm - El manejador de persistencia.
	 * @param idEstante - El identificador del estante. 
	 * @param productosTraidos - Número de unidades a aumentar.
	 * @param codigoBarrasProducto - El producto del que trajeron unidades de bodega..
	 * @return El número de tuplas modificadas.
	 */
	public long traerDeBodega(PersistenceManager pm, long idEstante, int productosTraidos, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad + ? WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(productosTraidos, idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para disminuir los productos del estante.
	 * @param pm - El manejador de persistencia.
	 * @param idEstante - EL identificador del estante.
	 * @param productosVendidos - Número de productos sacados del estante.
	 * @param codigoBarrasProducto - El identificador del producto.
	 * @return El número de tuplas modificadas.
	 */
	public long quitarProductosEstante(PersistenceManager pm, long idEstante, int productosVendidos, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductosEnEstante() + "SET cantidad = cantidad - ? WHERE idEstante = ? AND codigoBarrasProducto = ?");
		q.setParameters(productosVendidos, idEstante, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
}