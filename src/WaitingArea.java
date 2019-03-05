import java.util.List;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {
    private List<Customer> customers;
    private int capacity;
    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.capacity = size;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        // TODO Implement required functionality
        while(this.capacity <= customers.size()){
            try {
                this.wait();
            } catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        customers.add(customer);
        this.notify();
    }


    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        // TODO Implement required functionality
        while(customers.size() == 0){
            try{
                this.wait();
            } catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        this.notify();
        return customers.get(0);
    }

    // Add more methods as you see fit
}
