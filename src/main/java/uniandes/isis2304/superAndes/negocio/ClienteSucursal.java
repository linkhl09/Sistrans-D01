package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ClienteSucursal.
 * @author Andrés Hernández
 */
public class ClienteSucursal implements VOClienteSucursal{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String cliente;
	
	private String direccionSucursal;
	
	private String ciudadSucursal;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ClienteSucursal() 
	{
		cliente = "";
		direccionSucursal= "";
		ciudadSucursal = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param cliente
	 * @param direccionSucursal
	 * @param ciudadSucursal
	 */
	public ClienteSucursal(String cliente, String direccionSucursal, String ciudadSucursal) 
	{
		this.cliente = cliente;
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
	}
	
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public String getCliente() 
	{
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDireccionSucursal() 
	{
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) 
	{
		this.direccionSucursal = direccionSucursal;
	}

	public String getCiudadSucursal() 
	{
		return ciudadSucursal;
	}

	public void setCiudadSucursal(String ciudadSucursal) 
	{
		this.ciudadSucursal = ciudadSucursal;
	}

	/**
	 * Cadena de caracteres con todos los atributos de ClienteSurcursal.
	 */
	@Override
	public String toString()
	{
		return "ClienteSucursal [cliente =" + cliente + ", direccionSucursal ="+ direccionSucursal
				+", ciudadSucursal =" + ciudadSucursal +"]";
	}
}
