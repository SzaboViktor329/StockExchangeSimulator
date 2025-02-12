package com.adminscreen;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.frame.Frame;
import com.share.Share;
import com.share.historicaldata.HistoricalData;
import com.sharecollection.ShareCollection;
import com.user.User;
import com.usercollection.UserCollection;

public class Adminscreen extends JPanel{
	private static final long serialVersionUID = 1L;
	Frame frame;
	UserCollection ucoll;
	ShareCollection scoll;
	ShareCollection delscoll = new ShareCollection();
	
	JScrollPane uscrollPane = new JScrollPane();
	JScrollPane sscrollPane = new JScrollPane();
	JScrollPane historyscrollPane = new JScrollPane();
	JList<User> userList = new JList<User>();
	JList<Share> shareList = new JList<Share>();
	JList<HistoricalData> historyList = new JList<HistoricalData>();
	JButton userDataB= new JButton("Megtekintés");
	JButton deleteUB= new JButton("Felhasználó eltávolítása");
	JLabel usersL = new JLabel("Felhasználók:");
	JLabel userdataL = new JLabel("Felhasználó adatai:");
	JLabel sharesL = new JLabel("Részvények");
	JLabel shareaddL = new JLabel("Részvény hozzáadása:");
	
	JLabel SnameL = new JLabel("Cég neve:");
	JLabel SsymbL = new JLabel("Cég kódja:");
	JLabel SvalueL = new JLabel("Felviteli árfolyam:");
	JButton addshareB= new JButton("Hozzáadás");
	JButton removeshareB= new JButton("Eltávolítás");
	JButton save = new JButton("Mentés");
	JButton back = new JButton("Kijelentkezés");
	
	
	UserDataBLstr udataBLstr;
	DeleteUBLstr delUBLstr;
	ShareAddBtnLstr addshareBLstr;
	ShareRemoveBtnLstr shremoveBLstr;
	SelectionLstr selLstr;
	SaveBtnLstr saveLstr;
	BackBtnLstr backLstr;
	
	JTextField TF1 = new JTextField();
	JTextField TF2 = new JTextField();
	JTextField TF3 = new JTextField();
	
	JTextArea userTA = new JTextArea();
	
	DefaultListModel<User> udlm= new DefaultListModel<User>();
	DefaultListModel<Share> sdlm= new DefaultListModel<Share>();
	DefaultListModel<HistoricalData> hdlm= new DefaultListModel<HistoricalData>();
	
	
	
	public Adminscreen(Frame f, UserCollection uc, ShareCollection sc) {
		frame=f;
		ucoll=uc;
		scoll=sc;
		this.setLayout(null);
		
		setListmodell();
		
		initialize();
		
		
		udataBLstr = new UserDataBLstr();
		delUBLstr = new DeleteUBLstr();
		addshareBLstr= new ShareAddBtnLstr();
		shremoveBLstr= new ShareRemoveBtnLstr();
		selLstr= new SelectionLstr();
		saveLstr= new SaveBtnLstr();
		backLstr=new BackBtnLstr();
	}
	
	public void initialize() {
		//usersL.setOpaque(true);
		usersL.setFont(new Font("Serif", Font.PLAIN, 21));
		usersL.setBounds(10,10,200,50);
		add(usersL);
		
		deleteUB.setBounds(10,490,200,30);
		add(deleteUB);
		
		userdataL.setBounds(280,10,200,50);
		userdataL.setFont(new Font("Serif", Font.PLAIN, 20));
		add(userdataL);
		
		userTA.setBounds(280,70,200,100);
		add(userTA);
		
		uscrollPane.setBounds(10, 70, 200, 400);
		uscrollPane.setViewportView(userList);
		userList.setModel(udlm);
		add(uscrollPane);
		
		
		historyscrollPane.setBounds(840, 70, 200, 400);
		historyscrollPane.setViewportView(historyList);
		add(historyscrollPane);
		
		
		
		
		userDataB.setBounds(280, 190, 200, 20);
		add(userDataB);
		
		
		
		sharesL.setBounds(600,10,200,50);
		sharesL.setFont(new Font("Serif", Font.PLAIN, 20));
		add(sharesL);
		
		sscrollPane.setBounds(600, 70, 200, 400);
		sscrollPane.setViewportView(shareList);
		shareList.setModel(sdlm);
		add(sscrollPane);
		
		
		shareaddL.setBounds(280,220,200,50);
		shareaddL.setFont(new Font("Serif", Font.PLAIN, 20));
		add(shareaddL);
		
		
		SnameL.setBounds(280,280,200,20);
		add(SnameL);
		
		
		SsymbL.setBounds(280,320,200,20);
		add(SsymbL);
		
		
		SvalueL.setBounds(280,360,200,20);
		add(SvalueL);
		
		
		TF1.setBounds(280, 300, 200, 20);
		TF2.setBounds(280, 340, 200, 20);
		TF3.setBounds(280, 380, 200, 20);
		add(TF1);
		add(TF2);
		add(TF3);
		
		addshareB.setBounds(280, 420, 200, 20);
		add(addshareB);
		
		removeshareB.setBounds(280, 460, 200, 20);
		add(removeshareB);
		
		save.setBounds(475, 530, 100, 50);
		save.setFont(new Font("Serif", Font.BOLD, 20));
		add(save);
		
		back.setBounds(10, 570, 120, 30);
		add(back);
	}
	
	
	public class DeleteUBLstr implements ActionListener{
		public DeleteUBLstr() {
			deleteUB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			int response = JOptionPane.showConfirmDialog(frame, "Biztosan törölni akarod "+userList.getSelectedValue().getUsername()+" nevû felhasználót?","Felhasználó törlése",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response==JOptionPane.YES_OPTION) {
				ucoll.getUsercollection().remove(userList.getSelectedIndex());
				udlm.removeElement(userList.getSelectedValue());
			}
		}
		
	}
	
