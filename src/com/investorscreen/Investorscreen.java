package com.investorscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.exchrategen.ExchRateGen;
import com.frame.Frame;
import com.position.Position;
import com.share.Share;
import com.share.historicaldata.HistoricalData;
import com.sharecollection.ShareCollection;
import com.user.User;


public class Investorscreen extends JPanel{
	private static final long serialVersionUID = 1L;
	//Felhasználók és részvények adatai
	Frame frame;
	ShareCollection scoll;
	User user;
	int userindex;
	
	//Felhasználói elemek
	JLabel usrnameL = new JLabel();
	JLabel cashL = new JLabel();
	
	//Pozíciók
	JLabel postitionsL = new JLabel("Jelenlegi pozíciók:");
	JScrollPane posScrollPane = new JScrollPane();
	JList<Position> positionList = new JList<Position>();
	DefaultListModel<Position> posdlm= new DefaultListModel<Position>();
	
	//Pozícióadatok elemei
	JLabel posdetailsL = new JLabel("Pozíció adatai:");
	JPanel posPanel = new JPanel();
	JLabel companyL = new JLabel("Cég: ");
	JTextField companyTF = new JTextField();
	JLabel dateL = new JLabel("Dátum: ");
	JTextField dateTF = new JTextField();
	JLabel typeL = new JLabel("Típus: ");
	JTextField typeTF = new JTextField();
	JLabel quantityL = new JLabel("Mennyiség: ");
	JTextField quantityTF = new JTextField();
	JLabel startL = new JLabel("Nyitás: ");
	JTextField startTF = new JTextField();
	JLabel sumL = new JLabel("Összeg: ");
	JTextField sumTF = new JTextField();
	JButton closePosB = new JButton("Pozíció zárása");
	
	//Vásárlás elemei
	JButton buyShareB = new JButton("Pozíció nyitása");
	JPanel buyPanel = new JPanel();
	JLabel buycompanyL = new JLabel("Cég: ");
	JLabel compnameL = new JLabel();
	JLabel priceL = new JLabel("Ár: ");
	JLabel valueL = new JLabel();
	JLabel buytypeL = new JLabel("Típus: ");
	JComboBox<String> buytypeCB;
	JLabel buyquanL = new JLabel("Darab: ");
	JTextField buyquanTF = new JTextField();
	JLabel buytextL = new JLabel("Összeg: ");
	JLabel buysumL = new JLabel();
	JButton buyB = new JButton("Jóváhagy");
	JButton cancelB = new JButton("Mégse");
	
	//Részvények
	JLabel sharesL = new JLabel("Részvények:");
	JScrollPane shareScrollPane = new JScrollPane();
	JList<Share> shareList = new JList<Share>();
	DefaultListModel<Share> sdlm= new DefaultListModel<Share>();
	JScrollPane historyScrollPane = new JScrollPane();
	JList<HistoricalData> historyList = new JList<HistoricalData>();
	DefaultListModel<HistoricalData> hdlm= new DefaultListModel<HistoricalData>();
	
	//Gomb Listenerek
	PosSelectionLstr posLstr;
	ShareSelectionLstr shareLstr;
	PosOpenLstr posopenLStr;
	QuantityLstr quanLstr;
	BuyBtnLstr buybtnLstr;
	CancelBtnLstr cancelbtnLstr;
	ClosePosBtnLstr closeposbtnLstr;
	LogoutBtnLstr logoutbtnLstr;
	
	
	boolean vege = false;
	boolean selectionstop = false;
	backgroundProcess process;
	Share share= new Share();
	JButton logoutB = new JButton("Kijelentkezés");
	
	
	
	
	public Investorscreen(Frame f,ShareCollection sc, User u, int uidx) {
		frame=f;
		scoll=sc;
		user=u;
		userindex=uidx;
		this.setLayout(null);
		initialize();
		
		buyPanel.setVisible(false);
		posLstr = new PosSelectionLstr();
		shareLstr= new ShareSelectionLstr();
		process = new backgroundProcess();
		posopenLStr = new PosOpenLstr();
		quanLstr = new QuantityLstr();
		buybtnLstr = new BuyBtnLstr();
		cancelbtnLstr = new CancelBtnLstr();
		closeposbtnLstr = new ClosePosBtnLstr();
		logoutbtnLstr = new LogoutBtnLstr();
		
		process.execute();
	}
	
	public void initialize() {
		initUserInfo();
		initPositions();
		initPositionDetails();
		initBuying();
		initShares();
		
		logoutB.setBounds(51, 534, 130, 30);
		add(logoutB);
	}
	
	public void initUserInfo() {
		//Felhasználónév kiírása
		usrnameL.setFont(new Font("Serif", Font.PLAIN, 20));
		usrnameL.setBounds(10, 10, 500, 40);
		usrnameL.setText("Felhasználó: " + user.getUsername());
		add(usrnameL);
		
		//Felhasználó pénze
		cashL.setHorizontalAlignment(SwingConstants.CENTER);
		cashL.setFont(new Font("Serif", Font.PLAIN, 18));
		cashL.setBounds(690, 10, 350, 40);
		cashL.setText("Elérhetõ összeg: " + user.getCashavailable() + " Ft");
		add(cashL);
	}
	
