package MainGui.Forms.ChangeInfoForms;

import LogInManager.Managers.DataManager;
import Repository.CurrentUser;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ChangeUsernameForm extends JFrame {
    private JPanel mainPanel;
    JLabel oldusernameLabel;
    private Font customSmallFont;
    JLabel oldusernameTextField;
    JLabel newusernameLabel;
    JTextField newusernameTextField;
    JLabel passwordLabel;
    JTextField passwordField;
    JButton applychangesButton;
    JLabel label;
    JLabel testPasswordLabel;
    private Font customLargeFont;

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
        passwordField = new JPasswordField();
        c.insets = new Insets(1,1,1,20);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=2;
        c.gridy=3;
        this.add(passwordField,c);
    }

    private void LoadApplyButton(GridBagConstraints c) {
        applychangesButton = new JButton("Apply Changes");
        applychangesButton.setBounds(5,5,3,1);
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(3,1,1,1);
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=0;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=4;
        applychangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = passwordField.getText().toString();
                String enctyptpass = DataManager.GetEncPass(CurrentUser.userName);
                String encKey = DataManager.GetEncKey(CurrentUser.userName);
                String decryptpass = DataManager.Decrypt(enctyptpass,encKey);
                //System.out.println("Passsword " + decryptpass);
                if (pass.equals(decryptpass)){
                    if(!newusernameTextField.getText().trim().isEmpty())
                    {
                        Document found = (Document) DataManager.DbCollection.find(new Document("name",CurrentUser.userName)).first();
                        if(found != null){
                            String newname = newusernameTextField.getText();
                            Bson updatevalue = new Document("name",newname);
                            Bson updateoperation = new Document("$set",updatevalue);
                            DataManager.DbCollection.updateOne(found,updateoperation);
                            CurrentUser.userName = newname;
                            JOptionPane.showMessageDialog(null,"Name Changed Successfully","Done",JOptionPane.WARNING_MESSAGE );
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"No new username has been given","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect Password!","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        this.add(applychangesButton,c);
    }

    private void LoadLabels(GridBagConstraints c) {
        label = new JLabel("Change Username");
        label.setFont(customLargeFont);
        label.setForeground(Color.WHITE);
        c.insets=new Insets(15,1,1,1);
        c.weightx=1;
        c.weighty=0;
        c.gridwidth=3;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=0;
        this.add(label,c);

        oldusernameLabel = new JLabel("Current Username:");
        oldusernameLabel.setFont(customSmallFont);
        oldusernameLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(oldusernameLabel,c);

        newusernameLabel = new JLabel("New Username");
        newusernameLabel.setFont(customSmallFont);
        newusernameLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(newusernameLabel,c);

        c.insets=new Insets(5,0,0,0);
        oldusernameTextField=new JLabel(CurrentUser.userName);
        oldusernameTextField.setFont(customSmallFont);
        oldusernameTextField.setForeground(Color.WHITE);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=1;
        this.add(oldusernameTextField,c);

        passwordLabel = new JLabel("Enter your password to confirm ");
        passwordLabel.setFont(customSmallFont);
        passwordLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,1);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=3;
        this.add(passwordLabel,c);

        /*testPasswordLabel = new JLabel("Password " + decryptpass);
        testPasswordLabel.setFont(customSmallFont);
        testPasswordLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,1);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=6;
        this.add(testPasswordLabel,c);*/

    }

    private void LoadTextFields(GridBagConstraints c) {
        newusernameTextField = new JTextField();
        c.insets = new Insets(1,1,1,20);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=2;
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

    private void LoadLargeFont(String path) {
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customLargeFont = ttfBase.deriveFont(Font.BOLD, 48);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}