package LogInManager.Forms;

import LogInManager.Managers.ConnectToDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IntroPage extends JFrame{
    private JPanel panel1;
    private JButton openLogin;
    private JButton openRegister;
    private JLabel welcomeLabel;
    private JLabel logInLabel;
    private JLabel registerLabel;
    public static JFrame frame;
    private Font customWelcomeFont;
    private Font customLogInFont;
    private Image backgroundImage;
    private Graphics g;


    public IntroPage(){
        new ConnectToDataBase();

        frame=new JFrame("Rome City Guide");
        frame.setIconImage(new ImageIcon("src/resources/Icons/colosseumIcon.png").getImage());
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 474));
        frame.setResizable(false);
        frame.add(panel1);


        LoadLogInFont("src/resources/Fonts/CaviarDreams.ttf");
        LoadWelcomeFont("src/resources/Fonts/Roman SD.ttf");
        welcomeLabel.setFont(customWelcomeFont);
        logInLabel.setFont(customLogInFont);
        registerLabel.setFont(customLogInFont);

        frame.pack();
        frame.setLocationRelativeTo(null);
        //backgroundImage=new ImageIcon("src/resources/BackgroundImages/WelcomePhotoResized.png").getImage();

        this.pack();
        frame.setVisible(true);

        openLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
            }
        });
        openRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationForm(null);
            }
        });
    }

    private void LoadLogInFont(String path)
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customLogInFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadWelcomeFont(String path) {
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customWelcomeFont = ttfBase.deriveFont(Font.BOLD, 48);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
