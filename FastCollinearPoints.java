/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{
    private final Point[] points;
    private final Point[] points1;
    private int count;

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException("Invalid argument");
        for (Point p : points)
        {
            if (p == null)
                throw new IllegalArgumentException("Invalid argument");
        }
        if (duplicates(points))
            throw new IllegalArgumentException("Invalid argument");
        this.points = points;
        points1 = points.clone();
        count = 0;
    }


    private boolean duplicates(Point[] p)
    {
        boolean flag = false;
        for (int i = 0; i < p.length; i++)
        {
            for (int k = 0; k < p.length; k++)
            {
                if (i == k)
                    continue;
                else if (p[i].compareTo(p[k]) == 0)
                {
                    flag = true;
                    i = p.length;
                    break;
                }
            }
        }
        return flag;
    }


    public int numberOfSegments()
    {
        return count;
    }


    public LineSegment[] segments()
    {
        ArrayList<LineSegment> list1 = new ArrayList<>();
        ArrayList<Point> list2 = new ArrayList<>();

        for (int i = 0; i < points.length; i++)
        {
            Arrays.sort(points1, points[i].slopeOrder());
            list2.add(points[i]);

            for (int j = 0; j < points1.length - 1; j++)
            {
                if (points[i].slopeTo(points1[j]) == points[i].slopeTo(points1[j+1]))
                {
                    if (!list2.contains(points1[j]))
                        list2.add(points1[j]);
                    list2.add(points1[j+1]);
                }
            }
            Point[] p = new Point[list2.size()];
            list2.toArray(p);
            Arrays.sort(p);
            if (points[i].compareTo(p[0]) == 0)
            list1.add(new LineSegment(p[0], p[p.length - 1]));
            list2.clear();
        }
        LineSegment[] seg = new LineSegment[list1.size()];
        count = list1.size();
        list1.toArray(seg);
        return seg;
    }

    public static void main(String[] args)
    {
        In in = new In(StdIn.readString());
        int n = in.readInt();
        StdDraw.setPenRadius(0.0075);
        StdDraw.setPenColor(0,0,255);
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }


        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        StdDraw.setPenRadius(0.004);
        StdDraw.setPenColor(255, 0, 0);


        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
