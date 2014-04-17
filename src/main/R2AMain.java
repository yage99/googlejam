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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ya
 * 
 */
public class R2AMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("A-small-attempt5.in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {

				out.write("Case #" + (t + 1) + ": ");
				String line = br.readLine();
				int A = Integer.parseInt(line.split(" ")[0]);
				int N = Integer.parseInt(line.split(" ")[1]);

				String[] mline = br.readLine().split(" ");
				int[] motes = new int[N];
				for (int i = 0; i < N; i++) {
					motes[i] = Integer.parseInt(mline[i]);
				}

				int temp;
				for (int i = 0; i < N; i++) {
					for (int j = i + 1; j < N; j++) {
						if (motes[i] > motes[j]) {
							temp = motes[j];
							motes[j] = motes[i];
							motes[i] = temp;
						}
					}
				}

				List<Integer> array = new ArrayList<Integer>();
				int count = 0;
				for (int i = 0; i < N; i++) {
					if (A == 1)
						break;
					if (A > motes[i]) {
						A += motes[i];
						array.add(-1);
					} else {
						while (A <= motes[i]) {
							A = 2 * A - 1;
							array.add(1);
						}
						array.add(-1);
					}
				}

				int min = Integer.MAX_VALUE;
				int n = 0;
				for (int i = 0; i < array.size(); i++) {
					int sum = 0;
					for (int j = 0; j <= i; j++) {
						sum += array.get(j);
					}
					if (min > sum) {
						min = sum;
						n = i;
					}
				}

				if (min >= 0)
					count = N;
				else
					count = N + min;

				out.write(count + "\n");

			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean cut(int A, int[] motes, int num, int count) {
		int a = 0;
		int x = 0;
		for (int i = num; i < motes.length; i++) {
			if (A > motes[i])
				A += motes[i];
			else {

			}
		}
		return false;
	}
}