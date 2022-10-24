package LogInManager.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame{
    private JButton withdrawButton;
    private JButton depositButton;
    private JTextField textField1;
    private JTextPane textPane1;
    private JPanel formPanel;
    private JLabel NameLabel;


    public UserForm()
    {
        this.setTitle("projectBank");
        this.setLocation(700,350);
        this.add(formPanel);
        setVisible(true);
        this.setSize(new Dimension(500,500));
       depositButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("hello juice wrld");
           }
       });
       withdrawButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("hello xxx tentacion");
           }
       });
    }

}
