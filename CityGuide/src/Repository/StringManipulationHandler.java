package Repository;

public class StringManipulationHandler {
    public static void main(String[] args) {
        //Just for testing
        System.out.println("The returned changed string is: "+ChangeSpacesToPlus("Hello im an str!"));

    }
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

        public static int CountLines(String str)
        {
            int count=0;
            for(int i=0;i<str.length();i++)
            {
                if(str.charAt(i)=='\n')
                {
                    count++;
                }
            }
            return count;
        }
}


