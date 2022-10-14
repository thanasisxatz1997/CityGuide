package Main;
import Forms.MainForm;
import com.google.common.net.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;


import javax.swing.*;


public class Initialize {
    public Initialize()
    {

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
        new MainForm();
    }

    public static void main(String[] args) {
        new Initialize();
    }
}
