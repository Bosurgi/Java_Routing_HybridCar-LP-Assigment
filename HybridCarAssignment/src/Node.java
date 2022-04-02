import java.util.List;
import java.util.ArrayList;

/**
 * Class which defines a Node. A point on a map which has a name and it will be used in the Edge class.
 * <br> It also represent a point between edges.
 * 
 * @author A. La Fauci De Leo
 * Adapted from Mandliya(2020)
 */
public class Node implements Comparable<Node> {
    
    /**
     * The name of the Node
     */
    public String name, type;
    /**
     * Indicates if the node was explored or not
     */
    public boolean explored;
    /**
     * A List of Edges collecting the adjacent ones
     */
    public List<Edge> adjacentNodes;
    /**
     * Node which represents the previous node visited
     */
    public Node previous;
    /**
     * The distance of the Node set to max value, tend to infinity
     * because we don't know the distance yet.
     */
    public Integer distance = Integer.MAX_VALUE;
     
    /**
     * Getter for the Name attribute
     * @return the name of the Node
     */
    public String getName() {
	return this.name;
    }
    /**
     * Setter for the Name attribute
     * @param name the name of the node
     */
    public void setName(String name) {
	this.name = name;
    }
    
    /**
     * Getter for the type attribute
     * @return the type of the Node
     */
    public String getType() {
	return this.type;
    }
    /**
     * Setter for the type attribute
     * @param type the type of the node
     */
    public void setType(String type) {
	this.type = type;
    }
    
    /**
     * Method to check whether a node is explored or not
     * @return boolean. True if explored, False if not
     */
    public boolean isExplored() {
        return explored;
    }
    
    /**
     * Setter method for the attribute explored
     * @param explored boolean to indicate whether the node was explored or not
     */
    public void setExplored(boolean explored) {
        this.explored = explored;
    }
    
    /**
     * Getter method to return the list of adjacent nodes
     * @return the list of adjacent nodes
     */
    public List<Edge> getAdjacentNodes() {
        return adjacentNodes;
    }

    /**
     * Setter Method to set the list of current adjacent nodes to the specified one
     * @param adjacentNodes the list of adjacent nodes.
     */
    public void setAdjacentNodes(List<Edge> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    /**
     * Method to add an edge to the list of adjacent nodes
     * @param edge the Edge to add to the list
     */
    public void addAdjacent(Edge edge) {
	this.adjacentNodes.add(edge);
    }
    
    /**
     * Getter method for the attribute previous node
     * @return the node visited previously
     */
    public Node getPrevious() {
        return previous;
    }
    
    /**
     * Setter method for the attribute previous node
     * @param previous the node visited previously
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Getter for the attribute Distance
     * @return the distance from the Node
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * Setter method for the attribute distance
     * @param distance the distance between the node
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Overriding the compareTo method from the {@link Comparable#compareTo(Object)} interface
     * <br>
     * This method compares the distances between the {@link #Node(String)}
     */
    @Override
    public int compareTo(Node o) {
	/* 
	 * It returns the value 0 if x == y;
	 * a value less than 0 if x < y; 
	 * and a value greater than 0 if x > y
	 */
	return Integer.compare(this.distance, o.getDistance());
    }
    
    /**
     * Overriding the toString method to return the name of the node
     * in a nicely formatted way
     */
    public String toString() {
	return this.name;
    }
    
    /**
     * Constructor of the Node class which creates an instance of
     * node taking Name as a parameter and creating a new list for storing
     * adjacent nodes.
     * 
     * @param name the name of the Node
     */
    public Node(String name) {
	this.name = name;
	this.adjacentNodes = new ArrayList<>();
    
    } // End of Constructor
    
} // End of class
