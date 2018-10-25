package uniandes.isis2304.superAndes.negocio;

public interface VOPromPagueLleveCant {

	/**
	 * Interfaz para los m�todos get de promPagueLleveCantidad.
	 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
	 * @author Andr�s Hern�ndez y Jenifer Rodriguez */

	
	/**
	 * @return cantidad del producto que se debe pagar.
	 */
	public Integer getPague();
	/**
	 * Asigna la cantidad que se debe pagar del producto.
	 * @param cantidad a pagar.
	 */
	public void setPague(Integer pague);

	/**
	 * @return  cantidad del producto que se llevara.
	 */
	public Integer getLleve();

	/**
	 * Asigna la cantidad de producto que se llevara.
	 * @param cantidad que se llevara.
	 */
	public void setLleve(Integer lleve);
	
	/**
	 * Cadena de caracteres con todos los atributos de Promocion.
	 */
	@Override
	public String toString();

}
