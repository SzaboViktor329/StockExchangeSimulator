package com.loginscreen;

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

public class Loginscreen extends JPanel{
	private static final long serialVersionUID = 1L;
	Frame frame;
	UserCollection coll;
	JPanel panel1 = new JPanel();
	JLabel usernL = new JLabel("Felhasználónév:");
	JLabel passwL = new JLabel("Jelszó:");
	JTextField usernTF = new JTextField();
	JPasswordField passwTF = new JPasswordField();
	JPanel panel2 = new JPanel();
	JButton loginB = new JButton("Bejelentkezés");
	JButton registB = new JButton("Regisztráció");
	
	RegistBtnActionListener regbtnlstr;
	LoginBtnActionListener logbtnlstr;
	
	public Loginscreen(UserCollection c, Frame f) {
		frame=f;
		coll=c;
		this.setLayout(null);
		initialize();
		regbtnlstr = new RegistBtnActionListener();
		logbtnlstr = new LoginBtnActionListener();
	}
	
	public void initialize() {
		//1.panel felépítése 
		panel1.setBounds(40, 40, 310, 60);
		add(panel1);
		panel1.setLayout(null);
		
		usernL.setHorizontalAlignment(SwingConstants.RIGHT);
		usernL.setBounds(10, 10, 90, 15);
		panel1.add(usernL);
		
		passwL.setHorizontalAlignment(SwingConstants.RIGHT);
		passwL.setBounds(10, 35, 90, 15);
		panel1.add(passwL);
		
		usernTF.setBounds(110, 7, 190, 20);
		panel1.add(usernTF);
		
		passwTF.setBounds(110, 33, 190, 20);
		panel1.add(passwTF);
		
		//2.panel felépítése
		panel2.setBounds(40, 140, 310, 45);
		add(panel2);
		panel2.setLayout(null);
		
		loginB.setBounds(20, 10, 120, 25);
		panel2.add(loginB);
		
		registB.setBounds(170, 10, 120, 25);
		panel2.add(registB);
	}
	
	public class RegistBtnActionListener implements ActionListener{
		public RegistBtnActionListener() {
			registB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			frame.remove(frame.getLogin());
			frame.validate();
			frame.repaint();
			frame.initializeRegistScreen();
		}
	}
	
	public class LoginBtnActionListener implements ActionListener{
		public LoginBtnActionListener() {
			loginB.addActionListener(this);
		}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if(usernTF.getText().equals("admin")&&passwTF.getText().equals("admin")) {
				frame.remove(frame.getLogin());
				frame.validate();
				frame.repaint();
				frame.initializeAdminScreen();
			}
			else {
				String username = usernTF.getText();
				String password = passwTF.getText();
				int j = 0;
				for(User i : coll.getUsercollection()) {
					if(i.getUsername().equals(username)&&i.getPassword().equals(password)) {
						frame.remove(frame.getLogin());
						frame.validate();
						frame.repaint();
						frame.initializeInvestorScreen(j);
						return;
					}
					j++;
				}
				JOptionPane.showMessageDialog(frame,"Hibás felhasználónév, vagy jelszó!","Hiba",JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	
	
	
	
	
	
	
}
