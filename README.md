# Robot-Simulator
CCT (College of Computing Technology - Dublin - Ireland)
Name: Gustavo Lessa
Student number: 2016104
1st year
Programming and Maths project

 ————————————————————————————————————
 
TestRobots is the file containing the main method and the following:
* settings() = defines size of screen;
* createCharlie() = lets use input Charlie' coordinates or generate them randomly
 * mathCharlie() = Calls methods from Maths class to generate and display Math's questions and Charlie's properties
 * setup() = calls createCharlie(), create the other robots, calls the mathCharlie().
 * draw() = executes an infinite loop, where the methods from Robot class are called and where everything is drawn to the screen
——————————————————————————————

Robot is the file containing all characteristics and methods of a robot. Includes a constructor that takes name, colour, coordinates and speed as parameters.
The most important methods are the ones that define the movements needed for each robot: patrol(), randomWalk() and control(). Every method is commented inside the file.
——————————————————————————————

Maths is the file containing a simple constructor and several methods, aiming to calculate and print the information needed for math questions.
Every method is commented inside the file.
————————————————————————————————

To compile my code (instructions for MacOS):
- Install JDK.
- Unpack all files in the same directory.
- Copy core.jar from Processing folder to the same directory above.
- Open Terminal
- Navigate to the directory
- Type: javac -cp .:core.jar TestRobots.java Robot.java Maths.java
————————————————————————————————

To run my code (instructions for MacOS):
- Follow the compilation instructions
- Type: java -cp .:core.jar TestRobots
————————————————————————————————
