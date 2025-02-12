package com.main;

import com.frame.Frame;
import com.sharecollection.ShareCollection;
import com.usercollection.UserCollection;

public class Main {

	public static void main(String[] args) {
		ShareCollection scoll = new ShareCollection();
		UserCollection ucoll= new UserCollection();
		ucoll.load();
		scoll.load();
		ucoll.pritAllStuff();
		scoll.pritAllStuff();
		@SuppressWarnings("unused")
		Frame frame = new Frame(ucoll,scoll);
	}
	
}
