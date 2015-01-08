/*A class called driver that has the main method of the program and runs it. The code reads
 * through a text file which represents the graph, initializes it by adding each node to the
 * graph, prompts the user for the starting point and destination, and processes Dijkstra's 
 * algorithm and the A* algorithm in order to find the shortest path from the start to the end.
 * It then prints out the path, node by node, to show the user the quickest path he or she 
 * should take to reach the destination in the most efficient manner.
 * 
 */

package Controller;

import java.util.*;
import java.io.*;

import Dijkstra.Fringe;
import Dijkstra.Node;
import Dijkstra.Edge;

public class driver {
 
 private static Scanner console;

 //Main method of the program. All other methods are called from here.
 public static void main(String[] args)
  throws FileNotFoundException {
  Fringe fringe = new Fringe();
  console = new Scanner(System.in);
  Scanner input = new Scanner(new File("londontext.txt")); //This must be modified if the user wishes to look at their own graph.
  NodeList list = new NodeList();
  Path path = new Path();
  initialization(input, list);
  String source = "";
  System.out.println("Welcome! We are here to help you find the fastest route to your destination!");
  while (!source.equals("quit")) {
  System.out.println("Tell us, where are you starting from? (or type \"quit\" to quit)");
  System.out.println(">");
  source = console.nextLine();
  if (source.equals("quit")) break;
  System.out.println("Where are you headed?");
  System.out.println(">");
  String destination = console.nextLine();
  System.out.println("Wonderful! We are finding the best route for you now...");
  Node find = new Node(destination);
  Node end = list.findNode(find);
  dijkstra(fringe, end, list);
  Node beginning = new Node(source);
  Node start = list.findNode(beginning);
  //printGraph(list);
  aStar(start, end, path);
  reset(fringe, path, start, end, list);

  }
  System.out.println("Bye-bye!");
 }
 
 //This method creates and adds all nodes in the list to the graph, but first checks to 
 //see if they are already added. It does this by reading through the text file "input"
 //and processes each item in the file. This includes the edges, names, weights, and number
 //of edges each node is connected to. 
 public static void initialization(Scanner input, NodeList list) 
  throws FileNotFoundException {
  
  while(input.hasNext()) {
   String name = input.next();
   Node newNode = new Node(name);
   Node added = list.findNode(newNode);
   if(added == null) { 
    list.add(newNode);
    added = newNode;
   } 
   added.numEdges = input.nextInt();
   for(int i = 0; i < added.numEdges; i++) {
    String edge = input.next();
    double weight = input.nextDouble();
    Node temp = new Node(edge);
    Node connected = list.findNode(temp);
    if(connected == null) {
     list.add(temp);
     connected = temp;
    }
    Edge newEdge = new Edge(weight, added, connected);
    added.addEdge(newEdge);
   }
   if(input.hasNext()) {
    input.nextLine();
   }
  }
 }
 
 //This is the method that represents the dijkstra algorithm. It adds and removes nodes from
 //the fringe it creates, and checks whether or not the number of nodes it has added is equal
 //to the number of nodes in the list (to see if it is done yet).
 public static void dijkstra(Fringe fringe, Node start, NodeList list) {
  start.visited = true;
  start.distance = 0;
  int num = 1;
  Node temp = start;
  Edge current = null;
  while(num != list.numNodes) {
   current = temp.firstEdge;   
   Node n = null;
   for(int i = 0; i < temp.numEdges; i++) {
    if(!current.B.visited) {
     n = fringe.find(current.B);
     if(n == null) {
      fringe.add(current.B);
      n = current.B;
     } else {
      n = list.findNode(n);
     }
     if(n.distance != 0) {
      if((current.weight + current.A.distance) < n.distance) {
       n.distance = current.weight + current.A.distance;
      }
     } else {
      n.distance = current.weight + current.A.distance;
     }
    }
    current = current.nextEdge;
   }
   temp = findShortest(fringe, list);
  // printFringe(fringe, list);
   if(temp != null) {
    temp.visited = true;
    fringe.remove(temp);
    num++;
   }
  }
  start.distance = 0;
 }
 
 //This method finds and returns the node that is the shortest distance away from the first 
 //node in the list. 
 public static Node findShortest(Fringe fringe, NodeList list) {
  Node temp = fringe.firstNode;
  if(temp == null) {
   return null;
  }
  Node first = list.findNode(temp);
  double min = first.distance;
  String name = first.name;
  Node returnNode = null;
  Node want = null;
  while(temp != null) {
   want = list.findNode(temp);
   if(want.distance < min) {
    min = want.distance;
    name = want.name;
   }
   temp = temp.nextNode;
  }
  Node newNode = new Node(name);
  returnNode = list.findNode(newNode);
  return returnNode;
 }
 
 //This method counts and returns the number of nodes visited in the list. 
 public static int numVisited(NodeList list) {
  Node temp = list.firstNode;
  int count = 0;
  while(temp != null) {
   if(temp.visited) {
    count++;
   }
   temp = temp.nextNode;
  }
   return count;
 }
 
 //This method represents the A* algorithm. It calls the findNext method and adds each node
 //to the path, so the correct path can be printed out to the user. 
 public static void aStar(Node source, Node destination, Path path) {
  Node temp = source;
  while(!(temp.name.equals(destination.name))){
   path.add(temp);
   temp = findNext(temp);
  }
  path.add(temp);
  printPath(path);
 }
 
 //The findNext method finds the node that is connected to the start node that is the best
 //choice for the user to go to.
 public static Node findNext(Node start) {
  Edge current = start.firstEdge;
  double min = current.weight + current.B.distance;
  Node returnNode = current.B;
  for(int i = 0; i < start.numEdges; i++) {
   if((current.weight + current.B.distance) < min) {
    min = current.weight + current.B.distance;
    returnNode = current.B;
   }
   current = current.nextEdge;
  }
  return returnNode;
 }
 
 //Prints the path of the shortest distance. 
 public static void printPath(Path path) {
   Node temp = path.first;
   while(temp != null) {
    System.out.println(temp.name);
    temp = temp.nextNode;
   }
  }
 
 // This method resets the path, start and end nodes, and the list, so the user can ask for 
 // many different best paths in the graph without having to re-run the program. 
 public static void reset(Fringe fringe, Path path, Node start, Node end, NodeList list) {
  Node temp = list.firstNode;
  while (temp != null) {
   temp.visited = false;
   temp.distance = 0;
   temp = temp.nextNode;
  }
  fringe.firstNode = null;
  path.total = 0;
  path.first = null;
 }
}
