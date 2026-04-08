public class BusOnDemand extends Bus {

  // Attribute
  private String neighborhood;

  // Constructor with parameters
  public BusOnDemand(String id, int capacity, String routeNumber, String neighborhood) {
    super(id, capacity, routeNumber); // inherits from Bus
    this.neighborhood = neighborhood;
  }

  // getter method
  public String getNeighborhood() {
    return neighborhood;
  }

  // toString method to display bus on demand details
  public String toString() {
    return super.toString() + "\nNeighborhood: " + neighborhood;
  }

}
