import java.util.Scanner;

/**
 * Class representing a Hybrid car which can move and select Fuel or Electric energy to move depending on its autonomy.
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
     * @return the current position of the car.
     */
    public Node getCurrentPosition() {
	return this.currentPosition;
    }

    /**
     * Setter method which will set the Node were the car is.
     * @param currentPosition the Node where the car will be.
     */
    public void setCurrentPosition(Node currentPosition) {
	this.currentPosition = currentPosition;
    }

    /**
     * Getter Method for the Battery Level
     * @return the battery level.
     */
    public int getBattery() {
	return this.battery;
    }

    /**
     * Setter for the battery
     * @param battery the battery level to set
     */
    public void setBattery(int battery) {
	this.battery = battery;
    }

    /**
     * Getter Method for the Fuel Level
     * @return the fuel level.
     */
    public int getFuel() {
	return this.fuel;
    }

    /**
     * Setter for the Fuel
     * @param battery the Fuel level to set
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
	    System.out.printf("\nCar is moving with %s\n", getComponent());
	}

	else if (battery > fuel && fuel == 0) {
	    setComponent("battery");
	    System.out.printf("\nCar is moving with %s\n", getComponent());
	}

	else if (battery <= 0 && fuel <= 0) {
	    canMove = false;
	}

	else if (fuel <= 20) {
	    setComponent("battery");
	    System.out.printf("\nCar is moving with %s\n", getComponent());
	}

	else if (battery <= 20) {
	    setComponent("fuel");
	    System.out.printf("\nCar is moving with %s\n", getComponent());
	}
    }

    /**
     * Method which simulates the consumption of Fuel or Battery depending on what
     * the vehicle is using. <br>
     * If it is using Fuel it will reduce fuel by 10 units by default between nodes.
     * TODO: Modifying it with the actual distance between nodes. TODO: fix bug for
     * moving and make the car stopping if both levels are 0.
     */
    public void consumption() {

	if (this.currentUsing.toLowerCase() == "fuel" && canMove) {
	    setFuel(this.fuel - 10);
	} else if (this.currentUsing.toLowerCase() == "battery" && canMove) {
	    setBattery(this.battery - 10);
	}
	// Checking if after consumption both are not empty. If they are car can't move.
	if(this.fuel == 0 && this.battery == 0) {
	    canMove = false;
	}
    }

    public void move(Node nextNode) {
	if (canMove) {
	    checkAutonomy();
	    System.out.printf("\nCar moved from %s to %s \n", currentPosition, nextNode);
	    setCurrentPosition(nextNode);
	    consumption();
	}
	else stop();

    }

    /**
     * Method which will override the current toString method to print the status of the car in a nicely formatted way
     */
    public String toString() {

	return String.format("\nFuel Level: %d \n Battery Level: %d \n Current Using %s \n Position: %s", this.fuel,
		this.battery, this.currentUsing, this.currentPosition);
    }

    /*
     * Constructor for the Class Car with two parameters, fuel and battery level
     */
    public Car(int fuel, int battery) {
	this.fuel = fuel;
	this.battery = battery;

    }

}
