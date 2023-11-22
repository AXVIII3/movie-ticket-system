import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Repome {
    private static int bookedSeats = 0;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowWelcomeGUI();
        });
    }
    private static void createAndShowWelcomeGUI() {

        // Create the welcome frame
        JFrame welcomeFrame = new JFrame(" Movie booking software-PBAS");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(900, 500);
        welcomeFrame.getContentPane().setBackground(new Color(0, 0, 54)); // Blue background color

        // Create a JPanel for the welcome frame
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0, 0, 54));
        welcomeFrame.add(welcomePanel);
        welcomePanel.setLayout(new BorderLayout());

        // Add a welcome message label
        JLabel welcomeLabel = new JLabel("Welcome To The Movie Booking App");
        welcomeLabel.setForeground(Color.WHITE); // Black text color
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        Font labelFont = new Font("TimesNewRoman", Font.BOLD, 24); // Change the font size
        welcomeLabel.setFont(labelFont);

        // Create a "Continue" button
        JButton continueButton = new JButton("Continue To Book Your Ticket For Tomorrow");
        continueButton.setBackground(new Color(0, 102, 204)); // Blue background color
        continueButton.setForeground(Color.BLACK);
        continueButton.setFont(new Font("TimesNewRoman", Font.BOLD, 19)); // Change the font size
        welcomePanel.add(continueButton, BorderLayout.SOUTH);

         // ActionListener for the "Continue" button
       continueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show instructions pop-up
            showInstructionsPopup();
            welcomeFrame.dispose(); // Close the welcome frame
            createAndShowMovieSelectionGUI(); // Show the movie selection page
        }
    });
        welcomeFrame.setVisible(true);
    }
    private static void showInstructionsPopup() {
        JFrame instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setSize(400, 200);
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setLayout(new BorderLayout());
    
        JTextArea instructionsTextArea = new JTextArea();
        instructionsTextArea.setEditable(false);
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true);
    
        instructionsTextArea.append("Instructions:\n");
        instructionsTextArea.append("1. This is a movie booking software developed by PBAS.\n");
        instructionsTextArea.append("2. This software provides various selections of halls and the newest movies.\n");
        instructionsTextArea.append("3. Input the correctcredentials.\n");
    
        JScrollPane scrollPane = new JScrollPane(instructionsTextArea);
        instructionsFrame.add(scrollPane, BorderLayout.CENTER);
    
        JButton agreeButton = new JButton("Agree");
        agreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionsFrame.dispose();
            }
        });
        instructionsFrame.add(agreeButton, BorderLayout.SOUTH);
    
        instructionsFrame.setLocationRelativeTo(null); // Center the pop-up
        instructionsFrame.setVisible(true);
    }

    // 2nd Window Starts
    private static void createAndShowMovieSelectionGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame movieSelectionFrame = new JFrame("Movie Selection");
        movieSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieSelectionFrame.setSize(800, 500); // Enlarge the window
        movieSelectionFrame.getContentPane().setBackground(new Color(51, 204, 255)); // Blue background color

        // Create a JPanel for the movie selection frame with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(51, 204, 255)); // Blue background color
        movieSelectionFrame.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Add spacing between components

         // Create a panel for the top part of the frame with a gradient
        JPanel topPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(51, 204, 255); // Start color (light blue)
            Color color2 = new Color(173, 216, 230); // End color (light blue)
            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    };
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    panel.add(topPanel, constraints);

        // Add a label and a combo box for selecting a movie
        JLabel movieLabel = new JLabel("Choose a Movie:");
        movieLabel.setForeground(Color.BLACK); // Black text color
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(movieLabel, constraints);

        Font labelFont = new Font("TimesNewRoman", Font.BOLD, 19); // Change the font size
        movieLabel.setFont(labelFont);

        JComboBox<String> movieComboBox = new JComboBox<>();
        movieComboBox.addItem("Yeh Jawani Hain Deewani");
        movieComboBox.addItem("Jaawan");
        movieComboBox.addItem("Interstellar");
        movieComboBox.addItem("Tiger 3");
        movieComboBox.addItem("Raktabeej");
        movieComboBox.setBackground(new Color(236, 254, 255)); // Blue background color
        movieComboBox.setForeground(Color.BLACK); // Black text color
        constraints.gridx = 1;
        panel.add(movieComboBox, constraints);

        // Create a panel for the middle part of the frame
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(new Color(51, 204, 255));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(middlePanel, constraints);

        // Add a label and a combo box for selecting a time slot
        JLabel timeSlotLabel = new JLabel("Select a Time Slot:");
        timeSlotLabel.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        middlePanel.add(timeSlotLabel, constraints);

        Font timeSlotFont = new Font("TimesNewRoman", Font.BOLD, 19);
        timeSlotLabel.setFont(timeSlotFont);

        JComboBox<String> timeSlotComboBox = new JComboBox<>();
        timeSlotComboBox.addItem("Morning (9:00 AM)");
        timeSlotComboBox.addItem("Afternoon (2:00 PM)");
        timeSlotComboBox.addItem("Evening (6:00 PM)");
        timeSlotComboBox.addItem("Night (9:00 PM)");
        timeSlotComboBox.setBackground(new Color(236, 254, 255));
        timeSlotComboBox.setForeground(Color.BLACK);
        constraints.gridx = 1;
        middlePanel.add(timeSlotComboBox, constraints);

         // Add a label and a combo box for selecting a theater hall
