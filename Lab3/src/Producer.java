import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread{

    private ArrayList<Vector> vectors;
    private Lock _mutex;
    private Condition enough;
    private int currentIndex;
    private int count;

    public Producer(ArrayList<Vector> vectors, Lock _mutex, Condition condition)
    {
        this.vectors = vectors;
        this._mutex = _mutex;
        this.enough = condition;
        this.currentIndex = 0;
        this.count = 1;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                this.produce();
            }
        }
        catch (Exception exp) {System.out.println(exp);}
    }

    public synchronized void produce()
    {
        this.lock();
        try
        {
            while(this.vectors.size() % 2 != 0) {this.enough.await();}
            double i = this.vectors.get(this.currentIndex).getCoefficientI()*this.vectors.get(currentIndex+1).getCoefficientI();
            double j = this.vectors.get(this.currentIndex).getCoefficientJ()*this.vectors.get(currentIndex+1).getCoefficientJ();
            double k = this.vectors.get(this.currentIndex).getCoefficientK()*this.vectors.get(currentIndex+1).getCoefficientK();
            Vector resultVector = new Vector(i,j,k);
            this.vectors.add(resultVector);
            System.out.println("V" + (this.currentIndex+1) + " = " + this.vectors.get(currentIndex).toString());
            System.out.println("V" + (this.currentIndex+2) + " = " + this.vectors.get(currentIndex+1).toString());
            this.currentIndex += 2;
            System.out.println("Producer" + this.count + " : " + this.doubleBeautifier(i) + ", " + this.doubleBeautifier(j) + ", " + this.doubleBeautifier(k));
            this.count++;
            this.enough.signal();
        } catch (Exception exp) {}
        this.unlock();
    }

    private void lock() {this._mutex.lock();}
    private void unlock(){this._mutex.unlock();}

    private String doubleBeautifier(double number)
    {
        DecimalFormat doubleFormat = new DecimalFormat("#.##");
        return doubleFormat.format(number);
    }


}