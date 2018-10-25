package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromPagueLleveCant extends Promocion implements VOPromPagueLleveCant{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
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
	/**
	 * Constructor vacio.
	 */
	public PromPagueLleveCant()
	{
		id = 0;
		descripcion= "";
		unidadesDisponibles = 0;
		unidadesVendidas = 0;
		fechaInicio= null;
		fechaFin= null;
		producto= "";
		pague=0;
		lleve=0;
		
	}
	
	 /** Constructor con valores.
		 * @param id - identificador de la promocion
		 * @param descripcion - descripcion de la promocion
		 * @param unidadesDisponibles - unidades disponibles de la promocion
		 * @param unidadesVendidas - unidades de la promocion q ya fueron vendidas
		 * @param fechaInicio - fecha de inicion de la promocion
		 * @param fechaFin - fecha de finalizacion de la promocion
		 * @param poducto - codigo del producto asociado a la promocion
		 **@param pague -  cantidad del producto que se debe pagar
		 * @param lleve -  cantidad del producto que se llevara
		**/
		
		public PromPagueLleveCant(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
				, Date fechaInicio, Date fechaFin, String producto, int pague, int lleve) 
		{
		
			super(id,descripcion,unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto);
			this.pague = pague;
			this.lleve = lleve;
		
		}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	/**
	 * @return cantidad del producto que se debe pagar.
	 */
	public Integer getPague() {
		return pague;
	}

	/**
	 * Asigna la cantidad que se debe pagar del producto.
	 * @param cantidad a pagar.
	 */
	public void setPague(Integer pague) {
		this.pague = pague;
	}

	/**
	 * @return  cantidad del producto que se llevara.
	 */
	public Integer getLleve() {
		return lleve;
	}

	/**
	 * Asigna la cantidad de producto que se llevara.
	 * @param cantidad que se llevara.
	 */
	public void setLleve(Integer lleve) {
		this.lleve = lleve;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de la Promocion promPagueLleveCantidad.
	 */
	@Override
	public String toString()
	{
		return "Promocion [identificador de la promocion ="+ id +", descripcion =" + descripcion
				+ "producto =" + producto + "unidades disponibles =" + unidadesDisponibles 
				+"unidades vendidas =" + unidadesVendidas + "fecha de inicio =" + fechaInicio
				+"fecha de finalizacion =" + fechaFin+ "pague=" + pague +"lleve" + lleve +"]";
	}
	

	
	
}

