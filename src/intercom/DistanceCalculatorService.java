package intercom;

import java.lang.Math;

public final class DistanceCalculatorService {
	
	private static int RADIUS = 6371;

	/**
	 * Returns the distance in kilometer between 2 ILocatable objects, using their
	 * latitudes and longitudes and the radius of earth.
	 * 
	 * @param firstLocatable
	 *            The first locatable object
	 * @param secondLocatable
	 *            The second locatable object
	 * @return See description.
	 */
	static double getKilometerBetweenLocatables(ILocatable firstLocatable, ILocatable secondLocatable) {
		double kilometer;

		double firstLatitude = Math.toRadians(firstLocatable.getLatitude());
		double secondLatitude = Math.toRadians(secondLocatable.getLatitude());

		double firstLongitude = Math.toRadians(firstLocatable.getLongitude());
		double secondLongitude = Math.toRadians(secondLocatable.getLongitude());

		kilometer = (Math.sin(firstLatitude) * Math.sin(secondLatitude)) + //
				(Math.cos(firstLatitude) * Math.cos(secondLatitude)
						* Math.cos(Math.abs(firstLongitude - secondLongitude)));

		kilometer = Math.acos(kilometer);

		return kilometer * RADIUS;
	}

}