	public class UserDataBLstr implements ActionListener{
		public UserDataBLstr() {
			userDataB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				User u = userList.getSelectedValue();
				userTA.setText("Felhasználónév: " + u.getUsername()+ "\nJelszó: "+u.getPassword()+"\nPénzmennyiség: "+ u.getCashavailable());
			} catch (Exception e) {
				userTA.setText("");
			}
		}
	}
	
	public class ShareAddBtnLstr implements ActionListener{
		public ShareAddBtnLstr() {
			addshareB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			String companyname = TF1.getText();
			String symbol = TF2.getText();	
			Share s= null;
			boolean exist=false;
			try {
				double value = Double.parseDouble(TF3.getText());
				s = new Share(companyname,symbol,value);
				for(int i =0;i<sdlm.getSize();i++) {
					exist=sdlm.get(i).shareExist(s);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame,"A pénz mezõ értéke hibás!","Hiba",JOptionPane.ERROR_MESSAGE);  
			}
			if(!exist && s!=null) {
				sdlm.addElement(s);
			}
			else {
				JOptionPane.showMessageDialog(frame,"A részvény már szerepel a tõzsdén, vagy nem adott meg adatot.","Hiba",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class ShareRemoveBtnLstr implements ActionListener{
		public ShareRemoveBtnLstr() {
			removeshareB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			delscoll.addShare(shareList.getSelectedValue());
			sdlm.removeElement(shareList.getSelectedValue());
		}
	}
	
	public class SelectionLstr implements ListSelectionListener{
		public SelectionLstr() {
			shareList.getSelectionModel().addListSelectionListener(this);
		}
		public void valueChanged(ListSelectionEvent e) {
			
			try {
				hdlm.removeAllElements();
				for(HistoricalData i : shareList.getSelectedValue().getHistory()) {
					hdlm.addElement(i);
				}
				historyList.setModel(hdlm);
			} catch (Exception e1) {
				System.out.println("Részvény eltávolítva a tõzsdérõl.");
			}
		}	
	}
	
	public class SaveBtnLstr implements ActionListener{
		public SaveBtnLstr() {
			save.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			UserCollection uc = new UserCollection();
			ShareCollection sc= new ShareCollection();
			for(int i =0;i<udlm.getSize();i++) {
				uc.addUser(udlm.get(i));
			}
			for(int i =0;i<sdlm.getSize();i++) {
				sc.addShare(sdlm.get(i));
			}
			
			for(Share s : delscoll.getSharecollection()) {
				for(User u : ucoll.getUsercollection()) {
					for(int i =0;i<u.getPositions().size();i++) {
						if(u.getPositions().get(i).getShare().getCompanyname().equals(s.getCompanyname())) {
							u.closePosition(u.getPositions().get(i), s);
							u.getPositions().remove(i);
						}
					}
				}
			}
			
			frame.setUcoll(ucoll);
			frame.setScoll(sc);
			frame.getUcoll().pritAllStuff();
			frame.getScoll().pritAllStuff();
			frame.getScoll().save();
			frame.getUcoll().save();
		}
	}
	
	public class BackBtnLstr implements ActionListener{
		public BackBtnLstr() {
			back.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			frame.remove(frame.getAdminS());
			frame.validate();
			frame.repaint();
			frame.initializeLoginScreen();
		}
	}
	
	
	
	
	public void setListmodell() {
		for(User i : ucoll.getUsercollection()) {
			udlm.addElement(i);
		}
		for(Share i : scoll.getSharecollection()) {
			sdlm.addElement(i);
		}
	}
	
	
	
	
	
	
}
