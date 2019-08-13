package org.adapter;

public class AdapterExample {

	public static void main(String[] args) {
		
		AndroidCharger androidCharger = new AndroidCharger();
		Android androidPhone = new OnePlus();
		androidCharger.charge(androidPhone);

		
		IphoneCharger iphoneCharger = new IphoneCharger();
		Iphone iphone = new Iphone10();
		iphoneCharger.charge(iphone);
       
		
		IphoneToAndroidAdapter adapter = new IphoneToAndroidAdapter(androidPhone);
		iphoneCharger.charge(adapter);
		
	}

}
