/**
 * 
 */
package method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ya
 * 
 */
public class C {
	List<Integer> remember = new ArrayList<Integer>();

	int pow = 1;

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public String main(int a, int b) {
		int count = 0;
		int x = a;
		while (x >= 10) {
			x /= 10;
			pow++;
		}

		for (int i = a; i <= b; i++) {
			for (int j = i + 1; j <= b; j++) {
				for (int m = 0; m < pow; m++) {
					move(j);
					// System.out.println(by1);
					if (i == j) {
						// System.out.println(i + "," + j);
						count++;
						break;
					}
				}
			}
		}
		return String.valueOf(count);
	}

	void move(char[] by) {
		char x = by[0];
		int i;
		for (i = 1; i < by.length; i++) {
			by[i - 1] = by[i];
		}
		by[i - 1] = x;
	}

	void move(int x) {
		x = (int) (x / 10 + (Math.pow((x % 10), pow - 1)));
	}

	void replace(List<String> list) {
		// for (int i = 0; i < list.size(); i++) {
		// if (list.get(i).equals("")) {
		// list.remove(i);
		// }
		// }
		while (list.remove(""))
			;
	}
}
