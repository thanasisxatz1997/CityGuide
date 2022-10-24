package Managers;

import Forms.IntroPage;
import Forms.RegistrationForm;

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
    public static void main(String[] args) {
        new Initialize();
    }

}
