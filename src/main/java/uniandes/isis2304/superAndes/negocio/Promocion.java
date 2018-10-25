package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public abstract class  Promocion  implements VOPromocion{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * identificacion de la promocion
	 */
	protected long id;
	
	/**
	 * descripcion de la promocion
	 */
	protected String descripcion;
	
	/**
	 * unidades disponibles de la promocion
	 */
	protected int unidadesDisponibles;
	
	/**
	 * unidades vendidas de la promocion
	 */
	protected int unidadesVendidas;
	
	/**
	 * fecha en la cual se inicia la promocion
	 */
	protected Date fechaInicio;
	
	/**
	 * fecha en la cual se finaliza la promocion
	 */
	protected Date fechaFin;
	
	/**
	 * producto que esta en promocion
	 */
	protected String producto;

	/* ****************************************************************
	 * 			constructores
	 *****************************************************************/
	/**
	 * Constructor vacio.
	 */
	public Promocion()
	{
		id = 0;
		descripcion= "";
		unidadesDisponibles = 0;
		unidadesVendidas = 0;
		fechaInicio= null;
		fechaFin= null;
		producto= "";	
		
	}
	
	/**
	 * Constructor con valores.
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 */
	public Promocion(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto) 
	{
		this.id = id;
		this.descripcion = descripcion;
		this.unidadesDisponibles = unidadesDisponibles;
		this.unidadesVendidas = unidadesVendidas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.producto = producto;
		
	}
	
	
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	/**
	 * @return fecha de inicio de la promocion.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicion a una promocion.
	 * @param nueva fecha de incio.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return fecha de finalizacion de la promocion.
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Asigna la fecha de culminacion de una promocion.
	 * @param nueva fecha de terminacion.
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return codigo del producto en promocion.
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Asigna el codigo del producto que esta en promocion.
	 * @param ncodigo del producto.
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * @return idetificador de la promocion.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Asigna el identificador a la promocion.
	 * @param nuevo identificador.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return descripcion de la promocion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna la descripcion a la promocion.
	 * @param descripcion.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return unidades disponibles de la promocion.
	 */
	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	/**
	 *asigna las unidades disponibles de la promocion .
	 * @param unidades disponibles.
	 */
	public void setUnidadesDisponibles(int unidades) {
		this.unidadesDisponibles = unidades;
	}
	
	/**
	 * @return unidades vendidas de la promocion.
	 */
	public int getUnidadesVendidas() {
		// TODO Auto-generated method stub
		return unidadesVendidas;
	}

	/**
	 *asigna las unidades vendidas de la promocion .
	 * @param unidades vendidas.
	 */
	public void setUnidadesVendidas(int unidades) {
		this.unidadesVendidas = unidades;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de la Promocion.
	 */
	@Override
	public String toString()
	{
		return "Promocion [identificador de la promocion ="+ id +", descripcion =" + descripcion
				+ "producto =" + producto + "unidades disponibles =" + unidadesDisponibles 
				+"unidades vendidas =" + unidadesVendidas + "fecha de inicio =" + fechaInicio
				+"fecha de finalizacion =" + fechaFin+"]";
	}
	
	
}
