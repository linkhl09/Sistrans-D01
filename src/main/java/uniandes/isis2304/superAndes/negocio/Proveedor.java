package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Proveedor.
 * @author Andrés Hernández y Jenifer Rodriguez
 */
public class Proveedor implements VOProveedor{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * numero unico de identificacion del proveedor.
	 */
	private String nit;
	
	/**
	 * nombre del proveedor.
	 */
	private String nombre;
	
	/**
	 * calificacion del proveedor.
	 */
	private double calificacionCalidad;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Proveedor()
	{
		nit = "";
		nombre= "";
		calificacionCalidad = 0;
	}
	
	/**
	 * Constructor con valores.
	 * @param nit - numero unico de identificacion del proveedor.
	 * @param nombre - nombre del proveedor. 
	 * @param calificacion de calidad -  calificacion de calidad de el proveedor 
	 */
	public Proveedor(String nit, String nombre, double calificacionCalidad) 
	{
		this.nit = nit;
		this.nombre = nombre;
		this.calificacionCalidad = calificacionCalidad;
	}

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return numero unico de identificacion del proveedor.
	 */
	public String getNit() {
		return nit;
	}

	/**cambia el numero de identificacion del proveedor
	 * @param nueva nuemero de identificacion  
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return nombre del proveedor.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del proveedor
	 * @param nueva nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return calificacion de calidad de el proveedor.
	 */
	public double getCalificacionCalidad() {
		return calificacionCalidad;
	}

	/**
	 * Cambia la calidad del proveedor
	 * @param calificacion del proveedor
	 */
	public void setCalificacionCalidad(double calificacionCalidad) {
		this.calificacionCalidad = calificacionCalidad;
	}

	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		return "Proveedor [nit ="+ nit + ", nombre =" + nombre +
				", calificacionCalidad ="+ calificacionCalidad + "]";
	} 

}
