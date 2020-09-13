package org.api.wrapper.patterns;

import java.util.Calendar;

public class InternetProxyISP implements ISP {

	@Override
	public String getResrouce(String site) {
		logRequest(site);
		if (this.isBlocked(site)) {
			return "This site is blocked as per network firewall rules";
		}
		return NetworkSettings.getISP().getResrouce(site);
	}

	private void logRequest(String site) {
		System.out.println(Calendar.getInstance().getTime() + " Request for - " + site);
	}

	private boolean isBlocked(String site) {
		return switch (site) {
		case "gaming.com", "xyz.com" -> true;
		default -> false;
		};
	}

}
