package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Cliente.
 * @author Andrés Hernández y jenifer rodriguez
 */
public abstract class Cliente implements VOCliente{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 *coreo electronico del cliente
	 */
	protected String correoElectronico;
	
	/**
	 *nombre del cliente
	 */
	protected String nombre;
	
	/**
	 *puntos que lleva acumulado el cliente (para requerimientos futuros)
	 */
	protected int puntos;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Cliente()
	{
		correoElectronico = "";
		nombre= "";
		puntos = 0;
	}
	
	/**
	 * Constructor con valores.
	 * @param correoElectronico
	 * @param nombre del cliente
	 * @param puntos
	 */
	public Cliente(String correoElectronico, String nombre, int puntos) 
	{
		this.correoElectronico = correoElectronico;
		this.nombre = nombre;
		this.puntos = puntos;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	
	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public int getPuntos() 
	{
		return puntos;
	}

	public void setPuntos(int puntos) 
	{
		this.puntos = puntos;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de cliente.
	 */
	@Override
	public String toString()
	{
		return "Cliente [correoElectronico =" + correoElectronico
				+ ", nombre =" + nombre + ", puntos ="+ puntos+ "]";
	}
}
