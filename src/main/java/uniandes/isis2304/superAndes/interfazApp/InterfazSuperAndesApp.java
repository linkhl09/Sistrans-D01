package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.PersistenceManager;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import oracle.net.aso.n;
import uniandes.isis2304.superAndes.negocio.*;

/**
 * Clase principal de la interfaz.
 * @author Jenifer Rodriguez y Andrés Hernández
 */
@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes.
	// -----------------------------------------------------------------

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	/**
	 * Cliente que esta usando la aplicación.
	 */
	private String clienteActual;
	
	private long idCarrito;

	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------

	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;
	
	
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp()
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}


		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes(tableConfig);

		// corre la prueba del timer
		superAndes.prueba();
		
		//corre la verificacion de los carritos abandonados
		superAndes.verificarCarritosAbandonados();
		
		//corre la verificacion de las promociones
		superAndes.verificarPromociones();

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setBackground(Color.white);
		setLayout (new BorderLayout());
		JLabel labelImagen =new JLabel (new ImageIcon (path));
		labelImagen.setBackground(Color.WHITE);
		add (labelImagen, BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );
		identificarCliente();
	}

	// -----------------------------------------------------------------
	// Métodos de configuración de la interfaz.
	// -----------------------------------------------------------------

	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}


	// -----------------------------------------------------------------
	// Métodos REQUERIMIENTOS FUNCIONALES.
	// -----------------------------------------------------------------

	/**
	 * Adiciona un proveedor con la información dada por el usuario.
	 * Se crea una nueva tupla de Proveedor en la base de datos. Si un proveedor con ese nit no existia.
	 */
	public void adicionarProveedor()
	{
		try
		{
			String[] datosProveedor = new String[3];
			JTextField nit = new JTextField();
			JTextField nombre = new JTextField();
			JTextField calificacionString = new JTextField();
			Object[] message =
				{
						"Inserte nit:" , nit,
						"Inserte nombre:", nombre,
						"Calificación", calificacionString
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Inserte información del proveedor a adicionar", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Proveedor \n\n";
				datosProveedor[0] = nit.getText();
				datosProveedor[1] = nombre.getText();
				datosProveedor[2] = calificacionString.getText();
				if(!datosProveedor[0].equals("") || !datosProveedor[1].equals("") || !datosProveedor[2].equals(""))
				{
					double calificacionSinFormato = Double.parseDouble(datosProveedor[2]);
					NumberFormat formatter = new DecimalFormat("#0.0");
					String strDouble = formatter.format(calificacionSinFormato).trim().replace(',', '.');
					double calificacion = Double.parseDouble(strDouble);
					VOProveedor tb = superAndes.adicionarProveedor(datosProveedor[0], datosProveedor[1] , calificacion);
					if(tb == null)
						throw new Exception("No se pudo crear el proveedor con nit: " + nit);

					resultado += "Proveedor adicionado correctamente: " + tb.toString();
					resultado += "\n Operación terminada.";
				}
				else
				{
					resultado += "No se pueden dejar campos vacios.";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch(Exception e)
		{
			panelDatos.actualizarInterfaz("Exception!!!!!!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona un producto con la información dada por el usuario.
	 * Se crea una nueva tupla de Producto en la base de datos, si se cumple todo lo necesario.
	 */
	@SuppressWarnings("deprecation")
	public void adicionarProducto()
	{
		try
		{
			List<VOCategoria> categorias = superAndes.darVOCategoria();
			String[] categoriasDisponibles = new String[categorias.size()];
			for(int i = 0; i < categoriasDisponibles.length; i++)
				categoriasDisponibles[i]=categorias.get(i).getNombre();
			JComboBox<String> cbCategorias = new JComboBox<String>(categoriasDisponibles);
			cbCategorias.addActionListener(this);

			String [] info = new String[15];
			JTextField tFCodigoBarras = new JTextField();
			JTextField tFNombre = new JTextField();
			JTextField tFMarca = new JTextField();
			JTextField tFPrecioUnitario = new JTextField();
			JTextField tFPresentacion = new JTextField();
			JTextField tFPrecioUnidadMedida = new JTextField();
			JTextField tFCantidadPresentacion = new JTextField();
			JTextField tFPeso = new JTextField();
			JTextField tFUnidadMedidaPeso = new JTextField();
			JTextField tFVolumen = new JTextField();
			JTextField tFUnidadMedidaVolumen = new JTextField();
			JTextField tFCalidad = new JTextField();
			JTextField tFNivelReorden = new JTextField();
			JTextField tFFechaVencimiento = new JTextField();

			JCheckBox cbPromocion = new JCheckBox();
			Object[] message = 
				{
						"Codigo Barras:", tFCodigoBarras,
						"Nombre:", tFNombre,
						"Marca:", tFMarca,
						"Precio Unitario:", tFPrecioUnitario,
						"Presentación:", tFPresentacion,
						"Precio Unidad de medida:", tFPrecioUnidadMedida,
						"Cantidad presentacion:", tFCantidadPresentacion,
						"Peso (unidades):", tFPeso,
						"Unidad de medida Peso:", tFUnidadMedidaPeso,
						"Volumen (unidades):", tFVolumen,
						"Unidad de medida volumen:", tFUnidadMedidaVolumen,
						"Calidad:", tFCalidad,
						"Nivel de reorden:", tFNivelReorden,
						"Fecha de vencimiento 'dd/mm/yyyy' (opcional):", tFFechaVencimiento,
						"Categoria:", cbCategorias,
						"¿Esta en promocion? ", cbPromocion,
						"Tipos Disponibles:", "TODO" 
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Inserte información del producto a adicionar", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Producto \n\n";
				info[0]		=tFCodigoBarras.getText();
				info[1]		=tFNombre.getText();
				info[2]		=tFMarca.getText();
				info[3]		=tFPrecioUnitario.getText();
				info[4]		=tFPresentacion.getText();
				info[5]		=tFPrecioUnidadMedida.getText();
				info[6]		=tFCantidadPresentacion.getText();
				info[7]		=tFPeso.getText();
				info[8]		=tFUnidadMedidaPeso.getText();
				info[9]		=tFVolumen.getText();
				info[10]	=tFUnidadMedidaVolumen.getText();
				info[11]	=tFCalidad.getText();
				info[12]	=tFNivelReorden.getText();
				info[13]	=tFFechaVencimiento.getText();
				info[14]	=cbCategorias.getSelectedItem().toString();

				if(!info[0].equals("") && !info[1].equals("") && !info[2].equals("") && !info[3].equals("")
						&& !info[4].equals("") && !info[6].equals("") && !info[11].equals("") && !info[12].equals("")
						&& !info[14].equals("") )
				{
					//Algunas transformaciones para persistir la info recibida a la bdd.
					double calidadSinFormato = Double.parseDouble(info[11]);
					NumberFormat formatter = new DecimalFormat("#0.0");
					String strDouble = formatter.format(calidadSinFormato).trim().replace(',', '.');
					double calidadDouble = Double.parseDouble(strDouble);

					//Conversiones generales.
					double precioUnitario = Double.parseDouble(info[3]);
					double precioUnidadMedida = Double.parseDouble(info[5]);
					int cantidadPresentacion = Integer.parseInt(info[6]);
					double peso = Double.parseDouble(info[7]);
					double volumen = Double.parseDouble(info[9]);
					int nivelReorden = Integer.parseInt(info[12]);
					//Manejo de la fecha
					Date fechaVencimiento; 
					if(info[13].equals(""))
						fechaVencimiento= null;
					else
					{
						int year = Integer.parseInt(info[13].split("/")[2]);
						int month = Integer.parseInt(info[13].split("/")[1]);
						int day = Integer.parseInt(info[13].split("/")[0]);
						fechaVencimiento = new Date(year, month, day);
					}

					boolean estaEnPromocion = cbPromocion.isSelected();

					VOProducto productoAdicionado= superAndes.adicionarProducto(info[0], info[1], info[2], precioUnitario, info[4], precioUnidadMedida, cantidadPresentacion, peso, 
							info[8], volumen, info[10], calidadDouble, nivelReorden, fechaVencimiento, info[14], estaEnPromocion);

					if(productoAdicionado == null)
						throw new Exception("No se pudo crear el producto con el nombre: " + info[1]);
					else
						resultado += "Producto adicionado exitosamente: " + productoAdicionado.toString()+ "\n";

					//AHORA SE ASOCIA EL PRODUCTO A UNA SUCURSAL.
					List<VOSucursal> sucursales = superAndes.darVOSucursal();
					String[] sucursalesDisponibles = new String[sucursales.size()];
					for(int i = 0; i < sucursalesDisponibles.length; i++)
						sucursalesDisponibles[i]=sucursales.get(i).getNombre();
					JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
					cbSucursales.addActionListener(this);

					int option2 = JOptionPane.showConfirmDialog(null, cbSucursales, "Inserte Sucursal del producto a adicionar", JOptionPane.OK_CANCEL_OPTION);
					if(option2 == JOptionPane.OK_OPTION)
					{
						long idSucursal = superAndes.darSucursalPorNombre(cbSucursales.getSelectedItem().toString()).getId();
						VOSucursalProducto sucursalProducto = superAndes.adicionarSucursalProducto(idSucursal, info[0]);
						if(sucursalProducto == null)
							throw new Exception("No se pudo asociar el producto: " + info[1] + " a una sucursal.");
						else
							resultado+= "En adicionar Sucursal Producto: \n\n SucursalProducto adicionado exitosamente: " + sucursalProducto.toString() + "\n";
					}

				}
				else
				{
					resultado += "No se llenaron los campos correctamente.";
				}



				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch(Exception e)
		{
			panelDatos.actualizarInterfaz("Exception!!!!: " + e.getMessage());
		}
	}

	public void realizarCompraProducto()
	{
		try
		{
			//Información importante.
			JTextField jTFnombreCajero = new JTextField();
			JTextField jTFCantidad = new JTextField();

			//Lista de productos para comprar uno solo.
			List<VOProducto> productos = superAndes.darVOProducto();
			String[] productosDisponibles = new String[productos.size()];
			for(int i = 0; i < productosDisponibles.length; i++)
				productosDisponibles[i]=productos.get(i).getCodigoBarras() + "-" + productos.get(i).getNombre();
			JComboBox<String> cbProductos = new JComboBox<String>(productosDisponibles);
			cbProductos.addActionListener(this);

			//AHORA SE ASOCIA EL PRODUCTO A UNA SUCURSAL.
			List<VOSucursal> sucursales = superAndes.darVOSucursal();
			String[] sucursalesDisponibles = new String[sucursales.size()];
			for(int i = 0; i < sucursalesDisponibles.length; i++)
				sucursalesDisponibles[i]=sucursales.get(i).getNombre();
			JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
			cbSucursales.addActionListener(this);

			Object[] message = 
				{
						"Ingrese nombre del cajero:", jTFnombreCajero,
						"Seleccione sucursal de la compra:", cbSucursales,
						"Seleccione el producto que desea comprar:", cbProductos,
						"Ingrese la cantidad de este producto:", jTFCantidad
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Inserte información del producto a adicionar", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En hacer compra de producto \n\n";
				
				String codigoBarrasProducto = cbProductos.getSelectedItem().toString().split("-")[0];
				
				String nombreCajero = jTFnombreCajero.getText();
				String cantidadStr = jTFCantidad.getText();
				if(!nombreCajero.equals("") && !cantidadStr.equals(""))
				{
					long idSucursal = superAndes.darSucursalPorNombre(cbSucursales.getSelectedItem().toString()).getId();
					String direccion = superAndes.darSucursalPorId(idSucursal).getDireccion();
					int cantidad = Integer.parseInt(cantidadStr);
					Date fecha = new Date();

					
					double valorUnitario = superAndes.darProducto(codigoBarrasProducto).getPrecioUnitario();
					double valorTotal = valorUnitario * cantidad;
					int puntos = (int) valorTotal/100;
					
					VOFactura factura = superAndes.adicionarFactura(direccion,fecha , nombreCajero, valorTotal, true, puntos , clienteActual, idSucursal);
					long estante = superAndes.darEstanteProductoSucursal(idSucursal, codigoBarrasProducto);
					superAndes.quitarProductosEstante(estante, cantidad, codigoBarrasProducto);
					superAndes.adicionarFacturaProducto(factura.getNumero(), cantidad, codigoBarrasProducto);
					
				}
				
				
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch(Exception e)
		{
			panelDatos.actualizarInterfaz("Exception!!!!: " + e.getMessage());
		}



	}

	/**
	 * Adiciona un cliente persona natural con la información dada por el usuario.
	 * Se crea una nueva tupla de Persona natural en la base de datos, si se cumple todo lo necesario.
	 * Se crea una nueva tupla de Cliente en la base de datos, si se cumple todo lo necesario.
	 */
	public void adicionarPersonaNatural()
	{
		try
		{
			String[] infoPN = new String[3];
			JTextField documento = new JTextField();
			String[] opcionesDocumento = 
				{
						"CI", "DUI", "ID", "CC", "TI", "TD", "RC","CE", "CI","DNI"
				};
			JComboBox<String> cbTipoDocumento = new JComboBox<String>(opcionesDocumento);
			JTextField correoElectronico = new JTextField();
			JTextField nombre = new JTextField();
			Object[] message = {
					"Número de documento:", documento,
					"Tipo de documento:", cbTipoDocumento,
					"Correo electrónico:", correoElectronico,
					"Nombre completo:", nombre
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar CLIENTE Persona Natural: \n\n";
				infoPN[0] = documento.getText();
				infoPN[1] = correoElectronico.getText();
				infoPN[2] = nombre.getText();
				if(!infoPN[0].equals("") && !infoPN[1].equals("") && !infoPN[2].equals(""))
				{
					VOPersonaNatural pn = superAndes.adicionarPersonaNatural(infoPN[0], cbTipoDocumento.getSelectedItem().toString(), infoPN[1], infoPN[2]);
					if(pn == null)
						throw new Exception("No se pudo agregar el cliente Persona natural con nombre: " + nombre);
					resultado += "Cliente adicionado correctamente: " + pn.toString();
					resultado += "\n Operación terminada.";

				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona un cliente empresa con la información dada por el usuario.
	 * Se crea una nueva tupla de Empresa en la base de datos, si se cumple todo lo necesario.
	 * Se crea una nueva tupla de Cliente en la base de datos, si se cumple todo lo necesario.
	 */
	public void adicionarEmpresa()
	{
		try
		{
			String[] infoPN = new String[4];
			JTextField NIT = new JTextField();
			JTextField direccion = new JTextField();
			JTextField correoElectronico = new JTextField();
			JTextField nombre = new JTextField();
			Object[] message = {
					"NIT:", NIT,
					"Dirección:", direccion,
					"Correo electrónico:", correoElectronico,
					"Nombre completo:", nombre
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar CLIENTE Empresa: \n\n";
				infoPN[0] = NIT.getText();
				infoPN[1] = direccion.getText();
				infoPN[2] = correoElectronico.getText();
				infoPN[3] = nombre.getText();
				if(!infoPN[0].equals("") && !infoPN[1].equals("") && !infoPN[2].equals("") && !infoPN[3].equals(""))
				{
					VOEmpresa empresa = superAndes.adicionarEmpresa(infoPN[0], infoPN[1], infoPN[2], infoPN[3] );
					if(empresa == null)
						throw new Exception("No se pudo agregar el cliente Persona natural con nombre: " + nombre);
					resultado += "Cliente adicionado correctamente: " + empresa.toString();
					resultado += "\n Operación terminada.";

				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona una sucursal con la información dada por el usuario.
	 * Se crea una nueva tupla de Sucursal en la base de datos. Si no existia una con el nombre dado.
	 */
	public void adicionarSucursal()
	{
		try
		{

			String[] infoSucursal = new String[5];

			JTextField jTFdireccion = new JTextField();
			JTextField jTFciudad = new JTextField();
			JTextField jTFnombre = new JTextField();
			JTextField jTFsegmentacionMercado = new JTextField();
			JTextField jTFtamanio = new JTextField();
			Object[] message = {
					"Dirección:", jTFdireccion,
					"Ciudad", jTFciudad,
					"Nombre:", jTFnombre,
					"Segmentación de mercado:", jTFsegmentacionMercado,
					"Tamaño:", jTFtamanio
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Sucursal: \n\n";


				infoSucursal[0] = jTFdireccion.getText();
				infoSucursal[1] = jTFciudad.getText();
				infoSucursal[2] = jTFnombre.getText();
				infoSucursal[3] = jTFsegmentacionMercado.getText();
				infoSucursal[4] = jTFtamanio.getText();
				if(!infoSucursal[0].equals("") && !infoSucursal[1].equals("") && !infoSucursal[2].equals("") &&!infoSucursal[3].equals("") && !infoSucursal[4].equals(""))
				{
					String direccion = infoSucursal[0];
					String ciudad = infoSucursal[1];
					String nombre = infoSucursal[2] ;
					String segmentacionMercado = infoSucursal[3];
					int tamanio = Integer.parseInt(infoSucursal[4]);
					VOSucursal sucursal = superAndes.adicionarSucursal(direccion, ciudad, nombre, segmentacionMercado, tamanio);
					if (sucursal==null)
						throw new Exception("No se pudo agregar la sucursal con el nombre: " + nombre );
					resultado += "Sucursal adicionada correctamente: " + sucursal.toString();
					resultado += "\n Operación terminada.";

				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona una Bodega con la información dada por el usuario.
	 * Se crea una nueva tupla de Bodega en la base de datos. Si se cumplen las condiciones necesarias.
	 */	
	public void adicionarBodega()
	{
		try
		{
			//Lista de posibles categorias de la bodega.
			List<VOCategoria> categorias = superAndes.darVOCategoria();
			String[] categoriasDisponibles = new String[categorias.size()];
			for(int i = 0; i < categoriasDisponibles.length; i++)
				categoriasDisponibles[i]=categorias.get(i).getNombre();
			JComboBox<String> cbTipos = new JComboBox<String>(categoriasDisponibles);
			cbTipos.addActionListener(this);

			//Lista de posibles sucursales.
			List<VOSucursal> sucursales = superAndes.darVOSucursal();
			String[] sucursalesDisponibles = new String[sucursales.size()];
			for(int i = 0; i<sucursalesDisponibles.length; i++)
				sucursalesDisponibles[i] = sucursales.get(i).getNombre();
			JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
			cbSucursales.addActionListener(this);

			//Información que provee usuario.
			String[] infoBodega = new String[2];

			JTextField jTFcapacidadVol = new JTextField();
			JTextField jTFcapacidadPeso = new JTextField();
			Object[] message = {
					"Capacidad Volumen:", jTFcapacidadVol,
					"Capacidad Peso", jTFcapacidadPeso,
					"Tipo:", cbTipos,
					"Sucursal:",cbSucursales
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Bodega: \n\n";
				infoBodega[0] = jTFcapacidadVol.getText();
				infoBodega[1] = jTFcapacidadPeso.getText();

				if(!infoBodega[0].equals("") && !infoBodega[1].equals(""))
				{
					double capacidadVol = Double.parseDouble(infoBodega[0]);
					double capacidadPeso = Double.parseDouble(infoBodega[1]);
					long idSucursal = superAndes.darSucursalPorNombre(cbSucursales.getSelectedItem().toString()).getId();
					VOBodega bodega = superAndes.adicionarBodega(capacidadVol, capacidadPeso, cbTipos.getSelectedItem().toString(), idSucursal);	
					if (bodega == null)
						throw new Exception("No se pudo agregar la bodega.");
					resultado += "Bodega adicionada correctamente: " + bodega.toString();
					resultado += "\n Operación terminada.";
				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona una Estante con la información dada por el usuario.
	 * Se crea una nueva tupla de Estante en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void adicionarEstante()
	{
		try
		{
		
			
			//Lista de posibles categorias del estante.
			List<VOCategoria> categorias = superAndes.darVOCategoria();
			String[] categoriasDisponibles = new String[categorias.size()];
			for(int i = 0; i < categoriasDisponibles.length; i++)
				categoriasDisponibles[i]=categorias.get(i).getNombre();
			JComboBox<String> cbTipos = new JComboBox<String>(categoriasDisponibles);
			cbTipos.addActionListener(this);

			//Lista de posibles sucursales.
			List<VOSucursal> sucursales = superAndes.darVOSucursal();
			String[] sucursalesDisponibles = new String[sucursales.size()];
			for(int i = 0; i<sucursalesDisponibles.length; i++)
				sucursalesDisponibles[i] = sucursales.get(i).getNombre();
			JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
			cbSucursales.addActionListener(this);

			//Información que provee usuario.
			String[] infoBodega = new String[2];

			JTextField jTFcapacidadVol = new JTextField();
			JTextField jTFcapacidadPeso = new JTextField();
			Object[] message = {
					"Capacidad Volumen:", jTFcapacidadVol,
					"Capacidad Peso", jTFcapacidadPeso,
					"Tipo:", cbTipos,
					"Sucursal:",cbSucursales
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Bodega: \n\n";
				infoBodega[0] = jTFcapacidadVol.getText();
				infoBodega[1] = jTFcapacidadPeso.getText();

				if(!infoBodega[0].equals("") && !infoBodega[1].equals(""))
				{
					double capacidadVol = Double.parseDouble(infoBodega[0]);
					double capacidadPeso = Double.parseDouble(infoBodega[1]);
					long idSucursal = superAndes.darSucursalPorNombre(cbSucursales.getSelectedItem().toString()).getId();
					VOEstante estante = superAndes.adicionarEstante(capacidadVol, capacidadPeso, cbTipos.getSelectedItem().toString(), idSucursal);	
					if (estante == null)
						throw new Exception("No se pudo agregar la bodega.");
					resultado += "Bodega adicionada correctamente: " + estante.toString();
					resultado += "\n Operación terminada.";
				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}


	/**
	 * Adiciona una Promocion manual con la información dada por el usuario.
	 * Se crea una nueva tupla de Promocion en la base de datos. Si se cumplen las condiciones necesarias.
	 * En caso de que el producto ya tenga una Promocion activa , no se crea una tupla y manda error. 
	 */
	public void adicionarPromocion()
	{
		try
		{
			//Lista de posibles productos.
			List<VOProducto> productos = superAndes.darVOProducto();
			String[] productosDisponibles = new String[productos.size()];
			System.out.println("productosDisponibles" + productosDisponibles.length );
			for(int i = 0; i < productosDisponibles.length; i++)
			{
				productosDisponibles[i]=productos.get(i).getNombre();
			}
			JComboBox<String> cbProductos = new JComboBox<String>(productosDisponibles);
			cbProductos.addActionListener(this);

			
			//Información que provee usuario.
			String[] infoPromo = new String[7];

			JTextField jTFdescripcion = new JTextField();
			JTextField jTFunidadesDisponibles = new JTextField();
			JTextField jTFfechaInicio = new JTextField();
			JTextField jTFfechaFin = new JTextField();
			JTextField jTFdescuento = new JTextField();
			JTextField jTFpague = new JTextField();
			JTextField jTFlleve = new JTextField();


			JCheckBox cbDescuento = new JCheckBox();
			JCheckBox cbPagueLleveUnidad = new JCheckBox();
			JCheckBox cbDescuentoSegundaUnidad = new JCheckBox();
			JCheckBox cbPagueLleveCantidad = new JCheckBox();

			Object[] message = {
					"Descripcion:", jTFdescripcion,
					"Uidades disponibles", jTFunidadesDisponibles,
					"FechaInicio:", jTFfechaInicio,
					"FechaFin:",jTFfechaFin,
					"descuento:",jTFdescuento,
					"pague:",jTFpague,
					"lleve:",jTFlleve,
					"Promocion Descuento", cbDescuento,
					"Promocion Pague Lleve Unidad ", cbPagueLleveUnidad,
					"Promocion descuento en al segunda unidad" ,cbDescuentoSegundaUnidad,
					"Promocion Pague Lleve Cantidad ", cbPagueLleveCantidad,
					"Producto:",cbProductos
			};



			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Promcion: \n\n";
				infoPromo[0] = jTFdescripcion.getText();
				infoPromo[1] = jTFunidadesDisponibles.getText();
				infoPromo[2] = jTFfechaInicio.getText();
				infoPromo[3] = jTFfechaFin.getText();
				infoPromo[4] = jTFdescuento.getText();
				infoPromo[5] = jTFpague.getText();
				infoPromo[6] = jTFlleve.getText();

				if(!infoPromo[0].equals("") && !infoPromo[1].equals("") && !infoPromo[2].equals("") && !infoPromo[3].equals(""))
				{
					int descuento = Integer.parseInt(infoPromo[4]);
					int pague = Integer.parseInt(infoPromo[5]);
					int lleve = Integer.parseInt(infoPromo[6]);
					String descripcion = infoPromo[0];
					int unidadesDisponibles = Integer.parseInt(infoPromo[0]);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaInicio = formatter.parse(infoPromo[2]);
					Date fechaFin = formatter.parse(infoPromo[3]);
//					String codigoBarrasProducto = superAndes.darProductoPorNombre(cbProductos.getSelectedItem().toString()).getCodigoBarras();
					String codigoBarrasProducto = "";
;					if(cbDescuento.isSelected() && !(descuento<=0))
					{
						VOPromDesc promDescuento = superAndes.adicionarPromDescuento( descripcion, unidadesDisponibles, 0, fechaInicio, fechaFin, codigoBarrasProducto, descuento);	
						if (promDescuento == null)
							throw new Exception("No se pudo agregar la promocion.");

						resultado += "Promocion adicionada correctamente: " + promDescuento.toString();
						resultado += "\n Operación terminada.";
					}

					if(cbDescuentoSegundaUnidad.isSelected() && !(descuento<=0))
					{
						VOPromSegUniDesc promDescuentoSegundaUnidad = superAndes.adicionarPromDescSegUnid( descripcion, unidadesDisponibles, 0, fechaInicio, fechaFin, codigoBarrasProducto, descuento);	
						if (promDescuentoSegundaUnidad == null)
							throw new Exception("No se pudo agregar la promocion.");

						resultado += "Promocion adicionada correctamente: " + promDescuentoSegundaUnidad.toString();
						resultado += "\n Operación terminada.";
					}

					if(cbPagueLleveCantidad.isSelected() && !(pague<=0) && !(lleve<=0) )
					{
						VOPromPagueLleveCant promPagueLleveCant = superAndes.adicionarPromPagueLleveCantidad( descripcion, unidadesDisponibles, 0, fechaInicio, fechaFin, codigoBarrasProducto, pague, lleve);	
						if (promPagueLleveCant == null)
							throw new Exception("No se pudo agregar la promocion.");

						resultado += "Promocion adicionada correctamente: " + promPagueLleveCant.toString();
						resultado += "\n Operación terminada.";
					}

					if(cbPagueLleveUnidad.isSelected() && !(pague<=0) && !(lleve<=0) )
					{
						VOPromPagueLleveUnid promPagueLleveUnid = superAndes.adicionarPromPagueLleveUnid( descripcion, unidadesDisponibles, 0, fechaInicio, fechaFin, codigoBarrasProducto, pague, lleve);	
						if (promPagueLleveUnid == null)
							throw new Exception("No se pudo agregar la promocion.");

						resultado += "Promocion adicionada correctamente: " + promPagueLleveUnid.toString();
						resultado += "\n Operación terminada.";
					}


				}

				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}

		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	
	/**
	 * Adiciona una OrdenPedido manual con la información dada por el usuario.
	 * Se crea una nueva tupla de OrdenPedido en la base de datos. Si se cumplen las condiciones necesarias.
	 * En caso de tener una OrdenPedido activa para el proveedor, no se crea una tupla sino que se actualiza. 
	 */
	public void adicionarOrdenPedido()
	{
		try
		{
			//Para información del usuario.
			JTextField jTFfechaEsperada= new JTextField();

			//Para manejar los estados.
			String[] estados = {"Entregado", "En Espera"};
			JComboBox<String> cbEstado = new JComboBox<String>(estados);
			cbEstado.addActionListener(this);

			//Para manejar los proveedores
			List<VOProveedor> proveedores = superAndes.darVOProveedor();
			String[] proveedoresDisponibles = new String[proveedores.size()];
			for (int i = 0; i < proveedoresDisponibles.length; i++) 
				proveedoresDisponibles[i]= proveedores.get(i).getNombre();
			JComboBox<String> cbProveedores = new JComboBox<String>(proveedoresDisponibles);
			cbProveedores.addActionListener(this);

			//Para manejar las sucursales.
			List<VOSucursal> sucursales = superAndes.darVOSucursal();
			String[] sucursalesDisponibles = new String[sucursales.size()];
			for(int i = 0; i<sucursalesDisponibles.length; i++)
				sucursalesDisponibles[i] = sucursales.get(i).getNombre();
			JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
			cbSucursales.addActionListener(this);

			Object[] message = {
					"Fecha esperada de entrega(dd/mm/yyyy)", jTFfechaEsperada,
					"Estado", cbEstado,
					"Proveedor:", cbProveedores,
					"Sucursal:",cbSucursales
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				//Manejo del proveedor.
				String nitProveedor = superAndes.darProveedorPorNombre(cbProveedores.getSelectedItem().toString()).getNit();

				//Manejo de la sucursal.
				long idSucursal = superAndes.darSucursalPorNombre(cbSucursales.getSelectedItem().toString()).getId();



				//if(null== superAndes.darOrdenPedidoEnEsperaPorProveedor(nitProveedor,idSucursal)){
				String resultado = "En adicionar Orden pedido manual \n\n";
				String fechaParaSplit =jTFfechaEsperada.getText(); 
				if(!fechaParaSplit.equals(""))
				{
					//Manejo de la fecha.
					int year = Integer.parseInt(fechaParaSplit.split("/")[2]);
					int month = Integer.parseInt(fechaParaSplit.split("/")[1]);
					int day = Integer.parseInt(fechaParaSplit.split("/")[0]);
					@SuppressWarnings("deprecation")
					Date fechaEsperadaEntrega = new Date(year, month, day);

					VOOrdenPedido ordenPedido = superAndes.adicionarOrdenPedido(fechaEsperadaEntrega, nitProveedor , idSucursal, cbEstado.getSelectedItem().toString());
					if(ordenPedido == null)
						throw new Exception("No se pudo agregar la orden de pedido al proveedor: " + cbProveedores.getSelectedItem().toString());
					resultado+="OrdenPedido adicionada correctamente: " + ordenPedido.toString();
					resultado+="\n Operación terminada.";
				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}

				panelDatos.actualizarInterfaz(resultado);
				//}
				//else throw new Exception("Ya existe una orden de pedido en espera para este proveedor, en esa sucursal, solo registre el producto.");
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Registra la fecha de llegada de una orden pedido y actualiza la calificación de su proveedor.
	 */
	public void registrarFechaLlegada()
	{
		try
		{
			//Para manejar las Ordenes de pedido
			List<VOOrdenPedido> ordenPedido = superAndes.darVOOrdenPedido();
			String[] ordenPedidoDisponibles = new String[ordenPedido.size()];
			for (int i = 0; i < ordenPedidoDisponibles.length; i++) 
				ordenPedidoDisponibles[i]= ordenPedido.get(i).getId()+"-"+ordenPedido.get(i).getEstado();
			JComboBox<String> cbProveedores = new JComboBox<String>(ordenPedidoDisponibles);
			cbProveedores.addActionListener(this);

			//Para información del usuario.
			JTextField jTFfechaEntrega= new JTextField();

			Object[] message = {
					"Fecha de entrega(dd/mm/yyyy)", jTFfechaEntrega,
					"Orden de pedido a despachar", cbProveedores
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				String resultado = "En adicionar Orden pedido manual \n\n";
				String fechaParaSplit =jTFfechaEntrega.getText(); 
				if(!fechaParaSplit.equals(""))
				{
					double nuevaCalificacion= 0;

					long id = Long.parseLong(cbProveedores.getSelectedItem().toString());

					//Manejo de la fecha.
					int year = Integer.parseInt(fechaParaSplit.split("/")[2]);
					int month = Integer.parseInt(fechaParaSplit.split("/")[1]);
					int day = Integer.parseInt(fechaParaSplit.split("/")[0]);
					@SuppressWarnings("deprecation")
					Date fechaEntrega = new Date(year, month, day);

					List<ProductoOrdenPedido> productosOrdenP = superAndes.darProductosDelPedido(id);
					int cantidadProductosOrden = 0;
					double calidadTotal = 0;
					for(VOProductoOrdenPedido x : productosOrdenP)
					{
						cantidadProductosOrden++;
						calidadTotal += x.getCalidad();
					}




					superAndes.registrarFechaLlegada(id, fechaEntrega, nuevaCalificacion);
				}
				else
				{
					resultado+= "No se pueden dejar campos vacios!";
				}

				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario.");
			}
		}
		catch (Exception e) 
		{
			panelDatos.actualizarInterfaz("Exception en interfaz!!!: " + e.getMessage());
		}
	}

	/**
	 * Adiciona un CarritoCompras con la información dada por el usuario.
	 * Se crea una nueva tupla de CarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void adicionarCarritoCompras()
	{
		try
		{
       long numeroCarrito;
		CarritoCompras a =superAndes.adicionarCarritoCompras(clienteActual, 1);
		numeroCarrito = a.getId();
		idCarrito = numeroCarrito;
		panelDatos.actualizarInterfaz("Carrito Solicitado : cliente: " + clienteActual + "  numero Carrito : " + numeroCarrito);
		}
		catch (Exception e)
		{
			panelDatos.actualizarInterfaz("Usted ya tiene un carrito asociado");	
		}
		}

	/**
	 * Se abandona el  CarritoCompras vinculado al cliente
	 * Se cambia el cliente del carrito a null.
	 */
	public void abandonarCarritoCompras()
	{
	panelDatos.actualizarInterfaz(" Abandonando Carrito: cliente: " + clienteActual + "  numero Carrito : " + idCarrito);
		superAndes.abandonarCarrito(idCarrito);
		panelDatos.actualizarInterfaz("Se abandono correctamente el carrito : " + idCarrito);
		
	}

	/**
	 * Adiciona una ProductoCarritoCompras con la información dada por el usuario.
	 * Se crea una nueva tupla de ProductoCarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void agregarProductoCarrito()
	{

		
	}

	/**
	 * Elimina la cantidad dada de un ProductoCarritoCompras con la información dada por el usuario.
	 * Se actualiza la tupla de ProductoCarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void devolverProductoCarritoCompras()
	{

	}

	
	public void pagarCarritoCompras()
	{

	}

	@SuppressWarnings("deprecation")
	public void identificarCliente()
	{
		List<String> correos = superAndes.darTodosLosCorreos();
		String[] clientes = new String[correos.size() + 3];
		clientes[0] = "ADMINISTRADOR";
		int i = 1;
		for(String correo: correos)
		{
			clientes[i] = correo;
			i++;
		}
		clientes[clientes.length-2] = "adicionarPersonaNatural";
		clientes[clientes.length-1] = "adicionarEmpresa";
		JComboBox<String> cbClientes = new JComboBox<String>(clientes);
		Object[] message = 
			{
					"Seleccione su identificador", cbClientes
			};
		int resp = JOptionPane.showConfirmDialog(null, message, "Confirme su identidad", JOptionPane.OK_CANCEL_OPTION);
		if(resp == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		clienteActual = cbClientes.getSelectedItem().toString();
		if(!clienteActual.equals("ADMINISTRADOR"))
		{
			menuBar.getComponentAtIndex(0).setEnabled(false);
			//menuBar.getComponentAtIndex(1).setEnabled(false);
			menuBar.getComponentAtIndex(2).setEnabled(false);
			menuBar.getComponentAtIndex(4).setEnabled(false);
			menuBar.getComponentAtIndex(5).setEnabled(false);
			menuBar.getComponentAtIndex(6).setEnabled(false);
			menuBar.getComponentAtIndex(7).setEnabled(false);
		}
		
		
	}



	// -----------------------------------------------------------------
	// Métodos administrativos
	// -----------------------------------------------------------------

	/**
	 * Muestra el log de superAndes
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("superAndes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de superAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0]	 + " proveedoresProducto eliminados\n";
			resultado += eliminados [1]	 + " productoOrdenPedido eliminados\n";
			resultado += eliminados [2]	 + " facturaProducto eliminados\n";
			resultado += eliminados [3]	 + " productosEnBodega eliminadas\n";
			resultado += eliminados [4]	 + " productosEnEstante eliminados\n";
			resultado += eliminados [5]	 + " sucursalProducto eliminados\n";
			resultado += eliminados [7]	 + " ProductoCarritoCompras eliminados\n";
			resultado += eliminados [8]	 + " PromocionSucursal eliminados\n";
			resultado += eliminados [9]	 + " TipoProducto eliminados\n";
			resultado += eliminados [10] + " CarritoCompras eliminados\n";
			resultado += eliminados [11] + " Factura eliminados\n";
			resultado += eliminados [12] + " clientes eliminados\n";
			resultado += eliminados [13] + " bodegas eliminados\n";
			resultado += eliminados [14] + " estantes eliminados\n";
			resultado += eliminados [15] + " tipo eliminados\n";
			resultado += eliminados [16] + " personaNatural eliminados\n";
			resultado += eliminados [17] + " empresas eliminados\n";
			resultado += eliminados [18] + " Ordenpedidos eliminados\n";
			resultado += eliminados [19] + " sucursal eliminados\n";
			resultado += eliminados [20] + " proveedor eliminados\n";
			resultado += eliminados [21] + " promDescuento eliminados\n";
			resultado += eliminados [22] + " promPagueLleveUnidad eliminados\n";
			resultado += eliminados [23] + " PromPagueLleveCant eliminados\n";
			resultado += eliminados [24] + " PromSegUniDescuento eliminados\n";
			resultado += eliminados [25] + " producto eliminados\n";
			resultado += eliminados [26] + " categoria eliminados\n";;

			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		//TODO
		mostrarArchivo ("data/");
	}

	/**
	 * Muestra el modelo conceptual de SuperAndes
	 */
	public void mostrarModeloConceptual ()
	{
		//TODO 
		mostrarArchivo ("data/Iteracion2/superAndes.jpeg");
	}

	/**
	 * Muestra el esquema de la base de datos de SuperAndes
	 */
	public void mostrarEsquemaBD ()
	{
		//TODO
		mostrarArchivo ("data/Iteracion2/ModeloRelacional.xlsx");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/Crear y poblar tablas/esquemaSuperAndes.sql");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	// -----------------------------------------------------------------
	// Métodos privados para la presentación de resultados y otras operaciones
	// -----------------------------------------------------------------

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------
	// Métodos de interacción
	// -----------------------------------------------------------------

	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );
		if(!evento.equals("comboBoxChanged"))
		{
			try 
			{
				Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
				req.invoke ( this );
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	// -----------------------------------------------------------------
	// Programa principal.
	// -----------------------------------------------------------------

	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
			System.exit(0); 
		}
	}
}