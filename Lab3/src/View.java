import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class View {

    private ArrayList<Vector> vectors;
    private Lock _mutex = new ReentrantLock();
    private Condition condition = _mutex.newCondition();


    public View(ArrayList<Vector> vectors) {
        this.vectors = vectors;
    }

    public void run() {
        System.out.println("Number of vectors : " + this.vectors.size());
        Producer producer = new Producer(this.vectors, this._mutex, this.condition);
        Consumer consumer = new Consumer(this.vectors, this._mutex, this.condition);
        producer.start();
        consumer.start();
    }
}