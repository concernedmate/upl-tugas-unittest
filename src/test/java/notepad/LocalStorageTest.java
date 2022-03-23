package notepad;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.*;

public class LocalStorageTest {
	private LocalStorage storage;
	
	@BeforeEach
	public void setUp() {
		storage = new LocalStorage();
	}
	
	@Test
	@DisplayName("test createNote success")
	public void testCreateSuccess() {
		//setup
		File file = new File("test.txt");
		if (file.exists()) {
			file.delete();
		}
		
		//act
		boolean result = storage.createNote("test");
		
		//assert
		assertEquals(true, file.exists());
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@DisplayName("test createNote failure")
	public void testCreateFail() {
		//setup
		File file = new File("test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.exists()) {
			file.setReadOnly();
		}
		
		//act
		boolean result = storage.createNote("test");
		
		//assert
		assertEquals(false, result);
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@DisplayName("test saveNote and readNote")
	public void testSaveAndRead() {
		//setup
		File file = new File("test.txt");
		if (file.exists()) {
			file.delete();
		}
		
		//act
		boolean result = storage.saveNote("test-text", "text");
		String data = storage.readNote("text");
		
		//assert
		assertEquals(true, result);
		assertEquals("test-text", data);
		if (file.exists()) {
			file.delete();
		}
	}
}
