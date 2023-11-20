package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

import javax.swing.JPanel;

public class Panel extends JPanel
{
    public String name;

	private Color color1 = Color.white;
	private Color color2 = Color.white;
	private int gradientAngle;

	public Panel() { super(); }
	public Panel(String name)
	{
		super();
		this.name = name;
	}

	public void setGradient(Color _color1, Color _color2, int _gradientAngle)
	{
		color1 = _color1;
		color2 = _color2;
		gradientAngle = _gradientAngle;
	}

	@Override
    public void setBackground(Color color)
    {
    	color1 = color;
    	color2 = color;
    }

	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

		if (!isOpaque()) return;

        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        int[] points = getGradientPoints(w, h);
        GradientPaint gp = new GradientPaint(points[0], points[1], color1, points[2], points[3], color2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    private int[] getGradientPoints(double w, double h)
    {
    	int[] points = {0, (int) h, 0, 0};
    	double angle = Math.toRadians(gradientAngle);
    	double diagonalAngle = Math.atan(h / w);

    	if (angle >= 0 && angle < diagonalAngle)
    	{
    		double _h = h - (Math.tan(angle) * w);

    		points[2] = (int) w;
    		points[3] = (int) Math.min(Math.max(0, _h), h);
    	}
    	else if (angle >= diagonalAngle && (2 * angle) <= Math.PI)
    	{
    		double _w = Math.tan(0.5 * Math.PI - angle) * h;

    		points[2] = (int) Math.min(Math.max(0, _w), w);
        }


        return points;
    }
}
