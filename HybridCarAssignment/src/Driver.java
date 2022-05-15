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
     * 
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

	// Instantiating a car with Fuel 30 and battery 20
	Car hybrid = new Car(30, 20);

	// Instantiating the nodes of the graph
	List<Node> allNodes = new ArrayList<Node>();

	Node a = new Node("A", "Charge Station");
	Node b = new Node("B", "Gas Station");
	Node c = new Node("C", "Both");
	Node d = new Node("D", "Charge Station");
	Node e = new Node("E", "Gas Station");
	Node f = new Node("F", "Gas Station");
	Node g = new Node("G", "Both");
	Node start = new Node("Start");

	// List for all nodes present in the Knowledge base
	allNodes.add(a);
	allNodes.add(b);
	allNodes.add(c);
	allNodes.add(d);
	allNodes.add(e);
	allNodes.add(f);
	allNodes.add(g);
	allNodes.add(start);

	// Setting the car's current position to Start
	hybrid.setCurrentPosition(start);

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

	for (Map.Entry<Node, String> entry : nodesMap.entrySet()) {

	    if (entry.getValue() == "charge station") {
		chargeStations.add(entry.getKey());
	    } else if (entry.getValue() == "gas station") {
		gasStations.add(entry.getKey());
	    } else {
		both.add(entry.getKey());
	    }
	}

	// Setting the adjacent nodes
	start.addAdjacentNode(f, 7);
	start.addAdjacentNode(e, 8);
	f.addAdjacentNode(g, 6);
	f.addAdjacentNode(b, 9);
	g.addAdjacentNode(f, 6);
	g.addAdjacentNode(b, 10);
	g.addAdjacentNode(a, 17);
	a.addAdjacentNode(b, 6);
	a.addAdjacentNode(c, 13);
	a.addAdjacentNode(g, 17);
	b.addAdjacentNode(a, 6);
	b.addAdjacentNode(g, 10);
	b.addAdjacentNode(f, 9);
	b.addAdjacentNode(c, 15);
	c.addAdjacentNode(a, 13);
	c.addAdjacentNode(b, 15);
	c.addAdjacentNode(d, 10);
	c.addAdjacentNode(e, 12);
	d.addAdjacentNode(e, 5);
	d.addAdjacentNode(c, 10);
	e.addAdjacentNode(d, 5);
	e.addAdjacentNode(c, 12);

	// Calculating the shortest Path from where the car is to the nodes
	shortestPath.calcPath(start);

	// Prompting the user
	System.out.println("Select a Node between A and G: ");
	String input = sc.nextLine();

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
	    System.out.println("Battery: " + hybrid.battery);
	    System.out.println("Fuel: " + hybrid.fuel);



	    // Resetting all nodes distances to calculate them again from where the car is
	    for (Node node : allNodes) {
		node.setDistance(Integer.MAX_VALUE);
		node.explored = false;
	    }
	    
	    // Checking if the car needs to find the nearest point to refuel or re-charge
	    if (hybrid.fuel <= 20 && hybrid.battery <= 20) {
		hybrid.findRoute(both);
	    }

	    else if (hybrid.fuel <= 20) {
		hybrid.findRoute(gasStations);
	    }

	    else if (hybrid.battery <= 20) {
		hybrid.findRoute(chargeStations);
	    }

	} catch (Exception exception) {

	    // In case of errors it will print what exception was
	    System.out.println("Exception" + exception.getMessage() + exception.getStackTrace());

	}
	// Closing scanner
	sc.close();
    }

}
