package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get de persona natural.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOPersonaNatural {

	public String getDocumento();
	
	public String getTipoDocumento();
	
	/**
	 * Cadena de caracteres con todos los atributos de Persona natural.
	 */
	@Override
	public String toString();
}
