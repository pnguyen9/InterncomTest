package intercom;

public class User implements ILocatable {

	private int user_id;
	private String name;

	private double latitude;
	private double longitude;

	public User(int user_id, String name, double latitude, double longitude) {
		this.user_id = user_id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	@Override
	public double getLatitude() {
		return latitude;
	}

	@Override
	public double getLongitude() {
		return longitude;
	}
}
