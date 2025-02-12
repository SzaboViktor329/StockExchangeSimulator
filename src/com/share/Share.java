package com.share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.share.historicaldata.*;


public class Share implements Serializable{
	private static final long serialVersionUID = 1L;
	String companyname;
	String symbol;
	double value;
	List<HistoricalData> history = new ArrayList<HistoricalData>();
	
	
	public Share() {}
	public Share(String cegnev, String szimbol, double arfolyam) {
		companyname=cegnev;
		symbol=szimbol;
		value=arfolyam;
	}
	
	public String getCompanyname() {return companyname;}
	public String getSymbol() {return symbol;}
	public double getValue() {return value;}
	
	public void setCompanyname(String cegnev) {companyname=cegnev;}
	public void setSymbol(String szimbol) {symbol=szimbol;}
	public void setValue(double arfolyam) {value=arfolyam;}
	
	public void addLastValue(double szam) {
		Date datum = new Date(System.currentTimeMillis());
		history.add(new HistoricalData(datum, szam));
	}
	
	public void printLastValues() {
		for(HistoricalData i : history) {
			System.out.println(i.getDate() + "-   " + i.getValue() + " Ft");
		}
	}
	
	public boolean shareExist(Share s) {
		if(s.getCompanyname().equals(companyname)|| s.getSymbol().equals(symbol)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return companyname+ "_"+symbol +"   Árfolyam: "+ value +" Ft";
	}

	public List<HistoricalData> getHistory() {
		return history;
	}

	public void setHistory(List<HistoricalData> history) {
		this.history = history;
	}
}
