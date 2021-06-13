package fooditem;

public class LiquidFoodItem extends FoodItem {

	public LiquidFoodItem(String name, double amount, double calories, double carbohydrates, double proteins, double fat, double sodium, double sugar, double price) {
		super(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price);
	}

	@Override
	public String toString() {
		return "<html><pre>" + name + "<br/>"
				+ "Amount        " + amount + "mL<br/>"
				+ "Calories      " + calories + "g<br/>"
				+ "Carbohydrates " + carbohydrates + "g<br/>"
				+ "Proteins      " + proteins + "g<br/>"
				+ "Fat           " + fat + "g<br/>"
				+ "Sodium        " + sodium + "mg<br/>"
				+ "Sugar         " + sugar + "g<br/>"
				+ "Price         " + '$' + price
				+ "</pre></html>";
	}

	@Override
	public Object[] getTableFormat() {
		return new Object[] { getName() + "", getAmount() + "mL", getCalories() + "", getCarbohydrates() + "g",
				getProteins() + "g", getFat() + "g", getSodium() + "mg", getSugar() + "g", "$" + getPrice() };
	}

}
