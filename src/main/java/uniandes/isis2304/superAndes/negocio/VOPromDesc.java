package uniandes.isis2304.superAndes.negocio;

public interface VOPromDesc {

	/**
	 * Interfaz para los métodos get de promocion.
	 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
	 * @author Andrés Hernández y Jenifer Rodriguez */

	
	/**
	 * @return porcentaje de descuento a realizar.
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
