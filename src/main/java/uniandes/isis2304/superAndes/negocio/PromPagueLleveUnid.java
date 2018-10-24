package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromPagueLleveUnid extends Promocion implements VOPromPagueLleveUnid {

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
	 * numero que debe pagar n
	 */
	private Integer pague;

	/**
	 * numero queva a llevar m
	 */
	private Integer lleve;
	
	
	/* ****************************************************************
	 * 			Constructores
	 *****************************************************************/
	
	public PromPagueLleveUnid(Date pFechaInicio,Date pFechaFin, String pProducto
			, Integer pPague, Integer pLleve)
	{
		fechaInicio = pFechaInicio;
		fechaFin = pFechaFin;
		producto= pProducto; 
		pague = pPague;
		lleve = pLleve;
		
	}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	

	

	public Integer getPague() {
		return pague;
	}


	public void setPague(Integer pague) {
		this.pague = pague;
	}


	public Integer getLleve() {
		return lleve;
	}


	public void setLleve(Integer lleve) {
		this.lleve = lleve;
	}
	
	

	
	
}


