package food;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import food.item.FoodItem;
import food.item.LiquidFoodItem;
import food.item.SolidFoodItem;

/**
 * Loads and saves food items.
 * 
 * @author Jay
 *
 */
public class FoodItemLoader {

	private static final Preferences userPreferences = Preferences.userRoot().node("menuManager");
	private List<FoodItem> originalFoodItems;
	private List<FoodItem> foodItems;

	/**
	 * Gets the food items list, or loads them if the list is null
	 * 
	 * @return The food items list
	 */
	public List<FoodItem> getFoodItems() {
		if (foodItems != null) {
			return foodItems;
		}
		boolean nodeExists = userPreferences.get("fileLocation", "none") != "none";
		try (BufferedReader br = nodeExists ? externalFileReader() : factoryDefaultReader()) {
			foodItems = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split("\\|");
				String name = arr[0].trim();
				float calories = Float.parseFloat(arr[2].trim());
				float carbohydrates = Float.parseFloat(arr[3].trim());
				float proteins = Float.parseFloat(arr[4].trim());
				float fat = Float.parseFloat(arr[5].trim());
				float sodium = Float.parseFloat(arr[6].trim());
				float sugar = Float.parseFloat(arr[7].trim());
				float price = Float.parseFloat(arr[8].trim());
				FoodItem foodItem = null;
				arr[1] = arr[1].trim();
				if (arr[1].endsWith("mL")) {
					float amount = Float.parseFloat(arr[1].substring(0, arr[1].length() - 2));
					foodItem = new LiquidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
				} else {
					float amount = Float.parseFloat(arr[1]);
					foodItem = new SolidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
				}
				foodItems.add(foodItem);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return originalFoodItems = foodItems;
	}

	public void changeFoodItemsSaveLocation(String newPath) {
		userPreferences.put("fileLocation", newPath);
		try {
			userPreferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void deletePreferences() {
		try {
			userPreferences.removeNode();
			userPreferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void saveFoodItems() {
		if (foodItems.equals(originalFoodItems)) {
			return;
		} else {
			boolean nodeExists = false;
			try {
				nodeExists = userPreferences.nodeExists("");
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			if (nodeExists) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(userPreferences.get("fileLocation", System.getProperty("user.home"))))) {
					for (int i = 0; i < foodItems.size(); i++) {
						FoodItem foodItem = foodItems.get(i);
						bw.write(foodItem.getName() + '|' +
								foodItem.getAmount() + ((foodItem instanceof LiquidFoodItem) ? "mL" : "") + '|' +
								foodItem.getCalories() + '|' +
								foodItem.getCarbohydrates() + '|' +
								foodItem.getProteins() + '|' +
								foodItem.getFat() + '|' +
								foodItem.getSodium() + '|' +
								foodItem.getSugar() + '|' +
								foodItem.getPrice() + '\n');
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			userPreferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
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
		BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(path)));
		return br;
	}

}
