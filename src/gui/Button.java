package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

public class Button extends JButton
{
    private Color pressedBackgroundColor;

	public Button(String text)
	{
		super(text);
		super.setFocusPainted(false);
	    super.setFocusable(false);
	    super.setRolloverEnabled(false);
        super.setContentAreaFilled(false);
	}

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed()) g.setColor(pressedBackgroundColor);
        else g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

//    @Override
//    public void setContentAreaFilled(boolean b)  {}

    public void setPressedBackgroundColor(Color pressedBackgroundColor) { this.pressedBackgroundColor = pressedBackgroundColor; }
}
