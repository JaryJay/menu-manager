package food.item;

public class SolidFoodItem extends FoodItem {

	public SolidFoodItem(String name, float amount, float calories, float carbohydrates, float proteins, float fat, float sodium, float sugar, float price) {
		super(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
	}

	@Override
	public String toString() {
		return name + "\n=======================\n"
				+ "Amount:        " + amount + "g\n"
				+ "Calories:      " + calories + "g\n"
				+ "Carbohydrates: " + carbohydrates + "g\n"
				+ "Proteins:      " + proteins + "g\n"
				+ "Fat:           " + fat + "g\n"
				+ "Sodium:        " + sodium + "mg\n"
				+ "Sugar:         " + sugar + "g\n"
				+ "Price:         " + '$' + price;
	}

}
