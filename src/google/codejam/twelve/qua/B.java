/**
 * 
 */
package google.codejam.twelve.qua;

import google.codejam.tools.QuestionFile;

import java.io.IOException;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author ya
 * 
 */
public class B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuestionFile file = new QuestionFile(
				A.class.getResourceAsStream("B-large-practice.in"), "B.out");
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

	public static String parse(String line) {
		String[] nums = line.split(" ");
		int n = Integer.parseInt(nums[0]);
		int su = Integer.parseInt(nums[1]);
		int p = Integer.parseInt(nums[2]);

		int count = 0;
		String code = "";
		for (int i = 0; i < n; i++) {
			int score = Integer.parseInt(nums[3 + i]);
			if (unsuprise(score) >= p)
				count++;
			else if (suprise(score) >= p) {
				if (su > 0) {
					su--;
					count++;
				}
			}

		}
		return count + "";
	}

	public static int suprise(int n) {
		switch (n) {
		case 0:
			return -1;
		case 1:
			return -1;
		case 2:
			return 2;
		case 3:
			return 2;
		case 4:
			return 2;
		default:
			return (n + 1) / 3 + 1;
		}
	}

	public static int unsuprise(int n) {
		switch (n) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 1;
		default:
			return (n - 1) / 3 + 1;
		}
	}
}
