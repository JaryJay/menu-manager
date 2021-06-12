package fooditem;

public class LiquidFoodItem extends FoodItem {

	public LiquidFoodItem(String name, double amount, double calories, double carbohydrates, double proteins, double fat, double sodium, double sugar, double price) {
		super(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
	}

	@Override
	public String toString() {
		return name + "<br/>"
				+ "Amount        " + amount + "mL\n"
				+ "Calories      " + calories + "g\n"
				+ "Carbohydrates " + carbohydrates + "g\n"
				+ "Proteins      " + proteins + "g<br/>"
				+ "Fat           " + fat + "g<br/>"
				+ "Sodium        " + sodium + "mg<br/>"
				+ "Sugar         " + sugar + "g<br/>"
				+ "Price         " + '$' + price;
	}

	@Override
	public Object[] getTableFormat() {
		return new Object[] { getName() + "", getAmount() + "mL", getCalories() + "", getCarbohydrates() + "g",
				getProteins() + "g", getFat() + "g", getSodium() + "mg", getSugar() + "g", "$" + getPrice() };
	}

}
