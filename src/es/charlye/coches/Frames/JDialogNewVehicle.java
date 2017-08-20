package es.charlye.coches.Frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Propietario;
import es.charlye.coches.Modelo.Usuario;
import es.charlye.coches.Modelo.Vehiculo;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JYearChooser;

public class JDialogNewVehicle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static Propietario propietario=null;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtMotor;
	private JTextField txtChasis;
	private JTextField txtPropietario;

	/**
	 * Create the dialog.
	 */
	public JDialogNewVehicle(DAOManager manager,Usuario usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogNewVehicle.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Crear Vehículo");
		setBounds(100, 100, 327, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblMarca = new JLabel("Marca:");
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		
		JLabel lblAnho = new JLabel("Año:");
		lblAnho.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblMotor = new JLabel("Motor:");
		lblMotor.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblChasis = new JLabel("Chasis:");
		lblChasis.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblPropietario = new JLabel("Propietario:");
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		
		txtMotor = new JTextField();
		txtMotor.setColumns(10);
		
		txtChasis = new JTextField();
		txtChasis.setColumns(10);
		
		txtPropietario = new JTextField();
		txtPropietario.setToolTipText("Escribe el nombre o parte de el y pulsa enter para buscarlo. ");
		txtPropietario.setColumns(10);

		
		JYearChooser yearChooser = new JYearChooser();
		
		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = txtPropietario.getInputMap(condition);
		ActionMap aMap = txtPropietario.getActionMap();
		
		String enter = "enter";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		aMap.put(enter, new AbstractAction() {
			private static final long serialVersionUID = 3378381940748924908L;
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					JDialogPropietarios dialog = new JDialogPropietarios(manager,txtPropietario.getText(),3,usuario);	// 1 significa que al dar OK en vehículos rellena los datos del main.
					dialog.setVisible(true);
					txtPropietario.setText(propietario.getNombre());
				} catch(Exception e) {
					e.printStackTrace();
				}
		    }
		});
		
		JButton btnCrearVehiculo = new JButton("Crear Vehículo");
		btnCrearVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(propietario!=null && !txtMarca.getText().equals("") && !txtModelo.getText().equals("") && !txtMotor.getText().equals("") && txtChasis.getText().length()==17){
						manager.getVehiculoDAO().insertar(
								new Vehiculo(
									txtMarca.getText(),
									txtModelo.getText(),
									yearChooser.getYear(),
									txtMotor.getText(),
									txtChasis.getText(),
									propietario.getId_prop()
								));
						JOptionPane.showMessageDialog(btnCrearVehiculo, "Vehículo insertado correctamente.", "Vehículo insertado",1);
						dispose();
					} else{
						JOptionPane.showMessageDialog(btnCrearVehiculo, "Algun campo esta vacío, propietario no confirmado o numero de chasis incorrecto", "Error",1);
					}
				}catch (NumberFormatException | DAOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblAnho, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblModelo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(yearChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtModelo, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblMotor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtMotor, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChasis, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPropietario))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPropietario, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtChasis, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnCrearVehiculo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModelo)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAnho)
						.addComponent(yearChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMotor)
						.addComponent(txtMotor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChasis)
						.addComponent(txtChasis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPropietario)
						.addComponent(txtPropietario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCrearVehiculo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static Propietario getPropietario() {
		return propietario;
	}

	public static void setPropietario(Propietario propietario) {
		JDialogNewVehicle.propietario = propietario;
	}
}
