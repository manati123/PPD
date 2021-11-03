
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread{

    private Lock _mutex;
    private Condition sumDone;
    private ArrayList<Vector> vectors;
    private int count;

    public Consumer(ArrayList<Vector> vectors, Lock mutex, Condition condition) {
        this.vectors = vectors;
        this._mutex = mutex;
        this.sumDone = condition;
        this.count = 1;
    }

    @Override
    public void run()
    {
        try{
            while(true)
            {
                consume();
            }
        }catch (Exception exp){}
    }

    public synchronized void consume()
    {
        this.lock();
        try{
            while(this.vectors.size() % 2 != 1){this.sumDone.await();}
            double result = this.vectors.get(this.vectors.size()-1).getCoefficientI()+this.vectors.get(this.vectors.size()-1).getCoefficientJ()+this.vectors.get(this.vectors.size()-1).getCoefficientK();
            this.vectors.remove(this.vectors.size()-1);
            System.out.println("Consumer" + this.count + " : " + this.doubleBeautifier(result) );
            System.out.println("----------------------------------------------------------------------------------------------------");
            this.count++;
            this.sumDone.signal();
        }catch (Exception e){}
        this.unlock();
    }

    private void lock(){this._mutex.lock();}
    private void unlock(){this._mutex.unlock();}

    private String doubleBeautifier(double number)
    {
        DecimalFormat doubleFormat = new DecimalFormat("#.##");
        return doubleFormat.format(number);
    }



}