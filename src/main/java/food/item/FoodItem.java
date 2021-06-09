package food.item;

public class FoodItem {

	protected String name;
	protected float amount;
	protected float calories;
	protected float carbohydrates;
	protected float proteins;
	protected float fat;
	protected float sodium;
	protected float sugar;
	protected float price;

	public FoodItem(String name, float amount, float calories, float carbohydrates, float proteins, float fat, float sodium, float sugar, float price) {
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public float getProteins() {
		return proteins;
	}

	public void setProteins(float proteins) {
		this.proteins = proteins;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getSugar() {
		return sugar;
	}

	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
