package GUI.Screens;

import Utilities.Visuals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PasswordField extends JPasswordField
{
    public PasswordField()
    {
        super();
        setBackground(Visuals.Colors.INTERACTABLE_DARKER);
        setFont(Visuals.Fonts.NORMAL_FONT);
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBorder(BorderFactory.createCompoundBorder(
                Visuals.Borders.INTERACTABLE_BORDER,
                new EmptyBorder(new Insets(0, 10, 0, 10))
        ));
        setPreferredSize(new Dimension(300, 35));
    }
}
