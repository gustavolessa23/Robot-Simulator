/**
 * This class contains contructor for the robots and methods needed to calculate it's properties, make it move, and draw it.
 *  */
import processing.core.PApplet;

public class Robot{
	int colour;
	int colour1;
	int colour2;
	int colour3;
	String name;
	float robotWidth;
	float robotHeight;
	float x1;
	float y1;
	float x2;
	float y2;
	float x3;
	float y3;
	int direction; //1 is to the right, 2 down, 3 to the left and 4 up.
	int speed;
	float centroidX;
	float centroidY;
	PApplet parent;
	int originalSpeed;
	int hitFrameCount; //frame count at the moment before hitting any boundary.
	Boolean hit = false; //used for randomWalk(), stores if the robot is hitting something.
	int k = 0; //counter for randomWalk() method.
	
	/**
	 * Constructor that takes name, colour, coordinates and speed as parameters.
	 * @param PApplet, name, colour1, colour2, colour3, x1, y1, x2, y2, x3, y3, speed
	 */
	public Robot(PApplet parent, String name, int colour1, int colour2, int colour3, float x1, float y1, float x2, float y2, float x3, float y3, int speed){
		this.parent = parent;
		this.name = name;
		this.colour1 = colour1;
		this.colour2 = colour2;
		this.colour3 = colour3;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.speed = speed;
		determineDirection();
		calculateWidth();
		calculateHeight();
	}

	
	/**
	 * Determines the current robot's direction by comparing its x's and y's coordinates.
	 */
	public void determineDirection(){
		if (x1<x3 && x1>x2){
			if(y3>y1 && y2>y1){
				direction = 4;
			}else{
			direction = 2;
			}
		} else if (x1<x3 && x1<x2){
			direction = 3;
		} else if (x1>x3 && x1<x2){
			if(y3<y1 && y2>y1){
			direction = 2;
			}else{
			direction = 4;
			}
		} else if (x1>x3 && x1>x2){
			direction = 1;
		} else {
			System.out.println("Error while determining direction!");
		}
	}

	/**
	 * Calculates robot's approximate width using two of its coordinates
	 */
	public void calculateWidth(){
		switch (direction){
				case 1:
				case 3:
					robotWidth = y3-y2;
					break;
				case 2:
				case 4:
					robotWidth = x3-x2;
					break;
		}
		if (robotWidth < 0){
			robotWidth *= -1;
		}
	}

	/**
	 * Calculates robot's approximate height using two of its coordinates
	 */
	public void calculateHeight(){	
		switch (direction){
				case 1:
				case 3:
					robotHeight = x1-((x2+x3)/2);
					break;
				case 2:
				case 4:
					robotHeight = y1-((y2+y3)/2);
					break;
		}	
		if (robotHeight < 0){
			robotHeight *= -1;
		}
	}

	/**
	 * Calculates and prints the robot's (triangle) centroid, using its vertices coordinates.
	 */
	public void printCentroid(){
		centroidX = ((x1+x2+x3)/3);
		centroidY = ((y1+y2+y3)/3);
		System.out.println("\nCentroid of "+name+" = ("+centroidX+", "+centroidY+").");
	}
	
	/**
	 * Draws the triangle representing the robot and the circle pointing to the front.
	 */
	public void drawRobot(){
		parent.fill(colour1, colour2, colour3);
 		parent.triangle(x1, y1, x2, y2, x3, y3);
 		parent.ellipseMode(parent.CENTER);
		parent.ellipse(x1, y1, 4, 4);	
	}

	/**
	 * Moves the robot forward considering its current direction if it's not hitting anything.
	 * If it reaches the screen boundaries, it stops by not entering the second if statement.
	 */
	public void moveForward(){
		if(hit == false){
			if((direction==1&&((x1+speed)<=parent.width))||(direction==2&&((y1+speed)<= parent.height))||(direction==3&&(x1-speed)>=0)||(direction==4&&((y1-speed)>=0))){
				switch (direction){
					case 1:
						x1 += speed;
						x2 += speed;
						x3 += speed;
						break;
					case 2:
						y1 += speed;
						y2 += speed;
						y3 += speed;
						break;
					case 3:
						x1 -= speed;
						x2 -= speed;
						x3 -= speed;
						break;
					case 4:
						y1 -= speed;
						y2 -= speed;
						y3 -= speed;
						break;
				}
			}
		}
	}

