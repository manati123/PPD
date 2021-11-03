import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        VectorMaker v = new VectorMaker(100);
        View ui = new View(v.getVectors());
        ui.run();
    }

}
