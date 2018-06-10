package Wylaga.Util.Random;

import Wylaga.Util.Random.RandomNumberGenerators.NormalRNG;
import Wylaga.Util.Random.RandomNumberGenerators.PrecalcRNG;
import Wylaga.Util.Random.RandomNumberGenerators.RandomNumberGenerator;

public class Random
{
    private static RandomNumberGenerator rng = new PrecalcRNG(10000);

    private Random() { super(); }

    public static int rollInt(int bound) { return rng.rollInt(bound); }

    public static double rollDouble(double bound) { return rng.rollDouble(bound); }
}
