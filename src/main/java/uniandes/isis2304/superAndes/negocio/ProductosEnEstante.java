package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ProductosEnEstante.
 * @author Andrés Hernández
 */
public class ProductosEnEstante implements VOProductosEnEstante {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private long estante;
	
	private int cantidad;
	
	private String producto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProductosEnEstante() 
	{
		estante = 0;
		cantidad = 0;
		producto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param estante
	 * @param cantidad
	 * @param producto
	 */
	public ProductosEnEstante(long estante, int cantidad, String producto) 
	{
		this.estante = estante;
		this.cantidad = cantidad;
		this.producto = producto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public long getEstante() {
		return estante;
	}

	public void setEstante(long estante) {
		this.estante = estante;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnEstante.
	 */
	@Override
	public String toString()
	{
		return "ProductosEnEstante [ estante ="+ estante +", cantidad =" +cantidad
				+ "producto =" + producto + "]";
	}
}
