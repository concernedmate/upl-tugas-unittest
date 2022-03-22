package notepad;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LocalStorage {
	public boolean createNote(String name) {
		try {
			File myObj = new File(name+".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
			return true;
	    } catch (IOException e) {
	      	e.printStackTrace();
			return false;
	    }
	}
	
	public boolean saveNote(String text, String filename) {
		try {
			createNote(filename);
			FileWriter myWriter = new FileWriter(filename+".txt");
			myWriter.write(text);
	      	myWriter.close();
			return true;
	    } catch (IOException e) {
	    	e.printStackTrace();
			return false;
	    }
	}
	
	public String readNote(String filename) {
		try {
			File myObj = new File(filename+".txt");
			Scanner myReader = new Scanner(myObj);
			String data = "";
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();
			}
			myReader.close();
			return data;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
}
