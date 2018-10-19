package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de Producto.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProducto {
	
	/**
	 * @return codigo de barras del producto.
	 */
	public String getCodigoBarras();

	/**
	 * @return nombre del producto.
	 */
	public String getNombre();
	
	/**
	 * @return marca del producto.
	 */
	public String getMarca();
	
	/**
	 * @return precio unitario del producto.
	 */
	public double getPrecioUnitario();
	
	/**
	 * @return presentacion del producto.
	 */
	public String getPresentacion();
	
	/**
	 * @return precio unidad de medida del producto.
	 */
	public double getPrecioUnidadMedida();
	
	/**
	 * @return presentacion del producto
	 */
	public int getCantidadPresentacion();
	
	/**
	 * @return peso del producto.
	 */
	public double getPeso();
	
	/**
	 * @return unidad de medida del peso del producto.
	 */
	public String getUnidadMedidaPeso();
	
	/**
	 * @return Volumen del producto.
	 */
	public double getVolumen();
	
	/**
	 * @return unidad de medida del volumen del producto.
	 */
	public String getUnidadMedidaVolumen() ;
	
	/**
	 * @return calidad del producto.
	 */
	public double getCalidad();
	
	/**
	 * @return nivel de re orden producto.
	 */
	public int getNivelReorden();
	
	/**
	 * @return fecha vencimiento producto.
	 */
	public Date getFechaVencimiento();
	
	/**
	 * @return categoria del producto.
	 */
	public String getCategoria();
	
	/**
	 * @return Promocion actual del producto. Null si no tiene ninguna.
	 */
	public long getPromocion();
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString();
	
}
