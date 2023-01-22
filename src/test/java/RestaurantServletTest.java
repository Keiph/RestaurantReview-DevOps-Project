import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class RestaurantServletTest {

	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private String driverClass;
	private String crudURL;

	List<Restaurant> restaurants = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		restaurants.add(new Restaurant("Test Name", "Test Location", "Test Opening ", "Test Closing",
				"Test Description", "Test Cuisine"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConnectionInvalidCredentials() {
		// arrange
		driverClass = "com.mysql.jdbc.Driver";
		// act & assert
		assertThrows(SQLException.class, () -> new RestaurantServlet().getConnection(driverClass,
				"jdbc:mysql://localhost:3306/restaurant_review", "roots", "password"));

		

	}

	@Test
	void testConnectionClassNotFound() {
		// arrange
		jdbcURL = "jdbc:mysql://restaurant-db.cxlgkwuwv7wz.us-east-1.rds.amazonaws.com:3306/restaurant_db";
		jdbcUsername = "admin";
		jdbcPassword = "password";
		// act & assert
		assertThrows(ClassNotFoundException.class,
				() -> new RestaurantServlet().getConnection("wrongClass", jdbcURL, jdbcUsername, "password"));

		
	}

	@Test
	@Order(1)
	void testGetConnectionSuccessful() {
		// arrange
		jdbcURL = "jdbc:mysql://restaurant-db.cxlgkwuwv7wz.us-east-1.rds.amazonaws.com:3306/restaurant_db";
		jdbcUsername = "admin";
		jdbcPassword = "password";
		driverClass = "com.mysql.jdbc.Driver";
		
		// act & assert
		assertEquals("jdbc:mysql://restaurant-db.cxlgkwuwv7wz.us-east-1.rds.amazonaws.com:3306/restaurant_db", jdbcURL);
		assertEquals("admin", jdbcUsername);
		assertEquals("password", jdbcPassword);
		assertAll(() -> new RestaurantServlet().getConnection(driverClass, jdbcURL, jdbcUsername, jdbcPassword));
		
		
		
	}

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() {

	}

	@Test
	@Order(2)
	void testGetlistOfRestaurants() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher forwardResManagement = mock(RequestDispatcher.class);
		crudURL = "/RestaurantServlet/dashboard";

		when(request.getServletPath()).thenReturn(crudURL);
		when(request.getRequestDispatcher(eq("/restaurantManagement.jsp"))).thenReturn(forwardResManagement);
		when(response.getWriter()).thenReturn(printWriter);

		new RestaurantServlet().doPost(request, response);
		
		verify(request, atLeast(1)).getServletPath();
		assertEquals(crudURL, request.getServletPath());
	}

	@Test
	@Order(3)
	void testShowEditForm() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher forwardEdit = mock(RequestDispatcher.class);
		crudURL = "/RestaurantServlet/edit";

		// boilerplate code from RegisterRestaurantServlet
		// this is to ensure that a Restaurant Object have been created in the database
		// before calling the showEditForm method
		// Not to worry about the duplicate data that is going to occur when testing the
		// app as at the end of this unit test we gonna test delete function
		// which will delete all duplicate data that this test have created
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getParameter("restaurantName")).thenReturn(restaurants.get(0).getRestaurantName());
		when(request.getParameter("restaurantLocation")).thenReturn(restaurants.get(0).getRestaurantLocation());
		when(request.getParameter("restaurantOpening")).thenReturn(restaurants.get(0).getOpeningTime());
		when(request.getParameter("restaurantClosing")).thenReturn(restaurants.get(0).getClosingTime());
		when(request.getParameter("restaurantDescription")).thenReturn(restaurants.get(0).getRestaurantDescription());
		when(request.getParameter("restaurantCuisine")).thenReturn(restaurants.get(0).getRestaurantCuisine());

		assertAll(() -> new RegisterRestaurantServlet().doPost(request, response));

		verify(request, atLeast(1)).getParameter("restaurantName");
		verify(request, atLeast(1)).getParameter("restaurantLocation");
		verify(request, atLeast(1)).getParameter("restaurantOpening");
		verify(request, atLeast(1)).getParameter("restaurantClosing");
		verify(request, atLeast(1)).getParameter("restaurantDescription");
		verify(request, atLeast(1)).getParameter("restaurantCuisine");
		assertEquals(restaurants.get(0).getRestaurantName(),request.getParameter("restaurantName"));
		assertEquals(restaurants.get(0).getRestaurantLocation(),request.getParameter("restaurantLocation"));
		assertEquals(restaurants.get(0).getOpeningTime(),request.getParameter("restaurantOpening"));
		assertEquals(restaurants.get(0).getClosingTime(),request.getParameter("restaurantClosing"));
		assertEquals(restaurants.get(0).getRestaurantDescription(),request.getParameter("restaurantDescription"));
		assertEquals(restaurants.get(0).getRestaurantCuisine(),request.getParameter("restaurantCuisine"));
		
		when(request.getServletPath()).thenReturn(crudURL);
		when(request.getRequestDispatcher(eq("/restaurantEdit.jsp"))).thenReturn(forwardEdit);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getParameter("name")).thenReturn(restaurants.get(0).getRestaurantName());

		new RestaurantServlet().doPost(request, response);
		
		verify(request, atLeast(1)).getServletPath();
		verify(request, atLeast(1)).getParameter("name");
		assertEquals(crudURL, request.getServletPath());
		assertEquals(restaurants.get(0).getRestaurantName(), request.getParameter("name"));
	
		
		

	}

	@Test
	@Order(4)
	void testUpdateRestaurant() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		crudURL = "/RestaurantServlet/update";

		when(request.getServletPath()).thenReturn(crudURL);
		when(request.getParameter("oriRestaurantName")).thenReturn(restaurants.get(0).getRestaurantName());
		when(request.getParameter("restaurantName")).thenReturn(restaurants.get(0).getRestaurantName());
		when(request.getParameter("restaurantLocation")).thenReturn(restaurants.get(0).getRestaurantLocation());
		when(request.getParameter("openingTime")).thenReturn(restaurants.get(0).getOpeningTime());
		when(request.getParameter("closingTime")).thenReturn(restaurants.get(0).getClosingTime());
		when(request.getParameter("restaurantDescription")).thenReturn(restaurants.get(0).getRestaurantDescription());
		when(request.getParameter("restaurantCuisine")).thenReturn(restaurants.get(0).getRestaurantCuisine());

		when(response.getWriter()).thenReturn(printWriter);

		new RestaurantServlet().doPost(request, response);
		
		verify(request, atLeast(1)).getServletPath();
		verify(request, atLeast(1)).getParameter("oriRestaurantName");
		verify(request, atLeast(1)).getParameter("restaurantName");
		verify(request, atLeast(1)).getParameter("restaurantLocation");
		verify(request, atLeast(1)).getParameter("openingTime");
		verify(request, atLeast(1)).getParameter("closingTime");
		verify(request, atLeast(1)).getParameter("restaurantDescription");
		verify(request, atLeast(1)).getParameter("restaurantCuisine");
		
		assertEquals(crudURL, request.getServletPath());
		assertEquals(restaurants.get(0).getRestaurantName(),request.getParameter("oriRestaurantName"));
		assertEquals(restaurants.get(0).getRestaurantName(),request.getParameter("restaurantName"));
		assertEquals(restaurants.get(0).getRestaurantLocation(),request.getParameter("restaurantLocation"));
		assertEquals(restaurants.get(0).getOpeningTime(),request.getParameter("openingTime"));
		assertEquals(restaurants.get(0).getClosingTime(),request.getParameter("closingTime"));
		assertEquals(restaurants.get(0).getRestaurantDescription(),request.getParameter("restaurantDescription"));
		assertEquals(restaurants.get(0).getRestaurantCuisine(),request.getParameter("restaurantCuisine"));
		
	
	}

	@Test
	@Order(5)
	void testDeleteRestaurant() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		crudURL = "/RestaurantServlet/delete";
		when(request.getServletPath()).thenReturn(crudURL);
		when(request.getParameter("name")).thenReturn(restaurants.get(0).getRestaurantName());

		when(response.getWriter()).thenReturn(printWriter);

		new RestaurantServlet().doPost(request, response);
		
		
		verify(request, atLeast(1)).getServletPath();
		verify(request, atLeast(1)).getParameter("name");
		
		
		assertEquals(crudURL, request.getServletPath());
		assertEquals(restaurants.get(0).getRestaurantName(),request.getParameter("name"));

		
	
	}

}
