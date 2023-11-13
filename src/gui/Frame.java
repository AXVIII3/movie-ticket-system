package gui;

import java.util.ArrayList;
import javax.swing.JFrame;

public class Frame extends JFrame
{
    private final ArrayList<Panel> panels = new ArrayList<>();

	public void addNewPanel(Panel panel) { panels.add(panel); }

	public void openPanel(String _name)
	{
		for (Panel panel : panels)
		{
			if (panel.name.equals(_name)) add(panel);
			else if (!(panel.getParent() == null)) remove(panel);
		}

		revalidate();
		repaint();
	}
}
