package LogInManager.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroPage extends JFrame{
    private JPanel panel1;
    private JButton openLogin;
    private JButton openRegister;
    private JLabel welcomeLabel;
    private JFrame frame;

    public IntroPage(){

        frame=new JFrame("Intro Frame");
        frame.setTitle("Welcome Page");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 474));
        frame.setResizable(false);
        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
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


}
