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
import java.util.List;

/**
 * @author ya
 * 
 */
public class R1BMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("B-small-attempt0 (1).in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {

				out.write("Case #" + (t + 1) + ": ");
				String line = br.readLine();
				long E = Long.parseLong(line.split(" ")[0]);
				long R = Long.parseLong(line.split(" ")[1]);
				int n = Integer.parseInt(line.split(" ")[2]);

				long[] spend = new long[n];
				String[] values = br.readLine().split(" ");
				long[] data = new long[n];
				for (int i = 0; i < n; i++) {
					data[i] = Long.parseLong(values[i]);
					spend[i] = -1;
				}
				while (!parse(data, spend, E, R))
					;

				long all = 0;
				for (int i = 0; i < n; i++) {
					all += spend[i] * data[i];
				}

				out.write(all + "\n");
			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean parse(long[] data, long[] spend, long E, long regain) {
		int length = data.length;
		long max = 0;
		boolean allParsed = true;
		List<Integer> pos = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			if (spend[i] != -1)
				continue;
			allParsed = false;
			if (max < data[i]) {
				max = data[i];
				pos = new ArrayList<Integer>();
				pos.add(i);
			} else if (max == data[i]) {
				pos.add(i);
			}
		}

		for (int i = 0; i < pos.size(); i++) {
			long s = 0;
			int npos = pos.get(i);
			int j = 0;
			long remain = 0;
			for (j = 0; npos + j < spend.length && spend[npos + j] == -1
					&& remain < E; j++) {
				remain += regain;
			}

			for (; npos - j >= 0 && spend[npos - j] == -1 && s < E; j++) {
				s += regain;
			}
			if (npos - j == -1)
				s = E;
			if (s > E)
				s = E;

			if (remain < E && npos + j < spend.length) {
				s = s + remain - data[npos];
				for (j = 1; npos + j < spend.length && spend[npos + j] == -1; j++) {
					spend[npos + j] = 0;
				}
			}
			if (s < 0)
				System.out.println("error");
			spend[npos] = s;
		}
		return allParsed;
	}
}