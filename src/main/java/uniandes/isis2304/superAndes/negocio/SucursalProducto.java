package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de SucursalProducto.
 * @author Andrés Hernández
 */
public class SucursalProducto implements VOSucursalProducto{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String direccionSucursal;
	
	private String ciudadSucursal;
	
	private String producto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public SucursalProducto() 
	{
		direccionSucursal = "";
		ciudadSucursal = "";
		producto = "";
	}

	/**
	 * Constructor con valores.
	 * @param direccionSucursal
	 * @param ciudadSucursal
	 * @param producto
	 */
	public SucursalProducto(String direccionSucursal, String ciudadSucursal, String producto) 
	{
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
		this.producto = producto;
	}
   
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public String getDireccionSucursal()
	{
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getCiudadSucursal() {
		return ciudadSucursal;
	}

	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Cadena de caracteres con todos los atributos de SucursalProducto.
	 */
	@Override
	public String toString()
	{
		return "SucursalProducto [direccionSucursal =" + direccionSucursal 
				+ ", ciudadSucursal =" + ciudadSucursal 
				+ ", producto" + producto +"]";
	}
}
