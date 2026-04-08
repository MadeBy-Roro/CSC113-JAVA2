public class Station implements CapacityChecker {

  // Attributes
  private String stationName;
  private String location;
  private TransportUnit[] transportUnits; // aggregation relationship with TransportUnit
  private int currentUnits;
  private Employee[] assignedEmployees;
  private int numOfEmployees;

  // Constructor with parameters
  public Station(String stationName, String location, int maxCapacity) {
    this.stationName = stationName;
    this.location = location;
    this.transportUnits = new TransportUnit[maxCapacity];
    this.currentUnits = 0;
    this.assignedEmployees = new Employee[50];
    this.numOfEmployees = 0;
  }

  // Setter and getter methods
  public void setStationName(String sN) {
    stationName = sN;
  }

  public void setLocation(String l) {
    location = l;
  }

  public String getStationName() {
    return stationName;
  }

  public String getLocation() {
    return location;
  }

  public int getCurrentUnits() {
    return currentUnits;
  }

  public int getNumOfEmployees() {
    return numOfEmployees;
  }

  public TransportUnit[] getTransportUnits() {
    return transportUnits;
  }

  public boolean isFull() {
    return currentUnits >= transportUnits.length;
  }

  // Method to add a vehicle using the counter
  public boolean addTransportUnit(TransportUnit unit) {
    // check for duplicate IDs
    for (int i = 0; i < currentUnits; i++) {
      if (transportUnits[i] != null && transportUnits[i].getID().equals(unit.getID())) {
        System.out.println("Error: A vehicle with this ID is already at the station.");
        return false;
      }
    }

    // Check if full using the interface method
    if (!isFull()) {
      transportUnits[currentUnits++] = unit;
      return true;
    }

    // if full
    System.out.println("Error: Station is full. Cannot add more vehicles.");
    return false;
  }

  // Method to remove a vehicle by shifting
  public boolean removeTransportUnit(String unitId) {
    for (int i = 0; i < currentUnits; i++) {
      if (transportUnits[i].getID().equals(unitId)) {
        for (int j = i; j < currentUnits - 1; j++) {
          transportUnits[j] = transportUnits[j + 1];
        }
        transportUnits[currentUnits - 1] = null;
        currentUnits--;
        System.out.println("The vehicle has been removed successfully.");
        return true;
      }
    }
    System.out.println("Vehicle not found at this station.");
    return false;
  }

  // Method to print the array elements
  public void printTransportUnits() {
    if (currentUnits == 0) {
      System.out.println("No vehicles currently at this station.");
    } else {
      printTransportUnitsRecursive(0);
    }
  }

  private void printTransportUnitsRecursive(int index) {
    if (index >= currentUnits) {
      return;
    }
    System.out.println(transportUnits[index].toString());
    System.out.println("-----------------");

    printTransportUnitsRecursive(index + 1);
  }

  // Method to check if a driver is already assigned to a bus
  public boolean getIsDriverBusy(String driverId) {
    for (int i = 0; i < this.getCurrentUnits(); i++) {
      TransportUnit unit = this.getTransportUnits()[i];

      if (unit instanceof Bus) {
        Bus existingBus = (Bus) unit;
        if (existingBus.getDriver() != null && existingBus.getDriver().getId().equals(driverId)) {
          return true;
        }
      }
    }
    return false;
  }

  // Method to assign an employee to the station
  public boolean assignEmployee(Employee e) {
    for (int i = 0; i < numOfEmployees; i++) {// checking for duplicate employees
      if (assignedEmployees[i] != null && assignedEmployees[i].getId().equalsIgnoreCase(e.getId())) {
        System.out.println("This employee is already assigned to the station.");
        return false;
      }
    }
    if (numOfEmployees < assignedEmployees.length) {
      assignedEmployees[numOfEmployees++] = e;
      return true;
    }
    System.out.println("Station employee capacity is full.");
    return false;
  }

  // Method to remove an employee from the station by shifting
  public boolean removeEmployee(String empId) {
    for (int i = 0; i < numOfEmployees; i++) {
      if (assignedEmployees[i] != null && assignedEmployees[i].getId().equals(empId)) {
        for (int j = i; j < numOfEmployees - 1; j++) {
          assignedEmployees[j] = assignedEmployees[j + 1];
        }
        assignedEmployees[numOfEmployees - 1] = null;
        numOfEmployees--;
        System.out.println("The employee has been removed from the station successfully.");
        return true;
      }
    }
    System.out.println("Employee not found at this station.");
    return false;
  }

  // Method to print the employees assigned to this station
  public void printEmplyees(){ 
    if (numOfEmployees == 0) {
      System.out.println("No employees currently assigned to this station.");
      return;
    }
    for (int i = 0; i < numOfEmployees; i++) {
      System.out.println(assignedEmployees[i].toString());
      System.out.println("-----------------");
    }
  }

  // Method to get a transport unit by ID
  public TransportUnit getTransportUnitById(String id) {
      for (int i = 0; i < currentUnits; i++) {
          if (transportUnits[i] != null && transportUnits[i].getID().equalsIgnoreCase(id)) {
              return transportUnits[i];
          }
      }
      return null;
  }

  // method to calculate the total number of passengers in the station
  public int calNumOfPassengers() {
    int totalPassengers = 0;
    for (int i = 0; i < currentUnits; i++) {
      totalPassengers += transportUnits[i].getCurrentPassengers();
    }
    return totalPassengers;
  }

  // Method to display station information
  public void displayInfo() {
    System.out.println(this + "\n Transport Units: ");
    printTransportUnits();

  }

  public String toString() {
    return ("Station Name: " + stationName + "\nLocation: " + location);
  }
}
