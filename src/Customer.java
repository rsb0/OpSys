import java.util.Random;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {
    private int customerID;
    Random rand;


    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        // TODO Implement required functionality
        this.customerID = SushiBar.customerCounter.get();
        SushiBar.customerCounter.increment();
        this.rand = new Random();
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // TODO Implement required functionality
        long time = (long)rand.nextInt(SushiBar.customerWait);
        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        return this.customerID;
        // TODO Implement required functionality
    }

    // Add more methods as you see fit
}
