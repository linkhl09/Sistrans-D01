package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import oracle.net.aso.p;
import uniandes.isis2304.superAndes.negocio.PromDesc;
import uniandes.isis2304.superAndes.negocio.PromocionSucursal;

/**
 * Clase que encapsula los m閠odos que hacen acceso a la base de datos para el concepto PROMOCION DESCUENTO. 
 * N髏ese que es una clase que es s髄o conocida en el paquete de persistencia
 * 
 * @author Jenifer Rodriguez
 */
public class SQLPromocionSucursal {
	
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
	public  SQLPromocionSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Promocion-Sucursal a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - identificador de la promocion
	 * @param idSucursal - identificador de la sucursal
	 * @return El n煤mero de tuplas insertadas
	 */
	public long adicionarPromocionSucursal (PersistenceManager pm,long idPromocion,long idSucursal ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionSucursal() + "(idPromocion, idSucursal"
        		+ ") values (?, ?)");
        q.setParameters(idPromocion,idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PromocionSucursal de la base de datos de SuperAndes, por su idPromocion 
	 * Y su idSucursal
	 * @param pm- El manejador de persistencia
	 * @param idPromocion - El identificador de la promocion
	 * @param idSucursal- El identificador de la sucursal
	 * @return EL n煤mero de tuplas eliminadas
	 */
	public long eliminarPromocionDescuento (PersistenceManager pm, long idPromocion, long idSucursal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionSucursal() + " WHERE idPromocion = ? AND idSucursal = ? ");
        q.setParameters(idPromocion, idSucursal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de todas las  Promocion  de la  Sucursal de la 
	 * base de datos de SuperAndes
	 * @param pm- El manejador de persistencia
	 * @param idSucursal- El identificador de la sucursal
	 * @return Una lista de los objetos Promocion sucursal  que tienen el id sucursal dado
	 */
	public  List<PromocionSucursal> darPromocionesSucursal (PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal() + " WHERE idSucursal = ?");
		q.setResultClass(PromocionSucursal.class);
		q.setParameters(idSucursal);
		return (List<PromocionSucursal>)q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de todas las  sucursales que tienen una promocion dada de la 
	 * base de datos de SuperAndes
	 * @param pm- El manejador de persistencia
	 * @param idPromocion- El identificador de la promocion
	 * @return Una lista de los objetos Promocion sucursal  que tienen el id promocion dado
	 */
	public  List<PromocionSucursal> darSucursalesConPromocion (PersistenceManager pm, long idPromocion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal() + " WHERE idPromocion = ?");
		q.setResultClass(PromocionSucursal.class);
		q.setParameters(idPromocion);
		return (List<PromocionSucursal>)q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de todas las Promociones Sucursal de la 
	 * base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PromocionSucursal
	 */
	public List<PromocionSucursal> darTodasPromocionSucursal
	
	(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocionSucursal());
		q.setResultClass(PromocionSucursal.class);
		return (List<PromocionSucursal>) q.executeList();
	}
}