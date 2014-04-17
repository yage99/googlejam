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
public class BMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("B-small-attempt0.in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {

				out.write("Case #" + (t + 1) + ": ");

				String line = br.readLine();
				int n = Integer.parseInt(line.split(" ")[0]);
				int m = Integer.parseInt(line.split(" ")[1]);

				int[][] mat = new int[n][m];
				for (int i = 0; i < n; i++) {
					String[] nums = br.readLine().split(" ");
					for (int j = 0; j < nums.length; j++) {
						mat[i][j] = Integer.parseInt(nums[j]);
					}
				}

				System.out.println(t);
				/*
				 * while (true) { int min = 100, nextmin = 101; for (int i = 0;
				 * i < n; i++) { for (int j = 0; j < m; j++) { if (min ==
				 * mat[i][j]) ; else if (min > mat[i][j]) { nextmin = min; min =
				 * mat[i][j]; } else if (nextmin > mat[i][j]) { nextmin =
				 * mat[i][j]; } } } if (nextmin == 101) { out.write("YES\n");
				 * continue nextstep; }
				 * 
				 * for (int i = 0; i < n; i++) { for (int j = 0; j < m; j++) {
				 * if (min == mat[i][j]) { if (((i == 0 || mat[i - 1][j] == min)
				 * && (i == n - 1 || mat[i + 1][j] == min)) || ((j == 0 ||
				 * mat[i][j - 1] == min) && (j == m - 1 || mat[i][j + 1] ==
				 * min))) continue; out.write("NO\n"); continue nextstep; } } }
				 * 
				 * for (int i = 0; i < n; i++) { for (int j = 0; j < m; j++) {
				 * if (min == mat[i][j]) { mat[i][j] = nextmin; } } } }
				 */
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < m; ++j) {
						int c = mat[i][j];

						boolean ok = true;
						for (int k = 0; k < n; ++k) {
							if (mat[k][j] > c) {
								ok = false;
								break;
							}
						}
						if (ok)
							continue;

						ok = true;
						for (int k = 0; k < m; ++k) {
							if (mat[i][k] > c) {
								ok = false;
								break;
							}
						}

						if (!ok) {
							out.write("NO\n");
							continue nextstep;
						}
					}
				}
				out.write("YES\n");

			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}