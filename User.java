package guide;

import java.util.HashMap;

/**
 * A class that represents a thread for any update initiated by user
 * @author Ashesh Sheth
 * @author Damini Jain
 * @author Divya Muralidharan
 * @params RA instance of Restaurant
 * @params RB instance of Restaurant
 * @params RC instance of Restaurant
 * @params RD instance of Restaurant
*/
public class User extends Thread{

    public static Restaurant RA = new Restaurant();
    public static Restaurant RB = new Restaurant();
    public static Restaurant RC = new Restaurant();
    public static Restaurant RD = new Restaurant();
    public boolean stop = false;
    public int queueLength;
    public String restaurantName;
    public HashMap<String, Integer> readerLengths;

    /**
     * constructor
     * @params restaurantName Name of the restaurant for which a thread is initialised
     * @params queueLength Input value from the user
    */
    public User(String restaurantName, int queueLength){
        this.restaurantName = restaurantName;
        this.queueLength = queueLength;
    }

    /**
     * constructor
     * @params threadName to calulate and store average queue length
    */
    public User(String threadName){
        super(threadName);
        readerLengths = new HashMap<>();
    }

    //Thread handling - if update needs to made in queue or average queue length 
    public void run(){
        if(this.getName().equals("LengthReader")){
            outputReader();
        }
        else if(this.restaurantName.toLowerCase().equals("restauranta")){
            restaurantA();
        }
        else if(this.restaurantName.toLowerCase().equals("restaurantb")){
            restaurantB();
        }
        else if(this.restaurantName.toLowerCase().equals("restaurantc")){
            restaurantC();
        }
        else if(this.restaurantName.toLowerCase().equals("restaurantd")){
            restaurantD();
        }

    }

    /**
     * Method defines logic for the thread that calculates average
    */
    public void outputReader(){
        readerLengths.put("restaurantA", 0);
        readerLengths.put("restaurantB", 0);
        readerLengths.put("restaurantC", 0);
        readerLengths.put("restaurantD", 0);
        long start = System.currentTimeMillis();
        long current;

        while(! this.stop){
            current = System.currentTimeMillis();
            if(current - start > 5000){
                this.readerLengths.put("restaurantA", RA.outputQueueSize);
                this.readerLengths.put("restaurantB", RB.outputQueueSize);
                this.readerLengths.put("restaurantC", RC.outputQueueSize);
                this.readerLengths.put("restaurantD", RD.outputQueueSize);
                start = System.currentTimeMillis();
            }
        }
    }

    /**
     * Methods that updates queue with input value
    */
    public void restaurantA(){
        int oldLength = 0;
        synchronized (User.class){
            if(RA.queueSizes.size() == 5){
                oldLength = RA.queueSizes.poll();
            }
            RA.queueSizes.add(this.queueLength);
            RA.sumOfCurrentBlock += this.queueLength - oldLength;

            RA.outputQueueSize = RA.sumOfCurrentBlock / RA.queueSizes.size();
        }
    }

    public void restaurantB(){
        int oldLength = 0;
        synchronized (User.class){
            if(RB.queueSizes.size() == 5){
                oldLength = RB.queueSizes.poll();
            }
            RB.queueSizes.add(this.queueLength);
            RB.sumOfCurrentBlock += this.queueLength - oldLength;

            RB.outputQueueSize = RB.sumOfCurrentBlock / RB.queueSizes.size();
        }
    }

    public void restaurantC(){
        int oldLength = 0;
        synchronized (User.class){
            if(RC.queueSizes.size() == 5){
                oldLength = RC.queueSizes.poll();
            }
            RC.queueSizes.add(this.queueLength);
            RC.sumOfCurrentBlock += this.queueLength - oldLength;

            RC.outputQueueSize = RC.sumOfCurrentBlock / RC.queueSizes.size();
        }
    }

    public void restaurantD(){
        int oldLength = 0;
        synchronized (User.class){
            if(RD.queueSizes.size() == 5){
                oldLength = RD.queueSizes.poll();
            }
            RD.queueSizes.add(this.queueLength);
            RD.sumOfCurrentBlock += this.queueLength - oldLength;

            RD.outputQueueSize = RD.sumOfCurrentBlock / RD.queueSizes.size();
        }
    }

}
