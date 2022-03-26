package notepad;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;

import org.junit.jupiter.api.*;

public class NoteTest {
	private Note note;
	private LocalStorage mockStorage;
	
	@BeforeEach
	public void setUp() {
		mockStorage = mock(LocalStorage.class);
		note = new Note(mockStorage);
	}
	
	@Test
	@DisplayName("test write and clear text")
	public void testWriteAndClear() {
		//set up
		InputStream in = new ByteArrayInputStream("test".getBytes());
		System.setIn(in);
		
		//act and assert
		note.write();
		assertEquals("test", note.text);
		note.clear();
		assertEquals("", note.text);
	}
	
	@Test
	@DisplayName("test save")
	public void testSave() {
		//set up
		when(mockStorage.saveNote("test-text", "test")).thenReturn(true);
		
		//act and assert
		InputStream in = new ByteArrayInputStream("test-text".getBytes());
		System.setIn(in);
		note.write();
		in = new ByteArrayInputStream("test.txt".getBytes());
		System.setIn(in);
		note.setFileName();
		assertEquals("test", note.filename);
		boolean result = note.save();
		verify(mockStorage, times(1)).saveNote("test-text", "test");
		assertEquals(true, result);
	}
	

	@Test
	@DisplayName("test open certain filename")
	public void testOpen() {
		//set up
		when(mockStorage.readNote("nama-file")).thenReturn("test-text");
		
		//act and assert
		InputStream in = new ByteArrayInputStream("nama-file".getBytes());
		System.setIn(in);
		note.setFileName();
		assertEquals("nama-file", note.filename);
		note.open();
		assertEquals("test-text", note.text);
		verify(mockStorage, times(1)).readNote("nama-file");
		
	}
	
	
}
