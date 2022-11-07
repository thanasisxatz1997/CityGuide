package Forms;

import LogInManager.Managers.ConnectToDataBase;
import LogInManager.Managers.DataManager;
import Repository.ConnectToDatabase;
import Repository.CurrentUser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;

public class ChangeUsernameForm extends JFrame{

    private JPanel mainPanel;
    JLabel oldusernameLabel;
    private Font customSmallFont;
    JLabel oldusernameTextField;
    JLabel newusernameLabel;
    JTextField newusernameTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton applychangesButton;



    public ChangeUsernameForm(JFrame parent) {
        super();
        Load();
    }

    private void Load() {

        setSize(new Dimension(400,400));
        setTitle("Change Username");
        getContentPane().setBackground(new Color(108,139,219));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        LoadLabels(c);
        LoadTextFields(c);
        LoadPasswordFields(c);
        LoadApplyButton(c);
        LoadSmallFont("src/resources/Fonts/CaviarDreams.ttf");


        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void LoadPasswordFields(GridBagConstraints c) {
       /* passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        c.insets = new Insets(1,1,1,1);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=2;
        this.add(passwordField,c);*/
    }

    private void LoadApplyButton(GridBagConstraints c) {
        applychangesButton = new JButton("Apply Changes");
        c.insets = new Insets(1,1,1,1);
        c.weightx=0.5;
        c.weighty=1;
        c.gridwidth=0;
        c.gridheight=1;
        c.gridx=2;
        c.gridy=4;
        applychangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newname = newusernameTextField.getText();
                String email = CurrentUser.userEmail;
                //String pswrd = String.valueOf(passwordField.getPassword());
                //DataManager.DbCollection.updateOne(CurrentUser.userName);
                CurrentUser.userName = newusernameTextField.getText();
                /*try{
                    PreparedStatement st = (PreparedStatement) con.prepareStatement();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }*/
                //LogInManager.Managers.DataManager.PushData(String newname);
                DataManager.DbCollection.insertOne(new Document("name",newname));
                dispose();
            }
        });
        this.add(applychangesButton,c);
    }

    private void LoadLabels(GridBagConstraints c) {
        oldusernameLabel = new JLabel("Current Username");
        oldusernameLabel.setFont(customSmallFont);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=0;
        this.add(oldusernameLabel,c);

        newusernameLabel = new JLabel("New Username");
        newusernameLabel.setFont(customSmallFont);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(newusernameLabel,c);

        /*passwordLabel = new JLabel("Enter your password to confirm ");
        passwordLabel.setFont(customSmallFont);
        c.insets=new Insets(1,1,1,1);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(passwordLabel,c);*/

    }

    private void LoadTextFields(GridBagConstraints c) {

        c.insets=new Insets(5,0,0,0);
        oldusernameTextField=new JLabel(CurrentUser.userName);
        oldusernameTextField.setFont(customSmallFont);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=0;
        this.add(oldusernameTextField,c);


        newusernameTextField = new JTextField();
        c.insets = new Insets(1,1,1,1);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=1;
        this.add(newusernameTextField,c);

    }

    private void LoadSmallFont(String path)
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customSmallFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
