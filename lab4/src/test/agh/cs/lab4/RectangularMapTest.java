package agh.cs.lab4;

import agh.cs.oop.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest{
    //Mapy testowe
    IWorldMap map1 = new RectangularMap(5,10);
    IWorldMap map2 = new RectangularMap(15,5);
    IWorldMap map3 = new RectangularMap(4,1);
    IWorldMap map4 = new RectangularMap(7,9);
    IWorldMap map5 = new RectangularMap(4,12);

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
        assertEquals(map1.canMoveTo(new Vector2d(0,0)),true);
        assertEquals(map1.canMoveTo(new Vector2d(10,0)),false);
        assertEquals(map1.canMoveTo(new Vector2d(4,11)),false);
        assertEquals(map1.canMoveTo(new Vector2d(2,2)),false);
        assertEquals(map1.canMoveTo(new Vector2d(3,7)),true);

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertEquals(map2.canMoveTo(new Vector2d(16,0)),false);
        assertEquals(map2.canMoveTo(new Vector2d(5,5)),true);
        assertEquals(map2.canMoveTo(new Vector2d(15,5)),false);
        assertEquals(map2.canMoveTo(new Vector2d(-1,0)),false);
        assertEquals(map2.canMoveTo(new Vector2d(5,-16)),false);

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertEquals(map3.canMoveTo(new Vector2d(4,4)),false);
        assertEquals(map3.canMoveTo(new Vector2d(-8,-5)),false);
        assertEquals(map3.canMoveTo(new Vector2d(1,1)),false);
        assertEquals(map3.canMoveTo(new Vector2d(3,1)),true);
        assertEquals(map3.canMoveTo(new Vector2d(0,0)),true);

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertEquals(map4.canMoveTo(new Vector2d(12,12)),false);
        assertEquals(map4.canMoveTo(new Vector2d(-5,3)),false);
        assertEquals(map4.canMoveTo(new Vector2d(7,7)),false);
        assertEquals(map4.canMoveTo(new Vector2d(2,2)),true);
        assertEquals(map4.canMoveTo(new Vector2d(7,0)),true);

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertEquals(map5.canMoveTo(new Vector2d(4,4)),false);
        assertEquals(map5.canMoveTo(new Vector2d(2,2)),false);
        assertEquals(map5.canMoveTo(new Vector2d(0,0)),true);
        assertEquals(map5.canMoveTo(new Vector2d(5,4)),false);
        assertEquals(map5.canMoveTo(new Vector2d(-1,-1)),false);
    }

    @Test
    void testPlace(){
        /*
        Sprawdzenie, czy zwierzęta są wkładane do mapy nie kolidując z innymi zwierzętami oraz
        czy nie wkładamy poza mapę
         */
        assertEquals(map1.place(a1),true);
        assertEquals(map1.place(a2),false);
        assertEquals(map1.place(a3),true);
        assertEquals(map1.place(a4),true);

        assertEquals(map2.place(a5),true);
        assertEquals(map2.place(a6),false);
        assertEquals(map2.place(a7),true);
        assertEquals(map2.place(a8),true);

        assertEquals(map3.place(a9),false);
        assertEquals(map3.place(a10),true);
        assertEquals(map3.place(a11),true);
        assertEquals(map3.place(a12),false);

        assertEquals(map4.place(a13),true);
        assertEquals(map4.place(a14),true);
        assertEquals(map4.place(a15),false);
        assertEquals(map4.place(a16),false);

        assertEquals(map5.place(a17),true);
        assertEquals(map5.place(a18),false);
        assertEquals(map5.place(a19),true);
        assertEquals(map5.place(a20),false);
    }

    @Test
    void testIsOccupied()
    {   //Sprawdzenie, czy dane miejsce na danej mapie jest zajmowane przez zwierzę
        map1.place(a1);
        map1.place(a2);
        map1.place(a3);
        map1.place(a4);
        assertEquals(map1.isOccupied(new Vector2d(5,5)),false);
        assertEquals(map1.isOccupied(new Vector2d(2,2)),true);
        assertEquals(map1.isOccupied(new Vector2d(0,0)),false);
        assertEquals(map1.isOccupied(new Vector2d(4,9)),true);

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertEquals(map2.isOccupied(new Vector2d(15,5)),true);
        assertEquals(map2.isOccupied(new Vector2d(6,5)),false);
        assertEquals(map2.isOccupied(new Vector2d(1,2)),false);
        assertEquals(map2.isOccupied(new Vector2d(1,1)),true);

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertEquals(map3.isOccupied(new Vector2d(2,1)),false);
        assertEquals(map3.isOccupied(new Vector2d(4,1)),false);
        assertEquals(map3.isOccupied(new Vector2d(1,1)),true);
        assertEquals(map3.isOccupied(new Vector2d(4,0)),true);

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertEquals(map4.isOccupied(new Vector2d(4,4)),true);
        assertEquals(map4.isOccupied(new Vector2d(7,7)),true);
        assertEquals(map4.isOccupied(new Vector2d(0,5)),false);
        assertEquals(map4.isOccupied(new Vector2d(5,5)),false);

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertEquals(map5.isOccupied(new Vector2d(4,4)),true);
        assertEquals(map5.isOccupied(new Vector2d(2,2)),true);
        assertEquals(map5.isOccupied(new Vector2d(1,1)),false);
        assertEquals(map5.isOccupied(new Vector2d(6,6)),false);

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
        assertEquals(map1.objectAt(new Vector2d(5,5)),null);
        assertEquals(map1.objectAt(new Vector2d(3,3)),null);
        assertEquals(map1.objectAt(new Vector2d(4,9)),a3);
        assertEquals(map1.objectAt(new Vector2d(5,1)),a4);

        map2.place(a5);
        map2.place(a6);
        map2.place(a7);
        map2.place(a8);
        assertEquals(map2.objectAt(new Vector2d(5,5)),null);
        assertEquals(map2.objectAt(new Vector2d(1,1)),a8);
        assertEquals(map2.objectAt(new Vector2d(15,5)),a7);
        assertEquals(map2.objectAt(new Vector2d(2,2)),null);

        map3.place(a9);
        map3.place(a10);
        map3.place(a11);
        map3.place(a12);
        assertEquals(map3.objectAt(new Vector2d(0,0)),null);
        assertEquals(map3.objectAt(new Vector2d(1,2)),null);
        assertEquals(map3.objectAt(new Vector2d(1,1)),a11);
        assertEquals(map3.objectAt(new Vector2d(3,0)),null);

        map4.place(a13);
        map4.place(a14);
        map4.place(a15);
        map4.place(a16);
        assertEquals(map4.objectAt(new Vector2d(4,4)),a13);
        assertEquals(map4.objectAt(new Vector2d(5,5)),null);
        assertEquals(map4.objectAt(new Vector2d(1,7)),null);
        assertEquals(map4.objectAt(new Vector2d(3,0)),null);

        map5.place(a17);
        map5.place(a18);
        map5.place(a19);
        map5.place(a20);
        assertEquals(map5.objectAt(new Vector2d(4,4)),a17);
        assertEquals(map5.objectAt(new Vector2d(2,2)),a19);
        assertEquals(map5.objectAt(new Vector2d(5,5)),null);
        assertEquals(map5.objectAt(new Vector2d(0,0)),null);

    }

    @Test
    void testToString() {
        //Poprawność wyrysowywania danej mapy
        IWorldMap map1test = new RectangularMap(5,10);
        IWorldMap map2test = new RectangularMap(15,5);
        IWorldMap map3test = new RectangularMap(4,1);
        IWorldMap map4test = new RectangularMap(7,9);
        IWorldMap map5test = new RectangularMap(4,12);

        assertEquals(map1.toString(),map1test.toString());
        map1test.place(a1);
        assertEquals((map1.toString().equals(map1test.toString())),false);
        map1.place(a2);
        map1.place(a1);
        assertEquals((map1.toString().equals(map1test.toString())),true);
        map1test.place(a2);
        assertEquals((map1.toString().equals(map1test.toString())),true);
        map1.place(a4);
        map1.place(a3);
        map1test.place(a3);
        assertEquals((map1.toString().equals(map1test.toString())),false);
        map1test.place(a4);
        assertEquals((map1.toString().equals(map1test.toString())),true);

        map2.place(a6);
        assertEquals((map2.toString().equals(map2test.toString())),true);
        map2test.place(a6);
        assertEquals((map2.toString().equals(map2test.toString())),true);
        map2.place(a7);
        map2.place(a8);
        assertEquals((map2.toString().equals(map2test.toString())),false);
        map2test.place(a7);
        map2test.place(a8);
        assertEquals((map2.toString().equals(map2test.toString())),true);
        map2test.place(a5);
        assertEquals((map2.toString().equals(map2test.toString())),false);

        map3test.place(a9);
        map3.place(a9);
        assertEquals((map3.toString().equals(map3test.toString())),true);
        map3.place(a10);
        map3test.place(a10);
        assertEquals((map3.toString().equals(map3test.toString())),true);
        map3.place(a11);
        assertEquals((map3.toString().equals(map3test.toString())),false);
        map3.place(a12);
        map3test.place(a11);
        assertEquals((map3.toString().equals(map3test.toString())),true);

        map4test.place(a13);
        assertEquals((map4.toString().equals(map4test.toString())),false);
        map4.place(a13);
        assertEquals((map4.toString().equals(map4test.toString())),true);
        map4test.place(a15);
        assertEquals((map4.toString().equals(map4test.toString())),true);
        map4.place(a16);
        assertEquals((map4.toString().equals(map4test.toString())),true);
        map4.place(a14);
        assertEquals((map4.toString().equals(map4test.toString())),false);

        assertEquals((map5.toString().equals(map5test.toString())),true);
        map5.place(a17);
        assertEquals((map5.toString().equals(map5test.toString())),false);
        map5.place(a18);
        map5test.place(a18);
        assertEquals((map5.toString().equals(map5test.toString())),true);
        map5.place(a19);
        map5test.place(a20);
        assertEquals((map5.toString().equals(map5test.toString())),true);
    }
}