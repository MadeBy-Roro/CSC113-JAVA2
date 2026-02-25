
abstract class Animal {

   private String name;
   protected double weight;

   public Animal(String name, double weight) {
      this.name = name;
      this.weight = weight;
   }

   public abstract void sleep();
   
   public void setName(String n){
      name = n;
   }

   public String getName() {
      return name;
   }

   public double getWeight() {
      return weight;
   }

   @Override
   public String toString() {
      return "Name: " + name + ", Weight: " + weight;
   }
}


abstract class Reptiles extends Animal {

   private int amountOfPlants;   

   public Reptiles(String name, double weight, int amountOfPlants) {
      super(name, weight);
      this.amountOfPlants = amountOfPlants;
   }

   public int getAmountOfPlants() {
      return amountOfPlants;
   }

   public void setAmountOfPlants(int amountOfPlants) {
      this.amountOfPlants = amountOfPlants;
   }

   @Override
   public String toString() {
      return super.toString() + ", Amount of Plants: " + amountOfPlants;
   }
}

class Alligator extends Reptiles {

   private double maxDepth;

   public Alligator(String name, double weight, int amountOfPlants, double maxDepth) {
      super(name, weight, amountOfPlants);
      this.maxDepth = maxDepth;
   }

   public boolean swimsDeep() {
      return maxDepth > 4;
   }

   @Override
   public void sleep() {
      System.out.println("Alligator sleep: 6-8 hours");
   }

   @Override
   public String toString() {
      return "Alligator -> " + super.toString() + ", Max Depth: " + maxDepth;
   }
}

class Snake extends Reptiles {

   public Snake(String name, double weight, int amountOfPlants) {
      super(name, weight, amountOfPlants);
   }

   @Override
   public void sleep() {
      System.out.println("Snake sleep: at least 16 hours per day");
   }

   @Override
   public String toString() {
      return "Snake -> " + super.toString();
   }
}

public class Main {

   public static void main(String[] args) {
   
      Animal[] animals = new Animal[3];
   
      animals[0] = new Alligator("Alligator_1", 360, 10, 2.7);
      animals[1] = new Snake("Snake_1", 100, 0);
      animals[2] = new Snake("Snake_2", 150, 0);
   
      // Check if alligators swim deep
      for (Animal a : animals) {
         if (a instanceof Alligator) {
            Alligator al = (Alligator) a;
            System.out.println(al.getName() + " swims deep? " + al.swimsDeep());
         }
      }
   
      // Make each animal sleep
      for (Animal a : animals) {
         a.sleep();
      }
   
      // Display all animals information
      for (Animal a : animals) {
         System.out.println(a);
      }
   }
}
