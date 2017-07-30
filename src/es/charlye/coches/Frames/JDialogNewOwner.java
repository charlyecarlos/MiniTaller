package es.charlye.coches.Frames;

import es.charlye.coches.DAO.PropietarioDAO;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Propietario;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class JDialogNewOwner extends JDialog {

	private static final long serialVersionUID = -3563684273411668397L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Create the dialog.
	 */
	public JDialogNewOwner(PropietarioDAO propietario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogNewOwner.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Crear Cliente");
		setBounds(100, 100, 331, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblDireccion = new JLabel("Dirección:");
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					propietario.insertar(new Propietario(txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText()));
					JOptionPane.showMessageDialog(btnCrearCliente ,"¡¡Cliente creado!!.");
					dispose();
				} catch (DAOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		 int condition = JComponent.WHEN_FOCUSED;
		  InputMap iMap = txtTelefono.getInputMap(condition);
		  ActionMap aMap = txtTelefono.getActionMap();

		  String enter = "enter";
		  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		  aMap.put(enter, new AbstractAction() {

			private static final long serialVersionUID = -1096446379404108663L;

			@Override
		     public void actionPerformed(ActionEvent arg0) {
		    	 btnCrearCliente.doClick();
		     }
		  });
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCrearCliente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefono, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(lblDireccion)
								.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCrearCliente, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(null);
	}
}
