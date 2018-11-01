package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Estante.
 * @author Andrés Hernández
 */
public class Estante implements VOEstante{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * id generado por el sistema para el estante. único.
	 */
	private long id;
	
	/**
	 * capacidad en peso del estante, por defecto en Kg.
	 */
	private double capacidadPeso;
	
	/**
	 * Capacidad en volumen del estante, por defecto en m^3.
	 */
	private double capacidadVolumen;
	
	/**
	 * Tipo del estante.
	 */
	private String tipo;
	
	/**
	 * id de la surcursal del estante.
	 */
	private long idSucursal;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Estante()
	{
		this.id = 0;
		this.capacidadPeso = 0;
		this.capacidadVolumen = 0;
		this.tipo = "";
		this.idSucursal = 0;
	}
	
	/**
	 * Constructor con valores.
	 * @param id - id generado por el sistema para el estante.
	 * @param capacidadPeso - capacidad en peso del estante, por defecto en Kg.
	 * @param capacidadVolumen - Capacidad en volumen del estante, por defecto en m^3.
	 * @param tipo - Tipo del estante. 
	 * @param idSucursal - id de la surcursal del estante.
	 */
	public Estante(long id, double capacidadPeso, double capacidadVolumen, 
			String tipo, long idSucursal) 
	{
		super();
		this.id = id;
		this.capacidadPeso = capacidadPeso;
		this.capacidadVolumen = capacidadVolumen;
		this.tipo = tipo;
		this.idSucursal = idSucursal;
	}
		
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return id único del estante.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * asigna el id del estante.
	 * @param id nuevo id del estante.
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return capacidad en peso del estante.
	 */
	public double getCapacidadPeso() 
	{
		return capacidadPeso;
	}

	/**
	 * Asigna la capacidad de peso del estante.
	 * @param capacidadPeso nueva capacidad de peso del estante.
	 */
	public void setCapacidadPeso(double capacidadPeso) 
	{
		this.capacidadPeso = capacidadPeso;
	}

	/**
	 * @return capacidad en volumen de 
	 */
	public double getCapacidadVolumen() 
	{
		return capacidadVolumen;
	}

	/**
	 * Asigna la capacidad en volumen del estante.
	 * @param capacidadVolumen nueva capacidad en volumen del estante.
	 */
	public void setCapacidadVolumen(double capacidadVolumen) 
	{
		this.capacidadVolumen = capacidadVolumen;
	}
	
	/**
	 * @return Tipo de productos que almacena el estante.
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Asigna el tipo de productos que almacena el estante.
	 * @param tipo nuevo tipo del estante.
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return id de la sucursal del estante.
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}

	/**
	 * Asigna la id de la sucursal del estante.
	 * @param idSucursal nueva id de la sucursal del estante.
	 */
	public void setIdSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos del Estante.
	 */
	@Override
	public String toString()
	{
		return "Estante [id =" + id + ", capacidadVolumen=" + capacidadVolumen
				+ ", capacidadPeso =" + capacidadPeso + ", tipo =" +tipo 
				+ ", idSucursal =" + idSucursal + "]";
	}	
}
