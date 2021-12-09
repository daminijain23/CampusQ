package guide;
import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import com.twilio.Twilio;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;

public class CampusQServlet extends HttpServlet {
//    public String mymsg;
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

//    public void init() throws ServletException {
//        mymsg = "Welcome to RIT CampusQ";
//    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        out.println("<h1>" + mymsg + "</h1>");
//        out.println("<p>" + "Ahoy ahoy!" + "</p>");
        String message = "message";
        String body = request.getParameter("Body");
        if (body.toLowerCase().equals("hi") || body.toLowerCase().equals("help")) {
            StringBuilder messageBuilder;
            messageBuilder = new StringBuilder();
            messageBuilder.append("Welcome to RIT CampusQ").append("\n\n");
            messageBuilder.append("To check queue length for restaurants, send one of the following:").append("\n");
            messageBuilder.append("status all - Gets queue length for all restaurants").append("\n");
            messageBuilder.append("status <restaurant name> - Gets queue length for any restaurant").append("\n");
            messageBuilder.append("Example: status restaurantA").append("\n\n");
            messageBuilder.append("To provide input for queue length at a restaurant, please enter details as follows:").append("\n");
            messageBuilder.append("update <restaurant name> <queue length>").append("\n");
            messageBuilder.append("Example: update restaurantA 10");
            message = messageBuilder.toString();
        } else if (body.toLowerCase().startsWith("status")) {
            try{
                if(! CampusQ.readerUser.isAlive())
                    CampusQ.readerUser.start();
            }
            catch (Exception e){
                // Ignore
            }
            message = CampusQ.getQueueLength(body.split(" ")[1]);
        }
        else if (body.toLowerCase().startsWith("update")){
            String[] input = body.split(" ");
            String restaurantName = input[1];
            int queueLength = Integer.parseInt(input[2]);
            if(queueLength > 0){
                CampusQ.makeUser(restaurantName, queueLength);
                message = "Thank you for your contribution!";
            }
            else
                message = "Please try again with a valid queue length.";
        }

        else if (body.toLowerCase().startsWith("sum"))
        {
            String[] input = body.split(" ");
            message = CampusQ.getSum(input[1]);
        }
        else
        {
            message = "Invalid command! Please send \"help\" to get the commands to be entered.";
        }

        Body messageBody = new Body.Builder(message).build();
        Message whatsapp = new Message.Builder().body(messageBody).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(whatsapp).build();

        response.setContentType("application/xml");

        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//    }
}

