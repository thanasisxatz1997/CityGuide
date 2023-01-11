package LogInManager.Managers;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class DataManager {
    public static MongoCollection DbCollection;
    public static boolean CheckIfDataExists(String name,String password,String email) //this function....
    {
        if(DbCollection.find(new Document("name",name).append("password",password).append("email",email)).first()==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static boolean PushData(String name,String password,String email)
    {
        String encKey=GenerateEncryptionKey();
        String encPass=Encrypt(password,encKey);
        if(!CheckIfDataExists(name,encPass,email))
        {
            DbCollection.insertOne(new Document("name",name).append("password",encPass).append("email",email).append("encKey",encKey));
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean CheckLogin(String name,String password)
    {
        Document doc;
        doc=(Document) DataManager.DbCollection.find(eq("name",name)).first();
        if(doc==null)
        {
            System.out.println("Log in data does not exists");
            return false;
        }
        else
        {
            System.out.println("Log in data exists");
            if(GetEncPass(name).equals(Encrypt(password, GetEncKey(name))))
            {
                System.out.println("GOT PASS");
                return true;
            }
            else
            {
                System.out.println("NOPE");
            }
            return false;
        }

        //doc = (Document) DataManager.DbCollection.find(eq("name",name)).first();
       //String email=doc.get("email");
        //String encPass=Encrypt(password,key);
    }
    public static String GetEmail(String name)
    {
        Document result= (Document) DbCollection.find(new Document("name",name)).first();
        return result.get("email").toString();
    }
    public static String GetEncPass(String name)
    {
        Document result= (Document) DbCollection.find(new Document("name",name)).first();
        System.out.println("The password i got is: "+result.get("password").toString());
        return result.get("password").toString();
    }
    public static String GetEncKey(String name)
    {
        Document result= (Document) DbCollection.find(new Document("name",name)).first();

        return result.get("encKey").toString();
    }
    public static String GenerateEncryptionKey()
    {
        int leftLimit=97;
        int rightLimit=122;
        int targetLength=3;
        Random rand=new Random();
        String generatedString = rand.ints(leftLimit,rightLimit + 1).limit(targetLength).collect(StringBuilder::new ,StringBuilder::appendCodePoint,StringBuilder::append).toString();
        System.out.println("Encryption key is: "+generatedString);
        return generatedString;
    }
    public static String Encrypt(String password,String encKey)
    {
        System.out.println("PASS IS: "+password);
        int sum=0;
        for(int i=0;i<3;i++)
        {
            sum=sum+encKey.charAt(i);
        }
        System.out.println(sum);
        while(sum/10>0)
        {
            sum=sum/10;
        }
        sum++;
        System.out.println("sum= "+sum);
        String encryptedPass="";
        for(int i =0;i<password.length();i++)
        {
            encryptedPass=encryptedPass+(char)(password.charAt(i)+sum);
        }
        System.out.println("Encrypted pass is: "+encryptedPass);
        return encryptedPass;
    }
    public static String Decrypt(String encryptedPassword,String encryptionKey)
    {
        int sum=0;
        for(int i=0;i<3;i++)
        {
            sum=sum+encryptionKey.charAt(i);
        }
        while(sum/10>0)
        {
            sum=sum/10;
        }
        sum++;
        String decryptedPass="";
        for(int i =0;i<encryptedPassword.length();i++)
        {
            decryptedPass=decryptedPass+(char)(encryptedPassword.charAt(i)-sum);
        }
        System.out.println("Decrypted pass is: "+decryptedPass);
        return decryptedPass;
    }
}