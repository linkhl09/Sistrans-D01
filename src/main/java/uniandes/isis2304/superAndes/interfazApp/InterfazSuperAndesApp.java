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
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
<<<<<<< HEAD
=======
import javax.swing.JCheckBox;
>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
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
<<<<<<< HEAD
			
			String [] info = new String[16];
=======

			String [] info = new String[15];
>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
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
<<<<<<< HEAD
			JTextField tFEstaPromocion = new JTextField();
=======
			JCheckBox cbPromocion = new JCheckBox();
>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
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
<<<<<<< HEAD
						"¿Esta en promocion? (Y/N)", tFEstaPromocion,
=======
						"¿Esta en promocion? (Y/N)", cbPromocion,
>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
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
<<<<<<< HEAD
				info[15]	=tFEstaPromocion.getText();
				
				System.out.println(info[14]);
				
=======

>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
				if(!info[0].equals("") && !info[1].equals("") && !info[2].equals("") && !info[3].equals("")
						&& !info[4].equals("") && !info[6].equals("") && !info[11].equals("") && !info[12].equals("")
						&& !info[14].equals("") )
				{
					//Algunas transformaciones para persistir la info recibida a la bdd.
					double calidadSinFormato = Double.parseDouble(info[11]);
					NumberFormat formatter = new DecimalFormat("#0.0");
					String strDouble = formatter.format(calidadSinFormato).trim().replace(',', '.');
					double calidadDouble = Double.parseDouble(strDouble);
<<<<<<< HEAD
					
=======

>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
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
<<<<<<< HEAD
					
					boolean estaEnPromocion = false;
					if(info[15].equals("Y"))
						estaEnPromocion = true;
					
					System.out.println("valores-> precio: " + precioUnitario + " precioUM: " + precioUnidadMedida + " cantidadPr: " + cantidadPresentacion 
							+ "\n peso: " + peso + " volumen: " + volumen + " calidad:" + calidadDouble + " nivelRO: " + nivelReorden +  " fecha: " + fechaVencimiento);
					
=======
					boolean estaEnPromocion = cbPromocion.isSelected();

>>>>>>> b8da6a03614b07a9a0c705447edf71f5db24f34e
					VOProducto productoAdicionado= superAndes.adicionarProducto(info[0], info[1], info[2], precioUnitario, info[4], precioUnidadMedida, cantidadPresentacion, peso, 
							info[8], volumen, info[10], calidadDouble, nivelReorden, fechaVencimiento, info[14], estaEnPromocion);

					if(productoAdicionado == null)
						throw new Exception("No se pudo crear el producto con el nombre: " + info[1]);
					else
						resultado += "Producto adicionado exitosamente: " + productoAdicionado.toString()+ "\n";

					List<VOSucursal> sucursales = superAndes.darVOSucursal();
					String[] sucursalesDisponibles = new String[sucursales.size()];
					for(int i = 0; i < sucursalesDisponibles.length; i++)
						sucursalesDisponibles[i]=sucursales.get(i).getId()+"";
					JComboBox<String> cbSucursales = new JComboBox<String>(sucursalesDisponibles);
					cbCategorias.addActionListener(this);

					int option2 = JOptionPane.showConfirmDialog(null, cbSucursales, "Inserte Sucursal del producto a adicionar", JOptionPane.OK_CANCEL_OPTION);
					if(option2 == JOptionPane.OK_OPTION)
					{
						VOSucursalProducto sucursalProducto = superAndes.adicionarSucursalProducto(Long.parseLong(cbSucursales.getSelectedItem().toString()), info[0]);
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
	// -----------------------------------------------------------------
	// Métodos administrativos.
	// -----------------------------------------------------------------


	/**
	 * Adiciona una sucursal con la información dada por el usuario.
	 * Se crea una nueva tupla de Sucursal en la base de datos. Si no existia una con el nombre dado.
	 */
	public void adicionarSucursal()
	{
		
	}
	
	/**
	 * Adiciona una Bodega con la información dada por el usuario.
	 * Se crea una nueva tupla de Bodega en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void adicionarBodega()
	{
		
	}
	
	/**
	 * Adiciona una Estante con la información dada por el usuario.
	 * Se crea una nueva tupla de Estante en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void adicionarEstante()
	{
		
	}
	
	
	public void adicionarPromocion()
	{
		
	}
	
	/**
	 * Adiciona una OrdenPedido manual con la información dada por el usuario.
	 * Se crea una nueva tupla de OrdenPedido en la base de datos. Si se cumplen las condiciones necesarias.
	 * En caso de tener una OrdenPedido activa para el proveedor, no se crea una tupla sino que se actualiza. 
	 */
	public void adicionarOrdenPedido()
	{
		
	}
		
	
	public void registrarFechaLlegada()
	{
		
	}
	
	/**
	 * Adiciona un CarritoCompras con la información dada por el usuario.
	 * Se crea una nueva tupla de CarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void adicionarCarritoCompras()
	{
		
	}
	
	public void abandonarCarritoCompras()
	{
		
	}
	
	/**
	 * Adiciona una ProductoCarritoCompras con la información dada por el usuario.
	 * Se crea una nueva tupla de ProductoCarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
	 */
	public void agregarProductoCarrito()
	{
		
	}
	
	/**
	 * Adiciona una ProductoCarritoCompras con la información dada por el usuario.
	 * Se crea una nueva tupla de ProductoCarritoCompras en la base de datos. Si se cumplen las condiciones necesarias.
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
			menuBar.getComponentAtIndex(1).setEnabled(false);
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
		//TODO 
		mostrarArchivo ("data/");
	}

	/**
	 * Muestra la arquitectura de referencia para SuperAndes
	 */
	public void mostrarArqRef ()
	{
		//TODO
		mostrarArchivo ("data/");
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
	
	// -----------------------------------------------------------------
	// Métodos privados.
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
	// Métodos de la interacción.
	// -----------------------------------------------------------------
	
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
	// Programa Principal
	// -----------------------------------------------------------------
	
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
		}
	}
}