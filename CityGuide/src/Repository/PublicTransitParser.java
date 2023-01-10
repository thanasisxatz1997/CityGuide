package Repository;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class PublicTransitParser {

    public static String GetInstructions(Document doc)
    {
        ArrayList<Document> routesDocList= (ArrayList<Document>) doc.get("routes");
        ArrayList<Document> legsDocList= (ArrayList<Document>) routesDocList.get(0).get("legs");
        ArrayList<Document> stepsDocList= (ArrayList<Document>) legsDocList.get(0).get("steps");
        String finalInstr="";
        for(int i=0;i<stepsDocList.size();i++)
        {
            String startingInstr=stepsDocList.get(i).get("html_instructions").toString();
            String singleInstStr=PublicTransitParser.ChangeInstructionStr(startingInstr);
            //System.out.println(singleInstStr);
            finalInstr=finalInstr+singleInstStr+'\n';
        }
        return finalInstr;
    }

    public static String ChangeInstructionStr(String startingStr)
    {
        String finalStr="";


        while (startingStr.contains("<"))
        {
            int firstCut=startingStr.indexOf('<');
            finalStr=finalStr+startingStr.substring(0,firstCut);
            int lastCut=startingStr.indexOf('>');
            startingStr=startingStr.substring(lastCut+1,startingStr.length());
        }
        return finalStr;
    }

}
