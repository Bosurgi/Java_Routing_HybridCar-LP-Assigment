/**
 * Main method where the Knowledge base and the Graph is going to be build.
 * <br> This is going to used to execute the program and the algorithm to find the closes path.
 * 
 * @author A. La Fauci De Leo
 */
public class driver {

    public static void main(String[] args) {
	
	// Instantiating a car with Fuel 50 and battery 50
	Car hybrid = new Car(50, 50);
	
	// Instatiating the nodes of the graph
	Node a = new Node("A");
	Node b = new Node("B");
	Node c = new Node("C");
	Node d = new Node("D");
	Node e = new Node("E");
	Node f = new Node("F");
	Node g = new Node("G");
	Node start = new Node("Start");
	
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
	Edge e11 = new Edge(d, e, 5);
	Edge e12 = new Edge(c, e, 12);
	
	// Making the car move among nodes
	
	hybrid.move(c);
	hybrid.move(d);
	hybrid.move(f);
	
	
	
	
	
	

    }

}
