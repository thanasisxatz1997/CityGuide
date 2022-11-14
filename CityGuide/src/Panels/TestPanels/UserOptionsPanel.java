package Panels.TestPanels;

import Forms.ChagneInfoForms.ChangeEmailForm;
import Forms.ChagneInfoForms.ChangePasswordForm;
import Forms.ChagneInfoForms.ChangeUsernameForm;
import LogInManager.Forms.IntroPage;
import Repository.CurrentUser;
import Repository.Handlers;
import org.checkerframework.checker.units.qual.C;

import java.util.Timer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;

public class UserOptionsPanel extends JPanel {
    private JLabel statusLabel;
    private JButton logIn_OutButton;
    private Font customSmallFont;
    private Font customLargeFont;
    private JLabel nameDisplayLabel;
    private JLabel emailDisplayLabel;
    private JLabel sessionRuntimeLabel;
    private JButton usernameChangeButton;
    private JButton emailChangeButton;
    private JButton passwordChangeButton;
    private JLabel passwordDisplayLabel;


    public UserOptionsPanel()
    {
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(400,600));
        this.setMaximumSize(new Dimension(400,600));

        Border margin=new EmptyBorder(10,0,10,10);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        Border raisedBorder=this.getBorder();
        this.setBorder(new CompoundBorder(raisedBorder,margin));

        this.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.anchor=GridBagConstraints.NORTH;

        LoadSmallFont("src/resources/Fonts/CaviarDreams.ttf");
        LoadLargeFont("src/resources/Fonts/Roman SD.ttf");
        LoadStatusLabel(c);
        LoadLogIn_OutButtons(c);
        if(CurrentUser.IsLoggedIn())
        {
            //Here we load the components that appear only when user is logged in
            LoadUserDisplayLabels(c);
        }

        this.setVisible(true);
    }
    public void LoadStatusLabel(GridBagConstraints c)
    {
        statusLabel=new JLabel();
        statusLabel.setFont(customSmallFont);
        if (CurrentUser.IsLoggedIn())
        {
            statusLabel.setText("Welcome to Rome City Guide!");
        }
        else
        {
            statusLabel.setText("Please log in for more features!");
        }
        //c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(15,10,0,0);
        c.gridwidth=2;
        c.gridheight=1;
        c.weightx=0.5;
        c.weighty=0.5;
        c.gridx=0;
        c.gridy=0;
        this.add(statusLabel,c);
    }
    public void LoadLogIn_OutButtons(GridBagConstraints c)
    {
        //c.fill=GridBagConstraints.HORIZONTAL;

        c.gridwidth=1;
        c.gridheight=1;
        c.weightx=0.5;
        c.weighty=0.5;
        c.gridx=2;
        c.gridy=0;
        JButton logIn_OutButton=new JButton();
        if(CurrentUser.IsLoggedIn())
        {
            c.insets=new Insets(10,0,0,50);
            logIn_OutButton.setText("Log Out!");
            logIn_OutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int a = JOptionPane.showConfirmDialog(logIn_OutButton, "Are you sure?");
                    // JOptionPane.setRootFrame(null);
                    if (a == JOptionPane.YES_OPTION) {
                        CurrentUser.LogOut();
                        //TestMainForm.dispose();
                        invalidate();
                        validate();
                        repaint();
                        //Forms.TestMainForm obj = new Forms.TestMainForm();
                        //obj.setVisible(true);
                    }

                    //dispose();
                    //Forms.TestMainForm obj = new Forms.TestMainForm();
                    //obj.setVisible(true);
                }
            });
        }
        else
        {
            c.insets=new Insets(15,0,500,50);
            logIn_OutButton.setText("Log In!");
            logIn_OutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new IntroPage();
                }
            });
        }
        this.add(logIn_OutButton,c);
    }

    private void LoadUserDisplayLabels(GridBagConstraints c)
    {
        c.insets=new Insets(5,0,0,0);
        nameDisplayLabel=new JLabel("Username: "+ CurrentUser.userName);
        nameDisplayLabel.setFont(customSmallFont);
        c.gridx=0;
        c.gridy=1;
        this.add(nameDisplayLabel,c);
        usernameChangeButton=new JButton("Change");
        c.insets=new Insets(0,0,0,50);
        c.gridx=2;
        c.gridy=1;
        usernameChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeUsernameForm(new JFrame(String.valueOf(getParent())));
            }
        });
        this.add(usernameChangeButton,c);

        emailDisplayLabel=new JLabel("Email: "+CurrentUser.userEmail);
        c.insets=new Insets(5,0,0,0);
        emailDisplayLabel.setFont(customSmallFont);
        c.gridx=0;
        c.gridy=2;
        this.add(emailDisplayLabel,c);
        emailChangeButton=new JButton("Change");
        c.insets=new Insets(0,0,0,50);
        c.gridx=2;
        c.gridy=2;
        emailChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeEmailForm(new JFrame(String.valueOf(getParent())));
            }
        });
        this.add(emailChangeButton,c);

        passwordDisplayLabel=new JLabel("Password: ");
        c.insets=new Insets(5,0,0,0);
        passwordDisplayLabel.setFont(customSmallFont);
        c.gridx=0;
        c.gridy=3;
        this.add(passwordDisplayLabel,c);
        passwordChangeButton=new JButton("Change");
        c.insets=new Insets(0,0,0,50);
        c.gridx=2;
        c.gridy=3;
        this.add(passwordChangeButton,c);
        passwordChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordForm(new JFrame(String.valueOf(getParent())));
            }
        });

        sessionRuntimeLabel=new JLabel("Logged in for: "+ Handlers.ShowCurrentRunTime()+"seconds");
        sessionRuntimeLabel.setFont(customSmallFont);
        c.insets=new Insets(0,0,400,0);
        c.gridx=0;
        c.gridy=4;
        this.add(sessionRuntimeLabel,c);
        LoadSessionTimer();
    }
    private void LoadSessionTimer()
    {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sessionRuntimeLabel.setText("Logged in for: "+ Handlers.ShowCurrentRunTime()+"seconds");
            }
        },1*1000,1*1000);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
