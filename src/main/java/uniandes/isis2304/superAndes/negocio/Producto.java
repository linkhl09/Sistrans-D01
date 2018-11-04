package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto de producto.
 * @author Andr�s Hern�ndez
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
	 * Nombre del producto.
	 */
	private String nombre;
	
	/**
	 * Marca del producto.
	 */
	private String marca;
	
	/**
	 * Precio unitario del producto.
	 */
	private double precioUnitario;
	
	/**
	 * Presentacion del producto.
	 */
	private String presentacion;
	
	/**
	 * Precio unidad de medida del producto.
	 */
	private double precioUnidadMedida;
	
	/**
	 * Cantidad en la presentacion del producto.
	 */
	private int cantidadPresentacion;
	
	/**
	 * Peso del producto.
	 */
	private double peso;
	
	/**
	 * Unidad de medida del peso del producto.
	 */
	private String unidadMedidaPeso;
	
	/**
	 * Volumen del producto.
	 */
	private double volumen;
	
	/**
	 * Unidad de medida del volumen del producto.
	 */
	private String unidadMedidaVolumen;
	
	/**
	 * Calidad del producto.
	 */
	private double calidad;
	
	/**
	 * Nivel de re orden del producto.
	 */
	private int nivelReorden;
	
	/**
	 * Fecha de vencimiento del producto. Null si no es perecedero.
	 */
	private Date fechaVencimiento;
	
	/**
	 * Categoria del producto.
	 */
	private String categoria;
	
	/**
	 * Boolean que indica si el producto esta en promoci�n. 
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
	 * @param promocion boolean que indica si el producto esta en promocion.
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
    // M�todos.
    // -----------------------------------------------------------------

	/**
	 * @return codigo de barras del producto.
	 */
	public String getCodigoBarras() 
	{
		return codigoBarras;
	}

	/**
	 * Asigna el codigo de barras del producto.
	 * @param codigoBarras nuevo codigo de barras del producto.
	 */
	public void setCodigoBarras(String codigoBarras) 
	{
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return nombre del producto.
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * Asigna le nombre del producto.
	 * @param nombre nuevo nombre del producto.
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return marca del producto.
	 */
	public String getMarca() 
	{
		return marca;
	}

	/**
	 * Asigna la marca del producto.
	 * @param marca nueva marca del producto.
	 */
	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	/**
	 * @return precio unitario del producto.
	 */
	public double getPrecioUnitario() 
	{
		return precioUnitario;
	}

	/**
	 * Asigna el precio unitario del producto.
	 * @param precioUnitario nuevo precio unitario.
	 */
	public void setPrecioUnitario(double precioUnitario)
	{
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return presentacion del producto.
	 */
	public String getPresentacion() 
	{
		return presentacion;
	}

	/**
	 * Asigna la presentaci�n del producto.
	 * @param presentacion nueva presentaci�n del producto.
	 */
	public void setPresentacion(String presentacion)
	{
		this.presentacion = presentacion;
	}

	/**
	 * @return precio unidad de medida del producto.
	 */
	public double getPrecioUnidadMedida()
	{
		return precioUnidadMedida;
	}

	/**
	 * Asigna el precio por unidad de medida al producto.
	 * @param precioUnidadMedida nuevo precio por unidad de medida.
	 */
	public void setPrecioUnidadMedida(double precioUnidadMedida)
	{
		this.precioUnidadMedida = precioUnidadMedida;
	}

	/**
	 * @return presentacion del producto
	 */
	public int getCantidadPresentacion()
	{
		return cantidadPresentacion;
	}

	/**
	 * Asigna la cantidad en la presentaci�n del producto. 
	 * @param cantidadPresentacion nueva cantidad.
	 */
	public void setCantidadPresentacion(int cantidadPresentacion)
	{
		this.cantidadPresentacion = cantidadPresentacion;
	}

	/**
	 * @return peso del producto.
	 */
	public double getPeso()
	{
		return peso;
	}

	/**
	 * Asigna el peso del producto.
	 * @param peso nuevo peso del producto.
	 */
	public void setPeso(double peso)
	{
		this.peso = peso;
	}
	
	/**
	 * @return unidad de medida del peso del producto.
	 */	
	public String getUnidadMedidaPeso()
	{
		return unidadMedidaPeso;
	}

	/**
	 * Asigna la unidad de medida del peso del producto.
	 * @param unidadMedidaPeso
	 */
	public void setUnidadMedidaPeso(String unidadMedidaPeso)
	{
		this.unidadMedidaPeso = unidadMedidaPeso;
	}

	/**
	 * @return Volumen del producto.
	 */
	public double getVolumen()
	{
		return volumen;
	}

	/**
	 * Asigna el volumen del producto.
	 * @param volumen nuevo volumen del producto.
	 */
	public void setVolumen(double volumen) 
	{
		this.volumen = volumen;
	}

	/**
	 * @return unidad de medida del volumen del producto.
	 */
	public String getUnidadMedidaVolumen() 
	{
		return unidadMedidaVolumen;
	}

	/**
	 * Asigna la unidad de medida del volumen.
	 * @param unidadMedidaVolumen nueva unidad de medida del volumen.
	 */
	public void setUnidadMedidaVolumen(String unidadMedidaVolumen) 
	{
		this.unidadMedidaVolumen = unidadMedidaVolumen;
	}

	/**
	 * @return calidad del producto.
	 */
	public double getCalidad() 
	{
		return calidad;
	}

	/**
	 * Asigna la calidad del producto.
	 * @param calidad nueva calidad del producto.
	 */
	public void setCalidad(double calidad) 
	{
		this.calidad = calidad;
	}

	/**
	 * @return nivel de re orden producto.
	 */
	public int getNivelReorden() 
	{
		return nivelReorden;
	}
	
	/**
	 * Asigna el nivel de re orden del producto.
	 * @param nivelReorden nuevo nivel de re orden.
	 */
	public void setNivelReorden(int nivelReorden) 
	{
		this.nivelReorden = nivelReorden;
	}
	
	/**
	 * @return fecha vencimiento producto.
	 */	
	public Date getFechaVencimiento()
	{
		return fechaVencimiento;
	}

	/**
	 * Asigna una fecha de vencimiento al producto.
	 * @param fechaVencimiento nueva fecha de vencimiento.
	 */
	public void setFechaVencimiento(Date fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}
	
	/**
	 * @return categoria del producto.
	 */
	public String getCategoria()
	{
		return categoria;
	}

	/**
	 * Asigna una categoria al producto.
	 * @param categoria nueva categoria.
	 */
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

	/**
	 * @return Promocion actual del producto. Null si no tiene ninguna.
	 */
	public boolean getEstaEnPromocion()
	{
		return estaEnPromocion;
	}

	/**
	 * Asigna una promocion al producto.
	 * @param promocion nueva promocion.
	 */
	public void setEstaEnPromocion(boolean promocion)
	{
		this.estaEnPromocion = promocion;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de Producto.
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
		msj +=	", categoria=" + categoria + ", estaEnPromocion=" + estaEnPromocion + "]";
		return msj;
	}
}