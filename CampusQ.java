package guide;

public class CampusQ {

    public static User readerUser = new User("LengthReader");

    public static void makeUser(String restaurantName, int queueLength){
        new User(restaurantName, queueLength).start();
    }

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

//    public static void main(String[] args) {
//        User readerUser = new User("LengthReader");
//        User user1 = new User(10, "restaurantA");
//        User user2 = new User(8, "restaurantA");
//        User user3 = new User(12, "restaurantB");
//
//        readerUser.start();
//        user1.start();
//        user2.start();
//        user3.start();
//        while(true){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for(String restaurant : readerUser.readerLengths.keySet()) {
//                System.out.println(restaurant + ": " + readerUser.readerLengths.get(restaurant));
//            }
//        }
//
//    }
}