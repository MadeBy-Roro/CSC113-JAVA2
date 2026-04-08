public class Bus extends TransportUnit{

  //Attributes
  protected String routeNumber;
  protected Employee driver;

  // Constructor with parameters
  public Bus(String id, int capacity, String routeNumber){
     super(id, capacity); //inherits from TransportUnit
     this.routeNumber = routeNumber;
  }

  /*Method to assign a driver to the bus, returns true if the driver is assigned successfully, false if the driver is already assigned or the employee is not a driver*/
  public boolean assignDriver(Employee driver){
    
    // Frist check if the employee is not null and has the position of driver
    if( driver != null && driver.getPosition().equalsIgnoreCase("Driver")){
      
      // Check if the bus already has no a driver
        if(this.driver == null){
          this.driver = driver;
          return true;
          
        } else {
          System.out.println("This bus already has a driver "+ this.driver.getName());
          return false;
  
        
        }} else { 
          System.out.println(" Can't assign a employee " + (driver != null ? driver.getName() : " null") + " as a driver. Wronge position "); 
          return false;
      }   
  }
  
  // Setters and getters
  public Employee getDriver(){
    return driver;
  }
  
  public void setRouteNumber(String routeNumber){
    this.routeNumber = routeNumber;
  }
  
  public String getRouteNumber(){
    return routeNumber;
  }

  //toString method to display bus details
  public String toString() {
      String driverInfo = (driver != null) ? driver.toString() : "No driver assigned";
      return super.toString() + "\nRoute Number: " + routeNumber + "\nDriver: " + driverInfo;
  }
  
}

