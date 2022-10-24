package Forms;

import Managers.DataManager;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Managers.DataManager.DbCollection;

public class LogInForm {
    JFrame frame;
    JButton logInButton;
    JButton registerButton;
    JTextField nameTextField;
    JTextField passwordTextField;

    public LogInForm()
    {
        Load();
    }
    public void Load()
    {
        nameTextField=new JTextField();
        passwordTextField=new JTextField();
        nameTextField.setVisible(true);
        nameTextField.setBounds(100,100,200,20);
        passwordTextField.setBounds(100,150,200,20);
        passwordTextField.setVisible(true);
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(600,600);
        frame.add(nameTextField);
        frame.add(passwordTextField);
        logInButton=new JButton("Log In");
        logInButton.setBounds(100,500,100,50);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DataManager.CheckLogin(nameTextField.getText(),passwordTextField.getText()))
                {
                    System.out.println("DONE");
                    JOptionPane.showMessageDialog(null,
                            "Log In Successful!",
                            "Welcome!",
                            JOptionPane.ERROR_MESSAGE);
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
        frame.add(logInButton);

        registerButton.addActionListener(new ActionListener){
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationForm(null);
            }
        }
    }
}
