package intercom;

import java.util.ArrayList;
import java.util.Comparator;

public class Intercom {

	public static void main(String[] args) {
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
		for (User user : filteredUsers) {
			System.out.println("Name: " + user.getName() + "; Id: " + user.getUser_id());
		}
	}

	public static ArrayList<User> getUsersDistanceToOfficeBelowGivenDistanceInKilometer(Office office,
			ArrayList<User> users, int distance) {
		ArrayList<User> filteredUsers = new ArrayList<User>();

		for (User user : users) {
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
