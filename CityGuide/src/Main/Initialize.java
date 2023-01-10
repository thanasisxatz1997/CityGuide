package Main;
import Repository.ConnectToDatabase;
import Repository.CurrentUser;
import Repository.TimeHandlers;
//import chrriis.dj.nativeswing.NativeSwing;
//import chrriis.dj.nativeswing.swtimpl.NativeInterface;


import javax.swing.*;


public class Initialize {
    public Initialize()
    {
        //NativeSwing.initialize();
        TimeHandlers.startTime=System.nanoTime();
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
            System.out.println("Look NOT Changed");
        }
        new ConnectToDatabase();
        new MainGui.Forms.TestMainForm();
        //new IntroPage();
        new CurrentUser();
        //APIinfoplaces.GetInfoFromAPI();
        //new MainGui.Forms.MainForm();
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
