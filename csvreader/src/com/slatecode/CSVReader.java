package com.slatecode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {
	private Logger log = Logger.getGlobal();
	
	
	public static void main(String args[]) {
		String filename = "slatecode test.csv";
		if(args.length > 0)
			filename = args[0];
		CSVReader csvreader = new CSVReader();
		try {
			String responseString = csvreader.processFile(filename);
			System.out.println("Result is : " + responseString);
		} catch (IOException e) {
			csvreader.log.log(Level.SEVERE, "program terminated : "+e.getMessage());
		}
	}

	private String processFile(String filename) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			int currentRow = 0;
			StringBuilder result = new StringBuilder();
			String line = "";
			while (true){
				try {
					line = reader.readLine();
					if(line == null) break;
				} catch (IOException e) {
					log.log(Level.WARNING, e.getMessage());
					throw e;
				}
				String[] columns = line.split(",");
				assert columns.length >= currentRow;
				result.append(columns[currentRow] + " ");
				currentRow++;

			} 
			return result.toString();
		}
	}

}
