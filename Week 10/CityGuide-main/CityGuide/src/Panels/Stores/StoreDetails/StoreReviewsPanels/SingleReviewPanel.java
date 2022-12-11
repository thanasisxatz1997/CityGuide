package Panels.Stores.StoreDetails.StoreReviewsPanels;

import javax.swing.*;
import java.awt.*;

public class SingleReviewPanel extends JPanel {
    private JLabel nameLabel;
    private JTextArea commentTextArea;
    private String nameStr;
    private String commentStr;
    private JScrollPane commentScrollPane;
    public SingleReviewPanel(String name,String comment,String rating)
    {
        nameStr=name+"  \t  "+rating;
        commentStr=comment;
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(400,100));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new BorderLayout());
        nameLabel=new JLabel(nameStr);
        nameLabel.setHorizontalTextPosition(JLabel.LEFT);
        nameLabel.setIcon(new ImageIcon("src/resources/ButtonIcons/star-icon.png"));
        commentTextArea=new JTextArea(commentStr);
        commentTextArea.setMinimumSize(new Dimension(400,70));
        commentScrollPane=new JScrollPane();
        commentScrollPane.setMaximumSize(new Dimension(400,70));
        commentScrollPane.setPreferredSize(new Dimension(400,70));
        commentScrollPane.setViewportView(commentTextArea);
        this.add(nameLabel,BorderLayout.NORTH);
        this.add(commentScrollPane,BorderLayout.SOUTH);
    }
}
