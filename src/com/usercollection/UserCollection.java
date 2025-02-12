package com.usercollection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.user.*;

public class UserCollection implements Serializable{
	private static final long serialVersionUID = 1L;
	List<User> usercollection = new ArrayList<User>();
	
	
	public List<User> getUsercollection() {
		return usercollection;
	}

	public void setUsercollection(List<User> usercollection) {
		this.usercollection = usercollection;
	}

	public boolean addUser(User u) {
		for(User i : usercollection) {
			if(i.userExist(u)) {
				System.out.println("A felhasználó már létezik.");
				return false;
			}
		}	
		usercollection.add(u);
		return true;
	}
	
	public void save() {
		try {
			FileOutputStream f = new FileOutputStream("users.dat");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(usercollection);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			FileInputStream f = new FileInputStream("users.dat");
			ObjectInputStream in = new ObjectInputStream(f);
			usercollection=(List<User>)in.readObject();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(User u) {
		usercollection.remove(u);
	}
	
	public User getUser(int index) {
		return usercollection.get(index);
	}
	
	public void setUser(int index, User u) {
		usercollection.set(index, u);
	}
	
	public int getSize() {
		return usercollection.size();
	}
	
	public void pritAllStuff() {
		for(User i : usercollection) {
			System.out.println(i.getUsername()+" "+i.getPassword()+" "+i.getCashavailable());
		}
	}	
}
