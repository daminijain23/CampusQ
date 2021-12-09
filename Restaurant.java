package guide;

import java.util.LinkedList;
import java.util.Queue;

public class Restaurant {

    public Queue<Integer> queueSizes;
    public int outputQueueSize;
    public int sumOfCurrentBlock;

    public Restaurant()
    {
        outputQueueSize = 0;
        sumOfCurrentBlock = 0;
        queueSizes = new LinkedList<>();
    }
}