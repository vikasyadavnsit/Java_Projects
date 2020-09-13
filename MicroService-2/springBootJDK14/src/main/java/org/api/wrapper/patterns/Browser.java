package org.api.wrapper.patterns;

import javax.swing.JOptionPane;

public class Browser {

	public void sendRequest() {
		String site = JOptionPane.showInputDialog("Enter the site URL");
		String response = this.getInternetProvider().getResrouce(site);
		this.loadResponse(response);
	}

	private void loadResponse(Object response) {
		System.out.println(response.toString());
	}

	private ISP getInternetProvider() {
		return NetworkSettings.getInternet();
	}

}
