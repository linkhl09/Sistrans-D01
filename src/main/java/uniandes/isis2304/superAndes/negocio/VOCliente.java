package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get de Cliente .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOCliente {

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

	/**
	 * devuelve el correo electronico del cliente
	 */
	public String getCorreoElectronico();
	
	/**
	 * devuelve el nombre del cliente
	 */
	public String getNombre();
	
	/**
	 * devuelve los puntos acumulados del cliente
	 */
	public int getPuntos();
}
