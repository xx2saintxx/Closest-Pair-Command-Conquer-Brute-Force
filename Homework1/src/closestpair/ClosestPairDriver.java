/** A driver class for the Closest Pair algorithms
 *  Programming assignment for
 *  CSI403 Algorithms and Data Structures
 *  University at Albany - SUNY
 *
 * Instructions: Only change the runnningTimeComparison method
 * All other methods will not be considered when testing your 
 * program.
 */

/**
 * @Author Toussaint Turnier
 * Algorithms and Data Structures 403
 * SLO1. Analyze the complexity of simple algorithms and discuss advantages of alternative
 * algorithms for the same problem.
 * SLO2. Recognize design patterns such as greedy, divide-and-conquer and others
 */

package closestpair;
public class ClosestPairDriver {

    /** A unit test for BruteForce on five points
     * @return true if closest pair successfully found
     */
    public static boolean testFivePointsBruteForce() {
        Point[] pts = {new Point(0.0,0.0),
                new Point(0.0,1.0),
                new Point(1.0,0.0),
                new Point(1.0,1.0),
                new Point(0.2,0.2)
        };
        Point[] cp = ClosestPair.getCPBruteForce(pts);
        Double actual = (new Point(0.0,0.0)).dist(new Point(0.2,0.2));
        if(actual.equals(cp[0].dist(cp[1])))
            return true;
        else
            return false;
    }

    /** A unit test for Divide-And-Conquer on five points
     * @return true if closest pair successfully found
     */
    public static boolean testFivePointsDaC() {
        Point[] pts = {new Point(0.0,0.0),
                new Point(0.0,1.0),
                new Point(1.0,0.0),
                new Point(1.0,1.0),
                new Point(0.2,0.2)
        };

        Point[] cp = ClosestPair.getCPDivideAndConquer(pts);
        Double actual = (new Point(0.0,0.0)).dist(new Point(0.2,0.2));
        if(actual.equals(cp[0].dist(cp[1]))) {
            return true;
        } else
            return false;
    }

    /** A unit test for comparing the results of
     *  Divide-And-Conquer and BruteForce on five points.
     *  It also demonstrates how to time the executions
     *  for part (b) of your assignment
     * @return true if closest pairs' distances match
     */
    public static boolean testRandom(int numpoints) {
        Point[] pts = getRandomPoints(numpoints);

        // Execute and time BruteForce
        long tick = System.currentTimeMillis();
        Point[] cpBF = ClosestPair.getCPBruteForce(pts);
        long tock = System.currentTimeMillis();
        System.out.println("Exhaustive: " + numpoints + " (" + (tock-tick) + "ms)");

        // Execute and time Divide-and-Conquer
        tick = System.currentTimeMillis();
        Point[] cpDQ = ClosestPair.getCPDivideAndConquer(pts);
        tock = System.currentTimeMillis();
        System.out.println("Divide-And-Conquer: " + numpoints + " (" + (tock-tick) + "ms)");

        // Check if distances of pairs agree
        if(cpBF[0].dist(cpBF[1]).equals(cpDQ[0].dist(cpDQ[1])))
            return true;
        else
            return false;
    }

    /** Generates @numpoints random points in the [0,100] square
     * @return true if closest pair successfully found
     */
    private static Point[] getRandomPoints(int numpoints) {
        Point[] pts = new Point[numpoints];
        for(int i=0; i< numpoints; i++) {
            pts[i] = new Point(Math.random()*100, Math.random()*100);
        }
        return pts;
    }

    /** TODO: IMPLEMENT
     *  Runs and times the BruteForce and Divide-And-Conquer
     *  algorithms for 10,100,1000 and 10000 random points
     *  Should print out using stdout the running times for both
     *  algorithms for the above sizes.
     *  Use the provided random point generator getRandomPoints()
     */
    private static void runnningTimeComparison(int numpoints) {
        //TODO: Implement this method for part (b) of the the assignment
    testRandom(numpoints); //Not sure if I HAD to use getrandompoints in this method. testRandom technically does...
    }

    /** Driver class for tests
     */
    public static void main(String[] args) {

        if(testFivePointsBruteForce())
            System.out.println("Test BruteForce: SUCCESS");
        else
            System.err.println("Test BruteForce: FAILED");


        if(testFivePointsDaC())
            System.out.println("Test Divide-And-Conquer: SUCCESS");
        else
            System.err.println("Test Divide-And-Conquer: FAILED");

        int numpoints  = 10000;
        if(testRandom(numpoints))
            System.out.println("Test "+ numpoints +" Points: SUCCESS");
        else
            System.err.println("Test "+ numpoints +" Points: FAILED");

        // Running time comparison
        runnningTimeComparison(10);
        runnningTimeComparison(100);
        runnningTimeComparison(1000);
        runnningTimeComparison(10000);

    }
}