package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.AveriaDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Averia;

public class AlarmTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private AveriaDAO averia;
	private List<Averia> datos=new ArrayList<Averia>();
	
	public AlarmTableModel(AveriaDAO averia) {
		this.averia=averia;
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
		Averia preguntado= datos.get(rowIndex);
		switch(columnIndex){
		case 0:return preguntado.getId_averia();
		case 1:return preguntado.getId_vehi();
		case 2:return preguntado.getFecha_averia();
		case 3:return preguntado.getComen_averia();
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "ID_Averia";
		case 1:return "ID_Vehiculo";
		case 2:return "Fecha Averia";
		case 3:return "Comentario";
		default:return "[Desconocido]";
		}
	}
	
	public void updateModel() throws DAOException{
		datos=averia.obtenerTodos();
	}
}
