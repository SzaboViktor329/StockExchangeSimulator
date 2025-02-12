package com.registscreen;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.frame.Frame;
import com.user.User;
import com.usercollection.UserCollection;

public class Registscreen extends JPanel{
	private static final long serialVersionUID = 1L;
	Frame frame;
	UserCollection coll;
	
	JPanel panel1 = new JPanel();
	JLabel usernL = new JLabel("Felhasználónév:");
	JLabel passwL = new JLabel("Jelszó:");
	JLabel cashL = new JLabel("Pénz (Ft-ban):");
	JTextField usernTF = new JTextField();
	JPasswordField passwTF = new JPasswordField();
	JTextField cashTF = new JTextField();
	JLabel registL = new JLabel("Regisztráció");
	JButton backB = new JButton("Vissza");
	JButton registB = new JButton("Regisztrál");
	
	RegistBtnActionListener regbtnlstr;
	BackBtnActionListener backbtnlstr;
	
	public Registscreen(UserCollection c, Frame f) {
		frame=f;
		coll=c;
		this.setLayout(null);
		initialize();
		regbtnlstr = new RegistBtnActionListener();
		backbtnlstr = new BackBtnActionListener();
	}
	
	public void initialize() {
		registL.setBounds(0, 0, 110, 30);
		registL.setFont(new Font("Serif", Font.PLAIN, 21));
		
		registL.setHorizontalAlignment(SwingConstants.CENTER);
		registL.setVerticalAlignment(SwingConstants.CENTER);
		
		this.add(registL);
		
		panel1.setBounds(40, 60, 310, 85);
		add(panel1);
		panel1.setLayout(null);
		
		usernL.setHorizontalAlignment(SwingConstants.RIGHT);
		usernL.setBounds(10, 10, 90, 15);
		panel1.add(usernL);
		
		passwL.setHorizontalAlignment(SwingConstants.RIGHT);
		passwL.setBounds(10, 35, 90, 15);
		panel1.add(passwL);
		
		cashL.setHorizontalAlignment(SwingConstants.RIGHT);
		cashL.setBounds(10, 60, 90, 15);
		panel1.add(cashL);
		
		usernTF.setBounds(110, 7, 190, 20);
		panel1.add(usernTF);
		
		passwTF.setBounds(110, 33, 190, 20);
		panel1.add(passwTF);
		
		cashTF.setBounds(110, 59, 190, 20);
		panel1.add(cashTF);
		
		
		registB.setBounds(135, 190, 120, 25);
		this.add(registB);
		
		backB.setBounds(10, 265, 90, 25);
		this.add(backB);	
	}
	
	public class RegistBtnActionListener implements ActionListener{
		public RegistBtnActionListener() {
			registB.addActionListener(this);
		}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) {
			String username = usernTF.getText();
			String password = passwTF.getText();
			try {
				int cash = Integer.parseInt(cashTF.getText());
				User u = new User(username,password,cash);
				if(!coll.addUser(u)) {
					JOptionPane.showMessageDialog(frame,"A felhasználó már létezik!","Hiba",JOptionPane.ERROR_MESSAGE);
				}
				coll.save();
				JOptionPane.showMessageDialog(frame,"A felhasználó sikersen regisztrálva!","Siker",JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame,"A pénz mezõ értéke hibás!","Hiba",JOptionPane.ERROR_MESSAGE);  
			}
		}
	}
	
	public class BackBtnActionListener implements ActionListener{
		public BackBtnActionListener() {
			backB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			frame.remove(frame.getRegistration());
			frame.validate();
			frame.repaint();
			frame.initializeLoginScreen();
		}
	}
}
