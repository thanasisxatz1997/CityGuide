package MainGui.Panels.Home;

//import chrriis.dj.nativeswing.NativeComponentWrapper;
//import chrriis.dj.nativeswing.swtimpl.NativeInterface;
//import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.*;
import java.awt.*;

public class HomeVideoFrame extends JFrame {
    public HomeVideoFrame()
    {
        Load();
    }
    private void Load()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,600));
        this.setResizable(false);
        //this.getContentPane().add(getBrowserPanel());
        //NativeComponentWrapper ncw = new NativeComponentWrapper(getBrowserPanel());
        //this.add(ncw.createEmbeddableComponent());
        this.pack();
        this.setVisible(true);
    }

    /*public static JPanel getBrowserPanel() {

        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);
        webBrowser.navigate("https://www.youtube.com/v/b-Cr0EWwaTk?fs=1");
        return webBrowserPanel;
    }*/
}
