package agh.cs.lab4;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    MoveDirection[] moves1 = new OptionsParser().parse(new String[] {"f","b","r","l"});
    IWorldMap map1 = new RectangularMap(6,4);
    Vector2d[] positions1 = {new Vector2d(3,3), new Vector2d(4,4)};
    IEngine engine1 = new SimulationEngine(moves1,map1,positions1);

    MoveDirection[] moves2 = new OptionsParser().parse(new String[] {"f","b","r","l","f","b","l","r","b","b"});
    IWorldMap map2 = new RectangularMap(5,3);
    Vector2d[] positions2 = {new Vector2d(2,3),
            new Vector2d(4,1),new Vector2d(1,1), new Vector2d(5,3)};
    IEngine engine2 = new SimulationEngine(moves2,map2,positions2);

    MoveDirection[] moves3 = new OptionsParser().parse(new String[] {"f","f","b","r","l","r","t","a","r"});
    IWorldMap map3 = new RectangularMap(4,8);
    Vector2d[] positions3 = {new Vector2d(2,2),
            new Vector2d(1,7),new Vector2d(3,6)};
    IEngine engine3 = new SimulationEngine(moves3,map3,positions3);

    MoveDirection[] moves4 = new OptionsParser().parse(new String[] {"f","f","n","b","b","b","b","r","r","b","x","dx"});
    IWorldMap map4 = new RectangularMap(7,9);
    Vector2d[] positions4 = {new Vector2d(6,8)};
    IEngine engine4 = new SimulationEngine(moves4,map4,positions4);

    MoveDirection[] moves5 = new OptionsParser().parse(new String[] {"f","f","f","r","l","r","l","r","f","f"});
    IWorldMap map5 = new RectangularMap(7,10);
    Vector2d[] positions5 = {new Vector2d(2,3),
            new Vector2d(1,1),new Vector2d(6,5),
            new Vector2d(2,5), new Vector2d(7,7)};
    IEngine engine5 = new SimulationEngine(moves5,map5,positions5);

    MoveDirection[] moves6 = new OptionsParser().parse(new String[] {"r","f","f","f"});
    IWorldMap map6 = new RectangularMap(5,5);
    Vector2d[] positions6 = {new Vector2d(2,3),
            new Vector2d(2,2)};
    IEngine engine6 = new SimulationEngine(moves6,map6,positions6);
    @Test
    void testRun(){
        //Mapy testowe testujące poprawne położenie i orientację końcową zwierząt
        //Sprawdzane tym, czy wyjściowe wizualizacje map są takie same
        IWorldMap map1test = new RectangularMap(6,4);
        IWorldMap map2test = new RectangularMap(5,3);
        IWorldMap map3test = new RectangularMap(4,8);
        IWorldMap map4test = new RectangularMap(7,9);
        IWorldMap map5test = new RectangularMap(7,10);
        IWorldMap map6test = new RectangularMap(5,5);

        engine1.run();
        assertEquals(map1.isOccupied(new Vector2d(3,3)),false);
        assertEquals(map1.isOccupied(new Vector2d(4,4)),false);
        assertEquals(map1.isOccupied(new Vector2d(3,4)),true);
        assertEquals(map1.isOccupied(new Vector2d(4,3)),true);
        animal a1 = new animal(map1test, new Vector2d(3,4));
        animal a2 = new animal(map1test, new Vector2d(4,3));
        a1.move(MoveDirection.RIGHT);
        a2.move(MoveDirection.LEFT);
        map1test.place(a1);
        map1test.place(a2);
        assertEquals(map1.toString(),map1test.toString());

        engine2.run();
        assertEquals(map2.isOccupied(new Vector2d(2,3)),false);
        assertEquals(map2.isOccupied(new Vector2d(4,1)),false);
        assertEquals(map2.isOccupied(new Vector2d(1,1)),true);
        assertEquals(map2.isOccupied(new Vector2d(5,3)),true);

        assertEquals(map2.isOccupied(new Vector2d(2,2)),true);
        assertEquals(map2.isOccupied(new Vector2d(4,0)),true);
        assertEquals(map2.isOccupied(new Vector2d(1,2)),false);
        assertEquals(map2.isOccupied(new Vector2d(0,0)),false);
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
        assertEquals(map3.isOccupied(new Vector2d(2,2)),false);
        assertEquals(map3.isOccupied(new Vector2d(1,7)),false);
        assertEquals(map3.isOccupied(new Vector2d(3,6)),false);

        assertEquals(map3.isOccupied(new Vector2d(2,3)),true);
        assertEquals(map3.isOccupied(new Vector2d(1,8)),true);
        assertEquals(map3.isOccupied(new Vector2d(3,5)),true);
        assertEquals(map3.isOccupied(new Vector2d(4,8)),false);

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
        assertEquals(map4.isOccupied(new Vector2d(6,8)),false);
        assertEquals(map4.isOccupied(new Vector2d(6,9)),false);
        assertEquals(map4.isOccupied(new Vector2d(6,0)),false);
        assertEquals(map4.isOccupied(new Vector2d(6,6)),true);

        animal a10 = new animal(map4test,new Vector2d(6,6));
        a10.move(MoveDirection.RIGHT);
        a10.move(MoveDirection.RIGHT);
        map4test.place(a10);
        assertEquals(map4.toString(),map4test.toString());

        engine5.run();
        assertEquals(map5.isOccupied(new Vector2d(2,3)),false);
        assertEquals(map5.isOccupied(new Vector2d(1,1)),false);
        assertEquals(map5.isOccupied(new Vector2d(6,5)),false);
        assertEquals(map5.isOccupied(new Vector2d(2,5)),false);
        assertEquals(map5.isOccupied(new Vector2d(6,6)),true);

        assertEquals(map5.isOccupied(new Vector2d(2,4)),true);
        assertEquals(map5.isOccupied(new Vector2d(1,2)),true);
        assertEquals(map5.isOccupied(new Vector2d(6,3)),false);
        assertEquals(map5.isOccupied(new Vector2d(3,5)),true);
        assertEquals(map5.isOccupied(new Vector2d(6,7)),true);

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
        assertEquals(map6.isOccupied(new Vector2d(2,3)),true);
        assertEquals(map6.isOccupied(new Vector2d(2,2)),false);
        assertEquals(map6.isOccupied(new Vector2d(3,3)),true);
        assertEquals(map6.isOccupied(new Vector2d(0,0)),false);

        animal a16 = new animal(map6test,new Vector2d(3,3));
        animal a17 = new animal(map6test,new Vector2d(2,3));
        a16.move(MoveDirection.RIGHT);
        map6test.place(a16);
        map6test.place(a17);
        assertEquals(map6.toString(),map6test.toString());

    }
}