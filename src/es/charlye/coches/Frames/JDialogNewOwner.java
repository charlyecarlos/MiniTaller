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
	private JTextField txtEmail;

	/**
	 * Create the dialog.
	 */
	public JDialogNewOwner(PropietarioDAO propietario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogNewOwner.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Crear Cliente");
		setBounds(100, 100, 345, 230);
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
					propietario.insertar(new Propietario(txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText()));
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
		
		JLabel lblEmail = new JLabel("Email:");
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCrearCliente, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDireccion, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
								.addComponent(lblNombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
								.addComponent(lblTelefono, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
								.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(btnCrearCliente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
