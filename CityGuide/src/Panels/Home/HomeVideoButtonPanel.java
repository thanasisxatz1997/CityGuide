package Panels.Home;

//import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeVideoButtonPanel extends JPanel {
    private JButton videoButton;
    public HomeVideoButtonPanel()
    {
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(60,300));
        videoButton=new JButton();
        videoButton.setPreferredSize(new Dimension(50,50));
        videoButton.setIcon(new ImageIcon("src/resources/ButtonIcons/video-camera.png"));
        /*videoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HomeVideoFrame videoFrame=new HomeVideoFrame();
                        NativeInterface.open();
                        NativeInterface.runEventPump();
                        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                            @Override
                            public void run() {
                                    NativeInterface.close();
                            }
                        }));
                    }
                });

            }
        });*/
        this.add(videoButton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
