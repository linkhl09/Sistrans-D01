package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Empresa.
 * @author Andrés Hernández y jenifer Rodriguez
 */
public class Empresa extends Cliente implements VOEmpresa{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * numero de identificacion unico de la empresa.
	 */
	private String nit;
	
	/**
	 * direccion de la empresa.
	 */
	private String direccion;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Empresa()
	{
		super();
		nit = "";
		direccion = "";
	}
	/**
	 * 
	 * @param nit - numero de identificacion unico de la empresa
	 * @param direccion - direccion de la empresa
	 * @param correoElectronico - correo electronico del cliente
	 * @param nombre - nombre de la empresa
	 * @param puntos - numero de puntos de  la empresa
	 */
	public Empresa(String nit, String direccion, String correoElectronico, String nombre, int puntos) {
		super(correoElectronico, nombre, puntos);				
		this.nit = nit;
		this.direccion = direccion;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return identificador de la empresa.
	 */
	public String getNit() 
	{
		return nit;
	}
	
	/**
	 * cambia el identificador de la empresa.
	 */
	public void setNit(String nit) 
	{
		this.nit = nit;
	}
	
	/**
	 * @return direccion de la empresa.
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * canbia la direccion de la empresa.
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de Empresa.
	 */
	@Override
	public String toString()
	{
		return "Empresa [ correoElectronico =" + correoElectronico
				+ ", nombre =" + nombre + ", puntos ="+ puntos 
				+ ", nit ="+ nit +", direccion =" +direccion + "]";
	}	
}
