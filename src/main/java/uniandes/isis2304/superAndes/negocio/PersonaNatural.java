package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Pesona natural.
 * @author Andrés Hernández y jenifer rodriguez
 */
public class PersonaNatural extends Cliente implements VOPersonaNatural {
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	/**
	 * numero de documento de la persona.
	 */
	private String documento;
	/**
	 * tipo de documento del cliente cuando es persona natural.
	 */
	private String tipoDocumento;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public PersonaNatural()
	{
		super();
		documento = "";
		tipoDocumento = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param documento - numero de documento del cleinte
	 * @param tipoDocumento - tipo de documenrto del cleiente
	 * @param correoElectronico -correo electronico del cleinte
	 * @param nombre - nombre del cliente 
	 * @param puntos - puntod que lleva acumulados el cliente
	 */
	public PersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre, int puntos) {
		super(correoElectronico, nombre, puntos);
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return numero de documento de cliente .
	 */
	public String getDocumento()
	{
		return documento;
	}

	/**
	 * cambia el numero de documento de la persona.
	 */
	public void setDocumento(String documento) 
	{
		this.documento = documento;
	}

	/**
	 * @return tipo de documento del cliente.
	 */
	public String getTipoDocumento() 
	{
		return tipoDocumento;
	}

	/**
	 * cambia el tipo de documento del cliente.
	 */
	public void setTipoDocumento(String tipoDocumento) 
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Cadena de caracteres con todos los atributos de Persona natural.
	 */
	@Override
	public String toString()
	{
		return "PersonaNatural [correoElectronico =" + correoElectronico
				+ ", nombre =" + nombre + ", puntos ="+ puntos
				+ ", documento =" + documento +", tipoDocumento =" + tipoDocumento +"]";
	}
}
