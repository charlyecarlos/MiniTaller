package es.charlye.coches.Frames;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Encrypt.EncryptMD5;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Usuario;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class JDialogNewUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8794986226530275189L;
	private final JPanel contentPanel = new JPanel();
	private JTextField lblUser;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirm;
	private JButton btnCrearNuevoUsuario;
	private int priv=1;

	/**
	 * Create the dialog.
	 */
	public JDialogNewUser(DAOManager manager) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogNewUser.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Crear nuevo usuario");
		setBounds(100, 100, 350, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNombreUsuario = new JLabel("Nombre usuario:");
		
		lblUser = new JTextField();
		lblUser.setColumns(10);
		
		JLabel lblContrasenha = new JLabel("Contraseña:");
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("");
		
		JLabel lblConfirm = new JLabel("Repita contraseña:");
		
		pwdConfirm = new JPasswordField();
		pwdConfirm.setText("");
		
		JComboBox<String> cbPrivilegios = new JComboBox<String>();
		cbPrivilegios.addItem("Limitado");
		cbPrivilegios.addItem("Normal");
		cbPrivilegios.addItem("Administrador");
		
		btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=new String(pwdPassword.getPassword());
				String confirm=new String(pwdConfirm.getPassword());
				if(password.equals(confirm)){
					try {
						if(cbPrivilegios.getSelectedItem().equals("Normal"))
							priv=2;
						else if(cbPrivilegios.getSelectedItem().equals("Administrador"))
							priv=3;
						password=EncryptMD5.encryptMD5(password);
						manager.getUsuarioDAO().insertar(new Usuario(lblUser.getText(), password,priv));
						JOptionPane.showMessageDialog(btnCrearNuevoUsuario ,"El usuario '"+lblUser.getText()+"' ha sido creado correctamente.");
						dispose();
					} catch (DAOException e1) {
						JOptionPane.showMessageDialog(btnCrearNuevoUsuario ,"El usuario ya existe.");
					}
				}else
					JOptionPane.showMessageDialog(btnCrearNuevoUsuario ,"La contraseña no coincide.");
			}
		});
		
		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = pwdConfirm.getInputMap(condition);
		ActionMap aMap = pwdConfirm.getActionMap();

		String enter = "enter";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		aMap.put(enter, new AbstractAction() {
			private static final long serialVersionUID = 3232103694966329326L;

			@Override
		     public void actionPerformed(ActionEvent arg0) {
		    	 btnCrearNuevoUsuario.doClick();
		     }
		});
		
		JLabel lblPrivilegio = new JLabel("Pemisos:");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNombreUsuario)
									.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
									.addComponent(lblUser, 180, 180, 180))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblContrasenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConfirm))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(pwdConfirm, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
										.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(btnCrearNuevoUsuario, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(75))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPrivilegio)
							.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
							.addComponent(cbPrivilegios, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreUsuario)
						.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasenha)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirm)
						.addComponent(pwdConfirm, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrivilegio)
						.addComponent(cbPrivilegios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(btnCrearNuevoUsuario, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(null);
		setModal(true);
	}
}
