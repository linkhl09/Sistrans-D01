package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.TipoCategoria;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto TIPOPRODUCTO de SuperAndes.
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia.
 * 
 * @author Andr�s hern�ndez.
 */
class SQLTipoProducto 
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
		
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes psa;
	
	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor
	 * @param psa - El Manejador de persistencia de la aplicaci�n
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
	 * @return El n�mero de tuplas insertadas.
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
	 * @return El n�mero de tuplas eliminadas
	 */
	public long eliminarTipoCategoria(PersistenceManager pm, String codigoBarrasProducto, String nombreTipo)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipoProducto() + "WHERE codigoBarrasProducto = ? AND nombreTipo = ?");
		q.setParameters(codigoBarrasProducto, nombreTipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los tipos del producto dado por parametro.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarrasProducto - Producto del que se quieren conocer sus tipos.
	 * @return Lista con los TIPOPRODUCTO que cumplan con la especificaci�n.
	 */
	public List<TipoCategoria> darTiposDelProducto(PersistenceManager pm, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaTipoProducto() + "WHERE codigoBarrasProducto = ?");
		q.setParameters(codigoBarrasProducto);
		q.setResultClass(TipoCategoria.class);
		return (List<TipoCategoria>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de los TIPOCATEGORIA de la base de datos.
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
