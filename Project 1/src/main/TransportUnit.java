public abstract class TransportUnit implements CapacityChecker {

  // Attributes
  protected String id;
  protected int capacity;
  protected int currentPassengers;
  protected Passenger passengers[];

  // Constructor with parameters
  public TransportUnit(String id, int capacity) {
    this.id = id;
    this.capacity = capacity;
    currentPassengers = 0;
    passengers = new Passenger[capacity];
  }

  // Method to check if the vehicle is full
  public boolean isFull() {
    return currentPassengers >= capacity;
  }

  // Method to add a passenger to the vehicle
  public boolean addPassenger(Passenger p) {
    if (p != null && !isFull()) {
      passengers[currentPassengers++] = p;
      return true;
    }
    return false;
  }

  // Method to remove a passenger from the vehicle
  public boolean removePassenger(String passengerId) {
    for (int i = 0; i < currentPassengers; i++) {
      if (passengers[i] != null && passengers[i].getId().equalsIgnoreCase(passengerId)) {
        for (int j = i; j < currentPassengers - 1; j++) {
          passengers[j] = passengers[j + 1];
        }
        passengers[currentPassengers - 1] = null;
        currentPassengers--;
        return true;
      }
    }
    return false;
  }

  // Method to display passengers in the vehicle
  public void displayPassengers() {
    for (int i = 0; i < currentPassengers; i++) {
      System.out.printf("| %-83s |%n", passengers[i]);
    }

  }

  // Setters and getters methods
  public int getCurrentPassengers() {
    return currentPassengers;
  }

  public void setID(String Id) {
    id = Id;
  }

  public String getID() {
    return id;
  }

  public int getCapacity() {
    return capacity;
  }

  // toString method to display vehicle details
  public String toString() {
    return "Transport Unit ID: " + id + "\nCapacity: " + capacity;
  }
}