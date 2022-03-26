package notepad;

import java.util.Scanner;

public class Note {
	LocalStorage storage;
	String text;
	String filename;
	
	public Note(LocalStorage storage) {
		this.storage = storage;
		this.text = "";
		this.filename = "newnotepad";
	}
	
	public void write() {
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter new text: ");

	    text += myObj.nextLine();
	    myObj.close();
	}
	
	public void setFileName() {
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Enter new filename: ");

	    filename = myObj.nextLine();
	    if (filename.substring(filename.length()-4, filename.length()-3).equals(".")) {
	    	filename = filename.substring(0, filename.length()-4);
	    }
	    myObj.close();
	}
	
	public void clear() {
		text = "";
	}

	public void open() {
		text = storage.readNote(filename);
	}

	public boolean save() {
	    return storage.saveNote(text, filename);
	}
	
}
