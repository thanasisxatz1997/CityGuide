package Repository;

public class ManipulationString {
    public static String ChangeSpacesToPlus(String startingStr)
    {
        System.out.println("starting str before array is: "+startingStr.toCharArray());

        char[] startingArr;
        startingArr= startingStr.toCharArray();
        System.out.println("The arr is: ");
        for (int i=0;i<startingArr.length;i++)
        {
            if(startingArr[i]==' ')
            {
                startingArr[i]='+';
            }
        }
        String finalStr="";
        for(int i=0;i<startingArr.length;i++)
        {
            finalStr=finalStr+startingArr[i];
        }
        System.out.println("the finalstr here is: "+finalStr);
        return finalStr;
    }
}
