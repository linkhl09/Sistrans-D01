package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de la relacion promocion sucursal.
 * @author Andrés Hernández y jenifer Rodriguez
 */
public class PromocionSucursal {
	
	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------
	/**
	 * Iddentificador de la promocion
	 */
	private long idPromocion;

	/**
	 * identificador de la sucursal
	 */	
	private long idSucursal;

	// -----------------------------------------------------------------
	// Constructores.
	// -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public PromocionSucursal()
	{
		setIdPromocion(0);
		setIdSucursal(0);
	
	}

	/**
	 * Constructor con valores.
	 * @param numero de la factura a  la que esta asociado el producto
	 * @param cantidad del producto
	 * @param codigo del producto asociado a la factura 
	 */
	public PromocionSucursal(long idPromocion, long idSucursal ) 
	{
		this.idPromocion= idPromocion;
		this.idSucursal= idSucursal;
	}

	// -----------------------------------------------------------------
	// Métodos.
	// -----------------------------------------------------------------
	
	/**
	 * @return el identificador de la promocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion el nuevo identificador de la promocion
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	/**
	 * @return el identificador de la sucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal el nuevo identificador de la sucursal
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	
}
