import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileParser {

	private String[] rows;
	private String[][] cells;

	public FileParser() {
		rows = new String[9];
		cells = new String[9][9];
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("test.sud"));
			for (int i = 0; i < rows.length; i++) {
				if ((sCurrentLine = br.readLine()) != null) {
					rows[i] = sCurrentLine;
					String[] CurrentArray = rows[i].split(" ");
					if (CurrentArray.length < 9) {
						System.out
								.println("Oops, columns are missing. Expected length: 9");
						break;
					}
					for (int j = 0; j < cells[i].length; j++) {
						cells[i][j] = CurrentArray[j];
						// System.out.print(cells[i][j]);
					}
					System.out.println();
				} else {
					System.out
							.println("Oops, rows are missing. Expected length: "
									+ rows.length);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public FileParser(String path) {
		rows = new String[9];
		cells = new String[9][9];
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("path"));
			for (int i = 0; i < rows.length; i++) {
				if ((sCurrentLine = br.readLine()) != null) {
					rows[i] = sCurrentLine;
					String[] CurrentArray = rows[i].split(" ");
					if (CurrentArray.length < 9) {
						System.out
								.println("Oops, columns are missing. Expected length: 9");
					}
					for (int j = 0; j < cells[i].length; j++) {
						cells[i][j] = CurrentArray[j];
						// System.out.print(cells[i][j]);
					}
					System.out.println();
				} else {
					System.out
							.println("Oops, rows are missing. Expected length: "
									+ rows.length);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void writeFile(String str) {
		try {
			PrintWriter writer = new PrintWriter("solution.sud", "UTF-8");
			writer.println(str);
			writer.close();
		} catch (Exception e) {
			// do something
		}
	}

	public String[][] getCells() {
		return cells;
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
	}
}
