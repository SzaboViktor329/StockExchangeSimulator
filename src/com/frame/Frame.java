package com.frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.adminscreen.Adminscreen;
import com.investorscreen.Investorscreen;
import com.loginscreen.Loginscreen;
import com.registscreen.Registscreen;
import com.sharecollection.ShareCollection;
import com.usercollection.UserCollection;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	Loginscreen loginS;
	Registscreen registrationS;
	Adminscreen adminS;
	Investorscreen investorS;
	UserCollection ucoll;
	ShareCollection scoll;
	JMenuBar jmb = new JMenuBar();
	JMenu file = new JMenu("Fájl");
	JMenuItem about = new JMenuItem("Névjegy");
	AboutLstr aboutLstr;
	
	
	public Frame(UserCollection uc, ShareCollection sc) {
		ucoll=uc;
		scoll=sc;
		setResizable(false);
		setTitle("Tõzsde");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.setJMenuBar(jmb);
		jmb.add(file);
		file.add(about);
		aboutLstr = new AboutLstr(this);
		initializeLoginScreen();
	}
	
	public Loginscreen getLogin() {
		return loginS;
	}

	public Registscreen getRegistration() {
		return registrationS;
	}

	public Adminscreen getAdminS() {
		return adminS;
	}
	
	public void initializeLoginScreen() {
		loginS= new Loginscreen(ucoll,this);
		loginS.setPreferredSize(new Dimension(390,225));
		add(loginS);
		pack();
	}
	
	public void initializeRegistScreen() {
		registrationS = new Registscreen(ucoll,this);
		registrationS.setPreferredSize(new Dimension(390,300));
		add(registrationS);
		pack();
	}
	
	public void initializeAdminScreen() {
		adminS = new Adminscreen(this,ucoll,scoll);
		adminS.setPreferredSize(new Dimension(1050,610));
		add(adminS);
		pack();
	}
	
	public void initializeInvestorScreen(int userindex) {
		investorS = new Investorscreen(this, scoll, ucoll.getUser(userindex),userindex);
		investorS.setPreferredSize(new Dimension(1050,610));
		add(investorS);
		pack();
	}
	
	public Investorscreen getInvestorS() {
		return investorS;
	}
	
	public void setInvestorS(Investorscreen investorS) {
		this.investorS = investorS;
	}
	
	public UserCollection getUcoll() {
		return ucoll;
	}
	
	public void setUcoll(UserCollection ucoll) {
		this.ucoll = ucoll;
	}
	
	public ShareCollection getScoll() {
		return scoll;
	}
	
	public void setScoll(ShareCollection scoll) {
		this.scoll = scoll;
	}
	
	public class AboutLstr implements ActionListener{ 
		Frame frame;
		public AboutLstr(Frame f) {
			about.addActionListener(this);
			frame=f;
		}
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(frame,"Készítette: Szabó Viktor","Én csináltam.",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if(e.getID() == WindowEvent.WINDOW_CLOSING) {
			if(investorS==null) {
				ucoll.save();
			}
			else {
				investorS.save();
			}
			System.out.println("Itt a vége.");	
		}
		super.processWindowEvent(e);
	}
	
	
	
	
	
	
	
	
}
