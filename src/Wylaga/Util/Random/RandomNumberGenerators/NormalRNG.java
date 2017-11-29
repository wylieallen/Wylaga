package Wylaga.Util.Random.RandomNumberGenerators;

public class NormalRNG extends java.util.Random implements RandomNumberGenerator
{
    public NormalRNG() { super(); }

    public int rollInt(int bound) { return nextInt(bound); }

    public double rollDouble(double bound) { return (nextDouble() * bound); }
}
