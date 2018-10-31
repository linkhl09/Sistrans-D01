package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de promPagueLleveUnidad.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez y Jenifer Rodriguez */

public interface VOPromPagueLleveUnid {

	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * @return unidades del producto que se debe pagar.
	 */
	public double getPague();
	/**
	 * Asigna las unidades que se debe pagar del producto.
	 * @param unidades del producto a pagar.
	 */
	public void setPague(Integer pague);

	/**
	 * @return unidades del producto que se llevara.
	 */
	public double getLleve();

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
