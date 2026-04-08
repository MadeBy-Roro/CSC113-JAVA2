public class Ticket {

    // Attributes
    private int price;

    // Constructor
    public Ticket(int price) {
        this.price = price;
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

    // toString to show ticket details
    public String toString() {
        return getType() + " | Price: " + price;
    }

}