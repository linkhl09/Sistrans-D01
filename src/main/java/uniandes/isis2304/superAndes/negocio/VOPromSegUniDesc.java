package uniandes.isis2304.superAndes.negocio;

public interface VOPromSegUniDesc {
	/**
	 * Interfaz para los m�todos get de promocion promSegUniDes.
	 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
	 * @author Andr�s Hern�ndez y Jenifer Rodriguez */

	
	/**
	 * @return porcentaje de descuento a realizar a la segunda unidad.
	 */
	public double getDescuento() ;

	/**
	 * Asigna el porcentaje de descuento de la promocion.
	 * @param nuevo porcentaje.
	 */
	public void setDescuento(double descuento);
	
	/**
	 * Cadena de caracteres con todos los atributos de Promocion.
	 */
	@Override
	public String toString();

}
