package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
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
	 * @param nombre - El nombre del tipo de bebida a eliminar
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
	 * 
	 * @param nombreCategoria
	 * @return
	 */
	public List<TipoProducto> darTiposCategoria(String nombreCategoria)
	{
		log.info("Consultando TiposCategoria.");
		List<TipoProducto> list = psa.darTiposDelProducto(nombreCategoria);
		log.info("Consultando TiposCategoria: " + list.size() + " existentes.");
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<TipoProducto> darTodosTipoCategoria()
	{
		log.info("Consultando todos los tipoCategoria.");
		List<TipoProducto> list = psa.darTodosTipoProducto();
		log.info("Consultando todos los tipoCategoria: " + list.size() + " existentes.");
		return list;
	}
	
	/**
	 * 
	 * @return
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

	public List<Producto> darProductos()
	{
		log.info ("Consultando productos");
		List<Producto> list = psa.darProductos();	
		log.info ("Consultando productos: " + list.size() + " existentes");
		return list;
	}

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

	public Producto darProducto(String codigoBarras)
	{
		log.info ("Dar información de Producto con codigo de barras: "+ codigoBarras);
		Producto buscado = psa.darProducto(codigoBarras);
		log.info ("Buscando Producto: " + buscado != null ? buscado.toString() : "NO EXISTE");
		return buscado;
	}

	public long nuevaPromocion(String codigoBarras)
	{
		log.info("Se cambio el estado del producto a en promoción.");
		long cambios = psa.nuevaPromocion(codigoBarras);
		return cambios;
	}

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

	public List<Sucursal> darSucursales()
	{
		log.info ("Consultando Sucursales.");
		List<Sucursal> list = psa.darSucursales();	
		log.info ("Consultando sucursales: " + list.size() + " existentes");
		return list;
	}

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

	public Sucursal  darSucursalPorNombre(String nombre)
	{
		log.info ("Dar información de Sucursal con nombre: " + nombre + ".");
		Sucursal buscado = psa.darSucursalPorNombre(nombre); 
		log.info ("Buscando Sucursal: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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


	public List<SucursalProducto> darProductosSucursal(long idSucursal)
	{
		log.info ("Consultando SucursalProducto de una sucursal.");
		List<SucursalProducto> list = psa.darProductosSucursal(idSucursal);
		log.info ("Consultando SucursalProducto: " + list.size() + " existentes");
		return list;
	}


	public List<SucursalProducto> darSucursalesProducto(String codigoBarrasProducto )
	{
		log.info ("Consultando SucursalProducto de un producto.");
		List<SucursalProducto> list = psa.darSucursalesProducto(codigoBarrasProducto);	
		log.info ("Consultando SucursalProducto: " + list.size() + " existentes");
		return list;
	}


	public List<SucursalProducto> darTodosProductosSucursales()
	{
		log.info ("Consultando todos los SucursalProducto");
		List<SucursalProducto> list = psa.darTodosProductosSucursales();	
		log.info ("Consultando los SucursalProducto: " + list.size() + " existentes");
		return list;
	}


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


	public List<Bodega> darBodegas ()
	{
		log.info ("Consultando Bodegas.");
		List<Bodega> list = psa.darBodegas();
		log.info ("Consultando Bodegas: " + list.size() + " existentes");
		return list;
	}


	public List<Bodega> darBodegasSucursal(long idSucursal)
	{
		log.info ("Consultando Bodegas por sucursal.");
		List<Bodega> list = psa.darBodegasSucursal(idSucursal);
		log.info ("Consultando bodegas: " + list.size() + " existentes");
		return list;
	}


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


	public List<Estante> darEstantes()
	{
		log.info ("Consultando todos los Estantes");
		List<Estante> list = psa.darEstantes();	
		log.info ("Consultando Estantes: " + list.size() + " existentes");
		return list;
	}


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


	public Estante darEstante(long id)
	{
		log.info ("Dar información del Estante con id: " + id + ".");
		Estante buscado = psa.darEstante(id); 
		log.info ("Buscando Estante: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


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


	public List<ProductosEnBodega> darProductosEnBodega(long idBodega)
	{
		log.info ("Consultando ProductosEnBodega con id: " + idBodega);
		List<ProductosEnBodega> list = psa.darProductosEnBodega(idBodega);
		log.info ("Consultando ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}


	public List<ProductosEnBodega> darTodosProductosBodegas()
	{
		log.info ("Consultando todos los ProductosEnBodega.");
		List<ProductosEnBodega> list = psa.darTodosProductosBodegas();
		log.info ("Consultando todos los ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}


	public List<ProductosEnBodega> darBodegasProducto(String codigoBarrasProducto)
	{
		log.info ("Consultando ProductosEnBodega bodegas con el producto: " + codigoBarrasProducto);
		List<ProductosEnBodega> list = psa.darBodegasProducto(codigoBarrasProducto);	
		log.info ("Consultando ProductosEnBodega bodegas con el producto: " + list.size() + " existentes");
		return list;
	}


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


	public long aumentarProductosEnBodega( long bodega, String producto, int productosPedidos )
	{
		//TODO aumentar productos en Bodega.
		return 0;
	}


	public long disminuirProductosEnBodega(long bodega, String producto, int pasadosAEstante )
	{
		//TODO disminuir productos en Bodega.
		return 0;
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


	public List<ProductosEnEstante> darProductosEnEstante(long idEstante)
	{
		log.info ("Consultando ProductosEnEstante productos del estante: " + idEstante );
		List<ProductosEnEstante> list = psa.darProductosEnEstante(idEstante);	
		log.info ("Consultando ProductosEnEstante: " + list.size() + " existentes");
		return list;
	}


	public List<ProductosEnEstante>  darTodosProductosEnEstantes()
	{
		log.info ("Consultando todos los ProductosEnEstante.");
		List<ProductosEnEstante> list = psa.darTodosProductosEnEstantes();
		log.info ("Consultando todos los productos en estante: " + list.size() + " existentes.");
		return list;
	}


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


	public ProductosEnEstante darProductoEnEstante (long idEstante,  String codigoBarrasProducto)
	{
		log.info ("Dar información de ProductosEnEstante del producto: " + codigoBarrasProducto + " en el estante: " + idEstante);
		ProductosEnEstante buscado = psa.darProductoEnEstante(idEstante, codigoBarrasProducto);
		log.info ("Buscando ProductosEnEstante: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


	public long aumentarCantidadProductosEnEstante(long idEstante, int productosTraidos, String codigoBarrasProducto)
	{
		log.info("Aumentar cantidad de Productos en el estante: " + idEstante);
		long cambios = psa.aumentarCantidadProductosEnEstante(idEstante, productosTraidos, codigoBarrasProducto);
		log.info("Cantidad de tuplas modificadas: " + cambios);
		return cambios;
	}


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

	public List<PersonaNatural>darPersonasNaturales ()
	{
		log.info ("Consultando todas las personas naturales.");
		List<PersonaNatural> list = psa.darPersonasNaturales();
		log.info ("Consultando PersonasNaturales: " + list.size() + " existentes");
		return list;
	}

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

	public List<Empresa> darEmpresas()
	{
		log.info ("Consultando todas las empresas.");
		List<Empresa> list = psa.darEmpresas();
		log.info ("Consultando empresas: " + list.size() + " existentes");
		return list;
	}

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

	public List<Cliente> darClientes()
	{
		log.info ("Consultando Clientes: ");
		List<Cliente> list = psa.darClientes();	
		log.info ("Consultando Clientes: " + list.size() + " existentes");
		return list;
	}

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

	public Cliente darCliente (String correoElectronico)
	{
		log.info ("Dar información de Cliente");
		Cliente buscado = psa.darCliente(correoElectronico);
		log.info ("Buscando Cliente: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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
	
	public CarritoCompras darCarritoComprasPorId(long id)
	{
		log.info("Dar información de CarritoCompras con id: " + id);
		CarritoCompras buscado = psa.darCarritoComprasPorId(id);
		log.info("Buscando CarritoComrpas: " + buscado != null ? buscado.toString() : "NO EXISTE.");
		return buscado;
	}
	
	
	public CarritoCompras darCarritoComprasPorCliente(String cliente)
	{
		log.info("Dar información de carrito de compras con cliente: " + cliente);
		CarritoCompras buscado = psa.darCarritoComprasPorCliente(cliente);
		log.info("Buscando CarritoComrpas: " + buscado != null ? buscado.toString() : "NO EXISTE.");
		return buscado;		
	}
	
	public List<CarritoCompras> darTodosCarritosCompras()
	{
		log.info("Consultando todos CarritosCompras.");
		List<CarritoCompras> list = psa.darTodosCarritosCompras();
		log.info("Consultando CarritosCompras: " + list.size() + " existentes." );
		return list;
	}
	
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
	
	public ProductoCarritoCompras darProductoCarrito(long carrito, String codigoBarrasProducto)
	{
		log.info("Consultando ProductoCarritoCompras: carrito-" + carrito + " producto-" + codigoBarrasProducto);
		ProductoCarritoCompras buscado = psa.darProductoCarrito(carrito, codigoBarrasProducto);
		log.info("Consultando carrito de compras" + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}
	
	public List<ProductoCarritoCompras> darTodosProductosDeUnCarrito(long carrito)
	{
		log.info("Consultando todos ProductoCarritoCompras");
		List<ProductoCarritoCompras> list = psa.darTodosProductosCarrito();
		log.info("Consultando todos ProductoCarritoCompras: " + list.size() + " existentes.");
		return list;
	}
	
	public long aumentarUnidadesProductoCarritoCompras(long carrito, String codigoBarrasProducto , int productosAgregados)
	{
		log.info("Aumentando unidades del producto: " + codigoBarrasProducto + " en el carrito: " + carrito);
		long resp = psa.aumentarUnidadesProductoCarritoCompras(carrito, codigoBarrasProducto, productosAgregados);
		log.info("Aumentadas las unidades del producto en el carrito.");
		return resp;
	}
	
	
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

	public List<Factura> darFacturas()
	{
		log.info ("Consultando ");
		List<Factura> list = psa.darFacturas();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	
	public List<FacturaProducto> darProductosFacturas()
	{
		log.info ("Consultando FacturasProductos");
		List<FacturaProducto> list = psa.darProductosFacturas();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	
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

	public List<Proveedor> darProveedores ()
	{
		log.info ("Consultando ");
		List<Proveedor> list = psa.darProveedores();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public Proveedor darProveedor(String nit)
	{
		log.info ("Dar información de ");
		Proveedor buscado = psa.darProveedor(nit); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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

	public List<ProveedoresProducto> darProveedoresProducto(String producto)
	{
		log.info ("Consultando ProveedoresProducto");
		List<ProveedoresProducto> list = psa.darProveedoresProducto(producto);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<ProveedoresProducto> darProductosProveedor(String proveedor)
	{
		log.info ("Consultando Productos Proveedor");
		List<ProveedoresProducto> list = psa.darProveedoresProducto(proveedor);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<ProveedoresProducto> darTodosProveedoresProductos()
	{
		log.info ("Consultando Productos Proveedor");
		List<ProveedoresProducto> list = psa.darTodosProveedoresProductos();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public List<OrdenPedido> darOrdenesPedidos()
	{
		log.info ("Consultando OrdenesPedidos");
		List<OrdenPedido> list = psa.darOrdenesPedidos();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public OrdenPedido darOrdenPedido (long id)
	{
		log.info ("Dar información de OrdenPedido");
		OrdenPedido buscado = psa.darOrdenPedido(id);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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

	public List<ProductoOrdenPedido> darProductosOrdenPedidos()
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darProductosOrdenPedidos();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<ProductoOrdenPedido> darProductosDelPedido(long pedido)
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darProductosDelPedido(pedido);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<ProductoOrdenPedido> darHistorialPedidosProducto(String producto)
	{
		log.info ("Consultando ");
		List<ProductoOrdenPedido> list = psa.darHistorialPedidosProducto(producto);	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public List<PromDesc> darPromDescuento()
	{
		log.info ("Consultando ");
		List<PromDesc> list = psa.darPromDescuento();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public PromPagueLleveUnid darPromPagLlevUnidadPorId(long id)
	{
		log.info ("Dar información de ");
		PromPagueLleveUnid buscado = psa.darPromPagLlevUnidadPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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

	public List<PromSegUniDesc> darPromDescSegUnid()
	{
		log.info ("Consultando ");
		List<PromSegUniDesc> list = psa.darPromDescSegUnid();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public PromPagueLleveCant darPromPagueLleveCantPorId(long id)
	{
		log.info ("Dar información de ");
		PromPagueLleveCant buscado = psa.darPromPagueLleveCantPorId(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

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

	public List<PromPagueLleveCant> darPromPagLleveCatidad()
	{
		log.info ("Consultando ");
		List<PromPagueLleveCant> list = psa.darPromPagLleveCatidad();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

}