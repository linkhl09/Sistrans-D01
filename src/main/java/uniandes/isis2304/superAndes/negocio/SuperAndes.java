package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio.
 * Satisface todos los requerimientos funcionales del negocio.
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
public class SuperAndes {

	// -----------------------------------------------------------------
	// Constantes.
	// -----------------------------------------------------------------

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes psa;

	// -----------------------------------------------------------------
	// Constructores.
	// -----------------------------------------------------------------

	/**
	 * El constructor por defecto
	 */
	public SuperAndes()
	{
		psa = PersistenciaSuperAndes.getInstance();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes(JsonObject tableConfig)
	{
		psa = PersistenciaSuperAndes.getInstance(tableConfig);
	}

	// -----------------------------------------------------------------
	// Métodos Generales
	// -----------------------------------------------------------------

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		psa.cerrarUnidadPersistencia ();
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de SuperAndes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos 
	 * @return Un arreglo con 21 números que indican el número de tuplas borradas en las tablas ProveedoresProducto, ProductoOrdenPedido, FacturaProducto,
	 * ClienteSucursal, ProductosEnBodega, ProductosEnEstante, SucursalProducto, HistorialPromociones, Bodega, Estante, Promocion, Producto, Categoria, Tipo,
	 * Factura, PersonaNatural, Empresa, Cliente, OrdenPedido, Sucursal, Proveedor.
	 */
	public long[] limpiarSuperAndes()
	{
		log.info ("Limpiando la BD de SuperAndes");
		long [] borrrados = psa.limpiarSuperAndes();	
		log.info ("Limpiando la BD de SuperAndes: Listo!");
		return borrrados;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Tipo
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un tipo.
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo.
	 * @return El objeto Tipo adicionado. null si ocurre alguna Excepción
	 */
	public Tipo adicionarTipo (String nombre)
	{
		log.info ("Adicionando Tipo: " + nombre);
		Tipo tipo = psa.adicionarTipo(nombre);		
		log.info ("Adicionando Tipo : " + tipo);
		return tipo;
	}

	/**
	 * Elimina un tipo por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarTipo(String nombre)
	{
		log.info ("Eliminando Tipo por su identificador: " + nombre);
		long resp = psa.eliminarTipo(nombre);		
		log.info ("Eliminando Tipo por su identificador: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Tipo> darTipos()
	{
		log.info ("Consultando Tipos.");
		List<Tipo> tipos = psa.darTipos();	
		log.info ("Consultando Tipos: " + tipos.size() + " existentes");
		return tipos;
	}

	/**
	 * Encuentra el Tipo de SuperAndes con el nombre solicitado.
	 * @param nombre - El nombre del tipo.
	 * @return Un objeto Tipo con el tipo de ese nombre que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Tipo darTipo(String nombre)
	{
		log.info ("Dar información de Tipo");
		Tipo buscado = psa.darTipo(nombre); 
		log.info ("Buscando tipo: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Categoria
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente una categoria.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombre - El nombre de la categoria.
	 * @return El objeto Categoria adicionado. Null si ocurre alguna Exception.
	 */
	public Categoria adicionarCategoria(String nombre)
	{
		log.info ("Adicionando categoria: " + nombre);
		Categoria agregado = psa.adicionarCategoria(nombre);		
		log.info ("Adicionada categoria: " + nombre);
		return agregado;
	}

	/**
	 * Elimina una Categoria por su nombre.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombre - El nombre de la categoria a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarCategoria(String nombre) 
	{
		log.info ("Eliminando categoria");
		long resp = psa.eliminarCategoria(nombre);		
		log.info ("Eliminada: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todas las categorias de SuperAndes.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos Categoria con todas las categorias que conoce la aplicación.
	 * Llenos con su información básica.
	 */
	public List<Categoria> darCategorias()
	{
		log.info ("Consultando categorias.");
		List<Categoria> list = psa.darCategorias();	
		log.info ("Consultando categorias: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las categorias de SuperAndes y las devuelve como una lista de VOCategoria.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOCategoria con todas las categorias que conoce la aplicación.
	 * Llenos con su información básica.
	 */
	public List<VOCategoria> darVOCategoria()
	{
		log.info ("Generando los VO de categoria");        
		List<VOCategoria> list = new LinkedList<VOCategoria> ();
		for (Categoria tb : psa.darCategorias())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la categoria en SuperAndes con el nombr dado.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombre - El nombre de la categoria.
	 * @return Un objeto Categoria con la categoria de ese nombre que conoce la aplicación.
	 * Lleno con su información násica.
	 */
	public Categoria darCategoria (String nombre)
	{
		log.info ("Dar información de ");
		Categoria buscado = psa.darCategoria(nombre);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla TipoCategoria
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un tipo categoria.
	 * Adiciona entradas al log de la aplicación.
	 * @param nombreTipo - nombre del tipo asociado a la categoria.
	 * @param nombreCategoria - Nombre de la categoria.
	 * @return El objeto TipoCategoria adicionado. Null si ocurre alguna Exception.
	 */
	public TipoProducto adicionarTipoCategoria(String nombreTipo, String nombreCategoria)
	{
		log.info("Adicionando TipoCategoria: nombreTipo->" + nombreTipo +" nombreCategoria->" + nombreCategoria);
		TipoProducto agregado = psa.adicionarTipoProducto(nombreTipo, nombreCategoria);
		log.info("Adicionado TipoCategoria");
		return agregado;
	}
	
	/**
	 * Elimina un TipoCategoria por su identificador.
	 * @param nombreTipo - nombre del tipo asociado a la categoria.
	 * @param nombreCategoria - Nombre de la categoria.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarTipoCategoria(String nombreTipo, String nombreCategoria)
	{
		log.info("Eliminando TipoCategoria");
		long resp = psa.eliminarTipoProducto(nombreTipo, nombreCategoria);
		log.info("Eliminada: " + resp + " tuplas eliminadas.");
		return resp;
	}
	
	/**
	 * Encuentra todos las tipos del producto SuperAndes.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos TipoProducto con todos los tipos-prodcuto que conoce la aplicación.
	 * Llenos con su información básica.
	 */
	public List<TipoProducto> darTiposProducto(String codigoBarrasProducto)
	{
		log.info("Consultando los tipos de un producto.");
		List<TipoProducto> list = psa.darTiposDelProducto(codigoBarrasProducto);
		log.info("Consultando TiposProducto: " + list.size() + " existentes.");
		return list;
	}

	/**
	 * Encuentra todas los productos con su tipo de SuperAndes.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos TipoProducto con todas las productos con sus tipo que conoce la aplicación.
	 * Llenos con su información básica.
	 */
	public List<TipoProducto> darTodosTipoCategoria()
	{
		log.info("Consultando todos los tipoCategoria.");
		List<TipoProducto> list = psa.darTodosTipoProducto();
		log.info("Consultando todos los tipoCategoria: " + list.size() + " existentes.");
		return list;
	}
	
	/**
	 * Encuentra todas los TipoProducto de SuperAndes y las devuelve como una lista de VOTipoProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOTipoProducto con todas los productos con sus tipos que conoce la aplicación.
	 * Llenos con su información básica.
	 */
	public List<VOTipoProducto> darVOTipoCategoria()
	{
		log.info("Generando los VO de TipoCategoria");
		List<VOTipoProducto> list = new LinkedList<VOTipoProducto>();
		for(TipoProducto tb: psa.darTodosTipoProducto())
		{
			list.add(tb);
		}
		log.info("Generando los VO de : " + list.size() + " existentes.");
		return list;
	}
	
	// -----------------------------------------------------------------
	// Métodos de tabla Producto
	// -----------------------------------------------------------------

	
	/**
	 * Adiciona de manera persistente un producto.
	 * Adiciona entradas al log de la aplicación.
	 * @param codigoBarras codigo de barras del producto.
	 * @param nombre nombre del producto.
	 * @param marca marca del producto.
	 * @param precioUnitario precio unitario del producto.
	 * @param presentacion presentacion del producto.
	 * @param precioUnidadMedida precio por unidad de medida del producto.
	 * @param cantidadPresentacion cantidad en la presentacion del producto.
	 * @param peso peso del producto.
	 * @param unidadMedidaPeso unidad de medida del peso del producto.
	 * @param volumen volumen del producto.
	 * @param unidadMedidaVolumen unidad de medida del volumen del producto.
	 * @param calidad calidad del producto.
	 * @param nivelReorden nivel de re orden del producto.
	 * @param fechaVencimiento fecha de vencimiento del producto.
	 * @param categoria categoria del producto.
	 * @param promocion boolean que indica si el producto esta en promocion.
	 * @return El objeto Producto adicionado. Null si ocurre alguna Exception.
	 */
	public  Producto adicionarProducto(String codigoBarras, String nombre, String marca, 
			double precioUnitario, String presentacion, double precioUnidadMedida, int cantidadPresentacion, 
			double peso, String unidadMedidaPeso, double volumen, String unidadMedidaVolumen, double calidad, 
			int nivelReorden, Date fechaVencimiento, String categoria, boolean promocion)
	{
		log.info ("Adicionando producto: " + nombre  );
		Producto agregado = psa.adicionarProducto(codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, peso, unidadMedidaPeso, volumen, unidadMedidaVolumen, calidad, nivelReorden, fechaVencimiento, categoria, promocion);
		log.info ("Adicionado el producto.");
		return agregado;
	}

	/**
	 * Elimina un producto por su codigo de barras.
	 * @param codigoBarras - codigo de barras del producto a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarProducto(String codigoBarras) 
	{
		log.info ("Eliminando Producto con codigo de barras :" + codigoBarras);
		long resp = psa.eliminarProducto(codigoBarras);
		log.info ("Eliminando Producto: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los productos en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Producto con todos los productos que conoce la aplicación, llenos con su información básica
	 */
	public List<Producto> darProductos()
	{
		log.info ("Consultando productos");
		List<Producto> list = psa.darProductos();	
		log.info ("Consultando productos: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas los producto de SuperAndes y las devuelve como una lista de VOProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProducto con todas los productos que conoce la aplicación.
	 */
	public List<VOProducto> darVOProducto ()
	{
		log.info ("Generando los VO de Productos");        
		List<VOProducto> list = new LinkedList<VOProducto> ();
		for (Producto tb : psa.darProductos())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de productos: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra el Producto de SuperAndes con el codido de barras solicitado.
	 * @param codigoBarras - El codigo de barars  del producto.
	 * @return Un objeto producto con el codigo de barras solicitado que conoce la aplicacion.
	 * Lleno con su información básica.
	 */
	public Producto darProducto(String codigoBarras)
	{
		log.info ("Dar información de Producto con codigo de barras: "+ codigoBarras);
		Producto buscado = psa.darProducto(codigoBarras);
		log.info ("Buscando Producto: " + buscado != null ? buscado.toString() : "NO EXISTE");
		return buscado;
	}

	/**
	 * actualiza el estado del producto con el codigo de barras ingresado y lo coloca como "en promocion" 
	 * @param codigoBarras - El codigo de barras del producto.
	 * @return el numero de tuplas actualizadas
	 */
	public long nuevaPromocion(String codigoBarras)
	{
		log.info("Se cambio el estado del producto a en promoción.");
		long cambios = psa.nuevaPromocion(codigoBarras);
		return cambios;
	}

	/**
	 * actualiza el estado del producto con el codigo de barras ingresado y lo coloca como "fuera de promocion" 
	 * @param codigoBarras - El codigo de barras del producto.
	 * @return el numero de tuplas actualizadas
	 */
	public long terminarPromocion(String codigoBarras)
	{
		log.info("Se cambio el estado del producto fuera de promoción.");
		long cambios = psa.terminarPromocion(codigoBarras);
		return cambios;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla sucursal
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente una sucursal.
	 * Adiciona entradas al log de la aplicación
	 * @param id id generado por el sistema para la sucursal.
	 * @param direccion dirección de la nueva sucursal.
	 * @param ciudad Ciudad de la nueva sucursal.
	 * @param nombre Nombre de la nueva sucursal.
	 * @param segmentacionMercado Segmentación de mercado de la nueva sucursal.
	 * @param tamanio Tamaño de la nueva sucursal.
	 * @return El objeto Sucursal adicionado. null si ocurre alguna Excepción
	 */
	public Sucursal adicionarSucursal(String direccion, String ciudad,
			String nombre, String segmentacionMercado, int tamanio)
	{
		log.info ("Adicionando Sucursal: " + nombre +".");
		Sucursal agregado = psa.adicionarSucursal(direccion, ciudad, nombre, segmentacionMercado, tamanio);	
		log.info ("La sucursal fue adicionada.");
		return agregado;
	}

	/**
	 * Elimina una Sucursal por su nombre.
	 * @param nombre - nombre del tipo asociado a la categoria.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursal(String nombre) 
	{
		log.info ("Eliminando Sucursal por nombre.");
		long resp = psa.eliminarSucursalPorNombre(nombre);
		log.info ("Eliminando sucursal: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Elimina una sucursal por su id.
	 * @param id - id de la sucursal a eliminar.
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalPorId(long id) 
	{
		log.info ("Eliminando Sucursal por id.");
		long resp = psa.eliminarSucursalPorId(id);
		log.info ("Eliminando sucursal por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/**
	 * Encuentra todos las sucursales  en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Sucursal con todos las sucursales  que conoce la aplicación, llenos con su información básica
	 */
	public List<Sucursal> darSucursales()
	{
		log.info ("Consultando Sucursales.");
		List<Sucursal> list = psa.darSucursales();	
		log.info ("Consultando sucursales: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las sucursales de SuperAndes y las devuelve como una lista de VOSucursal.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOSucursal con todas las  sucursales que conoce la aplicación.
	 */
	public List<VOSucursal> darVOSucursal ()
	{
		log.info ("Generando los VO de Sucursal");        
		List<VOSucursal> list = new LinkedList<VOSucursal> ();
		for (Sucursal tb : psa.darSucursales())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Sucursal: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la sucursal de SuperAndes con el nombre solicitado.
	 * @param nombre - El nombre de la sucursal.
	 * @return Un objeto Sucursal con la sucursal con ese nombre que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Sucursal  darSucursalPorNombre(String nombre)
	{
		log.info ("Dar información de Sucursal con nombre: " + nombre + ".");
		Sucursal buscado = psa.darSucursalPorNombre(nombre); 
		log.info ("Buscando Sucursal: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Encuentra la sucursal de SuperAndes con el id solicitado.
	 * @param id - El identificador de la sucursal.
	 * @return Un objeto Sucursal con la sucursal con ese id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Sucursal  darSucursalPorId(long id)
	{
		log.info ("Dar información de Sucursal con el id: "+ id +".");
		Sucursal buscado = psa.darSucursalPorId(id); 
		log.info ("Buscando Sucursal: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla SucursalProducto
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto a la clase relacion sucursalProducto.
	 * Adiciona entradas al log de la aplicación
	 * @param idSucursal id de la sucursal a la que pertenece el producto.
	 * @param codigoBarras producto asociado a la sucursal.
	 * @return El objeto SucursalProducto adicionado. null si ocurre alguna Excepción
	 */
	public SucursalProducto adicionarSucursalProducto(long idSucursal, String codigoBarrasProducto)
	{
		log.info ("Adicionando SucursalProducto: id de la sucursal" + idSucursal + "codigo de barras del produucto" + codigoBarrasProducto);
		SucursalProducto agregado = psa.adicionarSucursalProducto(idSucursal, codigoBarrasProducto);	
		log.info ("Adicionado SucursalProducto.");
		return agregado;
	}

	/**
	 * Elimina una sucursalProducto por su idSucursal y su codigo de producto.
	 * @param idSucursal - id de la sucursal .
	 * @param producto - codigo de barras del producto .
	 * @return El número de tuplas eliminadas.
	 */
	public long eliminarSucursalProducto(long idSucursal, String producto)
	{
		log.info ("Eliminando SucursalProducto");
		long resp = psa.eliminarSucursalProducto(idSucursal, producto);
		log.info ("Eliminando SucursalProducto: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todas los productos de la sucursal  cuyo cidentificador es igual al ingresado por parametro en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos SucursalProducto con todas los productos de la sucursal  que conoce la aplicación, llenos con su información básica
	 */
	public List<SucursalProducto> darProductosSucursal(long idSucursal)
	{
		log.info ("Consultando SucursalProducto de una sucursal.");
		List<SucursalProducto> list = psa.darProductosSucursal(idSucursal);
		log.info ("Consultando SucursalProducto: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las sucursales que tienen el producto cuyo codigo de barras es igual al ingresado por parametro en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos SucursalProducto con todas las sucursales que tienen el producto  que conoce la aplicación, llenos con su información básica
	 */
	public List<SucursalProducto> darSucursalesProducto(String codigoBarrasProducto )
	{
		log.info ("Consultando SucursalProducto de un producto.");
		List<SucursalProducto> list = psa.darSucursalesProducto(codigoBarrasProducto);	
		log.info ("Consultando SucursalProducto: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos los productos de todas la sucursales en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos SucursalProducto con todos los productos de todas la sucursales que conoce la aplicación, llenos con su información básica
	 */
	public List<SucursalProducto> darTodosProductosSucursales()
	{
		log.info ("Consultando todos los SucursalProducto");
		List<SucursalProducto> list = psa.darTodosProductosSucursales();	
		log.info ("Consultando los SucursalProducto: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las sucursales con todos sus productos de SuperAndes y las devuelve como una lista de VOSucursalProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOSucursalProducto
     * con todas las sucursales con  sus productos  que conoce la aplicación.
	 */
	public List<VOSucursalProducto> darVOSucursalProducto ()
	{
		log.info ("Generando los VO de SucursalProducto");        
		List<VOSucursalProducto> list = new LinkedList<VOSucursalProducto> ();
		for (SucursalProducto tb : psa.darTodosProductosSucursales())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de SucursalProducto: " + list.size() + " existentes");
		return list;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Bodega
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto Bodega.
	 * Adiciona entradas al log de la aplicación
	 * @param capacidadPeso - capacidad en peso de la bodega, por defecto en Kg.
	 * @param capacidadVolumen - Capacidad en volumen de la bodega, por defecto en m^3.
	 * @param tipo - Tipo de la bodega.
	 * @param idSucursal - identificador de la surcursal .
	* @return El objeto Bodega adicionado. null si ocurre alguna Excepción
	 */
	public Bodega adicionarBodega(double capacidadVol, double capacidadPeso, String tipo, long idSucursal)
	{
		log.info ("Adicionando Bodega");
		Bodega agregado = psa.adicionarBodega(capacidadVol, capacidadPeso, tipo, idSucursal);	
		log.info ("Bodega adicionada");
		return agregado;
	}

	/**
	 * Elimina una bodega por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id de la bodega a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarBodega(long id) 
	{
		log.info ("Eliminando Bodega con el id: " + id);
		long resp = psa.eliminarBodega(id);
		log.info ("Eliminando bodega: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las bodegas en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bodega con todos las Bodegas que conoce la aplicación, llenos con su información básica
	 */
	public List<Bodega> darBodegas ()
	{
		log.info ("Consultando Bodegas.");
		List<Bodega> list = psa.darBodegas();
		log.info ("Consultando Bodegas: " + list.size() + " existentes");
		return list;
	}
	
	/**
	 * Encuentra todos las bodegas de una sucursal en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bodega con todas las bodegas de una sucursal que conoce la aplicación, llenos con su información básica
	 * @param idSucursal - El id de la sucursal de la cual se quieren conocer las bodegas
	 */
	public List<Bodega> darBodegasSucursal(long idSucursal)
	{
		log.info ("Consultando Bodegas por sucursal.");
		List<Bodega> list = psa.darBodegasSucursal(idSucursal);
		log.info ("Consultando bodegas: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las bodegas de SuperAndes y las devuelve como una lista de VOBodega.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOBodega
     * con todas las bodegas que conoce la aplicación.
	 */
	public List<VOBodega> darVOBodega()
	{
		log.info ("Generando los VO de Bodega");        
		List<VOBodega> list = new LinkedList<VOBodega> ();
		for (Bodega tb : psa.darBodegas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Bodega: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la bodega de SuperAndes con el id solicitado.
	 * @param id - El id de la bodega.
	 * @return Un objeto Bodega con la bodega de ese id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Bodega darBodega(long id)
	{
		log.info ("Dar información de Bodega por id.");
		Bodega buscado = psa.darBodega(id);
		log.info ("Buscando Bodega: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla estante
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto Estante.
	 * Adiciona entradas al log de la aplicación
	 * @param capacidadPeso - capacidad en peso del estante, por defecto en Kg.
	 * @param capacidadVolumen - Capacidad en volumen del estante, por defecto en m^3.
	 * @param tipo - Tipo del estante. 
	 * @param idSucursal - id de la surcursal del estante.
	 * @return El objeto Estante adicionado. null si ocurre alguna Excepción
	 */
	public Estante adicionarEstante(double capacidadVolumen, double capacidadPeso, String tipo, long idSucursal)
	{
		log.info ("Adicionando Estante.");
		Estante agregado = psa.adicionarEstante(capacidadVolumen, capacidadPeso, tipo, idSucursal);	
		log.info ("Estante adicionado.");
		return agregado;
	}

	/**
	 * Elimina un estante por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id  del estantea a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarEstante(long id)
	{
		log.info ("Eliminando Estante con id: " + id + ".");
		long resp = psa.eliminarEstante(id);
		log.info ("Eliminando Estante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Estantes  en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos los estantes que conoce la aplicación, llenos con su información básica
	 */
	public List<Estante> darEstantes()
	{
		log.info ("Consultando todos los Estantes");
		List<Estante> list = psa.darEstantes();	
		log.info ("Consultando Estantes: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas los estantes de SuperAndes y las devuelve como una lista de VOEstante.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOEstante
     * con todas los estantes que conoce la aplicación.
	 */
	public List<VOEstante> darVOEstante ()
	{
		log.info ("Generando los VO de Estante");        
		List<VOEstante> list = new LinkedList<VOEstante> ();
		for (Estante tb : psa.darEstantes())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Estante: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra el estante de SuperAndes con el id solicitado.
	 * @param id - El id del estante.
	 * @return Un objeto Estante con el estante de ese id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Estante darEstante(long id)
	{
		log.info ("Dar información del Estante con id: " + id + ".");
		Estante buscado = psa.darEstante(id); 
		log.info ("Buscando Estante: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Encuentra todos los estantes de una sucursal en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Estante con todos los estantes de una sucursal que conoce la aplicación, llenos con su información básica
	 * @param idSucursal - El id de la sucursal de la cual se quieren conocer los estantes
	 */
	public List<Estante> darEstantePorSucursal(long idSucursal)
	{
		log.info ("Consultando Estantes por sucursal.");
		List<Estante> list = psa.darEstantesPorSucursal(idSucursal);	
		log.info ("Consultando estantes por sucursal: " + list.size() + " existentes");
		return list;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnBodega
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto en la clase relacion productosEnBodega.
	 * Adiciona entradas al log de la aplicación
	 * Constructor con valores.
	 * @param idBodega id de la bodega donde se almacenará el producto.
	 * @param cantidad Cantidad de unidades del producto almacenado en bodega.
	 * @param nivelAbastecimiento nivel de abastecimiento de ese producto en la bodega.
	 * @param codigoBarrasProducto
	 * @return El objeto ProductosEnBodega adicionado. null si ocurre alguna Excepción
	 */
	public ProductosEnBodega adicionarProductosEnBodega(long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto)
	{
		log.info ("Adicionando ProductosEnBodega: producto: "+ codigoBarrasProducto + " y bodega con id: "+ idBodega);
		ProductosEnBodega agregado = psa.adicionarProductosEnBodega(idBodega, cantidad, nivelAbastecimiento, codigoBarrasProducto);	
		log.info ("Adicionado el producto a la bodega.");
		return agregado;
	}

	/**
	 * Elimina un producto de una bodega
	 * Adiciona entradas al log de la aplicación
	 * @param idBodega - El id de la bodega a la cual se le eliminara el producto
	 * @param codigoBarras - El codigo de barras del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoEnBodega(long idBodega, String codigoBarrasProducto) 
	{
		log.info ("Eliminando ProductosEnBodega: producto: " + codigoBarrasProducto + " de la bodega: " + idBodega);
		long resp = psa.eliminarProductoEnBodega(idBodega, codigoBarrasProducto);
		log.info ("Eliminando ProductoEnBodega: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los productos de una bodega en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param idBdega - El id de la bodega de la cual se quieren conocer sus productos
	 * @return Una lista de objetos ProductosEnBodega con todos los productos de la bodega que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEnBodega> darProductosEnBodega(long idBodega)
	{
		log.info ("Consultando ProductosEnBodega con id: " + idBodega);
		List<ProductosEnBodega> list = psa.darProductosEnBodega(idBodega);
		log.info ("Consultando ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las bodegas con todos sus productos en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductosEnBodega con todas las bodegas con todos sus productos que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEnBodega> darTodosProductosBodegas()
	{
		log.info ("Consultando todos los ProductosEnBodega.");
		List<ProductosEnBodega> list = psa.darTodosProductosBodegas();
		log.info ("Consultando todos los ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos las bodegas que tienen un producto en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param codigoBarrasProducto - El codigo de  barras del producto 
	 * @return Una lista de objetos ProductosEnBodega con todas las bodegas que tienen el producto que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEnBodega> darBodegasProducto(String codigoBarrasProducto)
	{
		log.info ("Consultando ProductosEnBodega bodegas con el producto: " + codigoBarrasProducto);
		List<ProductosEnBodega> list = psa.darBodegasProducto(codigoBarrasProducto);	
		log.info ("Consultando ProductosEnBodega bodegas con el producto: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las bodegas y todos sus productos de SuperAndes y las devuelve como una lista de VOProductosEnBodega.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProductosEnBodega
     * con todas las bodegas y todos sus productos que conoce la aplicación.
	 */
	public List<VOProductosEnBodega> darVOProductosEnBodega()
	{
		log.info ("Generando los VO de ProductosEnBodega");        
		List<VOProductosEnBodega> list = new LinkedList<VOProductosEnBodega>();
		for (ProductosEnBodega tb : psa.darTodosProductosBodegas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Aumenta la cantidad del producto en la bodega SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param bodega - El id de la bodega 
	 * @param producto - El codigo de barras del producto
	 * @param productosPedidos - La cantidad de productos a adicionar
	 * @return el numero de tuplas actualizadas
	  */
	public long aumentarProductosEnBodega( long bodega, String producto, int productosPedidos )
	{
		//TODO aumentar productos en Bodega.
		log.info ("Aumentando la cantidad del producto en la bodega: producto: " + producto + " de la bodega: " + bodega);
		long resp = psa.aumentarProductosEnBodega(bodega, producto, productosPedidos);
		log.info ("Aumentada la cantidad del producto en bodega: " + resp + " tuplas actualizadas");
		return resp;
	}
	
	/**
	 * Disminuye la cantidad del producto en la bodega SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param bodega - El id de la bodega 
	 * @param producto - El codigo de barras del producto
	 * @param productosPedidos - La cantidad de productos a disminuir
	 * @return el numero de tuplas actualizadas
	  */
	public long disminuirProductosEnBodega(long bodega, String producto, int pasadosAEstante )
	{
		//TODO disminuir productos en Bodega.
		log.info ("Disminuyendo la cantidad del producto en la bodega: producto: " + producto + " de la bodega: " + bodega);
		long resp = psa.disminuirProductosEnBodega(bodega, producto, pasadosAEstante);
		log.info ("Disminuaida la cantidad del producto en bodega: " + resp + " tuplas actualizadas");
		return resp;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnEstante
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto en la clase relacion productosEnEstante.
	 * Adiciona entradas al log de la aplicación
	 * Constructor con valores.
	  * @param idEstante Id del estante donde se almacena el producto.
	 * @param cantidad Cantidad de productos que se almacenan en el estante.
	 * @param codigoBarrasProducto Codigo de barras del producto almacenado en el estante.
	 * @return El objeto ProductosEnEstante adicionado. null si ocurre alguna Excepción
	 */
	public ProductosEnEstante adicionarProductosEnEstante(long idEstante, int cantidad, String codigoBarrasProducto)
	{
		log.info ("Adicionando ProductosEnEstante: producto: " + codigoBarrasProducto + " al estante con id: " + idEstante);
		ProductosEnEstante agregado = psa.adicionarProductosEnEstante(idEstante, cantidad, codigoBarrasProducto);
		log.info ("Adicionado ProductoEnEstante.");
		return agregado;
	}

	/**
	 * Elimina un producto de un estante
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El id del estante a la cual se le eliminara el producto
	 * @param codigoBarras - El codigo de barras del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductosEnEstante(long idEstante, String codigoBarrasProducto)
	{
		log.info ("Eliminando ProductosEnEstante: producto: " + codigoBarrasProducto + " del estante: " + idEstante);
		long resp = psa.eliminarProductosEnEstante(idEstante, codigoBarrasProducto);
		log.info ("Eliminando producto del estante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los productos de un estante en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param idEstante - El id del estante del cual se quieren conocer sus productos
	 * @return Una lista de objetos ProductosEnEstante con todos los productos del estante que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEnEstante> darProductosEnEstante(long idEstante)
	{
		log.info ("Consultando ProductosEnEstante productos del estante: " + idEstante );
		List<ProductosEnEstante> list = psa.darProductosEnEstante(idEstante);	
		log.info ("Consultando ProductosEnEstante: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos los estantes con todos sus productos en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductosEnEstante con todas los estantes con todos sus productos que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductosEnEstante>  darTodosProductosEnEstantes()
	{
		log.info ("Consultando todos los ProductosEnEstante.");
		List<ProductosEnEstante> list = psa.darTodosProductosEnEstantes();
		log.info ("Consultando todos los productos en estante: " + list.size() + " existentes.");
		return list;
	}

	/**
	 * Encuentra todas los estantes y todos sus productos de SuperAndes y las devuelve como una lista de VOProductosEnEstante.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProductosEnEstante
     * con todos los estantes y todos sus productos que conoce la aplicación.
	 */
	public List<VOProductosEnEstante> darVOProductosEnEstante ()
	{
		log.info ("Generando los VO de ProductosEnEstante:");        
		List<VOProductosEnEstante> list = new LinkedList<VOProductosEnEstante> ();
		for (ProductosEnEstante tb : psa.darTodosProductosEnEstantes())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de ProductosEnEstante: " + list.size() + " existentes.");
		return list;
	}

	/**
	 * Encuentra el producto en el estante de SuperAndes con el id y el codigo de barras solicitado.
	 * @param idEstante - El id del estante del cual se quieren conocer sus productos
	 * @param codigoBarras -codigo de barras del producto
	 * @return Un objeto ProductosEnEstante con el producto en el estante de esos identificadores que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public ProductosEnEstante darProductoEnEstante (long idEstante,  String codigoBarrasProducto)
	{
		log.info ("Dar información de ProductosEnEstante del producto: " + codigoBarrasProducto + " en el estante: " + idEstante);
		ProductosEnEstante buscado = psa.darProductoEnEstante(idEstante, codigoBarrasProducto);
		log.info ("Buscando ProductosEnEstante: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Aumenta la cantidad del producto en el estante SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param estante - El id del estante
	 * @param producto - El codigo de barras del producto
	 * @param productosTraidos - La cantidad de productos a adicionar
	 * @return el numero de tuplas actualizadas
	  */
	public long aumentarCantidadProductosEnEstante(long idEstante, int productosTraidos, String codigoBarrasProducto)
	{
		log.info("Aumentar cantidad de Productos en el estante: " + idEstante);
		long cambios = psa.aumentarCantidadProductosEnEstante(idEstante, productosTraidos, codigoBarrasProducto);
		log.info("Cantidad de tuplas modificadas: " + cambios);
		return cambios;
	}

	/**
	 * Disminuye la cantidad del producto en el estante SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param estante - El id del estante 
	 * @param producto - El codigo de barras del producto
	 * @param productosAQuitar - La cantidad de productos a disminuir
	 * @return el numero de tuplas actualizadas
	  */
	public long quitarProductosEstante(long estante, int productosAQuitar, String producto)
	{
		log.info("Quitar productos del estante: " + estante);
		long cambios = psa.quitarProductosEstante(estante, productosAQuitar, producto);
		return cambios;
	}


	// -----------------------------------------------------------------
	// Métodos de tabla personaNatural 
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto PersonaNatural.
	 * Adiciona entradas al log de la aplicación
	 * @param documento - numero de documento del cleinte
	 * @param tipoDocumento - tipo de documenrto del cleiente
	 * @param correoElectronico -correo electronico del cleinte
	 * @param nombre - nombre del cliente 
	 * @param puntos - puntod que lleva acumulados el cliente
	 * @return El objeto PersonaNatural adicionado. null si ocurre alguna Excepción
	 */
	public PersonaNatural adicionarPersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre)
	{
		log.info ("Adicionando Persona natural: " + nombre);
		PersonaNatural agregado = psa.adicionarPersonaNatural(documento, tipoDocumento, correoElectronico, nombre);	
		log.info ("Adicionada Persona natural.");
		return agregado;
	}
	/**
	 * Elimina una persona natural por su documento y su correo electronico
	 * Adiciona entradas al log de la aplicación
	 * @param documento - El numero de documento de la persona natural a eliminar
	 * @param correoElectronico - El correo electronico de la persona natural a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long[] eliminarPersonaNatural(String documento, String correoElectronico)
	{
		log.info ("Eliminando Persona Natural con el documento: " + documento + " y correo: " + correoElectronico);
		long[] resp = psa.eliminarPersonaNatural(documento, correoElectronico);
		log.info ("Eliminando Persona Natural: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las personas naturales en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PersonaNatural con todos laspersonas naturales que conoce la aplicación, llenos con su información básica
	 */
	public List<PersonaNatural>darPersonasNaturales ()
	{
		log.info ("Consultando todas las personas naturales.");
		List<PersonaNatural> list = psa.darPersonasNaturales();
		log.info ("Consultando PersonasNaturales: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las personas naturales de SuperAndes y las devuelve como una lista de VOPersonaNatural.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOPersonaNatural
    * con todas las personas naturales que conoce la aplicación.
	 */
	public List<VOPersonaNatural> darVOPersonaNatural()
	{
		log.info ("Generando los VO de PersonaNatural");        
		List<VOPersonaNatural> list = new LinkedList<VOPersonaNatural> ();
		for (PersonaNatural tb : psa.darPersonasNaturales())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Personas naturales: " + list.size() + " existentes.");
		return list;
	}

	/**
	 * Encuentra la persona natural de SuperAndes con el documento solicitado.
	 * @param documento - El documento de la persona natural.
	 * @return Un objeto PersonaNatural con la persona natural de ese numero de documento que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public PersonaNatural darPersonaNatural (String documento)
	{
		log.info ("Dar información de Persona natural con el documento: " + documento);
		PersonaNatural buscado = psa.darPersonaNatural(documento);
		log.info ("Buscando Persona natural: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Empresa
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un objeto Empresa.
	 * Adiciona entradas al log de la aplicación
	 * @param nit - numero de identificacion unico de la empresa
	 * @param direccion - direccion de la empresa
	 * @param correoElectronico - correo electronico del cliente
	 * @param nombre - nombre de la empresa
	 * @param puntos - numero de puntos de  la empresa
	 * @return El objeto Empresa adicionado. null si ocurre alguna Excepción
	 */
	public Empresa adicionarEmpresa(String nit, String direccion, String correoElectronico, String nombre)
	{
		log.info ("Adicionando empresa con NIT: " + nit );
		Empresa agregado = psa.adicionarEmpresa(nit, direccion, correoElectronico, nombre);
		log.info ("Adicionada empresa.");
		return agregado;
	}

	/**
	 * Elimina una empresa por su nit y su correo electronico
	 * Adiciona entradas al log de la aplicación
	 * @param nit - El numero de identificacion de la empresa a eliminar
	 * @param correoElectronico - El correo electronico de la empresa a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long[] eliminarEmpresaPorNit(String nit, String correoElectronico)
	{
		log.info ("Eliminando Empresa con el NIT: " + nit);
		long[] resp = psa.eliminarEmpresaPorNit(nit, correoElectronico);
		log.info ("Eliminando Empresa: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Elimina una empresa por su direccion y su correo electronico
	 * Adiciona entradas al log de la aplicación
	 * @param direccion - la direccion de la empresa a eliminar
	 * @param correoElectronico - El correo electronico de la empresa a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long[] eliminarEmpresaPorDireccion(String direccion, String correoElectronico) 
	{
		log.info ("Eliminando empresa por dirección: " + direccion);
		long[] resp = psa.eliminarEmpresaPorDireccion(direccion, correoElectronico);
		log.info ("Eliminando empresa: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las empresas en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Empresa con todas las empresas que conoce la aplicación, llenos con su información básica
	 */
	public List<Empresa> darEmpresas()
	{
		log.info ("Consultando todas las empresas.");
		List<Empresa> list = psa.darEmpresas();
		log.info ("Consultando empresas: " + list.size() + " existentes");
		return list;
	}


	/**
	 * Encuentra todas las empresas de SuperAndes y las devuelve como una lista de VOEmpresa.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOEmpresa
     * con todas las empresas que conoce la aplicación.
	 */
	public List<VOEmpresa> darVOEmpresa ()
	{
		log.info ("Generando los VO de Empresa");        
		List<VOEmpresa> list = new LinkedList<VOEmpresa> ();
		for (Empresa tb : psa.darEmpresas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Empresa: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la empresa de SuperAndes con el nit solicitado.
	 * @param nit - El numerod e identificacion de la empresa.
	 * @return Un objeto Empresa con la empresa de ese nit que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Empresa darEmpresa(String nit)
	{
		log.info ("Dar información de Empresa con el NIT: " + nit);
		Empresa buscado = psa.darEmpresa(nit);
		log.info ("Buscando Empresa: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Cliente
	// -----------------------------------------------------------------

	/**
	 * Encuentra todos los clientes en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cliente con todos los clientes que conoce la aplicación, llenos con su información básica
	 */
	public List<Cliente> darClientes()
	{
		log.info ("Consultando Clientes: ");
		List<Cliente> list = psa.darClientes();	
		log.info ("Consultando Clientes: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos las clientes de SuperAndes y las devuelve como una lista de VOCliente.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOCliente
     * con todos los clientes que conoce la aplicación.
	 */
	public List<VOCliente> darVOCliente()
	{
		log.info ("Generando los VO de Clientes");        
		List<VOCliente> list = new LinkedList<VOCliente> ();
		for (Cliente tb : psa.darClientes())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de Clientes: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra el cliente de SuperAndes con el correo electronico solicitado.
	 * @param correoEelctronico - El correoElectronico del cliente.
	 * @return Un objeto Cliente con el cliente de ese corrreoElectronico que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Cliente darCliente (String correoElectronico)
	{
		log.info ("Dar información de Cliente");
		Cliente buscado = psa.darCliente(correoElectronico);
		log.info ("Buscando Cliente: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Aumenta los puntos del cliente en  SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param correo - El correo del cliente 
	 * @param puntos - La cantidad de puntos a adicionar
	 * @return el numero de tuplas actualizadas
	  */
	public long aumentarPuntos(String correoElectronico, int puntos)
	{
		log.info("Aumentar puntos del cliente.");
		long cambios = psa.aumentarPuntos(correoElectronico, puntos);
		log.info("Se aumentaron en "+ puntos + " los puntos del cliente.");
		return cambios;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla CarritoCompras
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un  Carrito de Compras.
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del nuevo carrito de compras.
	 * @param cliente -Correo eletrónico del cliente dueño del carrito de compras.
	 * @return El objeto CarritoCompras adicionado. null si ocurre alguna Excepción
	 */
	public CarritoCompras adicionarCarritoCompras(String cliente)
	{
		log.info("Adicionando carrito compras al cliente: " + cliente);
		CarritoCompras agregado = psa.adicionarCarritoCompras(cliente);
		log.info("Adicionado carritoCompras");
		return agregado;
	}

	/**
	 * Elimina un carrito de compras por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el id del carrito de compras a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCarritoCompras(long id)
	{
		log.info("Eliminando carritoCompras: " + id);
		long resp = psa.eliminarCarritoCompras(id);
		log.info("Eliminando CarritoCompras: " + id + " tuplas eliminadas.");
		return resp;
	}
	
	/**
	 * Encuentra el carrito de compras de SuperAndes con el id solicitado.
	 * @param id - El id del carrito de compras.
	 * @return Un objeto CarritoCompras con el carrito de compras de ese id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public CarritoCompras darCarritoComprasPorId(long id)
	{
		log.info("Dar información de CarritoCompras con id: " + id);
		CarritoCompras buscado = psa.darCarritoComprasPorId(id);
		log.info("Buscando CarritoComrpas: " + buscado != null ? buscado.toString() : "NO EXISTE.");
		return buscado;
	}
	
	/**
	 * Encuentra el carrito de compras de SuperAndes con el cliete solicitado.
	 * @param cliente - El correo electronico del cliente 
	 * @return Un objeto CarritoCompras con el carrito de compras que tiene el cliente pasado por parametro  que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public CarritoCompras darCarritoComprasPorCliente(String cliente)
	{
		log.info("Dar información de carrito de compras con cliente: " + cliente);
		CarritoCompras buscado = psa.darCarritoComprasPorCliente(cliente);
		log.info("Buscando CarritoComrpas: " + buscado != null ? buscado.toString() : "NO EXISTE.");
		return buscado;		
	}

	/**
	 * Encuentra todos los carritos de compras en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos CarritoCompras con todos los carritos de compras que conoce la aplicación, llenos con su información básica
	 */
	public List<CarritoCompras> darTodosCarritosCompras()
	{
		log.info("Consultando todos CarritosCompras.");
		List<CarritoCompras> list = psa.darTodosCarritosCompras();
		log.info("Consultando CarritosCompras: " + list.size() + " existentes." );
		return list;
	}
	
	/**
	 * Encuentra todos los carritos de compras de SuperAndes y las devuelve como una lista de VOCarritoCompras.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOCarritoCompras
     * con todos los carritos de compras que conoce la aplicación.
	 */
	public List<VOCarritoCompras> darVOCarritoCompras()
	{
		log.info("Generando los VO de CarritoCompras");
		List<VOCarritoCompras> list = new LinkedList<VOCarritoCompras>();
		for(CarritoCompras tb : psa.darTodosCarritosCompras())
			list.add(tb);
		log.info("Generando los VO de CarritoCompras: " + list.size() +" existentes.");
		return list;
	}
	
	// -----------------------------------------------------------------
	// Métodos de tabla Producto Carrito Compras
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un  producto a un carrito de compras.
	 * Adiciona entradas al log de la aplicación
	 * @param carrito - Identificador del carrito de compras.
	 * @param cantidad - Cantidad del producto en el carrito.
	 * @param codigoBarrasProducto - Identificador del producto puesto en el carrito.
	 * @return El objeto CarritoCompras adicionado. null si ocurre alguna Excepción
	 */
	public ProductoCarritoCompras adicionarProductoCarrito(long carrito, int cantidad, String codigoBarrasProducto)
	{
		log.info("Adicionando ProductoCarritoCompras: " + carrito);
		ProductoCarritoCompras agregado = psa.adicionarProductoCarrito(carrito, cantidad, codigoBarrasProducto);
		log.info("Adicionado ProductoCarritoCompras.");
		return agregado;
	}
	
	/**
	 * Elimina un producto del carrito de compras
	 * Adiciona entradas al log de la aplicación
	 * @param carrito - el id del carrito de compras del cual se eliminara el producto
	 * @param codigoBarrasProducto - el codigo de barras del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoCarrito(long carrito, String codigoBarrasProducto)
	{
		log.info("Eliminando CarritoCompras: carrito-"+carrito + " producto-" + codigoBarrasProducto);
		long resp = psa.eliminarProductoCarrito(carrito, codigoBarrasProducto);
		log.info("Eliminando CarritoCompras: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/**
	 * Encuentra el productoCarrito de SuperAndes con el id de carrito y codigo de barras de producto solicitado.
	 * @param carrito - El id del carrito.
	 * @param codigoBarrasProducto - El codigo de  barras del producto.
	 * @return Un objeto ProductoCarritoCompras con el productoCarrito de ese id de carrito y codigo de barras de producto que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public ProductoCarritoCompras darProductoCarrito(long carrito, String codigoBarrasProducto)
	{
		log.info("Consultando ProductoCarritoCompras: carrito-" + carrito + " producto-" + codigoBarrasProducto);
		ProductoCarritoCompras buscado = psa.darProductoCarrito(carrito, codigoBarrasProducto);
		log.info("Consultando carrito de compras" + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}
	
	/**
	 * Encuentra todos los productos de un carrito de compras SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param carrito - El id del carrito.
	 * @return Una lista de objetos ProductoCarritoCompras con todos los productos de un carrito dado que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoCarritoCompras> darTodosProductosDeUnCarrito(long carrito)
	{
		log.info("Consultando todos ProductoCarritoCompras");
		List<ProductoCarritoCompras> list = psa.darTodosProductosCarrito();
		log.info("Consultando todos ProductoCarritoCompras: " + list.size() + " existentes.");
		return list;
	}
	
	/**
	 * Aumenta la cantidad del producto en el carrito de compras SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param carrito - El id del carrito 
	 * @param producto - El codigo de barras del producto
	 * @param productosAgregados - La cantidad de productos a adicionar
	 * @return el numero de tuplas actualizadas
	  */
	public long aumentarUnidadesProductoCarritoCompras(long carrito, String codigoBarrasProducto , int productosAgregados)
	{
		log.info("Aumentando unidades del producto: " + codigoBarrasProducto + " en el carrito: " + carrito);
		long resp = psa.aumentarUnidadesProductoCarritoCompras(carrito, codigoBarrasProducto, productosAgregados);
		log.info("Aumentadas las unidades del producto en el carrito.");
		return resp;
	}
	
	/**
	 * Disminuye la cantidad del producto en el carrito en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param carrito - El id del producto 
	 * @param producto - El codigo de barras del producto
	 * @param productosPedidos - La cantidad de productos a disminuir
	 * @return el numero de tuplas actualizadas
	  */
	public long disminuirUnidadesProductoCarritoCompras(long carrito, String codigoBarrasProducto, int productosDevueltos)
	{
		log.info("Disminuyendo unidades del producto: " + codigoBarrasProducto + " en el carrito: " + carrito);
		long resp = psa.disminuirUnidadesProductoCarritoCompras(carrito, codigoBarrasProducto, productosDevueltos);
		log.info("Disminuidas las unidades del producto en el carrito.");
		return resp;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Factura
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente una factura.
	 * Adiciona entradas al log de la aplicación
	 * @param direccion direccion de la factura
	 * @param fecha Fecha de la factura.
	 * @param nombreCajero nombre del cajero de la factura.
	 * @param valorTotal valor total de la factura.
	 * @param pagoExitoso pago exitoso de la compra.
	 * @param puntosCompra puntos de la factura.
	 * @param correoCliente CLiente que realiza la compra.
	 * @param idSucursal identificador de la sucursal donde se realiza la compra
	 * @return El objeto Factura adicionado. null si ocurre alguna Excepción
	 */
	public Factura adicionarFactura( String direccion, 
			Date fecha, String nombreCajero, double valorTotal, boolean pagoExitoso, 
			int puntosCompra, String correoCliente, long idSucursal)
	{
		log.info ("Adicionando factura: " + fecha.toString() );
		Factura agregado = psa.adicionarFactura(direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, correoCliente, idSucursal);
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una factura por su numero
	 * Adiciona entradas al log de la aplicación
	 * @param numero - el id de la factura a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarFactura(long numero)
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarFactura(numero);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todas las facturas  en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Factura con todos las facturas que conoce la aplicación, llenos con su información básica
	 */
	public List<Factura> darFacturas()
	{
		log.info ("Consultando ");
		List<Factura> list = psa.darFacturas();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las facturas de SuperAndes y las devuelve como una lista de VOFactura.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOFactura
     * con todas las facturas que conoce la aplicación.
	 */
	public List<VOFactura> darVOFactura ()
	{
		log.info ("Generando los VO de ");        
		List<VOFactura> list = new LinkedList<VOFactura> ();
		for (Factura tb : psa.darFacturas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la factura de SuperAndes con el numero solicitado.
	 * @param numero - El numero de la factura.
	 * @return Un objeto Fatura con la factura de ese numero que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Factura darFactura(long numero)
	{
		log.info ("Dar información de ");
		Factura buscado = psa.darFactura(numero); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Factura_Prodcuto
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente unprducto a una factura.
	 * Adiciona entradas al log de la aplicación
	 * @param numero de la factura a  la que esta asociado el producto
	 * @param cantidad del producto
	 * @param codigo del producto asociado a la factura 
	 * @return El objeto FacturaProducto adicionado. null si ocurre alguna Excepción
	 */
	public FacturaProducto adicionarFacturaProducto(long factura, int cantidad, String producto)
	{
		log.info ("Adicionando FacturaProducto: numero de la factura -> " + factura + " producto ->" + producto);
		FacturaProducto agregado = psa.adicionarFacturaProducto(factura, cantidad, producto);	
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina todos los productos de una factura
	 * Adiciona entradas al log de la aplicación
	 * @param factura - el numero de la factura a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductosDeFactura(long factura)
	{
		log.info ("Eliminando FacturaProducto");
		long resp = psa.eliminarFacturaProductos(factura);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Elimina un producto de una factura
	 * Adiciona entradas al log de la aplicación
	 * @param factura - el numero de la factura  de la cual se eliminara el producto
	 * @param producto - el codigo del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarProductoDeFactura(long factura, String producto) 
	{
		log.info ("Eliminando FacturaProducto");
		long resp = psa.eliminarProductoDeFactura(factura, producto);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todas las facturas con todos sus productos en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos FacturaProducto todas las facturas con todos sus productos que conoce la aplicación, llenos con su información básica
	 */
	public List<FacturaProducto> darProductosFacturas()
	{
		log.info ("Consultando FacturasProductos");
		List<FacturaProducto> list = psa.darProductosFacturas();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las facturas con todos sus productos en SuperAndesy las devuelve como una lista de VOFacturaProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOFacturaProducto
     * con todas as facturas con todos sus productos que conoce la aplicación.
	 */
	public List<VOFacturaProducto> darVOFacturaProducto ()
	{
		log.info ("Generando los VO de FacturaProducto");        
		List<VOFacturaProducto> list = new LinkedList<VOFacturaProducto> ();
		for (FacturaProducto tb : psa.darProductosFacturas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de FacturaProducto: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la factura producto de SuperAndes con el numero de factura y codigo de barras de producto solicitado.
	 * @param numero - El numero de la factura.
	 * @param producto - El codigo de barras del producto. 
	 * @return Un objeto FacturaProducto con el FacturaProducto de ese  numero de factura y codigo de barras de producto  que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public FacturaProducto darProductoDeFactura( long factura, String producto)
	{
		log.info ("Dar información deProductoDeFactura");
		FacturaProducto buscado = psa.darProductoDeFactura(factura, producto);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	
	// -----------------------------------------------------------------
	// Métodos de tabla Proveedor
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un proveedor.
	 * Adiciona entradas al log de la aplicación
	 * @param nit - numero unico de identificacion del proveedor.
	 * @param nombre - nombre del proveedor. 
	 * @param calificacion de calidad -  calificacion de calidad de el proveedor 
	 * @return El objeto Proveedor adicionado. null si ocurre alguna Excepción
	 */
	public Proveedor adicionarProveedor(String nit, String nombre, double calificacion)
	{
		log.info ("Adicionando proveedor: " + nombre);
		Proveedor agregado = psa.adicionarProveedor(nit, nombre, calificacion);	
		log.info ("Adicionado Proveedor");
		return agregado;
	}

	/**
	 * Elimina un proveedor  por su nit
	 * Adiciona entradas al log de la aplicación
	 * @param nit - el numero de identificacion del proveedor a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarProveedorPorNit (String nit)
	{
		log.info ("Eliminando proveedor: nit :" + nit);
		long resp = psa.eliminarProveedorPorNit(nit);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Elimina un proveedor  por su nit
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - el nombre del proveedor a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarProveedorPorNombre(String nombre) 
	{
		log.info ("Eliminando proveedor");
		long resp = psa.eliminarProveedorPorNombre(nombre);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los proveedores en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Proveedor con todos los proveedores que conoce la aplicación, llenos con su información básica
	 */
	public List<Proveedor> darProveedores ()
	{
		log.info ("Consultando ");
		List<Proveedor> list = psa.darProveedores();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas los proveedores de SuperAndes y las devuelve como una lista de VOProveedor.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProveedor
     * con todas los proveedores que conoce la aplicación.
	 */
	public List<VOProveedor> darVOProveedor ()
	{
		log.info ("Generando los VO de ");        
		List<VOProveedor> list = new LinkedList<VOProveedor> ();
		for (Proveedor tb : psa.darProveedores())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra el proveedor de SuperAndes con el nit solicitado.
	 * @param nit - El nidentificador del proveedor 
	 * @return Un objeto Proveedor con el proveedor de ese nit que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public Proveedor darProveedor(String nit)
	{
		log.info ("Dar información de ");
		Proveedor buscado = psa.darProveedor(nit); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Modifica la calificacion  del proveedor de SuperAndes con el nit ingresado por parametro .
	 * @param nit - El identificador  del proveedor.
	 * @param nuevaCalificacion - la nueva calificacion que se le asignara al proveedor del proveedor.
	 * @return Un objeto Tipo con el tipo de ese nombre que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public long updateCalificacionProveedor(String nit, double nuevaCalificacion)
	{
		log.info("Actualizanco calificacion del proveedor");
		long cambios = psa.updateCalificacionProveedor(nit, nuevaCalificacion);
		return cambios;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla proveedoresProducto 
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un producto a un proveedor.
	 * Adiciona entradas al log de la aplicación
	 * @param proveedor - numero de identificacion del proveedor
	 * @param producto - codigo del producto
	 * @return El objeto ProveedoresProducto adicionado. null si ocurre alguna Excepción
	 */
	public ProveedoresProducto adicionarProveedoresProducto(String proveedor, String producto)
	{
		log.info ("Adicionando ProveedoresProducto: nit del proveedor" + proveedor + "codigo del producto" +producto );
		ProveedoresProducto agregado = psa.adicionarProveedoresProducto(proveedor, producto);
		log.info ("Adicionado ProveedorProducto ");
		return agregado;
	}
	/**
	 * Elimina producto de un proveedor  
	 * Adiciona entradas al log de la aplicación
	 * @param proveedor - el nit del proveedor a eliminar
	 * @param producto - el codigo de barras del producto a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarProveedoresProducto(String proveedor, String producto)
	{
		log.info ("Eliminando ProveedoresProducto: producto ->" + producto + "proveedor ->" + proveedor );
		long resp = psa.eliminarProveedoresProducto(proveedor, producto);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/**
	 * Encuentra todos los proveedores que tienen el producto ingresado por parametro en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param producto - el codigo de barras del producto 
	 * @return Una lista de objetos ProveedoresProducto con todos los proveedores que tienen el producto pasado por parametro que conoce la aplicación, llenos con su información básica
	 */	
	public List<ProveedoresProducto> darProveedoresProducto(String producto)
	{
		log.info ("Consultando ProveedoresProducto");
		List<ProveedoresProducto> list = psa.darProveedoresProducto(producto);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos los productos de un proveedor en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @param proveedor - el identificador del proveedor 
	 * @return Una lista de objetos ProveedoresProducto los productos de un proveedor  que conoce la aplicación, llenos con su información básica
	 */
	public List<ProveedoresProducto> darProductosProveedor(String proveedor)
	{
		log.info ("Consultando Productos Proveedor");
		List<ProveedoresProducto> list = psa.darProveedoresProducto(proveedor);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos los proveedores con todos sus productos en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProveedoresProducto con todos los proveedores con todos sus producto que conoce la aplicación, llenos con su información básica
	 */
	public List<ProveedoresProducto> darTodosProveedoresProductos()
	{
		log.info ("Consultando Productos Proveedor");
		List<ProveedoresProducto> list = psa.darTodosProveedoresProductos();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 *Encuentra todos los proveedores con todos sus productos en SuperAndes  y las devuelve como una lista de VOProveedoresProducto.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProveedoresProducto
         * con todos los proveedores con todos sus productos que conoce la aplicación.
	 */
	public List<VOProveedoresProducto> darVOProveedoresProducto ()
	{
		log.info ("Generando los VO de ");        
		List<VOProveedoresProducto> list = new LinkedList<VOProveedoresProducto> ();
		for (ProveedoresProducto tb : psa.darTodosProveedoresProductos())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla ordenPedido
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un producto a un proveedor.
	 * Adiciona entradas al log de la aplicación
	 * @param proveedor - numero de identificacion del proveedor
	 * @param producto - codigo del producto
	 * @return El objeto ProveedoresProducto adicionado. null si ocurre alguna Excepción
	 */
	public OrdenPedido adicionarOrdenPedido( Date fechaEsperadaEntrega
			, String proveedor, long idSucursal, String estado)
	{
		log.info ("Adicionando OrdenPedido");
		OrdenPedido agregado = psa.adicionarOrdenPedido(fechaEsperadaEntrega, proveedor, idSucursal, estado);	
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una orden de pedido  por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el numero de identificacion de la orden pedido a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarOrdenPedido(long id)
	{
		log.info ("Eliminando OrdenPedido ");
		long resp = psa.eliminarOrdenPedido(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todas las ordenes de pedido en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos OrdenPedido con todas las ordenes de pedido que conoce la aplicación, llenos con su información básica
	 */
	public List<OrdenPedido> darOrdenesPedidos()
	{
		log.info ("Consultando OrdenesPedidos");
		List<OrdenPedido> list = psa.darOrdenesPedidos();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las odenes de pedido de SuperAndes y las devuelve como una lista de VOOrdenPedido.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOOrdenPedido
     * con todas las odenes de pedido que conoce la aplicación.
	 */
	public List<VOOrdenPedido> darVOOrdenPedido ()
	{
		log.info ("Generando los VO de OrdenPedido");        
		List<VOOrdenPedido> list = new LinkedList<VOOrdenPedido> ();
		for (OrdenPedido tb : psa.darOrdenesPedidos())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de OrdenPedido: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la OrdenPedido de SuperAndes con el id solicitado.
	 * @param id - El id de la orden pedido.
	 * @return Un objeto OrdenPedido con la orden pedido de ese id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public OrdenPedido darOrdenPedido (long id)
	{
		log.info ("Dar información de OrdenPedido");
		OrdenPedido buscado = psa.darOrdenPedido(id);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 *Actualza la fecha de llegada de la orden de pedido y la calificacion de calidad de la orden 
	 * @param id - El id de la orden de pedido.
	 * @param fechaEntrega - La fecha en la que se registra la entrega de la orden de pedido.
	 * @param nuevaCalificacion - La calificacion de calidad  de la orden de pedido.
	 * @return el numero de tuplas actualizadas.
	 */
	public long registrarFechaLlegada(long id, Date fechaEntrega, double nuevaCalificacion)
	{
		log.info("Registrar fecha de llega del pedido.");
		long cambios = psa.registrarFechaLlegada(id, fechaEntrega, nuevaCalificacion);
		return cambios;
	}	
	
	// -----------------------------------------------------------------
	// Métodos de tabla ProductoOrdenPedido
	// -----------------------------------------------------------------

	/**
	 * Adiciona de manera persistente un producto a un Orden Pedido.
	 * Adiciona entradas al log de la aplicación
	 * @param pedido - identificador del pedido
	 * @param cantidad - cantidad del producto asociado a la orden
	 * @param calidad - calidad de los productos asociados 
	 * @param producto - codigo del producto
	 * @return El objeto ProductoOrdenPedido adicionado. null si ocurre alguna Excepción
	 */
	public ProductoOrdenPedido adicionarProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto, Date fechaAgregado)
	{
		log.info ("Adicionando  ProductoOrdenPedido: " );
		ProductoOrdenPedido agregado = psa.adicionarProductoOrdenPedido(pedido, cantidad, calidad, producto, fechaAgregado);
		log.info ("Adicionado");
		return agregado;
	}
	/**
	 * Encuentra todos los productos de todas las ordenes de pedido en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoOrdenPedido con todos los productos de todas las ordenes de pedido que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoOrdenPedido> darProductosOrdenPedidos()
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darProductosOrdenPedidos();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas los productos de la orden pedido  cuyo identificador es igual al ingresado por parametro en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoOrdenPedido con todas los productos de la orden  que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoOrdenPedido> darProductosDelPedido(long pedido)
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darProductosDelPedido(pedido);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra  el historial de ordenes de pedido en las que se pidio el producto ingresado por parametro en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos ProductoOrdenPedido en los cuales se pidio el producto ingresado por parametro  que conoce la aplicación, llenos con su información básica
	 */
	public List<ProductoOrdenPedido> darHistorialPedidosProducto(String producto)
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darHistorialPedidosProducto(producto);	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos los productos de todas las ordenes de pedido en SuperAndes y las devuelve como una lista de VOProductoOrdenPedido.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOProductoOrdenPedido
     * con todos los productos de todas las ordenes de pedido que conoce la aplicación.
	 */
	public List<VOProductoOrdenPedido> darVOProductoOrdenPedido ()
	{
		log.info ("Generando los VO de ProductoOrdenPedido");        
		List<VOProductoOrdenPedido> list = new LinkedList<VOProductoOrdenPedido> ();
		for (ProductoOrdenPedido tb : psa.darProductosOrdenPedidos())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de ProductoOrdenPedido: " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra el ProductoOrdenPedido de SuperAndes con el id de pedido y codigo de barras del producto solicitado.
	 * @param pedido - El id de la orden de pedido.
	 * @param producto - El codigo de barras del producto.
	 * @return Un objeto ProductoOrdenPedido con el ProductoOrdenPedido de ese id pedido y codigo de barras de producto que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public ProductoOrdenPedido darProductoOrdenPedido (long pedido, String producto)
	{
		log.info ("Dar información de ProductoOrdenPedido");
		ProductoOrdenPedido buscado = psa.darProductoOrdenPedido(pedido, producto);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Prom Descuento
	// -----------------------------------------------------------------
	/**

	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	* @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param descuento -  porcentaje del descuento a  realizar 
	 **/
	public PromDesc adicionarPromDescuento( long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento )
	{
		log.info ("Adicionando PromDescuento "  );
		PromDesc agregado = psa.adicionarPromocionDescuento(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una promDescuento  por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el numero de identificacion de la promDescuento a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarPromDescuento(long id)
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarPromDesc(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las promociones descuento en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromDesc con todas las promociones descuento que conoce la aplicación, llenos con su información básica
	 */
	public List<PromDesc> darPromDescuento()
	{
		log.info ("Consultando ");
		List<PromDesc> list = psa.darPromDescuento();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	/**
	 * Encuentra todas las promDescuento de SuperAndes y las devuelve como una lista de VOPromDesc.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOPromDesc
     * con todas las promDescuento que conoce la aplicación.
	 */
	public List<VOPromDesc> darVOPromDesc()
	{
		log.info ("Generando los VO de ");        
		List<VOPromDesc> list = new LinkedList<VOPromDesc>();
		for (PromDesc tb : psa.darPromDescuento())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la promocion descuento de SuperAndes con el id solicitado.
	 * @param id - El id de la promocion descuento.
	 * @return Un objeto PromDesc con la promocion descuento de esa id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public PromDesc darPromDescuentoPorId(long id)
	{
		log.info ("Dar información de ");
		PromDesc buscado = psa.darPromDescuentoPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


	// -----------------------------------------------------------------
	// Métodos de tabla Prom Pag Lleve unid
	// -----------------------------------------------------------------
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromPagueLleveUnid a la base de datos de SuperAndes
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param pague -  unidades del producto que se debe pagar  
	 **@param lleve - unidades del producto que se llevara 
	**/
	public PromPagueLleveUnid adicionarPromPagueLleveUnid( long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve  )
	{
		log.info ("Adicionando PromPagueLleveUnid "  );
		PromPagueLleveUnid agregado = psa.adicionarPromocionPagueLleveUnid(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una promocion pague lleve unidad  por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el numero de identificacion de la PromPagueLleveUnid a eliminar
	 * @return El número de tuplas eliminadas
	 */	
	public long eliminarPromPagLlevUnidadPorId(long id)
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarPromPagLleveUnidad(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra la promocion pague lleve unidad de SuperAndes con el id solicitado.
	 * @param id - El id de la promocion pague lleve unidad.
	 * @return Un objeto PromPagueLleveUnid con la promocion pague lleve unidad de esa id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public PromPagueLleveUnid darPromPagLlevUnidadPorId(long id)
	{
		log.info ("Dar información de ");
		PromPagueLleveUnid buscado = psa.darPromPagLlevUnidadPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Encuentra todas las promPagueLleveUnid de SuperAndes y las devuelve como una lista de VOPromPagueLleveUnid.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOPromPagueLleveUnid
     * con todas las promPagueLleveUnidad que conoce la aplicación.
	 */
	public List<VOPromPagueLleveUnid> darVOPromPagueLleveUnid ()
	{
		log.info ("Generando los VO de ");        
		List<VOPromPagueLleveUnid> list = new LinkedList<VOPromPagueLleveUnid>();
		for (VOPromPagueLleveUnid tb : psa.darPromPagueLleveUnid())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos las promociones pague lleve unidad en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromPagueLleveUnid con todas las promociones pague lleve unidad  que conoce la aplicación, llenos con su información básica
	 */
	public List<PromPagueLleveUnid> darPromPagueLleveUnid()
	{
		log.info ("Consultando ");
		List<PromPagueLleveUnid> list = psa.darPromPagueLleveUnid();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	
	// -----------------------------------------------------------------
	// Métodos de tabla Prom Desc Seg Unidad
	// -----------------------------------------------------------------
	
	/**

	 * Crea y ejecuta la sentencia SQL para adicionar un PromDescuento a la base de datos de SuperAndes
	* @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param descuento -  porcentaje del descuento a  realizar   
	 **/
	public PromSegUniDesc adicionarPromDescSegUnid( long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento )
	{
		log.info ("Adicionando PromDescuento "  );
		PromSegUniDesc agregado = psa.adicionarPromDescSegUnid(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, descuento);
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una promocion descuento segunda unidad por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el numero de identificacion de la PromSegUniDesc a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromDescSegUnidPorId(long id)
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarPromDescSegUnidPorId(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las promociones descuento segunda unidad en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromSegUniDesc con todas las promociones descuento segunda unidad que conoce la aplicación, llenos con su información básica
	 */
	public List<PromSegUniDesc> darPromDescSegUnid()
	{
		log.info ("Consultando ");
		List<PromSegUniDesc> list = psa.darPromDescSegUnid();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todas las promSegUniDescuento de SuperAndes y las devuelve como una lista de VOPromSegUniDesc.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOPromSegUniDesc
     * con todas las promSegUniDescuento que conoce la aplicación.
	 */
	public List<VOPromSegUniDesc> darVOPromSegUniDesc()
	{
		log.info ("Generando los VO de ");        
		List<VOPromSegUniDesc> list = new LinkedList<VOPromSegUniDesc>();
		for (PromSegUniDesc tb : psa.darPromDescSegUnid())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra la promocion descuento segunda unidad de SuperAndes con el id solicitado.
	 * @param id - El id de la promocion descuento.
	 * @return Un objeto PromSegUniDesc con la promocion descuento segunda unidad descuento de esa id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public PromSegUniDesc darPromDescSegUnidPorId(long id)
	{
		log.info ("Dar información de ");
		PromSegUniDesc buscado = psa.darPromDescSegUnidPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	
	// -----------------------------------------------------------------
	// Métodos de tabla Prom Pag Lleve Cantidad
	// -----------------------------------------------------------------
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PromPagueLleveUnid a la base de datos de SuperAndes
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param pague -  cantidad del producto que se debe pagar  
	 **@param lleve -  cantidad del producto que se llevara 
	 **/
	public PromPagueLleveCant adicionarPromPagueLleveCantidad( long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, double pague, double lleve  )
	{
		log.info ("Adicionando PromPagueLleveCant "  );
		PromPagueLleveCant agregado = psa.adicionarPromPagueLleveCant(id, descripcion, unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto, pague, lleve);
		log.info ("Adicionado");
		return agregado;
	}

	/**
	 * Elimina una promocion pague lleve cantidad por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - el numero de identificacion de la PromPagueLleveCant a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromPagLleveCatidadPorId(long id)
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarPromPagLleveCatidadPorId(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra la promocion pague lleve cantidad de SuperAndes con el id solicitado.
	 * @param id - El id de la promocion pague lleve cantidad.
	 * @return Un objeto PromPagueLleveCant con la promocion pague lleve cantidad de esa id que conoce la aplicación.
	 * Lleno con su información básica.
	 */
	public PromPagueLleveCant darPromPagueLleveCantPorId(long id)
	{
		log.info ("Dar información de ");
		PromPagueLleveCant buscado = psa.darPromPagueLleveCantPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	/**
	 * Encuentra todas las PromPagueLleveCant de SuperAndes y las devuelve como una lista de VOPromPagueLleveCant.
	 * Adiciona entradas al log de la aplicación.
	 * @return Una lista de objetos VOPromPagueLleveCant
     * con todas las PromPagueLleveCant que conoce la aplicación.
	 */
	public List<VOPromPagueLleveCant> darVOPromPagueLleveCant ()
	{
		log.info ("Generando los VO de ");        
		List<VOPromPagueLleveCant> list = new LinkedList<VOPromPagueLleveCant>();
		for (VOPromPagueLleveCant tb : psa.darPromPagLleveCatidad())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	/**
	 * Encuentra todos las promociones pague lleve cantidad en SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos PromPagueLleveCant con todas las promociones pague lleve cantidad que conoce la aplicación, llenos con su información básica
	 */	
	public List<PromPagueLleveCant> darPromPagLleveCatidad()
	{
		log.info ("Consultando ");
		List<PromPagueLleveCant> list = psa.darPromPagLleveCatidad();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	// -----------------------------------------------------------------
    // Métodos de requerimientos
	// -----------------------------------------------------------------

	  private final ScheduledExecutorService scheduler =   Executors.newScheduledThreadPool(1);

	  public void verificarPromociones()
	  {
		  final Runnable verificador = new Runnable() 
		  {
			  public void run()
			  {
		//aqui se pone el metodo 
				  
			  }
		  };
		  
		  final ScheduledFuture<?> beeperHandle = scheduler.scheduleWithFixedDelay(verificador, 16, 24, HOURS);
		 
		  scheduler.schedule(new Runnable() {
			  public void run()  {	  beeperHandle.cancel(true); }  }  , 60 * 60, HOURS); }
	/**
	 * Finaliza una promocion , ya sea por q se agotaron las existencias o por llego la fecha de finalizacion 
	 * Adiciona entradas al log de la aplicación
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}