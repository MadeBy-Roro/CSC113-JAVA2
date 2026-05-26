import java.io.*;
import static java.lang.Math.*;

public class MetroNetwork implements Serializable {

  // Attributes
  private String networkName;
  private List<Station> stations; // aggregation relationship with Station
  private List<Employee> employees; // aggregation relationship with Employee
  private List<Passenger> passengers; // aggregation relationship with Passenger
  private int profit;

  // Constructor with parameters
  public MetroNetwork(String networkName) {
    this.networkName = networkName;
    stations = new List<Station>("Stations List");
    employees = new List<Employee>("Employees List");
    passengers = new List<Passenger>("Passengers List");
    profit = 0;
  }

  // Employee methods
  // Add employee to the network, updated to ls
  public boolean addEmployee(Employee e) {// need check
    employees.insertAtBack(e);
    return true;
  }

  // Get employee by id , updated to ls
  public Employee getEmployeeById(String id) {
    for (int i = 0; i < employees.size(); i++) {
      Employee currentEmployee = employees.get(i);
      if (currentEmployee.getId().equalsIgnoreCase(id)) { // check if the employee is in the network
        return currentEmployee;
      }
    }
    return null;
  }

  // Remove employee from the network, updated to ls
  public boolean removeEmployee(String id) {
    Employee emp = getEmployeeById(id);
    if (emp != null) {
      employees.remove(emp);
      return true;
    }
    return false;
  }

  // Passenger methods
  // Add passenger to the network, updated to ls

  public boolean addPassenger(Passenger p) {
    passengers.insertAtBack(p);
    return true;
  }

  // Remove passenger from the network, updated to ls
  public boolean removePassenger(String id) {
    Passenger p = getPassenger(id);
    if (p != null) {
      passengers.remove(p);
      return true;
    }
    return false;
  }

  // Get passenger from the network, updated to ls
  public Passenger getPassenger(String id) {
    for (int i = 0; i < passengers.size(); i++) {
      Passenger p = passengers.get(i);
      if (p.getId().equalsIgnoreCase(id)) { // check if the passenger is in the network
        return p;
      }
    }
    return null;
  }

  // Boarding for the passenger
  public boolean boardPassengerOnVehicle(Passenger p, TransportUnit vehicle) {
    if (vehicle.addPassenger(p)) { // check if the vehicle is full
      System.out.println(p.getName() + " successfully boarded vehicle " + vehicle.getID()); // add the passenger to the
                                                                                            // vehicle
      profit += p.getTicket().getPrice(); // add the ticket price to the profit
      return true;
    } else {
      System.out.println("Boarding failed. The vehicle is full!"); // prompt the user that the vehicle is full
      return false;
    }
  }

  // Unboarding for the passenger
  public boolean unboardPassengerFromVehicle(String passengerId, TransportUnit vehicle) {
    if (vehicle.removePassenger(passengerId)) { // check if the passenger is in the vehicle
      System.out
          .println("Passenger with ID " + passengerId + " successfully unboarded from vehicle " + vehicle.getID()); // remove
                                                                                                                    // the
                                                                                                                    // passenger
                                                                                                                    // from
                                                                                                                    // the
                                                                                                                    // vehicle
      return true;
    } else {
      System.out.println("Error: Passenger with ID " + passengerId + " is not currently on vehicle " + vehicle.getID()); // prompt
                                                                                                                         // the
                                                                                                                         // user
                                                                                                                         // that
                                                                                                                         // the
                                                                                                                         // passenger
                                                                                                                         // is
                                                                                                                         // not
                                                                                                                         // in
                                                                                                                         // the
                                                                                                                         // vehicle
      return false;
    }
  }

  // Station methods
  // Add station to the network, updated to ls
  public boolean addStation(Station s) {
    try {
      searchStation(s.getStationName());// check if the station is already in the network
      return false;
    } catch (StationNotFoundException e) {
      stations.insertAtBack(s);
      return true;
    }
  }

  // Remove station from the network, updated to ls
  public boolean removeStation(String stationName) {
    try {
      Station s = searchStation(stationName);
      if (s != null) {
        stations.remove(s);
        return true;
      }
    } catch (StationNotFoundException e) {
      return false;
    }
    return false;
  }

  public int getNumOfStations() {
    return stations.size();
  }

