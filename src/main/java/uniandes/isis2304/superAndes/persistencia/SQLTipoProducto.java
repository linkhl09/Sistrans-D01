package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.TipoCategoria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPOPRODUCTO de SuperAndes.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés hernández.
 */
class SQLTipoProducto 
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
	public SQLTipoProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPOPRODUCTO a la base de datos.
	 * @param pm - EL manejador de persistencia.
	 * @param codigoBarrasProducto - Identificador del producto.
	 * @param nombreTipo - Tipo del producto.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarTipoProducto(PersistenceManager pm, String codigoBarrasProducto, String nombreTipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaTipoProducto() + " (codigoBarrasProducto, nombreTipo) values (?, ?)");
		q.setParameters(codigoBarrasProducto, nombreTipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un TIPOPRODUCTO de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarrasProducto - Identificador del producto.
	 * @param nombreTipo - Tipo del producto.
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarTipoCategoria(PersistenceManager pm, String codigoBarrasProducto, String nombreTipo)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipoProducto() + "WHERE codigoBarrasProducto = ? AND nombreTipo = ?");
		q.setParameters(codigoBarrasProducto, nombreTipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los tipos del producto dado por parametro.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarrasProducto - Producto del que se quieren conocer sus tipos.
	 * @return Lista con los TIPOPRODUCTO que cumplan con la especificación.
	 */
	public List<TipoCategoria> darTiposDelProducto(PersistenceManager pm, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipoProducto() + "WHERE codigoBarrasProducto = ?");
		q.setParameters(codigoBarrasProducto);
		q.setResultClass(TipoCategoria.class);
		return (List<TipoCategoria>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los TIPOCATEGORIA de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista de objetos TIPOCATEGORIA.
	 */
	public List<TipoCategoria> darTodosTipoCategoria(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipoProducto());
		q.setResultClass(TipoCategoria.class);
		return (List<TipoCategoria>) q.executeList();
	}
}
