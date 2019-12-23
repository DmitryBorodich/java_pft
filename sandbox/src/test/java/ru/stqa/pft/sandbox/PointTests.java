package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests
{
    @Test
    public void testArea ()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        Assert.assertEquals(p1.distance(p1,p2), 5);

        Point p3 = new Point(-1, -1);
        Point p4 = new Point(-4, -5);
        Assert.assertEquals(p4.distance(p4,p3), 5);

        Point p5 = new Point(-1, -1);
        Point p6 = new Point(-4, -5);
        Assert.assertEquals(p4.distance(p4,p3), 6);

    }
}