JLabel hallLabel = new JLabel("Select a Theater Hall:");
hallLabel.setForeground(Color.BLACK);
constraints.gridx = 0;
constraints.gridy = 3; // Update the grid position
constraints.gridwidth = 1;
panel.add(hallLabel, constraints);

Font hallFont = new Font("TimesNewRoman", Font.BOLD, 19);
hallLabel.setFont(hallFont);

JComboBox<String> hallComboBox = new JComboBox<>();
    hallComboBox.addItem("Cinepolis");
    hallComboBox.addItem("Inox");
    hallComboBox.addItem("PVR");
    hallComboBox.addItem("Ashoka");
    hallComboBox.setBackground(new Color(236, 254, 255));
    hallComboBox.setForeground(Color.BLACK);
    constraints.gridx = 1;
    panel.add(hallComboBox, constraints);

// Create a panel for the bottom part of the frame
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(51, 204, 255));
    constraints.gridx = 0;
    constraints.gridy = 4; // Update the grid position
    constraints.gridwidth = 2;
    panel.add(bottomPanel, constraints);

JButton nextButton = new JButton("Next");
    nextButton.setBackground(new Color(0, 102, 204));
    nextButton.setForeground(Color.BLACK);
    constraints.gridx = 0; // Update the grid position
    constraints.gridy = 5; // Update the grid position
    constraints.gridwidth = 2; // Update the grid width
    panel.add(nextButton, constraints); // Add to the panel

Font buttonFont = new Font("TimesNewRoman", Font.BOLD, 19); // Change the font size
nextButton.setFont(buttonFont);

// Set focusPainted to false to remove the blue border around the button
nextButton.setFocusPainted(false);

nextButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedMovie = (String) movieComboBox.getSelectedItem();
        String selectedTimeSlot = (String) timeSlotComboBox.getSelectedItem();
        String selectedHall = (String) hallComboBox.getSelectedItem();
        int selectedSeatsCapacity = getSeatsCapacityForHall(selectedHall);
        bookMovie(selectedMovie, selectedTimeSlot, selectedHall, selectedSeatsCapacity);
        movieSelectionFrame.dispose();
    }
});

