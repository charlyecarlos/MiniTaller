package es.charlye.coches.DAO;

import java.util.List;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Propietario;

public interface PropietarioDAO extends DAO<Propietario, Long>{
	
	public List<Propietario> buscarPropietarios(String nombre) throws DAOException;

}
