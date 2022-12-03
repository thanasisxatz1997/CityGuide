package Main;
import LogInManager.Forms.IntroPage;
import Repository.*;


import javax.swing.*;


public class Initialize {
    public Initialize()
    {
        Handlers.startTime=System.nanoTime();
        UIManager.LookAndFeelInfo[] looks= UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            System.out.println("Look Changed");
        }
        catch (Exception ignored)
        {
            System.out.println("Look  NOT Changed");
        }
        new ConnectToDatabase();
        // connect to city guide db
        // new ConnectToCityGuideDB();

        new Forms.TestMainForm();
        //new IntroPage();
        new CurrentUser();
        //APIinfoplaces.GetInfoFromAPI();
        //new Forms.MainForm();
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
