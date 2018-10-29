package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import oracle.net.aso.p;
import uniandes.isis2304.superAndes.negocio.PromDesc;

public class SQLPromDescuento 
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
	public  SQLPromDescuento(PersistenciaSuperAndes pp)
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
	public long adicionarPromDescuento (PersistenceManager pm,long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromDescuento() + "(id, descripcion, unidadesDisponibles, uniadadesVendidas,"
        		+ "fechaInicio, fechaFin,producto,descuento) values (?, ?, ?, ?,?, ?,?,?)");
        q.setParameters(id,descripcion,unidadesDisponibles, unidadesVendidas,fechaInicio, fechaFin, producto, descuento);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PromDescuento de la base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la promDescuento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPromDescuentoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromDescuento() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PromDescuento de la 
	 * base de datos de SuperAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param numeroPromo - El identificador del PromDescuento
	 * @return El objeto PromDescuento que tiene el numeroPromo dado
	 */
	public  PromDesc darPromDescuentoPorid (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromDescuento() + " WHERE id = ?");
		q.setResultClass(PromDesc.class);
		q.setParameters(id);
		return (PromDesc)q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PromDescuento de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PromDescuento
	 */
	public List<PromDesc> darTodasPromDescuento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromDescuento());
		q.setResultClass(PromDesc.class);
		return (List<PromDesc>) q.executeList();
	}

	


}
