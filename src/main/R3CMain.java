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
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ya
 * 
 */
public class R3CMain {

				static int count = 0;static Wall wall;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("C-small-practice (2).in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {

				count = 0;
				out.write("Case #" + (t + 1) + ": ");

				int N = Integer.parseInt(br.readLine());

				wall = new Wall();
				Map<Integer, Tribe> attackMap = new TreeMap<Integer, Tribe>();
				for (int i = 0; i < N; i++) {
					String line = br.readLine();
					int a = Integer.parseInt(line.split(" ")[0]);
					int b = Integer.parseInt(line.split(" ")[1]);
					int c = Integer.parseInt(line.split(" ")[2]);
					int d = Integer.parseInt(line.split(" ")[3]);
					int e = Integer.parseInt(line.split(" ")[4]);
					int f = Integer.parseInt(line.split(" ")[5]);
					int g = Integer.parseInt(line.split(" ")[6]);
					int h = Integer.parseInt(line.split(" ")[7]);

					Tribe tribe = new Tribe(a, b, c, d, e, f, g, h);
					addTribe(tribe, attackMap);

				}

				while (attackMap.size() > 0) {
					Integer next = attackMap.keySet().iterator().next();
					Tribe tribe = attackMap.remove(next);
					if (tribe.attack(wall))
						count++;
					addTribe(tribe, attackMap);
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

	/**
	 * @param tribe
	 */
	private static void addTribe(Tribe tribe, Map<Integer, Tribe> attackMap) {
		if (tribe.n == 0)
			return;
		Integer key = tribe.getNextAttack();
		if (attackMap.containsKey(key)) {
			Tribe t1 = attackMap.get(key);
			if (t1.s < tribe.s) {
				attackMap.remove(key);
				attackMap.put(key, tribe);

				if (t1.attack(wall)) count++;
					addTribe(t1, attackMap);
			} else {
				if (tribe.attack(wall)) count++;
					addTribe(tribe, attackMap);
			}
		} else
			attackMap.put(key, tribe);
	}
}

class Tribe {
	public int d;
	public int n;
	public int w, e;
	public int s;
	public int delta_d;
	public int delta_p;
	public int delta_s;

	public Tribe(int d, int n, int w, int e, int s, int delta_d, int delta_p,
			int delta_s) {
		this.d = d;
		this.n = n;
		this.w = w;
		this.e = e;
		this.s = s;
		this.delta_d = delta_d;
		this.delta_p = delta_p;
		this.delta_s = delta_s;
	}

	public boolean attack(Wall wall) {
		int[] area = wall.getHeight(w, e);
		for (int i = 0; i < area.length; i++) {
			if (area[i] < s) {
				wall.setHeight(w, e, s);
				change();
				return true;
			}
		}
		change();
		return false;
	}

	public boolean change() {
		if (this.n == 0)
			return false;

		this.d += this.delta_d;
		this.w += this.delta_p;
		this.e += this.delta_p;
		this.s += this.delta_s;
		this.n--;
		return true;
	}

	public int getNextAttack() {
		return this.d;
	}
}

class Wall {
	int[] list = new int[3 * (10 ^ 6)];
	int[] list5 = new int[3 *(10^6)];

	public int[] getHeight(int w, int e) {
		w += 10 ^ 6;
		e += 10 ^ 6;

		int[] result = new int[(e - w + 1)*2-1];
		for (int i = w; i <= e; i++) {
			result[(i - w)*2] = list[i];
			if(i!=e) result[(i-w)*2+1] = list5[i];
		}
		return result;
	}

	public void setHeight(int w, int e, int h) {
		w += 10 ^ 6;
		e += 10 ^ 6;
		for (int i = w; i <= e; i++) {
			if(list[i] < h)
			list[i] = h;
			if(i!=e && list5[i] <h)list5[i] = h;
		}
	}
}