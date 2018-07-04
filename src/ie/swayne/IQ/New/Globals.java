package ie.swayne.IQ.New;

import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.io.*;

/*
 * Stores all global variables
 */


//TODO
//Replace references like @name with their actual value in the file.

final public class Globals {
	
	private static final String CONFIG_PATH = "res/config.properties";
	private static boolean edited = false;
	private static TreeMap<String, String> globals = getProperties();
	
	private static final String DIVIDER = " => ";
	
	private Globals() {
	}
	
	public static void iterate() {
		for(Map.Entry<String, String> entry : globals.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
		}
	}
	
	public static String get(String name) {
		return globals.get(name);
	}
	
	public static void put(String name, String value) {
		globals.put(name, value);
		edited = true;
	}
	
	public static void save() {
		if(edited) {
			File file = new File(CONFIG_PATH);
			
			try {
				FileWriter aFileWriter = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(aFileWriter);
				
				
				for(Map.Entry<String, String> entry : globals.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					
					out.write(key + DIVIDER + value + "\n");
					out.flush();
				}
				
				out.close();
			} catch(IOException e) {
				JOptionPane optionPane = new JOptionPane("Config file Could not be written to", JOptionPane.ERROR_MESSAGE);    
				JDialog dialog = optionPane.createDialog("CRITICAL ERROR");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
		}
	}
	
	//Reads in key value pairs from a properties file and stores in a treemap
	private static TreeMap<String, String> getProperties() {
		
		final int lhs = 0;
		final int rhs = 1;
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		try {
		BufferedReader bfr = new BufferedReader(new FileReader(new File(CONFIG_PATH)));
		
		String line;
		
		while((line = bfr.readLine()) != null) {
			if(!line.startsWith("!!") && !line.isEmpty()) {
				String[] pair = line.trim().split(DIVIDER);
				
				if(pair.length == 2)
					map.put(pair[lhs].trim(), pair[rhs].trim());
			}
				
		}
		bfr.close();
		
		} catch(IOException e) {
			JOptionPane optionPane = new JOptionPane("Config file Could not be found", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("CRITICAL ERROR");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		
		return map;
	}

}
