package managers;

import gui.Button;
import gui.Frame;
import gui.Panel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.Border;

public class GUICreator
{
    private static Frame frame;
    private static Movie movie;

    // COLOR PALETTE
    private static final Color[] gradientBackground = {new Color(0x111F25), new Color(0x1D0E0E)};
    private static final Color mainColor = new Color(0xFF7A7A);
    private static final Color mainColorDarker = new Color(0xDA6868);
    private static final Color mainColorDarkest = new Color(0xB55757);
    private static final Color darkColor = new Color(0x0E1A1E);
    private static final Color lightColor = new Color(0xE3D7D7);
    private static final Color disabledColor = new Color(0x9D9D9D);
    private static final Color seatSelected = new Color(0xA7EF84);

    public static void InitializeGUI()
    {
        CreateFrame();
        MainScreen();
    }

    private static void CreateFrame()
    {
        frame = new Frame();

        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width - 10, screenSize.height - 10);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setTitle("Movie Ticket System ~ P.B.A.S.");
        frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/assets/Logo.png");
        frame.setIconImage(icon.getImage());
    }

    private static void MainScreen()
    {
        // ELEMENTS
        Panel panel = new Panel("Main");
        JLabel logoLabel = new JLabel();
        JLabel tagLineLabel = new JLabel();
        Button bookNowButton = new Button("Book Now!");

        // LAYOUTS
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints logoLabelConstraints = new GridBagConstraints();
        GridBagConstraints tagLineLabelConstraints = new GridBagConstraints();
        GridBagConstraints bookNowButtonConstraints = new GridBagConstraints();

        // VISUALS
        Border panelBorder = BorderFactory.createLineBorder(mainColor, 3);
        Border buttonBorder = BorderFactory.createLineBorder(mainColorDarkest, 2);

        // Panel Configurations
        panel.setLayout(layout);
        panel.setBorder(panelBorder);
        panel.setGradient(gradientBackground[0], gradientBackground[1], 30);

        // Logo Label Configurations
        logoLabelConstraints.gridy = 0;
        logoLabel.setText("Showtime Hub 🎬");
        logoLabel.setForeground(mainColor);
        logoLabel.setVerticalAlignment(JLabel.BOTTOM);
        logoLabel.setFont(new Font("Roboto", Font.BOLD, 70));

        // Tagline Label Configurations
        tagLineLabelConstraints.gridy = 1;
        tagLineLabel.setText("Incredible cinematic experiences since 2006!");
        tagLineLabel.setForeground(mainColorDarker);
        tagLineLabel.setVerticalAlignment(JLabel.TOP);
        tagLineLabel.setFont(new Font("Roboto", Font.BOLD, 30));

        // Book Now Button Configurations
        bookNowButtonConstraints.gridy = 2;
        bookNowButtonConstraints.insets = new Insets(100, 0, 0, 0);
        bookNowButton.setPreferredSize(new Dimension(350, 50));
        bookNowButton.setBorder(buttonBorder);
        bookNowButton.setForeground(darkColor);
        bookNowButton.setBackground(mainColor);
        bookNowButton.setPressedBackgroundColor(mainColorDarker);
        bookNowButton.setFont(new Font("Roboto", Font.BOLD, 20));
        bookNowButton.addActionListener(e -> MovieSelectScreen());

        // Adding everything together
        panel.add(logoLabel, logoLabelConstraints);
        panel.add(tagLineLabel, tagLineLabelConstraints);
        panel.add(bookNowButton, bookNowButtonConstraints);
        frame.addNewPanel(panel);
        frame.openPanel("Main");
    }

    private static void MovieSelectScreen()
    {
        // ELEMENTS
        Panel panel = new Panel("MovieSelect");
        Panel movieItemsPanel = new Panel();
        JLabel headingLabel = new JLabel();
        Button backButton = new Button("Go Back");
        Button[] movieButtons = new Button[BookingManager.movies.length];

        // LAYOUTS
        GridBagLayout mainLayout = new GridBagLayout();
        FlowLayout movieItemsPanelLayout = new FlowLayout();
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        GridBagConstraints headingConstraints = new GridBagConstraints();
        GridBagConstraints movieItemsPanelConstraints = new GridBagConstraints();

        // VISUALS
        Border panelBorder = BorderFactory.createLineBorder(mainColor, 3);
        Border buttonBorder = BorderFactory.createLineBorder(mainColorDarkest, 2);

        // Panel Configuration
        panel.setLayout(mainLayout);
        panel.setBorder(panelBorder);
        panel.setGradient(gradientBackground[0], gradientBackground[1], 30);

        // Heading Label Configuration
        headingConstraints.gridy = 0;
        headingConstraints.insets = new Insets(10, 0, 0, 0);
        headingLabel.setText("Select Movie: ");
        headingLabel.setForeground(mainColor);
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 60));

        // Movie Panel Constraints
        movieItemsPanelConstraints.gridy = 1;
        movieItemsPanelConstraints.weightx = 1;
        movieItemsPanelConstraints.weighty = 1;
        movieItemsPanelConstraints.fill = GridBagConstraints.BOTH;
        movieItemsPanelConstraints.insets = new Insets(10, 10, 10, 10);
        movieItemsPanel.setLayout(movieItemsPanelLayout);
        movieItemsPanel.setOpaque(false);

        // Adding movies to the movie panel dynamically
        for (int i = 0; i < movieButtons.length; i++) {
            movieButtons[i] = new Button(BookingManager.movies[i].name);
            movieButtons[i].setIcon(
                    new ImageIcon("src/assets/thumbnails_small/" + BookingManager.movies[i].thumbnailFileName)
            );
            movieButtons[i].setIconTextGap(8);
            movieButtons[i].setHorizontalTextPosition(Button.CENTER);
            movieButtons[i].setVerticalTextPosition(Button.BOTTOM);
            movieButtons[i].setPreferredSize(new Dimension(235, 273));
            movieButtons[i].setBorder(buttonBorder);
            movieButtons[i].setForeground(darkColor);
            movieButtons[i].setBackground(mainColor);
            movieButtons[i].setFont(new Font("Roboto", Font.BOLD, 17));
            movieButtons[i].setPressedBackgroundColor(mainColorDarkest);

            int finalI = i;
            movieButtons[i].addActionListener(e -> {
                movie = BookingManager.movies[finalI];
                MovieDetailsScreen();
            });

            movieItemsPanel.add(movieButtons[i]);
        }

        // Back Button Configuration
        backButtonConstraints.gridy = 2;
        backButtonConstraints.insets = new Insets(0, 0, 10, 0);
        backButton.setMinimumSize(new Dimension(350, 40));
        backButton.setPreferredSize(new Dimension(350, 40));
        backButton.setBorder(buttonBorder);
        backButton.setForeground(darkColor);
        backButton.setBackground(mainColor);
        backButton.setPressedBackgroundColor(mainColorDarker);
        backButton.setFont(new Font("Roboto", Font.BOLD, 20));
        backButton.addActionListener(e -> frame.openPanel("Main"));

        // Adding everything together
        panel.add(headingLabel, headingConstraints);
        panel.add(movieItemsPanel, movieItemsPanelConstraints);
        panel.add(backButton, backButtonConstraints);
        frame.addNewPanel(panel);
        frame.openPanel("MovieSelect");
    }

    private static void MovieDetailsScreen()
    {
        // ELEMENTS
        Panel panel = new Panel(String.valueOf(movie.name));
        Panel movieDetailsPanel = new Panel();
        Panel buttonsPanel = new Panel();
        JLabel headingLabel = new JLabel();
        JLabel thumbnail = new JLabel();
        JLabel movieDetailsText = new JLabel();
        Button backButton = new Button("Go Back");
        Button bookButton = new Button("Book Tickets");

        // LAYOUTS
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout movieDetailsPanelLayout = new GridBagLayout();
        FlowLayout buttonsPanelLayout = new FlowLayout();
        GridBagConstraints buttonsPanelConstraints = new GridBagConstraints();
        GridBagConstraints headingConstraints = new GridBagConstraints();
        GridBagConstraints movieDetailsPanelConstraints = new GridBagConstraints();
        GridBagConstraints thumbnailConstraints = new GridBagConstraints();
        GridBagConstraints movieDetailsTextConstraints = new GridBagConstraints();

        // VISUALS
        Border panelBorder = BorderFactory.createLineBorder(mainColor, 3);
        Border buttonBorder = BorderFactory.createLineBorder(mainColorDarkest, 2);

        // Panel Configuration
        panel.setLayout(mainLayout);
        panel.setBorder(panelBorder);
        panel.setGradient(gradientBackground[0], gradientBackground[1], 30);

        // Heading Label Configuration
        headingConstraints.gridy = 0;
        headingConstraints.insets = new Insets(20, 0, 10, 0);
        headingLabel.setText("Movie Details:");
        headingLabel.setForeground(mainColor);
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 60));

        // Movie Details Panel Configuration
        movieDetailsPanelConstraints.gridy = 1;
        movieDetailsPanelConstraints.weightx = 1;
        movieDetailsPanelConstraints.weighty = 1;
        movieDetailsPanelConstraints.fill = GridBagConstraints.BOTH;
        movieDetailsPanelConstraints.insets = new Insets(10, 20, 20, 10);
        movieDetailsPanel.setLayout(movieDetailsPanelLayout);
        movieDetailsPanel.setOpaque(false);

        // Movie Details Configuration
        thumbnailConstraints.weighty = 1;
        thumbnailConstraints.fill = GridBagConstraints.BOTH;
        movieDetailsTextConstraints.gridy = 0;
        movieDetailsTextConstraints.gridx = 1;
        movieDetailsTextConstraints.fill = GridBagConstraints.BOTH;
        movieDetailsTextConstraints.weightx = 1;
        movieDetailsTextConstraints.weighty = 1;
        movieDetailsTextConstraints.insets = new Insets(0, 30, 0, 0);
        thumbnail.setIcon(
                new ImageIcon("src/assets/thumbnails_large/" + movie.thumbnailFileName)
        );
        movieDetailsText.setForeground(lightColor);
        movieDetailsText.setFont(new Font("Roboto", Font.BOLD, 45));
        StringBuilder text = new StringBuilder("<html>" +
                movie.name +
                "<h6>" +
                "<h1>About the movie:" +
                "<h3>Description: " + movie.description +
                "<h3>Cast: " + movie.cast +
                "<h3>Genre: " + movie.genre +
                "<h3>Rating: " + movie.rating +
                "<h6>" +
                "<h1>Booking details:" +
                "<h3>Movie starts at " + movie.time + " for " + movie.duration +
                "<h3>Available Dates: "
        );
        for (int i = 0; i < movie.formattedDates.length; i++)
            text.append(i == 0 ? "" : ", ").append(movie.formattedDates[i]);
        text.append("<h3>Cost per Ticket: Rs.").append(movie.seatCost).append("</html>");

        movieDetailsText.setText(text.toString());
        movieDetailsPanel.add(thumbnail, thumbnailConstraints);
        movieDetailsPanel.add(movieDetailsText, movieDetailsTextConstraints);

        // Back Button Configuration
        backButton.setMinimumSize(new Dimension(350, 40));
        backButton.setPreferredSize(new Dimension(350, 40));
        backButton.setBorder(buttonBorder);
        backButton.setForeground(darkColor);
        backButton.setBackground(mainColor);
        backButton.setPressedBackgroundColor(mainColorDarker);
        backButton.setFont(new Font("Roboto", Font.BOLD, 20));
        backButton.addActionListener(e -> frame.openPanel("MovieSelect"));

        // Book Button Configuration
        bookButton.setMinimumSize(new Dimension(350, 40));
        bookButton.setPreferredSize(new Dimension(350, 40));
        bookButton.setBorder(buttonBorder);
        bookButton.setForeground(darkColor);
        bookButton.setBackground(mainColor);
        bookButton.setPressedBackgroundColor(mainColorDarker);
        bookButton.setFont(new Font("Roboto", Font.BOLD, 20));
        bookButton.addActionListener(e -> {
            EmptyPanel();
            OpenDateSelectDialog();
        });

        // Buttons Panel
        buttonsPanelConstraints.gridy = 2;
        buttonsPanelConstraints.insets = new Insets(0, 0, 10, 0);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(backButton);
        buttonsPanel.add(bookButton);

        // Adding everything together
        panel.add(headingLabel, headingConstraints);
        panel.add(movieDetailsPanel, movieDetailsPanelConstraints);
        panel.add(buttonsPanel, buttonsPanelConstraints);
        frame.addNewPanel(panel);
        frame.openPanel(movie.name);
    }

    public static void EmptyPanel()
    {
        Panel panel = new Panel("Empty");
        Border panelBorder = BorderFactory.createLineBorder(new Color(0xff7a7a), 3);
        panel.setBorder(panelBorder);
        panel.setGradient(new Color(0x111f25), new Color(0x1d0e0e), 30);
        frame.addNewPanel(panel);
        frame.openPanel("Empty");
    }

    private static void OpenDateSelectDialog()
    {
        movie.selectedDate = 0;
        int dialogHeight = movie.dates.length * 53 + 70;

        JDialog dateDialog = new JDialog(frame, "Select Movie Date");
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints headingConstraints = new GridBagConstraints();
        JLabel headingLabel = new JLabel();

        dateDialog.setSize(300, dialogHeight);
        dateDialog.setResizable(false);
        dateDialog.setVisible(true);
        dateDialog.setLayout(layout);
        dateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dateDialog.setLocationRelativeTo(null);
        dateDialog.addWindowListener(new WindowListener() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                if (movie.selectedDate == 0) frame.openPanel(movie.name);
                else { SeatSelectScreen(); }
            }
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });

        headingConstraints.gridy = 0;
        headingConstraints.gridx = 0;
        headingConstraints.insets = new Insets(5, 0, 10, 0);
        headingLabel.setText("Select Date:");
        headingLabel.setForeground(new Color(0xff7a7a));
        headingLabel.setVerticalAlignment(JLabel.BOTTOM);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 30));

        Border buttonBorder = BorderFactory.createLineBorder(new Color(0xb55757), 2);
        GridBagConstraints constraints;
        for (int i = 0; i < movie.dates.length; i++)
        {
            constraints = new GridBagConstraints();
            Button button = new Button(
                    "       " + movie.formattedDates[i] + "       "
            );
            constraints.gridy = i + 1;
            constraints.gridx = 0;
            constraints.insets = new Insets(3, 0, 0, 0);
            if (i == movie.dates.length - 1)
                constraints.insets = new Insets(3, 0, 5, 0);
            button.setSize(new Dimension(250, 50));
            button.setBorder(buttonBorder);
            button.setForeground(new Color(0x0e1a1e));
            button.setBackground(new Color(0xff7a7a));
            button.setPressedBackgroundColor(new Color(0xb55757));
            button.setFont(new Font("Roboto", Font.BOLD, 20));
            int dateIndex = i;
            button.addActionListener(e -> {
                movie.selectedDate = movie.dates[dateIndex];
                dateDialog.dispose();
            });

            dateDialog.add(button, constraints);
        }

        dateDialog.add(headingLabel, headingConstraints);
        dateDialog.revalidate();
        dateDialog.repaint();
    }

    private static void SeatSelectScreen()
    {
        Panel panel = new Panel("Seat_" + movie.name + "_" + movie.selectedDate);
        Panel seatsPanel = new Panel("Seats");
        Panel buttonsPaneL = new Panel("Buttons");

        GridBagLayout layout = new GridBagLayout();
        FlowLayout buttonsLayout = new FlowLayout();

        GridBagConstraints headingLabelConstraints = new GridBagConstraints();
        GridBagConstraints seatsPaneConstraints = new GridBagConstraints();
        GridBagConstraints buttonsPaneConstraints = new GridBagConstraints();
        GridBagConstraints seatsPaneButtonsConstraints = new GridBagConstraints();
        GridBagConstraints screenDirectionLabelConstraints = new GridBagConstraints();

        Border panelBorder = BorderFactory.createLineBorder(mainColor, 3);
        Border buttonBorder = BorderFactory.createLineBorder(mainColorDarkest, 1);
        Border buttonSelectedBorder = BorderFactory.createLineBorder(seatSelected, 1);
        Border buttonBorderDisabled = BorderFactory.createLineBorder(disabledColor, 1);

        JLabel headingLabel = new JLabel();
        JLabel screenDirectionLabel = new JLabel();
        Button proceedButton = new Button("Book");
        Button returnButton = new Button("Go Back");

        ArrayList<Integer> seats;

        panel.setLayout(layout);
        panel.setBorder(panelBorder);
        panel.setGradient(gradientBackground[0], gradientBackground[1], 30);

        headingLabelConstraints.gridy = 0;
        headingLabelConstraints.gridx = 0;
        headingLabelConstraints.insets = new Insets(20, 0, 0, 0);
        headingLabel.setText("Select Movie: ");
        headingLabel.setForeground(mainColor);
        headingLabel.setVerticalAlignment(JLabel.BOTTOM);
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingLabel.setHorizontalTextPosition(JLabel.LEFT);
        headingLabel.setVerticalTextPosition(JLabel.CENTER);
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 60));

        screenDirectionLabelConstraints.gridy = 1;
        screenDirectionLabelConstraints.gridx = 0;
        screenDirectionLabel.setText("[↑ Screen this side ↑]");
        screenDirectionLabel.setForeground(mainColorDarker);
        screenDirectionLabel.setVerticalAlignment(JLabel.TOP);
        screenDirectionLabel.setHorizontalAlignment(JLabel.CENTER);
        screenDirectionLabel.setFont(new Font("Roboto", Font.BOLD, 25));

        seatsPaneConstraints.gridy = 2;
        seatsPaneConstraints.gridx = 0;
        seatsPaneConstraints.weightx = 1;
        seatsPaneConstraints.weighty = 1;
        seatsPaneConstraints.insets = new Insets(10, 10, 10, 10);
        seatsPaneConstraints.fill = GridBagConstraints.BOTH;
        seatsPanel.setLayout(layout);
        seatsPanel.setOpaque(false);

        buttonsPaneConstraints.gridy = 3;
        buttonsPaneConstraints.gridx = 0;
        buttonsPaneConstraints.weightx = 1;
        buttonsPaneConstraints.insets = new Insets(0, 0, 10, 0);
        buttonsPaneL.setLayout(buttonsLayout);
        buttonsPaneL.setOpaque(false);
        buttonsPaneL.add(returnButton);
        buttonsPaneL.add(proceedButton);

        proceedButton.setPreferredSize(new Dimension(250, 45));
        proceedButton.setBorder(buttonBorderDisabled);
        proceedButton.setForeground(darkColor);
        proceedButton.setBackground(disabledColor);
        proceedButton.setPressedBackgroundColor(disabledColor);
        proceedButton.setFont(new Font("Roboto", Font.BOLD, 20));

        returnButton.setPreferredSize(new Dimension(250, 45));
        returnButton.setBorder(buttonBorder);
        returnButton.setForeground(darkColor);
        returnButton.setBackground(mainColor);
        returnButton.setPressedBackgroundColor(mainColorDarkest);
        returnButton.setFont(new Font("Roboto", Font.BOLD, 20));
        returnButton.addActionListener(e -> frame.openPanel(movie.name));

        seatsPaneButtonsConstraints.weightx = 1;
        seatsPaneButtonsConstraints.weighty = 1;

        panel.add(headingLabel, headingLabelConstraints);
        panel.add(screenDirectionLabel, screenDirectionLabelConstraints);
        panel.add(seatsPanel, seatsPaneConstraints);
        panel.add(buttonsPaneL, buttonsPaneConstraints);

        frame.addNewPanel(panel);
        frame.openPanel("Seat_" + movie.name + "_" + movie.selectedDate);
    }
}
