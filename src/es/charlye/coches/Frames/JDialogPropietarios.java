package es.charlye.coches.Frames;



import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.DAO.MariaDB.MariaDBDAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.TableModel.PropietariosTableModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogPropietarios extends JDialog {

	private static final long serialVersionUID = 7147423908291492419L;
	private JPanel buttonPane;
	private JTable table;
	private PropietariosTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DAOManager manager=new MariaDBDAOManager("localhost","coches", "coches", "Coches");
			JDialogPropietarios dialog = new JDialogPropietarios(manager,"perez");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws DAOException 
	 */
	public JDialogPropietarios(DAOManager manager,String nombre) throws DAOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAutenticator.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);
		model=new PropietariosTableModel(manager.getPropietarioDAO());	
		model.updateModel(nombre);
		table.setModel(model);
		
		setBounds(100, 100, 694, 435);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			JButton btnCrearNuevoCliente = new JButton("Crear nuevo Cliente");
			btnCrearNuevoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JDialogNewOwner dialog = new JDialogNewOwner(manager.getPropietarioDAO());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setModal(true);
						model=new PropietariosTableModel(manager.getPropietarioDAO());
						model.updateModel(nombre);
						table.setModel(model);
						dialog.setVisible(true);
					} catch (Exception arg0) {
						arg0.printStackTrace();
					}
				}
			});
			buttonPane.add(btnCrearNuevoCliente);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int row=table.getSelectedRow();
							if(row!=-1){
								Long id=new Long(table.getModel().getValueAt(row, 0).toString());
								JDialogVehiculos dialog = new JDialogVehiculos(manager,id);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
								dispose();
							}else
								JOptionPane.showMessageDialog(okButton ,"No se ha seleccionado ningun cliente.");								
						} catch (Exception arg0) {
							arg0.printStackTrace();
							JOptionPane.showMessageDialog(okButton ,"No se ha seleccionado ningun cliente.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);

		getContentPane().setLayout(groupLayout);
	    setLocationRelativeTo(null);
	    setModal(true);
	}
}
