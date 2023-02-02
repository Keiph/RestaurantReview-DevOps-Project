import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void testGetSetRestaurantName() {
		// Arrange
		String res_name;

		// Act
		res_name = r1.getRestaurantName();

		// Assert
		assertEquals(res_name, "Mcdonald");

		// Act
		r1.setRestaurantName("KFC");

		res_name = r1.getRestaurantName();

		// Assert
		assertEquals(res_name, "KFC");
	}

	@Test
	void testGetSetRestaurantLocation() {
		// Arrange
		String res_location;

		// Act
		res_location = r1.getRestaurantLocation();

		// Assert
		assertEquals(res_location, "Punggol");

		r1.setRestaurantLocation("Sengkang");

		res_location = r1.getRestaurantLocation();

		assertEquals(res_location, "Sengkang");
	}

	@Test
	void testGetSetOpeningTime() {
		// Arrange
		String res_opening;

		// Act
		res_opening = r1.getOpeningTime();

		// Assert
		assertEquals(res_opening, "4:00am");

		r1.setOpeningTime("6:00am");

		res_opening = r1.getOpeningTime();

		assertEquals(res_opening, "6:00am");
	}

	@Test
	void testGetSetClosingTime() {
		// Arrange
		String res_closing;

		// Act
		res_closing = r1.getClosingTime();

		// Assert
		assertEquals(res_closing, "11:00pm");

		r1.setClosingTime("10:30pm");

		res_closing = r1.getClosingTime();

		assertEquals(res_closing, "10:30pm");
	}

	@Test
	void testGetSetRestaurantDescription() {
		// Arrange
		String res_description;

		// Act
		res_description = r1.getRestaurantDescription();

		// Assert
		assertEquals(res_description, "This is Mcdonalds in Punggol");

		r1.setRestaurantDescription("This is Mcdonalds in Sengkang");

		res_description = r1.getRestaurantDescription();

		assertEquals(res_description, "This is Mcdonalds in Sengkang");
	}

	@Test
	void testGetSetRestaurantCuisine() {
		// Arrange
		String res_cuisine;

		// Act
		res_cuisine = r1.getRestaurantCuisine();

		// Assert
		assertEquals(res_cuisine, "Western");

		r1.setRestaurantCuisine("Fast Foods");

		res_cuisine = r1.getRestaurantCuisine();

		assertEquals(res_cuisine, "Fast Foods");
	}

}
