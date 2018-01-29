package intercom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class FileReaderService {

	public static ArrayList<String> getTextFromFile(String filePath) {
		ArrayList<String> text = new ArrayList<String>();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String nextLine;

			while ((nextLine = bufferedReader.readLine()) != null) {
				text.add(nextLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return text;
	}

}
