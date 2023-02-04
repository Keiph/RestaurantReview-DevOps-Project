import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServletTest {
	
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	
	List<User> users = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		users.add(new User("Test Name", "Test Password", "Test Email ", "Test Language"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() {
		
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() {
		
	}

}
