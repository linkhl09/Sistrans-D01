package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Bodega.
 * @author Andrés Hernández
 */
public class Bodega implements VOBodega{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * id generado por el sistema para la bodega. único.
	 */
	private long id;
	
	/**
	 * capacidad en peso de la bodega, por defecto en Kg.
	 */
	private double capacidadPeso;
	
	/**
	 * Capacidad en volumen de la bodega, por defecto en m^3.
	 */
	private double capacidadVolumen;
	
	/**
	 * Tipo de la bodega.
	 */
	private String tipo;
	
	/**
	 * direccion de la surcursal de la bodega.
	 */
	private String direccionSucursal;
	
	/**
	 * ciudad de la sucursal de la bodega.
	 */
	private String ciudadSucursal;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Bodega()
	{
		this.id = 0;
		this.capacidadPeso = 0;
		this.capacidadVolumen = 0;
		this.tipo = "";
		this.direccionSucursal = "";
		this.ciudadSucursal = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param id id generado por el sistema para la bodega.
	 * @param capacidadPeso capacidad en peso de la bodega, por defecto en Kg.
	 * @param capacidadVolumen Capacidad en volumen de la bodega, por defecto en m^3.
	 * @param tipo Tipo de la bodega.
	 * @param direccionSucursal direccion de la surcursal de la bodega.
	 * @param ciudadSucursal ciudad de la sucursal de la bodega.
	 */
	public Bodega(long id, double capacidadPeso, double capacidadVolumen, 
			String tipo, String direccionSucursal, String ciudadSucursal) 
	{
		super();
		this.id = id;
		this.capacidadPeso = capacidadPeso;
		this.capacidadVolumen = capacidadVolumen;
		this.tipo = tipo;
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
	}
	
	
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return id único de la bodega.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * asigna el id de la bodega.
	 * @param id nuevo id de la bodega.
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return capacidad en peso de la bodega.
	 */
	public double getCapacidadPeso() 
	{
		return capacidadPeso;
	}

	/**
	 * Asigna la capacidad de peso de la bodega.
	 * @param capacidadPeso nueva capacidad de peso de la bodega.
	 */
	public void setCapacidadPeso(double capacidadPeso) 
	{
		this.capacidadPeso = capacidadPeso;
	}

	/**
	 * @return capacidad en volumen de la bodega.
	 */
	public double getCapacidadVolumen() 
	{
		return capacidadVolumen;
	}

	/**
	 * Asigna la capacidad en volumen de la bodega.
	 * @param capacidadVolumen nueva capacidad en volumen de la bodega.
	 */
	public void setCapacidadVolumen(double capacidadVolumen) 
	{
		this.capacidadVolumen = capacidadVolumen;
	}
	
	/**
	 * @return Tipo de productos que almacena la bodega.
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Asigna el tipo de productos que almacena la bodega..
	 * @param tipo nuevo tipo del estante.
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return dirección de la sucursal de la bodega.
	 */
	public String getDireccionSucursal() 
	{
		return direccionSucursal;
	}

	/**
	 * Asigna la dirección de la sucursal de la bodega.
	 * @param direccionSucursal nueva dirección de la sucursal de la bodega.
	 */
	public void setDireccionSucursal(String direccionSucursal) 
	{
		this.direccionSucursal = direccionSucursal;
	}

	/**
	 * @return Ciudad de la sucursal de la bodega.
	 */
	public String getCiudadSucursal() 
	{
		return ciudadSucursal;
	}

	/**
	 * Asigna la ciudad de la sucursal de la bodega.
	 * @param ciudadSucursal ciudad de la sucursal de la bodega.
	 */
	public void setCiudadSucursal(String ciudadSucursal) 
	{
		this.ciudadSucursal = ciudadSucursal;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de la bodega.
	 */
	@Override
	public String toString()
	{
		return "Estante [id =" + id + ", capacidadVolumen=" + capacidadVolumen
				+ ", capacidadPeso =" + capacidadPeso + ", tipo =" +tipo 
				+ ", direccionSucursal =" + direccionSucursal + ", ciudadSucursal =" + ciudadSucursal + "]";
	}	

}
