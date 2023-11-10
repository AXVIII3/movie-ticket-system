package gui;

import java.util.ArrayList;
import javax.swing.JFrame;

public class Frame extends JFrame
{
    private final ArrayList<Panel> panels = new ArrayList<>(0);

	public Frame()
	{
		super();
	}

	public void addNewPanel(Panel panel)
	{
		panels.ensureCapacity(panels.size() + 1);
		panels.add(panel);
	}

	public void openPanel(String _name)
	{
		for (Panel panel : panels)
		{
			System.out.println(panel.name + " " + panel.name.equals(_name));
			if (panel.name.equals(_name))
			{
				add(panel);
				revalidate();
				repaint();
				panel.revalidate();
				panel.repaint();
			}
			else
			{
				if (!(panel.getParent() == null)) remove(panel);
				revalidate();
				repaint();
			}
		}
	}
}
