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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import method.C;

/**
 * @author ya
 * 
 */
public class Main {

	static String in = "ejpmysljylckdkxveddknmcrejsicpdrysirbcpcypcrtcsradkhwyfrepkymveddknkmkrkcddekrkdeoyakwaejtysrreujdrlkgcjvzq";
	static String sout = "ourlanguageisimpossibletounderstandtherearetwentysixfactorialpossibilitiessoitisokayifyouwanttojustgiveupqz";

	public static Map<Integer, String> tasks = new HashMap<Integer, String>();
	public static Map<Integer, String> results = new TreeMap<Integer, String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("C-large-practice.in"))));
			FileWriter out = new FileWriter(new File("output.out"));
			
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				tasks.put(i + 1, br.readLine());
			}

			Manager mang = new Manager();
			for (int i = 0; i < 20; i++) {
				mang.addTask(new Task());
			}
			mang.start();
			mang.join();

			for (int i = 0; i < n; i++) {
				out.write("Case #" + (i + 1) + ": ");

				out.write(results.get(i));

				out.write("\n");
			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Task extends Thread {
	Integer n;
	String line;

	@Override
	public void run() {
		while (true) {
			synchronized (Main.tasks) {
				if (!Main.tasks.isEmpty()) {
					n = Main.tasks.keySet().iterator().next();
					line = Main.tasks.remove(n);
					System.out.println(Main.tasks.size());
				} else
					return;
			}
			String result = new C().main(Integer.valueOf(line.split(" ")[0]),
					Integer.valueOf(line.split(" ")[1]));
			synchronized (Main.results) {
				Main.results.put(n, result);
			}
		}
	}
}

class Manager extends Thread {
	List<Task> mon = new ArrayList<Task>();

	public void addTask(Task task) {
		mon.add(task);
	}

	@Override
	public void run() {
		for (Task task : mon) {
			task.start();
		}
		boolean wait = true;
		while (wait) {
			wait = false;
			for (Task task : mon) {
				if (task.isAlive())
					wait = true;
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
