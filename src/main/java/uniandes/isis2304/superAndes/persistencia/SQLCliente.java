package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CLIENTE. 
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
class SQLCliente 
{
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
	 * Constructor.
	 * @param psa - El Manejador de persistencia de la aplicación
	 */
	public SQLCliente(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Adiciona un nuevo cliente empresa a la tabla "Clientes"
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
	 * Adiciona un nuevo cliente natural a la tabla "Clientes"
	 * se incializan los puntos en 0
	 * @param correoElectronico - correo electronico del cliente es el identificador unico de la tabla clientes
	 * @param nombre - El nombre del cliente
	 * @param documento - es El identificador unico del cliente cuando es una persona natural es una llave foranea de la tabla "PersonaNatural"
	 */
	public long adicionarClientePersonaNatural(PersistenceManager pm, String correoElectronico, String nombre, String documento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + " (correoelectronico, nombre, puntos, documentopn) VALUES (?, ?, 0, ? )");
		q.setParameters(correoElectronico, nombre, documento);
		return (long) q.executeUnique();		
	}
	
	/**
	 * Elimina un cliente por su correo electronico
     * @param correoElectronico - correo electronico del cliente, es el identificador unico de la tabla clientes
	*/
	public long eliminarCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCliente() + " WHERE correoElectronico = ?");
		q.setParameters(correoElectronico);
		return (long) q.executeUnique();
	}
	
	/**
	 * Devuelve la informacion del cliente 
     * @param correoElectronico - correo electronico del cliente del cual se quiere la informacion, es el identificador unico de la tabla clientes
	*/
	public Cliente darCliente(PersistenceManager pm, String correoElectronico)
	{
		Query q = pm.newQuery(SQL, "SELECT correoElectronico, nombre, puntos FROM " + psa.darTablaCliente() + "WHERE correoElectronico = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(correoElectronico);
		return (Cliente) q.executeUnique();
	}
	
	/**
	 * Devuelve la informacion de todos los clientes
	 **/
	public List<Cliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT correoElectronico, nombre, puntos FROM " + psa.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	/**
	 * Devuelve todos los correos electronicos de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return Una lista con todos los correos de la base de datos.
	 */
	public List<String> darTodosLosCorreos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT correoElectronico FROM " + psa.darTablaCliente());
		q.setResultClass(String.class);
		return (List<String>) q.executeList();
	}

	/**
	 * Aumenta los puntos de un cliete dado
	 * @param correoElectronico - correo electronico del cliente del cual se quiere la informacion, es el identificador unico de la tabla clientes
	 * @param puntos - cantidad de puntos q se van a agregar al cleiete
	 */
	public long aumentarPuntos(PersistenceManager pm, String correoElectronico, int puntos)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCliente() + "SET puntos = puntos + ? WHERE correoElectronico = ?");
		q.setParameters(puntos, correoElectronico);
		return (long) q.executeUnique();
	}	
	
	/**
	 * Devuelve a todos los clientes que realizaron al menos una compra de un producto especifico
	 * en un rango de fechas
	 * @param codigoProducto -codigo del producto que se busca el cliente halla comprado
	 * @param fechaInicio -Rango de fechas de la busqueda (inicio del rango)
	 * @param fechaFin --Rango de fechas de la busqueda (final del rango)
	 *  */
	public List<Cliente>  darClientesRealizaronCompra(PersistenceManager pm, String codigoProducto, String fechaInicio, String  fechaFin)
	{
		Query q = pm.newQuery(SQL, "select CORREOELECTRONICO, NOMBRE, PUNTOS,EMPRESA,DOCUMENTOPN from( select factura from factura_producto  where producto = '" +codigoProducto+ "' ) a join (select *  from (( select numero , cliente from factura where fecha BETWEEN '" +fechaInicio+ "' AND '" +fechaFin+ "')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero");
	//	q.setParameters(codigoProducto, fechaInicio, fechaFin);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	/**
	 * Devuelve a todos los clientes que realizaron al menos una compra de un producto especifico
	 * en un rango de fechas ordenados por nombre
	 * @param codigoProducto -codigo del producto que se busca el cliente halla comprado
	 * @param fechaInicio -Rango de fechas de la busqueda (inicio del rango)
	 * @param fechaFin --Rango de fechas de la busqueda (final del rango)
	 *  */
	public List<Cliente>  ordenarPorNombreR10(PersistenceManager pm, String codigoProducto, String fechaInicio, String  fechaFin)
	{
		Query q = pm.newQuery(SQL, "select CORREOELECTRONICO, NOMBRE, PUNTOS,EMPRESA,DOCUMENTOPN from( select factura from factura_producto  where producto = '" +codigoProducto+ "' ) a join (select *  from (( select numero , cliente from factura where fecha BETWEEN '" +fechaInicio+ "' AND '" +fechaFin+ "')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero order by nombre");
	//	q.setParameters(codigoProducto, fechaInicio, fechaFin);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	/**
	 * Devuelve a todos los clientes que realizaron al menos una compra de un producto especifico
	 * en un rango de fechas ordenados por puntos
	 * @param codigoProducto -codigo del producto que se busca el cliente halla comprado
	 * @param fechaInicio -Rango de fechas de la busqueda (inicio del rango)
	 * @param fechaFin --Rango de fechas de la busqueda (final del rango)
	 *  */
	public List<Cliente>  ordenarPorPuntosR10(PersistenceManager pm, String codigoProducto, String fechaInicio, String  fechaFin)
	{
		Query q = pm.newQuery(SQL, "select CORREOELECTRONICO, NOMBRE, PUNTOS,EMPRESA,DOCUMENTOPN from( select factura from factura_producto  where producto = '" +codigoProducto+ "' ) a join (select *  from (( select numero , cliente from factura where fecha BETWEEN '" +fechaInicio+ "' AND '" +fechaFin+ "')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero order by puntos");
	//	q.setParameters(codigoProducto, fechaInicio, fechaFin);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	/**
	 * Devuelve a todos los clientes que realizaron al menos una compra de un producto especifico
	 * en un rango de fechas ordenados por nombre
	 * @param codigoProducto -codigo del producto que se busca el cliente halla comprado
	 * @param fechaInicio -Rango de fechas de la busqueda (inicio del rango)
	 * @param fechaFin --Rango de fechas de la busqueda (final del rango)
	 *  */
	public List<Cliente>  ordenarPorFechaR10(PersistenceManager pm, String codigoProducto, String fechaInicio, String  fechaFin)
	{
		Query q = pm.newQuery(SQL, "select CORREOELECTRONICO, NOMBRE, PUNTOS,EMPRESA,DOCUMENTOPN from( select factura from factura_producto  where producto = '" +codigoProducto+ "' ) a join (select *  from (( select numero , cliente, fecha from factura where fecha BETWEEN '" +fechaInicio+ "' AND '" +fechaFin+ "')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero order by fecha");
	//	q.setParameters(codigoProducto, fechaInicio, fechaFin);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	/**
	 * Devuelve a todos los clientes que realizaron al menos una compra de un producto especifico
	 * en un rango de fechas ordenados por nombre
	 * @param codigoProducto -codigo del producto que se busca el cliente halla comprado
	 * @param fechaInicio -Rango de fechas de la busqueda (inicio del rango)
	 * @param fechaFin --Rango de fechas de la busqueda (final del rango)
	 *  */
	public List<Cliente>  ordenarPorUnidadesR10(PersistenceManager pm, String codigoProducto, String fechaInicio, String  fechaFin)
	{
		Query q = pm.newQuery(SQL, "select * from( select factura, cantidad from factura_producto  where producto = '" +codigoProducto+ "' ) a join (select *  from (( select numero , cliente from factura where fecha BETWEEN '" +fechaInicio+ "' AND '" +fechaFin+ "')  c join(select * from cliente)d on c.cliente= d.correoelectronico )) b on a.factura=b.numero order by cantidad");
	//	q.setParameters(codigoProducto, fechaInicio, fechaFin);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

}