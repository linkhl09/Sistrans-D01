package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de Producto en bodega.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOProductosEnBodega 
{
	
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
