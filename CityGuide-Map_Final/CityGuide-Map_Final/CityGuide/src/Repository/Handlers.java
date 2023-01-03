package Repository;

public class Handlers {
    public static long startTime;
    public static long ShowCurrentRunTime()
    {
        return (System.nanoTime()-startTime)/1000000000;
    }
}
