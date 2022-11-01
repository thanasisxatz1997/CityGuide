package Repository;

import java.lang.*;

public class StringManipulation {
    public String EditString(boolean checkIfFirstCharU,boolean checkIfEndsS) {
        // test string name
        String name = "to kapHleIo TOY KwsTH";

        // create two substrings from name
        // first substring contains first letter of name
        // second substring contains remaining letters
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1, name.length());
        // create boolean primitives
        boolean b1,b2;

        // change the first letter to uppercase
        // Convert String to char
        char ch1=firstLetter.charAt(0);
        b1 = Character.isUpperCase(ch1);
        if (!b1){
            firstLetter = firstLetter.toUpperCase();
        }

        // change the remaining letters to lowercase
        String remainingLettersFinal="";
        for (int i=0; i<remainingLetters.length(); i++){
            char ch2 = remainingLetters.charAt(i);
            b2 = Character.isUpperCase(i);
            if(!b2){
                char ch3 = Character.toLowerCase(ch2);
                remainingLettersFinal =  remainingLettersFinal + ch3;
            }
        }

        // remove excessive last characters
        /*
        //Include Guava to the project
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>31.1-jre</version>
        </dependency>
        */
        //String newStr = CharMatcher.is('s').trimTrailingFrom(s);



        // join the two substrings
        name = firstLetter + remainingLettersFinal;

        // remove all white spaces
        name = name.replaceAll("\\s", "");

        //System.out.println("Name: " + name);
    }

}