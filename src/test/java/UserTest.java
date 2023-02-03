import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private User u1;

	@BeforeEach
	void setUp() throws Exception {
		u1 = new User("DevO","123","DVO@email.com","Spanish");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetSetName() {
		//Arrange
		String user_name;
		
		// Act
		user_name = u1.getName();

		// Assert
		assertEquals(user_name, "DevO");

		// Act
		u1.setName("DevOps");

		user_name = u1.getName();

		// Assert
		assertEquals(user_name, "DevOps");
	}

	@Test
	void testGetSetPassword() {
		// Arrange
		String user_password;

		// Act
		user_password = u1.getPassword();

		// Assert
		assertEquals(user_password, "123");

		u1.setPassword("456");

		user_password = u1.getPassword();

		assertEquals(user_password, "456");
	}

	@Test
	void testGetSetEmail() {
		// Arrange
		String user_email;

		// Act
		user_email = u1.getEmail();

		// Assert
		assertEquals(user_email, "DVO@email.com");

		u1.setEmail("DVOP@email.com");

		user_email = u1.getEmail();

		assertEquals(user_email, "DVOP@email.com");
	}

	@Test
	void testGetSetLanguage() {
		// Arrange
		String user_language;

		// Act
		user_language = u1.getLanguage();

		// Assert
		assertEquals(user_language, "Spanish");

		u1.setLanguage("English");

		user_language = u1.getLanguage();

		assertEquals(user_language, "English");
	}
}
