/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dauphine.ihm;

import java.io.File;

import dauphine.parser.InputHandler;
import dauphine.parser.OutputHandler;
import dauphine.stock.Fond;

/**
 *
 * @author Group
 */

public class HomeWindows extends javax.swing.JFrame {

	private javax.swing.JButton Open1jButton;
	private javax.swing.JLabel jLabel1Default;
	private javax.swing.JLabel jLabelInput;
	private javax.swing.JLabel jLabelOutput;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextFieldDefault;
	private javax.swing.JTextField jTextFieldMainText;
	private javax.swing.JButton open2jButton;
	private javax.swing.JButton runjButton;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1556840043009322215L;

	/**
	 * Creates new form homeWindows
	 */
	public HomeWindows(){ 
	this.setTitle("BI Project - M2 IF App 2016 - PADONOU & HERWAL & KODJO");
		initComponents();
	}

	private void initComponents() {

		jTextFieldMainText = new javax.swing.JTextField();
		jLabelInput = new javax.swing.JLabel();
		jLabelOutput = new javax.swing.JLabel();
		runjButton = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		Open1jButton = new javax.swing.JButton();
		open2jButton = new javax.swing.JButton();
		jLabel1Default = new javax.swing.JLabel();
		jTextFieldDefault = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTextFieldMainText.setEditable(false);
		jTextFieldMainText.setFont(new java.awt.Font("Lucida Handwriting", 1, 13)); // NOI18N
		jTextFieldMainText.setText("Projet BI");

		jLabelInput.setText("Input Path :");

		jLabelOutput.setText("Output Folder :");

		runjButton.setText("Run");
		runjButton.setEnabled(false);
		runjButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (chooserOutputXml == null) {
					chooserOutputXml = new JDirectoryChooserOutputXml();
					chooserOutputXml.setPath(jTextFieldDefault.getText());
				}
				Fond fond = InputHandler.getData(chooserInputXml.getPath());
				fond.initData();
				new OutputHandler(fond, chooserOutputXml.getPath());
			}
		});

		jTextField1.setText("University of Paris Dauphine");

		Open1jButton.setText("Open");
		Open1jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooserInputXml = new JFileChooserInputXml();
				if (!(chooserInputXml.getPath() == null))
					runjButton.setEnabled(true);
			}
		});

		open2jButton.setText("Select");
		open2jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooserOutputXml = new JDirectoryChooserOutputXml();
			}
		});
		jLabel1Default.setText("Default output path :");

		jTextFieldDefault.setEditable(false);
		jTextFieldDefault.setFont(new java.awt.Font("Lucida Handwriting", 1, 13)); // NOI18N
		jTextFieldDefault.setText(System.getProperty("user.dir") + File.separator);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(111, 111, 111))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(137, 137, 137).addComponent(
												jTextFieldMainText,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup().addGap(90, 90, 90).addComponent(
														runjButton,
														javax.swing.GroupLayout.PREFERRED_SIZE, 221,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(44, 44, 44)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(jLabelOutput).addComponent(jLabelInput)))
												.addGroup(layout.createSequentialGroup().addContainerGap()
														.addComponent(jLabel1Default)))
												.addGap(28, 28, 28)
												.addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jTextFieldDefault,
																javax.swing.GroupLayout.PREFERRED_SIZE, 206,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(Open1jButton)
																.addComponent(open2jButton)))))
								.addContainerGap(27, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(15, 15, 15)
						.addComponent(jTextFieldMainText, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(21, 21, 21)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabelInput).addComponent(Open1jButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabelOutput).addComponent(open2jButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1Default).addComponent(jTextFieldDefault,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(7, 7, 7).addComponent(runjButton).addGap(18, 18, 18)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)));

		pack();
	}

	private JDirectoryChooserOutputXml chooserOutputXml;
	private JFileChooserInputXml chooserInputXml;

	public JDirectoryChooserOutputXml getChooserOutputXml() {
		return chooserOutputXml;
	}

	public void setChooserOutputXml(JDirectoryChooserOutputXml chooserOutputXml) {
		this.chooserOutputXml = chooserOutputXml;
	}

	public JFileChooserInputXml getChooserInputXml() {
		return chooserInputXml;
	}

	public void setChooserInputXml(JFileChooserInputXml chooserInputXml) {
		this.chooserInputXml = chooserInputXml;
	}
}
