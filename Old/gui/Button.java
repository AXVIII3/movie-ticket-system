package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

public class Button extends JButton
{
    private Color pressedBackgroundColor;

    public Button()
    {
        super();
		setFocusPainted(false);
	    setFocusable(false);
	    setRolloverEnabled(false);
        setContentAreaFilled(false);
    }
	public Button(String text)
	{
		super(text);
		setFocusPainted(false);
	    setFocusable(false);
	    setRolloverEnabled(false);
        setContentAreaFilled(false);
	}

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed()) g.setColor(pressedBackgroundColor);
        else g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    { this.pressedBackgroundColor = pressedBackgroundColor; }
}
