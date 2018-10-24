package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public abstract class  Promocion {

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
	private Producto producto;

	/**
	 * tipo de promocion 1: PromPagLleveUni , 2: PronDesc , 3: PronSegunUnidDesc, 4 : PromPagueLleveCant
	 */
	private Integer tipoProm;
	
	
	
	
	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getTipoProm() {
		return tipoProm;
	}

	public void setTipoProm(Integer tipoProm) {
		this.tipoProm = tipoProm;
	}
	
}
