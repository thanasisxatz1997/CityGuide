package MainGui.Panels.Stores;

import Repository.CurrentUser;
import Repository.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StoresSinglePanel extends JPanel {
    public JButton buttonSave;
    public JButton buttonDetails;
    public JLabel labelName;
    public JLabel labelPicture;
    public JLabel labelRating;
    public JTextArea textarea;
    public Image backgroundImage;
    public StoresSinglePanel()
    {

        this.setPreferredSize(new Dimension(400,175));
        this.setMaximumSize(new Dimension(400,175));
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        labelName = new JLabel("Activity Name");
        ChangeFont(labelName);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=3;
        c.weightx = 0;
        c.ipadx=0;
        c.insets = new Insets(5,200,0,5);  //padding
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelName, c);

        labelRating = new JLabel("4.3");
        ChangeFont(labelRating);
        labelRating.setIcon(new ImageIcon("src/resources/ButtonIcons/star-icon.png"));
        c.anchor=GridBagConstraints.NORTHEAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.weightx = 1;
        c.ipadx=80;
        c.insets = new Insets(5,0,0,5);  //padding
        c.gridx = 2;
        c.gridy = 1;
        this.add(labelRating, c);

        /*labelPicture = new JLabel("Activity Picture");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=2;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelPicture, c);*/

        /*textarea= new JTextArea("Activity Description");
        textarea.setPreferredSize(new Dimension(100,100));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 40;       //make this component tall
        c.gridx = 2;
        c.gridy = 1;
        this.add(textarea, c);*/

        buttonDetails = new JButton("");
        buttonDetails.setIcon(new ImageIcon("src/resources/ButtonIcons/Logos-Details-icon.png"));
        //buttonDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
        //buttonDetails.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.gridwidth=1;
        c.weightx = 1;
        c.insets = new Insets(5,200,5,100);  //padding
        c.ipady = 15;
        c.gridx = 0;
        c.gridy = 2;
        this.add(buttonDetails, c);


        buttonSave = new JButton("");
        buttonSave.setIcon(new ImageIcon("src/resources/ButtonIcons/Save-icon.png"));
        //buttonSave.setVerticalTextPosition(SwingConstants.LEFT);
        //buttonSave.setHorizontalTextPosition(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.gridwidth=1;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);  //padding
        c.ipady = 15;
        c.gridx = 2;
        c.gridy = 2;
        this.add(buttonSave, c);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentUser.IsLoggedIn())
                {
                    System.out.println("Saving Store to favourites!");
                    if(DataManager.StoreExistsInFavourites(labelName.getText()))
                    {
                    }
                }
                else
                {
                    System.out.println("Please Log in to add store to favourites!");
                }
            }
        });

        this.setVisible(true);

        /*try {
            ImageResizer.resize("src/resources/BackgroundImages/Ellinon_Gefseis_cropped.png", "C:\\Users\\thana\\Desktop\\EllinonGefseisResized.jpg", 240, 175);
        } catch (IOException e) {
            System.out.println("CANT RESIZE");
            e.printStackTrace();
        }
        */
        backgroundImage=new ImageIcon("src/resources/BackgroundImages/EllinonGefseisResized.jpg").getImage();
    }

    private void ChangeFont(JLabel label)
    {
        Font font;
        try {

            InputStream myStream= new BufferedInputStream(new FileInputStream("src/resources/Fonts/CaviarDreams.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            font = ttfBase.deriveFont(Font.BOLD,16);
            System.out.println("FONT CREATED");
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        label.setFont(font);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       g.setColor(new Color(108,139,218));
       //g.fillRect(0,0,240,30);
       //g.drawLine(0,30,240,30);
       g.drawImage(backgroundImage,0,0,null);

       this.revalidate();
       this.repaint();
    }
}
