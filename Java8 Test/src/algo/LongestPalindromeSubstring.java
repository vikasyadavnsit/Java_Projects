package algo;

public class LongestPalindromeSubstring {
	static int s, e;

	public static void main(String[] args) {
		String str = "abcdabaabadcba";
		int maxs = 0, maxe = 0;
		for (int i = 0; i < str.length(); i++) {
			isPalindrome(str, i, i);
			if (e - s > maxe - maxs) {
				maxe = e;
				maxs = s;
			}
			isPalindrome(str, i, i + 1);
			if (e - s > maxe - maxs) {
				maxe = e;
				maxs = s;
			}
		}
		if (str.length() > 0)
			System.out.println(str.substring(maxs, maxe + 1));
		else
			System.out.println();
	}

	static void isPalindrome(String str, int start, int end) {
		if (str.length() <= 2) {
			s = 0;
			e = str.length() - 1;
			return;
		}
		while (start >= 0 && end <= (str.length() - 1) && str.charAt(start) == str.charAt(end)) {
			start--;
			end++;
		}
		s = ++start;
		e = --end;
	}

}
