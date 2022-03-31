/**
 * Class Edge which indicates the distance between two Nodes. <br>
 * An Edge has a weight, a Node from where it originates and the Nodes where it ends.
 * <br> In other terms an Edge is a Path between two nodes.
 * 
 * @author A. La Fauci De Leo
 * Adapted from Mandliya(2020)
 */

public class Edge {
    
    /**
     * The current node where the edge originates and where it ends.
     */
    public Node currentNode, endNode;
    
    /**
     * Weight of the Edge as an Integer
     */
    public Integer weight;

    /**
     * Getter method for the current node.
     * @return the currentNode where the edge originates
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * Setter method to set the current node to the specified one.
     * @param currentNode the currentNode to set
     */
    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    /**
     * Getter method for the Node where the edge ends.
     * @return the endNode the Node where the edge ends
     */
    public Node getEndNode() {
        return endNode;
    }
    
    /**
     * Setter method for the Node where the edge ends
     * @param endNode the endNode to set
     */
    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }
    
    /**
     * Getter method for the Weight of the edge.
     * @return the weight of the Edge
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Setter method for the Weight attribute
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    /**
     * Constructor for the Edge. Used to instantiate the edges and build the Graph.
     * @param firstNode The Node where the Edge starts
     * @param endNode the Node where the edge ends
     * @param weight the weight between the two nodes
     */
    public Edge(Node firstNode, Node endNode, Integer weight) {
	
	this.currentNode = firstNode;
	this.endNode = endNode;
	this.weight = weight;
    
    } // End of Constructor
    

} // End of Class
