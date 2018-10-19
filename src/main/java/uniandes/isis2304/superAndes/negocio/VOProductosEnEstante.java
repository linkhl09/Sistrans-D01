package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Producto en estante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProductosEnEstante {
	
	public long getEstante();
	
	public int getCantidad();
	
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnEstante.
	 */
	@Override
	public String toString();
}
