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
public class CMain {
	static List<Integer> tasks = new ArrayList<Integer>();
	static int count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("C-large-2.in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {
				System.out.println(t);

				out.write("Case #" + (t + 1) + ": ");

				String line = br.readLine();
				double s = Double.parseDouble(line.split(" ")[0]);
				double e = Double.parseDouble(line.split(" ")[1]);

				count = 0;
				int start = (int) Math.sqrt(s);
				int end = (int) Math.sqrt(e);
				if (start * start != s)
					start++;
				for (int i = start; i <= end; i++) {
					/*
					 * if (isPalin(i) && isPalin(i * i)) count++;
					 */
					tasks.add(i);
				}

				for (int i = 0; i < 10; i++) {
					new Cal().start();
				}

				out.write(count + "\n");
			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	static boolean isPalin(int i) {
		if (i < 10)
			return true;
		String num = String.valueOf(i);
		char[] reverse = num.toCharArray();
		int length = num.length() / 2;
		for (int n = 0; n < length; n++) {
			if (reverse[n] != reverse[num.length() - n - 1])
				return false;
		}
		return true;
	}

	public static synchronized void add() {
		count++;
	}
}

class Cal extends Thread {
	@Override
	public void run() {
		int i;
		while (true) {
			synchronized (CMain.tasks) {
				if (CMain.tasks.size() == 0)
					break;
				i = CMain.tasks.remove(0);
			}

			if (CMain.isPalin(i) && CMain.isPalin(i * i))
				CMain.add();
		}
	}
}