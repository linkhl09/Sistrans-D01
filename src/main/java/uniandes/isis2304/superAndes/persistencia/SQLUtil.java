package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto.
 * N�tese que es una clase que es s�lo conocida en el paquete persistencia.
 * 
 * @author Andr�s Hern�ndez y Jenifer Rodriguez
 */
class SQLUtil {

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
	 * Constructor
	 * @param psa - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLUtil(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo n�mero de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El n�mero de secuencia generado
	 */
	public long nextval(PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ psa.darSeqSuperAndes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 21 n�meros que indican el n�mero de tuplas borradas en las tablas ProveedoresProducto, ProductoOrdenPedido, FacturaProducto,
	 * ClienteSucursal, ProductosEnBodega, ProductosEnEstante, SucursalProducto, HistorialPromociones, Bodega, Estante, Promocion, Producto, Categoria, Tipo,
	 * Factura, PersonaNatural, Empresa, Cliente, OrdenPedido, Sucursal, Proveedor.
	 */
	public long[] limpiarSuperAndes(PersistenceManager pm)
	{
		Query qProveedoresProducto		= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedoresProducto() );
		Query qProductoOrdenPedido	 	= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoOrdenPedido() );
		Query qFacturaProducto 			= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFacturaProducto() );
		Query qProductosEnBodega 		= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnBodega() );
		Query qProductosEnEstante		= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductosEnEstante() );
		Query qSucursalProducto 		= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursalProducto() );
		Query qProductoCarritoCompras	= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoCarritoCompras());
		Query qTipoProducto				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipoProducto());
		Query qCarritoCompras 			= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCarritoCompras() );
		Query qFactura 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura() );
		Query qCliente 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCliente() );
		Query qBodega 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaBodega() );
		Query qEstante 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEstante() );
		Query qCategoria 				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria() );
		Query qTipo 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipo() );
		Query qPersonaNatural 			= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPersonaNatural() );
		Query qEmpresa 					= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEmpresa() );
		Query qOrdenPedido 				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOrdenPedido() );
		Query qSucursal 				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal() );	
		Query qProveedor 				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor() );	
		Query qPromDescuento 			= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromDescuento());
		Query qPromPagueLleveUnidad 	= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPagLleveUnida());
		Query qPromPagueLleveCant 	    = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPagLleveCant());
		Query qPromSegUniDescuento 	    = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromDescSegUnid());
		Query qProducto 				= pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProducto() );
		
		
		long proveedoresProductoEliminados   = (long) qProveedoresProducto.executeUnique();
		long productoOrdenPedidoEliminados   = (long) qProductoOrdenPedido.executeUnique();
		long facturaProductoEliminados       = (long) qFacturaProducto.executeUnique();
		long productosEnBodegaEliminados     = (long) qProductosEnBodega.executeUnique();
		long productosEnEstanteEliminados    = (long) qProductosEnEstante.executeUnique();
	    long sucursalProductoEliminados      = (long) qSucursalProducto.executeUnique();
	    long productoCarritoComprasEliminados= (long) qProductoCarritoCompras.executeUnique();
	    long tipoProductoEliminados			 = (long) qTipoProducto.executeUnique();
	    long carritoComprasEliminados		 = (long) qCarritoCompras.executeUnique();
	    long facturaEliminados               = (long) qFactura.executeUnique();
	    long clienteEliminados               = (long) qCliente.executeUnique();	
	    long bodegaEliminados                = (long) qBodega.executeUnique();
		long estanteEliminados               = (long) qEstante.executeUnique();
		long categoriaEliminados             = (long) qCategoria.executeUnique();
		long tipoEliminados                  = (long) qTipo.executeUnique();
		long personaNaturalEliminados        = (long) qPersonaNatural.executeUnique();
		long empresaEliminados               = (long) qEmpresa.executeUnique();
		long ordenPedidoEliminados           = (long) qOrdenPedido.executeUnique();
		long sucursalEliminados              = (long) qSucursal.executeUnique();
		long proveedorEliminados             = (long) qProveedor.executeUnique();
		long promDescuentoEliminados         = (long) qPromDescuento.executeUnique();
		long promSegUniDescuentoEliminados   = (long) qPromSegUniDescuento.executeUnique();
		long promPagLleveUniEliminados       = (long) qPromPagueLleveUnidad.executeUnique();
		long promPagLleveCantEliminados      = (long) qPromPagueLleveCant.executeUnique();
		long productoEliminados              = (long) qProducto.executeUnique();
			
		return new long[] {proveedoresProductoEliminados,productoOrdenPedidoEliminados, facturaProductoEliminados ,productosEnBodegaEliminados, 
				productosEnEstanteEliminados , sucursalProductoEliminados, productoCarritoComprasEliminados, tipoProductoEliminados, 
				carritoComprasEliminados, facturaEliminados , clienteEliminados, bodegaEliminados , estanteEliminados, categoriaEliminados, tipoEliminados, 
				personaNaturalEliminados , empresaEliminados, ordenPedidoEliminados , sucursalEliminados, proveedorEliminados, promDescuentoEliminados,
				promSegUniDescuentoEliminados , promPagLleveUniEliminados, promPagLleveCantEliminados, productoEliminados };
	}
}