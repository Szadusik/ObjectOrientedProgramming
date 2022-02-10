package agh.cs.lab6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(0,0);
    Vector2d v2 = new Vector2d(2,4);
    Vector2d v3 = new Vector2d(-4,-1);
    Vector2d v4 = new Vector2d(-3,4);
    Vector2d v5 = new Vector2d(7,-5);

    @Test
    void equals() {
        assertEquals(v1.equals(v3),false);
        assertEquals(v2.equals(v1),false);
        assertEquals(v3.equals(v3),true);
        assertEquals(v4.equals(v4),true);
        assertEquals(v5.equals(v1),false);
    }

    @Test
    void testToString() {
        assertEquals(v1.toString(),"(0,0)");
        assertEquals(v2.toString(),"(2,4)");
        assertEquals(v3.toString(),"(-4,-1)");
        assertEquals(v4.toString(),"(-3,4)");
        assertEquals(v5.toString(),"(7,-5)");
    }
    @Test
    void precedes() {
        assertEquals(v1.precedes(v3),false);
        assertEquals(v2.precedes(v1),false);
        assertEquals(v3.precedes(v1),true);
        assertEquals(v4.precedes(v4),true);
        assertEquals(v5.precedes(v1),false);
    }

    @Test
    void follows() {
        assertEquals(v1.follows(v3),true);
        assertEquals(v2.follows(v1),true);
        assertEquals(v3.follows(v5),false);
        assertEquals(v4.follows(v2),false);
        assertEquals(v5.follows(v5),true);
    }

    @Test
    void upperRight() {
        assertEquals((v1.upperRight(v3)).toString(),"(0,0)");
        assertEquals((v2.upperRight(v5)).toString(),"(7,4)");
        assertEquals((v3.upperRight(v4)).toString(),"(-3,4)");
        assertEquals((v4.upperRight(v1)).toString(),"(0,4)");
        assertEquals((v5.upperRight(v5)).toString(),"(7,-5)");
    }

    @Test
    void lowerLeft() {
        assertEquals((v1.lowerLeft(v5)).toString(),"(0,-5)");
        assertEquals((v2.lowerLeft(v3)).toString(),"(-4,-1)");
        assertEquals((v3.lowerLeft(v1)).toString(),"(-4,-1)");
        assertEquals((v4.lowerLeft(v4)).toString(),"(-3,4)");
        assertEquals((v5.lowerLeft(v2)).toString(),"(2,-5)");
    }

    @Test
    void add() {
        assertEquals((v1.add(v5)).toString(),"(7,-5)");
        assertEquals((v2.add(v4)).toString(),"(-1,8)");
        assertEquals((v3.add(v3)).toString(),"(-8,-2)");
        assertEquals((v4.add(v1)).toString(),"(-3,4)");
        assertEquals((v5.add(v2)).toString(),"(9,-1)");
    }

    @Test
    void subtract() {
        assertEquals((v1.subtract(v5)).toString(),"(-7,5)");
        assertEquals((v2.subtract(v4)).toString(),"(5,0)");
        assertEquals((v3.subtract(v1)).toString(),"(-4,-1)");
        assertEquals((v4.subtract(v4)).toString(),"(0,0)");
        assertEquals((v5.subtract(v2)).toString(),"(5,-9)");
    }

    @Test
    void opposite() {
        assertEquals((v1.opposite()).toString(),"(0,0)");
        assertEquals((v2.opposite()).toString(),"(-2,-4)");
        assertEquals((v3.opposite()).toString(),"(4,1)");
        assertEquals((v4.opposite()).toString(),"(3,-4)");
        assertEquals((v5.opposite()).toString(),"(-7,5)");
    }
}