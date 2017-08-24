package es.charlye.coches.Frames;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.TableModel.UsuariosTableModel;

public class JDialogUsuarios extends JDialog {

	private static final long serialVersionUID = 357221784089674786L;
	private JTable table;
	private UsuariosTableModel model;

	/**
	 * Create the dialog.
	 * @throws DAOException 
	 */
	public JDialogUsuarios(DAOManager manager) throws DAOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAutenticator.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Usuarios");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 202, 288);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model=new UsuariosTableModel(manager.getUsuarioDAO());	
		model.updateModel();
		table.setModel(model);
		getContentPane().setLayout(groupLayout);
	    setLocationRelativeTo(null);
	}
}
