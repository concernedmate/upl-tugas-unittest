package notepad;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

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
		assertEquals(true, result);
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
		assertEquals(file.exists(), result);
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
		
		//act and act
		boolean result = storage.saveNote("test-text", "test");
		assertEquals(true, result);
		String data = storage.readNote("test");
		assertEquals("test-text", data);
		
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@DisplayName("test saveNote failure")
	public void testSaveFail() {
		//setup
		File file = new File("test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.exists()) {
			file.setWritable(false);
		}
		boolean result = storage.saveNote("test-text", "test");
		assertEquals(false, result);

		if (file.exists()) {
			file.delete();
		}
	}
}
