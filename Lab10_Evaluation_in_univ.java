import java.io.*;
/*
class Flower implements Serializable{
  private char type;
  private double price;
  
  public Flower(char type, double price){
   this.type=type;
   this.price=price;
  }
}
*/

public class Bouquet {
  public static void main (String args[]) throws IOException {
     int tulipcount = 0;
     
      
      // read file
      FileInputStream fileIn = new FileInputStream("Bouquet_List.dat");
      DataInputStream dataIn = new DataInputStream(fileIn);
  
      try{
        // infinte number of reading, and it will be exited at the end of the file
        while(true){
         char flowerType = dataIn.readChar();
         double price = dataIn.readDouble();
          
         if( flowerType == 'T'){
           tulipcount++;
         }
        }
      } catch (EOFException e){
        // achivfe end of file to stop loop
      }
       
      dataIn.close();
       
      // creat a file and print it
      FileWriter fileOut = new FileWriter("Report.txt");
      PrintWriter writer =new PrintWriter(fileOut);
       
      writer.println("Number of Tulip flower:\t" + tulipcount);
       
      writer.close();
       
      System.out.println("Creat file Report have done");
      
   }
 }
       
