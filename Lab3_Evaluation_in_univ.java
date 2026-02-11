class Professor{

private int pID;
private String name;
private String specialty;

public Professor( int pID, String name, String specialty){

   this.pID=pID;
   this.name=name;
   this.specialty=specialty;

}

public String toString(){

  return "Professor ID " + pID + " Name " + name + " specialty " +specialty;

}

}


class Department {

private String deptName;
private int numOfProfessors;

Professor[] faculty;

  
public Department ( String deptName , int size ){

  this.deptName=deptName;
  
  // creat array of obj and put size
  faculty = new Professor[size];
  numOfProfessors=0;
}

public void hireProfessor(Professor p){

  // add obj to array simple way
  faculty[numOfProfessors]=p;
  numOfProfessors++;
  
  /* add odj to array and check if it is full or not 
  
  if ( numOfProfessors < size){
    faculty[count]=p;
    count++;
    } else {
     System.out.println(" the array is full");
   }*/
}

public void listProfessors(){
// print each obj
  for( int i = 0 ; i< numOfProfessors ; i++){
  System.out.println(faculty[i]);
  }


}

}


public class UniversityApp {

public static void main ( String[]args){

  Professor p1 = new Professor(1, "pro1" ,"swe");
  Professor p2 = new Professor(2, "pro2", "cs");
  Professor p3 = new Professor(3, "pro3", "it");
  
  Department d1 = new Department("cs",20);
  
  // add professors to the departemnt
  d1.hireProfessor(p1);
  d1.hireProfessor(p2);
  d1.hireProfessor(p3);

  d1.listProfessors();

   
}

}
