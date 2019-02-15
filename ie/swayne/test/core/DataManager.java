package ie.swayne.test.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class DataManager {
	
	private static String path = Globals.RES_PATH;
	
	public synchronized static void serialize(Serializable obj, String name) {
		String currentDir = path + "/persistant/";
		String currentPath = currentDir + name + ".ser";
		File file = new File(currentDir);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try (
			FileOutputStream fileOut = new FileOutputStream(currentPath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
		) {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Object retrieve(String name) {
		String currentDir = path + "/persistant/" + name + ".ser";
		File file = new File(currentDir);
		if(!file.exists())
			throw new IllegalArgumentException("Invalid file " + file.getAbsolutePath());
		try (
			FileInputStream fileIn = new FileInputStream(currentDir);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			) {
			return in.readObject();
		}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
		}
	}
}
