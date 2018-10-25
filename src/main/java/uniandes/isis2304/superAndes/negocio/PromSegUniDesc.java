package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromSegUniDesc extends Promocion implements VOPromSegUniDesc {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	
	/**
	 * porcentaje del descuento a  realizar a la segunda unidad
	 */
	private double descuento;

	
	
	/* ****************************************************************
	 * 			Constructores
	 *****************************************************************/
	/**
	 * Constructor vacio.
	 */
	public PromSegUniDesc()
	{
		id = 0;
		descripcion= "";
		unidadesDisponibles = 0;
		unidadesVendidas = 0;
		fechaInicio= null;
		fechaFin= null;
		producto= "";
		descuento =0;
	}
	
	
	 /** Constructor con valores.
	 * @param id - identificador de la promocion
	 * @param descripcion - descripcion de la promocion
	 * @param unidadesDisponibles - unidades disponibles de la promocion
	 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
	 * @param fechaInicio - fecha de inicion de la promocion
	 * @param fechaFin - fecha de finalizacion de la promocion
	 * @param poducto - codigo del producto asociado a la promocion
	 **@param descuento -  porcentaje del descuento a  realizar  a la segunda unidad 
	**/
	
	public PromSegUniDesc(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
			, Date fechaInicio, Date fechaFin, String producto, int descuento) 
	{
	
		super(id,descripcion,unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto);
		this.descuento= descuento;
	
	}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/

	/**
	 * @return porcentaje de descuento a realizar.
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * Asigna el porcentaje de descuento.
	 * @param nuevo porcentaje.
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * Cadena de caracteres con todos los atributos de la Promocion promDescuento.
	 */
	@Override
	public String toString()
	{
		return "Promocion [identificador de la promocion ="+ id +", descripcion =" + descripcion
				+ "producto =" + producto + "unidades disponibles =" + unidadesDisponibles 
				+"unidades vendidas =" + unidadesVendidas + "fecha de inicio =" + fechaInicio
				+"fecha de finalizacion =" + fechaFin+ "porcentaje de descuento=" + descuento+"%" +"]";
	}
	
	

}

