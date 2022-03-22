package notepad;

import java.util.Scanner;

public class Note {
	LocalStorage storage = new LocalStorage();
	String text = "";
	String filename = "newnote";
	
	public void write() {
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter new text: ");

	    text += myObj.nextLine();
	    myObj.close();
	}
	
	public void clear() {
		text = "";
	}

	public void open() {
		text = storage.readNote(filename);
	}

	public void save() {
	    storage.saveNote(text, filename);
	}
	
	public void saveNewFile() {
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter new text: ");
	    String name = myObj.nextLine();
	    storage.saveNote(text, name);
	    myObj.close();
	}
	
}
