abstract class Car {

   protected int id;
   protected String color;
   protected String oilType;
   protected int meters;

   public Car(int id, String color, String oilType, int meters) {
      this.id = id;
      this.color = color;
      this.oilType = oilType;
      this.meters = meters;
   }

   public abstract boolean changeOil();

   public void display() {
      System.out.println("ID: " + id);
      System.out.println("Color: " + color);
      System.out.println("Oil Type: " + oilType);
      System.out.println("Meters: " + meters);
   }
}

interface Honda {
   boolean isUnique();
}

class Accord extends Car implements Honda {

   public Accord(int id, String color, String oilType, int meters) {
      super(id, color, oilType, meters);
   }

   @Override
   public boolean changeOil() {
      return meters > 9000;
   }

   @Override
   public boolean isUnique() {
      return oilType.equals("Petrol 95")
             && color.equals("Gold")
             && meters < 20000;
   }

   @Override
   public void display() {
      System.out.println("---------");
      super.display();
   }
}

public class Test {

   public static void main(String[] args) {
   
      Honda[] hondas = new Honda[3];
   
      hondas[0] = new Accord(11, "Blue", "Petrol 90", 8600);
      hondas[1] = new Accord(22, "Gold", "Petrol 95", 10000);
      hondas[2] = new Accord(33, "Gold", "Diesel", 31780);
   
      // Display unique Accord
      System.out.println("Unique Accord");
      for (int i = 0; i < hondas.length; i++) {
         Accord a = (Accord) hondas[i];
         if (a.isUnique()) {
            a.display();
         }
      }
   
      // Display Accords that need oil change
      int count = 0;
      System.out.println( "Accords That Need Oil Change");
      for (int i = 0; i < hondas.length; i++) {
         Accord a = (Accord) hondas[i];
         if (a.changeOil()) {
            count++;
            a.display();
         }
      }
   
      System.out.println("Number of Accords that need oil change: " + count);
   }
}
