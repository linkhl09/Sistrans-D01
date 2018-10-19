package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOPromocion {

	public long getId();
	
	public String getDescripcion();
	
	public double getPrecio();
	
	public Date getInicio();
	
	public Date getFin();
	
	public int getUnidadesDisponibles();
	
	public int getUnidadesVentidas();
	
	public String getProveedor();

}