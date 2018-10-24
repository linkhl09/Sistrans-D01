package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;

class SQLCliente {

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
	public SQLCliente(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * adiciona un nuevo cliente empresa a la tabla "Clientes"
	 * se incializan los puntos en 0
	 * @param correoElectronico - correo electronico del cliente es el identificador unico de la tabla clientes
	 * @param nombre - El nombre del cliente
	 * @param empresa - es El identificador unico de la empresa "NIT" es una llave foranea de la tabla "Empresas"
	 */
	public long adicionarClienteEmpresa(PersistenceManager pm, String correoElectronico, String nombre, String empresa)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + " (correoelectronico, nombre, puntos, empresa) VALUES (?, ?, 0, ?)");
		q.setParameters(correoElectronico, nombre, empresa);
		return (long) q.executeUnique();
	}
	
	/**
	 * adiciona un nuevo cliente natural a la tabla "Clientes"
	 * se incializan los puntos en 0
	 * @param correoElectronico - correo electronico del cliente es el identificador unico de la tabla clientes
	 * @param nombre - El nombre del cliente
	 * @param documento - es El identificador unico del cliente cuando es una persona natural es una llave foranea de la tabla "PersonaNatural"
	 */
	public long adicionarClientePersonaNatural(PersistenceManager pm, String correoElectronico, String nombre, String documento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + " (correoelectronico, nombre, puntos, documentopn) VALUES (?, ?, 0, ?, ? )");
		q.setParameters(correoElectronico, nombre, documento);
		return (long) q.executeUnique();		
	}
	
	/**
	 * elimina un cliente por su correo electronico
     * @param correoElectronico - correo electronico del cliente, es el identificador unico de la tabla clientes
	*/
	public long eliminarCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCliente() + " WHERE correoElectronico = ?");
		q.setParameters(correoElectronico);
		return (long) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion del cliente 
     * @param correoElectronico - correo electronico del cliente del cual se quiere la informacion, es el identificador unico de la tabla clientes
	*/
	public Cliente darCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente() + "WHERE correoElectronico = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(correoElectronico);
		return (Cliente) q.executeUnique();
	}
	
	/**
	 * devuelve la informacion de todos los clientes
	 **/
	public List<Cliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

	/**
	 * aumenta los puntos de un cliete dado 
	* @param correoElectronico - correo electronico del cliente del cual se quiere la informacion, es el identificador unico de la tabla clientes
	* @param puntos - cantidad de puntos q se van a agregar al cleiete
	*/
	public long aumentarPuntos(PersistenceManager pm, String correoElectronico, int puntos)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCliente() + "SET puntos = puntos + ? WHERE correoElectronico = ?");
		q.setParameters(puntos, correoElectronico);
		return (long) q.executeUnique();
	}
	
}