movieSelectionFrame.setVisible(true);
}
private static int getSeatsCapacityForHall(String hall) {
    switch (hall) {
        case "Cinepolis":
            return 15; // Updated seat number for Cinepolis
        case "Inox":
            return 10; // Updated seat number for Inox
        case "PVR":
            return 8; // Updated seat number for PVR
        case "Ashoka":
            return 9 ; // Updated seat number for Ashoka
        default:
            return 0;
    }
}

    private static void bookMovie(String movie, String timeSlot, String selectedHall ,int seatingCapacity) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        JFrame bookingFrame = new JFrame("All Eyes This Way");
        bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookingFrame.setSize(800, 600);
        bookingFrame.getContentPane().setBackground(new Color(51, 204, 255));
    
        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 204, 255));
        bookingFrame.add(panel);
        panel.setLayout(new BorderLayout());
    
        JPanel theaterPanel = new JPanel(new GridLayout(5, 8));
        theaterPanel.setBackground(new Color(51, 204, 255));
    
        JButton[][] seatButtons = new JButton[5][seatingCapacity];

    for (int row = 0; row < 5; row++) {
        for (int seat = 0; seat < seatingCapacity; seat++) {
            int seatNumber = (row * seatingCapacity) + seat + 1;
            seatButtons[row][seat] = new JButton(Integer.toString(seatNumber));
            seatButtons[row][seat].setBackground(Color.GREEN);
            seatButtons[row][seat].setForeground(Color.BLUE);
            theaterPanel.add(seatButtons[row][seat]);

            seatButtons[row][seat].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    if (clickedButton.getBackground().equals(Color.GREEN)) {
                        if (bookedSeats < 2) {
                            clickedButton.setBackground(Color.RED);
                            clickedButton.setEnabled(false);
                            bookedSeats++;
                        } else {
                            JOptionPane.showMessageDialog(clickedButton, "You can book a maximum of 2 seats.", "Seat Booking", JOptionPane.WARNING_MESSAGE);
                        }
                    } else if (clickedButton.getBackground().equals(Color.RED)) {
                        clickedButton.setBackground(Color.GREEN);
                        clickedButton.setBackground(Color.YELLOW);
                        JOptionPane.showMessageDialog(clickedButton, "This seat is already booked.", "Seat Booking", JOptionPane.WARNING_MESSAGE);
                    } else if (clickedButton.getBackground().equals(Color.YELLOW)) {
                        // Do nothing for now
                    }
                    }
                });
            }
        }
        panel.add(theaterPanel, BorderLayout.NORTH);
    
        // Use SecureRandom for secure random number generation
        SecureRandom secureRandom = new SecureRandom();
    
        // Randomly mark a few seats as unavailable
        int totalSeats = 5 * 8;
        int unavailableSeats = secureRandom.nextInt(5) + 1;
    
        // Keep track of randomly unavailable seats
        boolean[][] randomlyUnavailableSeats = new boolean[5][8];
    
        for (int i = 0; i < unavailableSeats; i++) {
            int randomSeat = secureRandom.nextInt(totalSeats);
            int row = randomSeat / 8;
            int seat = randomSeat % 8;
            seatButtons[row][seat].setBackground(Color.GRAY);
            seatButtons[row][seat].setEnabled(false);
            randomlyUnavailableSeats[row][seat] = true;
        }
        panel.add(new JScrollPane(theaterPanel), BorderLayout.CENTER);
    
        int basePricePerSeat;
        switch (selectedHall) {
            case "Cinepolis":
                basePricePerSeat = 290;
                break;
            case "Inox":
                basePricePerSeat = 290;
                break;
            case "PVR":
                basePricePerSeat = 350;
                break;
            case "Ashoka":
                basePricePerSeat = 120;
                break;
            default:
                basePricePerSeat = 0;
                break;
        }
    
        // Add a button to book the selected seats
        JButton bookButton = new JButton("Book Selected Seats");
        bookButton.setBackground(new Color(255, 204, 0));
        bookButton.setForeground(Color.BLUE);
        panel.add(bookButton, BorderLayout.SOUTH);
    
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedSeats = new StringBuilder();
                int totalAmount = 0;
                for (int row = 0; row < 5; row++) {
                    for (int seat = 0; seat < 8; seat++) {
                        if (seatButtons[row][seat].getBackground().equals(Color.RED)) {
                            selectedSeats.append("Seat ").append(seatButtons[row][seat].getText()).append(", ");
                            totalAmount += basePricePerSeat;
                            seatButtons[row][seat].setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
                        }
                    }
                }

                if (selectedSeats.length() > 0) {
                    String name = JOptionPane.showInputDialog(bookingFrame, "Enter your name:");
                    if (name != null && !name.isEmpty()) {
                        String userPhoneNumber = null;
                        // Loop until a valid 10-digit phone number is entered
                        while (userPhoneNumber == null || !userPhoneNumber.matches("\\d{10}")) {
                            userPhoneNumber = JOptionPane.showInputDialog(bookingFrame, "Enter your phone number:");
                        
                            // Check if the input is a valid 10-digit numeric value
                            if (userPhoneNumber == null || !userPhoneNumber.matches("\\d{10}")) {
                                JOptionPane.showMessageDialog(bookingFrame, "Please enter a valid 10-digit numeric phone number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                String email;
                boolean validEmail = false;

                // Loop until a valid email address is entered
while (!validEmail) {
    email = JOptionPane.showInputDialog(bookingFrame, "Enter your email:");

    // Check if the input contains "@" for a basic email format validation
    if (email != null && email.contains("@")) {
        validEmail = true;
    } else {
        JOptionPane.showMessageDialog(bookingFrame, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

                        // Display payable amount without GST
    JOptionPane.showMessageDialog(bookingFrame, "Base Amount (without GST): " + totalAmount + " INR", "Booking Summary", JOptionPane.INFORMATION_MESSAGE);

    // Calculate CGST and SGST
    double cgst = 0.09 * totalAmount;
    double sgst = 0.09 * totalAmount;

    // Calculate total amount including taxes
    double totalAmountWithTaxes = totalAmount + cgst + sgst;

                        // Display breakdown of GST and total payable amount
                        JOptionPane.showMessageDialog(bookingFrame, "CGST (9%): " + cgst + " INR\nSGST (9%): " + sgst + " INR\nTotal Amount (including taxes): " + totalAmountWithTaxes + " INR", "GST Breakdown", JOptionPane.INFORMATION_MESSAGE);

                        // Generate a secure random ticket number
                        SecureRandom secureRandom = new SecureRandom();
                        int ticketNumber = 1000 + secureRandom.nextInt(9000);

                        FileWriter fileWriter;
                        try {
                            fileWriter = new FileWriter("movie_bookings.txt", true);
                            fileWriter.write("Movie: " + movie + ", Time Slot: " + timeSlot + "\n");
                            fileWriter.write("Name: " + name + ", Phone Number: " + userPhoneNumber + ", Email: " + validEmail + "\n");
                            fileWriter.write("Selected Seats: " + selectedSeats.toString() + "\n");
                            fileWriter.write("Base Amount: " + totalAmount + " INR\n");
                            fileWriter.write("CGST: " + cgst + " INR\n");
                            fileWriter.write("SGST: " + sgst + " INR\n");
                            fileWriter.write("Total Amount (including taxes): " + totalAmountWithTaxes + " INR\n");
                            fileWriter.write("Ticket Number: " + ticketNumber + "\n\n");
                            fileWriter.close();

                        JOptionPane.showMessageDialog(bookingFrame, "Thank you for booking!\nAmount Payable (including taxes): " + totalAmountWithTaxes + " INR\nTicket Number: " + ticketNumber, "Booking For "+movie, JOptionPane.INFORMATION_MESSAGE);
                        bookingFrame.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();  // Add better error handling or logging
                        }
                    } else {
                        JOptionPane.showMessageDialog(bookingFrame, "Please enter your name.", "Booking Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(bookingFrame, "Please select at least one seat.", "Booking Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bookingFrame.setVisible(true);
    }
}