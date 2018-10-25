package uniandes.isis2304.superAndes.negocio;

public interface VOPromPagueLleveUnid {

	/**
	 * Interfaz para los métodos get de promPagueLleveUnidad.
	 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
	 * @author Andrés Hernández y Jenifer Rodriguez */

	
	/**
	 * @return unidades del producto que se debe pagar.
	 */
	public Integer getPague();
	/**
	 * Asigna las unidades que se debe pagar del producto.
	 * @param unidades del producto a pagar.
	 */
	public void setPague(Integer pague);

	/**
	 * @return unidades del producto que se llevara.
	 */
	public Integer getLleve();

	/**
	 * Asigna las unidades de producto que se llevara.
	 * @param unidades del producto que se llevara.
	 */
	public void setLleve(Integer lleve);
	
	/**
	 * Cadena de caracteres con todos los atributos de Promocion.
	 */
	@Override
	public String toString();
}