	public void initPositions() {
		for(Position i : user.getPositions()) {
			posdlm.addElement(i);
		}
		postitionsL.setFont(new Font("Serif", Font.PLAIN, 16));
		postitionsL.setBounds(10, 65, 240, 30);
		add(postitionsL);
		
		posScrollPane.setBounds(10, 110, 240, 360);
		add(posScrollPane);
		
		posScrollPane.setViewportView(positionList);
		positionList.setModel(posdlm);
	}
	
	public void initPositionDetails() {
		posdetailsL.setFont(new Font("Serif", Font.PLAIN, 16));
		posdetailsL.setBounds(270, 65, 220, 30);
		add(posdetailsL);
		
		//Pozíció panel és feltöltése
		posPanel.setBackground(Color.GRAY);
		posPanel.setBounds(270, 110, 220, 120);
		posPanel.setLayout(null);
		add(posPanel);
		
		companyL.setBounds(0, 0, 70, 20);
		companyL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(companyL);
		companyTF.setBounds(70, 0, 150, 20);
		posPanel.add(companyTF);
		
		dateL.setBounds(0, 20, 70, 20);
		dateL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(dateL);
		dateTF.setBounds(70, 20, 150, 20);
		posPanel.add(dateTF);
		
		typeL.setBounds(0, 40, 70, 20);
		typeL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(typeL);
		typeTF.setBounds(70, 40, 70, 20);
		posPanel.add(typeTF);
		
		quantityL.setBounds(0, 60, 70, 20);
		quantityL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(quantityL);
		quantityTF.setBounds(70, 60, 70, 20);
		posPanel.add(quantityTF);
		
		startL.setBounds(0, 80, 70, 20);
		startL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(startL);
		startTF.setBounds(70, 80, 150, 20);
		posPanel.add(startTF);
		
		sumL.setBounds(0, 100, 70, 20);
		sumL.setHorizontalAlignment(SwingConstants.RIGHT);
		posPanel.add(sumL);
		sumTF.setBounds(70, 100, 150, 20);
		posPanel.add(sumTF);
		
		closePosB.setBounds(270, 230, 220, 26);
		add(closePosB);
	}
	
	public void initBuying(){
		buyShareB.setBounds(290, 268, 180, 40);
		add(buyShareB);
		
		//panel beállítása
		buyPanel.setBounds(270, 320, 220, 150);
		buyPanel.setLayout(null);
		buyPanel.setBackground(Color.GRAY);
		add(buyPanel);
		
		buycompanyL.setHorizontalAlignment(SwingConstants.RIGHT);
		buycompanyL.setBounds(0, 0, 70, 20);
		buyPanel.add(buycompanyL);
		compnameL.setBounds(70, 0, 150, 20);
		buyPanel.add(compnameL);
		
		priceL.setHorizontalAlignment(SwingConstants.RIGHT);
		priceL.setBounds(0, 20, 70, 20);
		buyPanel.add(priceL);
		valueL.setBounds(70, 20, 150, 20);
		buyPanel.add(valueL);
		
		buytypeL.setHorizontalAlignment(SwingConstants.RIGHT);
		buytypeL.setBounds(0, 40, 70, 20);
		buyPanel.add(buytypeL);
		String[] buytype = {"Vétel","Eladás"};
		buytypeCB = new JComboBox<String>(buytype);
		buytypeCB.setBounds(70, 40, 100, 20);
		buyPanel.add(buytypeCB);
		
		buyquanL.setHorizontalAlignment(SwingConstants.RIGHT);
		buyquanL.setBounds(0, 60, 70, 20);
		buyPanel.add(buyquanL);
		buyquanTF.setBounds(70, 60, 70, 20);
		buyPanel.add(buyquanTF);
		
		buytextL.setHorizontalAlignment(SwingConstants.RIGHT);
		buytextL.setBounds(0, 80, 70, 20);
		buyPanel.add(buytextL);
		buysumL.setBounds(70, 80, 150, 20);
		buyPanel.add(buysumL);
		
		buyB.setBounds(10, 115, 90, 25);
		buyPanel.add(buyB);
		
		cancelB.setBounds(120, 115, 90, 25);
		buyPanel.add(cancelB);
	}
	
	public void initShares() {
		//dlm-ek feltöltése
		for(Share i : scoll.getSharecollection()) {
			sdlm.addElement(i);
		}
		sharesL.setFont(new Font("Serif", Font.PLAIN, 16));
		sharesL.setBounds(520, 65, 240, 30);
		add(sharesL);
		
		shareScrollPane.setBounds(520, 110, 240, 360);
		add(shareScrollPane);
		
		shareScrollPane.setViewportView(shareList);
		shareList.setModel(sdlm);
		
		historyScrollPane.setBounds(770, 110, 240, 360);
		add(historyScrollPane);
		
		historyScrollPane.setViewportView(historyList);
		historyList.setModel(hdlm);
	}
	
