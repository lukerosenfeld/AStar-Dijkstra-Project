/* A Node class that represents a single node in the class. It has a string for the node's name, 
 * a pointer to the first edge, a variable to keep track of how many edges a node has, a double
 * to keep the distance the node is away from the destination (this is used when running Dijkstra's 
 * algorithm, a pointer to the next Node for when this node is in the NodeList, Path, or Fringe, and
 * a boolean for whether or not the node has been visited (this is also for Dijkstra). 
 * 
 */
package Dijkstra;

public class Node {
  public String name;
  public Edge firstEdge;
  public int numEdges;
  public double distance;
  public Node nextNode;
  public boolean visited;

   //A constructor that initializes all variables.
 public Node(String name) {
  this.name = name;
  firstEdge = null;
  this.distance = 0;
  this.numEdges = 0;
  this.nextNode = null;
  this.visited = false;
 }

 //A method to add an edge. It adds the edge to the beginning of the list of edges.
 public void addEdge(Edge add) {
  if (this.firstEdge == null) {
   this.firstEdge = add;
  } else {
   Edge temp = this.firstEdge;
   this.firstEdge = add;
   this.firstEdge.nextEdge = temp;
  }
 }
 
 //A method to find an edge among all the edges this node is connected to.
 public Edge findEdge(Edge toFind) {
  Edge temp = this.firstEdge;
  Edge found = null;
  while(temp != null) {
   if((temp.A.name.equals(toFind.A.name) && temp.B.name.equals(toFind.B.name)) ||
     (temp.A.name.equals(toFind.B.name) && temp.B.name.equals(toFind.A.name))) {
     found = temp;
     break;
    }
   temp = temp.nextEdge;
  } 
  return found;                                                                                                                                                                    
 }
}