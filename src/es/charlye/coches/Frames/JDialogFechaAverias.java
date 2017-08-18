package es.charlye.coches.Frames;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;
import es.charlye.coches.TableModel.AveriaTableModel;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class JDialogFechaAverias extends JDialog {

	private static final long serialVersionUID = 894920850365412698L;
	private JTable table;
	private AveriaTableModel model;
	private JPanel buttonPane;

	/**
	 * Create the dialog.
	 */
	public JDialogFechaAverias(DAOManager manager,String fecha,Usuario usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAutenticator.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Reparaciones Mensuales");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);
		model=new AveriaTableModel(manager.getAveriaDAO(),usuario);	
		try {
			model.updateMonth(fecha);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		table.setModel(model);
		
		setBounds(100, 100, 836, 514);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblReparacionesSeleccionadas = new JLabel("Reparaciones: "+table.getRowCount()+"      ");
		buttonPane.add(lblReparacionesSeleccionadas);

		double sumarepuestos=0;
		double sumacobrado=0;
		double sumatotal=0;
		for(int i=0;i<table.getRowCount();i++){
			sumarepuestos+=new Double (table.getModel().getValueAt(i, 4).toString());
			sumacobrado+=new Double (table.getModel().getValueAt(i, 5).toString());
			sumatotal+=new Double (table.getModel().getValueAt(i, 6).toString());
		}
		
		JLabel lblTotalRepuesto = new JLabel("Total Repuesto:"+sumarepuestos+"€     ");
		buttonPane.add(lblTotalRepuesto);
		
		JLabel lblTotalCobrado = new JLabel("Total Cobrado:"+sumacobrado+"€     ");
		buttonPane.add(lblTotalCobrado);
		
		JLabel lblBeneficioTotal = new JLabel("Beneficio total:"+sumatotal+"€    ");
		buttonPane.add(lblBeneficioTotal);

		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = table.getInputMap(condition);
		ActionMap aMap = table.getActionMap();
		
		String enter = "enter";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), enter);
		aMap.put(enter, new AbstractAction() {
			private static final long serialVersionUID = 3378381940748924908L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		getContentPane().setLayout(groupLayout);
	    setLocationRelativeTo(null);
	    setModal(true);
	}
}
