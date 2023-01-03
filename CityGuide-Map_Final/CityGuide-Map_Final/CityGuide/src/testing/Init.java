package testing;

public class Init {
    public static void main(String[] args) {
        int[] arr={6,3,1,4,11,4};

        int maxe=0;
        for(int i=0;i<arr.length;i++)
        {
            int e=arr[i];
            int min=arr[i];
            for(int j=i-1;j>=0;j--)
            {
                if(arr[j]<min)
                {
                    min=arr[j];
                }
                if(e<min*(i-j+1))
                {
                    e=min*(i-j+1);
                }
            }
            if(e>maxe)
            {
                maxe=e;
            }
        }
        System.out.println(maxe);
    }
}
