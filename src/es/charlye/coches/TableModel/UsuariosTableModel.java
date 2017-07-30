package es.charlye.coches.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.charlye.coches.DAO.UsuarioDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;

public class UsuariosTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 8311701494023368091L;

	private UsuarioDAO usuario;
	private List<Usuario> datos=new ArrayList<Usuario>();
	
	public UsuariosTableModel(UsuarioDAO usuario) {
		this.usuario = usuario;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario preguntado= datos.get(rowIndex);
		switch(columnIndex){
		case 0:return preguntado.getUser();
		default: return "";
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0:return "Nombre";
		default:return "[Desconocido]";
		}
	}
	
	public void updateModel() throws DAOException{
		datos=usuario.obtenerTodos();
	}

}
