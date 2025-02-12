package com.exchrategen;

import java.util.Random;

public class ExchRateGen {
	public double generateRate() {
		Random rand = new Random();
		double szam = rand.nextInt(100)+rand.nextDouble();
		double val = 100;
		
		for(int i = 0; i<=10; i++) {
			if(szam>(val/2)) {
				double j = i;
				return j/10;
			}
			val/=2;
		}
		return 1;
	}	
}
