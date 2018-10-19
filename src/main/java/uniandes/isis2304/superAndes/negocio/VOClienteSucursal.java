package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de ClienteSucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOClienteSucursal {

	public String getCliente();
	
	public String getDireccionSucursal();

	public String getCiudadSucursal();
	
	/**
	 * Cadena de caracteres con todos los atributos de ClienteSurcursal.
	 */
	@Override
	public String toString();
}
