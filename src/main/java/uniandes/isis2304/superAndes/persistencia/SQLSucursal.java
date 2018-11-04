package uniandes.isis2304.superAndes.persistencia;

import java.sql.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Andrés Hernández
 */
class SQLSucursal {

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
	public SQLSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una SUCURSAL a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador de la sucursal.
	 * @param direccion - Dirección de la sucursal.
	 * @param ciudad - Ciudad de la sucursal.
	 * @param nombre - Nombre de la sucursal.
	 * @param segmentacionMercado - Segmentación de mercado de la sucursal.
	 * @param tamanio - Tamaño de la sucursal.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarSucursal(PersistenceManager pm, long id, String direccion, String ciudad,
								 String nombre, String segmentacionMercado, int tamanio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursal() + " (id, direccion, ciudad, nombre, segmentacionmercado, tamanio) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, direccion, ciudad, nombre, segmentacionMercado, tamanio);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar SUCURSAL de la base de datos por su id.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador de la sucursal.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar SUCURSAL de la base de datos por su nombre.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - El nombre de la sucursal a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal()+ "WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una SUCURSAL de la base de datos, por su id.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador de la sucursal.
	 * @return Objeto de tipo SUCURSAL que tiene el identificador dado.
	 */
	public Sucursal darSucursalPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal() +" WHERE id = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(id);
		return (Sucursal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una SUCURSAL de la base de datos, por su nombre.
	 * @param pm - El manejador de persistencia.
	 * @param nombre - El nombre de la sucursal que se quiere encontrar.
	 * @return Objeto tipo SUCURSAL que tiene el nombre dado por parametro.
	 */
	public Sucursal darSucursalPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal() +" WHERE nombre = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(nombre);
		return (Sucursal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todas las SUCURSALES de la base de datos. 
	 * @param pm - El manejador de persistencia.
	 * @return Lista de objetos Sucursal.
	 */
	public List<Sucursal> darSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
	/**
	 * RFC1 MUESTRA EL DINERO RECOLECTADO POR VENTAS EN CADA SUCURSAL DURANTE UN PERIODO 
	  DE TIEMPO Y EN EL AÑO CORRIDO
	 * @param psa - El Manejador de persistencia de la aplicació
	 *
	 */
	public  List<Object[]>  darDineroRecolectadoSucursales(PersistenceManager pm , java.util.Date fechaInicio, java.util.Date fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(VALORTOTAL) AS VALORTOTAL, IDSUCURSAL FROM FACTURA" + pm
				+ "WHERE EXTRACT(YEAR FROM FECHA) = EXTRACT(YEAR FROM (SELECT SYSDATE FROM DUAL))"
				+ " AND FECHA BETWEEN " + fechaInicio + "AND " + fechaFin + "GROUP BY factura.idsucursal");
		q.setResultClass(Proveedor.class);
		return (List<Object[]>) q.executeList();
	}
	
}
