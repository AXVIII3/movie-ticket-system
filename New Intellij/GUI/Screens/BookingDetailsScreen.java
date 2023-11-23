package GUI.Screens;

import GUI.Button;
import GUI.Label;
import GUI.Screen;
import Utilities.Movie;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class BookingDetailsScreen extends Screen
{
    public BookingDetailsScreen()
    {
        super();

        // Components
        Label headingLabel = new Label(
                "Pssht... Your Ticket(s) Have Been Confirmed!",
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.HEADING_FONT
        );
        JPanel movieDetailsPanel = new JPanel();
        JPanel detailsPanel = new JPanel();
        JLabel posterLabel = new JLabel("");
        Button okButton  = new Button(
                "Woohoo",
                new Dimension(350, 50)
        );

        // Detail Labels
        Label nameLabel = new Label("Name", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.SUBTITLE_FONT);
        Label bookDetailsLabel = new Label("Booking details:", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.SUBHEADING_FONT);
        Label hallAndRoomLabel = new Label("Available Cinema Halls:  ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label dateLabel = new Label("Available Dates:               ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        Label timeLabel = new Label("Available Times:               ", Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.NORMAL_FONT);
        JPanel emptySpace = new JPanel();

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Other Configurations
        setLayout(layout);
        movieDetailsPanel.setLayout(layout);
        detailsPanel.setLayout(layout);
        movieDetailsPanel.setOpaque(false);
        detailsPanel.setOpaque(false);
        emptySpace.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
//        posterLabel.setIcon(new ImageIcon(movie.posterPath));
//
//        movieDetailsPanel.add(posterLabel, posterLabelConstraints);
//        movieDetailsPanel.add(detailsPanel, detailsPanelConstraints);
        add(headingLabel);

        setName("Booking Details");
    }

    public void Setup(Movie movie)
    {

    }
}
