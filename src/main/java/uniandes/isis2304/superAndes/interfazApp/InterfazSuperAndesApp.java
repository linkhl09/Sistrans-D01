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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
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

import jdk.nashorn.internal.scripts.JO;
import uniandes.isis2304.superAndes.negocio.*;

/**
 * Clase principal de la interfaz.
 * @author Jenifer Rodriguez y Andr�s Hern�ndez
 */
@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	// -----------------------------------------------------------------
	// Constantes.
	// -----------------------------------------------------------------

	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuraci�n de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos
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
	 * Asociaci�n a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------

	/**
	 * Objeto JSON con la configuraci�n de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacci�n para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Men� de la aplicaci�n
	 */
	private JMenuBar menuBar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye la ventana principal de la aplicaci�n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp()
	{
		// Carga la configuraci�n de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gr�fica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setBackground(Color.white);
		setLayout (new BorderLayout());
		JLabel labelImagen =new JLabel (new ImageIcon (path));
		labelImagen.setBackground(Color.WHITE);
		add (labelImagen, BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );  
	}

	// -----------------------------------------------------------------
	// M�todos de configuraci�n de la interfaz.
	// -----------------------------------------------------------------

	/**
	 * Lee datos de configuraci�n para la aplicaci�, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci�n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci�n
	 * @return Un objeto JSON con la configuraci�n del tipo especificado
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
			log.info ("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * M�todo para configurar el frame principal de la aplicaci�n
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuraci�n por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuraci�n indicada en el archivo de configuraci�n" );
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
	 * M�todo para crear el men� de la aplicaci�n con base em el objeto JSON le�do
	 * Genera una barra de men� y los men�s con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los men�s deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creaci�n de la barra de men�s
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creaci�n de cada uno de los men�s
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creaci�n de cada una de las opciones del men�
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
	// M�todos REQUERIMIENTOS FUNCIONALES.
	// -----------------------------------------------------------------

	/**
	 * Adiciona un proveedor con la informaci�n dada por el usuario.
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
					"Calificaci�n", calificacionString
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Inserte informaci�n del proveedor a adicionar", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				datosProveedor[0] = nit.getText();
				datosProveedor[1] = nombre.getText();
				datosProveedor[2] = calificacionString.getText();
				
				double calificacionSinFormato = Double.parseDouble(datosProveedor[2]);
				NumberFormat formatter = new DecimalFormat("#0.0");
				String strDouble = formatter.format(calificacionSinFormato).trim().replace(',', '.');
				double calificacion = Double.parseDouble(strDouble);
				VOProveedor tb = superAndes.adicionarProveedor(datosProveedor[0], datosProveedor[1] , calificacion);
				if(tb == null)
					throw new Exception("No se pudo crear el proveedor con nit: " + nit);
				String resultado = "En adicionar Proveedor \n\n";
				resultado += "Proveedor adicionado correctamente: " + tb.toString();
				resultado += "\n Operaci�n terminada.";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario.");
			}
		}
		catch(Exception e)
		{
			panelDatos.actualizarInterfaz("Exception: " + e.getMessage());
		}
	}

	public void adicionarProducto()
	{
		String [] array = new String[16];
		JTextField codigoBarras = new JTextField();
		JTextField nombre = new JTextField();
		JTextField marca = new JTextField();
		JTextField precioUnitario = new JTextField();
		JTextField presentacion = new JTextField();
		JTextField precioUnidadMedida = new JTextField();
		JTextField cantidadPresentacion = new JTextField();
		JTextField peso = new JTextField();
		JTextField unidadMedidaPeso = new JTextField();
		JTextField volumen = new JTextField();
		JTextField unidadMedidaVolumen = new JTextField();
		JTextField calidad = new JTextField();
		JTextField nivelReorden = new JTextField();
		JTextField fechaVencimiento = new JTextField();
		JTextField categoria = new JTextField();
		JTextField estaPromocion = new JTextField();
		Object[] message = 
			{
				"Codigo Barras:", codigoBarras,
				"Nombre:", nombre,
				"Marca:", marca,
				"Precio Unitario:", precioUnitario,
				"Presentaci�n:", presentacion,
				"Precio Unidad de medida:", precioUnidadMedida,
				"Cantidad presentacion:", cantidadPresentacion,
				"Peso (unidades):", peso,
				"Unidad de medida Peso:", unidadMedidaPeso,
				"Volumen (unidades):", volumen,
				"Unidad de medida volumen:", unidadMedidaVolumen,
				"Calidad:", calidad,
				"Nivel de reorden:", nivelReorden,
				"Fecha de vencimiento (opcional):", fechaVencimiento,
				"Categoria:", categoria,
				"�Esta en promocion? (Y/N)", estaPromocion
			};
		int option = JOptionPane.showConfirmDialog(null, message, "Inserte informaci�n del producto a adicionar", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			array[0]=codigoBarras.getText();
			array[1]=nombre.getText();
			array[2]=marca.getText();
			array[3]=precioUnitario.getText();
			array[4]=presentacion.getText();
			array[5]=precioUnidadMedida.getText();
			array[6]=cantidadPresentacion.getText();
			array[7]=peso.getText();
			array[8]=unidadMedidaPeso.getText();
			array[9]=volumen.getText();
			array[10]=unidadMedidaVolumen.getText();
			array[11]=calidad.getText();
			array[12]=nivelReorden.getText();
			array[13]=fechaVencimiento.getText();
			array[14]=categoria.getText();
			array[15]=estaPromocion.getText();			
		}
		else
		{
			
		}
		
	}

	public void adicionarPromocion()
	{
		//TODO lo del JComboBox.
	}

	// -----------------------------------------------------------------
	// M�todos administrativos.
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
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de superAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el n�mero de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecuci�n de la demo y recolecci�n de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();

			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
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
			resultado += eliminados [15] + " categoria eliminados\n";
			resultado += eliminados [16] + " tipo eliminados\n";
			resultado += eliminados [17] + " personaNatural eliminados\n";
			resultado += eliminados [18] + " empresas eliminados\n";
			resultado += eliminados [19] + " Ordenpedidos eliminados\n";
			resultado += eliminados [20] + " sucursal eliminados\n";
			resultado += eliminados [21] + " proveedor eliminados\n";
			resultado += eliminados [22] + " promDescuento eliminados\n";
			resultado += eliminados [23] + " promPagueLleveUnidad eliminados\n";
			resultado += eliminados [24] + " PromPagueLleveCant eliminados\n";
			resultado += eliminados [25] + " PromSegUniDescuento eliminados\n";
			resultado += eliminados [26] + " producto eliminados\n";

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
	 * Muestra la presentaci�n general del proyecto
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
		mostrarArchivo ("data/");
	}

	/**
	 * Muestra el esquema de la base de datos de SuperAndes
	 */
	public void mostrarEsquemaBD ()
	{
		//TODO
		mostrarArchivo ("data/");
	}

	/**
	 * Muestra el script de creaci�n de la base de datos
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
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	// -----------------------------------------------------------------
	// M�todos privados.
	// -----------------------------------------------------------------

	/**
	 * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
	 * @param e - La excepci�n recibida
	 * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para m�s detalles";
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
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del sistema
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
	// M�todos de interacci�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la ejecuci�n de los eventos que enlazan el men� con los m�todos de negocio
	 * Invoca al m�todo correspondiente seg�n el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
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

	// -----------------------------------------------------------------
	// Programa principal.
	// -----------------------------------------------------------------

	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por l�nea de comandos
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