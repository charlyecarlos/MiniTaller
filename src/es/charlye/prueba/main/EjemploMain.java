package es.charlye.prueba.main;

import java.sql.*;
import java.util.Scanner;

public class EjemploMain {
	static Scanner s=new Scanner (System.in);
	private static Connection conexion = null;
	static int id_prop=1;
	
	public static void main(String[] args) {
		try{
			conectar();
			System.out.println("Estoy dentro.");
			actualizar();
			menu();
		}catch(SQLException sql){
			sql.printStackTrace();
		}finally{
			try {
				cerrar();
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
		}
	}
	
	private static void menu() throws SQLException {
		int resp;
		do{
			System.out.println();
			System.out.println("--------------------------------------------");
			System.out.println();
			System.out.println("1-Mostrar clientes y sus vehiculo.");
			System.out.println("2-Consulta historial de un vehiculo.");
			System.out.println("3-Añadir avería.");
			System.out.println("0-Salir");
			System.out.print("Respuesta:");
			resp = s.nextInt();s.nextLine();
			System.out.println();
			System.out.println("---------------------------------------------");
			switch(resp){
			case 1:mostrarClientes();break;
			case 2:historialAverias();break;
			case 3:crearAveria();break;
			}
		}while(resp!=0);
		System.out.println("Adiós");
	}
	
	private static void crearAveria() throws SQLException {
		String insert="INSERT INTO `AVERIA`(ID_VEHI, `FECHA_AVERIA`, `COMEN_AVERIA`, `PRECIO_REPUESTO`, `PRECIO_COBRADO`) VALUES (?,?,?,?,?)";
		System.out.println();
		System.out.print("Escribe el nombre del propietario:");
		String nombre=s.nextLine();
		mostrarCliente(nombre);
		System.out.print("ID del vehículo(0 si no lo encuentras):");
		int vehi=s.nextInt();s.nextLine();
		if(vehi!=0){
			System.out.print("Fecha de la Avería (YYYY-MM-DD):");
			String fecha=s.nextLine();
			System.out.print("Comentario reparacion y detalles:");
			String comen=s.nextLine();
			System.out.print("Precio de los repuestos:");
			double coste=s.nextDouble();s.nextLine();
			System.out.print("Precio cobrado:");
			double cobro=s.nextDouble();s.nextLine();
			PreparedStatement statement=conexion.prepareStatement(insert);
			statement.setInt(2, vehi);
			statement.setString(3, fecha);
			statement.setString(4, comen);
			statement.setDouble(5, coste);
			statement.setDouble(6, cobro);
			statement.executeUpdate();
			id_prop++;
			System.out.println("Reparacion creada Correctamente.");
		}
	}

	private static void historialAverias() throws SQLException { 
		System.out.println();
		System.out.print("Escribe el nombre del cliente que deseas conocer su historial:");
		String nombre = s.nextLine().toUpperCase();
		mostrarCliente(nombre);
		System.out.print("que vehiculo desea mirar (id):");
		int modelo=s.nextInt();s.nextLine();
		System.out.println();
		String query = "SELECT FECHA_AVERIA,COMEN_AVERIA,PRECIO_REPUESTO,PRECIO_COBRADO FROM AVERIA WHERE ID_VEHI = ? ";
		PreparedStatement statement = conexion.prepareStatement(query);
		statement.setInt(1, modelo);
		ResultSet set = statement.executeQuery();
		while(set.next())
			System.out.println(set.getString("FECHA_AVERIA")+" --> "+set.getString("COMEN_AVERIA")+" - "+set.getString("PRECIO_REPUESTO")+"€ - "+set.getString("PRECIO_COBRADO")+"€.");
		System.out.println();
	}
	
	private static void mostrarClientes() throws SQLException {
		Statement statement = conexion.createStatement();
		ResultSet set = statement.executeQuery("SELECT P.NOMBRE,V.MARCA,V.MODELO,V.ANHO FROM PROPIETARIO P,VEHICULO V WHERE P.ID_PROP=V.ID_PROP");
		while(set.next())
			System.out.println(set.getString("P.NOMBRE")+" --> "+set.getString("V.MARCA")+" "+set.getString("V.MODELO")+" "+set.getInt("V.ANHO"));
		set.close();
		statement.close();
	}
	
	private static void mostrarCliente(String nombre) throws SQLException {
		String query="SELECT P.NOMBRE,V.ID_VEHI,V.MARCA,V.MODELO,V.ANHO FROM PROPIETARIO P,VEHICULO V WHERE P.ID_PROP=V.ID_PROP AND P.NOMBRE LIKE ?";
		PreparedStatement statement = conexion.prepareStatement(query);
		statement.setString(1, "%"+nombre+"%");
		ResultSet set = statement.executeQuery();
		while(set.next())
			System.out.println(set.getString("P.NOMBRE")+" --> "+set.getLong("ID_VEHI")+" "+set.getString("V.MARCA")+" "+set.getString("V.MODELO")+" "+set.getInt("V.ANHO"));
		set.close();
		statement.close();
	}

	static void conectar() throws SQLException{
		String jbdc="jdbc:mysql://localhost:3306/Coches";
		conexion = DriverManager.getConnection(jbdc, "coches", "coches");
	}
	
	static void cerrar() throws SQLException{
		if(conexion!= null)
			conexion.close();
	}
	
	static void actualizar () throws SQLException{
		boolean salir=false;
		ResultSet set=null;
		PreparedStatement statement=null;
		while(!salir){
			String query="SELECT * FROM PROPIETARIO WHERE id_prop = ?";
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_prop);
			set= statement.executeQuery();
			if(set.next())
				id_prop++;
			else
				salir=true;
		}
		set.close();
		statement.close();
	}
}
