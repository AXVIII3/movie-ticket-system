package Managers;

import Utilities.Account;
import Utilities.Security;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountsManager
{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static Account[] Accounts;
    public static Account currentAccount;

    public static void Initialize()
    {
        String[] accountData = DataManager.ProcessAccountsData();
        Accounts = new Account[accountData.length];

        int i = 0;
        for (String data : accountData)
        {
            Accounts[i++] = new Account(data.trim().split("\\r?\\n"));
        }
    }

    public static boolean IsAccountRegistered(String email)
    {
        for (Account account : Accounts)
        {
            if (account.holderEmail.trim().equals(email.trim()))
                return true;
        }
        return false;
    }

    public static boolean Login(String email, String password)
    {
        for (Account account : Accounts)
        {
            if (account.holderEmail.trim().equals(email.trim()) && account.holderPassword.trim().equals(password.trim()))
            {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    public static void Register(String name, String email, String password)
    {
        Path path = Path.of(DataManager.ACCOUNTS_FILE_PATH);
        try
        {
            Files.writeString(
                    path,
                    name.trim() + "\n" + email.trim() + "\n" + Security.encrypt(password.trim()) + "\n\n",
                    StandardOpenOption.APPEND);
            Initialize();
            for (Account account : Accounts)
                if (account.holderEmail.trim().equals(email.trim()) && account.holderPassword.trim().equals(password.trim()))
                    currentAccount = account;
            GuiAppManager.StartBooking();
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Writing Account Details In " + path);
            System.exit(0);
        }
    }

    public static boolean IsInvalidEmail(String emailStr)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return !matcher.matches();
    }
}
