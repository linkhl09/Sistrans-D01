package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los métodos get de Cliente .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOCliente {

	public String getCorreoElectronico();
	
	public String getNombre();
	
	public int getPuntos();
}
