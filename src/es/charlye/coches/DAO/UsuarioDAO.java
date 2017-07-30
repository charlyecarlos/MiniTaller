package es.charlye.coches.DAO;

import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;

public interface UsuarioDAO extends DAO<Usuario, String>{

	public boolean correcto(String usuario, String password)throws DAOException;
	
	public boolean existe(String usuario)throws DAOException;
}
