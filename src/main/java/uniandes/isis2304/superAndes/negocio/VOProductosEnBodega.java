package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Producto en bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProductosEnBodega {

	public long getBodega();
	
	public int getCantidad();
	
	public int getNivelAbastecimiento();
	
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnBodega.
	 */
	@Override
	public String toString();	
}
