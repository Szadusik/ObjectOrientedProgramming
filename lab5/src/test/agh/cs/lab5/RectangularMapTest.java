package agh.cs.lab5;

import agh.cs.oop.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest{
    //Mapy testowe
    IWorldMap map1 = new RectangularMap(5,10, new ArrayList<>());
    IWorldMap map2 = new RectangularMap(15,5, new ArrayList<>());
    IWorldMap map3 = new RectangularMap(4,1, new ArrayList<>());
    IWorldMap map4 = new RectangularMap(7,9, new ArrayList<>());
    IWorldMap map5 = new RectangularMap(4,12, new ArrayList<>());

    //Testowe zwierzęta (po 4 na każdą mapę)
    animal a1 = new animal(map1,new Vector2d(2,2));
    animal a2 = new animal(map1,new Vector2d(6,10));
    animal a3 = new animal(map1,new Vector2d(4,9));
    animal a4 = new animal(map1,new Vector2d(5,1));

    animal a5 = new animal(map2,new Vector2d(13,4));
    animal a6 = new animal(map2,new Vector2d(16,2));
    animal a7 = new animal(map2,new Vector2d(15,5));
    animal a8 = new animal(map2,new Vector2d(1,1));

    animal a9 = new animal(map3,new Vector2d(2,2));
    animal a10 = new animal(map3,new Vector2d(4,0));
    animal a11 = new animal(map3,new Vector2d(1,1));
    animal a12 = new animal(map3,new Vector2d(3,2));

    animal a13 = new animal(map4,new Vector2d(4,4));
    animal a14 = new animal(map4,new Vector2d(7,7));
    animal a15 = new animal(map4,new Vector2d(-1,2));
    animal a16 = new animal(map4,new Vector2d(9,0));

    animal a17 = new animal(map5,new Vector2d(4,4));
    animal a18 = new animal(map5,new Vector2d(4,4));
    animal a19 = new animal(map5,new Vector2d(2,2));
    animal a20 = new animal(map5,new Vector2d(2,2));

    @Test
    void testCanMoveTo(){
        /*Poprawność sprawdzania, czy możemy wykonać ruch na daną pozycję, czyli czy
        na danej pozycji nie trafimy na innego zwierzaka oraz czy nie wyjdziemy poza mapę
         */
        map1.place(a1);
        map1.place(a2);
        map1.place(a3);
        map1.place(a4);
        assertTrue(map1.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map1.canMoveTo(new Vector2d(10, 0)));
        assertFalse(map1.canMoveTo(new Vector2d(4, 11)));
        assertFalse(map1.canMoveTo(new Vector2d(2, 2)));
        assertTrue(map1.canMoveTo(new Vector2d(3, 7)));

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertFalse(map2.canMoveTo(new Vector2d(16, 0)));
        assertTrue(map2.canMoveTo(new Vector2d(5, 5)));
        assertFalse(map2.canMoveTo(new Vector2d(15, 5)));
        assertFalse(map2.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map2.canMoveTo(new Vector2d(5, -16)));

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertFalse(map3.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map3.canMoveTo(new Vector2d(-8, -5)));
        assertFalse(map3.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map3.canMoveTo(new Vector2d(3, 1)));
        assertTrue(map3.canMoveTo(new Vector2d(0, 0)));

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertFalse(map4.canMoveTo(new Vector2d(12, 12)));
        assertFalse(map4.canMoveTo(new Vector2d(-5, 3)));
        assertFalse(map4.canMoveTo(new Vector2d(7, 7)));
        assertTrue(map4.canMoveTo(new Vector2d(2, 2)));
        assertTrue(map4.canMoveTo(new Vector2d(7, 0)));

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertFalse(map5.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map5.canMoveTo(new Vector2d(2, 2)));
        assertTrue(map5.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map5.canMoveTo(new Vector2d(5, 4)));
        assertFalse(map5.canMoveTo(new Vector2d(-1, -1)));
    }

    @Test
    void testPlace(){
        /*
        Sprawdzenie, czy zwierzęta są wkładane do mapy nie kolidując z innymi zwierzętami oraz
        czy nie wkładamy poza mapę
         */
        assertTrue(map1.place(a1));
        assertFalse(map1.place(a2));
        assertTrue(map1.place(a3));
        assertTrue(map1.place(a4));

        assertTrue(map2.place(a5));
        assertFalse(map2.place(a6));
        assertTrue(map2.place(a7));
        assertTrue(map2.place(a8));

        assertFalse(map3.place(a9));
        assertTrue(map3.place(a10));
        assertTrue(map3.place(a11));
        assertFalse(map3.place(a12));

        assertTrue(map4.place(a13));
        assertTrue(map4.place(a14));
        assertFalse(map4.place(a15));
        assertFalse(map4.place(a16));

        assertTrue(map5.place(a17));
        assertFalse(map5.place(a18));
        assertTrue(map5.place(a19));
        assertFalse(map5.place(a20));
    }

    @Test
    void testIsOccupied()
    {   //Sprawdzenie, czy dane miejsce na danej mapie jest zajmowane przez zwierzę
        map1.place(a1);
        map1.place(a2);
        map1.place(a3);
        map1.place(a4);
        assertFalse(map1.isOccupied(new Vector2d(5, 5)));
        assertTrue(map1.isOccupied(new Vector2d(2, 2)));
        assertFalse(map1.isOccupied(new Vector2d(0, 0)));
        assertTrue(map1.isOccupied(new Vector2d(4, 9)));

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertTrue(map2.isOccupied(new Vector2d(15, 5)));
        assertFalse(map2.isOccupied(new Vector2d(6, 5)));
        assertFalse(map2.isOccupied(new Vector2d(1, 2)));
        assertTrue(map2.isOccupied(new Vector2d(1, 1)));

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertFalse(map3.isOccupied(new Vector2d(2, 1)));
        assertFalse(map3.isOccupied(new Vector2d(4, 1)));
        assertTrue(map3.isOccupied(new Vector2d(1, 1)));
        assertTrue(map3.isOccupied(new Vector2d(4, 0)));

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertTrue(map4.isOccupied(new Vector2d(4, 4)));
        assertTrue(map4.isOccupied(new Vector2d(7, 7)));
        assertFalse(map4.isOccupied(new Vector2d(0, 5)));
        assertFalse(map4.isOccupied(new Vector2d(5, 5)));

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertTrue(map5.isOccupied(new Vector2d(4, 4)));
        assertTrue(map5.isOccupied(new Vector2d(2, 2)));
        assertFalse(map5.isOccupied(new Vector2d(1, 1)));
        assertFalse(map5.isOccupied(new Vector2d(6, 6)));

    }
    @Test
    void testObjectAt() {
        /*Poprawność wyciągania danego zwierzaka na danej pozycji
            albo wartości null, gdy na danej pozycji nie ma żadnego zwierzaka
         */
        map1.place(a1);
        map1.place(a2);
        map1.place(a3);
        map1.place(a4);
        assertNull(map1.objectAt(new Vector2d(5, 5)));
        assertNull(map1.objectAt(new Vector2d(3, 3)));
        assertEquals(map1.objectAt(new Vector2d(4,9)),a3);
        assertEquals(map1.objectAt(new Vector2d(5,1)),a4);

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertNull(map2.objectAt(new Vector2d(5, 5)));
        assertEquals(map2.objectAt(new Vector2d(1,1)),a8);
        assertEquals(map2.objectAt(new Vector2d(15,5)),a7);
        assertNull(map2.objectAt(new Vector2d(2, 2)));

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertNull(map3.objectAt(new Vector2d(0, 0)));
        assertNull(map3.objectAt(new Vector2d(1, 2)));
        assertEquals(map3.objectAt(new Vector2d(1,1)),a11);
        assertNull(map3.objectAt(new Vector2d(3, 0)));

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertEquals(map4.objectAt(new Vector2d(4,4)),a13);
        assertNull(map4.objectAt(new Vector2d(5, 5)));
        assertNull(map4.objectAt(new Vector2d(1, 7)));
        assertNull(map4.objectAt(new Vector2d(3, 0)));

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertEquals(map5.objectAt(new Vector2d(4,4)),a17);
        assertEquals(map5.objectAt(new Vector2d(2,2)),a19);
        assertNull(map5.objectAt(new Vector2d(5, 5)));
        assertNull(map5.objectAt(new Vector2d(0, 0)));

    }

    @Test
    void testToString() {
        //Poprawność wyrysowywania danej mapy
        IWorldMap map1test = new RectangularMap(5,10, new ArrayList<>());
        IWorldMap map2test = new RectangularMap(15,5, new ArrayList<>());
        IWorldMap map3test = new RectangularMap(4,1, new ArrayList<>());
        IWorldMap map4test = new RectangularMap(7,9, new ArrayList<>());
        IWorldMap map5test = new RectangularMap(4,12, new ArrayList<>());

        assertEquals(map1.toString(),map1test.toString());
        map1test.place(a1);
        assertNotEquals(map1test.toString(), map1.toString());
        map1.place(a2);
        map1.place(a1);
        assertEquals(map1test.toString(), map1.toString());
        map1test.place(a2);
        assertEquals(map1test.toString(), map1.toString());
        map1.place(a4);
        map1.place(a3);
        map1test.place(a3);
        assertNotEquals(map1test.toString(), map1.toString());
        map1test.place(a4);
        assertEquals(map1test.toString(), map1.toString());

        map2.place(a6);
        assertEquals(map2test.toString(), map2.toString());
        map2test.place(a6);
        assertEquals(map2test.toString(), map2.toString());
        map2.place(a7);
        map2.place(a8);
        assertNotEquals(map2test.toString(), map2.toString());
        map2test.place(a7);
        map2test.place(a8);
        assertEquals(map2test.toString(), map2.toString());
        map2test.place(a5);
        assertNotEquals(map2test.toString(), map2.toString());

        map3test.place(a9);
        map3.place(a9);
        assertEquals(map3test.toString(), map3.toString());
        map3.place(a10);
        map3test.place(a10);
        assertEquals(map3test.toString(), map3.toString());
        map3.place(a11);
        assertNotEquals(map3test.toString(), map3.toString());
        map3.place(a12);
        map3test.place(a11);
        assertEquals(map3test.toString(), map3.toString());

        map4test.place(a13);
        assertNotEquals(map4test.toString(), map4.toString());
        map4.place(a13);
        assertEquals(map4test.toString(), map4.toString());
        map4test.place(a15);
        assertEquals(map4test.toString(), map4.toString());
        map4.place(a16);
        assertEquals(map4test.toString(), map4.toString());
        map4.place(a14);
        assertNotEquals(map4test.toString(), map4.toString());

        assertEquals(map5test.toString(), map5.toString());
        map5.place(a17);
        assertNotEquals(map5test.toString(), map5.toString());
        map5.place(a18);
        map5test.place(a18);
        assertEquals(map5test.toString(), map5.toString());
        map5.place(a19);
        map5test.place(a20);
        assertEquals(map5test.toString(), map5.toString());
    }
}