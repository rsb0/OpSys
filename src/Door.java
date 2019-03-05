import java.util.Random;

/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */

public class Door implements Runnable {
    private WaitingArea waitingArea;
    private Clock clock;
    Random rand;
    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        // TODO Implement required functionality
        this.waitingArea = waitingArea;
        this.rand = new Random();
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the waiting area
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        while (SushiBar.isOpen) {
            long time = (rand.nextInt(SushiBar.doorWait));
            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            waitingArea.enter(new Customer());
        }
    }

    // Add more methods as you see fit
}
