public class Main{
  // main method
  public static void Factor(int n){
  
  // call helper method and pass n then start test
  findFactor(n,n);
  }
  
  // helper method
  private static void findFactor ( int n , int d){
  
  // base case
  if ( d ==0){
   return;
   }
    
  if ( n %d == 0){
   if( d ==1){
     System.out.println(d);
   }else{
     System.out.println(d+ " ," );
   }
  }
  
  findFactor(n, d-1);
 }
 
  
 public static void main ( String [] args){
  
   System.out.println("Output: ");
   Factor(9);
}
}
    
