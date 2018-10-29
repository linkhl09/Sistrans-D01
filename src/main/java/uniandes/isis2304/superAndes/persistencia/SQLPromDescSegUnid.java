package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromSegUniDesc;


public class SQLPromDescSegUnid 
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
	public  SQLPromDescSegUnid(PersistenciaSuperAndes pp)
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
	 **@param descuento -  porcentaje del descuento a  realizar   
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromDescSegUnid (PersistenceManager pm,long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromDescuento() + "(id, descripcion, unidadesDisponibles, uniadadesVendidas,"
        		+ "fechaInicio, fechaFin,producto,descuento) values (?, ?, ?, ?,?, ?,?,?)");
        q.setParameters(id,descripcion,unidadesDisponibles, unidadesVendidas,fechaInicio, fechaFin, producto, descuento);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PromDescSegUnid de la base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de PromDescSegUnid
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromDescSegUnidPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromDescSegUnid() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PromDescSegUnid de la 
	 * base de datos de SuperAndes, por su numeroPromo
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del PromDescSegUnid
	 * @return El objeto PromDescSegUnid que tiene el numeroPromo dado
	 */
	public  PromSegUniDesc darPromDescSegUnidPornumeroPromo (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromDescSegUnid() + " WHERE id = ?");
		q.setResultClass(PromSegUniDesc.class);
		q.setParameters(id);
		return (PromSegUniDesc)q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PromDescSegUnid de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PromDescSegUnid
	 */
	public List<PromSegUniDesc> darPromDescSegUnid (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromDescSegUnid());
		q.setResultClass(PromSegUniDesc.class);
		return (List<PromSegUniDesc>) q.executeList();
	}

}
