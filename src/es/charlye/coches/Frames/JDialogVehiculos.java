package es.charlye.coches.Frames;


import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;
import es.charlye.coches.TableModel.VehiculosTableModel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JDialogVehiculos extends JDialog {

	private static final long serialVersionUID = 5199292522137345295L;
	private JPanel buttonPane;
	private JTable table;
	private VehiculosTableModel model;

	/**
	 * Create the dialog.
	 * @throws DAOException 
	 */
	public JDialogVehiculos(DAOManager manager,Long id,int btn,Usuario usuario) throws DAOException {
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
							if(btn==0)
								try {
									JFrameMain.setVehiculo(manager.getVehiculoDAO().obtener(new Long(table.getModel().getValueAt(row, 0).toString())));
								} catch (NumberFormatException | DAOException e) {
									e.printStackTrace();
								}
							else{
								JDialogAverias dialog = new JDialogAverias(manager,new Long(table.getModel().getValueAt(row, 0).toString()),usuario);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							}
							dispose();
						}else
							JOptionPane.showMessageDialog(okButton ,"No se ha seleccionado ningun cliente.");
					}
				});
				
				JButton btnNuevoVehiculo = new JButton("Nuevo Vehículo");
				btnNuevoVehiculo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							JDialogNewVehicle.setPropietario(manager.getPropietarioDAO().obtener(id));
							JDialogNewVehicle dialog = new JDialogNewVehicle(manager,usuario);
							dialog.setVisible(true);
							model.updateModel(id);
							table.setModel(model);
						}catch(DAOException arg0){
							arg0.printStackTrace();
						}
					}
				});
				buttonPane.add(btnNuevoVehiculo);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				getContentPane().setLayout(groupLayout);
				
				int condition = JComponent.WHEN_FOCUSED;
				  InputMap iMap = table.getInputMap(condition);
				  ActionMap aMap = table.getActionMap();

				  String enter = "enter";
				  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
				  aMap.put(enter, new AbstractAction() {

					private static final long serialVersionUID = -1096446379404108663L;

					@Override
				     public void actionPerformed(ActionEvent arg0) {
				    	 okButton.doClick();
				     }
				  });
				  
				  JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				  
				  String esc="escape";
				  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), esc);
				  aMap.put(esc, new AbstractAction() {
					private static final long serialVersionUID = 2534847686793735315L;

					@Override
					public void actionPerformed(ActionEvent e) {
						cancelButton.doClick();
					}
				});
			}
		}
		
	    setLocationRelativeTo(null);
	    setModal(true);
	}
}
