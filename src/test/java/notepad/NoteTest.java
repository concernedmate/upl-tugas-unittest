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
	@DisplayName("test open certain filename")
	public void testOpen() {
		//set up
		when(mockStorage.readNote("test")).thenReturn("test-answer");
		InputStream in = new ByteArrayInputStream("test.txt".getBytes());
		System.setIn(in);
		
		//act and assert
		note.setFileName();
		assertEquals("test", note.filename);
		note.open();
		verify(mockStorage, times(1)).readNote("test");
		assertEquals("test-answer", note.text);
		
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
		note.save();
		verify(mockStorage, times(1)).saveNote("test-text", "test");
		
	}
	
	
	
}
