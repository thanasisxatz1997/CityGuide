package Forms;

import LogInManager.Managers.DataManager;
import Repository.CurrentUser;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ChangeEmailForm extends JFrame{

    private JPanel mainPanel;
    JLabel orgemailLabel;
    private Font customSmallFont;
    JLabel orgemailTextField;
    JLabel newemailLabel;
    JTextField newemailTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton applychangesButton;



    public ChangeEmailForm(JFrame parent) {
        super();
        Load();
    }

    private void Load() {

        setSize(new Dimension(400,400));
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
                String newemail = newemailTextField.getText();
                //String pswrd = String.valueOf(passwordField.getPassword());
                //DataManager.DbCollection.updateOne(CurrentUser.userName);
                CurrentUser.userEmail = newemailTextField.getText();
                /*try{
                    PreparedStatement st = (PreparedStatement) con.prepareStatement();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }*/
                //LogInManager.Managers.DataManager.PushData(String newname);
                DataManager.DbCollection.insertOne(new Document("email",newemail));
                dispose();
            }
        });
        this.add(applychangesButton,c);
    }

    private void LoadLabels(GridBagConstraints c) {
        orgemailLabel = new JLabel("Current Email");
        orgemailLabel.setFont(customSmallFont);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=0;
        this.add(oldemailLabel,c);

        newemailLabel = new JLabel("New Email");
        newemailLabel.setFont(customSmallFont);
        c.insets=new Insets(1,1,1,20);
        c.weightx=1;
        c.weighty=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(newemailLabel,c);

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
        orgemailTextField=new JLabel(CurrentUser.userEmail);
        orgemailTextField.setFont(customSmallFont);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=0;
        this.add(oldemailTextField,c);


        newemailTextField = new JTextField();
        c.insets = new Insets(1,1,1,1);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridwidth=2;
        c.ipadx=1;
        c.ipady=0;
        c.gridx=1;
        c.gridy=1;
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


}
