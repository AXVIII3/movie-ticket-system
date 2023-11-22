package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.BookingManager;
import Managers.GuiAppManager;
import Utilities.Date;
import Utilities.Movie;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class MovieDetailsScreen extends Screen
{
    public MovieDetailsScreen(Movie movie)
    {
        super();

        // Components
        Label headingLabel = new Label(
                "Movie Details: " + movie.name,
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.HEADING_FONT
        );
        JPanel movieDetailsPanel = new JPanel();
        JPanel detailsPanel = new JPanel();
        JPanel datePanel = new JPanel();
        JPanel timePanel = new JPanel();
        JPanel screenTypePanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JLabel posterLabel = new JLabel("");
        Button returnButton  = new Button(
                "Return",
                new Dimension(350, 50)
        );
        Button bookButton  = new Button(
                "Book Tickets",
                new Dimension(350, 50)
        );
        ComboBox<String> datesComboBox = new ComboBox<>();
        ComboBox<String> timesComboBox = new ComboBox<>();
        ComboBox<String> availableScreensComboBox = new ComboBox<>();

        // Detail Labels
        Label nameLabel = new Label(movie.name + movie.tagline, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.SUBTITLE_FONT);
        Label releaseAndDurationLabel = new Label(movie.releaseYear + " • " + movie.duration,
                Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label aboutLabel = new Label("About the movie:", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.SUBHEADING_FONT);
        Label descriptionLabel = new Label("Description: " + movie.description, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label directorsLabel = new Label("Directors: " + movie.directors, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label writersLabel = new Label("Writers: " + movie.writers, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label starsLabel = new Label("Stars: " + movie.stars, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label genreLabel = new Label("Genre: " + movie.genre, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label ratingLabel = new Label("Rating: " + movie.rating, Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label bookDetailsLabel = new Label("Booking details:", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.SUBHEADING_FONT);
        Label dateLabel = new Label("Available Dates:      ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label timeLabel = new Label("Available Times:      ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label availableScreensLabel = new Label("Available Screens:  ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        JPanel emptySpace = new JPanel();

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout movieDetailsPanelLayout = new GridBagLayout();
        GridBagLayout movieDetailsTextLayout = new GridBagLayout();
        GridBagLayout buttonPanelLayout = new GridBagLayout();

        // Layout Config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings movieDetailsPanelConstraints = new GridBagSettings(0, 1, 1, 1,
                GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings buttonsPanelConstraints = new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));
        GridBagSettings detailsPanelConstraints = new GridBagSettings(1, 0, 1, 1,
                GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings posterLabelConstraints = new GridBagSettings(0, 0, 0, 1,
                GridBagSettings.VERTICAL, new Insets(0,10, 10, 5));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 0, 1, 1,
                GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings bookButtonConstraints = new GridBagSettings(1, 0, 1, 1,
                GridBagSettings.BOTH, new Insets(0,5, 10, 10));

        // Labels Constraints
        GridBagSettings nameLabelConstraints = new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 2, 0));
        GridBagSettings releaseYearAndDurationLabelConstraints = new GridBagSettings(
                0, 1, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 30, 0));
        GridBagSettings aboutLabelConstraints = new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings ratingLabelConstraints = new GridBagSettings(0, 3, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings descriptionLabelConstraints = new GridBagSettings(0, 4, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings directorsLabelConstraints = new GridBagSettings(0, 5, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings writersLabelConstraints = new GridBagSettings(0, 6, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings starsLabelConstraints = new GridBagSettings(0, 7, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings genreLabelConstraints = new GridBagSettings(0, 8, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 30, 0));
        GridBagSettings bookingLabelConstraints = new GridBagSettings(0, 9, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 2, 0));
        GridBagSettings datesPanelConstraints = new GridBagSettings(0, 10, 0, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings timesPanelConstraints = new GridBagSettings(0, 11, 0, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings availableScreensConstraints = new GridBagSettings(0, 12, 0, 0,
                GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings emptySpaceConstraints = new GridBagSettings(0, 13, 1, 1,
                GridBagSettings.BOTH, new Insets(0,0, 0, 0));

        // Other Configurations
        setLayout(mainLayout);
        movieDetailsPanel.setLayout(movieDetailsPanelLayout);
        buttonsPanel.setLayout(buttonPanelLayout);
        detailsPanel.setLayout(movieDetailsTextLayout);
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        screenTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        screenTypePanel.setOpaque(false);
        datePanel.setOpaque(false);
        timePanel.setOpaque(false);
        buttonsPanel.setOpaque(false);
        movieDetailsPanel.setOpaque(false);
        detailsPanel.setOpaque(false);
        emptySpace.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        posterLabel.setIcon(new ImageIcon(movie.posterPath));
        for (int i = 0; i < movie.dates.length; i++)
            if (BookingManager.AreSeatsAvailableOn(movie, movie.dates[i]))
                datesComboBox.addItem("  " + movie.dates[i].date);
        PopulateTimeComboBox(timesComboBox, movie, ((String) datesComboBox.getSelectedItem()).trim());
        datesComboBox.addActionListener (e -> PopulateTimeComboBox(timesComboBox, movie,
                ((String) datesComboBox.getSelectedItem()).trim()));
        for (int i = 0; i < movie.availableScreens.length; i++)
            availableScreensComboBox.addItem("  " + movie.availableScreens[i] + " " +
                    (movie.screensExtraCost[i] > 0 ? "(+ Rs." + movie.screensExtraCost[i] + ")" : "")
            );
        returnButton.addActionListener(e ->
                ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Movie Select")
        );
        bookButton.addActionListener(e -> {
            GuiAppManager.SetData(
                    movie,
                    ((String) datesComboBox.getSelectedItem()).trim(),
                    ((String) timesComboBox.getSelectedItem()).trim(),
                    availableScreensComboBox.getSelectedIndex()
            );
            GuiAppManager.StartAuthentication();
        });

        movieDetailsPanel.add(posterLabel, posterLabelConstraints);
        movieDetailsPanel.add(detailsPanel, detailsPanelConstraints);

        datePanel.add(dateLabel);
        datePanel.add(datesComboBox);

        timePanel.add(timeLabel);
        timePanel.add(timesComboBox);

        screenTypePanel.add(availableScreensLabel);
        screenTypePanel.add(availableScreensComboBox);

        detailsPanel.add(nameLabel, nameLabelConstraints);
        detailsPanel.add(releaseAndDurationLabel, releaseYearAndDurationLabelConstraints);
        detailsPanel.add(aboutLabel, aboutLabelConstraints);
        detailsPanel.add(descriptionLabel, descriptionLabelConstraints);
        detailsPanel.add(directorsLabel, directorsLabelConstraints);
        detailsPanel.add(writersLabel, writersLabelConstraints);
        detailsPanel.add(starsLabel, starsLabelConstraints);
        detailsPanel.add(genreLabel, genreLabelConstraints);
        detailsPanel.add(ratingLabel, ratingLabelConstraints);
        detailsPanel.add(releaseAndDurationLabel, releaseYearAndDurationLabelConstraints);
        detailsPanel.add(bookDetailsLabel, bookingLabelConstraints);
        detailsPanel.add(datePanel, datesPanelConstraints);
        detailsPanel.add(timePanel, timesPanelConstraints);
        detailsPanel.add(screenTypePanel, availableScreensConstraints);
        detailsPanel.add(emptySpace, emptySpaceConstraints);

        buttonsPanel.add(returnButton, returnButtonConstraints);
        buttonsPanel.add(bookButton, bookButtonConstraints);

        setName(movie.name);
        add(headingLabel, headingLabelConstraints);
        add(movieDetailsPanel, movieDetailsPanelConstraints);
        add(buttonsPanel, buttonsPanelConstraints);
    }

    private void PopulateTimeComboBox(ComboBox<String> comboBox, Movie movie, String chosenDate)
    {
        int i = 0;
        for (Date date : movie.dates)
        {
            if (date.date.equals(chosenDate)) break;
            i++;
        }

        comboBox.removeAllItems();

        for (int j = 0; j < movie.dates[i].times.length; j++)
            if (BookingManager.AreSeatsAvailableAt(movie, movie.dates[i], j))
                comboBox.addItem("  " + movie.dates[i].times[j]);
    }
}
