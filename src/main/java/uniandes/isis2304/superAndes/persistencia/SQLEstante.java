package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Estante;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ESTANTE.
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Andrés Hernández.
 */
class SQLEstante {

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
	public SQLEstante(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Estante a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param id - El id del nuevo Estante.
	 * @param capacidadVol - Valor numerico de la capacidad de almacenamiento por volumen del nuevo estante.
	 * @param capacidadPeso - Valor numerico de la capacidad de almacenamiento por volumen del nuevo estante.
	 * @param tipo - Tipo de productos que puede almacenar el nuevo estante.
	 * @param idSucursal - Identificador de la sucursal a la que pertenece el nuevo estante.
	 * @return El número de tuplas insertadas.
	 */
	public long adicionarEstante(PersistenceManager pm, long id, double capacidadVol, 
			double capacidadPeso, String tipo, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaEstante() + " (id, capacidadvol, capacidadpeso, tipo, idsucursal) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(id, capacidadVol, capacidadPeso, tipo, idSucursal);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un estante de la base de datos por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del estante a eliminar.
	 * @return El numero de tuplas eliminadas.
	 */
	public long eliminarEstante(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEstante() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un ESTANTE en la base de datos, por su identificador.
	 * @param pm - El manejador de persistencia.
	 * @param id - El identificador del estante buscado.
	 * @return El objeto de tipo Estante que tiene el identificador dado. 
	 */
	public Estante darEstante(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante() + "WHERE id = ?");
		q.setResultClass(Estante.class);
		q.setParameters(id);
		return (Estante) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los estantes.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista con todos los Estantes de la base de datos.
	 */
	public List<Estante> darEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos los estantes dado un id.
	 * @param pm - EL manejador de persistencia.
	 * @param idSucursal - El id de la sucursal a la  que pertenecen los estantes.
	 * @return Una lista de objetos Estante que pertenecen a la sucursal preguntada.
	 */
	public List<Estante> darEstantesSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEstante() + "WHERE idSucursal = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idSucursal);
		return (List<Estante>) q.executeList();
	}
}