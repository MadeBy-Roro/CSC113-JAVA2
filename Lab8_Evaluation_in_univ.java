import java.util.*;

/* or
import java.util.Scanner;
import java.util.InputMismatchException; */
 
 class Stuff{

protected String name;
protected int ID;
protected double salary;
protected int hours;

public Stuff( String name, int ID, double salary, int hours){
    
   this.name = name;
   this.ID= ID;
   this.salary=salary;
   this.hours= hours;
 }
 
public String toString() {

  return "Name:\t" + this.name + "\n ID:\t" + this.ID + " \n Salary\t" + this.salary + "\nHours: \t" + this.hours;
  
 }
 
 // no need to setter and getter in this position 
 }
 

class Cashier extends Stuff {

 protected int overtime;
 
 public Cashier ( String name, int ID, double salary , int hours , int overtime){
 
  super(name, ID, salary, hours);
  this.overtime = overtime;
 }
 
 
 public double getAllowance(){
   
  Scanner input = new Scanner(System.in);
  double allownce =0;
   
  while(true){
   try{
   allownce = (salary * overtime) / ( hours * 100);
   break;
   } catch ( Exception e){
      System.out.println("Invalid hours can not be zero , try again !! " );
      hours = input.nextInt();
    }    
  }
  
  return allownce;    
 }
 
 public String toString (){
  
 return super.toString() + " \n Overtime:\t" + this.overtime;
  
 }
  
 }
  
  
  
 // no need to setter and getter in this position
 
 public class test {
  
 public static void main( String args[]){
    Scanner input = new Scanner(System.in);
    
    Cashier sList [] = new Cashier[3]; // aray size 3
    
    for( int i =0; i<2 ; i++){
     System.out.println(" Enter cashier " + ( i + 1));
     
     try{
      System.out.println("Name: " );
      String name = input.next();
       
      System.out.println("ID: " );
      int ID = input.nextInt();
       
      System.out.println("Salary: " );
      double salary = input.nextDouble();
       
      System.out.println(" Hours: " );
      int hours = input.nextInt();
       
      System.out.println("Overtime: " );
      int overtime = input.nextInt();
     
      sList[i] = new Cashier( name,ID,salary,hours,overtime); // fill information
    } catch ( InputMismatchException e){
      System.out.println("Invalid input! try againg !!" );
      input.nextLine();
      i--;
    }
   } 
     
    System.out.println("# Cashier info #" );
      for( int i =0 ; i< 2 ; i ++){
      System.out.println(sList[i]);
      System.out.println( "Allowance:\t" +sList[i].getAllowance());
     }   
     
  }
   
  }
