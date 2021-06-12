package fooditem;

public class SolidFoodItem extends FoodItem {

	public SolidFoodItem(String name, double amount, double calories, double carbohydrates, double proteins, double fat, double sodium, double sugar, double price) {
		super(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
	}

	@Override
	public String toString() {
		return name + "<br/>"
				+ "Amount        " + amount + "g<br/>"
				+ "Calories      " + calories + "g<br/>"
				+ "Carbohydrates " + carbohydrates + "g<br/>"
				+ "Proteins      " + proteins + "g<br/>"
				+ "Fat           " + fat + "g<br/>"
				+ "Sodium        " + sodium + "mg<br/>"
				+ "Sugar         " + sugar + "g<br/>"
				+ "Price         " + '$' + price;
	}

	@Override
	public Object[] getTableFormat() {
		return new Object[] { getName() + "", getAmount() + "g", getCalories() + "", getCarbohydrates() + "g",
				getProteins() + "g", getFat() + "g", getSodium() + "mg", getSugar() + "g", "$" + getPrice() };
	}

}
