package agh.cs.lab7;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest{
    // Testowe pola traw
    GrassField field1 = new GrassField(10,new HashMap<>());
    GrassField field2 = new GrassField(20,new HashMap<>());
    GrassField field3 = new GrassField(50,new HashMap<>());
    GrassField field4 = new GrassField(25,new HashMap<>());
    GrassField field5 = new GrassField(43,new HashMap<>());

    // Testowe zwierzaki
    animal a1 = new animal(field1,new Vector2d(2,3));
    animal a2 = new animal(field1,new Vector2d(5,5));
    animal a3 = new animal(field1,new Vector2d(5,5));
    animal a4 = new animal(field1,new Vector2d(12,3));
    animal a5 = new animal(field1,new Vector2d(7,0));

    animal a6 = new animal(field2,new Vector2d(3,4));
    animal a7 = new animal(field2,new Vector2d(0,0));
    animal a8 = new animal(field2,new Vector2d(12,5));
    animal a9 = new animal(field2,new Vector2d(6,2));
    animal a10 = new animal(field2,new Vector2d(-1,1));

    animal a11 = new animal(field3,new Vector2d(2,8));
    animal a12 = new animal(field3,new Vector2d(11,2));
    animal a13 = new animal(field3,new Vector2d(-15,-1));
    animal a14 = new animal(field3,new Vector2d(5,-17));
    animal a15 = new animal(field3,new Vector2d(25,0));

    animal a16 = new animal(field4,new Vector2d(4,1));
    animal a17 = new animal(field4,new Vector2d(4,1));
    animal a18 = new animal(field4,new Vector2d(6,7));
    animal a19 = new animal(field4,new Vector2d(6,12));
    animal a20 = new animal(field4,new Vector2d(4,0));

    animal a21 = new animal(field5,new Vector2d(11,11));
    animal a22 = new animal(field5,new Vector2d(21,-86));
    animal a23 = new animal(field5,new Vector2d(-5,-21));
    animal a24 = new animal(field5,new Vector2d(-9,-6));
    animal a25 = new animal(field5,new Vector2d(11,9));

    @Test
    void canMoveTo(){
        field1.place(a1);
        field1.place(a2);
        field1.place(a4);
        field1.place(a5);

        field2.place(a6);
        field2.place(a7);
        field2.place(a8);
        field2.place(a9);

        field3.place(a11);
        field3.place(a12);
        field3.place(a15);

        field4.place(a16);
        field4.place(a18);
        field4.place(a19);
        field4.place(a20);

        field5.place(a21);
        field5.place(a25);

        assertFalse(field1.canMoveTo(new Vector2d(2, 3)));
        assertFalse(field1.canMoveTo(new Vector2d(5, 5)));
        assertFalse(field1.canMoveTo(new Vector2d(12, 3)));
        assertFalse(field1.canMoveTo(new Vector2d(7, 0)));

        assertFalse(field2.canMoveTo(new Vector2d(0, 0)));
        assertTrue(field2.canMoveTo(new Vector2d(20, 20)));
        assertTrue(field2.canMoveTo(new Vector2d(40, 3)));
        assertFalse(field2.canMoveTo(new Vector2d(6, 2)));

        assertFalse(field3.canMoveTo(new Vector2d(2, 8)));
        assertFalse(field3.canMoveTo(new Vector2d(25, 0)));
        assertTrue(field3.canMoveTo(new Vector2d(26, 0)));
        assertTrue(field3.canMoveTo(new Vector2d(50, 60)));

        assertFalse(field4.canMoveTo(new Vector2d(4, 1)));
        assertFalse(field4.canMoveTo(new Vector2d(6, 7)));
        assertFalse(field4.canMoveTo(new Vector2d(-1, 0)));
        assertTrue(field4.canMoveTo(new Vector2d(50, 50)));

        assertFalse(field5.canMoveTo(new Vector2d(11, 11)));
        assertFalse(field5.canMoveTo(new Vector2d(11, 9)));
        assertFalse(field5.canMoveTo(new Vector2d(-1, 0)));
        assertTrue(field5.canMoveTo(new Vector2d(80, 80)));

    }

    @Test
    void place() {

        assertTrue(field1.place(a1));
        assertTrue(field1.place(a2));
        assertThrows(IllegalArgumentException.class, () -> field1.place(a3));
        assertTrue(field1.place(a4));
        assertTrue(field1.place(a5));

        assertTrue(field2.place(a6));
        assertTrue(field2.place(a7));
        assertTrue(field2.place(a8));
        assertTrue(field2.place(a9));
        assertThrows(IllegalArgumentException.class, () -> field2.place(a10));

        assertTrue(field3.place(a11));
        assertTrue(field3.place(a12));
        assertThrows(IllegalArgumentException.class, () -> field3.place(a13));
        assertThrows(IllegalArgumentException.class, () -> field3.place(a14));
        assertTrue(field3.place(a15));

        assertTrue(field4.place(a16));
        assertThrows(IllegalArgumentException.class, () -> field4.place(a17));
        assertTrue(field4.place(a18));
        assertTrue(field4.place(a19));
        assertTrue(field4.place(a20));

        assertTrue(field5.place(a21));
        assertThrows(IllegalArgumentException.class, () -> field5.place(a22));
        assertThrows(IllegalArgumentException.class, () -> field5.place(a23));
        assertThrows(IllegalArgumentException.class, () -> field5.place(a24));
        assertTrue(field5.place(a25));

    }

    @Test
    void isOccupied() {
        field1.place(a1);
        field1.place(a2);
        field1.place(a4);
        field1.place(a5);

        field2.place(a6);
        field2.place(a7);
        field2.place(a8);
        field2.place(a9);

        field3.place(a11);
        field3.place(a12);
        field3.place(a15);

        field4.place(a16);
        field4.place(a18);
        field4.place(a19);
        field4.place(a20);

        field5.place(a21);
        field5.place(a25);

        assertTrue(field1.isOccupied(new Vector2d(2, 3)));
        assertTrue(field1.isOccupied(new Vector2d(5, 5)));
        assertTrue(field1.isOccupied(new Vector2d(12, 3)));
        assertTrue(field1.isOccupied(new Vector2d(7, 0)));

        assertTrue(field2.isOccupied(new Vector2d(0, 0)));
        assertFalse(field2.isOccupied(new Vector2d(20, 20)));
        assertFalse(field2.isOccupied(new Vector2d(40, 3)));
        assertTrue(field2.isOccupied(new Vector2d(6, 2)));

        assertTrue(field3.isOccupied(new Vector2d(2, 8)));
        assertTrue(field3.isOccupied(new Vector2d(25, 0)));
        assertFalse(field3.isOccupied(new Vector2d(26, 0)));
        assertFalse(field3.isOccupied(new Vector2d(50, 60)));

        assertTrue(field4.isOccupied(new Vector2d(4, 1)));
        assertTrue(field4.isOccupied(new Vector2d(6, 7)));
        assertTrue(field4.isOccupied(new Vector2d(4, 0)));
        assertFalse(field4.isOccupied(new Vector2d(50, 50)));

        assertTrue(field5.isOccupied(new Vector2d(11, 11)));
        assertTrue(field5.isOccupied(new Vector2d(11, 9)));
        assertFalse(field5.isOccupied(new Vector2d(-1, 0)));
        assertFalse(field5.isOccupied(new Vector2d(80, 80)));

    }

    @Test
    void objectAt() {
        field1.place(a1);
        field1.place(a2);
        field1.place(a4);
        field1.place(a5);

        field2.place(a6);
        field2.place(a7);
        field2.place(a8);
        field2.place(a9);

        field3.place(a11);
        field3.place(a12);
        field3.place(a15);

        field4.place(a16);
        field4.place(a18);
        field4.place(a19);
        field4.place(a20);

        field5.place(a21);
        field5.place(a25);

        assertNotNull(field1.objectAt(new Vector2d(2, 3)));
        assertNotNull(field1.objectAt(new Vector2d(5, 5)));
        assertNull(field1.objectAt(new Vector2d(56, 63)));
        assertNotNull(field1.objectAt(new Vector2d(12, 3)));
        assertNotNull(field1.objectAt(new Vector2d(7, 0)));

        assertNotNull(field2.objectAt(new Vector2d(3, 4)));
        assertNotNull(field2.objectAt(new Vector2d(0, 0)));
        assertNull(field2.objectAt(new Vector2d(-15, 3)));
        assertNull(field2.objectAt(new Vector2d(2, -3)));
        assertNotNull(field2.objectAt(new Vector2d(6, 2)));

        assertNotNull(field3.objectAt(new Vector2d(2, 8)));
        assertNotNull(field3.objectAt(new Vector2d(11, 2)));
        assertNotNull(field3.objectAt(new Vector2d(25, 0)));
        assertNull(field3.objectAt(new Vector2d(35, -1)));
        assertNull(field3.objectAt(new Vector2d(87, 87)));

        assertNotNull(field4.objectAt(new Vector2d(4, 1)));
        assertNotNull(field4.objectAt(new Vector2d(6, 7)));
        assertNotNull(field4.objectAt(new Vector2d(6, 12)));
        assertNotNull(field4.objectAt(new Vector2d(4, 0)));
        assertNull(field4.objectAt(new Vector2d(-2, -3)));

        assertNotNull(field5.objectAt(new Vector2d(11, 11)));
        assertNotNull(field5.objectAt(new Vector2d(11, 9)));
        assertNull(field5.objectAt(new Vector2d(90, 390)));
        assertNull(field5.objectAt(new Vector2d(0, -9)));
        assertNull(field5.objectAt(new Vector2d(15, 90)));
    }

    @Test
    void testToString() {
        GrassField testfield1 = new GrassField(10,new HashMap<>());
        GrassField testfield2 = new GrassField(55,new HashMap<>());
        GrassField testfield3 = new GrassField(50,new HashMap<>());
        GrassField testfield4 = new GrassField(25,new HashMap<>());
        GrassField testfield5 = new GrassField(19,new HashMap<>());
        GrassField testfield6 = new GrassField(100,new HashMap<>());

        animal at1 = new animal(testfield2,new Vector2d(5,3));
        animal at2 = new animal(testfield4,new Vector2d(4,1));
        animal at4 = new animal(testfield5,new Vector2d(50,0));
        animal at5 = new animal(field5,new Vector2d(50,0));

        field1.place(a1);
        field4.place(a16);
        testfield5.place(at4);
        field5.place(at5);
        testfield2.place(at1);

        assertNotEquals(field1.toString(), testfield1.toString());
        assertNotEquals(field1.toString(), testfield1.toString());
        assertNotEquals(testfield2.toString(), testfield1.toString());
        assertNotEquals(testfield2.toString(), testfield4.toString());
        assertNotEquals(testfield5.toString(), testfield3.toString());
        assertNotEquals(testfield1.toString(), testfield5.toString());
        assertNotEquals(field4.toString(), testfield4.toString());
        field2.place(a8);

        assertNotEquals(field5.toString(), testfield5.toString());
        assertEquals(testfield5.toString(), testfield5.toString());
        assertEquals(testfield1.toString(), testfield1.toString());
        assertEquals(testfield2.toString(), testfield2.toString());
        assertEquals(testfield3.toString(), testfield3.toString());
        assertEquals(testfield4.toString(), testfield4.toString());
        assertEquals(field1.toString(), field1.toString());
        assertEquals(field2.toString(), field2.toString());
        assertEquals(field3.toString(), field3.toString());
        assertEquals(field4.toString(), field4.toString());
        assertEquals(field5.toString(), field5.toString());
        assertNotEquals(testfield6.toString(),field4.toString());

        testfield4.place(at2);
        assertNotEquals(testfield4.toString(), field2.toString());

    }
}