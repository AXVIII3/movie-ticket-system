package GUI.Dialogs;

import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class AuthenticateDialog extends JDialog
{
    public AuthenticateDialog(Window window)
    {
        super(window, "Authentication");

        // Components
        JTextField nameTextField = new JTextField();
        JTextField emailTextField = new JTextField();
        JTextField TextField = new JTextField();

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();

        // Configurations
        setLayout(mainLayout);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setBackground(Visuals.Colors.GRADIENT_1);

        setVisible(true);
    }
}
