package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto de producto.
 * @author Andrés Hernández
 */
public class Producto implements VOProducto{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Codigo de barras del producto.
	 */
	private String codigoBarras;
	
	/**
	 * nombre del producto.
	 */
	private String nombre;
	
	/**
	 * marca del producto.
	 */
	private String marca;
	
	/**
	 * Precio unitario del producto.
	 */
	private double precioUnitario;
	
	/**
	 * presentacion del producto.
	 */
	private String presentacion;
	
	/**
	 * Precio unidad de medida del producto.
	 */
	private double precioUnidadMedida;
	
	/**
	 * cantidad en la presentacion del producto.
	 */
	private int cantidadPresentacion;
	
	/**
	 * peso del producto.
	 */
	private double peso;
	
	/**
	 * unidade de medida del peso del producto.
	 */
	private String unidadMedidaPeso;
	
	/**
	 * volumen del producto.
	 */
	private double volumen;
	
	/**
	 * unidad de medida del volumen del producto.
	 */
	private String unidadMedidaVolumen;
	
	/**
	 * calidad del producto.
	 */
	private double calidad;
	
	/**
	 * nivel de re orden del producto.
	 */
	private int nivelReorden;
	
	/**
	 * fecha de vencimiento del producto. null si no es perecedero.
	 */
	private Date fechaVencimiento;
	
	/**
	 * categoria del producto.
	 */
	private String categoria;
	
	/**
	 * promocion del producto. null si actualmente no tiene una promoción.
	 */
	private boolean estaEnPromocion;	

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	
	/**
	 * Constructor vacio.
	 */
	public Producto()
	{
		this.codigoBarras = "";
		this.nombre = "";
		this.marca = "";
		this.precioUnitario = 0;
		this.presentacion = "";
		this.precioUnidadMedida = 0;
		this.cantidadPresentacion = 0;
		this.peso = 0;
		this.unidadMedidaPeso = "";
		this.volumen = 0;
		this.unidadMedidaVolumen = "";
		this.calidad = 0;
		this.nivelReorden = 0;
		this.fechaVencimiento = null;
		this.categoria = "";
		this.estaEnPromocion = false;
	}

