package es.charlye.coches.Frames;

import es.charlye.coches.DAO.UsuarioDAO;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class JDialogChangePassword extends JDialog {

	private static final long serialVersionUID = 2663062374082020134L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField repeatPassword;

	/**
	 * Create the dialog.
	 */
	public JDialogChangePassword(UsuarioDAO usuario,String nombre) {
		setTitle("Cambiar Contraseña");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogChangePassword.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setBounds(100, 100, 336, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblContrasenhaActual = new JLabel("Contraseña actual:");
		
		oldPassword = new JPasswordField();
		
		JLabel lblNuevaContrasenha = new JLabel("Nueva contraseña:");
		
		newPassword = new JPasswordField();
		
		JLabel lblRepetirContrasenha = new JLabel("Repetir contraseña:");
		
		repeatPassword = new JPasswordField();
		
		JButton btnCambiarContrasea = new JButton("Cambiar Contraseña");
		btnCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					char[]pass=oldPassword.getPassword();
					String password=new String(pass);
					password=EncryptMD5.encryptMD5(password);
					if(usuario.correcto(nombre, password)){
						password=new String(newPassword.getPassword());
						String rp=new String(repeatPassword.getPassword());
						if(password.equals(rp)){
							password=EncryptMD5.encryptMD5(password);
							usuario.modificar(new Usuario(nombre, password.toLowerCase()));
							JOptionPane.showMessageDialog(newPassword ,"Contraseña cambiada.");
							dispose();
						}else
							JOptionPane.showMessageDialog(newPassword ,"Las contraseñas no coinciden.");
					}else
						JOptionPane.showMessageDialog(oldPassword ,"La contraseña es incorrecta.");
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblContrasenhaActual, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNuevaContrasenha, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(newPassword, 159, 159, 159))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRepetirContrasenha, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(repeatPassword, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(71)
							.addComponent(btnCambiarContrasea)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasenhaActual, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNuevaContrasenha, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRepetirContrasenha, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(repeatPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnCambiarContrasea)
					.addContainerGap())
		);
		
		int condition = JComponent.WHEN_FOCUSED;
		  InputMap iMap = newPassword.getInputMap(condition);
		  ActionMap aMap = newPassword.getActionMap();

		  String enter = "enter";
		  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		  aMap.put(enter, new AbstractAction() {

			private static final long serialVersionUID = -1096446379404108663L;

			@Override
		     public void actionPerformed(ActionEvent arg0) {
		    	 btnCambiarContrasea.doClick();
		     }
		  });
		  
		contentPanel.setLayout(gl_contentPanel);
	    setLocationRelativeTo(null);
	}
}
