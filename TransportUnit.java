import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class Ticket implements Serializable{

    // Attributes
    private int price;
    private boolean isTicketActive;


    // Constructor
    public Ticket(int price) {
        this.price = price;
        this.isTicketActive = false;
    }

    // Getter
    public int getPrice() {
        return price;
    }

    // Determine ticket type by price
    public String getType() {

        switch (price) {

            case 4:
                return "2 hours ticket";

            case 40:
                return "7 days ticket";

            case 140:
                return "30 days ticket";

            case 10:
                return "2 hours first class ticket";

            case 100:
                return "7 days first class ticket";

            case 350:
                return "30 days first class ticket";

            default:
                return "Unknown ticket type";
        }
    }


     // method to calculate expiry date
    public String calculateExpiryDate(String ticketType) {

        // Set the time zone to Riyadh
        ZoneId riyadhZone = ZoneId.of("Asia/Riyadh");

        // Get the current time in Riyadh
        LocalDateTime activationTime = LocalDateTime.now(riyadhZone);
        LocalDateTime expiryTime = activationTime; 

        // Determine the expiry time based on the ticket type
        String type = ticketType.toLowerCase();

        if (type.contains("2") && type.contains("hour")) {
            expiryTime = activationTime.plusHours(2);  
        } else if (type.contains("7") && type.contains("day")) {
            expiryTime = activationTime.plusDays(7); 
        } else if (type.contains("30") && type.contains("day")) {
            expiryTime = activationTime.plusDays(30); 
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return expiryTime.format(formatter);
    }
    public String getcalculateExpiryDate(){
        return calculateExpiryDate(getType());
    }

    public boolean isTicketActive(){
        return isTicketActive;
    }
    public void setTicketActive(boolean isTicketActive){
        this.isTicketActive = isTicketActive;
    }
    
    // toString to show ticket details
    public String toString() {
        return getType() + " | Price: " + price;
    }

}