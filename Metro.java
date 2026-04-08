public class Employee extends Person{
  
  // Attributes 
  private String position;
  
  // Constructor with parameters
  public Employee(String name, String id, String position){
    super(name,id);
    this.position = position;
  }
  
  //Setter and getter methods
  public void setPosition(String position){
   this.position = position;
  }
  
  public String getPosition(){
    return position;
  }

  // toString method to display employee details
  public String toString() {
      return super.toString() + " | Position: " + position;  
  }
  
}