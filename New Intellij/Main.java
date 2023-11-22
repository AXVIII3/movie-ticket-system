import Managers.AccountsManager;
import Managers.BookingManager;
import Managers.DataManager;
import Managers.GuiAppManager;
import Utilities.Security;
import Utilities.Visuals;

public class Main
{

    public static void main(String[] args)
    {
        Initialize();
        Play();
    }

    private static void Initialize()
    {
        BookingManager.Initialize();
        DataManager.Initialize();
        Security.Initialize();
        AccountsManager.Initialize();
        Visuals.Initialize();
        GuiAppManager.Initialize();
    }

    private static void Play()
    {
        GuiAppManager.StartGUI();
    }
}
