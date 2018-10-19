package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Proveedor.
 * @author Andrés Hernández
 */
public class Proveedor implements VOProveedor{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String nit;
	
	private String nombre;
	
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
	
	public Proveedor(String nit, String nombre, double calificacionCalidad) 
	{
		this.nit = nit;
		this.nombre = nombre;
		this.calificacionCalidad = calificacionCalidad;
	}

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCalificacionCalidad() {
		return calificacionCalidad;
	}

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
