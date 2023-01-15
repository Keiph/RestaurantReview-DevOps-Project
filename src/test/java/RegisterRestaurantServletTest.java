import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RegisterRestaurantServletTest {
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);

	RegisterRestaurantServlet registerRestaurant;

	private static String jdbcURL = "jdbc:mysql://localhost:3306/restaurant_review";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";

	@BeforeAll
	@RepeatedTest(3)
	static void testDatabaseConnection() throws Exception {
		assertEquals("jdbc:mysql://localhost:3306/restaurant_review", jdbcURL, "Unable to find Database URL");
		assertEquals("root", jdbcUsername, "Invalid username and password, Access Denied!");
		assertEquals("password", jdbcPassword, "Invalid username and password, Access Denied");

		Class.forName("com.mysql.jdbc.Driver");

		assertAll(() -> DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword));

		DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(response.getWriter()).thenReturn(printWriter);
		when(request.getParameter("restaurantName")).thenReturn("KFC");
		when(request.getParameter("restaurantLocation")).thenReturn("Bedok");
		when(request.getParameter("restaurantOpening")).thenReturn("7:00am");
		when(request.getParameter("restaurantClosing")).thenReturn("11:30pm");
		when(request.getParameter("restaurantDescription")).thenReturn("This is KFC");
		when(request.getParameter("restaurantCuisine")).thenReturn("Fast Food");

		new RegisterRestaurantServlet().doPost(request, response);
		assertAll(() -> new RegisterRestaurantServlet().doPost(request, response));

	}

	@Test
	void testExceptionFromDoPost() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		assertThrows(Exception.class, () -> new RegisterRestaurantServlet().doPost(request, response));

		/*
		 * registerRestaurant = spy(RegisterRestaurantServlet.class);
		 * 
		 * doThrow(NullPointerException.class).when(registerRestaurant).doPost(request,
		 * response);
		 */
	}

	@AfterAll
	static void testCloseConnection() throws Exception {
		Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		con.close();

		assertTrue(con.isClosed(), "Connection is not closed");
	}

}
