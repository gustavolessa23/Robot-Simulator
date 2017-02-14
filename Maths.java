/**
 * This class contains methods needed to calculate the required parameters for the math questions.
 * The constructor takes only the PApplet variable.
 */
import processing.core.PApplet;

public class Maths{
	float slope;
	float b; //from (y = ax + b)
	float length; //length of a segment
	float area; //area of triangle
	PApplet parent;

	/**
	 * Contructor needed to use the methods created. Only takes PApplet at parameter.
	 * @param PApplet
	 */
	public Maths(PApplet parent){
		this.parent = parent;
	}
	
	/**
 	* Prints the equation of a line that connects two points using the formula y-y1 = slope(x-x1), where x1 and y1 are the coordinates of one chosen point.
 	*  @param x1, y1, x2, y2.
 	*/
	public void equation(float x1, float y1, float x2, float y2){
		slope = (y2-y1)/(x2-x1);
		b = -slope*x1+y1;
		if (b==0){
			System.out.print("\nEquation of the line connecting ("+x1+", "+y1+") and ("+x2+", "+y2+") is:\ny = "+String.format("%1$.2f", slope)+"x");		
		} else if (b>0){
			System.out.print("\nEquation of the line connecting ("+x1+", "+y1+") and ("+x2+", "+y2+") is:\ny = "+String.format("%1$.2f", slope)+"x + "+String.format("%1$.2f", b));
		} else {
			System.out.print("\nEquation of the line connecting ("+x1+", "+y1+") and ("+x2+", "+y2+") is:\ny = "+String.format("%1$.2f", slope)+"x - "+String.format("%1$.2f", -b));
		}
	}

	/**
 	* Prints and returns the length of a segment that connects two points.
	* @param x1, y1, x2, y2.
 	*/
	public float length(float x1, float y1, float x2, float y2){
		length = parent.sqrt(parent.sq(x2-x1)+parent.sq(y2-y1));
		System.out.print("\nLength of the line connecting ("+x1+", "+y1+") and ("+x2+", "+y2+"): "+String.format("%1$.2f", length)+" metric units.");
		return length;
	}

	/**
 	* Returns the length of a segment that connects two points, without printing it out.
 	*  @param x1, y1, x2, y2.
 	*/
	public float returnLength(float x1, float y1, float x2, float y2){
		length = parent.sqrt(parent.sq(x2-x1)+parent.sq(y2-y1));
		return length;
	}

	/**
 	* Prints and returns the area of a triangle using its vertex coordinates.
 	*  @param x1, y1, x2, y2, x3, y3.
 	*/
	public float area(float x1, float y1, float x2, float y2, float x3, float y3){
		float calc = (x1*y2 - x2*y1) + (x2*y3 - x3*y2) + (x3*y1 - x1*y3);

		if(calc > 0){
			area = calc/2;
		} else if(calc < 0){
			area = -calc/2;
		} else {
			System.out.print("\nError calculating the area.");
		}
		System.out.println("\nArea of the triangle: "+String.format("%1$.2f", area)+" square units.");
		return area;
	}

	/**
 	* Determines and prints the type of triangle using its vertex coordinates.
 	* Note that the same triangle meet more than one condition. For example, all equilateral triangles are isosceles as well.
 	*  @param x1, y1, x2, y2, x3, y3.
 	*/
	public void triangleType(float x1, float y1, float x2, float y2, float x3, float y3){
		float ab = returnLength(x1, y1, x2, y2);
		float ac = returnLength(x1, y1, x3, y3);
		float bc = returnLength(x2, y2, x3, y3);
		if(ab == ac && ab == bc){
			System.out.print("\nThe triangle is equilateral.");
		}
		if(ab == ac || ab == bc || bc == ac){
			System.out.print("\nThe triangle is isosceles.");
		}
		if(ab != ac && ab != bc && bc != ac){
			System.out.print("\nThe triangle is scalene.");
		}
		if(ab > ac && ab > bc){
			if(parent.sq(ab)==parent.sq(ac)+parent.sq(bc)){
				System.out.print("\nThe triangle is right-angled.");
			}
		} else if(ac > ab && ac > bc){
			if(parent.sq(ac)==parent.sq(ab)+parent.sq(bc)){
				System.out.print("\nThe triangle is right-angled.");
			}
		} else if(bc > ab && bc > ac){
			if(parent.sq(bc)==parent.sq(ac)+parent.sq(ab)){
				System.out.print("\nThe triangle is right-angled.");
			}
		}
	}

