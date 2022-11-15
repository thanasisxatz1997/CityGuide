package LogInManager.Forms;

import Forms.TestMainForm;
import LogInManager.Managers.DataManager;
import Repository.CurrentUser;
import Repository.Handlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{
    private JTextField nameTextField;
    private JPanel panel1;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JFrame frame;

    public LoginPage() {
        panel1.setBackground(new Color(108,139,218));
        frame = new JFrame("Login Frame");
        nameLabel.setIcon(new ImageIcon("user_resized.png"));
        passwordLabel.setIcon(new ImageIcon("password_resized.png"));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(350, 300));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack(); //all components are sized so they are visible
        frame.setLocationRelativeTo(null); //frames form at center of the screen
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DataManager.CheckLogin(nameTextField.getText(),passwordTextField.getText()))
                {
                    System.out.println("DONE");
                    JOptionPane.showMessageDialog(null,
                            "Log In Successful!",
                            "Welcome!",
                            JOptionPane.ERROR_MESSAGE);
                    CurrentUser.loggedIn=true;
                    Handlers.startTime=System.nanoTime();
                    CurrentUser.userName=nameTextField.getText();
                    CurrentUser.userEmail=DataManager.GetEmail(nameTextField.getText());
                    TestMainForm.mainPanel.backgroundPanel.AddUserPanel();
                    frame.dispose();
                    IntroPage.frame.dispose();
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Wrong Credentials!",
                            "Try Again!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



}
