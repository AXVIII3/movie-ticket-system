package Utilities;

public class Account
{
    public String holderName;
    public String holderEmail;
    public String holderPassword;

    public Account(String[] data)
    {
        holderName = data[0];
        holderEmail = data[1];
        holderPassword = Security.decrypt(data[2]);
    }
}
