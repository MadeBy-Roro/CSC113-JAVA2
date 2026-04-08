
import static java.lang.Math.*;

public class MetroNetwork {

  // Attributes
  private String networkName;
  private Station[] stations; // aggregation relationship with Station
  private int numOfStations;
  private Employee[] employees; // aggregation relationship with Employee
  private int numOfEmployees;
  private Passenger[] passengers; // aggregation relationship with Passenger
  private int numOfPassengers;
  private int profit;

  // Constructor with parameters
  public MetroNetwork(String networkName, int maxStations, int maxEmployees, int maxPassengers) {
    this.networkName = networkName;
    this.stations = new Station[maxStations];
    this.numOfStations = 0;
    this.employees = new Employee[maxEmployees];
    this.numOfEmployees = 0;
    this.passengers = new Passenger[maxPassengers];
    this.numOfPassengers = 0;
    this.profit = 0;
  }

  // Employee methods
  // Add employee to the network
  public boolean addEmployee(Employee e) {
    if (numOfEmployees < employees.length) { // check if the network is full
      employees[numOfEmployees++] = e; // add the employee to the network
      return true;
    } else {
      return false;
    }
  }

  // Get employee by id ,
  public Employee getEmployeeById(String id) {
    for (int i = 0; i < numOfEmployees; i++) {
      if (employees[i].getId().equalsIgnoreCase(id)) {
        return employees[i];
      }
    }
    return null;
  }

  // Remove employee from the network
  public boolean removeEmployee(String id) {
    for (int i = 0; i < numOfEmployees; i++) {
      if (employees[i].getId().equalsIgnoreCase(id)) { // check if the employee is in the network
        for (int j = i; j < numOfEmployees - 1; j++) { // shift the employees to the left
          employees[j] = employees[j + 1];
        }
        employees[numOfEmployees - 1] = null; // remove the last employee
        numOfEmployees--; // decrement the number of employees
        return true;
      }
    }
    return false;
  }

  // Getter for the number of employees
  public int getNumOfEmployees() {
    return numOfEmployees;
  }

  // Passenger methods
  public boolean addPassenger(Passenger p) {
    if (numOfPassengers < passengers.length) { // check if the network is full
      passengers[numOfPassengers++] = p; // add the passenger to the network
      return true;
    } else {
      return false;
    }
  }

  // Remove passenger from the network
  public boolean removePassenger(String id) {
    for (int i = 0; i < numOfPassengers; i++) {
      if (passengers[i].getId().equalsIgnoreCase(id)) { // check if the passenger is in the network
        for (int j = i; j < numOfPassengers - 1; j++) { // shift the passengers to the left
          passengers[j] = passengers[j + 1];
        }
        passengers[numOfPassengers - 1] = null; // remove the last passenger
        numOfPassengers--; // decrement the number of passengers
        return true;
      }
    }
    return false;
  }

