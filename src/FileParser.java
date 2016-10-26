import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
	
	private String[][] cells;
	private String str;
	public FileParser() {
		cells = new String[9][9];
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("test.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public FileParser(String path) {
			cells = new String[9][9];
			BufferedReader br = null;
			try {

				String sCurrentLine;

				br = new BufferedReader(new FileReader(path));

				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
	}
	
	public static void main (String [] args) {
		FileParser tak = new FileParser();
		System.out.println(tak.str);
	}
}
