package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get de Empresa.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOEmpresa {

	/**
	 * Devuelve el nit de la empresa.
	 */
	public String getNit();
	
	/**
	 * Devuelve la direccion de la empresa.
	 */
	public String getDireccion();
	
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
	
	/**
	 * Cadena de caracteres con todos los atributos de Empresa.
	 */
	@Override
	public String toString();	
}
