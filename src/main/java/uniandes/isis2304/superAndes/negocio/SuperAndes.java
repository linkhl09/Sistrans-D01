package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 * Clase principal del negocio.
 * Satisface todos los requerimientos funcionales del negocio.
 * @author Andrés Hernández
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
	 * Adiciona de manera persistente un tipo 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo
	 * @param categoria - El nombre de la categoria
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Tipo adicionarTipo (String nombre, String categoria)
	{
		log.info ("Adicionando Tipo: " + nombre);
		Tipo tipo = psa.adicionarTipo(nombre, categoria);		
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
		log.info ("Eliminando Tipo de bebida por nombre: " + nombre);
		long resp = psa.eliminarTipo(nombre);		
		log.info ("Eliminando Tipo de bebida por nombre: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Tipo> darTipos()
	{
		log.info ("Consultando Tipos de bebida");
		List<Tipo> tipos = psa.darTipos();	
		log.info ("Consultando Tipos de bebida: " + tipos.size() + " existentes");
		return tipos;
	}

	public Tipo darTipo(String nombre)
	{
		log.info ("Dar información de Tipo");
		Tipo buscado = psa.darTipoPorNombre(nombre); 
		log.info ("Buscando tipo: " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Categoria
	// -----------------------------------------------------------------


	public Categoria adicionarCategoria(String nombre)
	{
		log.info ("Adicionando categoria: " + nombre);
		Categoria agregado = psa.adicionarCategoria(nombre);		
		log.info ("Adicionada categoria: " + nombre);
		return agregado;
	}

	public long eliminarCategoria(String nombre) 
	{
		log.info ("Eliminando categoria");
		long resp = psa.eliminarCategoria(nombre);		
		log.info ("Eliminada: " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Categoria> darCategorias()
	{
		log.info ("Consultando ");
		List<Categoria> list = psa.darCategorias();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

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

	public Categoria darCategoria (String nombre)
	{
		log.info ("Dar información de ");
		Categoria buscado = psa.darCategoria(nombre);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


	// -----------------------------------------------------------------
	// Métodos de tabla Proveedor
	// -----------------------------------------------------------------

	public Proveedor adicionarProveedor(String nit, String nombre, double calificacion)
	{
		log.info ("Adicionando proveedor: " + nombre);
		Proveedor agregado = psa.adicionarProveedor(nit, nombre, calificacion);	
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProveedorPorNit (String nit)
	{
		log.info ("Eliminando proveedor");
		long resp = psa.eliminarProveedorPorNit(nit);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

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
	// Métodos de tabla promoción
	// -----------------------------------------------------------------


	// -----------------------------------------------------------------
	// Métodos de tabla producto
	// -----------------------------------------------------------------

	public  Producto adicionarProducto(String codigoBarras, String nombre, String marca, 
			double precioUnitario, String presentacion, double precioUnidadMedida, int cantidadPresentacion, 
			double peso, String unidadMedidaPeso, double volumen, String unidadMedidaVolumen, double calidad, 
			int nivelReorden, Date fechaVencimiento, String categoria, boolean promocion)
	{
		log.info ("Adicionando producto: " + nombre);
		Producto agregado = psa.adicionarProducto(codigoBarras, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, peso, unidadMedidaPeso, volumen, unidadMedidaVolumen, calidad, nivelReorden, fechaVencimiento, categoria, promocion);
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProducto(String codigoBarras) 
	{
		log.info ("Eliminando ");
		long resp = psa.eliminarProducto(codigoBarras);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Producto> darProductos()
	{
		log.info ("Consultando ");
		List<Producto> list = psa.darProductos();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOProducto> darVOProducto ()
	{
		log.info ("Generando los VO de ");        
		List<VOProducto> list = new LinkedList<VOProducto> ();
		for (Producto tb : psa.darProductos())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	public Producto darProducto(String codigoBarras)
	{
		log.info ("Dar información de ");
		Producto buscado = psa.darProducto(codigoBarras);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


	public long nuevaPromocion(String codigoBarras, long promocion)
	{
		log.info("Crear nueva promocion al producto");
		long cambios = psa.nuevaPromocion(codigoBarras, promocion);
		return cambios;
	}

	public long terminarPromocion(String codigoBarras)
	{
		log.info("");
		long cambios = psa.terminarPromocion(codigoBarras);
		return cambios;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla personaNatural 
	// -----------------------------------------------------------------

	public PersonaNatural adicionarPersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre, String empresa)
	{
		log.info ("Adicionando Persona natural : " + nombre);
		PersonaNatural agregado = psa.adicionarPersonaNatural(documento, tipoDocumento, correoElectronico, nombre, empresa);	
		log.info ("Adicionado");
		return agregado;
	}

	public long[] eliminarPersonaNatural(String documento, String tipoDocumento, String correoElectronico)
	{
		log.info ("Eliminando ");
		long[] resp = psa.eliminarPersonaNatural(documento, tipoDocumento, correoElectronico);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<PersonaNatural>darPersonasNaturales ()
	{
		log.info ("Consultando ");
		List<PersonaNatural> list = psa.darPersonasNaturales();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOPersonaNatural> darVOPersonaNatural()
	{
		log.info ("Generando los VO de ");        
		List<VOPersonaNatural> list = new LinkedList<VOPersonaNatural> ();
		for (PersonaNatural tb : psa.darPersonasNaturales())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	public PersonaNatural darPersonaNatural (String documento, String tipodocumento)
	{
		log.info ("Dar información de ");
		PersonaNatural buscado = psa.darPersonaNatural(documento, tipodocumento);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Empresa
	// -----------------------------------------------------------------

	public Empresa adicionarEmpresa(String nit, String direccion, String correoElectronico, String nombre, String empresa)
	{
		log.info ("Adicionando empresa : " + nit );
		Empresa agregado = psa.adicionarEmpresa(nit, direccion, correoElectronico, nombre, empresa);
		log.info ("Adicionado");
		return agregado;
	}

	public long[] eliminarEmpresaPorNit(String nit, String correoElectronico)
	{
		log.info ("Eliminando ");
		long[] resp = psa.eliminarEmpresaPorNit(nit, correoElectronico);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public long[] eliminarEmpresaPorDireccion(String direccion, String correoElectronico) 
	{
		log.info ("Eliminando ");
		long[] resp = psa.eliminarEmpresaPorDireccion(direccion, correoElectronico);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Empresa> darEmpresas()
	{
		log.info ("Consultando ");
		List<Empresa> list = psa.darEmpresas();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOEmpresa> darVOEmpresa ()
	{
		log.info ("Generando los VO de ");        
		List<VOEmpresa> list = new LinkedList<VOEmpresa> ();
		for (Empresa tb : psa.darEmpresas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	public Empresa darEmpresa(String nit)
	{
		log.info ("Dar información de ");
		Empresa buscado = psa.darEmpresa(nit);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}


	// -----------------------------------------------------------------
	// Métodos de tabla Cliente
	// -----------------------------------------------------------------

	public List<Cliente> darClientes()
	{
		log.info ("Consultando ");
		List<Cliente> list = psa.darClientes();	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOCliente> darVOCliente()
	{
		log.info ("Generando los VO de ");        
		List<VOCliente> list = new LinkedList<VOCliente> ();
		for (Cliente tb : psa.darClientes())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	public Cliente darCliente (String correoElectronico)
	{
		log.info ("Dar información de ");
		Cliente buscado = psa.darCliente(correoElectronico);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	public long aumentarPuntos(String correoElectronico, int puntos)
	{
		log.info("Aumentar puntos del cliente.");
		long cambios = psa.aumentarPuntos(correoElectronico, puntos);
		return cambios;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla Factura
	// -----------------------------------------------------------------

	public Factura adicionarFactura( String direccion, 
			Date fecha, String nombreCajero, double valorTotal, int pagoExitoso, 
			int puntosCompra, String cliente)
	{
		log.info ("Adicionando factura: " + fecha.toString() );
		Factura agregado = psa.adicionarFactura(direccion, fecha, nombreCajero, valorTotal, pagoExitoso, puntosCompra, cliente);	
		log.info ("Adicionado");
		return agregado;
	}

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
	// Métodos de tabla sucursal
	// -----------------------------------------------------------------

	public Sucursal adicionarSucursal( String direccion, String ciudad,
			String nombre, String segmentacionMercado, int tamanio)
	{
		log.info ("Adicionando Sucursal: " + nombre );
		Sucursal agregado = psa.adicionarSucursal(direccion, ciudad, nombre, segmentacionMercado, tamanio);	
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarSucursal(String nombre) 
	{
		log.info ("Eliminando Sucursal");
		long resp = psa.eliminarSucursal(nombre);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public long eliminarSucursal(String direccion, String ciudad) 
	{
		log.info ("Eliminando Sucursal");
		long resp = psa.eliminarSucursal(direccion, ciudad);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Sucursal> darSucursales()
	{
		log.info ("Consultando Sucursales");
		List<Sucursal> list = psa.darSucursales();	
		log.info ("Consultando : " + list.size() + " existentes");
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
		log.info ("Generando los VO de Sucursal : " + list.size() + " existentes");
		return list;
	}

	public Sucursal  darSucursal(String nombre)
	{
		log.info ("Dar información de Sucursal");
		Sucursal buscado = psa.darSucursal(nombre); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	public Sucursal  darSucursal(String direccion, String ciudad)
	{
		log.info ("Dar información de Sucursal");
		Sucursal buscado = psa.darSucursal(direccion, ciudad); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla ordenPedido
	// -----------------------------------------------------------------

	public OrdenPedido adicionarOrdenPedido( Date fechaEsperadaEntrega
			, String proveedor, String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Adicionando OrdenPedido");
		OrdenPedido agregado = psa.adicionarOrdenPedido(fechaEsperadaEntrega, proveedor, direccionSucursal, ciudadSucursal);	
		log.info ("Adicionado");
		return agregado;
	}

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
	// Métodos de tabla bodega
	// -----------------------------------------------------------------

	public Bodega adicionarBodega(double capacidadVol, double capacidadPeso, String tipo, long idSucursal)
	{
		log.info ("Adicionando Bodega");
		Bodega agregado = psa.adicionarBodega(capacidadVol, capacidadPeso, tipo, idSucursal);	
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarBodega(long id) 
	{
		log.info ("Eliminando Bodega");
		long resp = psa.eliminarBodega(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Bodega> darBodegas ()
	{
		log.info ("Consultando Bodegas");
		List<Bodega> list = psa.darBodegas();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<Bodega> darBodegasSucursal(long idSucursal)
	{
		log.info ("Consultando Bodegas por sucursal");
		List<Bodega> list = psa.darBodegasSucursal(idSucursal);
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOBodega> darVOBodega ()
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
		log.info ("Dar información de Bodega");
		Bodega buscado = psa.darBodega(id);
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla estante
	// -----------------------------------------------------------------

	public Estante adicionarEstante(double capacidadVolumen, double capacidadPeso, String tipo, String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Adicionando Estante");
		Estante agregado = psa.adicionarEstante(capacidadVolumen, capacidadPeso, tipo, direccionSucursal, ciudadSucursal);	
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarEstante(long id)
	{
		log.info ("Eliminando Estante");
		long resp = psa.eliminarEstante(id);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<Estante> darEstantes ()
	{
		log.info ("Consultando Estantes");
		List<Estante> list = psa.darEstantes();	
		log.info ("Consultando : " + list.size() + " existentes");
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
		log.info ("Dar información de Estante");
		Estante buscado = psa.darEstante(id); 
		log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	public List<Estante> darEstantePorSucursal(String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Consultando Estantes por sucursal.");
		List<Estante> list = psa.darEstantesPorSucursal(direccionSucursal, ciudadSucursal);	
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla proveedoresProducto 
	// -----------------------------------------------------------------

	public ProveedoresProducto adicionarProveedoresProducto(String proveedor, String producto)
	{
		log.info ("Adicionando ProveedoresProducto: " + proveedor + "-" +producto );
		ProveedoresProducto agregado = psa.adicionarProveedoresProducto(proveedor, producto);
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProveedoresProducto(String proveedor, String producto)
	{
		log.info ("Eliminando ProveedoresProducto");
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
	// Métodos de tabla ProductoOrdenPedido
	// -----------------------------------------------------------------

	public ProductoOrdenPedido adicionarProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto)
	{
		log.info ("Adicionando  ProductoOrdenPedido: " );
		ProductoOrdenPedido agregado = psa.adicionarProductoOrdenPedido(pedido, cantidad, calidad, producto);
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
		List<ProductoOrdenPedido> list = psa.dardarProductosDelPedido(pedido);
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
	// Métodos de tabla Factura_Prodcuto
	// -----------------------------------------------------------------

	public FacturaProducto adicionarFacturaProducto(long factura, int cantidad, String producto)
	{
		log.info ("Adicionando FacturaProducto: " + factura);
		FacturaProducto agregado = psa.adicionarFacturaProducto(factura, cantidad, producto);	
				log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProductosDeFactura(long factura)
	{
		log.info ("Eliminando FacturaProducto");
		long resp = psa.eliminarProductosDeFactura(factura);
				log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

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
	// Métodos de tabla cliente_sucursal
	// -----------------------------------------------------------------

	public ClienteSucursal adicionarClienteSucursal(String cliente, String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Adicionando ClienteSucursal: " + cliente);
		ClienteSucursal agregado = psa.adicionarClienteSucursal(cliente, direccionSucursal, ciudadSucursal);
				log.info ("Adicionado");
		return agregado;
	}

	public long eliminarClienteSucursal(String cliente, String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Eliminando ClienteSucursal");
		long resp = psa.eliminarClienteSucursal(cliente, direccionSucursal, ciudadSucursal);
				log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<ClienteSucursal> darSucursalesCliente(String cliente)
	{
		log.info ("Consultando ClienteSucursal");
		List<ClienteSucursal> list = psa.darSucursalesCliente(cliente);	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<ClienteSucursal> darClientesSucursal(String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Consultando ClienteSucursal");
		List<ClienteSucursal> list = psa.darClientesSucursal(direccionSucursal, ciudadSucursal);	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<ClienteSucursal> darTodosClientesSucursales()
	{
		log.info ("Consultando ClienteSucursal");
		List<ClienteSucursal> list = psa.darTodosClientesSucursales();	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOClienteSucursal> darVOClienteSucursal ()
	{
		log.info ("Generando los VO de ClienteSucursal");        
		List<VOClienteSucursal> list = new LinkedList<VOClienteSucursal> ();
		for (ClienteSucursal tb : psa.darTodosClientesSucursales())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de ClienteSucursal: " + list.size() + " existentes");
		return list;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnBodega
	// -----------------------------------------------------------------

	public ProductosEnBodega adicionarProductosEnBodega(long bodega, int cantidad, int nivelAbastecimiento, String producto)
	{
		log.info ("Adicionando ProductosEnBodega: " + bodega);
		ProductosEnBodega agregado = psa.adicionarProductosEnBodega(bodega, cantidad, nivelAbastecimiento, producto);	
				log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProductoEnBodega(long bodega, String producto) 
	{
		log.info ("Eliminando ProductosEnBodega");
		long resp = psa.eliminarProductoEnBodega(bodega, producto);
				log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<ProductosEnBodega> darProductosEnBodega(long bodega)
	{
		log.info ("Consultando ProductosEnBodega ");
		List<ProductosEnBodega> list = psa.darProductosEnBodega(bodega);
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<ProductosEnBodega> darTodosProductosBodegas()
	{
		log.info ("Consultando ProductosEnBodega ");
		List<ProductosEnBodega> list = psa.darTodosProductosBodegas();
		log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<ProductosEnBodega> darBodegasProducto(String producto)
	{
		log.info ("Consultando ProductosEnBodega ");
		List<ProductosEnBodega> list = psa.darBodegasProducto(producto);	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<VOProductosEnBodega> darVOProductosEnBodega ()
	{
		log.info ("Generando los VO de ProductosEnBodega");        
		List<VOProductosEnBodega> list = new LinkedList<VOProductosEnBodega> ();
		for (ProductosEnBodega tb : psa.darTodosProductosBodegas())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de ProductosEnBodega: " + list.size() + " existentes");
		return list;
	}

	public long aumentarProductosEnBodega( long bodega, String producto, int productosPedidos )
	{
		return 0;
	}

	public long disminuirProductosEnBodega(long bodega, String producto, int pasadosAEstante )
	{
		return 0;
	}

	// -----------------------------------------------------------------
	// Métodos de tabla productosEnEstante
	// -----------------------------------------------------------------

	public ProductosEnEstante adicionarProductosEnEstante(long estante, int cantidad, String producto)
	{
		log.info ("Adicionando ProductosEnEstante: " + estante);
		ProductosEnEstante agregado = psa.adicionarProductosEnEstante(estante, cantidad, producto);
				log.info ("Adicionado");
		return agregado;
	}

	public long eliminarProductosEnEstante(long estante, String producto)
	{
		log.info ("Eliminando ProductosEnEstante");
		long resp = psa.eliminarProductosEnEstante(estante, producto);
		log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<ProductosEnEstante> darProductosEnEstante(long estante)
	{
		log.info ("Consultando ProductosEnEstante ");
		List<ProductosEnEstante> list = psa.darProductosEnEstante(estante);	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<ProductosEnEstante>  darTodosProductosEnEstantes()
	{
		log.info ("Consultando ProductosEnEstante ");
		List<ProductosEnEstante> list = psa.darTodosProductosEnEstantes();
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<VOProductosEnEstante> darVOProductosEnEstante ()
	{
		log.info ("Generando los VO de ");        
		List<VOProductosEnEstante> list = new LinkedList<VOProductosEnEstante> ();
		for (ProductosEnEstante tb : psa.darTodosProductosEnEstantes())
		{
			list.add (tb);
		}
		log.info ("Generando los VO de : " + list.size() + " existentes");
		return list;
	}

	public ProductosEnEstante darProductoEnEstante (long estante,  String producto)
	{
		log.info ("Dar información de ");
		ProductosEnEstante buscado = psa.darProductoEnEstante(estante, producto);
				log.info ("Buscando : " + buscado != null ? buscado : "NO EXISTE");
		return buscado;
	}

	public long traerDeBodega(long estante, int productosTraidos, String producto)
	{
		log.info("Traer productos de bodega");
		long cambios = psa.traerDeBodega(estante, productosTraidos, producto);
		return cambios;
	}
	
	public long venderProductos(long estante, int productosVendidos, String producto)
	{
		log.info("vender productos del estante");
		long cambios = psa.venderProductos(estante, productosVendidos, producto);
		return cambios;
	}
	
	// -----------------------------------------------------------------
	// Métodos de tabla SucursalProducto
	// -----------------------------------------------------------------

	public SucursalProducto adicionarSucursalProducto(String direccionSucursal, String ciudadSucursal, String producto)
	{
		log.info ("Adicionando SucursalProducto: " + direccionSucursal);
		SucursalProducto agregado = psa.adicionarSucursalProducto(direccionSucursal, ciudadSucursal, producto);	
		log.info ("Adicionado");
		return agregado;
	}

	public long eliminarSucursalProducto(String direccionSucursal, String ciudadSucursal, String producto)
	{
		log.info ("Eliminando SucursalProducto ");
		long resp = psa.eliminarSucursalProducto(direccionSucursal, ciudadSucursal, producto);
				log.info ("Eliminando : " + resp + " tuplas eliminadas");
		return resp;
	}

	public List<SucursalProducto> darProductosSucursal(String direccionSucursal, String ciudadSucursal)
	{
		log.info ("Consultando SucursalProducto");
		List<SucursalProducto> list = psa.darProductosSucursal(direccionSucursal, ciudadSucursal);
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}

	public List<SucursalProducto> darSucursalesProducto(String producto )
	{
		log.info ("Consultando SucursalProducto");
		List<SucursalProducto> list = psa.darSucursalesProducto(producto);	
				log.info ("Consultando : " + list.size() + " existentes");
		return list;
	}
	
	public List<SucursalProducto> darTodosProductosSucursales()
	{
		log.info ("Consultando SucursalProducto");
		List<SucursalProducto> list = psa.darTodosProductosSucursales();	
				log.info ("Consultando : " + list.size() + " existentes");
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
}
