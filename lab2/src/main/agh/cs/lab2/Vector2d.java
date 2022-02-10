package agh.cs.lab2;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "("+ x + "," + y + ")";
    }

    public boolean precedes(Vector2d other)
    {
        if(this.x <= other.x && this.y <= other.y)
            return true;
        else
            return false;
    }

    public boolean follows(Vector2d other)
    {
        if(this.x >= other.x && this.y >= other.y)
            return true;
        else
            return false;
    }

    public Vector2d upperRight(Vector2d other)
    {
        int a,b;
        a = Math.max(this.x,other.x);
        b = Math.max(this.y,other.y);
        Vector2d another = new Vector2d(a,b);
        return another;
    }
    public Vector2d lowerLeft(Vector2d other)
    {
        int a,b;
        a = Math.min(this.x,other.x);
        b = Math.min(this.y,other.y);
        Vector2d another = new Vector2d(a,b);
        return another;
    }
    public Vector2d add(Vector2d other)
    {
        Vector2d sum = new Vector2d(this.x + other.x, this.y + other.y);
        return sum;
    }
    public Vector2d subtract(Vector2d other)
    {
        Vector2d sub = new Vector2d(this.x - other.x,this.y - other.y);
        return sub;
    }
    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        else
            return false;
    }
    public Vector2d opposite()
    {
        Vector2d opp = new Vector2d(-this.x,-this.y);
        return opp;
    }
}
