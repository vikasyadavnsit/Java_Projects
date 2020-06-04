package algo;

import java.io.IOException;
import java.util.HashMap;

public class URLShortner {

	static HashMap<Integer, String> urls = new HashMap<>();
	static int countOfUrl = 0;
	static char charMap[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static void main(String[] args) throws IOException {
		fetchRecord("Uj10p");
	   //insertRecord(234242432);
	}

	static void fetchRecord(String shortUrl) {
		int id = 0;
		for (int i = 0; i < shortUrl.length(); i++) {
			if (shortUrl.charAt(i) >= 'a' && shortUrl.charAt(i) <= 'z') {
				id = (int) (id + Math.pow(62, i) * (shortUrl.charAt(i) - 'a'));
			}
			if (shortUrl.charAt(i) >= 'A' && shortUrl.charAt(i) <= 'Z') {
				id = (int) (id + Math.pow(62, i) * (shortUrl.charAt(i) - 'A' + 26));
			}
			if (shortUrl.charAt(i) >= '0' && shortUrl.charAt(i) <= '9') {
				id = (int) (id + Math.pow(62, i) * (shortUrl.charAt(i) - '0' + 52));
			}
		}
		System.out.println(id);
	}

	static void insertRecord(int count) {
		StringBuffer sf = new StringBuffer();
		while (count > 0) {
			sf.append(charMap[count % 62]);
			count = count / 62;
		}
		System.out.println(sf.toString());
	}

}
