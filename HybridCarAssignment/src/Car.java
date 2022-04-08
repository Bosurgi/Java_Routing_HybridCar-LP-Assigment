import java.util.List;
import java.util.Scanner;

/**
 * Class representing a Hybrid car which can move and select Fuel or Electric
 * energy to move depending on its autonomy.
 *
 * @author A. La Fauci De Leo
 *
 */
public class Car {

    /**
     * What the car uses to move.
     */
    String currentUsing = "fuel";
    /**
     * The current position of the car which is Start by default.
     */
    Node currentPosition = new Node("Start");
    /**
     * The level of Fuel and battery.
     */
    int fuel, battery;
    /**
     * Boolean flag which indicates if the car can move or not.
     */
    boolean canMove = true;

    /**
     * Method which will set the component the car will use.
     * 
     * @param component the component to be set
     */
    public void setComponent(String component) {
	this.currentUsing = component;
    }

    /**
     * Getter for the current component in use.
     * 
     * @return what the car is using to move
     */
    public String getComponent() {
	return this.currentUsing;
    }

    /**
     * Getter method for the Current position
     * 
     * @return the current position of the car.
     */
    public Node getCurrentPosition() {
	return this.currentPosition;
    }

    /**
     * Setter method which will set the Node were the car is.
     * 
     * @param currentPosition the Node where the car will be.
     */
    public void setCurrentPosition(Node currentPosition) {
	this.currentPosition = currentPosition;
    }

    /**
     * Getter Method for the Battery Level
     * 
     * @return the battery level.
     */
    public int getBattery() {
	return this.battery;
    }

    /**
     * Setter for the battery
     * 
     * @param battery the battery level to set
     */
    public void setBattery(int battery) {
	this.battery = battery;
    }

    /**
     * Getter Method for the Fuel Level
     * 
     * @return the fuel level.
     */
    public int getFuel() {
	return this.fuel;
    }

    /**
     * Setter for the Fuel
     * 
     * @param fuel the Fuel level to set
     */
    public void setFuel(int fuel) {
	this.fuel = fuel;
    }

    /**
     * Method which allows the car to stop and not moving from the current position.
     */
    public void stop() {
	setCurrentPosition(getCurrentPosition());
	System.out.println("\nWarning car can't move.\n");
    }

    /**
     * Method which will check the Autonomy of the car and will determine which type
     * of fuel to use.
     */

    public void checkAutonomy() {

	if (fuel > battery && battery == 0) {

	    setComponent("fuel");
	    System.out.printf("\nCar is using %s\n", getComponent());
	}

	else if (battery > fuel && fuel == 0) {
	    setComponent("battery");
	    System.out.printf("\nCar is using %s\n", getComponent());
	}

	else if (battery <= 0 && fuel <= 0) {
	    canMove = false;
	}

	else if (fuel <= 20) {
	    setComponent("battery");
	    System.out.printf("\nCar is using %s\n", getComponent());
	}

	else if (battery <= 20) {
	    setComponent("fuel");
	    System.out.printf("\nCar is using %s\n", getComponent());
	}
    }

    /**
     * Method which simulates the consumption of Fuel or Battery depending on what
     * the vehicle is using. <br>
     * If it is using Fuel it will reduce fuel by the distance between nodes.
     * 
     */
    public void consumption(Node nextNode) {

	if (this.currentUsing.toLowerCase() == "fuel" && canMove) {
	    setFuel(this.fuel - (nextNode.getDistance() - nextNode.getPrevious().getDistance()));
	} else if (this.currentUsing.toLowerCase() == "battery" && canMove) {
	    setBattery(this.battery - (nextNode.getDistance() - nextNode.getPrevious().getDistance()));
	}
	// Checking if after consumption both are not empty. If they are car can't move.
	if (this.fuel == 0 && this.battery == 0) {
	    canMove = false;
	}
    }

