/* This class is the Fringe class used for running Dijkstra's algorithm. It is a temporary storage
 * spot for nodes that have been touched but whose distances have not been finalized as the 
 * algorithm is running. It has one variable which is simply a pointer to the first node. 
 * 
 */
package Dijkstra;

public class Fringe {
 public Node firstNode;
 
 //A constructor that initializes the first node.
 public Fringe() {
  this.firstNode = null;
 }
 
 //A method that takes in a Node and adds it to the front of the list.
 public void add(Node add) {
  Node newNode = new Node(add.name);
  if (this.firstNode == null) {
   this.firstNode = newNode;
  } else {
   Node temp = this.firstNode;
   this.firstNode = newNode;
   this.firstNode.nextNode = temp;
  }
 }
 
 //A method that takes in a node and removes it from the list. 
 public void remove(Node remove) {
  Node previous = null;
  Node current = this.firstNode;
  while(current != null) {
   if(current.name.equals(remove.name)) {
    if(previous == null) {
     this.firstNode = this.firstNode.nextNode;
     break;
    } else {
     previous.nextNode = current.nextNode;
     break;
    }
   }
   previous = current;
   current = current.nextNode;
  }
 }
 
 //A method that takes in a node in order to find it in the list and return it. 
 public Node find(Node find) {
  Node temp = this.firstNode;
  Node returnNode = null;
  while(temp != null) {
   if(temp.name.equals(find.name)) {
    returnNode = temp;
    break;
   }
   temp = temp.nextNode;
  }
  return returnNode;
 }
}
