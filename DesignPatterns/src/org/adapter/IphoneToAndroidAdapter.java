package org.adapter;

public class IphoneToAndroidAdapter implements Iphone {

	Android androidPhone;
	
	public IphoneToAndroidAdapter(Android androidPhone) {
		this.androidPhone = androidPhone;
	}

	@Override
	public void charge() {
		androidPhone.charge();
	}

}
