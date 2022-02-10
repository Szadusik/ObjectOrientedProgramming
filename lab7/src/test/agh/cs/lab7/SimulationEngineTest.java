package agh.cs.lab7;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    MoveDirection[] moves1 = new OptionsParser().parse(new String[] {"f","b","r","l"});
    IWorldMap map1 = new RectangularMap(6,4,new HashMap<>());
    Vector2d[] positions1 = {new Vector2d(3,3), new Vector2d(4,4)};
    IEngine engine1 = new SimulationEngine(moves1,map1,positions1);

    MoveDirection[] moves2 = new OptionsParser().parse(new String[] {"f","b","r","l","f","b","l","r","b","b"});
    IWorldMap map2 = new RectangularMap(5,3,new HashMap<>());
    Vector2d[] positions2 = {new Vector2d(2,3),
            new Vector2d(4,1),new Vector2d(1,1), new Vector2d(5,3)};
    IEngine engine2 = new SimulationEngine(moves2,map2,positions2);

    MoveDirection[] moves3 = new OptionsParser().parse(new String[] {"f","f","b","r","l","r","r"});
    IWorldMap map3 = new RectangularMap(4,8,new HashMap<>());
    Vector2d[] positions3 = {new Vector2d(2,2),
            new Vector2d(1,7),new Vector2d(3,6)};
    IEngine engine3 = new SimulationEngine(moves3,map3,positions3);

    MoveDirection[] moves4 = new OptionsParser().parse(new String[] {"f","f","b","b","b","b","r","r","b"});
    IWorldMap map4 = new RectangularMap(7,9,new HashMap<>());
    Vector2d[] positions4 = {new Vector2d(6,8)};
    IEngine engine4 = new SimulationEngine(moves4,map4,positions4);

    MoveDirection[] moves5 = new OptionsParser().parse(new String[] {"f","f","f","r","l","r","l","r","f","f"});
    IWorldMap map5 = new RectangularMap(7,10,new HashMap<>());
    Vector2d[] positions5 = {new Vector2d(2,3),
            new Vector2d(1,1),new Vector2d(6,5),
            new Vector2d(2,5), new Vector2d(7,7)};
    IEngine engine5 = new SimulationEngine(moves5,map5,positions5);

    MoveDirection[] moves6 = new OptionsParser().parse(new String[] {"r","f","f","f"});
    IWorldMap map6 = new RectangularMap(5,5,new HashMap<>());
    Vector2d[] positions6 = {new Vector2d(2,3),
            new Vector2d(2,2)};
    IEngine engine6 = new SimulationEngine(moves6,map6,positions6);
    @Test
    void testRun(){
        //Mapy testowe testujące poprawne położenie i orientację końcową zwierząt
        //Sprawdzane tym, czy wyjściowe wizualizacje map są takie same
        IWorldMap map1test = new RectangularMap(6,4,new HashMap<>());
        IWorldMap map2test = new RectangularMap(5,3,new HashMap<>());
        IWorldMap map3test = new RectangularMap(4,8,new HashMap<>());
        IWorldMap map4test = new RectangularMap(7,9,new HashMap<>());
        IWorldMap map5test = new RectangularMap(7,10,new HashMap<>());
        IWorldMap map6test = new RectangularMap(5,5,new HashMap<>());

        engine1.run();
        assertFalse(map1.isOccupied(new Vector2d(3, 3)));
        assertFalse(map1.isOccupied(new Vector2d(4, 4)));
        assertTrue(map1.isOccupied(new Vector2d(3, 4)));
        assertTrue(map1.isOccupied(new Vector2d(4, 3)));
        animal a1 = new animal(map1test, new Vector2d(3,4));
        animal a2 = new animal(map1test, new Vector2d(4,3));
        a1.move(MoveDirection.RIGHT);
        a2.move(MoveDirection.LEFT);
        map1test.place(a1);
        map1test.place(a2);
        assertEquals(map1.toString(),map1test.toString());

        engine2.run();
        assertFalse(map2.isOccupied(new Vector2d(2, 3)));
        assertFalse(map2.isOccupied(new Vector2d(4, 1)));
        assertTrue(map2.isOccupied(new Vector2d(1, 1)));
        assertTrue(map2.isOccupied(new Vector2d(5, 3)));

        assertTrue(map2.isOccupied(new Vector2d(2, 2)));
        assertTrue(map2.isOccupied(new Vector2d(4, 0)));
        assertFalse(map2.isOccupied(new Vector2d(1, 2)));
        assertFalse(map2.isOccupied(new Vector2d(0, 0)));
        animal a3 = new animal(map2test,new Vector2d(2,2));
        animal a4 = new animal(map2test,new Vector2d(4,0));
        animal a5 = new animal(map2test,new Vector2d(1,1));
        animal a6 = new animal(map2test,new Vector2d(5,3));
        map2test.place(a3);
        map2test.place(a4);
        map2test.place(a5);
        map2test.place(a6);
        assertEquals(map2.toString(),map2test.toString());

        engine3.run();
        assertFalse(map3.isOccupied(new Vector2d(2, 2)));
        assertFalse(map3.isOccupied(new Vector2d(1, 7)));
        assertFalse(map3.isOccupied(new Vector2d(3, 6)));

        assertTrue(map3.isOccupied(new Vector2d(2, 3)));
        assertTrue(map3.isOccupied(new Vector2d(1, 8)));
        assertTrue(map3.isOccupied(new Vector2d(3, 5)));
        assertFalse(map3.isOccupied(new Vector2d(4, 8)));

        animal a7 = new animal(map3test,new Vector2d(2,3));
        animal a8 = new animal(map3test,new Vector2d(1,8));
        animal a9 = new animal(map3test,new Vector2d(3,5));
        a7.move(MoveDirection.RIGHT);
        a7.move(MoveDirection.RIGHT);
        map3test.place(a7);
        a8.move(MoveDirection.LEFT);
        map3test.place(a8);
        a9.move(MoveDirection.RIGHT);
        map3test.place(a9);
        assertEquals(map3.toString(),map3test.toString());

        engine4.run();
        assertFalse(map4.isOccupied(new Vector2d(6, 8)));
        assertFalse(map4.isOccupied(new Vector2d(6, 9)));
        assertFalse(map4.isOccupied(new Vector2d(6, 0)));
        assertTrue(map4.isOccupied(new Vector2d(6, 6)));

        animal a10 = new animal(map4test,new Vector2d(6,6));
        a10.move(MoveDirection.RIGHT);
        a10.move(MoveDirection.RIGHT);
        map4test.place(a10);
        assertEquals(map4.toString(),map4test.toString());

        engine5.run();
        assertFalse(map5.isOccupied(new Vector2d(2, 3)));
        assertFalse(map5.isOccupied(new Vector2d(1, 1)));
        assertFalse(map5.isOccupied(new Vector2d(6, 5)));
        assertFalse(map5.isOccupied(new Vector2d(2, 5)));
        assertTrue(map5.isOccupied(new Vector2d(6, 6)));

        assertTrue(map5.isOccupied(new Vector2d(2, 4)));
        assertTrue(map5.isOccupied(new Vector2d(1, 2)));
        assertFalse(map5.isOccupied(new Vector2d(6, 3)));
        assertTrue(map5.isOccupied(new Vector2d(3, 5)));
        assertTrue(map5.isOccupied(new Vector2d(6, 7)));

        animal a11 = new animal(map5test,new Vector2d(2,4));
        animal a12 = new animal(map5test,new Vector2d(1,2));
        animal a13 = new animal(map5test,new Vector2d(6,6));
        animal a14 = new animal(map5test,new Vector2d(3,5));
        animal a15 = new animal(map5test,new Vector2d(6,7));
        a11.move(MoveDirection.RIGHT);
        map5test.place(a11);
        a12.move(MoveDirection.LEFT);
        map5test.place(a12);
        a13.move(MoveDirection.RIGHT);
        map5test.place(a13);
        a14.move(MoveDirection.RIGHT);
        map5test.place(a14);
        a15.move(MoveDirection.LEFT);
        map5test.place(a15);
        assertEquals(map5.toString(),map5test.toString());

        engine6.run();
        assertTrue(map6.isOccupied(new Vector2d(2, 3)));
        assertFalse(map6.isOccupied(new Vector2d(2, 2)));
        assertTrue(map6.isOccupied(new Vector2d(3, 3)));
        assertFalse(map6.isOccupied(new Vector2d(0, 0)));

        animal a16 = new animal(map6test,new Vector2d(3,3));
        animal a17 = new animal(map6test,new Vector2d(2,3));
        a16.move(MoveDirection.RIGHT);
        map6test.place(a16);
        map6test.place(a17);
        assertEquals(map6.toString(),map6test.toString());

    }
}