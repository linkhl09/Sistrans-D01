package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ProductoEnBodega.
 * @author Andrés Hernández
 */
public class ProductosEnBodega implements VOProductosEnBodega {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private long bodega;
	
	private int cantidad;
	
	private  int nivelAbastecimiento;
	
	private String producto;	

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProductosEnBodega() 
	{
		bodega = 0;
		cantidad = 0; 
		nivelAbastecimiento = 0;
		producto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param bodega
	 * @param cantidad
	 * @param nivelAbastecimiento
	 * @param producto
	 */
	public ProductosEnBodega(long bodega, int cantidad, int nivelAbastecimiento, String producto) {
		this.bodega = bodega;
		this.cantidad = cantidad;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.producto = producto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public long getBodega() {
		return bodega;
	}

	public void setBodega(long bodega) {
		this.bodega = bodega;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getNivelAbastecimiento() {
		return nivelAbastecimiento;
	}

	public void setNivelAbastecimiento(int nivelAbastecimiento) {
		this.nivelAbastecimiento = nivelAbastecimiento;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnBodega.
	 */
	@Override
	public String toString()
	{
		return "ProductosEnBodega [bodega =" + bodega + ", cantidad =" + cantidad 
				+ ", nivelAbastecimiento =" + nivelAbastecimiento 
				+ ", producto =" + producto + "]";
	}	
}
