package org.api.wrapper.patterns;

public class NetworkSettings {
	public static ISP getInternet() {
		return new InternetProxyISP();
	}

	public static ISP getISP() {
		return new HathwayISP();
	}
}
