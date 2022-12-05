package Panels.Stores.StoreDetails.StorePhotoPanels;

import org.bson.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class StorePhotoDisplayPanel extends JPanel {
    private ArrayList<Document> photosDocList;
    private int currentImageNum;
    private int lastImageNum;
    private Image currentImage;
    private JButton rightImageButton;
    private JButton leftImageButton;
    public StorePhotoDisplayPanel(ArrayList<Document> phDocList)
    {
        photosDocList=phDocList;
        currentImageNum=0;
        lastImageNum=phDocList.size()-1;
        currentImage=GetImage(phDocList.get(0));
        Load();
    }
    private void Load()
    {
        this.setPreferredSize(new Dimension(580,520));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new BorderLayout());
        LoadRightButton();
        LoadLeftButton();
        this.setVisible(true);
    }
    private void LoadRightButton()
    {
        rightImageButton=new JButton();
        rightImageButton.setIcon(new ImageIcon("src/resources/ButtonIcons/arrow-right.png"));
        rightImageButton.setPreferredSize(new Dimension(50,100));
        rightImageButton.setMaximumSize(new Dimension(50,100));
        rightImageButton.setMinimumSize(new Dimension(50,100));
        this.add(rightImageButton,BorderLayout.EAST);
        rightImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentImageNum==lastImageNum)
                {
                    currentImageNum=0;
                }
                else
                {
                    currentImageNum=currentImageNum+1;
                }
                ChangeImage();
            }
        });
    }
    private void LoadLeftButton()
    {
        leftImageButton=new JButton();
        leftImageButton.setIcon(new ImageIcon("src/resources/ButtonIcons/arrow-left.png"));
        leftImageButton.setPreferredSize(new Dimension(50,100));
        leftImageButton.setMaximumSize(new Dimension(50,100));
        leftImageButton.setMinimumSize(new Dimension(50,100));
        this.add(leftImageButton,BorderLayout.WEST);
        leftImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentImageNum==0)
                {
                    currentImageNum=lastImageNum;
                }
                else
                {
                    currentImageNum=currentImageNum-1;
                }
                ChangeImage();
            }
        });
    }
    private void ChangeImage()
    {
        currentImage=GetImage(photosDocList.get(currentImageNum));
        this.revalidate();
        this.repaint();
    }
    private Image GetImage(Document imageDoc)
    {
        String photoReference=imageDoc.get("photo_reference").toString();
        long photoWidth=(long)imageDoc.get("width");
        long photoHeight=(long)imageDoc.get("height");
        String photoFullStr = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + photoReference + "&sensor=false&maxheight=" + photoWidth + "&maxwidth=" + photoHeight + "&key=" + "AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes";
        System.out.println("THE PHOTO STRING IS: " + photoFullStr);
        Image returnedImage=new ImageIcon("src/resources/BackgroundImages/colosseum.png").getImage();
        try {
            URL photoUrl = new URL(photoFullStr);
            returnedImage = ImageIO.read(photoUrl.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedImage;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(backgroundImage,0,0,null);
        g.setClip(new Rectangle2D.Float(0,0,getSize().width-1, getSize().height-1));
        g.drawImage(currentImage, 0, 0, getSize().width-1, getSize().height-1, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        this.revalidate();
        this.repaint();
    }
}
