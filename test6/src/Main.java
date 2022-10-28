import javax.swing.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("AWT/Swing");
            frame.setBounds(100, 100, 1024, 768);

            JFXPanel fxPanel = new JFXPanel();
            frame.add(fxPanel);
            frame.setVisible(true);
            Platform.runLater(()->{
                WebView webView = new WebView();
                File f = new File(Main.class.getClassLoader().getResource("mapInfo.html").getFile());
                fxPanel.setScene(new Scene(webView));
                webView.getEngine().load(f.toURI().toString());
            });
            /*
            Platform.runLater(()->{
                WebView webView = new WebView();
                fxPanel.setScene(new Scene(webView));
                webView.getEngine().load("https://maps.google.com");
            });*/
        });
    }
}