package agh.cs.lab7;

import agh.cs.oop.IPositionChangeObserver;
import agh.cs.oop.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class animalTest{
    //Testowe zwierzaki oraz mapa, na której się znajdują
    IWorldMap map = new RectangularMap(10,5,new HashMap<>());
    animal p1 = new animal(map,new Vector2d(3,4));
    animal p2 = new animal(map,new Vector2d(0,3));
    animal p3 = new animal(map,new Vector2d(0,4));
    animal p4 = new animal(map,new Vector2d(0,0));
    animal p5 = new animal(map,new Vector2d(0,5));
    animal p6 = new animal(map,new Vector2d(10,0));
    animal p7 = new animal(map,new Vector2d(10,5));
    @Test
    void testToString() {
        //Poprawność wypisywania orientacji zwierza
        assertEquals(p1.toString(),"N");
        p1.move(MoveDirection.RIGHT);
        assertEquals(p1.toString(),"E");
        p1.move(MoveDirection.RIGHT);
        assertEquals(p1.toString(),"S");
        p1.move(MoveDirection.RIGHT);
        assertEquals(p1.toString(),"W");
        p1.move(MoveDirection.RIGHT);
        assertEquals(p1.toString(),"N");
    }

    @Test
    void testGetPosition() {
        //Poprawność wypisywania pozycji zadanego zwierza
        assertEquals(p1.getPosition().toString(),new Vector2d(3,4).toString());
        assertEquals(p2.getPosition().toString(),new Vector2d(0,3).toString());
        assertEquals(p3.getPosition().toString(),new Vector2d(0,4).toString());
        assertEquals(p4.getPosition().toString(),new Vector2d(0,0).toString());
        assertEquals(p5.getPosition().toString(),new Vector2d(0,5).toString());
        assertEquals(p6.getPosition().toString(),new Vector2d(10,0).toString());
        assertEquals(p7.getPosition().toString(),new Vector2d(10,5).toString());
    }

    @Test
    void testMove(){
        /*
        Testy sprawdzające, czy zwierzaki poprawnie się poruszają czyli czy wchodzą na właściwe pozycje,
        czy nie wychodzą poza mapę oraz czy nie wchodzą na siebie.
         */
        map.place(p1);
        map.place(p2);
        map.place(p3);
        map.place(p4);
        map.place(p5);
        map.place(p6);
        map.place(p7);
        IPositionChangeObserver m = (IPositionChangeObserver) map;
        p1.addObserver(m);
        p2.addObserver(m);
        p3.addObserver(m);
        p4.addObserver(m);
        p5.addObserver(m);
        p6.addObserver(m);
        p7.addObserver(m);
        Vector2d a = p1.getPosition();


        p1.move(MoveDirection.FORWARD);
        assertEquals(p1.getPosition().toString() + p1.toString(),new Vector2d(3,5).toString() + "N");
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.BACKWARD);
        assertEquals(p1.getPosition().toString() + p1.toString(),new Vector2d(3,4).toString() + "N");
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.RIGHT);
        p1.move(MoveDirection.FORWARD);
        assertEquals(p1.getPosition().toString() + p1.toString(),new Vector2d(4,4).toString() + "E");
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.BACKWARD);
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.RIGHT);
        p1.move(MoveDirection.FORWARD);
        assertEquals(p1.getPosition().toString() + p1.toString(),new Vector2d(3,3).toString() + "S");
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.BACKWARD);
        p1.positionChanged(a,p1.getPosition());

        a = p1.getPosition();
        p1.move(MoveDirection.RIGHT);
        p1.move(MoveDirection.FORWARD);
        assertEquals(p1.getPosition().toString() + p1.toString(),new Vector2d(2,4).toString() + "W");
        p1.positionChanged(a,p1.getPosition());
        // pozycja p1 = (2,4)

        a = p2.getPosition();
        p2.move(MoveDirection.FORWARD);
        assertEquals(p2.getPosition().toString() + p2.toString(),new Vector2d(0,3).toString() + "N");
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.BACKWARD);
        assertEquals(p2.getPosition().toString() + p2.toString(),new Vector2d(0,2).toString() + "N");
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.RIGHT);
        p2.move(MoveDirection.FORWARD);
        assertEquals(p2.getPosition().toString() + p2.toString(),new Vector2d(1,2).toString() + "E");
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.BACKWARD);
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.RIGHT);
        p2.move(MoveDirection.FORWARD);
        assertEquals(p2.getPosition().toString() + p2.toString(),new Vector2d(0,1).toString() + "S");
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.BACKWARD);
        p2.positionChanged(a,p2.getPosition());

        a = p2.getPosition();
        p2.move(MoveDirection.RIGHT);
        p2.move(MoveDirection.FORWARD);
        assertEquals(p2.getPosition().toString() + p2.toString(),new Vector2d(0,2).toString() + "W");
        p2.positionChanged(a,p2.getPosition());
        //pozycja p2 = (0,2)

        a = p3.getPosition();
        p3.move(MoveDirection.FORWARD);
        assertEquals(p3.getPosition().toString() + p3.toString(),new Vector2d(0,4).toString() + "N");
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.BACKWARD);
        assertEquals(p3.getPosition().toString() + p3.toString(),new Vector2d(0,3).toString() + "N");
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.RIGHT);
        p3.move(MoveDirection.FORWARD);
        assertEquals(p3.getPosition().toString() + p3.toString(),new Vector2d(1,3).toString() + "E");
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.BACKWARD);
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.RIGHT);
        p3.move(MoveDirection.FORWARD);
        assertEquals(p3.getPosition().toString() + p3.toString(),new Vector2d(0,3).toString() + "S");
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.BACKWARD);
        p3.positionChanged(a,p3.getPosition());

        a = p3.getPosition();
        p3.move(MoveDirection.RIGHT);
        p3.move(MoveDirection.FORWARD);
        assertEquals(p3.getPosition().toString() + p3.toString(),new Vector2d(0,4).toString() + "W");
        p3.positionChanged(a,p3.getPosition());
        // pozycja p3 = (0,4)

        a = p4.getPosition();
        p4.move(MoveDirection.FORWARD);
        assertEquals(p4.getPosition().toString() + p4.toString(),new Vector2d(0,1).toString() + "N");
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.BACKWARD);
        assertEquals(p4.getPosition().toString() + p4.toString(),new Vector2d(0,0).toString() + "N");
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.RIGHT);
        p4.move(MoveDirection.FORWARD);
        assertEquals(p4.getPosition().toString() + p4.toString(),new Vector2d(1,0).toString() + "E");
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.BACKWARD);
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.RIGHT);
        p4.move(MoveDirection.FORWARD);
        assertEquals(p4.getPosition().toString() + p4.toString(),new Vector2d(0,0).toString() + "S");
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.BACKWARD);
        p4.positionChanged(a,p4.getPosition());

        a = p4.getPosition();
        p4.move(MoveDirection.RIGHT);
        p4.move(MoveDirection.FORWARD);
        assertEquals(p4.getPosition().toString() + p4.toString(),new Vector2d(0,1).toString() + "W");
        p4.positionChanged(a,p4.getPosition());

        // pozycja p4 = (0,1)
        a = p5.getPosition();
        p5.move(MoveDirection.FORWARD);
        assertEquals(p5.getPosition().toString() + p5.toString(),new Vector2d(0,5).toString() + "N");
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.BACKWARD);
        assertEquals(p5.getPosition().toString() + p5.toString(),new Vector2d(0,5).toString() + "N");
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.RIGHT);
        p5.move(MoveDirection.FORWARD);
        assertEquals(p5.getPosition().toString() + p5.toString(),new Vector2d(1,5).toString() + "E");
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.BACKWARD);
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.RIGHT);
        p5.move(MoveDirection.FORWARD);
        assertEquals(p5.getPosition().toString() + p5.toString(),new Vector2d(0,5).toString() + "S");
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.BACKWARD);
        p5.positionChanged(a,p5.getPosition());

        a = p5.getPosition();
        p5.move(MoveDirection.RIGHT);
        p5.move(MoveDirection.FORWARD);
        assertEquals(p5.getPosition().toString() + p5.toString(),new Vector2d(0,5).toString() + "W");
        p5.positionChanged(a,p5.getPosition());
        // pozycja p5 = (0,5)
        a = p6.getPosition();
        p6.move(MoveDirection.FORWARD);
        assertEquals(p6.getPosition().toString() + p6.toString(),new Vector2d(10,1).toString() + "N");
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.BACKWARD);
        assertEquals(p6.getPosition().toString() + p6.toString(),new Vector2d(10,0).toString() + "N");
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.RIGHT);
        p6.move(MoveDirection.FORWARD);
        assertEquals(p6.getPosition().toString() + p6.toString(),new Vector2d(10,0).toString() + "E");
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.BACKWARD);
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.RIGHT);
        p6.move(MoveDirection.FORWARD);
        assertEquals(p6.getPosition().toString() + p6.toString(),new Vector2d(9,0).toString() + "S");
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.BACKWARD);
        p6.positionChanged(a,p6.getPosition());

        a = p6.getPosition();
        p6.move(MoveDirection.RIGHT);
        p6.move(MoveDirection.FORWARD);
        assertEquals(p6.getPosition().toString() + p6.toString(),new Vector2d(8,1).toString() + "W");
        p6.positionChanged(a,p6.getPosition());
        // pozycja p6 = (8,1)
        a = p7.getPosition();
        p7.move(MoveDirection.FORWARD);
        assertEquals(p7.getPosition().toString() + p7.toString(),new Vector2d(10,5).toString() + "N");
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.BACKWARD);
        assertEquals(p7.getPosition().toString() + p7.toString(),new Vector2d(10,4).toString() + "N");
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.RIGHT);
        p7.move(MoveDirection.FORWARD);
        assertEquals(p7.getPosition().toString() + p7.toString(),new Vector2d(10,4).toString() + "E");
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.BACKWARD);
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.RIGHT);
        p7.move(MoveDirection.FORWARD);
        assertEquals(p7.getPosition().toString() + p7.toString(),new Vector2d(9,3).toString() + "S");
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.BACKWARD);
        p7.positionChanged(a,p7.getPosition());

        a = p7.getPosition();
        p7.move(MoveDirection.RIGHT);
        p7.move(MoveDirection.FORWARD);
        assertEquals(p7.getPosition().toString() + p7.toString(),new Vector2d(8,4).toString() + "W");
        p7.positionChanged(a,p7.getPosition());
        // pozycja p7 = (8,4)




    }
}