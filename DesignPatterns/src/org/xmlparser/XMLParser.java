package org.xmlparser;

public class XMLParser {

	public void parseXML(String xml) {
		if (xml == null || xml.length() < 1) {
			return;
		}
		xml = xml.replaceAll("\\s+", "");

		int i = 0, count = 0;
		int xmlLength = xml.length();

		while (i < xmlLength) {

			if (xml.charAt(i) == '<') {
				String temp = "", temp2 = "";
				++i;
				if (xml.charAt(i) != '/') {
					while (xml.charAt(i) != '>') {
						temp += xml.charAt(i);
						++i;
					}
				} else {
					while (xml.charAt(i) != '>') {
						temp2 += xml.charAt(i);
						++i;
					}
					--count;
				}
				++i;

				String startingTag = "";
				if (!temp.isEmpty()) {
					startingTag = "<" + temp + ">";
				} else {
					startingTag = "<" + temp2 + ">";
				}

				System.out.println();
				String tabSpacing = "";
				for (int j = 0; j < count; j++) {
					tabSpacing += "   ";
				}
				System.out.print(tabSpacing + startingTag);

				if (!temp.isEmpty()) {
					++count;
				}

			} else {

				String temp = "";
				while (xml.charAt(i) != '<') {
					temp += xml.charAt(i);
					++i;
				}
				System.out.println();
				String tabSpacing = "";
				for (int j = 0; j < count; j++) {
					tabSpacing += "   ";
				}

				System.out.print(tabSpacing + temp);
			}
		}

	}

	public static void main(String[] args) {

		String str = "<breakfast_menu> <food> <name>Belgian Waffles</name> <price>$5.95</price> <description> Two of our famous Belgian Waffles with plenty of real maple syrup </description> <calories>650</calories> </food> <food> <name>Strawberry Belgian Waffles</name> <price>$7.95</price> <description> Light Belgian waffles covered with strawberries and whipped cream </description> <calories>900</calories> </food> <food> <name>Berry-Berry Belgian Waffles</name> <price>$8.95</price> <description> Light Belgian waffles covered with an assortment of fresh berries and whipped cream </description> <calories>900</calories> </food> <food> <name>French Toast</name> <price>$4.50</price> <description> Thick slices made from our homemade sourdough bread </description> <calories>600</calories> </food> <food> <name>Homestyle Breakfast</name> <price>$6.95</price> <description> Two eggs, bacon or sausage, toast, and our ever-popular hash browns </description> <calories>950</calories> </food> </breakfast_menu>";
		new XMLParser().parseXML(str);
	}

}
