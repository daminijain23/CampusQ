package guide;

import java.util.LinkedList;
import java.util.Queue;


/** 
 * Represents a Restaurant
 * @author Ashesh Sheth, Damini Jain, Divya Muralidharan
*/
public class Restaurant {

    public Queue<Integer> queueSizes;
    public int outputQueueSize;
    public int sumOfCurrentBlock;

    /** 
     * @params queueSizes A queue where a user's input get populated
     * @params sumOfCurrentBlock Sum of current five values in the queue
     * @params outputQueueSize Average of current five values in the queue. Current queue length of a restuarnt
    */
    public Restaurant()
    {
        outputQueueSize = 0;
        sumOfCurrentBlock = 0;
        queueSizes = new LinkedList<>();
    }
}
