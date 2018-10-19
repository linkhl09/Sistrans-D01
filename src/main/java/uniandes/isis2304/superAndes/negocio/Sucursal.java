package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández
 */
public class Sucursal implements VOSucursal{
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String direccion;
	
	private String ciudad;
	
	private String nombre;
	
	private String segmentacionMercado;
	
	private int tamanio;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	

	/**
	 * Constructor vacio.
	 */
	public Sucursal() 
	{
		this.direccion = "";
		this.ciudad = "";
		this.nombre = "";
		this.segmentacionMercado = "";
		this.tamanio = 0;
	}
    
	/**
	 * Constructor con valores.
	 * @param direccion
	 * @param ciudad
	 * @param nombre
	 * @param segmentacionMercado
	 * @param tamanio
	 */
	public Sucursal(String direccion, String ciudad, String nombre, String segmentacionMercado, int tamanio) 
	{
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.segmentacionMercado = segmentacionMercado;
		this.tamanio = tamanio;
	}
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	
	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	public String getCiudad() 
	{
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegmentacionMercado() {
		return segmentacionMercado;
	}

	public void setSegmentacionMercado(String segmentacionMercado) {
		this.segmentacionMercado = segmentacionMercado;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		return "Sucursal [direccion =" + direccion + ", ciudad =" + ciudad 
				+ ", nombre =" + nombre + ", segmentacionMercado =" + segmentacionMercado 
				+ ", tamanio =" + tamanio + "]";
	}

}
