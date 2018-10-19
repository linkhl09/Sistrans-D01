package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Historial promociones.
 * @author Andrés Hernández
 */
public class HistorialPromociones implements VOHistorialPromociones{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String producto;
	
	private long promocion;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public HistorialPromociones()
	{
		producto = "";
		promocion = 0;
	}
	
	/**
	 * Constructor con valores.
	 * @param producto 
	 * @param promocion
	 */
	public HistorialPromociones(String producto, long promocion) {
		this.producto = producto;
		this.promocion = promocion;
	}
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public String getProducto() {
		return producto;
	}
	

	public void setProducto(String producto) {
		this.producto = producto;
	}

	
	public long getPromocion() {
		return promocion;
	}

	
	public void setPromocion(long promocion) {
		this.promocion = promocion;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		return "HistorialPromociones[producto ="+ producto + ", promocion =" + promocion + "]";
	}
}