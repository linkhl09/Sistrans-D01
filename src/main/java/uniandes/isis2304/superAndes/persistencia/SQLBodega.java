package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BODEGA de SuperAndes.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia.
 * 
 * @author Andrés Hernández
 */
class SQLBodega {

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
	public SQLBodega(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una BODEGA a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador de la bodega.
	 * @param capacidadVol - Capacidad númerica del volumen de la bodega.
	 * @param capacidadPeso - Capacidad númerica del peso de la bodega.
	 * @param tipo - Tipo de los objetos que puede almacenar la bodega.
	 * @param idSucursal - id de la sucursal a la que pertenece la bodega.
	 * @return
	 */
	public long adicionarBodega(PersistenceManager pm, long id, double capacidadVol, 
			double capacidadPeso, String tipo, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaBodega() + " (id, capacidadvol, capacidadpeso, tipo, idsucursal) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(id, capacidadVol, capacidadPeso, tipo, idSucursal);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTO de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador de la bodega.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarBodega(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaBodega() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la setencia SQL para encontrar la información de una BODEGA en la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El id de la bodega que se desea buscar.
	 * @return El objeto Bodega que tiene el identificador dado.
	 */
	public Bodega darBodega(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega() + "WHERE id = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(id);
		return (Bodega) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todas las bodegas de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista de objetos Bodega.
	 */
	public List<Bodega> darBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para  encontrar la información de las bodegas que pertenecen 
	 * @param pm - El manejador de persistencia.
	 * @param idSucursal - id de la sucursal a la que pertenecen las bodegas.
	 * @return Una lista de objetos Bodega que hacen parte de la sucursal identificada con el id dado.
	 */
	public List<Bodega> darBodegasSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaBodega() + "WHERE idsucursal = ?");
		q.setParameters(idSucursal);
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
}