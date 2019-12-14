package airtel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClosetPath {

	static List<String> masterList = new ArrayList<String>();
	
	public static void main(String[] args) {

		masterList.add("6480, Sector C6, Vasant Kunj");
		masterList.add("Plot 16, Udyog Vihar Phase -4, Gurgaon");
		masterList.add("8231, Sector C8, Vasant Kunj");
		masterList.add("C-6/6280, Vasant Kunj");

		String input = "Plot 18, National Highway  8, Udyog Vihar Phase -4, Gurgaon";
		// "6279, Sector C6, Vasant Kunj";

		int[] closestMatchIndex = new int[masterList.size()];

		Iterator<String> itr = masterList.iterator();
		int index = 0;

		while (itr.hasNext()) {

			String str = itr.next();
			str = str.toLowerCase();
			input = input.toLowerCase();

			String inputStringArray[] = input.split("\\s+");

			for (String il : inputStringArray) {
				if (str.contains(il)) {
					closestMatchIndex[index]++;
				}
			}

			++index;
		}

		int maxProbability = 0, probableIndex = 0;
		for (int i = 0; i < closestMatchIndex.length; i++) {
			if (closestMatchIndex[i] > maxProbability) {
				maxProbability = closestMatchIndex[i];
				probableIndex = i;
			}
		}
		
/*		
		//Compare cases with different classification Numeric Match
		ArrayList<String> moreProbableList = new ArrayList<String>();
		
		Arrays.sort(closestMatchIndex);
		//fetch top 3 
		
		for (int i = 0; i < closestMatchIndex.length; i++) {
			moreProbableList.add( masterList.get(probableIndex));
		}
		
		Iterator<String> itr2 = moreProbableList.iterator();
		int index2 = 0;
		int[] closestMatchIndex2 = new int[masterList.size()];

		while (itr2.hasNext()) {

			String str = itr2.next();
			str = str.toLowerCase();
			input = input.toLowerCase();

			String inputStringArray[] = input.split("\\s+");

			for (String il : inputStringArray) {
				
				Pattern p = Pattern.compile("-?\\d+");
				Matcher m = p.matcher(str);
				while (m.find()) {
					//Group According to closest Number Match
				}				
			}

			++index2;
		}
*/		
		
		if(closestMatchIndex[probableIndex] > 0)
			System.out.println("Max Probable Address is : " + masterList.get(probableIndex));

	}

}
