package es.charlye.coches.Frames;


import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.DAO.MariaDB.MariaDBDAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.TableModel.VehiculosTableModel;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JDialogVehiculos extends JDialog {

	private static final long serialVersionUID = 5199292522137345295L;
	private JPanel buttonPane;
	private JTable table;
	private VehiculosTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DAOManager manager=new MariaDBDAOManager("localhost","coches", "coches", "Coches");
			JDialogVehiculos dialog=new JDialogVehiculos(manager,new Long(1));
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws DAOException 
	 */
	public JDialogVehiculos(DAOManager manager,Long id) throws DAOException {
		setTitle("Vehiculos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogVehiculos.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
				
		setBounds(100, 100, 750, 397);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				
				JScrollPane scrollPane = new JScrollPane();
				GroupLayout groupLayout = new GroupLayout(getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 726, GroupLayout.PREFERRED_SIZE))
								.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 717, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(1611, Short.MAX_VALUE))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(112))
				);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				model=new VehiculosTableModel(manager.getVehiculoDAO());
				model.updateModel(id);
				table.setModel(model);
				
				JButton okButton = 
						new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int row=table.getSelectedRow();
						if(row!=-1){
							try {
								JFrameMain.setVehiculo(manager.getVehiculoDAO().obtener(new Long(table.getModel().getValueAt(row, 0).toString())));
							} catch (NumberFormatException | DAOException e) {
								e.printStackTrace();
							}
							dispose();
						}else
							JOptionPane.showMessageDialog(okButton ,"No se ha seleccionado ningun cliente.");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				getContentPane().setLayout(groupLayout);
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

		
	    setLocationRelativeTo(null);
	    setModal(true);
	}
}
