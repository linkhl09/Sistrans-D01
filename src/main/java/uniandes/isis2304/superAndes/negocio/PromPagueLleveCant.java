package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromPagueLleveCant extends Promocion implements VOPromPagueLleveCant{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 *cantidad del producto que se esta pagando
	 */
	private double pague;

	/**
	 * cantidad de producto que se esta llevando
	 */
	private double lleve;
	
	
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
		 **@param pague2 -  cantidad del producto que se debe pagar
		 * @param lleve2 -  cantidad del producto que se llevara
		**/
		
		public PromPagueLleveCant(long id, String descripcion, int unidadesDisponibles,int unidadesVendidas
				, Date fechaInicio, Date fechaFin, String producto, double pague2, double lleve2) 
		{
		
			super(id,descripcion,unidadesDisponibles, unidadesVendidas, fechaInicio, fechaFin, producto);
			this.pague = pague2;
			this.lleve = lleve2;
		
		}

	/* ****************************************************************
	 * 			Metodos
	 *****************************************************************/
	
	/**
	 * @return cantidad del producto que se debe pagar.
	 */
	public double getPague() {
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
	public double getLleve() {
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
				+"fecha de finalizacion =" + fechaFin+ "pague =" + pague +"lleve" + lleve +"]";
	}

	
	

	
	
}

