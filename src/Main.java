import java.util.Scanner;

import managers.BookingManager;
import managers.ConsoleManager;
import managers.GUIManager;
import managers.DataManager;

public class Main
{
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        BookingManager.InitializeMovieList();

        GUIManager.Start();

//        System.out.println("MENU:-");
//        System.out.println("1) Start Console App");
//        System.out.println("2) Start GUI App");
//        System.out.println("3) Exit");
//        System.out.print("Choice (1/2/3): ");
//
//        switch (scanner.nextByte())
//        {
//            case 1: ConsoleManager.scanner = scanner;
//                    ConsoleManager.Start();
//                    break;
//            case 2: GUIManager.Start();
//                    System.out.println("\nGUI App Started! Logging here.");
//                    break;
//            case 3: break;
//            case 127: System.out.println("\nResetting Data!");
//                      DataManager.ResetData();
//                      break;
//            default: System.out.println("\nInvalid Choice! Retry.\n");
//                     main(args);
//        }
//
//        scanner.close();
    }
}
