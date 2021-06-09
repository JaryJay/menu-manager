package food;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import food.item.FoodItem;

public class FoodItemLoader {

	private static final Preferences userPreferences = Preferences.userRoot().node("menuManager");

	public List<FoodItem> loadFoodItems() {
		boolean nodeExists = false;
		try {
			nodeExists = userPreferences.nodeExists("");
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		BufferedReader br;
		if (nodeExists) {
			br = externalFileReader();
		} else {
			br = factoryDefaultReader();
		}
		if (br == null) {
			throw new RuntimeException("Could not get buffered reader");
		}
		List<FoodItem> foodItems = new ArrayList<>();
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] arr = line.split("\\|");
				String name = arr[0];
				float amount = Float.parseFloat(arr[1]);
				float calories = Float.parseFloat(arr[2]);
				float carbohydrates = Float.parseFloat(arr[3]);
				float proteins = Float.parseFloat(arr[4]);
				float fat = Float.parseFloat(arr[5]);
				float sodium = Float.parseFloat(arr[6]);
				float sugar = Float.parseFloat(arr[7]);
				float price = Float.parseFloat(arr[8]);
				FoodItem foodItem = new FoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
				foodItems.add(foodItem);
				System.out.println(foodItem);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return foodItems;
	}

	private BufferedReader externalFileReader() {
		String path = userPreferences.get("fileLocation", System.getProperty("user.home"));
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			return br;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedReader factoryDefaultReader() {
		ClassLoader classLoader = this.getClass().getClassLoader();
		String path = "food/foodItems.txt";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(path)))) {
			return br;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
