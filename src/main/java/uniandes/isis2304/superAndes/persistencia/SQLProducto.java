package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Producto;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PRODUCTO. 
 * N�tese que es una clase que es s�lo conocida en el paquete de persistencia
 * 
 * @author Andr�s Hern�ndez
 */
class SQLProducto {

	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
		
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes psa;
	
	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor.
	 * @param psa - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRODUCTO a la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarras - el C�digo de barras que identifica al nuevo producto.
	 * @param nombre - El nombre del nuevo producto.
	 * @param marca - Marca del nuevo Producto.
	 * @param precioUnitario - Precio unitario del nuevo producto.
	 * @param presentacion - presentaci�n del nuevo producto. 
	 * @param precioUnidadMedida - Precio por unidad de medida del nuevo prodcuto.
	 * @param cantidadPresentacion - Cantidad en la presentaci�n del producto.
	 * @param peso - Valor numerico del peso del producto. 
	 * @param unidadMedidaPeso - Unidad de medida del peso del producto.
	 * @param volumen - Valor n�merico del volumen del producto.
	 * @param unidadMedidaVolumen - Unidad de medida del volumen del producto.
	 * @param calidad - Calidad del nuevo producto.
	 * @param nivelReorden - Nivel de reorden del nuevo producto.
	 * @param fechaVencimiento - fehca de vencimiento del producto. Null si no es un producto perecedero.
	 * @param categoria - Categoria del producto.
	 * @param estaEnPromocion - Booleano que indica si el nuevo producto esta en promoci�n.
	 * @return El n�mero de tuplas insertadas.
	 */
	public long adicionarProducto(PersistenceManager pm, String codigoBarras, String nombre, String marca, 
			double precioUnitario, String presentacion, double precioUnidadMedida, int cantidadPresentacion, 
			double peso, String unidadMedidaPeso, double volumen, String unidadMedidaVolumen, double calidad, 
			int nivelReorden, Date fechaVencimiento, String categoria, boolean estaEnPromocion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProducto() + " (codigobarras, nombre, marca, preciounitario, presentacion, preciounidadmedida, cantidadpresentacion, peso, unidadmedidapeso, volumen, unidadmedidavolumen, calidad, nivelreorden, fechavencimiento, categoria, estaenpromocion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, 
						peso, unidadMedidaPeso, volumen, unidadMedidaVolumen, calidad, nivelReorden, fechaVencimiento,
						categoria, estaEnPromocion);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un PRODUCTO por su codigo de barras de la base de datos.
	 * @param pm - El manejador  de persistencia.
	 * @param codigoBarras - El c�digo de Barras del producto a eliminar.
	 * @return El n�mero de tuplas eliminadas.
	 */
	public long eliminarProductoCodigoBarras(PersistenceManager pm, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProducto() + " WHERE codigobarras = ?");
		q.setParameters(codigoBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la secuencia SQL para encontrar la informaci�n de un PRODUCTO de la base de datos, por su codigo de barras.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarras - El identificador del producto.
	 * @return El objeto Producto que tiene el identificador dado.
	 */
	public Producto darProducto(PersistenceManager pm, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProducto() + "WHERE codigobarras = ?");
		q.setResultClass(Producto.class);
		q.setParameters(codigoBarras);
		return (Producto) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informaci�n de todos los productos de la base de datos.
	 * @param pm - El manejador de persistencia.
	 * @return 
	 */
	public List<Producto> darProductos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para cambiar el estado de la promoci�n de un producto a verdadero.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarras - El identificador del producto que se desea modificar.
	 * @return El n�mero de tuplas modificadas.
	 */
	public long nuevaPromocion(PersistenceManager pm, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProducto() + "SET promocion = 1 WHERE codigobarras = ?");
		q.setParameters(codigoBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para cambiar el estado de una promici�n de un producto a falso.
	 * @param pm - El manejador de persistencia.
	 * @param codigoBarras - El identificador del producto que se desea modificar.
	 * @return El n�mero de tuplas modificadas.
	 */
	public long terminarPromocion(PersistenceManager pm, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProducto() + "SET promocion = 0 WHERE codigobarras = ?");
		q.setParameters(codigoBarras);
		return (long) q.executeUnique();
	}
}