package menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import fooditem.FoodItem;
import fooditem.FoodItemLoader;

public class FoodMenuLoader {

	private static FoodMenuLoader instance;
	public static final Preferences USER_PREFERENCES = Preferences.userRoot().node("menuManager");

	public static FoodMenuLoader instance() {
		return instance != null ? instance : (instance = new FoodMenuLoader());
	}

	private FoodMenuLoader() {
	}

	public FoodMenu loadFoodMenu(String path) {
		File file = new File(path);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String fileName = file.getName();
			FoodMenu foodMenu = new FoodMenu(fileName.substring(0, fileName.length() - 4));
			while ((line = br.readLine()) != null) {
				FoodItem byName = FoodItemLoader.instance().getByName(line.strip());
				if (byName != null) {
					foodMenu.add(byName);
				}
			}
			return foodMenu;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveFoodMenu(FoodMenu foodMenu) {
		if (foodMenu == null) {
			return;
		}
		String menuSaveLocation = getMenuSaveLocation();
		new File(menuSaveLocation).mkdirs();
		File file = new File(menuSaveLocation + File.separatorChar + foodMenu.getName() + ".txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (int i = 0; i < foodMenu.size(); i++) {
				bw.write(foodMenu.get(i).getName() + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMenuSaveLocation() {
		String def = USER_PREFERENCES.get("menuSaveLocation", System.getProperty("user.home") + File.separatorChar + "menuManager");
		return USER_PREFERENCES.get("menuSaveLocation", def);
	}

	public void setMenuSaveLocation(String location) {
		USER_PREFERENCES.put("menuSaveLocation", location);
		try {
			USER_PREFERENCES.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public boolean foodMenuExists(String foodMenuName) {
		return new File(getMenuSaveLocation() + File.separatorChar + foodMenuName + ".txt").exists();
	}

}
