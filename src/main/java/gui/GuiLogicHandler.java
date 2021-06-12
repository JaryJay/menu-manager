package gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import fooditem.FoodItem;
import fooditem.FoodItemLoader;
import fooditem.LiquidFoodItem;
import fooditem.SolidFoodItem;
import menu.FoodMenu;
import menu.FoodMenuGenerator;
import menu.FoodMenuLoader;

public class GuiLogicHandler {

	private FoodMenu currentSelected;
	private JList<String> menuList;
	private JTextPane nutritionFactsTextPane;
	private Object[] columnNames = { "Name", "Amount", "Calories", "Carbohydrates", "Proteins", "Fat", "Sodium", "Sugar", "Price" };

	public void addToolButtonActionListeners(JButton newMenuButton, JButton saveMenuButton, JButton openMenuButton,
			JButton addFoodItemToMenuButton, JButton removeFoodItemFromMenuButton,
			JList<String> menuList, JTextPane nutritionFactsTextPane) {

		this.menuList = menuList;
		this.nutritionFactsTextPane = nutritionFactsTextPane;
		newMenuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(newMenuButton, "Menu name", "Untitled");
				if (name != null) {
					while (name.isEmpty() || FoodMenuLoader.instance().foodMenuExists(name)) {
						name = JOptionPane.showInputDialog(newMenuButton, "Menu name\nInvalid name or menu already exists", name);
						if (name == null) {
							return;
						}
					}
					saveCurrentSelected();
					currentSelected = new FoodMenu(name);
					updateMenuList();
				}
			}
		});
		saveMenuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FoodMenuLoader instance = FoodMenuLoader.instance();
				if (currentSelected != null) {
					instance.saveFoodMenu(currentSelected);
					String message = "Saved to " + instance.getMenuSaveLocation() + currentSelected.getName() + ".txt";
					JOptionPane.showMessageDialog(saveMenuButton, message, "Info", INFORMATION_MESSAGE);
				}
			}
		});
		openMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser(FoodMenuLoader.instance().getMenuSaveLocation());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				jf.setFileFilter(filter);
				int result = jf.showOpenDialog(openMenuButton);
				if (result == JFileChooser.APPROVE_OPTION) {
					saveCurrentSelected();
					currentSelected = FoodMenuLoader.instance().loadFoodMenu(jf.getSelectedFile().getAbsolutePath());
					updateMenuList();
				}
			}
		});
		addFoodItemToMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (currentSelected != null) {
					Object[] array = FoodItemLoader.instance().getFoodItemsBuffer().stream().map(fi -> fi.getName()).toArray();
					JComboBox<Object> foodItemChooser = new JComboBox<>(array);
					ComboPopup popup = (ComboPopup) foodItemChooser.getUI().getAccessibleChild(foodItemChooser, 0);

					int width = (int) ((JComponent) popup).getPreferredSize().getWidth();
					((JComponent) popup).setPreferredSize(new Dimension(width + 4, 400));
					((JComponent) popup).setLayout(new GridLayout(1, 1));
					Object[] message = { "Add a food item from the list.", foodItemChooser };
					int option = JOptionPane.showConfirmDialog(addFoodItemToMenuButton, message, "Add a food item to the menu", OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						int selectedIndex = Math.max(menuList.getSelectedIndex(), 0);
						FoodItem selectedFoodItem = FoodItemLoader.instance().getFoodItemsBuffer().get(foodItemChooser.getSelectedIndex());
						if (currentSelected.stream().noneMatch(fi -> {
							return fi.getName().equals(selectedFoodItem.getName());
						})) {
							currentSelected.add(selectedIndex, selectedFoodItem);
							updateMenuList();
						} else {
							JOptionPane.showMessageDialog(addFoodItemToMenuButton, "Food item already in menu.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		removeFoodItemFromMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (currentSelected != null) {
					int selectedIndex = menuList.getSelectedIndex();
					if (selectedIndex != -1) {
						currentSelected.remove(selectedIndex);
						updateMenuList();
					} else {
						JOptionPane.showMessageDialog(removeFoodItemFromMenuButton, "No selected food item in menu.", "Error", ERROR_MESSAGE);
					}
				}
			}
		});
		new MenuListListenerHandler().addMouseListeners(menuList);
	}

	public void addGenerateMenuButtonActionListeners(JButton generateMenuButton) {
		generateMenuButton.addActionListener(new ActionListener() {

			private FoodMenuGenerator foodmenuGenerator = new FoodMenuGenerator();

			@Override
			public void actionPerformed(ActionEvent e) {
				saveCurrentSelected();
				currentSelected = foodmenuGenerator.generateFoodMenu();
				updateMenuList();
			}

		});
	}

	public void addSettingsButtonActionListeners(JButton settingsButton) {
		Object[] message = {};
		int c = JOptionPane.showConfirmDialog(settingsButton, message, "Settings", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
		if (c == JOptionPane.OK_OPTION) {

		}
	}

	public void addEditFoodItemsButtonActionListeners(JButton switchToFoodItemsPageButton, JPanel contentPane) {
		switchToFoodItemsPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.next(contentPane);

			}
		});
	}

	private void updateMenuList() {
		menuList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = -5194169193071166453L;

			@Override
			public int getSize() {
				return currentSelected.size();
			}

			@Override
			public String getElementAt(int index) {
				return currentSelected.get(index).getName();
			}
		});
		nutritionFactsTextPane.setText("<font face=\"courier\" size = \"+0\">" + currentSelected.toString() + "</font>");
	}

	public JTable makeFoodItemsTableActionListeners() {
		List<FoodItem> foodItemsBuffer = FoodItemLoader.instance().getFoodItemsBuffer();
		Object[][] foodItemsData = new Object[foodItemsBuffer.size()][];
		for (int i = 0; i < foodItemsBuffer.size(); i++) {
			FoodItem foodItem = foodItemsBuffer.get(i);
			foodItemsData[i] = foodItem.getTableFormat();
		}
		JTable foodItemsTable = new JTable(new DefaultTableModel(foodItemsData, columnNames));
		foodItemsTable.getColumnModel().getColumn(0).setPreferredWidth(300);
		foodItemsTable.setFont(new Font("Arial", Font.PLAIN, 12));
		foodItemsTable.setRowHeight(24);
		foodItemsTable.setRowMargin(5);
		foodItemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foodItemsTable.setDefaultEditor(Object.class, null); // Make table non-editable
		return foodItemsTable;
	}

	public void addFoodItemTableEditButtonsActionListeners(JButton newFoodItemButton, JButton editFoodItemButton,
			JButton deleteFoodItemButton, JTable foodItemsTable) {
		newFoodItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<FoodItem> foodItemsBuffer = FoodItemLoader.instance().getFoodItemsBuffer();
				JTextField nameTextField = new JTextField();
				JComboBox<String> typeC = new JComboBox<>(new String[] { "Solid (g)", "Liquid (mL)" });
				JSpinner amountS = disallowInvalidJspinner();
				JSpinner caloriesS = disallowInvalidJspinner();
				JSpinner carbohydratesS = disallowInvalidJspinner();
				JSpinner proteinsS = disallowInvalidJspinner();
				JSpinner fatS = disallowInvalidJspinner();
				JSpinner sodiumS = disallowInvalidJspinner();
				JSpinner sugarS = disallowInvalidJspinner();
				JSpinner priceS = disallowInvalidJspinner();
				Object[] message = {
						"",
						"Name:", nameTextField,
						"Type:", typeC,
						"Amount:", amountS,
						"Calories:", caloriesS,
						"Carbohydrates:", carbohydratesS,
						"Proteins:", proteinsS,
						"Fat:", fatS,
						"Sodium:", sodiumS,
						"Sugar:", sugarS,
						"Price:", priceS };
				boolean valid = true;
				do {
					valid = true;
					int option = JOptionPane.showConfirmDialog(newFoodItemButton, message, "New Food Item", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						String name = nameTextField.getText();
						if (name == null || name.isEmpty()) {
							message[0] = "Invalid name - Name cannot be empty";
							valid = false;
							continue;
						} else if (foodItemsBuffer.stream().anyMatch(fi -> fi.getName().equals(name))) {
							message[0] = "Invalid name - A food item with that name already exists";
							valid = false;
							continue;
						}
						boolean solid = typeC.getSelectedIndex() == 0;
						double amount = ((Number) amountS.getValue()).doubleValue();
						double calories = ((Number) caloriesS.getValue()).doubleValue();
						double carbohydrates = ((Number) carbohydratesS.getValue()).doubleValue();
						double proteins = ((Number) proteinsS.getValue()).doubleValue();
						double fat = ((Number) fatS.getValue()).doubleValue();
						double sodium = ((Number) sodiumS.getValue()).doubleValue();
						double sugar = ((Number) sugarS.getValue()).doubleValue();
						double price = ((Number) priceS.getValue()).doubleValue();
						int selectedIndex = Math.max(foodItemsTable.getSelectedRow(), 0);
						if (solid) {
							foodItemsBuffer.add(selectedIndex, new SolidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price));
						} else {
							foodItemsBuffer.add(selectedIndex, new LiquidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price));
						}

						DefaultTableModel model = (DefaultTableModel) foodItemsTable.getModel();
						Object[][] foodItemsData = new Object[foodItemsBuffer.size()][];
						for (int i = 0; i < foodItemsBuffer.size(); i++) {
							FoodItem foodItem = foodItemsBuffer.get(i);
							foodItemsData[i] = foodItem.getTableFormat();
						}
						Object[] columnNames = { "Name", "Amount", "Calories", "Carbohydrates", "Proteins", "Fat", "Sodium", "Sugar", "Price" };
						model.setDataVector(foodItemsData, columnNames);
					} else {
						return;
					}
				} while (!valid);
			}

		});
		editFoodItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = foodItemsTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(deleteFoodItemButton, "No food item selected.", "Error", ERROR_MESSAGE);
					return;
				}
				List<FoodItem> foodItemsBuffer = FoodItemLoader.instance().getFoodItemsBuffer();
				FoodItem fi = foodItemsBuffer.get(selectedRow);
				JTextField nameTextField = new JTextField(fi.getName());
				JComboBox<String> typeC = new JComboBox<>(new String[] { "Solid (g)", "Liquid (mL)" });
				typeC.setSelectedIndex(fi instanceof SolidFoodItem ? 0 : 1);
				JSpinner amountS = disallowInvalidJspinner(fi.getAmount());
				JSpinner caloriesS = disallowInvalidJspinner(fi.getCalories());
				JSpinner carbohydratesS = disallowInvalidJspinner(fi.getCarbohydrates());
				JSpinner proteinsS = disallowInvalidJspinner(fi.getProteins());
				JSpinner fatS = disallowInvalidJspinner(fi.getFat());
				JSpinner sodiumS = disallowInvalidJspinner(fi.getSodium());
				JSpinner sugarS = disallowInvalidJspinner(fi.getSugar());
				JSpinner priceS = disallowInvalidJspinner(fi.getPrice());
				Object[] message = {
						"",
						"Name:", nameTextField,
						"Type:", typeC,
						"Amount:", amountS,
						"Calories:", caloriesS,
						"Carbohydrates:", carbohydratesS,
						"Proteins:", proteinsS,
						"Fat:", fatS,
						"Sodium:", sodiumS,
						"Sugar:", sugarS,
						"Price:", priceS };
				boolean valid = true;
				do {
					valid = true;
					int option = JOptionPane.showConfirmDialog(newFoodItemButton, message, "New Food Item", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						String name = nameTextField.getText();
						if (name == null || name.isEmpty()) {
							message[0] = "Invalid name - Name cannot be empty";
							valid = false;
							continue;
						} else if (foodItemsBuffer.stream().filter(f -> f != fi).anyMatch(f -> f.getName().equals(name))) {
							message[0] = "Invalid name - A food item with that name already exists";
							valid = false;
							continue;
						}
						boolean solid = typeC.getSelectedIndex() == 0;
						double amount = ((Number) amountS.getValue()).doubleValue();
						double calories = ((Number) caloriesS.getValue()).doubleValue();
						double carbohydrates = ((Number) carbohydratesS.getValue()).doubleValue();
						double proteins = ((Number) proteinsS.getValue()).doubleValue();
						double fat = ((Number) fatS.getValue()).doubleValue();
						double sodium = ((Number) sodiumS.getValue()).doubleValue();
						double sugar = ((Number) sugarS.getValue()).doubleValue();
						double price = ((Number) priceS.getValue()).doubleValue();
						int selectedIndex = Math.max(foodItemsTable.getSelectedRow(), 0);
						if (solid) {
							foodItemsBuffer.set(selectedIndex, new SolidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price));
						} else {
							foodItemsBuffer.set(selectedIndex, new LiquidFoodItem(name, amount, calories, carbohydrates, proteins, fat, sodium, sugar, price));
						}

						DefaultTableModel model = (DefaultTableModel) foodItemsTable.getModel();
						Object[][] foodItemsData = new Object[foodItemsBuffer.size()][];
						for (int i = 0; i < foodItemsBuffer.size(); i++) {
							FoodItem foodItem = foodItemsBuffer.get(i);
							foodItemsData[i] = foodItem.getTableFormat();
						}
						Object[] columnNames = { "Name", "Amount", "Calories", "Carbohydrates", "Proteins", "Fat", "Sodium", "Sugar", "Price" };
						model.setDataVector(foodItemsData, columnNames);
					} else {
						return;
					}
				} while (!valid);
			}
		});
		deleteFoodItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = foodItemsTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(deleteFoodItemButton, "No food item selected.", "Error", ERROR_MESSAGE);
					return;
				}
				int c = JOptionPane.showConfirmDialog(deleteFoodItemButton, "Are you sure you want to delete this food item?"
						+ "\nThis action cannot be reverted.", "Warning", YES_NO_OPTION, WARNING_MESSAGE);
				if (c == YES_OPTION) {
					List<FoodItem> foodItemsBuffer = FoodItemLoader.instance().getFoodItemsBuffer();
					foodItemsBuffer.remove(selectedRow);
					DefaultTableModel model = (DefaultTableModel) foodItemsTable.getModel();
					Object[][] foodItemsData = new Object[foodItemsBuffer.size()][];
					for (int i = 0; i < foodItemsBuffer.size(); i++) {
						FoodItem foodItem = foodItemsBuffer.get(i);
						foodItemsData[i] = foodItem.getTableFormat();
					}
					model.setDataVector(foodItemsData, columnNames);
				}
			}
		});
	}

	public void addExitEditFoodItemsPageActionListeners(JButton saveAndCloseEditFoodItemsButton, JButton cancelEditFoodItemsButton,
			JTable foodItemsTable, JPanel contentPane) {
		saveAndCloseEditFoodItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) contentPane.getLayout();
				layout.next(contentPane);
				FoodItemLoader.instance().saveFoodItems();
			}

		});
		cancelEditFoodItemsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FoodItemLoader.instance().noChangesExist()) {
					CardLayout layout = (CardLayout) contentPane.getLayout();
					layout.next(contentPane);
					return;
				}
				int c = JOptionPane.showConfirmDialog(cancelEditFoodItemsButton, "You have unsaved changes."
						+ "\nAre you sure you want to discard your changes?", "Warning", YES_NO_OPTION, WARNING_MESSAGE);
				if (c == YES_OPTION) {
					CardLayout layout = (CardLayout) contentPane.getLayout();
					layout.next(contentPane);
					FoodItemLoader.instance().revertChanges();
					List<FoodItem> foodItemsBuffer = FoodItemLoader.instance().getFoodItemsBuffer();
					DefaultTableModel model = (DefaultTableModel) foodItemsTable.getModel();
					Object[][] foodItemsData = new Object[foodItemsBuffer.size()][];
					for (int i = 0; i < foodItemsBuffer.size(); i++) {
						FoodItem foodItem = foodItemsBuffer.get(i);
						foodItemsData[i] = foodItem.getTableFormat();
					}
					model.setDataVector(foodItemsData, columnNames);
				}
			}
		});
	}

	private JSpinner disallowInvalidJspinner() {
		return disallowInvalidJspinner(0);
	}

	private JSpinner disallowInvalidJspinner(double initialValue) {
		SpinnerNumberModel model = new SpinnerNumberModel(initialValue, 0, Integer.MAX_VALUE, 1);
		JSpinner amount = new JSpinner(model);
		((NumberFormatter) ((JSpinner.NumberEditor) amount.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
		return amount;
	}

	private void saveCurrentSelected() {
		if (currentSelected != null) {
			FoodMenuLoader.instance().saveFoodMenu(currentSelected);
		}
	}

}
