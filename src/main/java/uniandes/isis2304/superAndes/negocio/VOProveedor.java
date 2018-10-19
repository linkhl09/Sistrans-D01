package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get de Proveedor.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProveedor 
{
	public String getNit();
		
	public String getNombre();

	public double getCalificacionCalidad();
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString();
}
