package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class MenuManagerGui {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel foodItemsTableContainer;
	private JTable foodItemsTable;
	private JButton newMenuButton;
	private JButton saveMenuButton;
	private JButton newFoodItemButton;
	private JButton deleteFoodItemButton;
	private JPanel exitFoodItemsEditorButtonsContainer;
	private JButton saveAndCloseEditFoodItemsButton;
	private JButton cancelEditFoodItemsButton;
	private JPanel manageMenusPage;
	private JPanel toolsContainer;
	private JPanel menuContainer;
	private JScrollPane menuScrollPane;
	private JList<String> menuList;
	private JPanel menuDataContainer;
	private JButton openMenuButton;
	private JButton addFoodItemToMenuButton;
	private JButton removeFoodItemFromMenuButton;
	private JButton generateMenuButton;
	private JSeparator separator;
	private JPanel toolButtonsContainer;
	private JPanel settingsButtonContainer;
	private JButton settingsButton;
	private JButton switchToFoodItemsPageButton;
	private JPanel nutritionFactsPanel;
	private JLabel menuData;
	private JTextPane nutritionFactsTextPane;
	private JSeparator nutritionFactsSeparator;
	private JPanel editFoodItemsPage;
	private JButton editFoodItemButton;

	/**
	 * Create the frame.
	 */
	public MenuManagerGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initComponents();
		initGuiActions();
	}

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MenuManagerGui menuManagerGui = new MenuManagerGui();
				menuManagerGui.frame.setVisible(true);
			}
		});
	}

	private void initComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 0, 1070, 749);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		frame.setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		manageMenusPage = new JPanel();
		contentPane.add(manageMenusPage, "name_41319267217400");
		manageMenusPage.setLayout(new BorderLayout(0, 0));

		toolsContainer = new JPanel();
		toolsContainer.setBorder(null);
		toolsContainer.setPreferredSize(new Dimension(120, 10));
		toolsContainer.setBackground(new Color(51, 51, 51));
		manageMenusPage.add(toolsContainer, BorderLayout.WEST);
		toolsContainer.setLayout(new BorderLayout(0, 0));

		toolButtonsContainer = new JPanel();
		toolButtonsContainer.setBorder(new EmptyBorder(4, 0, 0, 0));
		toolsContainer.add(toolButtonsContainer, BorderLayout.CENTER);
		toolButtonsContainer.setOpaque(false);
		toolButtonsContainer.setPreferredSize(new Dimension(100, 430));

		newMenuButton = new JButton("New");
		newMenuButton.setPreferredSize(new Dimension(100, 100));
		newMenuButton.setOpaque(true);
		newMenuButton.setBorderPainted(false);
		newMenuButton.setBackground(new Color(122, 131, 153));
		toolButtonsContainer.add(newMenuButton);

		saveMenuButton = new JButton("Save");
		saveMenuButton.setPreferredSize(new Dimension(100, 100));
		saveMenuButton.setOpaque(true);
		saveMenuButton.setBorderPainted(false);
		saveMenuButton.setBackground(new Color(122, 131, 153));
		toolButtonsContainer.add(saveMenuButton);

		openMenuButton = new JButton("Open");
		openMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolButtonsContainer.add(openMenuButton);
		openMenuButton.setBackground(new Color(122, 131, 153));
		openMenuButton.setOpaque(true);
		openMenuButton.setBorderPainted(false);
		openMenuButton.setPreferredSize(new Dimension(100, 100));

		addFoodItemToMenuButton = new JButton("Add");
		addFoodItemToMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolButtonsContainer.add(addFoodItemToMenuButton);
		addFoodItemToMenuButton.setPreferredSize(new Dimension(100, 100));
		addFoodItemToMenuButton.setOpaque(true);
		addFoodItemToMenuButton.setBorderPainted(false);
		addFoodItemToMenuButton.setBackground(new Color(122, 131, 153));

		removeFoodItemFromMenuButton = new JButton("Remove");
		removeFoodItemFromMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolButtonsContainer.add(removeFoodItemFromMenuButton);
		removeFoodItemFromMenuButton.setPreferredSize(new Dimension(100, 100));
		removeFoodItemFromMenuButton.setOpaque(true);
		removeFoodItemFromMenuButton.setBorderPainted(false);
		removeFoodItemFromMenuButton.setBackground(new Color(122, 131, 153));

		separator = new JSeparator();
		toolButtonsContainer.add(separator);
		separator.setBackground(new Color(102, 102, 102));
		separator.setPreferredSize(new Dimension(100, 2));

		generateMenuButton = new JButton("Generate");
		generateMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolButtonsContainer.add(generateMenuButton);
		generateMenuButton.setPreferredSize(new Dimension(100, 100));
		generateMenuButton.setOpaque(true);
		generateMenuButton.setBorderPainted(false);
		generateMenuButton.setBackground(new Color(122, 131, 153));

		settingsButtonContainer = new JPanel();
		settingsButtonContainer.setPreferredSize(new Dimension(10, 60));
		toolsContainer.add(settingsButtonContainer, BorderLayout.SOUTH);
		settingsButtonContainer.setOpaque(false);

		switchToFoodItemsPageButton = new JButton("Edit Food Items");
		switchToFoodItemsPageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settingsButtonContainer.add(switchToFoodItemsPageButton);

		settingsButton = new JButton("Settings");
		settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settingsButton.setActionCommand("Settings");
		settingsButton.setPreferredSize(new Dimension(107, 23));
		settingsButtonContainer.add(settingsButton);

		menuContainer = new JPanel();
		manageMenusPage.add(menuContainer, BorderLayout.CENTER);
		GridBagLayout gbl_menuContainer = new GridBagLayout();
		gbl_menuContainer.columnWidths = new int[] { 522, 0, 0 };
		gbl_menuContainer.rowHeights = new int[] { 0, 0 };
		gbl_menuContainer.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_menuContainer.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		menuContainer.setLayout(gbl_menuContainer);

		menuScrollPane = new JScrollPane();
		GridBagConstraints gbc_menuScrollPane = new GridBagConstraints();
		gbc_menuScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_menuScrollPane.fill = GridBagConstraints.BOTH;
		gbc_menuScrollPane.gridx = 0;
		gbc_menuScrollPane.gridy = 0;
		menuContainer.add(menuScrollPane, gbc_menuScrollPane);

		menuList = new JList();
		menuList.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -5018073408623960956L;
			private MatteBorder matteOuter = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
			private Border emptyInner = BorderFactory.createEmptyBorder(8, 5, 6, 5);

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createCompoundBorder(matteOuter, emptyInner));
				return listCellRendererComponent;
			}
		});
		menuScrollPane.setViewportView(menuList);

		menuDataContainer = new JPanel();
		menuDataContainer.setBorder(new EmptyBorder(10, 0, 0, 0));
		GridBagConstraints gbc_menuDataContainer = new GridBagConstraints();
		gbc_menuDataContainer.fill = GridBagConstraints.BOTH;
		gbc_menuDataContainer.gridx = 1;
		gbc_menuDataContainer.gridy = 0;
		menuContainer.add(menuDataContainer, gbc_menuDataContainer);

		nutritionFactsPanel = new JPanel();
		nutritionFactsPanel.setBorder(new LineBorder(Color.BLACK, 2));
		nutritionFactsPanel.setBackground(Color.WHITE);
		menuDataContainer.add(nutritionFactsPanel);
		nutritionFactsPanel.setLayout(new BoxLayout(nutritionFactsPanel, BoxLayout.Y_AXIS));

		menuData = new JLabel("Menu Data");
		menuData.setVerticalAlignment(SwingConstants.TOP);
		menuData.setVerticalTextPosition(SwingConstants.TOP);
		menuData.setHorizontalAlignment(SwingConstants.LEFT);
		menuData.setHorizontalTextPosition(SwingConstants.LEFT);
		menuData.setBorder(new EmptyBorder(5, 0, 5, 0));
		menuData.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
		nutritionFactsPanel.add(menuData);

		nutritionFactsSeparator = new JSeparator();
		nutritionFactsSeparator.setBorder(new LineBorder(new Color(0, 0, 0)));
		nutritionFactsSeparator.setBackground(Color.BLACK);
		nutritionFactsPanel.add(nutritionFactsSeparator);

		nutritionFactsTextPane = new JTextPane();
		nutritionFactsTextPane.setFont(new Font("Courier New", Font.PLAIN, 12));
		nutritionFactsTextPane.setEditable(false);
		nutritionFactsTextPane.setPreferredSize(new Dimension(360, 600));
		nutritionFactsTextPane.setContentType("text/html");
		nutritionFactsPanel.add(nutritionFactsTextPane);

		editFoodItemsPage = new JPanel();
		contentPane.add(editFoodItemsPage, "name_40737169866500");
		editFoodItemsPage.setLayout(new BorderLayout(0, 0));

		foodItemsTableContainer = new JPanel();
		foodItemsTableContainer.setBorder(new EmptyBorder(10, 10, 5, 0));
		editFoodItemsPage.add(foodItemsTableContainer, BorderLayout.CENTER);
		foodItemsTableContainer.setLayout(new BorderLayout(0, 0));

		foodItemsTable = new JTable();
		foodItemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel editFoodItemsButtonsContainer = new JPanel();
		FlowLayout flowLayout = (FlowLayout) editFoodItemsButtonsContainer.getLayout();
		flowLayout.setVgap(8);
		editFoodItemsButtonsContainer.setBorder(new EmptyBorder(50, 0, 50, 0));
		editFoodItemsButtonsContainer.setPreferredSize(new Dimension(120, 10));
		editFoodItemsPage.add(editFoodItemsButtonsContainer, BorderLayout.EAST);

		newFoodItemButton = new JButton("New");
		newFoodItemButton.setPreferredSize(new Dimension(100, 30));
		editFoodItemsButtonsContainer.add(newFoodItemButton);

		editFoodItemButton = new JButton("Edit");
		editFoodItemButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editFoodItemButton.setPreferredSize(new Dimension(100, 30));
		editFoodItemsButtonsContainer.add(editFoodItemButton);

		deleteFoodItemButton = new JButton("Delete");
		deleteFoodItemButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteFoodItemButton.setPreferredSize(new Dimension(100, 30));
		editFoodItemsButtonsContainer.add(deleteFoodItemButton);

		exitFoodItemsEditorButtonsContainer = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) exitFoodItemsEditorButtonsContainer.getLayout();
		flowLayout_1.setHgap(8);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		exitFoodItemsEditorButtonsContainer.setPreferredSize(new Dimension(10, 40));
		editFoodItemsPage.add(exitFoodItemsEditorButtonsContainer, BorderLayout.SOUTH);

		saveAndCloseEditFoodItemsButton = new JButton("Save And Close");
		saveAndCloseEditFoodItemsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveAndCloseEditFoodItemsButton.setPreferredSize(new Dimension(150, 30));
		exitFoodItemsEditorButtonsContainer.add(saveAndCloseEditFoodItemsButton);

		cancelEditFoodItemsButton = new JButton("Cancel");
		cancelEditFoodItemsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelEditFoodItemsButton.setPreferredSize(new Dimension(150, 30));
		exitFoodItemsEditorButtonsContainer.add(cancelEditFoodItemsButton);
	}

	private void initGuiActions() {
		GuiLogicHandler guiLogicHandler = new GuiLogicHandler();
		guiLogicHandler.addToolButtonActionListeners(newMenuButton, saveMenuButton, openMenuButton, addFoodItemToMenuButton, removeFoodItemFromMenuButton, menuList, nutritionFactsTextPane);
		guiLogicHandler.addGenerateMenuButtonActionListeners(generateMenuButton);
		guiLogicHandler.addSettingsButtonActionListeners(settingsButton);
		guiLogicHandler.addEditFoodItemsButtonActionListeners(switchToFoodItemsPageButton, contentPane);
		foodItemsTable = guiLogicHandler.makeFoodItemsTableActionListeners();
		foodItemsTableContainer.add(new JScrollPane(foodItemsTable), BorderLayout.CENTER);
		guiLogicHandler.addFoodItemTableEditButtonsActionListeners(newFoodItemButton, editFoodItemButton, deleteFoodItemButton, foodItemsTable);
		guiLogicHandler.addExitEditFoodItemsPageActionListeners(saveAndCloseEditFoodItemsButton, cancelEditFoodItemsButton, foodItemsTable, contentPane);
	}

}
