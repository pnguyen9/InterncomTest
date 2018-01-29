package intercom;

import java.util.ArrayList;

import com.google.gson.Gson;

public class JsonDeserializer<clazz> {

	private Class<clazz> clazz;
	private Gson gson = new Gson();

	public JsonDeserializer(Class<clazz> clazz) {
		this.clazz = clazz;
	}

	public ArrayList<clazz> deserializeJsonFile(String filePath) {
		ArrayList<String> json = FileReaderService.getTextFromFile(filePath);
		ArrayList<clazz> objects = new ArrayList<clazz>();

		for (String objectJson : json) {
			objects.add(deserializeOneJsonFromText(objectJson));
		}

		return objects;
	}

	public clazz deserializeOneJsonFromText(String json) {
		return gson.fromJson(json, clazz);
	}

}
