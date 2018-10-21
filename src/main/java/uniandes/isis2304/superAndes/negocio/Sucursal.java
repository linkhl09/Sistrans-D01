package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Sucursal.
 * @author Andrés Hernández
 */
public class Sucursal implements VOSucursal{
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * id generado por el sistema para la sucursal.
	 */
	private long id;
	
	/**
	 * dirección de la sucursal.
	 */
	private String direccion;
	
	/**
	 * Ciudad de la sucursal.
	 */
	private String ciudad;
	
	/**
	 * Nombre de la sucursal.
	 */
	private String nombre;
	
	/**
	 * Segmentación de mercado de la sucursal.
	 */
	private String segmentacionMercado;
	
	/**
	 * Tamaño de la sucursal.
	 */
	private int tamanio;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Sucursal() 
	{
		this.id = 0;
		this.direccion = "";
		this.ciudad = "";
		this.nombre = "";
		this.segmentacionMercado = "";
		this.tamanio = 0;
	}
    
	/**
	 * Constructor con valores para la sucursal.
	 * @param id id generado por el sistema para la sucursal.
	 * @param direccion dirección de la nueva sucursal.
	 * @param ciudad Ciudad de la nueva sucursal.
	 * @param nombre Nombre de la nueva sucursal.
	 * @param segmentacionMercado Segmentación de mercado de la nueva sucursal.
	 * @param tamanio Tamaño de la nueva sucursal.
	 */
	public Sucursal(long id, String direccion, String ciudad, String nombre, String segmentacionMercado, int tamanio) 
	{
		this.id = id;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.segmentacionMercado = segmentacionMercado;
		this.tamanio = tamanio;
	}
    
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return El id de la sucursal.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id de la sucursal.
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return La dirección de la sucursal.
	 */
	public String getDireccion() 
	{
		return direccion;
	}

	/**
	 * @param direccion - La nueva dirección de la sucursal.
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	/**
	 * @return La ciudad de la sucursal.
	 */
	public String getCiudad() 
	{
		return ciudad;
	}

	/**
	 * @param ciudad - La nueva ciudad de la sucursal.
	 */
	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}

	/**
	 * @return El nombre de la sucursal.
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre de la sucursal.
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return la segmentación de mercado de la sucursal.
	 */
	public String getSegmentacionMercado() 
	{
		return segmentacionMercado;
	}

	/**
	 * @param segmentacionMercado - La nueva segmentación de mercado de la sucursal.
	 */
	public void setSegmentacionMercado(String segmentacionMercado) 
	{
		this.segmentacionMercado = segmentacionMercado;
	}

	/**
	 * @return el tamaño de la sucursal.
	 */
	public int getTamanio() 
	{
		return tamanio;
	}

	/**
	 * @param tamanio El nuevo tamaño de la sucursal.
	 */
	public void setTamanio(int tamanio) 
	{
		this.tamanio = tamanio;
	}

	/**
	 * Cadena de caracteres con todos los atributos de la sucursal.
	 */
	@Override
	public String toString()
	{
		return "Sucursal [id =" + id + ", direccion =" + direccion + ", ciudad =" + ciudad 
				+ ", nombre =" + nombre + ", segmentacionMercado =" + segmentacionMercado 
				+ ", tamanio =" + tamanio + "]";
	}

}
