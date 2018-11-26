package setsAndMapsHandsOn;
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//Creates the Scanner with the input file
		File file = new File("input.txt");
		Scanner reader = new Scanner(file);
		//Tree Map that will hold the keys and values from the input file
		Map<String,String> index = new TreeMap<String,String>();
		//Variable used throughout the class
		String line;
		String[] tokens;
		String[] keyTokens;
		String key;
		String tempKey;
		String tempValue;
		String value;
		//Reads files and seperates Each line into key and value
		while(reader.hasNext()) {
			//Gets the line of the file and breaks it apart to keys and values
			line = reader.nextLine();
		    tokens = line.split("\\{");
		    key = tokens[1].subSequence(0, tokens[1].length()-2).toString();
			value = tokens[2].subSequence(0, tokens[2].length()-1).toString();
			//If the key contains a subclass and if it's a range
			if(key.contains("!")) {
				keyTokens = key.split("\\!");
				tempKey = keyTokens[1].subSequence(0, keyTokens[1].length()-2).toString();
				key = keyTokens[1];
				if(key.contains(")")) {
					tempValue = index.get(tempKey);
					if(!tempValue.equals(value))
						value = tempValue+"-"+value;
				}
				index.put(tempKey, value);
					
			}
			//If the key is range
			else if(key.contains("|")) {
				tempKey = key.subSequence(0, key.length()-2).toString();				
				if(key.contains(")")) {
					tempValue = index.get(tempKey);
					if(!tempValue.equals(value))
						value = tempValue+"-"+value;
				}
				index.put(tempKey, value);
			}
			//If the key is a single page
			else {
				tempKey = key.subSequence(0, key.length()).toString();
				if(index.containsKey(tempKey)) {
					tempValue = index.get(tempKey);
					value = tempValue+", "+value;
				}
			    index.put(tempKey, value);
			}
				
		}
		//Closes Scanner and prints out map
		reader.close();
        for(String iKey: index.keySet())
            System.out.println(iKey + " - " + index.get(iKey));
        System.out.println();

	}
}
