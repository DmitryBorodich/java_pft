package ru.stqa.pft.sandbox;

public class Point
{
    public double a;
    public double b;

    public Point (double a, double b)
    {
        this.a= a;
        this.b= b;
    }
    public double distance(Point p1)
    {
        return Math.sqrt((p1.a-this.a)*(p1.a-this.a) + (p1.b-this.b)*(p1.b-this.b));
    }
}
