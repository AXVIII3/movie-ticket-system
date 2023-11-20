import java.util.Scanner;

import managers.ConsoleManager;
import managers.GUICreator;
import managers.BookingManager;

import static managers.DataManager.ResetData;

public class Main
{
    public static void main(String[] args)
    {
        BookingManager.InitializeMovieList();
        ConsoleManager.scanner = new Scanner(System.in);
        ConsoleManager.Start();
    }
}
