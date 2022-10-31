package Repository;

public class CurrentUser {
    public static boolean loggedIn=false;
    public static String userName;
    public static String userEmail;
    public CurrentUser()
    {
        loggedIn=false;
    }
    public static boolean IsLoggedIn()
    {
        return loggedIn;
    }
}
