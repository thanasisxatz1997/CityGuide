package testing;

public class Car extends Vehicle{
    public Car()
    {
        name="car";
        wheels="4";
    }
    public void MakeSound()
    {
        super.MakeSound();
        System.out.println("I Go BRRRRRRRRR!");
    }
}
