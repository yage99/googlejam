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
public class CopyOfMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("A-large.in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int n = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < n; t++) {
				int[] x = new int[10];
				int[] o = new int[10];

				for (int i = 0; i < x.length; i++) {
					x[i] = 0;
					o[i] = 0;
				}

				int count = 0;
				for (int i = 0; i < 5; i++) {
					char line[] = br.readLine().toCharArray();
					for (int j = 0; j < line.length; j++) {
						count++;
						if (line[j] == 'X') {
							x[i]++;
							x[4 + j]++;

							if (i == j)
								x[8]++;
							else if (i == 3 - j) {
								x[9]++;
							}
						} else if (line[j] == 'O') {
							o[i]++;
							o[4 + j]++;
							if (i == j)
								o[8]++;
							else if (i == 3 - j)
								o[9]++;
						} else if (line[j] == 'T') {
							o[i]++;
							x[i]++;
							o[4 + j]++;
							x[4 + j]++;

							if (i == j) {
								x[8]++;
								o[8]++;
							} else if (i == 3 - j) {
								x[9]++;
								o[9]++;
							}
						} else if (line[j] == '.')
							count--;
					}

				}

				out.write("Case #" + (t + 1) + ": ");

				for (int i = 0; i < x.length; i++) {
					if (x[i] == 4) {
						out.write("X won");
						out.write("\n");
						continue nextstep;
					} else if (o[i] == 4) {
						out.write("O won");
						out.write("\n");
						continue nextstep;
					}
				}

				if (count == 16)
					out.write("Draw\n");
				else
					out.write("Game has not completed\n");
			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}