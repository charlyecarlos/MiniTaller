package es.charlye.coches.Frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.DAO.UsuarioDAO;
import es.charlye.coches.DAO.MariaDB.MariaDBDAOManager;
import es.charlye.coches.Encrypt.EncryptMD5;
import es.charlye.coches.Exception.DAOException;

public class JFrameAutenticator extends JFrame {

	private static final long serialVersionUID = -2673452622177109864L;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private UsuarioDAO usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DAOManager manager=new MariaDBDAOManager("localhost","coches", "coches", "Coches");
					JFrameAutenticator frame = new JFrameAutenticator(manager);
				    frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameAutenticator(DAOManager manager) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAutenticator.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setTitle("Autentificar");
		
		usuario=manager.getUsuarioDAO();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContrasenha = new JLabel("Contraseña:");
		lblContrasenha.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblContrasenha.setHorizontalAlignment(SwingConstants.CENTER);
		
		userField = new JTextField();
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					char[]pass=passwordField.getPassword();
					String password=new String(pass);
					password=EncryptMD5.encryptMD5(password);
					if(usuario.correcto(userField.getText().toLowerCase(), password)){
						JOptionPane.showMessageDialog(btnEntrar ,"Correcto.");
						JFrameMain frame = new JFrameMain(manager,userField.getText().toLowerCase());
						frame.setVisible(true);
						dispose();
					}else
						JOptionPane.showMessageDialog(btnEntrar ,"El usuario o la contraseña es incorrecta.");
				} catch (DAOException e) {
					e.printStackTrace();
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
		    	 btnEntrar.doClick();
		     }
		  });
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContrasenha))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(96)
							.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(userField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasenha, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEntrar)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane); 
	}
}
