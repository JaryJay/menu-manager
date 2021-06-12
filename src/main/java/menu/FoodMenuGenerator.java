package menu;

import java.util.ArrayList;
import java.util.List;

import fooditem.FoodItem;
import fooditem.FoodItemLoader;

public class FoodMenuGenerator {

	public FoodMenu generateFoodMenu() {
		FoodMenu foodMenu = new FoodMenu("generated");
		List<FoodItem> foodItemsBuffer = new ArrayList<>(FoodItemLoader.instance().getFoodItemsBuffer());
		while (foodMenu.totalAmount() <= 2300) {
			foodMenu.add(foodItemsBuffer.remove((int) (Math.random() * foodItemsBuffer.size())));
		}
		return foodMenu;
	}

}
