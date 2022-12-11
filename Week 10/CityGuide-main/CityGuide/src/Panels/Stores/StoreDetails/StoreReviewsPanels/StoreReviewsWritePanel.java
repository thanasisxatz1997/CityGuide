package Panels.Stores.StoreDetails.StoreReviewsPanels;

import javax.swing.*;
import java.awt.*;

public class StoreReviewsWritePanel extends JPanel {
    private JTextArea commentTextArea;
    private JButton postCommentButton;
    private JScrollPane textAreaScrollPane;
    public StoreReviewsWritePanel()
    {
        Load();
    }
    public void Load()
    {
        this.setPreferredSize(new Dimension(430,100));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        commentTextArea=new JTextArea();
        textAreaScrollPane=new JScrollPane();
        textAreaScrollPane.setPreferredSize(new Dimension(430,70));
        textAreaScrollPane.setMaximumSize(new Dimension(430,70));
        textAreaScrollPane.setViewportView(commentTextArea);

        postCommentButton=new JButton("Post!");
        postCommentButton.setPreferredSize(new Dimension(50, 30));
        postCommentButton.setMaximumSize(new Dimension(50, 30));

        this.add(textAreaScrollPane,BorderLayout.NORTH);
        this.add(postCommentButton,BorderLayout.SOUTH);
        this.setVisible(true);


    }

}
