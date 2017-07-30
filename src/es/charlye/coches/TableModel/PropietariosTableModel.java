package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.PropietarioDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Propietario;

public class PropietariosTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private PropietarioDAO propietario;
	private List<Propietario> datos=new ArrayList<Propietario>();
	
	public PropietariosTableModel(PropietarioDAO propietario) {
		this.propietario = propietario;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Propietario preguntado= datos.get(rowIndex);
		switch(columnIndex){
		case 0:return preguntado.getId_prop();
		case 1:return preguntado.getNombre();
		case 2:return preguntado.getDireccion();
		case 3:return preguntado.getTelefono();
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "ID";
		case 1:return "Nombre";
		case 2:return "Direccion";
		case 3:return "Telefono";
		default:return "[Desconocido]";
		}
	}
	
	public void updateModel(String nombre) throws DAOException{
		datos=propietario.buscarPropietarios(nombre);
	}

}
