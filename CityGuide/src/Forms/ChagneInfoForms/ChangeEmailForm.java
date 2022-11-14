package Forms.ChagneInfoForms;

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

public class ChangeEmailForm extends JFrame {
    private JPanel mainPanel;
    JLabel oldemailLabel;
    private Font customSmallFont;
    JLabel oldemailTextField;
    JLabel newemailLabel;
    JTextField newemailTextField;
    JLabel passwordLabel;
    JTextField passwordField;
    JButton applychangesButton;
    JLabel label;
    JLabel testPasswordLabel;
    private Font customLargeFont;

    public ChangeEmailForm(JFrame parent) {
        super();
        Load();
    }

    private void Load() {

        this.setSize(400,400);
        setTitle("Change Email");
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
                if (pass.equals(decryptpass)){
                    if(!newemailTextField.getText().trim().isEmpty())
                    {
                        if(ValidEmailAddress(newemailTextField.getText()))
                        {
                            Document found = (Document) DataManager.DbCollection.find(new Document("email", CurrentUser.userEmail)).first();
                            if(found != null){
                                String newemail = newemailTextField.getText();
                                Bson updatevalue = new Document("email",newemail);
                                Bson updateoperation = new Document("$set",updatevalue);
                                DataManager.DbCollection.updateOne(found,updateoperation);
                                CurrentUser.userEmail = newemail;
                                JOptionPane.showMessageDialog(null,"Email Changed Successfully","Done",JOptionPane.WARNING_MESSAGE );
                                dispose();}

                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Invalid email! Please Try again","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No new email has been given","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect Password!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(applychangesButton,c);
    }

    private void LoadLabels(GridBagConstraints c) {
        label = new JLabel("Change Email");
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

        oldemailLabel = new JLabel("Current Email");
        oldemailLabel.setFont(customSmallFont);
        oldemailLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(oldemailLabel,c);

        newemailLabel = new JLabel("New Email");
        newemailLabel.setFont(customSmallFont);
        newemailLabel.setForeground(Color.WHITE);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(newemailLabel,c);

        c.insets=new Insets(5,0,0,0);
        oldemailTextField=new JLabel(CurrentUser.userEmail);
        oldemailTextField.setFont(customSmallFont);
        oldemailTextField.setForeground(Color.WHITE);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=1;
        this.add(oldemailTextField,c);

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

        newemailTextField = new JTextField();
        c.insets = new Insets(1,1,1,20);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=2;
        this.add(newemailTextField,c);

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

    private boolean  ValidEmailAddress(String email)
    {
        if(email.matches(("^[A-Za-z0-9+_.-]+@(.+)$")))
        {
            System.out.println("Email valid");
            return true;
        }
        else
        {
            System.out.println("Email NOT valid");
            return false;
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


