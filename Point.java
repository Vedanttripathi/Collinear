/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;


    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public void draw()
    {
        StdDraw.point(x, y);
    }


    public void drawTo(Point that)
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public double slopeTo(Point that)
    {
        if (this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else if (this.y == that.y)
            return (+0.0);
        else
            return ((double) (that.y - this.y))/((double) (that.x - this.x));
    }


    public int compareTo(Point that)
    {
        if (this.y == that.y)
        {
            if (this.x == that.x)
                return 0;
            else if (this.x < that.x)
                return -1;
            else
                return +1;
        }
        else if (this.y < that.y)
            return -1;
        else
            return +1;

    }

    public Comparator<Point> slopeOrder()
    {

        return new Slopeorder();
    }


    private class Slopeorder implements Comparator<Point>
    {
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2))
                return -1;
            else if (slopeTo(o1) > slopeTo(o2))
                return +1;
            else
                return 0;

        }
    }


    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    public static void main(String[] args)
    {
        /* YOUR CODE HERE */
    }
}
