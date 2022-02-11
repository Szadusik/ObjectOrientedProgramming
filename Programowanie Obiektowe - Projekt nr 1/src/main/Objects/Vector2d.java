package Objects;
import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "("+ this.x + "," + this.y + ")";
    }

    public boolean precedes(Vector2d other)
    {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other)
    {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }
    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public double euklidesDistance(Vector2d a){
        return Math.sqrt((this.x-a.x)^2 + (this.y - a.y)^2);
    }

    @Override
    public boolean equals(Object other)
    {
        Vector2d t = (Vector2d) other;
        return this.x == t.x && this.y == t.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}