package MainGui.Forms.ChangeInfoForms;

import LogInManager.Managers.DataManager;
import Repository.CurrentUser;
import com.mongodb.client.MongoCollection;
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

public class ChangePasswordForm extends JFrame{

    private JPanel mainPanel;
    JLabel oldpasswordLabel;
    private Font customSmallFont;
    JLabel oldpasswordTextField;
    JLabel newpasswordLabel;
    JTextField newpasswordTextField;
    JLabel passwordLabel;
    JTextField passwordField;
    JButton applychangesButton;
    JLabel label;
    JLabel testPasswordLabel;
    private Font customLargeFont;


    public ChangePasswordForm(JFrame parent) {
        super();
        Load();
    }

    public static MongoCollection DbCollection;

    private void Load() {

        setSize(new Dimension(400,400));
        setTitle("Change Password");
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
        this.add(applychangesButton,c);
        applychangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = passwordField.getText().toString();
                String enctyptpass = DataManager.GetEncPass(CurrentUser.userName);
                String encKey = DataManager.GetEncKey(CurrentUser.userName);
                String decryptpass = DataManager.Decrypt(enctyptpass,encKey);
                if (pass.equals(decryptpass)){
                    if(!newpasswordTextField.getText().trim().isEmpty())
                    {
                        Document found = (Document) DataManager.DbCollection.find(new Document("password",pass)).first();
                        if(!found.equals(null)){
                            String newpassword = DataManager.Encrypt(newpasswordTextField.getText(),encKey);
                            Bson updatedvalue = new Document("password", newpassword);
                            Bson updateoperation = new Document("$set", updatedvalue);
                            DataManager.DbCollection.updateOne(found,updateoperation);
                            pass = newpassword;
                            JOptionPane.showMessageDialog(null,"Password Changed Successfully","Done",JOptionPane.WARNING_MESSAGE );
                            dispose();
                            System.out.println("Passsword " + decryptpass +" runs" +" runs!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No new password has been given","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Password!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void LoadLabels(GridBagConstraints c) {
        label = new JLabel("Change Password");
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

        /*oldpasswordLabel = new JLabel("Current Password");
        oldpasswordLabel.setFont(customSmallFont);
        oldpasswordLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(oldpasswordLabel,c);*/

        newpasswordLabel = new JLabel("New Password");
        newpasswordLabel.setFont(customSmallFont);
        newpasswordLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(newpasswordLabel,c);

       /* c.insets=new Insets(5,0,0,0);
        String enctyptpass = DataManager.GetEncPass(CurrentUser.userName);
        String encKey = DataManager.GetEncKey(CurrentUser.userName);
        String decryptpass = DataManager.Decrypt(enctyptpass,encKey);
        oldpasswordTextField=new JLabel(decryptpass);
        oldpasswordTextField.setFont(customSmallFont);
        oldpasswordTextField.setForeground(Color.WHITE);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=1;
        this.add(oldpasswordTextField,c);*/

        passwordLabel = new JLabel("Enter old password to confirm ");
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

        /*testPasswordLabel = new JLabel("Password " + CurrentUser.userPassword);
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
        newpasswordTextField = new JTextField();
        c.insets = new Insets(1,1,1,20);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=2;
        this.add(newpasswordTextField,c);

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