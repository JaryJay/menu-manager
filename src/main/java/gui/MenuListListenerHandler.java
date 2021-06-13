package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ToolTipManager;

import fooditem.FoodItemLoader;

public class MenuListListenerHandler {

	public void addMouseListeners(JList<String> menuList) {
		ToolTipManager.sharedInstance().setDismissDelay(1000000);
		menuList.addMouseMotionListener(new MouseMotionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseMoved(MouseEvent e) {
				JList<String> l = (JList<String>) e.getSource();
				ListModel<String> m = l.getModel();
				int index = l.locationToIndex(e.getPoint());
				if (index > -1) {
//					l.setToolTipText(m.getElementAt(index).toString());
					l.setToolTipText(FoodItemLoader.instance().getByName(m.getElementAt(index)).toString());
				}
			}
		});
	}

}
