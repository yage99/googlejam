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
public class DMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("D-small-attempt6.in"))));
			FileWriter out = new FileWriter(new File("output.out"));

			int l = Integer.parseInt(br.readLine());
			nextstep: for (int t = 0; t < l; t++) {
				System.out.println(t);

				List<Key> keys = new ArrayList<Key>();
				List<Chest> chests = new ArrayList<Chest>();

				String line = br.readLine();
				int k = Integer.parseInt(line.split(" ")[0]);
				int c = Integer.parseInt(line.split(" ")[1]);

				String[] keyline = br.readLine().split(" ");
				for (int i = 0; i < keyline.length; i++) {
					keys.add(new Key(Integer.parseInt(keyline[i])));
				}

				List<Key> allKeys = new ArrayList<Key>();
				allKeys.addAll(keys);
				for (int i = 0; i < c; i++) {
					String[] chestline = br.readLine().split(" ");
					Chest newChest = new Chest(i + 1,
							Integer.parseInt(chestline[0]));
					int keynum = Integer.parseInt(chestline[1]);
					for (int j = 0; j < keynum; j++) {
						Key key = new Key(Integer.parseInt(chestline[2 + j]));
						newChest.addContain(key);
						allKeys.add(key);
					}
					chests.add(newChest);
				}
				out.write("Case #" + (t + 1) + ": ");

				List<Chest> chestscop = new ArrayList<Chest>();
				chestscop.addAll(chests);
				if (!check(allKeys, chestscop)) {
					out.write("IMPOSSIBLE\n");
					continue nextstep;
				}

				List<Chest> zero = new ArrayList<Chest>();
				List<Chest> copychest = new ArrayList<Chest>();
				copychest.addAll(chests);
				for (int i = 0; i < copychest.size(); i++) {
					if (copychest.get(i).getKeys().size() == 0) {
						zero.add(copychest.remove(i));
						i--;
					}
				}

				count = 0;
				List<Chest> openlist = new ArrayList<Chest>();
				List<Key> copykey = new ArrayList<Key>();
				copykey.addAll(keys);
				if (open(copykey, copychest, openlist)) {
					StringBuilder sb = new StringBuilder();
					for (; openlist.size() > 0;) {
						for (int j = 0; j < zero.size(); j++) {
							if (zero.get(j).chestnum < openlist.get(0).chestnum) {
								for (int x = 0; x < keys.size(); x++) {
									if (!keys.get(x).gold
											&& keys.get(x).canOpen(zero.get(j))) {
										sb.append(zero.get(j).chestnum + " ");
										zero.remove(j);
										j--;
										keys.remove(x);
										break;
									}
								}
							} else
								break;
						}

						sb.append(openlist.get(0).chestnum + " ");
						Chest chest = openlist.remove(0);
						chests.remove(chest);
						keys.addAll(chest.keys);
					}

					boolean opened = true;
					for (; zero.size() > 0 && opened;) {
						opened = false;
						for (int i = 0; i < keys.size(); i++) {
							if (!keys.get(i).gold
									&& keys.get(i).canOpen(zero.get(0))) {
								sb.append(zero.get(0).chestnum + " ");
								zero.remove(0);
								keys.remove(i);
								opened = true;
								break;
							}
						}
					}

					if (zero.size() > 0)
						out.write("IMPOSSIBLE");
					else
						out.write(sb.toString().substring(0,
								sb.toString().length() - 1));
					/*
					 * StringBuilder sb = new StringBuilder(); int n =
					 * openlist.size(); for (int i = n - 1; i > 0; i--) {
					 * sb.append(openlist.get(i).getNum() + " "); }
					 * sb.append(openlist.get(0).getNum());
					 */
				} else
					out.write("IMPOSSIBLE");

				out.write("\n");

			}
			out.flush();
			out.close();
			br.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param allKeys
	 * @param chests
	 */
	private static boolean check(List<Key> allKeys, List<Chest> chests) {
		next: for (int j = 0; j < chests.size();) {
			for (int i = 0; i < allKeys.size(); i++) {
				if (allKeys.get(i).keynum == chests.get(j).need) {
					allKeys.remove(i);
					chests.remove(j);
					if (chests.size() == 0)
						return true;
					continue next;
				}
			}
			return false;
		}
		return false;
	}

	static int count = 0;

	/**
	 * @param keys
	 * @param chests
	 * @param openlist
	 * @return
	 */
	public static boolean open(List<Key> keys, List<Chest> chests,
			List<Chest> openlist) {
		if (chests.size() == 0)
			return true;
		else if (keys.size() == 0)
			return false;

		for (Chest chest : chests) {
			for (int i = keys.size() - 1; i >= 0; i--) {
				Key key = keys.get(i);
				if (key.canOpen(chest)) {
					List<Key> ck = new ArrayList<Key>();
					ck.addAll(keys);
					ck.addAll(chest.getKeys());
					ck.remove(key);
					List<Chest> cc = new ArrayList<Chest>();
					cc.addAll(chests);
					cc.remove(chest);
					openlist.add(chest);
					// System.out.println(key.keynum + ":" + chest.chestnum);
					// System.out.println(count++);
					boolean result = open(ck, cc, openlist);
					if (result) {
						key.gold = true;
						return result;
					}
					openlist.remove(chest);
				}
			}
		}
		return false;
	}
}

class Chest {
	public List<Key> keys = new ArrayList<Key>();
	int chestnum;
	int need;

	public Chest(int chestnum, int need) {
		this.chestnum = chestnum;
		this.need = need;
	}

	public void addContain(Key e) {
		keys.add(e);
	}

	public List<Key> getKeys() {
		return keys;
	}

	public int getNum() {
		return chestnum;
	}
}

class Key {
	public List<Chest> chests = new ArrayList<Chest>();
	int keynum;
	boolean gold = false;

	public Key(int keynum) {
		this.keynum = keynum;
	}

	public boolean canOpen(Chest chest) {
		if (chest.need == keynum)
			return true;
		return false;
	}
}