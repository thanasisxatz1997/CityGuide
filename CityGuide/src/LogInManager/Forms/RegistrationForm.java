package LogInManager.Forms;

import LogInManager.Managers.DataManager;
import LogInManager.Managers.SendEmail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog {
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnRegister;
    private JButton btnLogIn;
    private JPanel registerPanel;
    private JPasswordField pfConfirmPassword;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel passowrdConfirmLabel;
    private JCheckBox chkboxTerms;
    private ImageIcon labelIcon=new ImageIcon();

    public RegistrationForm(JFrame parent) {
        super(parent);
        registerPanel.setBackground(new Color(108,139,218));
        setSize(630,410);
        //nameLabel.setIcon(new ImageIcon("user_resized.png"));
        //passwordLabel.setIcon(new ImageIcon("password_resized.png"));
        //passowrdConfirmLabel.setIcon(new ImageIcon("password_resized.png"));
        //emailLabel.setIcon(new ImageIcon("email_resized.png"));
        //mageLabel.setIcon(new ImageIcon("ProjectBankIcon.png"));
        setDefaultLookAndFeelDecorated(true);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(50, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();

            }
        });

        setVisible(true);
    }

    private void ClearFields()
    {
        tfUsername.setText("");
        tfEmail.setText("");
        pfPassword.setText("");
        pfConfirmPassword.setText("");
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

    private void registerUser() {
        String name = tfUsername.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            ClearFields();

            return;
        }
        else if(!ValidEmailAddress(email))
        {
            JOptionPane.showMessageDialog(this,
                    "Incorrect Email Address",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            ClearFields();
            return;
        }
        else
        {
            if(DataManager.PushData(name,password,email))
            {
                JOptionPane.showMessageDialog(this,
                        "User Registered successfully",
                        "Done!",
                        JOptionPane.ERROR_MESSAGE);
                SendEmail.SendEmail(email);
                this.dispose();
                new LoginPage();
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "User already exists",
                        "Try again!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }



        /*user = addUserToDatabase(name, email, password);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
    /*


    public User user;
    private User addUserToDatabase(String name, String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, email, password) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.username = name;
                user.email = email;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of: " + user.username);
        }
        else {
            System.out.println("Registration canceled");
        }
    }

}*/