package com.share.historicaldata;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricalData implements Serializable{
	private static final long serialVersionUID = 1L;
	double value;
	Date date = new Date();
	
	public HistoricalData(Date datum, double szam) {
		date = datum;
		value = szam;
	}
	
	public double getValue() {return value;}
	public Date getDate() {return date;}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date)+" Árfolyam: "+value;
	}
}
