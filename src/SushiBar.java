import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 2;
    private static int waitressCount = 3;
    private static int duration = 2;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2000; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");
        List<Thread> threadList = new ArrayList<Thread>();

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // TODO initialize the bar and start the different threads
        Clock clock = new Clock(duration);
        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
        for(int i=0; i < waitressCount; i++){
            Thread waitressThread = new Thread(new Waitress(waitingArea));
            waitressThread.start();
            threadList.add(waitressThread);
        }
        Thread doorThread = new Thread(new Door(waitingArea));
        doorThread.start();
        try {
            doorThread.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        write("***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        for(Thread thread: threadList){
            try {
                thread.join();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        write("Total numer of orders:\t\t\t\t\t\t\t" +
                totalOrders.get() +
                "\nTotal number of takeaway orders:\t\t\t\t\t\t\t\t" +
                takeawayOrders.get() +
                "\nTotal numer of orders that customers have eaten at the bar:\t\t" +
                servedOrders.get());

    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
