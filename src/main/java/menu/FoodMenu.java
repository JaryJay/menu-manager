package menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import fooditem.FoodItem;

public class FoodMenu {

	private String name;
	private List<FoodItem> foodItems;

	public FoodMenu(String name) {
		this.name = name;
		foodItems = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int size() {
		return foodItems.size();
	}

	public boolean isEmpty() {
		return foodItems.isEmpty();
	}

	public boolean contains(Object o) {
		return foodItems.contains(o);
	}

	public Object[] toArray() {
		return foodItems.toArray();
	}

	public boolean add(FoodItem e) {
		return foodItems.add(e);
	}

	public boolean addAll(Collection<? extends FoodItem> c) {
		return foodItems.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends FoodItem> c) {
		return foodItems.addAll(index, c);
	}

	public FoodItem get(int index) {
		return foodItems.get(index);
	}

	public FoodItem set(int index, FoodItem element) {
		return foodItems.set(index, element);
	}

	public void add(int index, FoodItem element) {
		foodItems.add(index, element);
	}

	public FoodItem remove(int index) {
		return foodItems.remove(index);
	}

	public Stream<FoodItem> stream() {
		return foodItems.stream();
	}

	@Override
	public String toString() {
		return "Menu name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + name + "<br/>"
				+ "Amount&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalAmount() + "g<br/>"
				+ "Calories&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalCalories() + "g<br/>"
				+ "Carbohydrates&nbsp;" + totalCarbohydrates() + "g<br/>"
				+ "Proteins&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalProteins() + "g<br/>"
				+ "Fat&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalFat() + "g<br/>"
				+ "Sodium&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalSodium() + "mg<br/>"
				+ "Sugar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + totalSugar() + "g<br/>"
				+ "Price&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + '$' + totalPrice();
	}

	public float totalAmount() {
		return (float) stream().mapToDouble(fi -> fi.getAmount()).sum();
	}

	private float totalCalories() {
		return (float) stream().mapToDouble(fi -> fi.getCalories()).sum();
	}

	private float totalCarbohydrates() {
		return (float) stream().mapToDouble(fi -> fi.getCarbohydrates()).sum();
	}

	private float totalProteins() {
		return (float) stream().mapToDouble(fi -> fi.getProteins()).sum();
	}

	private float totalFat() {
		return (float) stream().mapToDouble(fi -> fi.getFat()).sum();
	}

	private float totalSodium() {
		return (float) stream().mapToDouble(fi -> fi.getSodium()).sum();
	}

	private float totalSugar() {
		return (float) stream().mapToDouble(fi -> fi.getSugar()).sum();
	}

	private float totalPrice() {
		return (float) stream().mapToDouble(fi -> fi.getPrice()).sum();
	}

}
