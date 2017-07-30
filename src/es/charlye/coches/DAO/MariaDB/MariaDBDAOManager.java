package es.charlye.coches.DAO.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.DAO.PropietarioDAO;
import es.charlye.coches.DAO.VehiculoDAO;
import es.charlye.coches.DAO.AveriaDAO;
import es.charlye.coches.DAO.UsuarioDAO;

/**
 * @author charlye
 */
public class MariaDBDAOManager implements DAOManager{

	private Connection conn;
	
	private PropietarioDAO prop=null;
	private VehiculoDAO vehi=null;
	private AveriaDAO averia=null;
	private UsuarioDAO usuario=null;
	
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
	
//	public static void main(String[] args) throws SQLException, DAOException {
//		MariaDBDAOManager man= new MariaDBDAOManager("localhost", "coches", "coches", "Coches");
//		List<Propietario> prop=man.getPropietarioDAO().obtenerTodos();
//		for(Propietario p:prop)
//			System.out.println(p);
//	}
	
}
