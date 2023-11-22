package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.AccountsManager;
import Managers.BookingManager;
import Managers.GuiAppManager;
import Utilities.Account;
import Utilities.Movie;
import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class SeatsSelectScreen extends Screen
{
    public SeatsSelectScreen()
    {
        super();

        // Components
        Label headingLabel = new Label(
                "Book your seats: ",
                Visuals.Colors.TEXT_NORMAL,
                Visuals.Fonts.HEADING_FONT
        );
        JPanel seatButtonPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        Button returnButton  = new Button(
                "Return",
                new Dimension(350, 50)
        );
        Button confirmButton  = new Button(
                "Confirm Seats",
                new Dimension(350, 50)
        );

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout seatButtonsPanelLayout = new GridBagLayout();
        GridBagLayout buttonPanelLayout = new GridBagLayout();

        // Layout Config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings seatButtonsPanelConstraints = new GridBagSettings(0, 1, 1, 1,
                GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings buttonsPanelConstraints = new GridBagSettings(0, 2, 1, 0,
                GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 0, 1, 1,
                GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings bookButtonConstraints = new GridBagSettings(1, 0, 1, 1,
                GridBagSettings.BOTH, new Insets(0,5, 10, 10));

        // Other Configurations
        setLayout(mainLayout);
        seatButtonPanel.setLayout(seatButtonsPanelLayout);
        buttonsPanel.setLayout(buttonPanelLayout);
        buttonsPanel.setOpaque(false);
//        seatButtonPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        confirmButton.addActionListener(e -> {
            System.out.println("Confirmed");
        });

        buttonsPanel.add(returnButton, returnButtonConstraints);
        buttonsPanel.add(confirmButton, bookButtonConstraints);

        setName("Seat Select");
        add(headingLabel, headingLabelConstraints);
        add(seatButtonPanel, seatButtonsPanelConstraints);
        add(buttonsPanel, buttonsPanelConstraints);
    }
}
