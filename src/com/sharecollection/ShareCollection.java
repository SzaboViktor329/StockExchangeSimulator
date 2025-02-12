package com.sharecollection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.share.*;

public class ShareCollection implements Serializable{
	private static final long serialVersionUID = 1L;
	List<Share> sharecollection = new ArrayList<Share>();
	
	public void addShare(Share s) {
		for(Share i : sharecollection) {
			if(i.shareExist(s)) {
				System.out.println("A részvény már létezik.");
				return;
			}
		}	
		sharecollection.add(s);
	}
	
	public void save() {
		try {
			FileOutputStream f = new FileOutputStream("shares.dat");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(sharecollection);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			FileInputStream f = new FileInputStream("shares.dat");
			ObjectInputStream in = new ObjectInputStream(f);
			sharecollection=(List<Share>)in.readObject();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pritAllStuff() {
		for(Share i : sharecollection) {
			System.out.println(i.getCompanyname()+" "+i.getSymbol()+" "+i.getValue());
			i.printLastValues();
		}
	}
	
	public void removeShare(Share s) {
		sharecollection.remove(s);
	}
	
	public Share getShare(String cegnev) {
		for(Share i : sharecollection) {
			if(cegnev.equals(i.getCompanyname())) {
				return i;
			}
		}
		return null;	
	}

	public List<Share> getSharecollection() {
		return sharecollection;
	}

	public void setSharecollection(List<Share> sharecollection) {
		this.sharecollection = sharecollection;
	}
}
