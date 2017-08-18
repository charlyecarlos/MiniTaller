package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.DAO.UsuarioDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;

/**
 * 
 * @author charlye
 *
 */
public class MariaDBUsuarioDAO implements UsuarioDAO{

	final String INSERT="INSERT INTO USUARIO (USUARIO,CONTRASENHA,PRIVILEGIO) VALUES(?,?,?)";
	final String UPDATE="UPDATE USUARIO SET USUARIO=?,CONTRASENHA=? WHERE USUARIO=?";
	final String DELETE="DELETE FROM USUARIO WHERE USUARIO=?";
	final String GETALL="SELECT USUARIO,CONTRASENHA,PRIVILEGIO FROM USUARIO";
	final String GETONE=GETALL+" WHERE USUARIO=?";
	final String VALIDATE="SELECT COUNT(USUARIO) FROM USUARIO WHERE USUARIO=? AND CONTRASENHA=?";
	final String EXITS="SELECT COUNT(USUARIO) FROM USUARIO WHERE USUARIO=?";
	
	private Connection conn;
	
	public MariaDBUsuarioDAO(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insertar(Usuario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, a.getUser());
			stat.setString(2, a.getPassword());
			stat.setInt(3, a.getPrivileges());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya guardado.");
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
	}

	@Override
	public void modificar(Usuario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, a.getUser());
			stat.setString(2, a.getPassword());
			stat.setString(3, a.getUser());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya guardado.");
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		
	}

	@Override
	public void eliminar(Usuario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setString(1, a.getUser());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya eliminado.");
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
	}

	@Override
	public List<Usuario> obtenerTodos() throws DAOException {
		List<Usuario> a=new ArrayList<Usuario>();
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			while(set.next())
				a.add(new Usuario(set.getString(1), set.getString(2),set.getInt(3)));
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return a;
	}

	@Override
	public Usuario obtener(String nombre) throws DAOException {
		PreparedStatement stat=null;
		Usuario usuario=null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setString(1, nombre);
			ResultSet set=stat.executeQuery();
			set.next();
			usuario=new Usuario(set.getString(1), set.getString(2),set.getInt(3));
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return usuario;
	}

	@Override
	public boolean correcto(String usuario,String contrasenha) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(VALIDATE);
			stat.setString(1, usuario);
			stat.setString(2, contrasenha);
			ResultSet rs=stat.executeQuery();
			rs.next();
			if(rs.getInt(1)==1)
				return true;
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return false;
	}

	@Override
	public boolean existe(String usuario) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(EXITS);
			stat.setString(1, usuario);
			ResultSet set=stat.executeQuery();
			set.next();
			if(set.getInt(1)==1)
				return true;
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return false;
	}
	
}
