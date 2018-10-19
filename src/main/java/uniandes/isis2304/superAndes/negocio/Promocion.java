package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Promocion.
 * @author Andrés Hernández
 */

import java.util.Date;
public class Promocion implements VOPromocion
{
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private long id;
	
	private String descripcion;
	
	private double precio;
	
	private Date inicio;
	
	private Date fin;
	
	private int unidadesDisponibles;
	
	private int unidadesVentidas;
	
	private String proveedor;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Promocion()
	{
		this.id = 0;
		this.descripcion = "";
		this.precio = 0;
		this.inicio = new Date();
		this.fin = new Date();
		this.unidadesDisponibles = 0;
		this.unidadesVentidas = 0;
		this.proveedor = "";
	}

	/**
	 * 
	 * @param id
	 * @param descripcion
	 * @param precio
	 * @param inicio
	 * @param fin
	 * @param unidadesDisponibles
	 * @param unidadesVentidas
	 * @param proveedor
	 */
	public Promocion(long id, String descripcion, double precio, Date inicio, Date fin, int unidadesDisponibles,
			int unidadesVentidas, String proveedor) 
	{
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.inicio = inicio;
		this.fin = fin;
		this.unidadesDisponibles = unidadesDisponibles;
		this.unidadesVentidas = unidadesVentidas;
		this.proveedor = proveedor;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------


	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public double getPrecio() 
	{
		return precio;
	}

	public void setPrecio(double precio) 
	{
		this.precio = precio;
	}

	public Date getInicio() 
	{
		return inicio;
	}

	public void setInicio(Date inicio) 
	{
		this.inicio = inicio;
	}

	public Date getFin() 
	{
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public int getUnidadesDisponibles() 
	{
		return unidadesDisponibles;
	}

	public void setUnidadesDisponibles(int unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}

	public int getUnidadesVentidas() {
		return unidadesVentidas;
	}

	public void setUnidadesVentidas(int unidadesVentidas) {
		this.unidadesVentidas = unidadesVentidas;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		return "Promocion [ id =" + id + ", descripcion =" + descripcion 
				+ ", precio =" + precio + ", inicio ="+ inicio 
				+ ", fin =" + fin + ", unidadesDisponibles=" + unidadesDisponibles 
				+ ", unidadesVendidas =" + unidadesVentidas + ", proveedor ="+ proveedor + "]";
	}
}
