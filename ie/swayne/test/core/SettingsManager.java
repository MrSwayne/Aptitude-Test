package ie.swayne.test.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public final class SettingsManager {
	
	private static TreeMap<String, String> globals = null;
	private static String path = Globals.CONFIG_PATH;
	private static final String DIVIDER = "=";
	
	private static void retrieveSettings() {
		File file = new File(path);
		
		if(checkFileIntegrity(file)) {
			globals = new TreeMap<>();
			
			try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
				
				String line;
				while((line = bfr.readLine()) != null) 
					if(!line.startsWith("!") && !line.isEmpty() && !line.startsWith("#")) {
						String[] pair = line.trim().split(DIVIDER);
						
						if(pair.length == 2)
							globals.put(pair[0].trim().toLowerCase(), pair[1].trim());
					}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean checkFileIntegrity(File file) {
		if(!file.exists())
			try {
				System.err.println("Config file not found. Creating now @ " + file.getAbsolutePath());
				return file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("FATAL ERROR, Could not create file " + path);
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	private static void saveSettings() {
		File file = new File(path);
		if(checkFileIntegrity(file) && globals != null && !globals.isEmpty()) {
			
			try (
				BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
				
				for(Map.Entry<String, String> entry : globals.entrySet()) {
					out.write(entry.getKey() + DIVIDER + entry.getValue() + "\n");
					out.flush();
				}
				
			} catch (IOException e) {
				throw new IllegalStateException("Settings Manager is in a bad way");
			}
		}
	}
	
	public static void setSetting(String key, String val) {
		if(globals == null) {
			retrieveSettings();
		}
		
		globals.put(key.toLowerCase(), val);
		saveSettings();
	}
	
	public static String getSetting(String key) {
		key = key.toLowerCase();
		String result = null;
		if(globals == null) {
			retrieveSettings();
		}
		
		if(!globals.isEmpty()) {
			result = globals.get(key);
			
			if(result != null && result.contains("@")) {
				String[] tokens = result.split("@");
				
				result = "";
			}
		}
		
		globals = null;
		return result;
	}
}
