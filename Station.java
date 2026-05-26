import java.io.*;
public abstract class Person implements Serializable{

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
   // Setters (test something)
   public void setName(String name){
      this.name = name;
   }
   public void setId(String id){
      this.id = id;
   }
   // Getters
  public String getName(){
     return name;
  }
  public String getId(){
     return id;
  }
}