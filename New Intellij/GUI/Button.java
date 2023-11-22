package GUI;

import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    private Color pressedBackgroundColor;

	public Button(String text)
	{
		super(text);
		setFocusPainted(false);
	    setFocusable(false);
	    setRolloverEnabled(false);
        setContentAreaFilled(false);
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBackground(Visuals.Colors.INTERACTABLE_NORMAL);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_INTERACTED);
        setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        setFont(Visuals.Fonts.NORMAL_FONT);
	}
    public Button(String text, Dimension dimensions)
    {
        super(text);
		setFocusPainted(false);
	    setFocusable(false);
	    setRolloverEnabled(false);
        setContentAreaFilled(false);
        setPreferredSize(dimensions);
        setMaximumSize(dimensions);
        setMinimumSize(dimensions);
        setSize(dimensions);
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBackground(Visuals.Colors.INTERACTABLE_NORMAL);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_INTERACTED);
        setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        setFont(Visuals.Fonts.NORMAL_FONT);
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    { this.pressedBackgroundColor = pressedBackgroundColor; }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed()) g.setColor(pressedBackgroundColor);
        else g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