	/**
	 * Moves the robot forward at chosen speed, considering its current direction if it's not hitting anything.
	 * If it reaches the screen boundaries, it stops by not entering the second if statement.
	 * @param
	 */
	public void moveForward(int speed){
		this.speed = speed;
		if(hit == false){
			if((direction==1&&((x1+speed)<=parent.width))||(direction==2&&((y1+speed)<= parent.height))||(direction==3&&(x1-speed)>=0)||(direction==4&&((y1-speed)>=0))){
				switch (direction){
					case 1:
						x1 += speed;
						x2 += speed;
						x3 += speed;
						break;
					case 2:
						y1 += speed;
						y2 += speed;
						y3 += speed;
						break;
					case 3:
						x1 -= speed;
						x2 -= speed;
						x3 -= speed;
						break;
					case 4:
						y1 -= speed;
						y2 -= speed;
						y3 -= speed;
						break;
				}
			}
		}
	}

	/**
	 * Moves the robot backwards until it reaches a screen boundary.
	 */
	public void moveBackwards(){
		switch (direction){
			case 1:
				x1 -= speed;
				x2 -= speed;
				x3 -= speed;
				break;
			case 2:
				y1 -= speed;
				y2 -= speed;
				y3 -= speed;
				break;
			case 3:
				x1 += speed;
				x2 += speed;
				x3 += speed;
				break;
			case 4:
				y1 += speed;
				y2 += speed;
				y3 += speed;
				break;
		}
	}
	
	/**
	 * Moves the robot backwards at chosen speed until it reaches a screen boundary.
	 * @param speed
	 */
	public void moveBackwards(int speed){
		this.speed = speed;
		switch (direction){
			case 1:
				x1 -= speed;
				x2 -= speed;
				x3 -= speed;
				break;
			case 2:
				y1 -= speed;
				y2 -= speed;
				y3 -= speed;
				break;
			case 3:
				x1 += speed;
				x2 += speed;
				x3 += speed;
				break;
			case 4:
				y1 += speed;
				y2 += speed;
				y3 += speed;
				break;
		}
	}

 	/**
	 * Turns the robot 90 degrees counterclockwise by performing the following tasks:
	 * Translation of the rotation point to the origin (0, 0).
	 * Translation of all points acconding the same translation rate.
	 * Rotation of all points 90° counterclockwise about the origin.
	 * Translation of all points using the inversed translation rate.
	 */
  	public void turnLeft(){
  		//Store original coordinates.
  		float tempX1 = x1;
  		float tempY1 = y1;
   		float tempX2 = x2;
  		float tempY2 = y2;  		
  		float tempX3 = x3;
  		float tempY3 = y3;

  		//Calculate translation of the centroid of the triangle to the origin.
  		centroidX = ((x1+x2+x3)/3);
		centroidY = ((y1+y2+y3)/3);
  		float xTranslation = 0 - centroidX;
		float yTranslation = 0 - centroidY;

		//Translate all points by the translation calculated.
		float translatedX1 = tempX1 + xTranslation;
		float translatedY1 = tempY1 + yTranslation;
		float translatedX2 = tempX2 + xTranslation;
		float translatedY2 = tempY2 + yTranslation;
		float translatedX3 = tempX3 + xTranslation;
		float translatedY3 = tempY3 + yTranslation;

  		//Rotate all points 90 degrees counterclockwise, (x, y) -->  (y, -x).
		float rotatedX1 = translatedY1;
		float rotatedY1 = -translatedX1;
		float rotatedX2 = translatedY2;
		float rotatedY2 = -translatedX2;
		float rotatedX3 = translatedY3;
		float rotatedY3 = -translatedX3;
		
  		//Translate all points back.
		x1 = rotatedX1 - xTranslation;
		y1 = rotatedY1 - yTranslation;
		x2 = rotatedX2 - xTranslation;
		y2 = rotatedY2 - yTranslation;
		x3 = rotatedX3 - xTranslation;
		y3 = rotatedY3 - yTranslation;

		//Change direction variable and adjust it between 0 and 4.
		direction -=1;
		if (direction == 0){
			direction = 4;
		} 	
 	}

