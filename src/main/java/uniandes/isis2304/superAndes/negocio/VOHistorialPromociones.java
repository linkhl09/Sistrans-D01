package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get del HistorialPromociones.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOHistorialPromociones {

	public String getProducto();
	
	public long getPromocion();	

	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString();
}
