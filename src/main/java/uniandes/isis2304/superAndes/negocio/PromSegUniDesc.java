package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromSegUniDesc extends Promocion implements VOPromSegUniDesc {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * fecha en la cual se inicia la promocion
	 */
	private Date fechaInicio;
	
	/**
	 * fecha en la cual se finaliza la promocion
	 */
	private Date fechaFin;
	
	/**
	 * producto que esta en promocion
	 */
	private String producto;

	/**
	 * tipo de promocion 1: PromPagLleveUni , 2: PronDesc , 3: PronSegunUnidDesc, 4 : PromPagueLleveCant
	 */
	private Integer tipoProm;
	
	/**
	 * porcentaje del descuento a  realizar
	 */
	private Integer descuento;

	
	
	/* ****************************************************************
	 * 			Constructores
	 *****************************************************************/
	
	public PromSegUniDesc(Date pFechaInicio,Date pFechaFin, String pCodigoProducto, Integer pDescuento)
	{
		fechaInicio = pFechaInicio;
		fechaFin = pFechaFin;
		producto= pCodigoProducto; 
		setDescuento(pDescuento);
		tipoProm =3;
	}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/

	public Integer getDescuento() {
		return descuento;
	}



	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}
	

	

	
	
}

