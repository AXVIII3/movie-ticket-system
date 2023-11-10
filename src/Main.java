import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENU:-");
        System.out.println("1) Start Console App");
        System.out.println("2) Start GUI App");
        System.out.println("3) Edit data");
        System.out.println("4) Exit");
        System.out.print("Choice (1/2/3/4): ");

        switch (scanner.nextByte())
        {
            case 1: System.out.println("\nConsole App Here!");
                    break;
            case 2: InitializeGUI();
                    System.out.println("\nGUI App Started! Logging here.");
                    break;
            case 3: System.out.println("\nEditing Data!");
                    break;
            case 4: break;
            default: System.out.println("\nInvalid Choice! Retry.\n");
                     main(args);
        }
    }

    private static void InitializeGUI ()
    {
        Panels.InitializeFrame();
        Panels.MainScreen();
        Panels.MovieSelectScreen();

        Panels.GetFrame().openPanel("Main");
    }
}
