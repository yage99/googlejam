/**
 * 
 */
package google.codejam.twelve.qua;

import java.io.IOException;

import google.codejam.tools.QuestionFile;

/**
 * @author ya
 * 
 */
public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuestionFile file = new QuestionFile(
				A.class.getResourceAsStream("A-small-practice.in"), "A.out");
		int queNum = file.getLength();
		try {
			for (int t = 0; t < queNum; t++) {
				String line = file.getLine();
				file.writeAns(t, parse(line));
			}
			file.end();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String src = "ejpmysljylckdkxveddknmcrejsicpdrysirbcpcypcrtcsradkhwyfrepkymveddknkmkrkcddekrkdeoyakwaejtysrreujdrlkgcjvzq";
	static String dist = "ourlanguageisimpossibletounderstandtherearetwentysixfactorialpossibilitiessoitisokayifyouwanttojustgiveupqz";

	private static String parse(String line) {
		String ans = "";
		int i = 0;
		try {
			char[] chs = line.toCharArray();
			for (i = 0; i < chs.length; i++) {
				if (chs[i] == ' ') {
					ans += " ";
					continue;
				}
				ans += dist.charAt(src.indexOf(chs[i]));
			}
		} catch (Exception e) {
			System.out.println(line.charAt(i));
			e.printStackTrace();
		}
		return ans;
	}

}
