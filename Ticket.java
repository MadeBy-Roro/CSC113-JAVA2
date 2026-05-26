import java.io.*;
public class Station implements CapacityChecker, Serializable {

  // Attributes
  private String stationName;
  private String location;
  private List<TransportUnit> transportUnits; // aggregation relationship with TransportUnit
  private List<Employee> assignedEmployees;
  private int maxCapacity;

  // Constructor with parameters
    public Station(String stationName, String location, int maxCapacity) {
      this.stationName = stationName;
      this.location = location;
      this.maxCapacity = maxCapacity;
      this.transportUnits = new List<TransportUnit>("Transport Units");
      this.assignedEmployees = new List<Employee>("Assigned Employees");
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
    return transportUnits.size();
  }

  public int getNumOfEmployees() {
  return assignedEmployees.size();  }

  public List<TransportUnit> getTransportUnits() {
    return transportUnits;
  }

  public boolean isFull() {
    return transportUnits.size() >= maxCapacity;
  }

  // Method to add a vehicle using the counter
  public boolean addTransportUnit(TransportUnit unit) {
    // check for duplicate IDs
    for (int i = 0; i < transportUnits.size(); i++) {
      if (transportUnits.get(i).getID().equals(unit.getID())) {
        System.out.println("Error: A vehicle with this ID is already at the station.");//check
        return false;
      }
    }
    // Check if full using the interface method
    if (!isFull()) {
      transportUnits.insertAtBack(unit); // Using List method
      return true;
    }

    // if full
    System.out.println("Error: Station is full. Cannot add more vehicles.");
    return false;
  }

  // Method to remove a vehicle
    public boolean removeTransportUnit(String unitId) {
      TransportUnit tu = getTransportUnitById(unitId);

      if (tu != null) {
          transportUnits.remove(tu);
          System.out.println("The vehicle has been removed successfully.");
          return true;
      }

      System.out.println("Vehicle not found at this station.");
      return false;
    }

  // Method to print the list elements, updated to ls
  public String printTransportUnits() {
   if(transportUnits.isEmpty())
     return "No vehicles at this station.\n";
    else 
     return printTransportUnitsRecursive(0);
  }


  private String printTransportUnitsRecursive(int index) {//updated to ls
    if (index >= transportUnits.size()) {
      return"";
    }
    return transportUnits.get(index).toString()+"\n\n"+ printTransportUnitsRecursive(index + 1);
  }

  // Method to check if a driver is already assigned to a bus
  public boolean getIsDriverBusy(String driverId) {
    for (int i = 0; i < this.getCurrentUnits(); i++) {
      TransportUnit unit = transportUnits.get(i);
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
    for (int i = 0; i < assignedEmployees.size(); i++) {// checking for duplicate employees
        if (assignedEmployees.get(i).getId().equalsIgnoreCase(e.getId())) {
          System.out.println("This employee is already assigned to the station.");
          return false;
      }
    }
if (assignedEmployees.size() < 50) {
  assignedEmployees.insertAtBack(e);
  return true;
}
    return false;
  }

  // Method to remove an employee from the station
  public boolean removeEmployee(String empId) {
  for (int i = 0; i < assignedEmployees.size(); i++) {
    Employee emp = assignedEmployees.get(i);
    if (emp.getId().equals(empId)) {
      assignedEmployees.remove(emp);
      return true;
    }
  }
  return false;
}

  // Method to print the employees assigned to this station
public String printEmployees() { 
    if (assignedEmployees.isEmpty()) {
        return "No employees currently assigned to this station.\n";
    }
    String info = "";
    for (int i = 0; i < assignedEmployees.size(); i++) {
        info += assignedEmployees.get(i).toString() + "\n-----------------\n";
    }
    return info;
}

  // Method to get a transport unit by ID
public TransportUnit getTransportUnitById(String id) {
    for (int i = 0; i < transportUnits.size(); i++) {
        TransportUnit unit = transportUnits.get(i);
        if (unit.getID().equalsIgnoreCase(id)) {
            return unit;
        }
    }
    return null;
}

  // method to calculate the total number of passengers in the station
public int calNumOfPassengers() {
  int totalPassengers = 0;
  for (int i = 0; i < transportUnits.size(); i++) {
    totalPassengers += transportUnits.get(i).getCurrentPassengers();
  }
  return totalPassengers;
}

  // Method to display station information
  public String displayInfo() {
   return this.toString()+"\n Transport Units:\n"+printTransportUnits()+"\n Employees:\n"+printEmployees();
  }

  public String toString() {
    return ("Station Name: " + stationName + "\nLocation: " + location);
  }
}