  // Get passenger from the network
  public Passenger getPassenger(String id) {
    for (int i = 0; i < numOfPassengers; i++) {
      if (passengers[i].getId().equalsIgnoreCase(id)) { // check if the passenger is in the network
        return passengers[i];
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
  public boolean addStation(Station s) {
    if (numOfStations < stations.length) {
      if (searchStation(s.getStationName()) != null) { // check if the station is already in the network
        System.out.println("Station already exists."); // prompt the user that the station is already in the network
        return false;
      }
      stations[numOfStations++] = s; // add the station to the network
      return true;
    } else {
      System.out.println("Network is full. Cannot add more stations."); // prompt the user that the network is full
      return false;
    }
  }

  // Remove station from the network
  public boolean removeStation(String stationName) {
    for (int i = 0; i < numOfStations; i++) {
      if (stations[i].getStationName().equalsIgnoreCase(stationName)) { // check if the station is in the network
        for (int j = i; j < numOfStations - 1; j++) { // shift the stations to the left
          stations[j] = stations[j + 1];
        }
        stations[numOfStations - 1] = null; // remove the last station
        numOfStations--; // decrement the number of stations
        return true;
      }
    }
    return false;
  }

  // Search for a station in the network
  public Station searchStation(String stationName) {
    for (int i = 0; i < numOfStations; i++) {
      if (stations[i].getStationName().equalsIgnoreCase(stationName)) { // check if the station is in the network
        return stations[i];
      }
    }
    return null;
  }

  // Getters
  public String getNetworkName() {
    return networkName;
  }

  public int getProfit() {
    return profit;
  }

  public int getNumOfStations() {
    return numOfStations;
  }

  // Display for the manager
  public void displayNetworkInfo() {

    // Display the network name and the number of stations, employees and passengers

    System.out.println(" -------------------------------------------------");

    System.out.printf("| Network Name: %-34s|%n", networkName);

    System.out.printf("| Number of Stations: %-28d|%n", numOfStations);

    System.out.printf("| Number of Employees: %-27d|%n", numOfEmployees);

    System.out.printf("| Number of Passengers: %-26d|%n", numOfPassengers);

    System.out.printf("| Total profit: %-34d|%n", profit);

    System.out.println(" -------------------------------------------------");

    // Display the stations info

    for (int i = 0; i < numOfStations; i++) {

      System.out.println(" -------------------------------------------------");

      System.out.printf("|                    Staion: %-21d|%n", (i + 1));

      System.out.println(" -------------------------------------------------");

      System.out.printf("| Station Name: %-34s|%n", stations[i].getStationName());

      System.out.printf("| Number of Vehicles: %-28d|%n", stations[i].getCurrentUnits());

      System.out.printf("| Number of Employees: %-27d|%n", stations[i].getNumOfEmployees());

      System.out.printf("| Number of Passengers: %-26d|%n", stations[i].calNumOfPassengers());

      System.out.println(" -------------------------------------------------");

      // Display the vehicles info

      for (int j = 0; j < stations[i].getCurrentUnits(); j++) {

        System.out.println(" -------------------------------------------------");

        System.out.printf("|                    Vehicles: %-19d|%n", (j + 1));

        System.out.println(" -------------------------------------------------");

        System.out.printf("| Vehicle ID: %-36s|%n", stations[i].getTransportUnits()[j].getID());

        System.out.printf("| Number of Passengers: %-26d|%n",
            stations[i].getTransportUnits()[j].getCurrentPassengers());

        // Display the passengers info if there are any

        System.out.println(" -------------------------------------------------");

        if (stations[i].getTransportUnits()[j].getCurrentPassengers() == 0) {

          System.out.println("| There are no passengers in this vehicle.        |");

          System.out.println(" -------------------------------------------------");

          continue;

        }

        // Display the passengers info

        System.out.println(" -------------------------------------------------------------------------------------");

        System.out.println("|                                     Passengers                                      |");

        System.out.println(" -------------------------------------------------------------------------------------");

        stations[i].getTransportUnits()[j].displayPassengers();

        System.out.println(" -------------------------------------------------------------------------------------");

      }

    }

  }

  // Maintenance method
  public void maintenance() {
    Station[] stationsWithVehicles = new Station[numOfStations]; // array to store the stations with vehicles
    int numOfStationsWithVehicles = 0; // number of stations with vehicles
    if (numOfStations == 0) { // check if there are any stations in the network
      System.out.println("There are no stations in the network");
      return;
    }
    for (int i = 0; i < numOfStations; i++) {
      if (stations[i].getCurrentUnits() > 0) { // check if the station has any vehicles
        stationsWithVehicles[numOfStationsWithVehicles] = stations[i]; // add the station to the array
        numOfStationsWithVehicles++; // increment the number of stations with vehicles
      }
    }
    if (numOfStationsWithVehicles == 0) { // check if there are any vehicles in the network
      System.out.println("There are no vehicles in the network"); // prompt the user that there are no vehicles in the
                                                                  // network
      return;
    }
    int check = (int) (Math.random() * 10); // random possiblity wether there is a maintenance or not
    int indexOfStations = 0; // index of the station with the vehicle that needs maintenance
    int indexOfVehicles = 0; // index of the vehicle that needs maintenance

    // The chances of maintenance are 50%, if you get 1,3,5,7,9 then there is a
    // maintenance, else there is no maintenance

    if (check % 2 != 0) {
      profit -= 200;
      indexOfStations = (int) (Math.random() * numOfStationsWithVehicles);/*
                                                                           * randomly select a station with vehicles
                                                                           * (multiplied by the number of stations with
                                                                           * vehicles to get a random index in range of
                                                                           * the number of stations with vehicles)
                                                                           */
      indexOfVehicles = (int) (Math.random() * stationsWithVehicles[indexOfStations].getCurrentUnits());/*
                                                                                                         * randomly
                                                                                                         * select a
                                                                                                         * vehicle in
                                                                                                         * the station
                                                                                                         * with vehicles
                                                                                                         * to be
                                                                                                         * maintained
                                                                                                         * (multiplied
                                                                                                         * by the number
                                                                                                         * of vehicles
                                                                                                         * in the
                                                                                                         * station to
                                                                                                         * get a random
                                                                                                         * index in
                                                                                                         * range of the
                                                                                                         * number of
                                                                                                         * vehicles in
                                                                                                         * the station)
                                                                                                         */
      System.out.println(
          "The vehicle with ID: " + stationsWithVehicles[indexOfStations].getTransportUnits()[indexOfVehicles].getID()
              + " in station: " + stationsWithVehicles[indexOfStations].getStationName()
              + " needs maintenance. \nThe profit after the maintenance is: " + profit);
    } else {
      System.out.println("No maintenance needed");// prompt the user that there is no maintenance needed
    }
  }

}