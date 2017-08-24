package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.charlye.coches.DAO.*;

/**
 * @author charlye
 */
public class MariaDBDAOManager implements DAOManager{

	private Connection conn;
	
	private PropietarioDAO prop=null;
	private VehiculoDAO vehi=null;
	private AveriaDAO averia=null;
	private UsuarioDAO usuario=null;
	private Tipo_AlarmaDAO tipo_alarma=null;
	private AlarmaDAO alarma=null;
	
	public MariaDBDAOManager(String host,String username,String password,String database) throws SQLException{
		conn= DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, username, password); 
	}

	@Override
	public PropietarioDAO getPropietarioDAO() {
		if(prop==null)
			prop=new MariaDBPropietarioDAO(conn);
		return prop;
	}

	@Override
	public VehiculoDAO getVehiculoDAO() {
		if(vehi==null)
			vehi=new MariaDBVehiculoDAO(conn);
		return vehi;
	}

	@Override
	public AveriaDAO getAveriaDAO() {
		if(averia==null)
			averia=new MariaDBAveriaDAO(conn);
		return averia;
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		if(usuario==null)
			usuario=new MariaDBUsuarioDAO(conn);
		return usuario;
	}

	@Override
	public Tipo_AlarmaDAO getTipo_AlarmaDAO() {
		if(tipo_alarma==null)
			tipo_alarma=new MariaDBTipo_AlarmaDAO(conn);
		return tipo_alarma;
	}

	@Override
	public AlarmaDAO getAlarmaDAO() {
		if(alarma==null)
			alarma=new MariaDBAlarmaDAO(conn);
		return alarma;
	}
}
