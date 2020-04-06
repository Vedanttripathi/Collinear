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

public class BruteCollinearPoints
{

    private final Point[] points;
    private final Point[] points1;
    private int count;


    public BruteCollinearPoints(Point[] points)
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
                {
                    continue;
                }
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
        ArrayList<LineSegment> list = new ArrayList<>();
        Arrays.sort(points1);
        for (int i = 0; i < points1.length; i++)
        {
            for (int j = i + 1; j < points1.length; j++)
            {
                for (int k = j + 1; k < points1.length; k++)
                {
                    for (int l = k + 1; l < points1.length; l++)
                    {
                            if (points1[i].slopeTo(points1[j]) == points1[i].slopeTo(points1[k]) && points1[i].slopeTo(points1[j]) == points1[i].slopeTo(points1[l]))
                            {
                                Point[] p = new Point[4];
                                p[0] = points1[i];
                                p[1] = points1[j];
                                p[2] = points1[k];
                                p[3] = points1[l];
                                if (!list.contains(new LineSegment(p[0], p[3])))
                                {
                                list.add(new LineSegment(p[0], p[3]));
                                count++;
                            }
                            }
                    }
                }
            }
        }
        LineSegment[] seg = new LineSegment[list.size()];
        list.toArray(seg);
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
