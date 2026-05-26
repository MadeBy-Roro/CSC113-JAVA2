class node
{
  public char data;
  public node next ;
  public node(char d){data = d;}
}
  class linkedlist
  {    
   node head; // head of list    
  // Method to insert a new node at front
   public void insertAtfront(char d) { 
    // Create a new node with given data 
     node new_node = new node(d); 
     new_node.next=head;
     head=new_node; }
  
  // Method to print the LinkedList. 
  
   public void printList() 
   { 
   if(head == null) return;
     System.out.println("Head of list: "+ head.data); 
     System.out.println("list elements");
     node current = head ;
     String output="";
     
     char data;
     while (current != null) { 
      //data=head.data ;
      //System.out.print(" "+data); 
      // Go to next node 
      output = current.data+""+output;
      current = current.next;
       } 
      System.out.print(output);
   } 
  }

  class test {
  
   public static void main(String[] args) {
     linkedlist l = new linkedlist(); 
    //insert elements A to E from front of list
     l.insertAtfront('A'); 
     l.insertAtfront('B'); 
     l.insertAtfront('C'); 
     l.insertAtfront('D'); 
     l.insertAtfront('E'); 
       // Print the LinkedList
     l.printList();
   }
  }
