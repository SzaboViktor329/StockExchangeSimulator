package com.position;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.share.*;

public class Position implements Serializable{
	private static final long serialVersionUID = 1L;
	Share share = new Share();
	double startposition;
	int quantity;
	boolean type;  //true: vásárlás , false: eladás
	Date date = new Date(System.currentTimeMillis());
	
	public Position(){}
	public Position(Share reszv, double kezdpoz, int db, boolean fajta) {
		share=reszv;
		startposition=kezdpoz;
		quantity=db;
		type= fajta;
	}
	
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
	public double getStartposition() {
		return startposition;
	}
	public void setStartposition(double startposition) {
		this.startposition = startposition;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date)+" Cég: "+share.getCompanyname();
	}
	
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	
	
	

	
	
	
	
	
}