	public class PosSelectionLstr implements ListSelectionListener{
		public PosSelectionLstr() {
			positionList.getSelectionModel().addListSelectionListener(this);
		}
		public void valueChanged(ListSelectionEvent arg0) {
			if(!selectionstop) {
				Position p= positionList.getSelectedValue();
				companyTF.setText(p.getShare().getCompanyname());
				dateTF.setText(p.getDate());
				if(p.getType()) {
					typeTF.setText("Vétel");
				}
				else {
					typeTF.setText("Eladás");
				}
				quantityTF.setText(""+p.getQuantity());
				startTF.setText(""+p.getStartposition()+ " Ft");
				sumTF.setText(""+p.getStartposition()*p.getQuantity()+ " Ft");
			}
		}
	}
	
	public class ShareSelectionLstr implements ListSelectionListener{
		public ShareSelectionLstr() {
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
				System.out.println("Részvény el lett távolítva a tõzsdérõl.");
			}
		}
	}
	
	public class backgroundProcess extends SwingWorker<Void, Double> {
		@Override
		protected Void doInBackground() throws Exception {
			ExchRateGen gen = new ExchRateGen();
			Random rand = new Random();
			while(vege!=true) {
				if(vege!=true) {
					Thread.sleep(2000);
					for(int i =0;i<sdlm.size();i++) {
						Share s = sdlm.get(i);
						double arfolyam = s.getValue();
						double rate=gen.generateRate();
						if(rand.nextBoolean()) {
							rate*=-1;
						}
						s.setValue(arfolyam + Math.round(arfolyam*rate));
						sdlm.set(i, s);
					}
				}
			}
			return null;
		}
	}
	
	public class PosOpenLstr implements ActionListener {
		public PosOpenLstr() {
			buyShareB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			try {
				share = shareList.getSelectedValue();
				compnameL.setText(share.getCompanyname());
				double value= share.getValue();
				valueL.setText(""+value);
				buyShareB.setEnabled(false);
				buyPanel.setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame,"Nincs kiválasztva részvény!","Hiba",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class QuantityLstr implements ActionListener{
		public QuantityLstr(){
			buyquanTF.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			try {
				int quan = Integer.parseInt(buyquanTF.getText());
				buysumL.setText(""+quan*Double.parseDouble(valueL.getText()));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame,"Nem egész számértéket adott meg mennyiségnek!","Hiba",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class BuyBtnLstr implements ActionListener{
		public BuyBtnLstr() {
			buyB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			boolean fajta;
			if(buytypeCB.getSelectedIndex()<1) {
				fajta=true;
			}
			else {
				fajta=false;
			}
			double price= Double.parseDouble(valueL.getText())*Integer.parseInt(buyquanTF.getText());
			if(user.getCashavailable() - (int)price>=0) {
				posdlm.addElement(new Position(share,Double.parseDouble(valueL.getText()),Integer.parseInt(buyquanTF.getText()),fajta));
				user.setCashavailable(user.getCashavailable() - (int)price);
				cashL.setText("Elérhetõ összeg: " + user.getCashavailable() + " Ft");
				buyPanel.setVisible(false);
				buyShareB.setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(frame,"Nincs elég pénz!","Hiba",JOptionPane.ERROR_MESSAGE);
			}
			if(posdlm.size()==1) {
				selectionstop=false;
				positionList.setSelectedIndex(0);
			}
		}
	}
	
	public class CancelBtnLstr implements ActionListener{
		public CancelBtnLstr() {
			cancelB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			buyPanel.setVisible(false);
			buyShareB.setEnabled(true);
		}
	}
	
	public class ClosePosBtnLstr implements ActionListener{
		public ClosePosBtnLstr() {
			closePosB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			
				try {
					if(posdlm.size()!=0) {
						Position p= positionList.getSelectedValue();
						user.closePosition(p, p.getShare());
						cashL.setText("Elérhetõ összeg: " + user.getCashavailable() + " Ft");
						selectionstop=true;
						positionList.clearSelection();
						posdlm.removeElement(p);
						selectionstop= false;
						if(posdlm.size()==0) {
							selectionstop=true;
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame,"Nincsen kiválasztva pozíció!","Hiba",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
	
	public class LogoutBtnLstr implements ActionListener{
		public LogoutBtnLstr() {
			logoutB.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			save();
			frame.remove(frame.getInvestorS());
			frame.validate();
			frame.repaint();
			frame.initializeLoginScreen();
		}
	}
	
	public void save() {
		List<Position> positions = new ArrayList<Position>();
		for(int i =0;i<posdlm.size();i++) {
			positions.add(posdlm.get(i));
		}
		user.setPositions(positions);
		frame.getUcoll().setUser(userindex, user);
		frame.getUcoll().save();
		
		ShareCollection sc= new ShareCollection();
		for(int i =0;i<sdlm.getSize();i++) {
			sdlm.get(i).addLastValue(sdlm.get(i).getValue());
			sc.addShare(sdlm.get(i));
		}
		frame.setScoll(sc);
		frame.getScoll().save();
		vege=true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
