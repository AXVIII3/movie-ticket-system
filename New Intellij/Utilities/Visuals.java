package Utilities;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class Visuals
{
    public static void Initialize()
    {
        try
        {
            File fontFile = new File("Assets/Font.ttf");
            Fonts.TITLE_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 70f);
            Fonts.SUBTITLE_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 30f);
            Fonts.HEADING_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 55f);
            Fonts.SUBHEADING_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 25f);
            Fonts.NORMAL_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 20f);
            Fonts.SMALL_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 12f);
        }
        catch (Exception e)
        {
            System.out.println("Error Creating Fonts");
            System.exit(0);
        }
    }

    public static class Colors
    {
        public static final Color GRADIENT_1 = new Color(0x101010);
        public static final Color GRADIENT_2 = new Color(0x1A1300);

        public static final Color TEXT_NORMAL = new Color(0xD3AE00);
        public static final Color TEXT_DARKER = new Color(0xC2A000);

        public static final Color BUTTON_NORMAL = new Color(0x14110A);
        public static final Color BUTTON_PRESSED = new Color(0x0A0905);
        public static final Color BUTTON_DISABLED = new Color(0x9D9D9D);
        public static final Color BUTTON_SELECTED = new Color(0xA7EF84);

        public static final Color SCROLLBAR_BACKGROUND = new Color(0x000000);
        public static final Color SCROLLBAR_THUMB = new Color(0x5E4800);
        public static final Color SCROLLBAR_BORDER = new Color(0xFFE4AA);

        public static final Color BORDERS = new Color(0x9D8100);
    }

    public static class Fonts
    {
        public static Font TITLE_FONT;
        public static Font SUBTITLE_FONT;

        public static Font HEADING_FONT;
        public static Font SUBHEADING_FONT;

        public static Font NORMAL_FONT;
        public static Font SMALL_FONT;
    }

    public static class Borders
    {
        public static final Border SCREEN_BORDER = BorderFactory.createLineBorder(Colors.BORDERS, 4);

        public static final Border BUTTON_BORDER = BorderFactory.createLineBorder(Colors.BORDERS, 2);
        public static final Border BUTTON_DISABLED_BORDER =
                BorderFactory.createLineBorder(Colors.BUTTON_DISABLED, 2);

        public static final Border SEAT_BUTTON_BORDER =
                BorderFactory.createLineBorder(Colors.BORDERS, 1);
        public static final Border SEAT_BUTTON_DISABLED_BORDER =
                BorderFactory.createLineBorder(Colors.BUTTON_DISABLED, 1);
        public static final Border SEAT_BUTTON_SELECTED_BORDER =
                BorderFactory.createLineBorder(Colors.BUTTON_SELECTED, 1);
    }
}
