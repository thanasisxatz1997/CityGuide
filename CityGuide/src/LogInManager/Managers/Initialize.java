package LogInManager.Managers;

import LogInManager.Forms.IntroPage;

import javax.swing.*;

public class Initialize {
    ConnectToDataBase DbConnection;
    IntroPage startingForm;
    public Initialize()
    {
        DbConnection=new ConnectToDataBase();
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
        startingForm=new IntroPage();
    }


}
