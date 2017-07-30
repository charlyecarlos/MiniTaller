package es.charlye.coches.DAO;

import java.util.List;

import es.charlye.coches.Exception.DAOException;

public interface DAO <T, K> {

	void insertar(T a) throws DAOException;
	
	void modificar(T a) throws DAOException;
	
	void eliminar(T a) throws DAOException;
	
	List<T> obtenerTodos() throws DAOException;
	
	T obtener(K id) throws DAOException;
}
