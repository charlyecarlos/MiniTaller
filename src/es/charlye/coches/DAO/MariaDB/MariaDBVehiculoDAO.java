package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.DAO.VehiculoDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Vehiculo;

/**
 * 
 * @author charlye
 *
 */
public class MariaDBVehiculoDAO implements VehiculoDAO{

	final String INSERT="INSERT INTO VEHICULO(MARCA,MODELO,ANHO,MOTOR,CHASIS,ID_PROP) VALUES (?,?,?,?,?,?)";
	final String UPDATE="UPDATE VEHICULO SET ID_VEHI=?,MARCA=?,MODELO=?,ANHO=?,MOTOR=?,CHASIS=?,ID_PROP=?";
	final String DELETE="DELETE FROM VEHICULO WHERE ID_VEHI=?";
	final String GETALL="SELECT ID_VEHI,MARCA,MODELO,ANHO,MOTOR,CHASIS,ID_PROP FROM VEHICULO";
	final String GETONE="SELECT ID_VEHI,MARCA,MODELO,ANHO,MOTOR,CHASIS,ID_PROP FROM VEHICULO WHERE ID_VEHI = ?";
	final String GETGROUP="SELECT ID_VEHI,MARCA,MODELO,ANHO,MOTOR,CHASIS,ID_PROP FROM VEHICULO WHERE ID_PROP = ?";

	private Connection conn;
	private List<Vehiculo> vehiculo;
	
	public MariaDBVehiculoDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insertar(Vehiculo a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, a.getMarca());
			stat.setString(2, a.getModelo());
			stat.setInt(3, a.getAnho());
			stat.setString(4, a.getMotor());
			stat.setString(5, a.getChasis());
			stat.setLong(6, a.getId_prop());
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
	public void modificar(Vehiculo a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setLong(1, a.getId_vehi());
			stat.setString(2, a.getMarca());
			stat.setString(3, a.getModelo());
			stat.setInt(4, a.getAnho());
			stat.setString(5, a.getMotor());
			stat.setString(6, a.getChasis());
			stat.setLong(7, a.getId_prop());
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
	public void eliminar(Vehiculo a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setLong(1, a.getId_vehi());
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
	public List<Vehiculo> obtenerTodos() throws DAOException {
		PreparedStatement stat=null;
		vehiculo = null;
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			while(set.next())
				vehiculo.add(new Vehiculo(set.getLong(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5),set.getString(6),set.getLong(7)));
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
		return vehiculo;
	}

	@Override
	public Vehiculo obtener(Long id) throws DAOException {
		PreparedStatement stat=null;
		Vehiculo vehiculo=null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setLong(1, id);
			ResultSet set=stat.executeQuery();
			set.next();
			vehiculo=new Vehiculo(set.getLong(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5),set.getString(6),set.getLong(7));
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
		return vehiculo;
	}

	/*
	 * id del propietario
	 */
	public List<Vehiculo> obtenerGrupo(Long id) throws DAOException {
		List<Vehiculo> vehiculos=new ArrayList<Vehiculo>();
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(GETGROUP);
			stat.setLong(1, id);
			ResultSet set=stat.executeQuery();
			while(set.next())
				vehiculos.add(new Vehiculo(set.getLong(1),set.getString(2),set.getString(3),set.getInt(4),set.getString(5),set.getString(6),set.getLong(7)));
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
		return vehiculos;
	}

}
