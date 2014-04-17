/**
 * 
 */
package google.codejam.tools;

import java.util.Map;

import google.codejam.fourteen.qua.A;

/**
 * @author ya
 * 
 */
public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuestionFile file = new QuestionFile(
				A.class.getResourceAsStream("C-large.in"), "C-l.out");
		int queNum = file.getLength();
		int t = 0;
		try {
			for (t = 0; t < queNum; t++) {
				String line = file.getLine();
				String[] nums = line.split(" ");
				Integer R = Integer.parseInt(nums[0]);
				Integer C = Integer.parseInt(nums[1]);
				Integer M = Integer.parseInt(nums[2]);

				Map<String, Object> params;
				char[][] result = null;
				//char[][] result = solve(params);
				file.writeAns(t, "");
				if (result == null) {
					file.write("Impossible\n");
				} else {
					for (int i = 0; i < R; i++) {
						String w = new String(result[i]);
						file.write(w + "\n");
					}
				}
			}
			file.end();
		} catch (Exception e) {
			System.out.println(t);
			e.printStackTrace();
		}
	}

	//abstract protected static char[][] solve(Map<String, Object> params);
}
