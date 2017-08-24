package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.AlarmaDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.ModeloAlerta;

public class AlarmTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private AlarmaDAO alarma;
	private List<ModeloAlerta> datos=new ArrayList<ModeloAlerta>();
	
	public AlarmTableModel(AlarmaDAO alarma) {
		this.alarma=alarma;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ModeloAlerta preguntado= datos.get(rowIndex);
		switch(columnIndex){
		case 0:return preguntado.getNombre_prop();
		case 1:return preguntado.getTelefono();
		case 2:return preguntado.getEmail();
		case 3:return preguntado.getMarca();
		case 4:return preguntado.getModelo();
		case 5:return preguntado.getNombre_alarma();
		case 6:return preguntado.getFecha();
		case 7:return preguntado.getKm();
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "Propietario";
		case 1:return "Telefono";
		case 2:return "Email";
		case 3:return "Marca";
		case 4:return "Modelo";
		case 5:return "Alarma";
		case 6:return "Fecha Exp.";
		case 7:return "Km Exp.";
		default:return "[Desconocido]";
		}
	}
	
	public void updateModel() throws DAOException{
		datos=alarma.obtenerAlertas();
	}
}
