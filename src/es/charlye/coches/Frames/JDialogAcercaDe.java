package es.charlye.coches.Frames;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JDialogAcercaDe extends JDialog {

	private static final long serialVersionUID = 8049477898516313450L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public JDialogAcercaDe() {
			setResizable(false);
		setTitle("Acerca de");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogAcercaDe.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setBounds(100, 100, 422, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		
		JLabel lblAbout = new JLabel("Acerca De");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(new Font("Gargi", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblCreator = new JLabel("Aplicacion creada por Charlye");
		lblCreator.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreator.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblImage = new JLabel("Imagen sacada de Google sin nombrar propietario");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblFramework = new JLabel("Programado en eclipse");
		lblFramework.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFramework.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblBBDD = new JLabel("Utiliza base de datos MariaSQL");
		lblBBDD.setHorizontalAlignment(SwingConstants.CENTER);
		lblBBDD.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblLicencia = new JLabel("Este programa tiene licencia GNU v3.0");
		lblLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblLicencia.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblAbout, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addGap(115))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblCreator, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblFramework, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
								.addContainerGap()))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBBDD, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblLicencia, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblAbout, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCreator, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFramework, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBBDD)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLicencia)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(null);
	}
}
