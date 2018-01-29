package intercom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class DistanceCalculatorServiceTest {

	@Test
	void isFindingExpectedDistance() {
		double expectedDistance = 41.76872550078046;

		User userTest = new User(12, "Christina McArdle", 52.986375, -6.043701);
		Office dublinOffice = new Office("Dublin Office", 53.339428, -6.257664);

		assertEquals(expectedDistance, DistanceCalculatorService.getKilometerBetweenLocatables(dublinOffice, userTest));
	}

	@Test
	void isFindingExpectedUsersBelowHundredKilometers() {
		List<String> expectedUsers = Arrays.asList( //
				"Name: Ian Kehoe; Id: 4", //
				"Name: Nora Dempsey; Id: 5", //
				"Name: Theresa Enright; Id: 6", //
				"Name: Eoin Ahearn; Id: 8", //
				"Name: Richard Finnegan; Id: 11", //
				"Name: Christina McArdle; Id: 12", //
				"Name: Olive Ahearn; Id: 13", //
				"Name: Michael Ahearn; Id: 15", //
				"Name: Patricia Cahill; Id: 17", //
				"Name: Eoin Gallagher; Id: 23", //
				"Name: Rose Enright; Id: 24", //
				"Name: Stephen McArdle; Id: 26", //
				"Name: Oliver Ahearn; Id: 29", //
				"Name: Nick Enright; Id: 30", //
				"Name: Alan Behan; Id: 31", //
				"Name: Lisa Ahearn; Id: 39" //
		);

		String usersJsonFile = "./data/gistfile1.txt";
		String dublinOfficeJsonFile = "./data/intercomDublin.txt";
		String dublinOfficeJson = FileReaderService.getTextFromFile(dublinOfficeJsonFile).get(0);

		JsonDeserializer<User> userDeserializer = new JsonDeserializer<User>(User.class);
		JsonDeserializer<Office> officeDeserializer = new JsonDeserializer<Office>(Office.class);

		ArrayList<User> users = new ArrayList<User>(userDeserializer.deserializeJsonFile(usersJsonFile));
		Office dublinOffice = officeDeserializer.deserializeOneJsonFromText(dublinOfficeJson);

		int distance = 100;
		ArrayList<User> filteredUsers = getUsersDistanceToOfficeBelowGivenDistanceInKilometer(dublinOffice, users,
				distance);
		ArrayList<String> filteredUsersToString = new ArrayList<String>();
		for (User user : filteredUsers) {
			filteredUsersToString.add("Name: " + user.getName() + "; Id: " + user.getUser_id());
		}

		assertTrue(filteredUsersToString.containsAll(expectedUsers));
	}

	public static ArrayList<User> getUsersDistanceToOfficeBelowGivenDistanceInKilometer(Office office,
			ArrayList<User> users, int distance) {
		ArrayList<User> filteredUsers = new ArrayList<User>();

		for (User user : users) {
			System.out.println(DistanceCalculatorService.getKilometerBetweenLocatables(office, user));
			if (DistanceCalculatorService.getKilometerBetweenLocatables(office, user) < distance) {
				filteredUsers.add(user);
			}
		}

		filteredUsers.sort(new Comparator<User>() {
			@Override
			public int compare(User firstUser, User secondUser) {
				return firstUser.getUser_id() > secondUser.getUser_id() ? 1 : -1;
			}
		});

		return filteredUsers;
	}

}
