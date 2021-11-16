import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Matrix firstMatrix;
    private static Matrix secondMatrix;

    public static Matrix productByTasks() throws InterruptedException {
        Matrix productMatrix = new Matrix(Utils.nrRowsFirstMatrix, Utils.nrColumnsSecondMatrix);
        List<Thread> threads = new ArrayList<>();

        // iterations - the number of operations/products each thread will compute for the resulting matrix
        int iterations = Utils.nrRowsFirstMatrix*Utils.nrColumnsSecondMatrix / Utils.taskNr;

        for(int i = 0; i < Utils.taskNr; ++i) {
            // left and right represent the indices of the cell in a matrix
            // a thread will compute the numbers in the resulting matrix from left to right
            int left = i * iterations;
            int right = Math.min((i+1) * iterations, Utils.nrRowsFirstMatrix*Utils.nrColumnsSecondMatrix);

            if(Utils.scanType.equals("row")) {
                threads.add(new RowThread(firstMatrix, secondMatrix, productMatrix, left, right, Utils.nrColumnsSecondMatrix));
            } else if(Utils.scanType.equals("col")) {
                threads.add(new ColumnThread(firstMatrix, secondMatrix, productMatrix, left, right, Utils.nrRowsFirstMatrix));
            } else if(Utils.scanType.equals("kth")) {
                threads.add(new ThreadK(firstMatrix, secondMatrix, productMatrix, i, Utils.taskNr, Utils.nrRowsFirstMatrix,
                        Utils.nrColumnsSecondMatrix));
            }
        }

        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        return productMatrix;
    }

    public static Matrix productByThreadPool() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Utils.taskNr);
        Matrix productMatrix = new Matrix(Utils.nrRowsFirstMatrix, Utils.nrColumnsSecondMatrix);
        List<Runnable> tasks = new ArrayList<>();
        int iterations = Utils.nrRowsFirstMatrix*Utils.nrColumnsSecondMatrix / Utils.taskNr;

        for(int i = 0; i < Utils.taskNr; ++i) {
            int left = i * iterations;
            int right = Math.min((i+1) * iterations, Utils.nrRowsFirstMatrix*Utils.nrColumnsSecondMatrix);

            if(Utils.scanType.equals("row")) {
                tasks.add(new RowThread(firstMatrix, secondMatrix, productMatrix, left, right, Utils.nrColumnsSecondMatrix));
            } else if(Utils.scanType.equals("col")) {
                tasks.add(new ColumnThread(firstMatrix, secondMatrix, productMatrix, left, right, Utils.nrRowsFirstMatrix));
            } else if(Utils.scanType.equals("kth")) {
                tasks.add(new ThreadK(firstMatrix, secondMatrix, productMatrix, i, Utils.taskNr, Utils.nrRowsFirstMatrix,
                        Utils.nrColumnsSecondMatrix));
            }
        }

        for (Runnable task : tasks) {
            executor.execute(task);
        }

        executor.shutdown();

        while (!executor.awaitTermination(1, TimeUnit.DAYS)){
            System.out.println("How on earth");
        }

        return productMatrix;
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            Utils.readParamsFromFile();

            firstMatrix = new Matrix(Utils.nrRowsFirstMatrix, Utils.nrColumnsFirstMatrix);
            secondMatrix = new Matrix(Utils.nrColumnsFirstMatrix, Utils.nrColumnsSecondMatrix);
            System.out.println("First matrix: \n" + firstMatrix.printMatrix());
            System.out.println("Second matrix: \n" + secondMatrix.printMatrix());

            Matrix sequentiallyProduct = Matrix.computeSequentiallyProduct(firstMatrix, secondMatrix);
            System.out.println("The product computed sequentially: \n" + sequentiallyProduct.printMatrix());

            Matrix computedProduct = null;
            if (Utils.fType.equals("task")) {
                computedProduct = productByTasks();
            } else if (Utils.fType.equals("pool")) {
                computedProduct = productByThreadPool();
            }

            if (computedProduct != null) {
                System.out.println("The product computed using threads: \n" + computedProduct.printMatrix());
            }

            long end = System.currentTimeMillis();
            System.out.println("The program run in " + (end - start) + "ms\n");
        } catch (RuntimeException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}