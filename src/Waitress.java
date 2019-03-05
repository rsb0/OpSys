import java.util.Random;

/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {
    WaitingArea waitingArea;
    Random rand;
    Customer customer;
    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        // TODO Implement required functionality
        this.waitingArea = waitingArea;
        this.rand = new Random();
        this.customer = null;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        while(SushiBar.isOpen) {
            long time = (long) rand.nextInt(SushiBar.waitressWait);
            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            customer = waitingArea.next();
            System.out.println("Customer " + customer.getCustomerID() + " is now waiting");
        }
    }


}

