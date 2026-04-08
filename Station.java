public abstract class Person{

 //Attributes
  protected String name;
  protected String id;

    
// Constructor with parameters    
public Person(String name, String id){
    this.name = name;
    this.id = id;
}
   // toString method to display person details
   public String toString() {
       return "Name: " + name + " | ID: " + id;
   }

   // Getters
  public String getName(){
     return name;
  }
  public String getId(){
     return id;
  }
}