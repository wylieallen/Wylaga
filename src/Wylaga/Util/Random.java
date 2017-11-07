package Wylaga.Util;

public class Random extends java.util.Random
{
    private static Random instance = new Random();

    private Random() { super(); }

    public static int rollInt(int bound) { return instance.nextInt(bound); }
}
