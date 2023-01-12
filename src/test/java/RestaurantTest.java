import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dvops.maven.eclipse.Restaurant;

class RestaurantTest {

	private Restaurant r1;

	@BeforeEach
	void setUp() throws Exception {
		r1 = new Restaurant("Mcdonald", "Punggol", "4:00am", "11:00pm", "This is Mcdonalds in Punggol", "Western");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetRestaurantName() {
		// Arrange
		String res_name;

		// Act
		res_name = r1.getRestaurantName();

		// Assert
		assertEquals(res_name, "Mcdonald");

	}

	@Test
	void testSetRestaurantName() {
		String res_name;

		res_name = r1.getRestaurantName();

		assertEquals(res_name, "Mcdonald");
		
		r1.setRestaurantName("KFC");
		
		res_name = r1.getRestaurantName();
		
		assertEquals(res_name, "KFC");

	}

	/*
	 
	 
	@Test
	void testGetRestaurantLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRestaurantLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOpeningTime() {
		fail("Not yet implemented");
	}

	@Test
	void testSetOpeningTime() {
		fail("Not yet implemented");
	}

	@Test
	void testGetClosingTime() {
		fail("Not yet implemented");
	}

	@Test
	void testSetClosingTime() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRestaurantDescription() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRestaurantDescription() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRestaurantCuisine() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRestaurantCuisine() {
		fail("Not yet implemented");
	}
	*/
}
