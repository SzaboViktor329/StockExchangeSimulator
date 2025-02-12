package com.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.position.*;
import com.share.Share;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	String username;
	String password;
	int cashavailable;
	List<Position> positions = new ArrayList<Position>();
	
	
	public User(String fhsznev, String jelszo, int penzosszeg) {
		username=fhsznev;
		password=jelszo;
		cashavailable=penzosszeg;
	}
	
	public String getUsername() {return	username;}
	public String getPassword() {return	password;}
	public int getCashavailable() {return cashavailable;}
	
	public void setUsername(String fhsznev) {username=fhsznev;}
	public void setPassword(String jelszo) {password=jelszo;}
	public void setCashavailable(int penzosszeg) {cashavailable=penzosszeg;}
	
	public boolean userExist(User u) {
		if(u.getUsername().equals(username) || u.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public boolean addPosition(Share s, int db, boolean fajta) {
		if(s.getValue()*db>cashavailable) {
			System.out.println("Nincs elég pénz");
			return false;
		}
		positions.add(new Position(s,s.getValue(),db, fajta));
		cashavailable-=s.getValue()*db;
		return true;
	}
	
	public void closePosition(Position p, Share s) {
		if(p.getType()==true) {
			cashavailable+=s.getValue()*p.getQuantity();
		}
		else {
			double value = 2*p.getStartposition() - s.getValue();
			cashavailable+=value*p.getQuantity();
		}
	}

	@Override
	public String toString() {
		
		return "Fhsz: " + username;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}
