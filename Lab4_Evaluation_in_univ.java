class Vehicle {

   protected int id;
   protected int days;

   public Vehicle ( int id, int days){
   
      this.id = id;
      this.days = days;
   
   }

   public String toString(){
   
      return "ID: " + id + " Days: " + days;
   
   }
  
   public void setId ( int id ){
   
      this.id= id;
   
   }
 
   public void setDays ( int days ){
   
      this.days= days;
   
   }

   public int getId(){
   
      return id;
   }

   public int getDays(){
   
      return days;
   }
 

 
}

class Car extends Vehicle {

   private int kilos;
   
   public Car ( int id, int days, int kilos){
   
      super( id, days);
      this.kilos= kilos;
   }
 
 
   public int  CalculateRent(){
   
      return days * kilos;
   
   }

   public String toString(){
   
      return " Kilos: " + kilos + " Rent : " + CalculateRent();
   
   }

   public void setKilos ( int kilos ){
   
      this.kilos = kilos;
   
   }

   public int getKilos(){
   
      return kilos;
     
   }
     
   
}



class Truck extends Vehicle {

   private int capacity;
   
   public Truck ( int id, int days, int capacity){
   
      super( id, days);
      this.capacity= capacity;
   }
 
 
   public int  CalculateRent(){
   
      if ( capacity <= 10){
         return 200* days;
      }else{
         return 300*days;
      }
   
   }

   public String toString(){
   
      return " Capacity: " + capacity + " Rent : " + CalculateRent();
   
   }

   public void setCapacity ( int capacity ){
   
      this.capacity = capacity;
   
   }

   public int getCapacity(){
   
      return capacity;
     
   }


}


public class TestRent{
   public static void main (String [] args){
   
      Vehicle obj1 =new Vehicle(1, 2);
      Car obj2 =new Car(2, 6, 100);
      Truck obj3 = new Truck(4, 5, 10);
   
      System.out.println(obj1);
      System.out.println(obj2);
      System.out.println(obj3);
   
   
   
   
   
   }
}
