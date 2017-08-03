package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.AveriaDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Averia;

public class AveriaTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private AveriaDAO averia;
	private List<Averia> datos=new ArrayList<Averia>();
	
	public AveriaTableModel(AveriaDAO averia) {
		this.averia=averia;
	}

	@Override
	public int getColumnCount() {
		return 7;
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
		case 1:return preguntado.getFecha_averia();
		case 2:return preguntado.getComen_averia();
		case 3:return preguntado.getKm();
		case 4:return preguntado.getPrecio_repuesto();
		case 5:return preguntado.getPrecio_cobrado();
		case 6:return (preguntado.getPrecio_cobrado()-preguntado.getPrecio_repuesto());
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "ID_Averia";
		case 1:return "Fecha Averia";
		case 2:return "Comentario";
		case 3:return "Km actuales";
		case 4:return "Precio Repuesto";
		case 5:return "Precio Cobrado";
		case 6:return "Beneficio";
		default:return "[Desconocido]";
		}
	}
	
	public void updateModel() throws DAOException{
		datos=averia.obtenerTodos();
	}
	
	public void updateMonth(String fecha) throws DAOException{
		datos=averia.obtenerPorFecha(fecha);
	}
	
	public void updateVehi(Long id_vehi) throws DAOException{
		datos=averia.obtenerGrupo(id_vehi);
	}
}