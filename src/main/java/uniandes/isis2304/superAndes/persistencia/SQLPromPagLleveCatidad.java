package uniandes.isis2304.superAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.sun.org.apache.bcel.internal.generic.LLOAD;

import uniandes.isis2304.superAndes.negocio.PromPagueLleveCant;

/**
 * Clase que encapsula los m閠odos que hacen acceso a la base de datos para el concepto PROM PAG LLEVE CANTIDAD  
 * N髏ese que es una clase que es s髄o conocida en el paquete de persistencia
 * 
 * @author Jenifer Rodriguez
 */
public class SQLPromPagLleveCatidad 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac谩 para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci贸n
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			M茅todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci贸n
	 */
	public  SQLPromPagLleveCatidad (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param pague -  cantidad del producto que se debe pagar  
	 **@param lleve -  cantidad del producto que se llevara 
	 * @return El n煤mero de tuplas insertadas
	 */
	public long adicionarPromDescuento (PersistenceManager pm,long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve  ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromDescuento() + "(id, descripcion, unidadesDisponibles, uniadadesVendidas,"
        		+ "fechaInicio, fechaFin,producto,pague, lleve) values (?, ?, ?, ?,?, ?,?,?,?)");
        q.setParameters(id,descripcion,unidadesDisponibles, unidadesVendidas,fechaInicio, fechaFin, producto,pague, lleve );
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PromPagLleveCatidad de la base de datos de SuperAndes, por su numeroPromo
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la promocion PromPagLleveCatidad
	 * @return EL n煤mero de tuplas eliminadas
	 */
	public long eliminarPromPagLleveCatidadPornumeroPromo (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromPagLleveCant() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de UN PromPagLleveCatidad de la 
	 * base de datos de SuperAndes, por su numeroPromo
	 * @param pm - El manejador de persistencia
	 * @param numeroPromo - El identificador del PromPagLleveCatidad
	 * @return El objeto PromPagLleveCatidad que tiene el numeroPromo dado
	 */
	public  PromPagueLleveCant darPromPagLleveCatidadPornumeroPromo (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromPagLleveCant() + " WHERE id = ?");
		q.setResultClass(PromPagueLleveCant.class);
		q.setParameters(id);
		return (PromPagueLleveCant)q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de LOS PromPagLleveCatidad de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PromPagLleveCatidad
	 */
	public List<PromPagueLleveCant> darPromPagLleveCatidad (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromPagLleveCant());
		q.setResultClass(PromPagueLleveCant.class);
		return (List<PromPagueLleveCant>) q.executeList();
	}
}