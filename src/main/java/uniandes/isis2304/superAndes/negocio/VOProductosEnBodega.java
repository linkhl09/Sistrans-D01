package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Producto en bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProductosEnBodega 
{
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return  id de la bodega donde esta almacenado el producto.
	 */
	public long getIdBodega();
	
	/**
	 * @return Cantidad de unidades del producto almacenado en bodega.
	 */
	public int getCantidad();
	
	/**
	 * @return Nivel de abastecimiento del producto almacenado en bodega.
	 */
	public int getNivelAbastecimiento();
	
	/**
	 * @return codigo de barras del producto almacenado en bodega.
	 */
	public String getCodigoBarrasProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnBodega.
	 */
	@Override
	public String toString();	
}
