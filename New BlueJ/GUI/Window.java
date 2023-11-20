package GUI;

import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame
{
    private final ArrayList<Screen> screens = new ArrayList<>();

    public Window()
    {
        super();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon icon = new ImageIcon("Assets/Branding/Icon.png");

        setSize(screenSize.width + 10, screenSize.height + 3);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setResizable(false);
        setTitle("Movie Ticket System ~ P.B.A.S.");
        setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
        setIconImage(icon.getImage());
    }

    public void addScreen(Screen screen)
    {
        super.add(screen);
        screens.add(screen);
    }

    public void openScreen(String name)
    {
        for (Screen screen : screens)
        {
            if (screen.getName().equals(name))
            {
                add(screen);
                screen.revalidate();
                screen.repaint();
            }
            else remove(screen);
        }

        revalidate();
        repaint();
    }
}
