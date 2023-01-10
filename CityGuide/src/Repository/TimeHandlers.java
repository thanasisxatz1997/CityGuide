package Repository;

public class TimeHandlers {
    public static long startTime;
    public static long ShowCurrentRunTime()
    {
        return (System.nanoTime()-startTime)/1000000000;
    }
}
