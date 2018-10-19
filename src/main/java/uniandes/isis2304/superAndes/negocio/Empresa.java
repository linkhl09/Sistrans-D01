package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Empresa.
 * @author Andrés Hernández
 */
public class Empresa extends Cliente implements VOEmpresa{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	private String nit;
	
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
	 * @param nit
	 * @param direccion
	 * @param correoElectronico
	 * @param nombre
	 * @param puntos
	 */
	public Empresa(String nit, String direccion, String correoElectronico, String nombre, int puntos) {
		super(correoElectronico, nombre, puntos);				
		this.nit = nit;
		this.direccion = direccion;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public String getNit() 
	{
		return nit;
	}
	
	public void setNit(String nit) 
	{
		this.nit = nit;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}
	
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
