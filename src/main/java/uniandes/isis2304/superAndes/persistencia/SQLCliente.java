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
	
	
	public long adicionarClienteEmpresa(PersistenceManager pm, String correoElectronico, String nombre, String empresa)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + " (correoelectronico, nombre, puntos, empresa) VALUES (?, ?, 0, ?)");
		q.setParameters(correoElectronico, nombre, empresa);
		return (long) q.executeUnique();
	}
	
	public long adicionarClientePersonaNatural(PersistenceManager pm, String correoElectronico, String nombre, String tipoDocumento, String documento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + " (correoelectronico, nombre, puntos, documentopn, tipoDocPN) VALUES (?, ?, 0, ?, ? ,?)");
		q.setParameters(correoElectronico, nombre, documento, tipoDocumento);
		return (long) q.executeUnique();		
	}
	
	public long eliminarCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCliente() + " WHERE correoElectronico = ?");
		q.setParameters(correoElectronico);
		return (long) q.executeUnique();
	}

	public Cliente darCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente() + "WHERE correoElectronico = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(correoElectronico);
		return (Cliente) q.executeUnique();
	}
	
	public List<Cliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}


	public long aumentarPuntos(PersistenceManager pm, String correoElectronico, int puntos)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCliente() + "SET puntos = puntos + ? WHERE correoElectronico = ?");
		q.setParameters(puntos, correoElectronico);
		return (long) q.executeUnique();
	}
}
