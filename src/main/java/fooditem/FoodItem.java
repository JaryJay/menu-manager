package fooditem;

public abstract class FoodItem {

	protected String name;
	protected double amount;
	protected double calories;
	protected double carbohydrates;
	protected double proteins;
	protected double fat;
	protected double sodium;
	protected double sugar;
	protected double price;

	public FoodItem(String name, double amount, double calories, double carbohydrates, double proteins, double fat, double sodium, double sugar, double price) {
		this.name = name;
		this.amount = amount;
		this.calories = calories;
		this.carbohydrates = carbohydrates;
		this.proteins = proteins;
		this.fat = fat;
		this.sodium = sodium;
		this.sugar = sugar;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public abstract Object[] getTableFormat();

}
