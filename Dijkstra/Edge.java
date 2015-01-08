/* A class called Edge that represents an edge in the graph. It has a node A that is the source
 * which connects to node B. It also has a pointer to the next edge and a variable to keep
 * track of the weight of the edge.
 * 
 */
package Dijkstra;

public class Edge {
 public Node A;
 public Node B;
 public Edge nextEdge;
 public double weight;
  
 //A constructor that initializes all variables.
  public Edge(double weight, Node a, Node b) {
   this.nextEdge = null;
   this.weight = weight;
   this.A = a;
   this.B = b;
  }
}
