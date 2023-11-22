package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends Screen
{
    public MainScreen()
    {
        super();

        // Components
        Label titleLabel = new Label(
                "Showtime Hub ",
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.TITLE_FONT
        );
        Label subTitleLabel = new Label(
                "Incredible cinematic experiences since 2006!",
                Visuals.Colors.TEXT_NORMAL_DARKER,
                Visuals.Fonts.SUBTITLE_FONT
        );
        Button promptButton = new Button(
                "Start Booking Now!",
                new Dimension(350, 50)
        );
        ImageIcon logo = new ImageIcon("Assets/Branding/Logo.png");

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();

        // Layout config
        GridBagSettings titleLabelConstraints = new GridBagSettings(0, 0);
        GridBagSettings subTitleLabelConstraints = new GridBagSettings(0, 1);
        GridBagSettings promptButtonConstraints = new GridBagSettings(0, 2,
                new Insets(150, 0, 0, 0));

        // Other Configurations
        setLayout(mainLayout);
        titleLabel.setVerticalAlignment(JLabel.BOTTOM);
        titleLabel.setIcon(logo);
        titleLabel.setHorizontalTextPosition(Label.LEFT);
        subTitleLabel.setVerticalAlignment(JLabel.TOP);
        promptButton.addActionListener(e ->
                ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Movie Select")
        );

        setName("Main");
        add(titleLabel, titleLabelConstraints);
        add(subTitleLabel, subTitleLabelConstraints);
        add(promptButton, promptButtonConstraints);
    }
}
