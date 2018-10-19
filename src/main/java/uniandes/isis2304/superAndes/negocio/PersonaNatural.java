package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Pesona natural.
 * @author Andrés Hernández
 */
public class PersonaNatural extends Cliente implements VOPersonaNatural {
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String documento;
	
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
	 * @param documento
	 * @param tipoDocumento
	 * @param correoElectronico
	 * @param nombre
	 * @param puntos
	 */
	public PersonaNatural(String documento, String tipoDocumento, String correoElectronico, String nombre, int puntos) {
		super(correoElectronico, nombre, puntos);
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------


	public String getDocumento()
	{
		return documento;
	}


	public void setDocumento(String documento) 
	{
		this.documento = documento;
	}


	public String getTipoDocumento() 
	{
		return tipoDocumento;
	}


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
