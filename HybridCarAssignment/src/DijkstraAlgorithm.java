import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra algorithm class. This is the class which runs the Dijkstra algorithm <br>
 * to find the shortest path between two Nodes in a Graph.
 * <br>
 * It calculates, among the list of Edges and Nodes, which one is the one with less weight.
 * <br>
 * As well as adding the distance value and adding it to the weight of the list, so that it records the actual distance.
 * <br> It also allows to visit one Node only once to avoid cycles in the Graph.
 * 
 * @author A. La Fauci De Leo
 * Adapted from Mandliya(2020)
 */
public class DijkstraAlgorithm {
    
    /**
     * Method which calculates the shortest path between the specified node based on the adjacent nodes.
     * @param targetNode the node we want to calculate the distance from.
     */
    public void calcPath(Node targetNode) {
	// The first node will have 0 as distance as it is the first and it will be visited because we are currently in it.
	targetNode.setDistance(0);
	targetNode.setExplored(true);
	// Setting the Priority Queues and adding the first node to the list of Priority Queue
	PriorityQueue<Node> pq = new PriorityQueue<Node>();
	pq.add(targetNode);
	
	
	//While loop which runs until the List of priority queue is empty.
	while(pq.isEmpty() == false) {
	    // Taking the element from the List
	    Node currentNode = pq.poll();
	    // Looping through the adjacent nodes belonging to the edge
	    for(Edge edge : currentNode.getAdjacentNodes()) {
		// The next node will be the node where the edge ends
		Node node = edge.getEndNode();
		
		// If node is not explored then add the distance and the current weight to keep track of the actual path weight.
		if(node.isExplored() == false) {
		    Integer totDistance = currentNode.getDistance() + edge.getWeight();
		    
		    // If the updated distance is less than the next node remove the node from the queue.
		    if(totDistance < node.getDistance()) {
			/*
			 * Updating the distance of the node and adding it into
			 * the list of visited nodes.
			 * And adding the visited node to the priority queue
			 */
			pq.remove(node);
			node.setDistance(totDistance);
			node.setPrevious(currentNode);
			pq.add(node);
		    
		    } // End of IF distance
		} // End of IF explored
	    } // End of For loop
	    
	    // Set the current node as explored
	    currentNode.setExplored(true);
	} // End of While loop
    } // End of Method
    
    /**
     * Method to get the List of Nodes visited until the node is null.
     * @param destination the Node to reach
     * @return the path of nodes between the starting point and the destination.
     */
    public List<Node> getPath(Node destination) {
	// Initialising a new List containing the Path
	List<Node> path = new ArrayList<>();
	
	// Looping through the nodes and adding the nodes to the path.
	for(Node node = destination; node != null; node = node.getPrevious()) {
	    path.add(node);
	}
	
	// Reversing the path since it will be on the opposite since we get the Previous node
	Collections.reverse(path);
	return path;
    }

}