  // Search for a station in the network, updated to ls
  public Station searchStation(String stationName) throws StationNotFoundException {
    for (int i = 0; i < stations.size(); i++) {
      Station currentStation = stations.get(i);
      if (currentStation.getStationName().equalsIgnoreCase(stationName)) {
        return currentStation;
      }
    }
    throw new StationNotFoundException("Error: Station '" + stationName + "' does not exist.");
  }

  // Getters
  public String getNetworkName() {
    return networkName;
  }

  public int getProfit() {
    return profit;
  }

  public void setProfit(int profit) {// extra
    try {
      if (profit < 0) {
        throw new IllegalArgumentException("Profit cannot be set to a negative number.");
      }
      this.profit = profit;
    } catch (IllegalArgumentException e) {
      System.out.println("Handled Exception: " + e.getMessage());
    }
  }

  // Display for the manager
  public String displayNetworkInfo() {
    String info = "";
    // Display the network name and the number of stations, employees and passengers

    info += " -------------------------------------------------\n";
    info += " Network Name: " + networkName + "\n";
    info += " Number of Stations: " + stations.size() + "\n";
    info += " Number of Employees: " + employees.size() + "\n";
    info += " Number of Passengers: " + passengers.size() + "\n";
    info += " Total profit: " + profit + "\n";
    info += " -------------------------------------------------\n";
    // Display the stations info

    for (int i = 0; i < stations.size(); i++) {
      Station currentStation = stations.get(i);

      info += " -------------------------------------------------\n";
      info += "                    Station: " + (i + 1) + "\n";
      info += " -------------------------------------------------\n";
      info += " Station Name: " + currentStation.getStationName() + "\n";
      info += " Number of Vehicles: " + currentStation.getCurrentUnits() + "\n";
      info += " Number of Employees: " + currentStation.getNumOfEmployees() + "\n";
      info += " Number of Passengers: " + currentStation.calNumOfPassengers() + "\n";
      info += " -------------------------------------------------\n";

      // Display the vehicles info

      for (int j = 0; j < currentStation.getCurrentUnits(); j++) {
        info += " -------------------------------------------------\n";
        info += "                    Vehicle: " + (j + 1) + "\n";
        info += " -------------------------------------------------\n";
        info += " Vehicle ID: " + currentStation.getTransportUnits().get(j).getID() + "\n";
        info += " Number of Passengers: " + currentStation.getTransportUnits().get(j).getCurrentPassengers() + "\n";
        info += " -------------------------------------------------\n";
        // passengers info if theres any
        if (currentStation.getTransportUnits().get(j).getCurrentPassengers() == 0) {
          info += "| There are no passengers in this vehicle.        |\n";
          info += " -------------------------------------------------\n";
          continue;
        }

        info += " -------------------------------------------------------------------------------------\n";

        info += "|                                      Passengers                                     |\n";
        info += " -------------------------------------------------------------------------------------\n";

        info += currentStation.getTransportUnits().get(j).displayPassengers();

        info += " -------------------------------------------------------------------------------------\n";
      }
    }

    return info;
  }

  // Maintenance method
  public String maintenance() {
    List<Station> stationsWithVehicles = new List<Station>("Stations with vehicles");

    if (stations.size() == 0) {
      return "There are no stations in the network.";
    }

    for (int i = 0; i < stations.size(); i++) {
      Station currentStation = stations.get(i);
      if (currentStation.getCurrentUnits() > 0) {
        stationsWithVehicles.insertAtBack(currentStation);
      }
    }

    if (stationsWithVehicles.size() == 0) {
      return "There are no vehicles in the network.";
    }

    int check = (int) (Math.random() * 10);
    int indexOfStations = 0;
    int indexOfVehicles = 0;

    if (check % 2 != 0) {
      profit -= 200;
      indexOfStations = (int) (Math.random() * stationsWithVehicles.size());
      indexOfVehicles = (int) (Math.random() * stationsWithVehicles.get(indexOfStations).getCurrentUnits());

      return "The vehicle with ID: "
          + stationsWithVehicles.get(indexOfStations).getTransportUnits().get(indexOfVehicles).getID()
          + " in station: " + stationsWithVehicles.get(indexOfStations).getStationName()
          + " needs maintenance. \n\nThe profit after the maintenance is: " + profit;
    } else {
      return "No maintenance needed today.";
    }
  }

  public Station getStation(int index) {
    return stations.get(index);
  }

  public Employee getEmployee(int index) {
    return employees.get(index);
  }

  public int getNumOfEmployees() {
    return employees.size();
  }

}