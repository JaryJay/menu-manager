package app;

import java.util.prefs.BackingStoreException;

import food.FoodItemLoader;

public class MenuManagerApp {

	public static void main(String[] args) throws BackingStoreException {
		new FoodItemLoader().getFoodItems();
	}

}
