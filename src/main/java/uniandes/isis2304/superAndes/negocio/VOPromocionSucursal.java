package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de promocion promSegUniDes.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez y Jenifer Rodriguez */

public interface VOPromocionSucursal
{

	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * @return el identificador de la promocion
	 */
	public long getIdPromocion();

	/**
	 * @param idPromocion el nuevo identificador de la promocion
	 */
	public void setIdPromocion(long idPromocion);

	/**
	 * @return el identificador de la sucursal
	 */
	public long getIdSucursal();

	/**
	 * @param idSucursal el nuevo identificador de la sucursal
	 */
	public void setIdSucursal(long idSucursal);

}
