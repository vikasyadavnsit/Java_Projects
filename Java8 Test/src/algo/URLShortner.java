package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class URLShortner {

	static HashMap<Integer, String> urls = new HashMap<>();
	static int countOfUrl = 0;
	static char charMap[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String url = "";
		System.out.println("##########  Press 'E' to exit anytime ###########");
		while (!url.equalsIgnoreCase("e")) {
			System.out.println("Press 'C' To get Complete URL ");
			System.out.println("Press 'S' To get Short URL ");
			url = bf.readLine();
			if (url.equalsIgnoreCase("C")) {
				System.out.println("Enter Short URL : ");
				url = bf.readLine();
				System.out.println(fetchRecord(url.trim()));
			}
			if (url.equalsIgnoreCase("S")) {
				System.out.println("Enter Complete URL : ");
				url = bf.readLine();
				System.out.println(insertRecord(url.trim()));
			}
		}
	}

	static String fetchRecord(String shortUrl) {
		int id = 0;
		for (int i = 0; i < shortUrl.length(); i++) {
			if (shortUrl.charAt(i) >= 'a' && shortUrl.charAt(i) <= 'z') {
				id = id * 62 + shortUrl.charAt(i) - 'a';
			}
			if (shortUrl.charAt(i) >= 'A' && shortUrl.charAt(i) <= 'A') {
				id = id * 62 + shortUrl.charAt(i) - 'A' + 26;
			}
			if (shortUrl.charAt(i) >= '0' && shortUrl.charAt(i) <= '9') {
				id = id * 62 + shortUrl.charAt(i) - '0' + 52;
			}
		}
		return urls.get(id);
	}

	static String insertRecord(String completeUrl) {
		int count = ++countOfUrl;
		//urls.put(count, completeUrl);
		StringBuffer sf = new StringBuffer();
		while (count > 0) {
			sf.append(charMap[count % 62]);
			count = count / 62;
		}
		return sf.toString();
	}

}