	/**
	 * Constructor con valores.
	 * @param codigoBarras codigo de barras del producto.
	 * @param nombre nombre del producto.
	 * @param marca marca del producto.
	 * @param precioUnitario precio unitario del producto.
	 * @param presentacion presentacion del producto.
	 * @param precioUnidadMedida precio por unidad de medida del producto.
	 * @param cantidadPresentacion cantidad en la presentacion del producto.
	 * @param peso peso del producto.
	 * @param unidadMedidaPeso unidad de medida del peso del producto.
	 * @param volumen volumen del producto.
	 * @param unidadMedidaVolumen unidad de medida del volumen del producto.
	 * @param calidad calidad del producto.
	 * @param nivelReorden nivel de re orden del producto.
	 * @param fechaVencimiento fecha de vencimiento del producto.
	 * @param categoria categoria del producto.
	 * @param promocion promocion del producto.
	 */
	public Producto(String codigoBarras, String nombre, String marca, double precioUnitario, String presentacion,
			double precioUnidadMedida, int cantidadPresentacion, double peso, String unidadMedidaPeso, double volumen,
			String unidadMedidaVolumen, double calidad, int nivelReorden, Date fechaVencimiento, String categoria,
			boolean promocion) 
	{
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.marca = marca;
		this.precioUnitario = precioUnitario;
		this.presentacion = presentacion;
		this.precioUnidadMedida = precioUnidadMedida;
		this.cantidadPresentacion = cantidadPresentacion;
		this.peso = peso;
		this.unidadMedidaPeso = unidadMedidaPeso;
		this.volumen = volumen;
		this.unidadMedidaVolumen = unidadMedidaVolumen;
		this.calidad = calidad;
		this.nivelReorden = nivelReorden;
		this.fechaVencimiento = fechaVencimiento;
		this.categoria = categoria;
		this.estaEnPromocion = promocion;
	}
	
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return codigo de barras del producto.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * Asigna el codigo de barras del producto.
	 * @param codigoBarras nuevo codigo de barras del producto.
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna le nombre del producto.
	 * @param nombre nuevo nombre del producto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return marca del producto.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Asigna la marca del producto.
	 * @param marca nueva marca del producto.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return precio unitario del producto.
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Asigna el precio unitario del producto.
	 * @param precioUnitario nuevo precio unitario.
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return presentacion del producto.
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Asigna la presentación del producto.
	 * @param presentacion nueva presentación del producto.
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return precio unidad de medida del producto.
	 */
	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}

	/**
	 * Asigna el precio por unidad de medida al producto.
	 * @param precioUnidadMedida nuevo precio por unidad de medida.
	 */
	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}

	/**
	 * @return presentacion del producto
	 */
	public int getCantidadPresentacion() {
		return cantidadPresentacion;
	}

	/**
	 * Asigna la cantidad en la presentación del producto. 
	 * @param cantidadPresentacion nueva cantidad.
	 */
	public void setCantidadPresentacion(int cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	/**
	 * @return peso del producto.
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Asigna el peso del producto.
	 * @param peso nuevo peso del producto.
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	/**
	 * @return unidad de medida del peso del producto.
	 */	
	public String getUnidadMedidaPeso() {
		return unidadMedidaPeso;
	}

	/**
	 * Asigna la unidad de medida del peso del producto.
	 * @param unidadMedidaPeso
	 */
	public void setUnidadMedidaPeso(String unidadMedidaPeso) {
		this.unidadMedidaPeso = unidadMedidaPeso;
	}

	/**
	 * @return Volumen del producto.
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * Asigna el volumen del producto.
	 * @param volumen nuevo volumen del producto.
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return unidad de medida del volumen del producto.
	 */
	public String getUnidadMedidaVolumen() {
		return unidadMedidaVolumen;
	}

	/**
	 * Asigna la unidad de medida del volumen.
	 * @param unidadMedidaVolumen nueva unidad de medida del volumen.
	 */
	public void setUnidadMedidaVolumen(String unidadMedidaVolumen) {
		this.unidadMedidaVolumen = unidadMedidaVolumen;
	}

	/**
	 * @return calidad del producto.
	 */
	public double getCalidad() {
		return calidad;
	}

	/**
	 * Asigna la calidad del producto.
	 * @param calidad nueva calidad del producto.
	 */
	public void setCalidad(double calidad) {
		this.calidad = calidad;
	}

	/**
	 * @return nivel de re orden producto.
	 */
	public int getNivelReorden() {
		return nivelReorden;
	}
	
	/**
	 * Asigna el nivel de re orden del producto.
	 * @param nivelReorden nuevo nivel de re orden.
	 */
	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}
	
	/**
	 * @return fecha vencimiento producto.
	 */	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Asigna una fecha de vencimiento al producto.
	 * @param fechaVencimiento nueva fecha de vencimiento.
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	/**
	 * @return categoria del producto.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Asigna una categoria al producto.
	 * @param categoria nueva categoria.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Promocion actual del producto. Null si no tiene ninguna.
	 */
	public boolean getEstaEnPromocion() {
		return estaEnPromocion;
	}

	/**
	 * Asigna una promocion al producto.
	 * @param promocion nueva promocion.
	 */
	public void setEstaEnPromocion(boolean promocion) {
		this.estaEnPromocion = promocion;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		String msj = "Producto [codigoBarras=" +codigoBarras + ", nombre =" + nombre + ", marca =" + marca
				+ ", precioUnitario=" + precioUnitario + ", presentacion =" + presentacion 
				+ ", precioUnidadMedida=" + precioUnidadMedida + ", cantidadPresentacion=" + cantidadPresentacion	
				+ ", peso=" + peso + ", unidadMedidaPeso=" + unidadMedidaPeso 
				+ ", volumen=" + volumen + ", unidadMedidaVolumen =" + unidadMedidaVolumen + ", calidad =" + calidad 
				+ ", niverReorden=" + nivelReorden;
		if (fechaVencimiento != null)
			msj += ", fechaVencimiento =" + fechaVencimiento.toString();
		else
			msj += ", fechaVencimiento =" + fechaVencimiento;
		msj +=	", categoria=" + categoria + ", estaEnPromocion" + estaEnPromocion + "]";
		
		return msj;
	}
}