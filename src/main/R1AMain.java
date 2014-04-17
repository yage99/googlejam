/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ya
 * 
 */
public class R1AMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("A-large (1).in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {

				out.write("Case #" + (t + 1) + ": ");
				String line = br.readLine();
				long r = Long.parseLong(line.split(" ")[0]);
				long tt = Long.parseLong(line.split(" ")[1]);
				//
				// long count = (long) (Math.sqrt(t + r * r) - r);
				// long count = 0;
				// long sum = 0;
				// for (int i = 1; i <= count; i++) {
				// sum += (2 * r + 4 * i - 3);
				// }
				// while (true) {
				// count++;
				// sum += (2 * r + 4 * count - 3);
				// if (sum > tt)
				// break;
				// }
				long count = (long) ((1 - 2 * r + Math.sqrt(4 * r * r - 2 * r + 8 * tt
						+ 1)) / 4);
				System.out.println(t);

				out.write(count - 1 + "\n");
			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}