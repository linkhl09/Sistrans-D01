package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los m�todos get de Empresa.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
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
