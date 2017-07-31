package es.charlye.coches.DAO;

import java.util.List;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Averia;

public interface AveriaDAO extends DAO<Averia, Long>{

	public List<Averia> obtenerGrupo(Long id) throws DAOException;
	
	public List<Averia> obtenerPorFecha(String fecha) throws DAOException;
}
