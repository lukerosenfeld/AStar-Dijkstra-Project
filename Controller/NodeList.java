/* A class called NodeList that keeps track of the list of nodes in the graph. It has methods to 
 * add a Node into the list and to find a Node that is in the list.
 * 
 */
package Controller;
import Dijkstra.Node;
public class NodeList {

 Node firstNode;
 int numNodes;
 
 //Constructor that initializes the first node and the number of all nodes.
 NodeList() {
  this.firstNode = null;
  this.numNodes = 0;
 }
 
 //This method takes in a node and adds it always at the front of the list.
 public void add(Node add) {
  if(this.firstNode == null) {
   this.firstNode = add;
  } else {
   Node temp = this.firstNode;
   this.firstNode = add;
   this.firstNode.nextNode = temp;
  }
  this.numNodes++;
 }
 
 //This method takes in a node and does a linear search through the list to find
 //the node and return it. 
 public Node findNode(Node find) {
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