 	/**
	 * Turns the robot 90 degrees clockwise by performing the following tasks:
	 * Translation of the rotation point to the origin (0, 0).
	 * Translation of all points acconding the same translation rate.
	 * Rotation of all points 90° clockwise about the origin.
	 * Translation of all points using the inversed translation rate.
	 */
 	public void turnRight(){
  		//Store original coordinates.
  		float tempX1 = x1;
  		float tempY1 = y1;
   		float tempX2 = x2;
  		float tempY2 = y2;  		
  		float tempX3 = x3;
  		float tempY3 = y3;

  		//Calculate translation of the centroid of the triangle to the origin.
  		centroidX = ((x1+x2+x3)/3);
		centroidY = ((y1+y2+y3)/3);
  		float xTranslation = 0 - centroidX;
		float yTranslation = 0 - centroidY;

		//Translate all points by the translation calculated.
		float translatedX1 = tempX1 + xTranslation;
		float translatedY1 = tempY1 + yTranslation;
		float translatedX2 = tempX2 + xTranslation;
		float translatedY2 = tempY2 + yTranslation;
		float translatedX3 = tempX3 + xTranslation;
		float translatedY3 = tempY3 + yTranslation;

  		//Rotate all points 90 degrees clockwise, (x, y) -->  (-y, x).
		float rotatedX1 = -translatedY1;
		float rotatedY1 = translatedX1;
		float rotatedX2 = -translatedY2;
		float rotatedY2 = translatedX2;
		float rotatedX3 = -translatedY3;
		float rotatedY3 = translatedX3;
		
  		//Translate all points back.
		x1 = rotatedX1 - xTranslation;
		y1 = rotatedY1 - yTranslation;
		x2 = rotatedX2 - xTranslation;
		y2 = rotatedY2 - yTranslation;
		x3 = rotatedX3 - xTranslation;
		y3 = rotatedY3 - yTranslation;

		//Change direction variable and adjust it between 0 and 4.
		
		if (direction == 4){
			direction = 1;
		} else {
			direction +=1;
		}
 	}

	/**
	 * Stops the robot by setting his speed to 0 (zero).
	 * Stores its old speed for further use.
	 */
	public void stop(){
		originalSpeed = speed;
		speed = 0;
	}
 	
	/**
	 * Corrects out of bounds coordinates by adjusting the whole triangle back to inside screen.
	 * It uses while loops to check each boundary.
	 */
	public void fixPosition(){
		float diff; //adjustment needed for all coordinates.		
		while(x2>=parent.width || x3>=parent.width){
			if (x2>=x3){
				diff = x2-parent.width+1; 
			} else {
				diff = x3-parent.width+1;
			}
			x1 -= diff;
			x2 -= diff;
			x3 -= diff;
		} 

		while(x2<=0 || x3<=0){
			if (x2<=x3){
				diff = 1-x2; 
			} else {
				diff = 1-x3;
			}
			x1 += diff;
			x2 += diff;
			x3 += diff;
		}

		while(y3>parent.height || y2>parent.height){
			if (y3>=y2){
				diff = y3-parent.height+1;
			} else {
				diff = y2-parent.height+1;
			}
			y1 -= diff;
			y2 -= diff;
			y3 -= diff;
		}

		while(y3<=0 || y2 <=0){
			if (y3<=y2){
				diff = 1-y3;
			} else {
				diff = 1-y2;
			}
			y1 += diff;
			y2 += diff;
			y3 += diff;
		}
	}

