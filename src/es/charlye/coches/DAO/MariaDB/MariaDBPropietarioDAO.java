package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.DAO.PropietarioDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Propietario;

public class MariaDBPropietarioDAO implements PropietarioDAO{
	
	Connection conn=null;
	
	final String INSERT="INSERT INTO PROPIETARIO(NOMBRE,DIRECCION,TELEFONO,EMAIL) VALUES (?,?,?,?)";
	final String UPDATE="UPDATE PROPIETARIO SET ID_PROP=?,NOMBRE=?,DIRECCION=?,TELEFONO=?,EMAIL=?";
	final String DELETE="DELETE FROM PROPIETARIO WHERE ID_PROP=?";
	final String GETALL="SELECT ID_PROP,NOMBRE,DIRECCION,TELEFONO,EMAIL FROM PROPIETARIO";
	final String GETONE= GETALL+" WHERE ID_PROP=?";
	final String GETSOME= GETALL+" WHERE NOMBRE LIKE ?";
	
	
	public MariaDBPropietarioDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertar(Propietario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getDireccion());
			stat.setString(3, a.getTelefono());
			stat.setString(4, a.getEmail());
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
	public void modificar(Propietario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setLong(1, a.getId_prop());
			stat.setString(2, a.getNombre());
			stat.setString(3, a.getDireccion());
			stat.setString(4, a.getTelefono());
			stat.setString(5, a.getEmail());
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
	public void eliminar(Propietario a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setLong(1, a.getId_prop());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya eliminado.");
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				}catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
	}

	@Override
	public List<Propietario> obtenerTodos() throws DAOException {
		PreparedStatement stat=null;
		List<Propietario> propietario=new ArrayList<Propietario>();
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			while(set.next())
				propietario.add(new Propietario(set.getLong(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
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
		return propietario;
	}

	@Override
	public Propietario obtener(Long id) throws DAOException {
		PreparedStatement stat=null;
		Propietario propietario=null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setLong(1, id);
			ResultSet set=stat.executeQuery();
			set.next();
			propietario= new Propietario(set.getLong(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5));
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
		return propietario;
	}

	@Override
	public List<Propietario> buscarPropietarios(String nombre) throws DAOException {
		PreparedStatement stat=null;
		List<Propietario> propietario=new ArrayList<Propietario>();
		try {
			stat = conn.prepareStatement(GETSOME);
			stat.setString(1,"%"+nombre+"%");
			ResultSet set=stat.executeQuery();
			while(set.next())
				propietario.add(new Propietario(set.getLong(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
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
		return propietario;
	}
	
	
}
