package es.charlye.coches.DAO;

import java.util.List;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Vehiculo;

public interface VehiculoDAO extends DAO<Vehiculo, Long> {
	
	public List<Vehiculo> obtenerGrupo(Long id) throws DAOException;

}
