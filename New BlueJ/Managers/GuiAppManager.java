package Managers;

import GUI.Button;
import GUI.GridBagSettings;
import GUI.Screen;
import GUI.ScrollPane;
import GUI.Window;
import GUI.Label;
import Utilities.Movie;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class GuiAppManager
{
    private static Window window;
    public static void Initialize()
    {
        window = new Window();
        MainScreen();
        MovieSelectScreen();
    }

    public static void Start()
    {
        window.setVisible(true);
        window.openScreen("Main");
    }

    public static void MainScreen()
    {
        // Components
        Screen mainScreen = new Screen();
        Label titleLabel = new Label(
                "Showtime Hub ",
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.TITLE_FONT
        );
        Label subTitleLabel = new Label(
                "Incredible cinematic experiences since 2006!",
                Visuals.Colors.TEXT_DARKER,
                Visuals.Fonts.SUBTITLE_FONT
        );
        Button promptButton = new Button(
                "Start Booking Now!",
                new Dimension(350, 50)
        );
        ImageIcon logo = new ImageIcon("Assets/Branding/Logo.png");

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagSettings titleLabelConstraints = new GridBagSettings(0, 0);
        GridBagSettings subTitleLabelConstraints = new GridBagSettings(0, 1);
        GridBagSettings promptButtonConstraints = new GridBagSettings(0, 2,
                new Insets(150, 0, 0, 0));

        // Other Configurations
        mainScreen.setLayout(mainLayout);
        titleLabel.setVerticalAlignment(JLabel.BOTTOM);
        titleLabel.setIcon(logo);
        titleLabel.setHorizontalTextPosition(Label.LEFT);
        subTitleLabel.setVerticalAlignment(JLabel.TOP);
        promptButton.addActionListener(e -> window.openScreen("Movie Select"));

        mainScreen.setName("Main");
        mainScreen.add(titleLabel, titleLabelConstraints);
        mainScreen.add(subTitleLabel, subTitleLabelConstraints);
        mainScreen.add(promptButton, promptButtonConstraints);
        window.addScreen(mainScreen);
    }

    public static void MovieSelectScreen()
    {
        // Components
        Screen movieSelectScreen = new Screen();
        Label headingLabel = new Label(
                "Select movie: ",
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.HEADING_FONT
        );
        JPanel moviesPanel = new JPanel();
        ScrollPane moviesScrollPane = new ScrollPane(
                moviesPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        Button returnButton  = new Button(
                "Return",
                new Dimension(350, 50)
        );

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout moviesPanelLayout = new GridBagLayout();
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings moviesPanelConstraints = new GridBagSettings(0, 1, 1, 1,
                GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));

        // Other Configurations
        movieSelectScreen.setLayout(mainLayout);
        moviesPanel.setLayout(moviesPanelLayout);
        moviesPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        returnButton.addActionListener(e -> window.openScreen("Main"));

        int x = 0, y = 0;
        for (Movie movie : BookingManager.Movies)
        {
            ImageIcon thumbnail = new ImageIcon("Assets/Thumbnail/" + movie.name + ".jpg");
            Button movieButton = new Button(movie.name, new Dimension(255, 330));
            movieButton.setFont(Visuals.Fonts.NORMAL_FONT);
            movieButton.setIcon(thumbnail); 
            movieButton.setVerticalTextPosition(Button.BOTTOM);
            movieButton.setHorizontalTextPosition(Button.CENTER);
            moviesPanel.add(movieButton, new GridBagSettings(x, y, new Insets(2, 2, 2, 2)));
            x++;
            if (x % 5 == 0 && x != 0)
            { y++; x = 0; }
        }


        movieSelectScreen.setName("Movie Select");
        movieSelectScreen.add(headingLabel, headingLabelConstraints);
        movieSelectScreen.add(moviesScrollPane, moviesPanelConstraints);
        movieSelectScreen.add(returnButton, returnButtonConstraints);
        window.addScreen(movieSelectScreen);
    }
}
