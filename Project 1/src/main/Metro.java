public class Metro extends TransportUnit{

  //Attribute
  private String line;
  
  public Metro(String id, int capacity, String line){
      super(id, capacity); // inherits from TransportUnit
      this.line = line;
    }

  //toString method to display metro details
  public String toString(){
      return super.toString() + "\nLine: " + line;
    }
}
    
  