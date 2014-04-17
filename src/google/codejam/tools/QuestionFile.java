/**
 * 
 */
package google.codejam.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author ya
 * 
 */
public class QuestionFile {
	private BufferedReader br;
	private FileWriter writer;
	private int l;

	/**
	 * @param in
	 * @param out
	 */
	public QuestionFile(InputStream is, String out) {
		br = getReader(is);
		parse(br);
		getWriter(out);
	}

	private void parse(BufferedReader br) {
		try {
			l = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param filename
	 * @return
	 */
	private BufferedReader getReader(InputStream is) {
		return new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * @param filename
	 * @return
	 */
	private void getWriter(String filename) {
		try {
			writer = new FileWriter(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param t
	 * @param str
	 * @throws IOException
	 */
	public void writeAns(int t, String str) throws IOException {
		writer.write("Case #" + (t + 1) + ": " + str + "\n");
	}

	/**
	 * @param t
	 * @param str
	 * @throws IOException
	 */
	public void writeAns(int t, int str) throws IOException {
		writer.write("Case #" + (t + 1) + ": " + str + "\n");
	}

	public void end() throws IOException {
		writer.flush();
		writer.close();
		br.close();
	}

	/**
	 * @return
	 */
	public int getLength() {
		return l;
	}

	/**
	 * @param t
	 * @param result
	 * @throws IOException
	 */
	public void writeAns(int t, Double result) throws IOException {
		writer.write("Case #" + (t + 1) + ": " + Double.toString(result) + "\n");
	}

	/**
	 * @param w
	 * @throws IOException 
	 */
	public void write(String w) throws IOException {
		writer.write(w);
	}
}
