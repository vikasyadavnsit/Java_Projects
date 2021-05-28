package org.api.wrapper.patterns;

public class HathwayISP implements ISP {

	@Override
	public String getResrouce(String site) {
		return switch (site) {
		case "google.com" -> "Welcome To Google";
		default -> "Resouce Not Found";
		};
	}

}
