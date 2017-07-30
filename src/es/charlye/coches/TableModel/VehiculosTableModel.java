package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.VehiculoDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Vehiculo;

public class VehiculosTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private VehiculoDAO vehiculo;
	private List<Vehiculo> datos=new ArrayList<Vehiculo>();
	
	public VehiculosTableModel(VehiculoDAO vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public void updateModel(Long id) throws DAOException{
		datos=vehiculo.obtenerGrupo(id);
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vehiculo preguntado= datos.get(rowIndex);
		switch(columnIndex){
		case 0:return preguntado.getId_vehi();
		case 1:return preguntado.getMarca();
		case 2:return preguntado.getModelo();
		case 3:return preguntado.getAnho();
		case 4:return preguntado.getMotor();
		case 5:return preguntado.getChasis();
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "ID_Vehi";
		case 1:return "Marca";
		case 2:return "Modelo";
		case 3:return "Año";
		case 4:return "Motor";
		case 5:return "Chasis";
		default:return "[Desconocido]";
		}
	}

}
