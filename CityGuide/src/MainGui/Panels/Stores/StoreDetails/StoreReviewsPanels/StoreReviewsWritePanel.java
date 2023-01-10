package MainGui.Panels.Stores.StoreDetails.StoreReviewsPanels;

import Repository.ConnectToDatabase;
import Repository.CurrentUser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreReviewsWritePanel extends JPanel {
    private JTextArea commentTextArea;
    private Document storeDoc;
    private JButton postCommentButton;
    private JScrollPane textAreaScrollPane;
    public StoreReviewsWritePanel(Document doc)
    {
        storeDoc=doc;
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
        postCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentUser.IsLoggedIn())
                {
                    PostComment(commentTextArea.getText());
                }
                else
                {System.out.println("Not Logged in!");}
            }
        });

        this.add(textAreaScrollPane,BorderLayout.NORTH);
        this.add(postCommentButton,BorderLayout.SOUTH);
        this.setVisible(true);


    }
    private void PostComment(String commentStr)
    {
        storeDoc=AddCommentToStoreDoc(storeDoc,commentStr);
        MongoDatabase db= ConnectToDatabase.mainDatabase;
        MongoIterable<String> list=db.listCollectionNames();
        for (String name: list) {
            if(StoreExistsInColl(storeDoc,db.getCollection(name)))
            {
                UpdateStoreComments(storeDoc,db.getCollection(name));
            }
        }
    }

    private Document AddCommentToStoreDoc(Document storeDoc,String commentStr)
    {
        ArrayList<Document> reviewsDocList= (ArrayList<Document>) storeDoc.getList("reviews", Document.class);
        Document newComment=new Document();
        newComment.append("author_name",CurrentUser.userName);
        newComment.append("rating",5);
        newComment.append("text",commentStr);
        reviewsDocList.add(newComment);
        return storeDoc;
    }

    public boolean StoreExistsInColl(Document storeDoc,MongoCollection coll)
    {
        String storeName= storeDoc.get("name").toString();
        if(coll.find(new Document("name",storeName)).first()==null)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public void UpdateStoreComments(Document storeDoc,MongoCollection coll)
    {
        coll.findOneAndDelete(new Document("place_id",storeDoc.get("place_id")));
        coll.insertOne(storeDoc);
        System.out.println("Updated comment section of store with name: "+storeDoc.get("name").toString());
    }

}
