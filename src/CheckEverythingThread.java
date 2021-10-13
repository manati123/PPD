public record CheckEverythingThread(Supermarket supermarket, int threadNumber) implements Runnable {
    @Override
    public void run(){
        System.out.println("Thread " + thredNumber + " is checking the supermarket");
        if(supermarket.checkEverything())
            System.out.println("Booooon");
        else
            System.out.println("Nononono domnu' sunteti pe minus");
    }
}