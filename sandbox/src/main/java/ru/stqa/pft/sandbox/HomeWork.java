package ru.stqa.pft.sandbox;

public class HomeWork
{
    public static void main(String[] args)
    {
        Point p1 = new Point(4,6);
        Point p2 = new Point(7,10);
        System.out.println("Длинна между двумя двумерными точками с координатами (" + p1.a + "," + p1.b + ") и (" + p2.a + "," + p2.b + ") = " + distance1(p1,p2));

        System.out.println("Длинна между двумя двумерными точками с координатами (" + p1.a + "," + p1.b + ") и (" + p2.a + "," + p2.b + ") = " + p1.distance(p2));
    }

    public static double distance1(Point p1, Point p2)
    {
        return Math.sqrt((p1.a-p2.a)*(p1.a-p2.a) + (p1.b-p2.b)*(p1.b-p2.b));
    }
}

