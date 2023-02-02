import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HelloRestaurantServletTest extends Mockito{
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(response.getWriter()).thenReturn(printWriter);
		
		new HelloRestaurantServlet().doGet(request,response);
		
		printWriter.flush();

		verify(response, atLeast(1)).getWriter();
		assertTrue(stringWriter.toString().contains("Served at: ") );
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("yourName")).thenReturn("Keiph");
		when(response.getWriter()).thenReturn(printWriter);
		
		new HelloRestaurantServlet().doPost(request,response);
		printWriter.println("<h1>Hello " + request.getParameter("yourName") + "</h1>");
		printWriter.close();
		
		verify(request, atLeast(1)).getParameter("yourName");
		assertEquals("Keiph", request.getParameter("yourName"));
		
	}

}