/**
 * Name: Gustavo Lessa
 * Student number: 2016104
 * 
 * import: import processing.core.PApplet;
 * import: java.util.Scanner;
 * 
 * This class contains the main method along with the following:
 * settings() = defines size of screen;
 * createCharlie() = lets use input Charlie' coordinates or generate them randomly
 * mathCharlie() = Calls methods from Maths class to generate and display Math's questions and Charlie's properties
 * setup() = calls createCharlie(), create the other robots, calls the mathCharlie().
 * draw() = executes an infinite loop, where the methods from Robot class are called and where everything is drawn to the screen
 * 
 */
import processing.core.PApplet;
import java.util.Scanner;

public class TestRobots extends PApplet{
	Robot alice;
	Robot charlie;	
	Robot bob;
	float charlieX1;
	float charlieY1;
	float charlieX2;
	float charlieY2;
	float charlieX3;
	float charlieY3;
	char c; //char retrieved from input
	Scanner sc = new Scanner(System.in);
	Maths calc = new Maths(this);
	
	public static void main(String[] args){
 		PApplet.main("TestRobots");
 	}

	public void settings(){
 		size(1000, 500);
 		smooth();
 	}

	/**
 * Lets the user choose between randomly generating Charlie's coordinates or inputing the desired ones.
 * To choose, the user has to input Y or N after questioned. Any other input won't work.
 * Several conditions apply for the numbers. They can't be negative or exceed the screen's boundaries.
 * When randomly generated, limits were defined not to generate a oddly shaped robot.
 * @param x1, x2, x3, y1, y2, y3
 */
	public void createCharlie(){
		System.out.print("\nWould you like to choose Charlie's coordinates?  (Y/N)\n");
		do{
			c = sc.next().charAt(0);
			if(c != 'y'&& c != 'Y' && c != 'n' && c != 'N'){
				System.out.println("\nPlease enter Y or N.");
				c = sc.next().charAt(0);
			}
		}while(c != 'y'&& c != 'Y' && c != 'n' && c != 'N');
		
		if (c == 'y' || c == 'Y'){
			System.out.print("\nX should be between 0 and " +width+".");
			System.out.println("\nY should be between 0 and " +height+".");
			
			System.out.print("\nVertex A");
			do{
				System.out.print("\nx1: ");
				charlieX1 = round(sc.nextFloat());	
					if(charlieX1 < 0 || charlieX1 > width){
						System.out.print("\nInvalid number!");
					}
			}while (charlieX1 < 0 || charlieX1 > width);

			do{
				System.out.print("y1: ");
				charlieY1 = round(sc.nextFloat());
					if(charlieY1 < 0 || charlieY1 > height){
						System.out.print("\nInvalid number!");
					}
			}while (charlieY1 < 0 || charlieY1 > height);
						
			System.out.print("\nVertex B");
			do{
				System.out.print("\nx2: ");
				charlieX2 = round(sc.nextFloat());
					if(charlieX2 < 0 || charlieX2 > width){
						System.out.print("\nInvalid number!");
					}
			}while (charlieX2 < 0 || charlieX2 > width);
			do{
				System.out.print("y2: ");
				charlieY2 = round(sc.nextFloat());
					if(charlieY2 < 0 || charlieY2 > height){
						System.out.print("\nInvalid number!");
					}
			}while (charlieY2 < 0 || charlieY2 > height);
			
			System.out.print("\nVertex C");
			do{
				System.out.print("\nx3: ");
				charlieX3 = round(sc.nextFloat());
				if(charlieX3 < 0 || charlieX3 > width){
						System.out.print("\nInvalid number!");
				}
			}while (charlieX3 < 0 || charlieX3 > width);
			do{
				System.out.print("y3: ");
				charlieY3 = round(sc.nextFloat());
					if(charlieY3 < 0 || charlieY3 > height){
						System.out.print("\nInvalid number!");
					}
			}while (charlieY3 < 0 || charlieY3 > height);
		} else {
			do{
			charlieX1 = round(random(30, width-30));
			charlieY1 = round(random(30, height-30));
			charlieX2 = round(random(charlieX1+30, charlieX1+200));
			charlieY2 = round(random(charlieY1+30, charlieY1+150));
			charlieX3 = round(random(charlieX1-200, charlieX1-30));
			charlieY3 = round(random(charlieY1+30, charlieY1+150));
			} while (charlieX2 < 0 || charlieX3 < 0 || charlieY2 < 0 || charlieY3 < 0 || charlieX2 > width || charlieX3 > width || charlieY2 > height || charlieY3 > height );
			
			System.out.println("\nCharlie's vertex A = ("+charlieX1+", "+charlieY1+").");
			System.out.println("Charlie's vertex B = ("+charlieX2+", "+charlieY2+").");
			System.out.println("Charlie's vertex C = ("+charlieX3+", "+charlieY3+").");
		}
	}

	/**
	 * Calls methods from Maths class to generate and display Math's questions and Charlie's properties
	 */
	public void mathCharlie(){
		calc.equation(charlieX1, charlieY1, charlieX2, charlieY2);
		calc.equation(charlieX1, charlieY1, charlieX3, charlieY3);
		calc.equation(charlieX2, charlieY2, charlieX3, charlieY3);
		System.out.println("");
		calc.length(charlieX1, charlieY1, charlieX2, charlieY2);
		calc.length(charlieX1, charlieY1, charlieX3, charlieY3);
		calc.length(charlieX2, charlieY2, charlieX3, charlieY3);	
		System.out.println("");
		calc.area(charlieX1, charlieY1, charlieX2, charlieY2, charlieX3, charlieY3);
		System.out.println("");
		calc.triangleType(charlieX1, charlieY1, charlieX2, charlieY2, charlieX3, charlieY3);
		System.out.println("");
		charlie.printCentroid();
		calc.rotate(45, charlieX1, charlieY1, charlieX2, charlieY2, charlieX3, charlieY3);
		System.out.println("");
		calc.dilation(70, charlieX1, charlieY1, charlieX2, charlieY2, charlieX3, charlieY3);
	}
	
	public void setup(){
		frameRate(40);
		createCharlie();
		alice = new Robot(this, "Alice", 255, 204, 0, 257f, 389f, 309f, 450f, 209f, 450f, 3);
		charlie = new Robot(this, "Charlie", 239, 60, 67, charlieX1, charlieY1, charlieX2, charlieY2, charlieX3, charlieY3, 10);	
		bob = new Robot(this, "Bob", 0, 162, 103, 50f, 50f, 10f, 10f, 90f, 10f, 8);
		mathCharlie();	
		
	}

	public void draw(){
		background(177,177,180);
		alice.patrol();
		charlie.control();
		bob.randomWalk();
		alice.fixPosition();
		charlie.fixPosition();
		bob.fixPosition();
		alice.drawRobot();	
		charlie.drawRobot();
		bob.drawRobot();

	}
}