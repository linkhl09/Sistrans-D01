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
	 * Cadena de caracteres con todos los atributos de Empresa.
	 */
	@Override
	public String toString();	
}
