import java.util.ArrayList;
import java.util.Random;

public class VectorMaker {
    private ArrayList<Vector> vectors;
    private int numberOfVectors;
    public VectorMaker(int nov)
    {
        this.vectors = new ArrayList<>();
        this.numberOfVectors = nov;

        this.populate();
    }


    private void populate()
    {
        //int numberOfVectors = this.getRandomInteger();
        //int numberOfVectors = 10;
        for(int index=0; index<this.numberOfVectors; index++)
        {
            Vector vector = new Vector(Math.round(this.getRandomDouble() * 1000.0)/100.0,Math.round(this.getRandomDouble() * 1000.0)/100.0,Math.round(this.getRandomDouble() * 1000.0)/100.0);
            this.vectors.add(vector);
        }
    }

    private double getRandomDouble()
    {
        Random random = new Random();
        double randomNumber = random.nextDouble();
        return randomNumber;
    }

    private int getRandomInteger()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (randomNumber <= 0) {return this.getRandomInteger();}
        else if (randomNumber % 2 != 0) {return randomNumber-1;}
        return randomNumber;
    }

    public ArrayList<Vector> getVectors() {
        return vectors;
    }
}
