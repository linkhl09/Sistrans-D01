package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.SucursalProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL PRODUCTO.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
class SQLSucursalProducto {

	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos.
	 * Se renombra acá para facilitar la escritura de las sentencias.
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
		
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * El manejador de persistencia general de la aplicación.
	 */
	private PersistenciaSuperAndes psa;
	
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor
	 * @param psa - El Manejador de persistencia de la aplicación
	 */
	public SQLSucursalProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SUCURSALPRODUCTO a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param idSucursal - La sucutsal dueña del producto.
	 * @param codigoBarrasProducto - El identificador del producto.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarSucursalProducto(PersistenceManager pm, long idSucursal, String codigoBarrasProducto)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursalProducto() + " (idsucursal, codigoBarrasProdcuto) VALUES (?, ?)");
		q.setParameters(idSucursal, codigoBarrasProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SUCURSALPRODUCTO de la base de datos por su identificador.
	 * @param pm - El manejador de persistencia .
	 * @param idSucursal - Identificador de la sucursal .
	 * @param producto - Identificador del producto.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalProducto(PersistenceManager pm, long idSucursal, String producto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursalProducto() + " WHERE idSucursal = ? AND codigoBarrasProdcuto = ?");
		q.setParameters(idSucursal, producto);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SUCURSALPRODUCTO de una sucursal.
	 * @param pm - El manejador de persistencia.
	 * @param idSucursal - Id de la sucursal de interes.
	 * @return Una lista de Objetos SucursalProducto que pertenecen a la sucursal buscada.
	 */
	public List<SucursalProducto> darProductosSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto() + " WHERE idSucursal = ?");
		q.setParameters(idSucursal);
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SUCURSALPRODUCTO con cierto producto.
	 * @param pm - EL manejador de persistencia.
	 * @param producto - Producto del que se realiza la busqueda.
	 * @return Una lista de objetos SucursalProducto con que tienen el producto de interes.
	 */
	public List<SucursalProducto> darSucursalesProducto(PersistenceManager pm, String producto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto() + " WHERE codigoBarrasProdcuto = ?");
		q.setParameters(producto);
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eencontrar la información de todos los SUCURSALPRODUCTO.
	 * @param pm - EL manejador de persistencia.
	 * @return Una lista de todos los objetos SucursalProducto.
	 */
	public List<SucursalProducto> darTodosProductosSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursalProducto());
		q.setResultClass(SucursalProducto.class);
		return (List<SucursalProducto>) q.executeList();
	}
}