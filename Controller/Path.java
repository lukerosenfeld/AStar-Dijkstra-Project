/* This is a class called path which is used when running the A* algorithm to add the nodes that
 * are part of the shortest path to get from the source to the destination. It has two variables:
 * one is a node that is a pointer to the first node in the path and an integer that keeps track
 * of the number of nodes in the list.
 * 
 */
package Controller;

import Dijkstra.Node;

public class Path {
 Node first;
 int total;
 
 //A constructor that initializes all variables.
 Path() {
  this.first = null;
  this.total = 0;
 }

 //A method that takes in a node and adds it to the list at the end.
 public void add(Node add) {
  Node newNode = new Node(add.name);
  if (this.first == null) {
   this.first = newNode;
  } else {
   Node temp = this.first;
   while(temp.nextNode != null) {
    temp = temp.nextNode;
   }
   temp.nextNode = newNode;
  }
  this.total++;
 }
 
}
