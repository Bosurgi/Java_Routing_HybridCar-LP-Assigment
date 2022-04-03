import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main method where the Knowledge base and the Graph is going to be build. <br>
 * This is going to used to execute the program and the algorithm to find the
 * closes path.
 * 
 * @author A. La Fauci De Leo
 */
public class Driver {

    /**
     * Main method for the class driver
     * @param args argument for the main method
     */
    public static void main(String[] args) {
	
	// A list of nodes with Key Node and value type
	HashMap<Node, String> nodesMap = new HashMap<>();

	// Initialising the variables path and weight
	Integer weight = null;
	List<Node> path = null;

	// Instantiating the Scanner
	Scanner sc = new Scanner(System.in);
	// Instantiating the algorithm class
	DijkstraAlgorithm shortestPath = new DijkstraAlgorithm();

	// Instantiating a car with Fuel 50 and battery 50
	Car hybrid = new Car(30, 20);

	// Instantiating the nodes of the graph

	Node a = new Node("A", "Charge Station");
	Node b = new Node("B", "Gas Station");
	Node c = new Node("C", "Both");
	Node d = new Node("D", "Charge Station");
	Node e = new Node("E", "Gas Station");
	Node f = new Node("F", "Gas Station");
	Node g = new Node("G", "Both");
	Node start = new Node("Start");

	// Adding the nodes to the list
	nodesMap.put(a, "charge station");
	nodesMap.put(b, "gas station");
	nodesMap.put(c, "both");
	nodesMap.put(d, "charge station");
	nodesMap.put(e, "gas station");
	nodesMap.put(f, "gas station");
	nodesMap.put(g, "both");
	
	// Creating lists of Gas Station or Charge station
	List<Node> gasStations = new ArrayList<Node>();
	List<Node> chargeStations = new ArrayList<Node>();
	List<Node> both = new ArrayList<Node>();
	
	for(Map.Entry<Node, String> entry : nodesMap.entrySet()) {
	    
	    if(entry.getValue() == "charge station") {
		chargeStations.add(entry.getKey());
	    }
	    else if(entry.getValue() == "gas station") {
		gasStations.add(entry.getKey());
	    }
	    else {
		both.add(entry.getKey());
	    }
	}
			
	// Instantiating the Edges between nodes
	Edge e1 = new Edge(start, f, 7);
	Edge e2 = new Edge(start, e, 8);
	Edge e3 = new Edge(f, g, 6);
	Edge e4 = new Edge(f, b, 9);
	Edge e5 = new Edge(g, b, 10);
	Edge e6 = new Edge(g, a, 17);
	Edge e7 = new Edge(a, c, 13);
	Edge e8 = new Edge(a, b, 6);
	Edge e9 = new Edge(b, c, 15);
	Edge e10 = new Edge(c, d, 10);
	Edge e11 = new Edge(e, d, 5);
	Edge e12 = new Edge(e, c, 12);

	// Setting the adjacent nodes
	start.addAdjacent(e1);
	start.addAdjacent(e2);
	f.addAdjacent(e3);
	f.addAdjacent(e4);
	g.addAdjacent(e5);
	g.addAdjacent(e6);
	a.addAdjacent(e7);
	a.addAdjacent(e8);
	b.addAdjacent(e9);
	c.addAdjacent(e10);
	e.addAdjacent(e11);
	e.addAdjacent(e12);

	// Calculating the shortest Path from where the car is to the nodes
	shortestPath.calcPath(start);
	
	// Prompting the user
	System.out.println("Select a Node between A and G: ");
	String input = sc.nextLine();
	sc.close();

	// Using the input
	switch (input.toUpperCase()) {

	case "A": {
	    weight = a.getDistance();
	    path = shortestPath.getPath(a);
	    break;
	}
	case "B": {
	    weight = b.getDistance();
	    path = shortestPath.getPath(b);
	    break;
	}

	case "C": {
	    weight = c.getDistance();
	    path = shortestPath.getPath(c);
	    break;
	}

	case "D": {
	    weight = d.getDistance();
	    path = shortestPath.getPath(d);
	    break;
	}

	case "E": {
	    weight = e.getDistance();
	    path = shortestPath.getPath(e);
	    break;
	}

	case "F": {
	    weight = f.getDistance();
	    path = shortestPath.getPath(f);
	    break;
	}

	case "G": {
	    weight = g.getDistance();
	    path = shortestPath.getPath(g);
	    break;
	}

	} // End of Switch

	try {

	    // Displaying the shortest path
	    System.out.println("************************************************************\n");
	    System.out.printf("The shortest Path between %s and %s is distant %d units.\n", path.get(0),
		    path.get(path.size() - 1), weight);
	    System.out.printf("\n\tThe path to follow is: " + path + "\n");
	    System.out.println("\n************************************************************\n");

	    // Car can move through the path.
	    hybrid.move(path);
	    

	     // Checking if the car needs to find the nearest point to refuel or recharge

	    if(hybrid.fuel <= 20 && hybrid.battery <= 20) {
		hybrid.findRoute(both);
	    }
	    
	    else if(hybrid.fuel <= 20) {
		hybrid.findRoute(gasStations);
	    }
	    
	    else if(hybrid.battery <= 20) {
		hybrid.findRoute(chargeStations);
	    }
	    

	} catch (Exception exception) {

	    System.out.println("Error");

	}

    }

} 
