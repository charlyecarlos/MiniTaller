package es.charlye.coches.Frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import es.charlye.coches.DAO.DAOManager;
import es.charlye.coches.Exception.DAOException;
import es.charlye.coches.Modelo.Tipo_Alarma;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class JDialogNewAlarmType extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreAlarma;

	/**
	 * Create the dialog.
	 */
	public JDialogNewAlarmType(DAOManager manager) {
		setTitle("Nuevo Tipo de Alarma");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogNewAlarmType.class.getResource("/es/charlye/coches/Resources/ico_taller1.png")));
		setBounds(100, 100, 358, 159);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNombreAlarma = new JLabel("Nombre Alarma:");
		
		txtNombreAlarma = new JTextField();
		txtNombreAlarma.setColumns(10);
		txtNombreAlarma.setText("");
		JLabel lblFechaExpiracion = new JLabel("Fecha Expiración:");
		
		JFormattedTextField frmtdtxtfldAnho = new JFormattedTextField();
		frmtdtxtfldAnho.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldAnho.setValue(new Integer(0));
		frmtdtxtfldAnho.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if((int)frmtdtxtfldAnho.getValue()>15 || (int)frmtdtxtfldAnho.getValue()<0){
					frmtdtxtfldAnho.setForeground(Color.RED);
				}else
					frmtdtxtfldAnho.setForeground(Color.GREEN);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldAnho.setForeground(Color.BLACK);
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
						frmtdtxtfldAnho.selectAll();
		            }
		        });
			}
		});
		
		JFormattedTextField frmtdtxtfldMes = new JFormattedTextField();
		frmtdtxtfldMes.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldMes.setValue(new Integer(0));
		frmtdtxtfldMes.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if((int)frmtdtxtfldMes.getValue()>50 || (int)frmtdtxtfldMes.getValue()<0){
					frmtdtxtfldMes.setForeground(Color.RED);
				}else
					frmtdtxtfldMes.setForeground(Color.GREEN);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldMes.setForeground(Color.BLACK);
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
						frmtdtxtfldMes.selectAll();
		            }
		        });
			}
		});
		
		
		
		JLabel lblAo = new JLabel("Año:");
		
		JLabel lblMes = new JLabel("Mes:");
		
		JLabel lblKmExpiracin = new JLabel("Km Expiración:");
		
		JFormattedTextField frmtdtxtfldKmexp = new JFormattedTextField();
		frmtdtxtfldKmexp.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldKmexp.setValue(new Integer(0));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblNombreAlarma)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtNombreAlarma))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblFechaExpiracion)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblAo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(frmtdtxtfldAnho, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(lblMes)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(frmtdtxtfldMes, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblKmExpiracin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(frmtdtxtfldKmexp, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreAlarma)
						.addComponent(txtNombreAlarma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAo)
						.addComponent(lblFechaExpiracion)
						.addComponent(frmtdtxtfldAnho, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMes)
						.addComponent(frmtdtxtfldMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKmExpiracin)
						.addComponent(frmtdtxtfldKmexp, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtNombreAlarma.getText().equals("")){
							Integer meses_exp=null;
							Integer km_exp=null;
							if((int)frmtdtxtfldAnho.getValue()!=0 || (int)frmtdtxtfldMes.getValue()!=0)
								meses_exp=(((int)frmtdtxtfldAnho.getValue())*12)+(int)frmtdtxtfldMes.getValue();
							try{
								if(Integer.parseInt(frmtdtxtfldKmexp.getText())!=0)
									km_exp=Integer.parseInt(frmtdtxtfldKmexp.getText());									
							}catch(Exception arg0){
								if((Integer)frmtdtxtfldKmexp.getValue()!=0)
									km_exp=(Integer) frmtdtxtfldKmexp.getValue();
							}
							if(meses_exp!=null || km_exp!=null)
								try {
									if(!manager.getTipo_AlarmaDAO().existe(txtNombreAlarma.getText())){
										manager.getTipo_AlarmaDAO().insertar(new Tipo_Alarma(txtNombreAlarma.getText().toUpperCase(),meses_exp,km_exp));
										JOptionPane.showMessageDialog(txtNombreAlarma, "El tipo de alarma "+txtNombreAlarma.getText().toUpperCase()+" a sido añadida.", "Tipo de alarma creado",1);
										dispose();										
									}else
										JOptionPane.showMessageDialog(txtNombreAlarma, "El tipo de alarma ya existe.", "Tipo de alarma duplicada",1);
								} catch (DAOException e1) {
									e1.printStackTrace();
								}
							else
								JOptionPane.showMessageDialog(txtNombreAlarma, "La alarma necesita saltar por tiempo o por kilometros, sino no puede saltar.", "Error",1);
						}else
							JOptionPane.showMessageDialog(txtNombreAlarma, "Nombre vacio", "Error",1);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				

				int condition = JComponent.WHEN_FOCUSED;
				InputMap iMap = frmtdtxtfldKmexp.getInputMap(condition);
				ActionMap aMap = frmtdtxtfldKmexp.getActionMap();

				String enter = "enter";
				iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
				aMap.put(enter, new AbstractAction() {
					private static final long serialVersionUID = 3232103694966329326L;

					@Override
				     public void actionPerformed(ActionEvent arg0) {
				    	 okButton.doClick();
				     }
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		}
		setLocationRelativeTo(null);
		setModal(true);
	}
}
