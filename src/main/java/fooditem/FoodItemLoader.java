package fooditem;

import static menu.FoodMenuLoader.USER_PREFERENCES;

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

/**
 * Loads and saves food items.
 * 
 * @author Jay
 *
 */
public class FoodItemLoader {

	private static FoodItemLoader instance;

	private List<FoodItem> originalFoodItems;
	private List<FoodItem> foodItemsBuffer;

	public static FoodItemLoader instance() {
		return instance != null ? instance : (instance = new FoodItemLoader());
	}

	private FoodItemLoader() {
	}

	/**
	 * Gets the food items buffer, or loads them if the buffer is null
	 * 
	 * @return The food items buffer
	 */
	public List<FoodItem> getFoodItemsBuffer() {
		if (foodItemsBuffer != null) {
			return foodItemsBuffer;
		}
		boolean nodeExists = USER_PREFERENCES.get("foodItemsFileLocation", "not found") != "not found";
		try (BufferedReader br = nodeExists ? externalFileReader() : factoryDefaultReader()) {
			foodItemsBuffer = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split("\\|");
				String name = arr[0].trim();
				double calories = Double.parseDouble(arr[2].trim());
				double carbohydrates = Double.parseDouble(arr[3].trim());
				double proteins = Double.parseDouble(arr[4].trim());
				double fat = Double.parseDouble(arr[5].trim());
				double sodium = Double.parseDouble(arr[6].trim());
				double sugar = Double.parseDouble(arr[7].trim());
				double price = Double.parseDouble(arr[8].trim());
				FoodItem foodItem = null;
				arr[1] = arr[1].trim();
				if (arr[1].endsWith("mL")) {
					double amount = Double.parseDouble(arr[1].substring(0, arr[1].length() - 2));
					foodItem = new LiquidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
				} else {
					double amount = Double.parseDouble(arr[1]);
					foodItem = new SolidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
				}
				foodItemsBuffer.add(foodItem);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		originalFoodItems = new ArrayList<>(foodItemsBuffer);
		return foodItemsBuffer;
	}

	public void changeFoodItemsSaveLocation(String newPath) {
		USER_PREFERENCES.put("foodItemsFileLocation", newPath);
		try {
			USER_PREFERENCES.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void deletePreferences() {
		try {
			USER_PREFERENCES.removeNode();
			USER_PREFERENCES.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void revertChanges() {
		foodItemsBuffer = originalFoodItems;
	}

	public boolean noChangesExist() {
		return foodItemsBuffer.equals(originalFoodItems);
	}

	public void saveFoodItems() {
		if (foodItemsBuffer.equals(originalFoodItems)) {
			return;
		} else {
			boolean nodeExists = false;
			try {
				nodeExists = USER_PREFERENCES.nodeExists("");
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			if (nodeExists) {
				String path = USER_PREFERENCES.get("foodItemsFileLocation", System.getProperty("user.home") + File.separator + "menuManager");
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
					for (int i = 0; i < foodItemsBuffer.size(); i++) {
						FoodItem foodItem = foodItemsBuffer.get(i);
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
			USER_PREFERENCES.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public FoodItem getByName(String name) {
		if (foodItemsBuffer == null) {
			getFoodItemsBuffer();
		}
		for (int i = 0; i < foodItemsBuffer.size(); i++) {
			FoodItem foodItem = foodItemsBuffer.get(i);
			if (foodItem.getName().equals(name)) {
				return foodItem;
			}
		}
		return null;
	}

	// ======================================
	// Public and private methods separator
	// ======================================

	private BufferedReader externalFileReader() {
		String path = USER_PREFERENCES.get("foodItemsFileLocation", System.getProperty("user.home") + File.separator + "menuManager");
		try {
			return new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
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
