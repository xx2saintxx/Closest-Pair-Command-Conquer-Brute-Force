/** A main class for the Closest Pair algorithms
 *  Programming assignment for
 *  CSI403 Algorithms and Data Structures
 *  University at Albany - SUNY
 *
 * Instructions: Implement methods: 
 * 1) getCPBruteForce()
 * 2) getCPDivideAndConquer()
 * As discussed in class and in the assignment part (a)
 */

/**
 * @Author Toussaint Turnier
 * Algorithms and Data Structures 403
 * SLO1. Analyze the complexity of simple algorithms and discuss advantages of alternative
 * algorithms for the same problem.
 * SLO2. Recognize design patterns such as greedy, divide-and-conquer and others
 */

package closestpair;


import java.util.ArrayList;

public class ClosestPair {

    /**
     * TODO: IMPLEMENT
     * A brute force method for the closest pair
     *
     * @returns an array of exactly the two closest points
     * IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
     * ONLY THE BODY WILL BE CONSIDERED FOR GRADING
     */
    public static Point[] getCPBruteForce(Point[] pts) {
        //TODO: Implement this method for part (a) of the assignment
        Point a = null; // Setting two Place holder points.
        Point b = null;
        Double minDistance = Double.MAX_VALUE; //Minimum distance is the Maximum.
        for (int i = 0; i < pts.length - 1; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                Double distance = pts[i].dist(pts[j]); //Brute Force
                if (distance < minDistance) {
                    a = pts[i];
                    b = pts[j];
                    minDistance = distance;
                }
            }
        }
        return new Point[]{a, b};
    }

    /**
     * A driver for the Divide-And-Conquer method for the closest pair
     * takes unsorted array of points, sorts them and invokes
     * the recursive method you are required to implement
     *
     * @returns an array of exactly the two closest points
     * IMPORTANT: DO NOT CHANGE THIS METHOD
     */
    public static Point[] getCPDivideAndConquer(Point[] pts) {
        Point[] ptsX = Point.sortByX(pts);
        Point[] ptsY = Point.sortByY(pts);
        return getCPDivideAndConquer(ptsX, ptsY);
    }

    /**
     * TODO: IMPLEMENT
     * A Divide-And-Conquer method for the closest pair
     * takes as input the points sorted by increasing x
     * and y coordinates in ptsX and ptsY respectively
     *
     * @returns an array of exactly the two closest points.
     * IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
     * ONLY THE BODY WILL BE CONSIDERED FOR GRADING
     */
    public static Point[] getCPDivideAndConquer(Point[] ptsX, Point[] ptsY) {
        int n = ptsX.length; // n equal to size of input.
        if (n <= 3) { //Base Case.
            return getCPBruteForce(ptsX);
        }
        ArrayList<Point> splitter = new ArrayList<>(); // Splitter ArrayList
        int middle = n/2; //Gets the middle Point as a reference.
        Point[] leftX = new Point[middle]; //Left side X values.
        Point[] leftY = new Point[middle]; //Left side Y values.

        Point[] rightX = new Point[n - middle]; //Right side X values.
        Point[] rightY = new Point[n - middle]; //Right side Y values.

        for (int i = 0; i < middle; i++) { //On the Left side. Set X and Y values.
            leftX[i] = ptsX[i];
            leftY[i] = ptsY[i];
        }
        Point[] firstPair = getCPDivideAndConquer(leftX, leftY); // Recursive call to get closest pair on Left side of Array
        Double minDistance = (new Point(firstPair[0].x, firstPair[0].y)).dist(new Point(firstPair[1].x, firstPair[1].y));
        for (int i = middle; i < n; i++) { //On the Right side. Set X and Y values.
            rightX[i - middle] = ptsX[i];
            rightY[i - middle] = ptsY[i];
        }
        Point[] secondPair = getCPDivideAndConquer(rightX, rightY); // Recursive call to get closest pair on Right side of Array
        Double minDistance2 = (new Point(secondPair[0].x, secondPair[0].y)).dist(new Point(secondPair[1].x, secondPair[1].y));

        double min; //minimum placeholder.
        Point[] closestPair; // Array that will hold the closest pair of points.

        if (minDistance <= minDistance2) { //Setting the smallest distance.
            closestPair = firstPair;
            min = minDistance;
        }else{
            closestPair = secondPair;
            min = minDistance2;
        }

        double leftMax = (ptsX[middle].x - min); //Set Boundaries
        double rightMax = (ptsX[middle].x + min);


        for (int i = 0; i < ptsY.length; i++) {
            if (leftMax < ptsY[i].x && ptsY[i].x < rightMax) {
                splitter.add(ptsY[i]);
            }
        }
        for (int i = 0; i < (splitter.size() - 1); i++){ //Use Array list to easily get values that are closest to the middle and find smallest strip
            for (int j = i + 1; j < Math.min(splitter.size(), i + 5); j++){ // 5 buffer
                Double dist = (new Point(splitter.get(i).x, splitter.get(i).y)).dist(new Point(splitter.get(j).x, splitter.get(j).y));

                if (dist <= min) {
                    min = dist;
                    closestPair[0] = splitter.get(i);
                    closestPair[1] = splitter.get(j);
                }
            }
        }
        return closestPair;
    }
   }

