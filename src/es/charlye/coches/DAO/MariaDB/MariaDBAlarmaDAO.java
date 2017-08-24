package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.charlye.coches.DAO.AlarmaDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Alarma;
import es.charlye.coches.Modelo.ModeloAlerta;

public class MariaDBAlarmaDAO implements AlarmaDAO{

	final String INSERT="INSERT INTO ALARMA (ID_VEHI,NOMBRE,FECHA_ALARMA,KM_ACTUAL) VALUES(?,?,?,?)";
	final String UPDATE="UPDATE ALARMA SET ID_VEHI=?,NOMBRE=?,FECHA_ALARMA=?,KM_ACTUAL=? WHERE ID_VEHI=? AND NOMBRE=?";//NO SE UTILIZARA
	final String DELETE="DELETE FROM ALARMA WHERE ID_VEHI=? AND NOMBRE=?";
	final String GETALL="SELECT ID_VEHI,NOMBRE,FECHA_ALARMA,KM_ACTUAL FROM ALARMA";
	final String GETONE=GETALL+" WHERE USUARIO=?";//NO SE UTILIZARA
	
	final String ALARM="SELECT P.NOMBRE,P.TELEFONO,P.EMAIL,V.MARCA,V.MODELO,T.NOMBRE,DATE_ADD(A.FECHA_ALARMA,INTERVAL T.MESES_EXP MONTH),(A.KM_ACTUAL+T.KM_EXP) FROM PROPIETARIO P,VEHICULO V,TIPO_ALARMA T,ALARMA A WHERE V.ID_VEHI=A.ID_VEHI AND T.NOMBRE=A.NOMBRE AND P.ID_PROP=V.ID_PROP AND (DATE_ADD(A.FECHA_ALARMA,INTERVAL T.MESES_EXP MONTH)<=DATE_ADD(CURDATE(), INTERVAL 1 WEEK) OR (A.KM_ACTUAL+T.KM_EXP) <= (SELECT MAX(KM_ACTUAL) FROM AVERIA WHERE ID_VEHI = A.ID_VEHI)) ORDER BY 7";
	final String BORRAR="SELECT ID_VEHI,NOMBRE,FECHA_ALARMA,KM_ACTUAL FROM ALARMA WHERE ID_VEHI = (SELECT ID_VEHI FROM VEHICULO WHERE MARCA = ? AND MODELO = ? AND ID_PROP = (SELECT ID_PROP FROM PROPIETARIO WHERE NOMBRE = ? AND TELEFONO = ? AND EMAIL = ? ) ) AND FECHA_ALARMA = (SELECT DATE_SUB( ? ,INTERVAL MESES_EXP MONTH) FROM TIPO_ALARMA WHERE NOMBRE = ?)";
	
	private Connection conn;
	private List<Alarma> alarma;
	

	public MariaDBAlarmaDAO(Connection conn) {
		this.conn=conn;
	}
	
	
	@Override
	public void insertar(Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setLong(1, a.getId_vehi());
			stat.setString(2, a.getNombre());
			stat.setString(3, a.getFecha());
			stat.setInt(4, a.getKm_actual());
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
	public void modificar(Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setLong(1, a.getId_vehi());
			stat.setString(2, a.getNombre());
			stat.setLong(3, a.getId_vehi());
			stat.setString(4, a.getNombre());
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
	public void eliminar(Alarma a) throws DAOException {
		PreparedStatement stat=null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setLong(1, a.getId_vehi());
			stat.setString(2, a.getNombre());
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
	public List<Alarma> obtenerTodos() throws DAOException {
		PreparedStatement stat=null;
		alarma=new ArrayList<Alarma>();
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			while(set.next())
				alarma.add(new Alarma(set.getLong(1), set.getString(2),set.getString(3),set.getInt(4)));
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
		return alarma;
	}

	@Override
	public Alarma obtener(Alarma a) throws DAOException {
		PreparedStatement stat=null;
		Alarma alarma=null;
		try {
			stat = conn.prepareStatement(GETALL);
			ResultSet set=stat.executeQuery();
			set.next();
			alarma=new Alarma(set.getLong(1), set.getString(2),set.getString(3),set.getInt(4));
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
		return alarma;
	}


	@Override
	public List<ModeloAlerta> obtenerAlertas() throws DAOException {
		PreparedStatement stat=null;
		List<ModeloAlerta> respuesta=new ArrayList<ModeloAlerta>();
		try {
			stat = conn.prepareStatement(ALARM);
			ResultSet set=stat.executeQuery();
			while(set.next())
				respuesta.add(new ModeloAlerta(set.getString(1),set.getString(2), set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7),set.getString(8)));
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
		return respuesta;
	}
	
	public Alarma encontrarAlarma(ArrayList<String> a) throws DAOException{
		PreparedStatement stat=null;
		Alarma alarma=null;
		try {
			stat = conn.prepareStatement(BORRAR);
			stat.setString(1, a.get(3));
			stat.setString(2, a.get(4));
			stat.setString(3, a.get(0));
			stat.setString(4, a.get(1));
			stat.setString(5, a.get(2));
			stat.setString(6, a.get(6));
			stat.setString(7, a.get(5));
			ResultSet set=stat.executeQuery();
			set.next();
			alarma=new Alarma(set.getLong(1), set.getString(2),set.getString(3),set.getInt(4));
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
		return alarma;
	}
}
