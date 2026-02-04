class Department {
  
  private String depName;
  private int totalStudents;
  
public Department ( String depName, int totalStudents){
  this.depName= depName;
  this.totalStudents = totalStudents;
}
  

public String toString (){

  return"department Name"+ depName + "total Students" +totalStudents;
}


public void setDepName (String depName){
  this.depName = depName;
}


public void setTotalStudents (int totalStudents){
  this.totalStudents = totalStudents;
}


public String getDepName(){
  return depName;

} 

public int getTotalStudents(){
  return totalStudents;

} 

}


class College{

  private Department []depList;
  private int numOfDepts;
 
public College (int size){

   depList = new Department [size];
   numOfDepts = 0;
}
 

public void addDept( Department dept){

  // this to check is array full or not by using length of array and number of dempartment
  if( numOfDepts < depList.length){
   depList[numOfDepts] = dept;
   numOfDepts++;
   } else {
     System.out.println("Sorry there is no space to add more department");
   }  
}

public int sumOfStudents(){

  //this method will call getTotalStudents() then full array and sum the total
  int sum = 0;
  for ( int i = 0 ; i < numOfDepts ; i++){
   sum += depList[i].getTotalStudents();
  }
  return sum ;
  }     
  
}


class collegeApp {
public static void main( String args[]){

  // create a college that holds 4 departments
  College CCIS = new College(4);
  
  // create departments
  Department d1 = new Department ("CS",150);
  Department d2 = new Department ("IS",100);
  Department d3 = new Department ("SWE",120);
  

  CCIS.addDept(d1);
  CCIS.addDept(d2);
  CCIS.addDept(d3);

  
  System.out.println(" The sum of all the students studying in ccis= " + CCIS.sumOfStudents());
 
  }
  
  
  
}
