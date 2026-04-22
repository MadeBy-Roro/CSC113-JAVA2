import java.util.*;
class NegativeNumberException extends Exception{ // to can use try-catch

  public NegativeNumberException (String msg){ // reseive from constructor and send a massege to the parent to print exception 
   super(msg);
  }
  
}
 
 
class Person{
 
  private String name;
  private int id;
  private int age;
  
  public Person( String name, int id, int age) throws NegativeNumberException{
  
   if( age<0){
     throw new NegativeNumberException("invaild age!"); 
   } 
    
   this.name=name;
   this.id=id;
   this.age=age;
    
  }
  
  public String toString(){
   return "Name:\t" + this.name+ "\nID:\t" + this.id + "\nAge:\t"+ this.age;
  }
  
}
 
 
class Student extends Person{
  
  private int sid;
  private int level;
  
  public Student(String name, int id, int age, int sid , int level)throws NegativeNumberException{
    
   super(name,id,age);
   this.sid=sid;
   this.level=level;
  }
  
  public double calculateGpa( String point, int hours){
  
  // convert String to number
   double p =Double.parseDouble(point);
   return p / hours;
  
  }
  
  public String toString (){
   return super.toString() + "\n SID:\t" + this.sid + "\nLevel\t" + this.level;
  
  }
}

public class Test{
  public static void main (String args[]){
  
   Scanner input =new Scanner(System.in);
   Person[] pList = new Person[3]; //store three person
    
   int count = 0;  
   while( count<2){      
     System.out.println("Enter name" );
     String name =input.next();
        
     System.out.println("Enter id" );
     int id =input.nextInt();
     
     System.out.println("Enter sid" );
     int sid =input.nextInt();
        
     System.out.println("Enter level" );
     int level =input.nextInt();
     
     
     while(true){
      try{
        System.out.println("Enter age" );
        int age =input.nextInt();
       
        Student s = new Student(name,id,age,sid,level);
        pList[count] = s;
        count++;
        break;
       
      } catch (NegativeNumberException e){
        System.out.println(e.getMessage());
        System.out.println(" Try agin to re-inter age");
      }
     }
     
   }  
     
   for ( int i = 0; i< count; i++){
     System.out.println(pList[i]);
   }
      
    
   
  
  }
    
}     
  
  
