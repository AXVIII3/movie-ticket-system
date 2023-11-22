package GUI.Dialogs;

import GUI.GridBagSettings;
import GUI.Label;
import GUI.TextField;
import GUI.Button;
import GUI.Window;
import Managers.AccountsManager;
import Managers.GuiAppManager;
import Utilities.Movie;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class AuthenticateDialog extends JDialog
{
    public AuthenticateDialog(Window window, Movie movie)
    {
        super(window, "Authentication");

        // Components
        JPanel mainPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel registerPanel = new JPanel();
        Label mainLabel = new Label("Authenticate:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        Label loginLabel = new Label("Login:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        Label loginNotifyLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label registerLabel = new Label("Register:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        Label registerNotifyLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label nameLabel = new Label("Name:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label emailLabel = new Label("E-mail:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label passwordLabel = new Label("Password:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label confirmPasswordLabel = new Label("Confirm Password:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Button authenticateButton = new Button("Authenticate", new Dimension(200, 35));
        Button registerButton = new Button("Register", new Dimension(200, 35));
        Button loginButton = new Button("Login", new Dimension(200, 35));
        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField passwordTextField = new TextField();
        TextField confirmPasswordTextField = new TextField();
        AtomicBoolean isAuthenticated = new AtomicBoolean(false);

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Configurations
        setSize(400, 240);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Visuals.Colors.DIALOG_BACKGROUND);
        addWindowListener(new WindowListener()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (!isAuthenticated.get()) window.openScreen(movie.name);
            }
            @Override
            public void windowClosed(WindowEvent e)
            {
                if (!isAuthenticated.get()) window.openScreen(movie.name);;
            }

            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        mainPanel.setLayout(layout);
        mainPanel.setOpaque(false);
        loginPanel.setLayout(layout);
        loginPanel.setOpaque(false);
        registerPanel.setLayout(layout);
        registerPanel.setOpaque(false);

        authenticateButton.addActionListener(e -> {
            if (!AccountsManager.ValidateEmail(emailTextField.getText()))
                emailTextField.setText("Please enter a valid E-mail!");
            else
            {
                remove(mainPanel);
                if (AccountsManager.IsAccountRegistered(emailTextField.getText()))
                {
                    setLocation(getLocation().x, getLocation().y - 20);
                    setSize(400, 307);
                    add(loginPanel);
                    loginPanel.add(emailLabel, new GridBagSettings(0, 2, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                    loginPanel.add(emailTextField, new GridBagSettings(0, 3, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                    loginPanel.add(passwordLabel, new GridBagSettings(0, 4, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                    loginPanel.add(passwordTextField, new GridBagSettings(0, 5, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 27, 10)));
                }
                else
                {
                    setLocation(getLocation().x, getLocation().y - 75);
                    setSize(400, 430);
                    add(registerPanel);
                    registerPanel.add(emailLabel, new GridBagSettings(0, 4, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                    registerPanel.add(emailTextField, new GridBagSettings(0, 5, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                    registerPanel.add(passwordLabel, new GridBagSettings(0, 6, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                    registerPanel.add(passwordTextField, new GridBagSettings(0, 7, 1, 0,
                            GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                }
                revalidate();
                repaint();
            }
        });
        registerButton.addActionListener(e -> {
            if (!AccountsManager.ValidateEmail(emailTextField.getText()))
                emailTextField.setText("Please enter a valid E-mail");
            if (AccountsManager.IsAccountRegistered(emailTextField.getText()))
            {
                remove(registerPanel);
                loginNotifyLabel.setText("Account with email already exists! Login Instead");
                setLocation(getLocation().x, getLocation().y + 50);
                setSize(400, 320);
                add(loginPanel);
                loginPanel.add(emailLabel, new GridBagSettings(0, 2, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                loginPanel.add(emailTextField, new GridBagSettings(0, 3, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                loginPanel.add(passwordLabel, new GridBagSettings(0, 4, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                loginPanel.add(passwordTextField, new GridBagSettings(0, 5, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 27, 10)));
                revalidate();
                repaint();
            }
            else
            {
                if (nameTextField.getText().trim().isBlank() || nameTextField.getText().trim().isEmpty())
                {
                    nameTextField.setText("Enter your name here");
                }
                else if (nameTextField.getText().trim().equals("Enter your name here"))
                {
                    nameTextField.setText("Enter your actual name here");
                }
                else if (passwordTextField.getText().trim().equals(confirmPasswordTextField.getText().trim()))
                {
                    AccountsManager.Register(nameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
                    isAuthenticated.set(true);
                    dispose();
                }
                else
                {
                    if (getSize().height != 430) setLocation(getLocation().x, getLocation().y - 7);
                    setSize(400, 440);
                    registerNotifyLabel.setText("Password and Confirm Password do not match!");
                }
            }
        });
        loginButton.addActionListener(e -> {
            if (!AccountsManager.ValidateEmail(emailTextField.getText()))
                emailTextField.setText("Please enter a valid E-mail");
            if (!AccountsManager.IsAccountRegistered(emailTextField.getText()))
            {
                remove(loginPanel);
                registerNotifyLabel.setText("Account with email doesn't exists! Register Instead");
                setLocation(getLocation().x, getLocation().y - 50);
                setSize(400, 440);
                add(registerPanel);
                registerPanel.add(emailLabel, new GridBagSettings(0, 4, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                registerPanel.add(emailTextField, new GridBagSettings(0, 5, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                registerPanel.add(passwordLabel, new GridBagSettings(0, 6, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
                registerPanel.add(passwordTextField, new GridBagSettings(0, 7, 1, 0,
                        GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
                revalidate();
                repaint();
            }
            else
            {
                if (AccountsManager.Login(emailTextField.getText(), passwordTextField.getText()))
                {
                    GuiAppManager.StartBooking();
                    isAuthenticated.set(true);
                    dispose();
                }
                else
                {
                    if (getSize().height != 320) setLocation(getLocation().x, getLocation().y - 5);
                    setSize(400, 320);
                    loginNotifyLabel.setText("Incorrect Password!");
                }
            }
        });

        mainPanel.add(mainLabel, new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 30, 10)));
        mainPanel.add(emailLabel, new GridBagSettings(0, 1, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
        mainPanel.add(emailTextField, new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 10, 10)));
        mainPanel.add(authenticateButton, new GridBagSettings(0, 3, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));

        loginPanel.add(loginLabel, new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
        loginPanel.add(loginNotifyLabel, new GridBagSettings(0, 1, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 34, 10)));
        loginPanel.add(loginButton, new GridBagSettings(0, 6, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));

        registerPanel.add(registerLabel, new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
        registerPanel.add(registerNotifyLabel, new GridBagSettings(0, 1, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 25, 10)));
        registerPanel.add(nameLabel, new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
        registerPanel.add(nameTextField, new GridBagSettings(0, 3, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 7, 10)));
        registerPanel.add(confirmPasswordLabel, new GridBagSettings(0, 8, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));
        registerPanel.add(confirmPasswordTextField, new GridBagSettings(0, 9, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 27, 10)));
        registerPanel.add(registerButton, new GridBagSettings(0, 10, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 10, 0, 10)));

        add(mainPanel);
        setVisible(true);
    }
}
