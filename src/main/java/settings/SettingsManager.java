package settings;

import static java.io.File.separator;
import static menu.FoodMenuLoader.USER_PREFERENCES;

import menu.FoodMenuLoader;

public class SettingsManager {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));
	}

	public Object[] getSettings() {
		Object[] settings = new Object[2];
		settings[0] = USER_PREFERENCES.get("foodItemsFileLocation", System.getProperty("user.home") + separator + "menuManager" + separator + "foodItems.txt");
		settings[1] = USER_PREFERENCES.get("menuSaveLocation", System.getProperty("user.home") + separator + "menuManager");
		return settings;
	}

	public void saveSettings() {
		FoodMenuLoader.USER_PREFERENCES.put(null, null);
	}

}
