public class Car {

    String currentUsing = "Fuel";
    Node currentPosition = new Node("Start");
    int fuel, battery;

    /**
     * Method which will set the component the car will need to use
     */
    public void setComponent(String component) {
	this.currentUsing = component;
	System.out.printf("\nThe car is using now %s\n", component);
    }

    /**
     * Getter for the current component in use
     * 
     * @return
     */
    public String getComponent() {
	return this.currentUsing;
    }

    public Node getCurrentPosition() {
	return this.currentPosition;
    }
    
    public void setCurrentPosition(String currentPosition) {
	this.currentPosition.name = currentPosition;
    }

    /*
     * Getters and Setters for Battery and Fuel
     */
    public int getBattery() {
	return this.battery;
    }

    public void setBattery(int battery) {
	this.battery = battery;
    }

    public int getFuel() {
	return this.fuel;
    }

    public void setFuel(int fuel) {
	this.fuel = fuel;
    }

    /**
     * Method used to change mode of the engine. Using Battery when Fuel is low and
     * vice versa. When both are low they are using Fuel to charge the Battery just
     * to get to the nearest Station available.
     */
    public void changeMode() {

	if (this.battery <= 30 && this.fuel <= 30) {
	    System.out.println("Low Fuel and Battery. Using Fuel to charge battery and get to nearest Station.");
	    currentUsing = "Fuel";
	}

	else if (this.battery <= 30) {
	    System.out.printf("Low Battery Level. Battery: %d", this.battery);
	    setComponent("Fuel");
	} else if (this.fuel <= 30) {
	    System.out.printf("Low Fuel Level. Fuel: %d", this.fuel);
	    setComponent("Battery");
	}

    }

    /**
     * Method used to Stop the Car signalling that the car can't move
     */
    public void stop() {
	System.out.println("\nCar can't move. Not enough Fuel or Battery\n");
    }

    /**
     * Method which is used to determine if a car can move or not. If the car has no
     * battery or fuel it will call the Stop method to indicate the car cannot move.
     * If not it will change mode depending on the levels of the Battery and Fuel
     * 
     * @return false if it cannot move, true if there is enough energy or fuel to
     *         move
     */
    public boolean canMove() {
	if (this.battery == 0 && this.fuel == 0) {
	    stop();
	    return false;
	} else { //TODO: Refine this so it doesn't print everytime it moves
	    changeMode();
	    return true;
	}
    }

    /**
     * Method to make the car move from the Current position to the next Position It
     * will check if the car can move first, if it can it will proceed. If not
     * canMove method will handle the situation where the car cannot move.
     * 
     * @param nextPosition the next position where the car will move.
     */
    public void move(Node nextPosition) {
	if (canMove()) {
	    System.out.printf("\nCar is moving from %s to %s\n", this.currentPosition, nextPosition.getName());
	    // Keeping track of the car's position.
	    this.currentPosition = nextPosition;
	    consumption();
	}
    }
    
    /**
     * Method which simulates the consumption of Fuel or Battery depending on what the veichle is using.
     */
    public void consumption() {
	if(canMove()) {
	    if(this.currentUsing == "Fuel") {
		setFuel(this.fuel - 10);
	    }
	    else if(this.currentUsing == "Battery" ) {
		setBattery(this.battery - 10);
	    }
	}
    }

    /*
     * TODO: Implement this method with new code
     * 
    public String whereTo(Car car, Edge vertices) {
	if (vertices.type == "gas station") {
	    car.setComponent("fuel");
	    car.move(vertices.name);
	    return vertices.name;
	} else if (vertices.type == "charge station") {
	    car.setComponent("fuel");
	    car.move(vertices.name);
	    return vertices.name;
	} else {
	    car.move(vertices.name);
	    return vertices.name;
	}

    }
    */

    /**
     * Method which will override the current toString method
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
