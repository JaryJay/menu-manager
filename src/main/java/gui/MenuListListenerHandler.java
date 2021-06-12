package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JList;
import javax.swing.ListModel;

public class MenuListListenerHandler {

	public void addMouseListeners(JList<String> menuList) {
		menuList.addMouseMotionListener(new MouseMotionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseMoved(MouseEvent e) {
				JList<String> l = (JList<String>) e.getSource();
				ListModel<String> m = l.getModel();
				int index = l.locationToIndex(e.getPoint());
				if (index > -1) {
					l.setToolTipText(m.getElementAt(index).toString());
				}
			}
		});
	}

}
