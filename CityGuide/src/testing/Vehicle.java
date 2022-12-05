package testing;

public class Vehicle {
    String pron="I am a: ";
    String wheels;
    String name;

    public Vehicle()
    {
        name="Vehicle";
        wheels="unknown";
    }
    public void Pronounce()
    {
        System.out.println(pron+name);
        System.out.println("I have a number of "+wheels+" wheels");
    }
}
