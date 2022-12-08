package Panels.Stores.StoreDetails.StoreDetailsMapPanels;

import org.bson.Document;

import javax.swing.*;

public class DetailsMapPanel extends JPanel {
    private Document storeDoc;
    public DetailsMapPanel(Document doc)
    {
        storeDoc=doc;
        //Document geometryDoc=(Document) storeDoc.get("geometry");
        //Document locationDoc=(Document) geometryDoc.get("location");
        Document locationDoc=(Document) ((Document) storeDoc.get("geometry")).get("location");
    }
}
