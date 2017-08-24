package es.charlye.coches.Frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.util.List;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Tipo_Alarma;

public class JDialogAlarm extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static Tipo_Alarma tipo_alarma=null;

	/**
	 * Create the dialog.
	 */
	public JDialogAlarm(DAOManager manager) {
		setTitle("Nueva Alarma");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogAlarm.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setBounds(100, 100, 393, 143);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblTipoDeAlarma = new JLabel("Tipo de alarma:");
		lblTipoDeAlarma.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		try {
			List<Tipo_Alarma> tipo_alarma=manager.getTipo_AlarmaDAO().obtenerTodos();
			for(Tipo_Alarma a:tipo_alarma)
				comboBoxTipo.addItem(a.getNombre());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblTipoDeAlarma)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(lblTipoDeAlarma))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(28)
							.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNuevoTipoDe = new JButton("Nuevo Tipo de alarma");
			btnNuevoTipoDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialogNewAlarmType dialog = new JDialogNewAlarmType(manager);
					dialog.setVisible(true);
					try {
						List<Tipo_Alarma> tipo_alarma=manager.getTipo_AlarmaDAO().obtenerTodos();
						comboBoxTipo.removeAllItems();
						for(Tipo_Alarma a:tipo_alarma)
							comboBoxTipo.addItem(a.getNombre());
					} catch (DAOException arg0) {
						arg0.printStackTrace();
					}
				}
			});
			buttonPane.add(btnNuevoTipoDe);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(comboBoxTipo.getSelectedItem()!=null)
							try {
								tipo_alarma=manager.getTipo_AlarmaDAO().obtener((String) comboBoxTipo.getSelectedItem());
								dispose();
							} catch (DAOException e1) {
								e1.printStackTrace();
							}
						else
							JOptionPane.showMessageDialog(btnNuevoTipoDe, "No se ha seleccionado ningún tipo de alarma.", "Campo Vacío",1);
							
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						tipo_alarma=null;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setModal(true);
	}

	public static Tipo_Alarma getTipo_alarma() {
		return tipo_alarma;
	}

	public static void setTipo_alarma(Tipo_Alarma tipo_alarma) {
		JDialogAlarm.tipo_alarma = tipo_alarma;
	}
}
