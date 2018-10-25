package uniandes.isis2304.superAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromPagueLleveUnid;


public class SQLPromPagLlevUnidad
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public  SQLPromPagLlevUnidad (PersistenciaSuperAndes pp)
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
	 **@param pague -  unidades del producto que se debe pagar  
	 **@param lleve - unidades del producto que se llevara 
	 * @return El número de tuplas insertadas
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
	 * Crea y ejecuta la sentencia SQL para eliminar PromPagLlevUnidad de la base de datos de SuperAndes, por su numeroPromo
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la promocion PromPagLlevUnidad
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromPagLlevUnidadPornumeroPromo (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromPagLleveUnida() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PromPagLlevUnidad de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del PromPagLlevUnidad
	 * @return El objeto PromPagLlevUnidad que tiene el id dado
	 */
	public  PromPagueLleveUnid darPromPagLlevUnidadPornumeroPromo (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromPagLleveUnida() + " WHERE numero_promo = ?");
		q.setResultClass(PromPagueLleveUnid.class);
		q.setParameters(id);
		return (PromPagueLleveUnid)q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PromPagueLleveUnid de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PromPagueLleveUnid
	 */
	public List<PromPagueLleveUnid> darPromPagueLleveUnid (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromPagLleveUnida());
		q.setResultClass(PromPagueLleveUnid.class);
		return (List<PromPagueLleveUnid>) q.executeList();
	}


}