    /**
     * Method which allows the car to move to a node
     * 
     * @param nextNode the node where to move the car
     */
    public void move(Node nextNode) {
	if (canMove) {
	    checkAutonomy();
	    if (currentPosition == nextNode) {
		System.out.printf("Car stays in %s", currentPosition);
	    } else {
		System.out.printf("\nCar moved from %s to %s \n", currentPosition, nextNode);
		setCurrentPosition(nextNode);
		consumption(nextNode);
	    }
	} else
	    stop();
    }

    /**
     * Variation of the Move method. Instead of a single Node it takes a List of
     * nodes. <br>
     * It then loops through the list and moves into each node of the Path.
     * 
     * @param path the list of nodes forming the path.
     */
    public void move(List<Node> path) {
	if (canMove) {
	    for (Node node : path) {
		// This prevents the car to move from the same node where it currently is.
		if (node == path.get(0))
		    continue;
		checkAutonomy();
		// If the current position is different from the node the car will move.

		if (currentPosition == node) {
		    System.out.printf("Car stays in %s", currentPosition);
		}

		else if (currentPosition != node) {

		    System.out.printf("\nCar moved from %s to %s \n", currentPosition, node);
		    setCurrentPosition(node);
		    consumption(node);
		}

	    } // End of For

	} // End of If
	else
	    stop();
    } // End of Method

    /**
     * Method to find the route to refill the tank or battery when level are low.
     * 
     * @param nodeTypes a list of nodes depending on what is needed
     */
    public void findRoute(List<Node> nodeTypes) {
	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
	dijkstra.calcPath(currentPosition);
	System.out.println("\n******* Low Autonomy - Calculating Fastest Route *******\n");

	for (Node node : nodeTypes) {
	    if (node == nodeTypes.get(0))
		continue;
	    if (fuel >= node.getDistance() || battery >= node.getDistance()) {
		move(node);
	    }
	}
    }

    /**
     * Method which will simulate re-charging or re-fueling the car 
     * TODO: Implementing it and test it
     */
    public void refuel() {
	// Initialising the scanner to make it interactive.
	Scanner sc = new Scanner(System.in);
	String answer = sc.nextLine();

	if (fuel <= 10 && currentPosition.type.toLowerCase() == "gas station"
		|| currentPosition.type.toLowerCase() == "both") {
	    System.out.printf("\nDo you want to refuel at %s: (y/n)", currentPosition);
	    if (answer.toLowerCase() == "y" || answer.toLowerCase() == "yes") {
		setFuel(100);
		System.out.println("\nCar Refueled");
	    } else if (answer.toLowerCase() == "n" || answer.toLowerCase() == "no") {
		System.out.println("\nCar NOT refueled");
	    } else {
		System.out.println("\nInput not recognized. Retry.");
		refuel();
	    }

	} // End of IF

	if (battery <= 10 && currentPosition.type.toLowerCase() == "charge station"
		|| currentPosition.type.toLowerCase() == "both") {
	    System.out.printf("\nDo you want to recharge at %s: (y/n)", currentPosition);
	    if (answer.toLowerCase() == "y" || answer.toLowerCase() == "yes") {
		setBattery(100);
		System.out.println("\nCar Recharged");
	    } else if (answer.toLowerCase() == "n" || answer.toLowerCase() == "no") {
		System.out.println("\nCar NOT recharged");
	    } else {
		System.out.println("\nInput not recognized. Retry.");
		refuel();
	    }
	} // End of IF

	// Closing scanner
	sc.close();
    } // End of method

    /**
     * Method which will override the current toString method to print the status of
     * the car in a nicely formatted way
     */
    public String toString() {

	return String.format("\nFuel Level: %d \n Battery Level: %d \n Current Using %s \n Position: %s", this.fuel,
		this.battery, this.currentUsing, this.currentPosition);
    }

    /**
     * Constructor for the Class Car with two parameters, fuel and battery level
     * 
     * @param fuel    the fuel level of the car
     * @param battery the battery level of the car
     */
    public Car(int fuel, int battery) {
	this.fuel = fuel;
	this.battery = battery;

    }

}
