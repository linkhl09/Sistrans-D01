package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Clase de interfaz para mostrar los resultados de la ejecuci�n de las 
 * operaciones realizadas por el usuario
 * @author Jenifer Rodriguez y Andr�s Hern�ndez
 *
 */
@SuppressWarnings("serial")
public class PanelDatos extends JPanel
{
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------


    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
	/**
	 * �rea de texto con barras de deslizamiento
	 */
	private JTextArea textArea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel
     * 
     */
    public PanelDatos ()
    {
        setBorder (new TitledBorder ("Panel de informaci�n"));
        setLayout( new BorderLayout( ) );
        
        textArea = new JTextArea("Aqu� sale el resultado de las operaciones solicitadas");
        textArea.setEditable(false);
        add (new JScrollPane(textArea), BorderLayout.CENTER);
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la informaci�n recibida por par�metro.
     * @param texto El texto con el que actualiza el �rea
     */
    public void actualizarInterfaz (String texto)
    {
    	textArea.setText(texto);
    }
    
}
