package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromPagueLleveCant extends Promocion implements VOPromPagueLleveCant{

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
	 *cantidad del producto que se esta pagando
	 */
	private Integer pague;

	/**
	 * cantidad de producto que se esta llevando
	 */
	private Integer lleve;
	
	
	/* ****************************************************************
	 * 			Constructores
	 *****************************************************************/
	
	public PromPagueLleveCant(Date pFechaInicio,Date pFechaFin, String pProducto
			, Integer pPague, Integer pLleve)
	{
		fechaInicio = pFechaInicio;
		fechaFin = pFechaFin;
		producto= pProducto; 
		pague = pPague;
		lleve = pLleve;
		tipoProm = 4;
		
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

