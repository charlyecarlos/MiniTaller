package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.DAO.Tipo_AlarmaDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Tipo_Alarma;

public class MariaDBTipo_AlarmaDAO implements Tipo_AlarmaDAO{

	final String INSERT="INSERT INTO TIPO_ALARMA (NOMBRE,MESES_EXP,KM_EXP) VALUES (?,?,?)";
	final String UPDATE="UPDATE TIPO_ALARMA SET NOMBRE=?,MESES_EXP=?,KM_EXP=? WHERE NOMBRE=?";
	final String DELETE="DELETE FROM TIPO_ALARMA WHERE NOMBRE=?";
	final String GETALL="SELECT NOMBRE,MESES_EXP,KM_EXP FROM TIPO_ALARMA";
	final String GETONE=GETALL+" WHERE NOMBRE=?";
	final String EXITS="SELECT COUNT(NOMBRE) FROM TIPO_ALARMA WHERE NOMBRE=?";

	private Connection conn;
	private List<Tipo_Alarma> tipo_alarma;
	
	public MariaDBTipo_AlarmaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insertar(Tipo_Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, a.getNombre());
			stat.setInt(2, a.getMeses_exp());
			stat.setInt(3, a.getKm_exp());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya guardado");
		}catch (SQLException e) {
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
	public void modificar(Tipo_Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, a.getNombre());
			stat.setInt(2, a.getMeses_exp());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya modificado");
		}catch (SQLException e) {
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
	public void eliminar(Tipo_Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setString(1, a.getNombre());
			if(stat.executeUpdate()==0)
				throw new DAOException("Puede que no se haya eliminado");
		}catch (SQLException e) {
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
	public List<Tipo_Alarma> obtenerTodos() throws DAOException {
		PreparedStatement stat=null;
		tipo_alarma=new ArrayList<Tipo_Alarma>();
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			while(set.next())
				tipo_alarma.add(new Tipo_Alarma(set.getString(1), set.getInt(2),set.getInt(3)));
		}catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return tipo_alarma;
	}

	@Override
	public Tipo_Alarma obtener(String nombre) throws DAOException {
		PreparedStatement stat=null;
		Tipo_Alarma tipo_alarma=null;
		try{
			stat=conn.prepareStatement(GETONE);
			stat.setString(1, nombre);
			ResultSet set=stat.executeQuery();
			set.next();
			tipo_alarma=new Tipo_Alarma(set.getString(1), set.getInt(2),set.getInt(3));
		}catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}finally{
			if(stat!=null)
				try {
					stat.close();
				} catch (SQLException e){
					throw new DAOException("Error en SQL", e);
				}
		}
		return tipo_alarma;
	}

	@Override
	public boolean existe(String nombre) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(EXITS);
			stat.setString(1, nombre);
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
