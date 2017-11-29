package Wylaga.Util.Random;

import Wylaga.Util.Random.RandomNumberGenerators.NormalRNG;
import Wylaga.Util.Random.RandomNumberGenerators.PrecalcRNG;
import Wylaga.Util.Random.RandomNumberGenerators.RandomNumberGenerator;

public class Random extends java.util.Random {
    private static RandomNumberGenerator instance = new PrecalcRNG(10000);

    private Random() { super(); }

    public static int rollInt(int bound) { return instance.rollInt(bound); }

    public static double rollDouble(double bound) { return instance.rollDouble(bound); }
}
