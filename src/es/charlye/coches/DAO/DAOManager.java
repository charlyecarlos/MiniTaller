package es.charlye.coches.DAO;


/**
 * 
 * @author charlye
 */
public interface DAOManager {
	PropietarioDAO getPropietarioDAO();
	
	VehiculoDAO getVehiculoDAO();
	
	AveriaDAO getAveriaDAO();
	
	UsuarioDAO getUsuarioDAO();
	
	Tipo_AlarmaDAO getTipo_AlarmaDAO();
	
	AlarmaDAO getAlarmaDAO();
}
