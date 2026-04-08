
public class Passenger extends Person {

    // Attributes
    private double balance;
    private Ticket ticket; // passenger currently owns one ticket

    // Constructor
    public Passenger(String name, String id, double balance) {
        super(name, id);
        this.balance = balance;
        this.ticket = null; // starts with no ticket
    }


    // Method to buy a ticket
    public void buyTicket(int price) {
        if (ticket == null) {
            if (balance >= price) {
                balance -= price;
                ticket = new Ticket(price); 
                System.out.println("Ticket purchased successfully!");
            } else {
                System.out.println("Not enough balance to buy the ticket.");
            }
        } else {
            System.out.println("You already have a ticket.");
        }
    }


// Method to refund a ticket
    public void refundTicket() {
        if (ticket != null) {
            balance += ticket.getPrice();
            ticket = null;
            System.out.println("Ticket refunded successfully!");
        } else{
            System.out.println("No ticket to refund.");
        }
    }


    public void addBalance(double amount) {
        if (amount >= 0) {
            balance += amount;
            System.out.println("Balance updated. New balance: " + balance);
        } else{
            System.out.println("Invalid amount. Please enter a positive number.");
        }

    }

    public String toString() {
        String ticketStatus = (ticket != null) ? ticket.getType() : "No ticket purchased";
        return super.toString() + " | Balance: " + balance + " | Ticket: " + ticketStatus;
    }

    
    // setters and getters
    public void setBalance(double b) {
        balance = b;
    }

    public double getBalance() {
        return balance;
    }


    public Ticket getTicket() {
        return ticket;
    }


}