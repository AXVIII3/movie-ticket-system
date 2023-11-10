import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import gui.Button;
import gui.Frame;
import gui.Panel;

public class Panels
{
    private static Frame frame;

    public static void InitializeFrame()
    {
        frame = new Frame();

        frame.setVisible(true);
		frame.setSize(1093, 614);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setTitle("Movie Ticket System ~ P.B.A.S.");
		frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);

		ImageIcon icon = new ImageIcon("assets/Logo.png");
		frame.setIconImage(icon.getImage());
    }

    public static Frame GetFrame()
    {
        return frame;
    }

    public static void MainScreen()
    {
        Panel panel = new Panel("Main");
    	GridBagLayout layout = new GridBagLayout();
        GridBagConstraints logoLabelConstraints = new GridBagConstraints();
        GridBagConstraints tagLineLabelConstraints = new GridBagConstraints();
        GridBagConstraints bookNowButtonConstraints = new GridBagConstraints();

        Border panelBorder = BorderFactory.createLineBorder(new Color(0xff7a7a), 3);
        Border buttonBorder = BorderFactory.createLineBorder(new Color(0xb55757), 2);

    	JLabel logoLabel = new JLabel();
    	JLabel tagLineLabel = new JLabel();
    	Button bookNowButton = new Button("Book Now!");

        panel.setLayout(layout);
        panel.setBorder(panelBorder);
        panel.setGradient(new Color(0x111f25), new Color(0x1d0e0e), 30);

        logoLabelConstraints.gridy = 0;
        logoLabelConstraints.gridx = 0;
        logoLabel.setText("Showtime Hub 🎬");
        logoLabel.setForeground(new Color(0xff7a7a));
        logoLabel.setVerticalAlignment(JLabel.BOTTOM);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalTextPosition(JLabel.LEFT);
        logoLabel.setVerticalTextPosition(JLabel.CENTER);
        logoLabel.setFont(new Font("Roboto", Font.BOLD, 70));
        ImageIcon icon = new ImageIcon("assets/Logo.png");
        logoLabel.setIcon(icon);

        tagLineLabelConstraints.gridy = 1;
        tagLineLabelConstraints.gridx = 0;
        tagLineLabel.setText("Incredible cinematic experiences since 2006!");
        tagLineLabel.setForeground(new Color(0xDA6868));
        tagLineLabel.setVerticalAlignment(JLabel.TOP);
        tagLineLabel.setHorizontalAlignment(JLabel.CENTER);
        tagLineLabel.setFont(new Font("Roboto", Font.BOLD, 30));

        bookNowButtonConstraints.gridy = 2;
        bookNowButtonConstraints.gridx = 0;
        bookNowButtonConstraints.insets = new Insets(100, 0, 0, 0);
        bookNowButton.setPreferredSize(new Dimension(350, 50));
        bookNowButton.setMinimumSize(new Dimension(200, 45));
        bookNowButton.setMaximumSize(new Dimension(400, 55));
        bookNowButton.setBorder(buttonBorder);
        bookNowButton.setForeground(new Color(0x0e1a1e));
        bookNowButton.setBackground(new Color(0xff7a7a));
        bookNowButton.setPressedBackgroundColor(new Color(0xb55757));
        bookNowButton.setFont(new Font("Roboto", Font.BOLD, 20));
        bookNowButton.addActionListener(e -> frame.openPanel("MovieSelect"));

        panel.add(logoLabel, logoLabelConstraints);
        panel.add(tagLineLabel, tagLineLabelConstraints);
        panel.add(bookNowButton, bookNowButtonConstraints);

        frame.addNewPanel(panel);
    }

    public static void MovieSelectScreen()
    {
        Panel panel = new Panel("MovieSelect");
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints logoLabelConstraints = new GridBagConstraints();

        Border panelBorder = BorderFactory.createLineBorder(new Color(0xff7a7a), 3);

        JLabel logoLabel = new JLabel();

        panel.setLayout(layout);
        panel.setBorder(panelBorder);
        panel.setGradient(new Color(0x111f25), new Color(0x1d0e0e), 30);

        logoLabelConstraints.gridy = 0;
        logoLabelConstraints.gridx = 0;
        logoLabel.setText("Select Movie: ");
        logoLabel.setForeground(new Color(0xff7a7a));
        logoLabel.setVerticalAlignment(JLabel.BOTTOM);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalTextPosition(JLabel.LEFT);
        logoLabel.setVerticalTextPosition(JLabel.CENTER);
        logoLabel.setFont(new Font("Roboto", Font.BOLD, 70));

        panel.add(logoLabel, logoLabelConstraints);

        frame.addNewPanel(panel);
    }
}
