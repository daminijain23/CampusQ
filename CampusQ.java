package guide;

/**
 * Main handler class of the CampusQ app
 * @author Ashesh Sheth, Damini Jain, Divya Muralidharan
*/
public class CampusQ {
    
    // Thread that calculates the average of current (five) values in the queue for each of the restaurants.
    public static User readerUser = new User("LengthReader");

    /**
     * @params restuarantName Name of the restuarnt for which a thread is started and value needs to be updated
     * @params queueLength The value provided by the user
    */
    public static void makeUser(String restaurantName, int queueLength){
        new User(restaurantName, queueLength).start();
    }

    /**
     * A method that fetches the output queue length for a restaurnt.
     * @params restaurantName Name of the restaurant for which current queue length needs to fetched
    */
    public static String getQueueLength(String restaurantName){
        if(restaurantName.toLowerCase().equals("all")){
            StringBuilder allRestaurants;
            allRestaurants = new StringBuilder();
            for(String restaurant : readerUser.readerLengths.keySet()) {
                allRestaurants.append(restaurant).append(": ").append(readerUser.readerLengths.get(restaurant));
                allRestaurants.append("\n");
            }
            return allRestaurants.toString();
        }
        else
        {
            return restaurantName + ": " + readerUser.readerLengths.get(restaurantName);
        }
    }

    /**
     * A method that gets the current sum for a restaurant.
     * @params restuarntName Name of a restuarnt for which sum of values in queue needs to be fetched.
    */
    public static String getSum(String restaurantName)
    {
        if(restaurantName.toLowerCase().equals("restauranta"))
            return restaurantName + ": " + readerUser.RA.sumOfCurrentBlock;
        else if(restaurantName.toLowerCase().equals("restaurantb"))
            return restaurantName + ": " + readerUser.RB.sumOfCurrentBlock;
        else if(restaurantName.toLowerCase().equals("restaurantc"))
            return restaurantName + ": " + readerUser.RC.sumOfCurrentBlock;
        else
            return restaurantName + ": " + readerUser.RD.sumOfCurrentBlock;
    }

}