	/**
	 * Calculates and prints the vertices of a triangle under rotation of chosen degree about its centroid.
	 * @param degrees, x1, y1, x2, y2, x3, y3
	 */
	public void rotate(int degrees, float x1, float y1, float x2, float y2, float x3, float y3){
  		//Calculate translation of the centroid of the triangle to the origin.
  		float xTranslation = 0 - ((x1+x2+x3)/3);
		float yTranslation = 0 - ((y1+y2+y3)/3);

		//Translate all points by the translation calculated.
		float translatedX1 = x1 + xTranslation;
		float translatedY1 = y1 + yTranslation;
		float translatedX2 = x2 + xTranslation;
		float translatedY2 = y2 + yTranslation;
		float translatedX3 = x3 + xTranslation;
		float translatedY3 = y3 + yTranslation;

		//Rotate all points 45° clockwise (considering Cartesian Plane) about the origin
		float tempX1 = translatedX1*parent.cos(parent.radians(degrees))+translatedY1*parent.sin(parent.radians(degrees));
		float tempY1 = -translatedX1*parent.sin(parent.radians(degrees))+translatedY1*parent.cos(parent.radians(degrees));
		float tempX2 = translatedX2*parent.cos(parent.radians(degrees))+translatedY2*parent.sin(parent.radians(degrees));
		float tempY2 = -translatedX2*parent.sin(parent.radians(degrees))+translatedY2*parent.cos(parent.radians(degrees));
		float tempX3 = translatedX3*parent.cos(parent.radians(degrees))+translatedY3*parent.sin(parent.radians(degrees));
		float tempY3 = -translatedX3*parent.sin(parent.radians(degrees))+translatedY3*parent.cos(parent.radians(degrees));

		//Translate all points back using the inverse of translation used before.
		float rotatedX1 = tempX1 - xTranslation;
		float rotatedY1 = tempY1 - yTranslation;
		float rotatedX2 = tempX2 - xTranslation;
		float rotatedY2 = tempY2 - yTranslation;
		float rotatedX3 = tempX3 - xTranslation;
		float rotatedY3 = tempY3 - yTranslation;

		System.out.println("\nCharlie's vertices after being rotated "+degrees+"° clockwise:");
		System.out.println("A = ("+String.format("%1$.2f", rotatedX1)+", "+String.format("%1$.2f", rotatedY1)+").");
		System.out.println("B = ("+String.format("%1$.2f", rotatedX2)+", "+String.format("%1$.2f", rotatedY2)+").");
		System.out.println("C = ("+String.format("%1$.2f", rotatedX3)+", "+String.format("%1$.2f", rotatedY3)+").");
	}

	/**
	 * Calculates and prints the coordinates of a triangle after a dilation. Takes the dilation rate in percent, and the coordinates.
	 * @param percent, x1, y1, x2, y2, x3, y3
	 */
	public void dilation(int percent, float x1, float y1, float x2, float y2, float x3, float y3){
		float newX1 = x1 * percent/100;
		float newY1 = y1 * percent/100;
		float newX2 = x2 * percent/100;
		float newY2 = y2 * percent/100;
		float newX3 = x3 * percent/100;
		float newY3 = y3 * percent/100;

		System.out.println("Charlie's vertices after being shrunk by "+(100-percent)+"%:");
		System.out.println("A = ("+String.format("%1$.2f", newX1)+", "+String.format("%1$.2f", newY1)+").");
		System.out.println("B = ("+String.format("%1$.2f", newX2)+", "+String.format("%1$.2f", newY2)+").");
		System.out.println("C = ("+String.format("%1$.2f", newX3)+", "+String.format("%1$.2f", newY3)+").");
		area(newX1, newY1, newX2, newY2, newX3, newY3);
	}

}