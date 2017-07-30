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
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class JDialogDeleteUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536862617980778597L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public JDialogDeleteUser(DAOManager manager,String usuario) {
		setTitle("Eliminar cuenta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogDeleteUser.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setBounds(100, 100, 311, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblPassword = new JLabel("Escribe tu contraseña para confirmar");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String password=EncryptMD5.encryptMD5(new String(passwordField.getPassword()));
					if(manager.getUsuarioDAO().correcto(usuario,password)){
						manager.getUsuarioDAO().eliminar(new Usuario(usuario, password));
						JOptionPane.showMessageDialog(lblPassword ,"La cuenta '"+usuario+"' a sido eliminada correctamente.");
						dispose();
					}else
						JOptionPane.showMessageDialog(lblPassword ,"La contraseña no coincide.");
				} catch (DAOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		
		 int condition = JComponent.WHEN_FOCUSED;
		  InputMap iMap = passwordField.getInputMap(condition);
		  ActionMap aMap = passwordField.getActionMap();

		  String enter = "enter";
		  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		  aMap.put(enter, new AbstractAction() {
			private static final long serialVersionUID = 3232103694966329326L;

			@Override
		     public void actionPerformed(ActionEvent arg0) {
		    	 btnAceptar.doClick();
		     }
		  });
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(40)
							.addComponent(btnAceptar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnAceptar))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(null);
	}
}
