package Wylaga.Util.Random.RandomNumberGenerators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrecalcRNG extends java.util.Random implements RandomNumberGenerator
{
    private List<Integer> integers;
    private List<Double> doubles;

    private Iterator<Integer> intIterator;
    private Iterator<Double> doubleIterator;

    public PrecalcRNG(int size)
    {
        super();
        integers = new ArrayList<>();
        doubles = new ArrayList<>();

        for(int i = 0; i < size; i++)
        {
            int x = nextInt();
            if(x < 0) x = -x;
            System.out.println(x);
            integers.add(x);
        }

        for(int i = 0; i < size; i++)
        {
            double x = nextInt();
            if(x < 0) x = -x;
            System.out.println(x);
            doubles.add(x);
        }

        intIterator = integers.iterator();
        doubleIterator = doubles.iterator();
    }

    public int rollInt(int bound)
    {
        if(!intIterator.hasNext())
        {
            intIterator = integers.iterator();
        }
        return (intIterator.next() % bound);
    }

    public double rollDouble(double bound)
    {
        if(!doubleIterator.hasNext())
        {
            doubleIterator = doubles.iterator();
        }
        return (doubleIterator.next() % bound);
    }
}