	/**
	 * Prints out the RGB code of robot's current color.
	 */
 	public void displayRobotColour(){
 		System.out.println(name+"'s RGB colour is: (" + colour1 + ", " + colour2 + ", " + colour3 + ").");
 	}
	
	/**
	 * Changes robot's colour using RGB values.
	 * @param c1 = first number of RGB (0-255).
	 * @param c2 = second number of RGB (0-255).
	 * @param c3 = third number of RGB (0-255).
	 */
 	public void changeRobotColour(int c1, int c2, int c3){
 		colour1 = c1;
 		colour2 = c2;
 		colour3 = c3;
 	}

	/**
	 * Set the robot's position by taking desired x1 and y1 coordinates (front point) and adjusting the whole triangle.
	 * @param targetX1.
	 * @param targetY1.
	 */
	public void setPosition(float targetX1, float targetY1){
		float intervalX2 = x2 - x1;
		float intervalX3 = x3 - x1;

		float intervalY2 = y2 - y1;
		float intervalY3 = y3 - y1;
		
		x1 = targetX1;
		x2 = targetX1 + intervalX2;
		x3 = targetX1 + intervalX3;

		y1 = targetY1;
		y2 = targetY1 + intervalY2;
		y3 = targetY1 + intervalY3;

	}

	/**
	 * Set the robot's direction by turning it left until it reaches the desired direction. 
	 * @param degrees.
	 * 0 = facing right.
	 * 90 = facing down.
	 * 180 = facing left.
	 * 270 = facing up.
	 */
	public void setDirection(int degrees){		
		switch(degrees){
			case 0:
				while(direction != 1){
					turnLeft();
				}
			case 90:
				while(direction != 2){
					turnLeft();
				}
			case 180:
				while(direction != 3){
					turnLeft();
				}
			case 270:
				while(direction != 4){
					turnLeft();
				}
		}
	}
	
	/**
	 * Controls the robot by arrow keys.
	 * When an arrow key is pressed, this method will correct the robot's direction and make it move forward.
	 */
	public void control(){
		parent.keyTyped(); 
			if(parent.keyPressed && parent.keyCode==parent.UP ){
				while(direction!=4){
					turnLeft();
				}
				moveForward();
				
			}
			if(parent.keyPressed && parent.keyCode==parent.DOWN){
				while(direction!=2){
					turnLeft();
				}
				moveForward();
			}
			if(parent.keyPressed && parent.keyCode==parent.LEFT){
				while(direction!=3){
					turnLeft();
				}
				moveForward();
			}
			if(parent.keyPressed && parent.keyCode==parent.RIGHT){
				while(direction!=1){
					turnLeft();
				}
				moveForward();
			}
 		
	}

	/**
	 * Makes the robot patrol de screen by moving it forward until hitting a wall and then turning it left.
	 */
	public void patrol(){
		if((direction==1&&((x1+speed)>=parent.width-1))||(direction==2&&((y1+speed)>= parent.height-1))||(direction==3&&(x1-speed)<=1)||(direction==4&&((y1-speed)<= 1))){
			turnLeft();
			turnLeft();
			turnLeft();
			turnLeft();
			turnLeft();
		}
		moveForward();
	}

	/**
	 * Makes the robot randomly walk. It goes forward until it reaches a wall.
	 * Then, it goes back a few steps, turns a random times to a random direction and goes forward again.
	 */
	public void randomWalk(){
		if((direction==1&&((x1+speed)>=parent.width-1))||(direction==2&&((y1+speed)>= parent.height-1))||(direction==3&&(x1-speed)<=1)||(direction==4&&((y1-speed)<= 1))){			
			hit = true;
		}
			
		if(hit == false){
			moveForward();		
		} else if (hit == true){
			moveBackwards();
			k++;
			if(k >= 8){
				int turns = parent.round(parent.random(10));
				for(int i = 0; i==turns; i++){
					int side = parent.round(parent.random(1));
					if (side == 0){
						turnLeft();
						hit = false;
						k = 0;
					} else {
						turnRight();
						hit = false;
						k = 0;
					}
				}
			}
		}	
	}

}
