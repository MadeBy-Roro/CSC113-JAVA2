import java.util.Scanner;

public class MetroTest {

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);

      MetroNetwork riyadhMetro = new MetroNetwork("Riyadh Metro", 20, 50, 1000);

      // creating unique manager
      Employee manager = new Employee("Ghala", "ADMIN-01", "Manager");
      riyadhMetro.addEmployee(manager);

      System.out.println("=== Welcome to the Riyadh Metro System ===");

      boolean systemRunning = true;

      // MAIN SYSTEM LOOP
      while (systemRunning) {
         System.out.println("\nSelect your role:");
         System.out.println("1. Manager");
         System.out.println("2. Passenger");
         System.out.println("3. Exit System");
         System.out.print("Choice: ");

         char mainChoice = input.next().charAt(0);
         input.nextLine();

         switch (mainChoice) {
            case '1':// manager information validation
               System.out.println("\n--- Manager Login ---");
               System.out.print("Enter Name: ");
               String empName = input.nextLine();
               System.out.print("Enter ID: ");
               String empId = input.nextLine();
               Employee loggedInManager = riyadhMetro.getEmployeeById(empId);

               if (loggedInManager != null) {
                  System.out.println("\nLogin Successful! Welcome, Manager " + loggedInManager.getName());
                  boolean managerSession = true;

                  // manager menu
                  while (managerSession) {
                     System.out.println("\n-- Manager Tasks --");
                     System.out.println("1. Add a Station");
                     System.out.println("2. Remove a Station");
                     System.out.println("3. Select a Station (Manage Vehicles & Employees)");
                     System.out.println("4. Add Employee");
                     System.out.println("5. Fire Employee");
                     System.out.println("6. Maintenance");
                     System.out.println("7. Display Network Info");
                     System.out.println("8. Logout");
                     System.out.print("Choice: ");

                     char mgrChoice = input.next().charAt(0);
                     input.nextLine();

                     switch (mgrChoice) {

                        case '1':// Adding a station to the network
                           System.out.print("Enter Station Name: ");
                           String sName = input.nextLine();
                           System.out.print("Enter Location: ");
                           String sLoc = input.nextLine();
                           int sCap = 0;
                           do { // to make sure that the capacity is a positive number
                              System.out.print("Enter Max Station Capacity: ");
                              sCap = input.nextInt();
                              if (sCap <= 0)
                                 System.out.println("Please enter a positive number");
                              else
                                 break;
                           } while (sCap <= 0);
                           input.nextLine();

                           Station newStation = new Station(sName, sLoc, sCap);
                           if (riyadhMetro.addStation(newStation)) {
                              System.out.println("Station added successfully!");
                           }
                           break;

                        case '2':// removing a station from the network
                           if (riyadhMetro.getNumOfStations() == 0)
                              System.out.println("No stations to remove.");
                           else {
                              System.out.print("Enter Station Name to remove: ");
                              String removeName = input.nextLine();
                              if (riyadhMetro.removeStation(removeName))
                                 System.out.println("Station removed successfully!");
                              else
                                 System.out.println("Station not found.");
                           }
                           break;

                        case '3':// start of selecting a station to manage
                           if (riyadhMetro.getNumOfStations() == 0) {
                              System.out.println("No stations to manage.");
                              break;
                           }
                           System.out.print("Enter Station Name to select: ");
                           String searchName = input.nextLine();
                           Station selectedStation = riyadhMetro.searchStation(searchName);

                           if (selectedStation != null) {
                              boolean stationSession = true;

                              // station management
                              while (stationSession) {
                                 System.out
                                       .println("\n-- Managing Station: " + selectedStation.getStationName() + " --");
                                 System.out.println("1. Add Vehicle to Station");
                                 System.out.println("2. Remove Vehicle from Station");
                                 System.out.println("3. Assign Employee to Station");
                                 System.out.println("4. Back to Manager Menu");
                                 System.out.print("Choice: ");

                                 char stChoice = input.next().charAt(0);
                                 input.nextLine();

                                 switch (stChoice) {

                                    case '1':// adding a vehicle to the station
                                       System.out.println("What type of vehicle?");
                                       System.out.println("1. Metro");
                                       System.out.println("2. Bus");
                                       System.out.println("3. Bus On Demand");
                                       System.out.print("Choice: ");
                                       char vehicleChoice = input.next().charAt(0);
                                       input.nextLine();
                                       switch (vehicleChoice) {
                                          case '1':// adding a metro to the station
                                             System.out.print("Enter ID: ");
                                             String id = input.nextLine();
                                             int cap = 0;
                                             do { // to make sure that the capacity is a positive number
                                                System.out.print("Enter capacity: ");
                                                cap = input.nextInt();
                                                if (cap <= 0)
                                                   System.out.println("Please enter a positive number");
                                                else
                                                   break;
                                             } while (cap <= 0);
                                             input.nextLine();
                                             System.out.print("Enter line: ");
                                             String line = input.nextLine();

                                             // FIXED: Removed dummy price (10)
                                             TransportUnit metro = new Metro(id, cap, line);

                                             if (selectedStation.addTransportUnit(metro))
                                                System.out.println("Vehicle added successfully!");
                                             else
                                                System.out.println("Failed to add vehicle.");
                                             break;

                                          case '2':// adding a bus to the station
                                             System.out.print("Enter ID: ");
                                             String id2 = input.nextLine();
                                             int cap2 = 0;
                                             do { // to make sure that the capacity is a positive number
                                                System.out.print("Enter capacity: ");
                                                cap2 = input.nextInt();
                                                if (cap2 <= 0)
                                                   System.out.println("Please enter a positive number");
                                                else
                                                   break;
                                             } while (cap2 <= 0);
                                             input.nextLine();
                                             System.out.print("Enter route: ");
                                             String route = input.nextLine();
                                             TransportUnit bus = new Bus(id2, cap2, route);
                                             if (selectedStation.addTransportUnit(bus))
                                                System.out.println("Vehicle added successfully!");
                                             else
                                                System.out.println("Failed to add vehicle.");

                                             // Assigning a driver to the bus, here i added a new feature to assign a
                                             // driver to the bus
                                             System.out.println("Do you want to assign a driver? (Y/N)");
                                             char choice = input.next().charAt(0);
                                             input.nextLine();

                                             if (choice == 'Y' || choice == 'y') {

                                                System.out.print("Enter driver ID: ");
                                                String driverId = input.nextLine();

                                                Employee driver = riyadhMetro.getEmployeeById(driverId);

                                                if (driver == null) {
                                                   System.out.println("Driver not found.");
                                                   break;
                                                }

                                                if (selectedStation.getIsDriverBusy(driverId)) {
                                                   System.out.println("Driver is already assigned to another bus!");
                                                } else {
                                                   Bus b = (Bus) bus;
                                                   boolean assigned = b.assignDriver(driver);

                                                   if (assigned) {
                                                      System.out.println("Driver assigned successfully!");
                                                   } else {
                                                      System.out.println("Failed to assign driver.");
                                                   }
                                                }

                                             } else {
                                                System.out.println("Driver not assigned.");
                                             }
                                             break;

                                          case '3':// adding a bus on demand to the station
                                             System.out.print("Enter ID: ");
                                             String id3 = input.nextLine();
                                             int cap3 = 0;
                                             do { // to make sure that the capacity is a positive number
                                                System.out.print("Enter capacity: ");
                                                cap3 = input.nextInt();
                                                if (cap3 <= 0)
                                                   System.out.println("Please enter a positive number");
                                                else
                                                   break;
                                             } while (cap3 <= 0);
                                             input.nextLine();
                                             System.out.print("Enter route: ");
                                             String route3 = input.nextLine();
                                             System.out.print("Enter neighborhood: ");
                                             String neighborhood = input.nextLine();
                                             TransportUnit busOnDemand = new BusOnDemand(id3, cap3, route3,
                                                   neighborhood);

                                             if (selectedStation.addTransportUnit(busOnDemand))
                                                System.out.println("Bus On Demand added successfully!");
                                             else
                                                System.out.println("Failed to add vehicle.");
                                             // assign a driver to the bus on demand
                                             System.out.println("Do you want to assign a driver? (Y/N)");
                                             char choice1 = input.next().charAt(0);
                                             input.nextLine();

                                             if (choice1 == 'Y' || choice1 == 'y') {

                                                System.out.print("Enter driver ID: ");
                                                String driverId = input.nextLine();

                                                Employee driver = riyadhMetro.getEmployeeById(driverId);

                                                if (driver == null) {
                                                   System.out.println("Driver not found.");
                                                   break;
                                                }

                                                boolean isBusy = selectedStation.getIsDriverBusy(driverId);

                                                if (isBusy) {
                                                   System.out.println("Driver is already assigned to another bus!");
                                                } else {
                                                   BusOnDemand b = (BusOnDemand) busOnDemand;
                                                   boolean assigned = b.assignDriver(driver);

                                                   if (assigned) {
                                                      System.out.println("Driver assigned successfully!");
                                                   } else {
                                                      System.out.println("Failed to assign driver.");
                                                   }
                                                }

                                             } else {
                                                System.out.println("Driver not assigned.");
                                             }
                                             break;

                                          default:
                                             System.out.println("Invalid choice.");
                                       }
                                       break; // End of adding vehicle switch

                                    case '2': // removing a vehicle from the station

                                       if (selectedStation.getCurrentUnits() == 0) {
                                          System.out.println("No vehicles to remove.");
                                          break;
                                       }
                                       System.out.println("Enter vehicle ID to remove: ");
                                       String removeId = input.nextLine();
                                       selectedStation.removeTransportUnit(removeId);
                                       break;

                                    case '3':// Assigning an employee to the station
                                       System.out.println("Enter employee name: ");
                                       String eName = input.nextLine();
                                       System.out.println("Enter employee ID: ");
                                       String eId = input.nextLine();

                                       Employee e = riyadhMetro.getEmployeeById(eId);
                                       if (e != null) {
                                          if (selectedStation.assignEmployee(e)) {
                                             System.out.println("Employee assigned successfully!");
                                          } else {
                                             System.out.println("Failed to assign employee. Station might be full.");
                                          }
                                       } else {
                                          System.out.println(
                                                "Employee not found in the Metro Network. Please add them first.");
                                       }
                                       break;

                                    case '4':// returning to the manager main menu
                                       System.out.println("Returning to Manager Menu...");
                                       stationSession = false; // Exits back to Manager Menu
                                       break;

                                    default:
                                       System.out.println("Invalid choice.");
                                 }
                              }
                           } else {
                              System.out.println("Station not found. Please add it first.");
                           }
                           break;// End of selecting a station to manage

                        case '4': // Add Employee
                           System.out.print("Enter Employee Name: ");
                           String newEmpName = input.nextLine();
                           System.out.print("Enter Employee ID: ");
                           String newEmpId = input.nextLine();
                           System.out.print("Enter Employee Position: ");
                           String newEmpPos = input.nextLine();
                           Employee newEmployee = new Employee(newEmpName, newEmpId, newEmpPos);
                           if (riyadhMetro.addEmployee(newEmployee)) {
                              System.out.println("Employee added successfully!");
                           } else
                              System.out.println("Failed to add employee.");
                           break;

                        case '5': // Fire Employee
                           if (riyadhMetro.getNumOfEmployees() == 0) {
                              System.out.println("No employees to fire.");
                           }
                           System.out.println("Enter Employee ID to fire: ");
                           String fireId = input.nextLine();
                           if (riyadhMetro.removeEmployee(fireId)) {
                              System.out.println("Employee fired successfully!");
                           } else
                              System.out.println("Employee not found.");
                           break;

                        case '6':
                           if (riyadhMetro.getProfit() < 200) {
                              System.out.println("Not enough profit for maintenance.");
                              break;
                           }
                           riyadhMetro.maintenance();
                           break;
                        case '7':// Print all the info in the network
                           riyadhMetro.displayNetworkInfo();
                           break;
                        case '8':// logging out, returning to login menu
                           System.out.println("Logging out manager...");
                           managerSession = false; // Logs out
                           break;

                        default:
                           System.out.println("Invalid choice.");
                     }
                  }
               } else {
                  System.out.println(" Login Failed. Invalid Name or ID.");
               }
               break;

            case '2': // Passenger login menu
               System.out.println("\n--- Passenger Menu ---");
               System.out.println("1. Login to existing account");
               System.out.println("2. Create new account");
               System.out.print("Choice: ");
               char passChoice = input.next().charAt(0);
               input.nextLine();

               Passenger loggedInPassenger = null;

               switch (passChoice) {
                  case '1':
                     System.out.print("Enter Name: ");
                     String pName = input.nextLine();
                     System.out.print("Enter ID: ");
                     String pId = input.nextLine();
                     loggedInPassenger = riyadhMetro.getPassenger(pId);

                     if (loggedInPassenger == null) {
                        System.out.println("Account not found. Please try again or create an account.");
                     }
                     break;
                  case '2':
                     System.out.print("Enter new Name: ");
                     String newName = input.nextLine();
                     System.out.print("Enter new ID: ");
                     String newId = input.nextLine();

                     loggedInPassenger = new Passenger(newName, newId, 0);
                     riyadhMetro.addPassenger(loggedInPassenger);
                     System.out.println("Account created successfully!");
                     break;
                  default:
                     System.out.println("Invalid choice.");
               }

               if (loggedInPassenger != null) {
                  boolean passSession = true;

                  while (passSession) {// Passenger main menu
                     System.out.println("\n-- Welcome, " + loggedInPassenger.getName() + " --");
                     System.out.println("1. Buy a Ticket");
                     System.out.println("2. Display Info and Balance");
                     System.out.println("3. Refund Ticket");
                     System.out.println("4. Add Balance");
                     System.out.println("5. Board a Vehicle");
                     System.out.println("6. Unboard a Vehicle");
                     System.out.println("7. Logout");
                     System.out.print("Choice: ");

                     char actionChoice = input.next().charAt(0);
                     input.nextLine();

                     switch (actionChoice) {
                        case '1':
                           // Ticket purchase menu
                           System.out.println("Select ticket type:");
                           System.out.println("1. 2 hours ticket (4 SR)");
                           System.out.println("2. 7 days ticket (40 SR)");
                           System.out.println("3. 30 days ticket (140 SR)");
                           System.out.println("4. 2 hours first class ticket (10 SR)");
                           System.out.println("5. 7 days first class ticket (100 SR)");
                           System.out.println("6. 30 days first class ticket (350 SR)");
                           System.out.print("Choice: ");

                           char ticketChoice = input.next().charAt(0);
                           input.nextLine();
                           // determine price based on user's choice
                           int price = 0;
                           switch (ticketChoice) {
                              case '1':
                                 price = 4;
                                 break;
                              case '2':
                                 price = 40;
                                 break;
                              case '3':
                                 price = 140;
                                 break;
                              case '4':
                                 price = 10;
                                 break;
                              case '5':
                                 price = 100;
                                 break;
                              case '6':
                                 price = 350;
                                 break;
                              default:
                                 System.out.println("Invalid choice.");
                                 break;
                           }
                           if (price > 0) {
                              loggedInPassenger.buyTicket(price);
                           }
                           break;

                        case '2':
                           System.out.println(loggedInPassenger.toString());
                           break;
                        case '3':
                           loggedInPassenger.refundTicket();
                           break;
                        case '4':
                           System.out.print("Enter amount to add: ");
                           double deposit = input.nextDouble();
                           input.nextLine();
                           loggedInPassenger.addBalance(deposit);
                           break;

                        case '5':
                           // Board a vehicle
                           if (loggedInPassenger.getTicket() == null) {
                              System.out.println("You need to buy a ticket before boarding!");
                              break;
                           }

                           System.out.print("Enter the name of the Station you are currently at: ");
                           String boardStationName = input.nextLine();
                           Station boardStation = riyadhMetro.searchStation(boardStationName);

                           if (boardStation != null) {
                              System.out.println("--- Available Vehicles ---");
                              boardStation.printTransportUnits();
                              System.out.print("Enter the ID of the vehicle you want to board: ");
                              String vId = input.nextLine();
                              TransportUnit selectedVehicle = boardStation.getTransportUnitById(vId);
                              if (selectedVehicle != null) {
                                 riyadhMetro.boardPassengerOnVehicle(loggedInPassenger, selectedVehicle);
                              } else {
                                 System.out.println("Vehicle ID not found at this station.");
                              }
                           } else {
                              System.out.println("Station not found in the network.");
                           }
                           break;

                        case '6':
                           System.out.print("Enter the name of the Station you are unboarding at: ");
                           String unboardStationName = input.nextLine();
                           Station unboardStation = riyadhMetro.searchStation(unboardStationName);

                           if (unboardStation != null) {
                              System.out.print("Enter the ID of the vehicle you are leaving: ");
                              String arrVId = input.nextLine();

                              TransportUnit arrivingVehicle = unboardStation.getTransportUnitById(arrVId);

                              if (arrivingVehicle != null) {
                                 riyadhMetro.unboardPassengerFromVehicle(loggedInPassenger.getId(), arrivingVehicle);
                              } else {
                                 System.out.println("Vehicle ID not found at this station.");
                              }
                           } else {
                              System.out.println("Station not found in the network.");
                           }
                           break;

                        case '7':
                           passSession = false;
                           System.out.println("Logging out...");
                           break;
                        default:
                           System.out.println("Invalid choice.");
                     }
                  }
               }
               break;
            case '3':
               System.out.println("Shutting down the Riyadh Metro system. Goodbye!");
               systemRunning = false;
               break;
            default:
               System.out.println("Invalid choice. Please enter 1, 2, or 3.");
         }
      }
   }
}