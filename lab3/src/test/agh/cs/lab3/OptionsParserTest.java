package agh.cs.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest extends OptionsParser {

    @Test
    void testParse() {
        //Testy sprawdzające poprawność konsertowania tablicy łańcuchów na tablicę wartości MoveDirection
        OptionsParser s = new OptionsParser();
        MoveDirection[] p;

        p = s.parse(new String[] {});
        assertArrayEquals(p,new MoveDirection[] {});

        p = s.parse(new String[] {"f","forward","b","backward","l","left","r","right"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.FORWARD,
                MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,
                MoveDirection.LEFT,MoveDirection.RIGHT,MoveDirection.RIGHT});

        p = s.parse(new String[] {"a","c","d","e","f","g","h","i"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD});

        p = s.parse(new String[] {"f","f0rWaRd","b","Backward","L","left","R","RIGHT"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT});

        p = s.parse(new String[] {"ax","lol","b","backward","1","43243","xfgfdg","right"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.BACKWARD,MoveDirection.BACKWARD, MoveDirection.RIGHT});

        p = s.parse(new String[] {"f","f","f","f","f","F","F","f"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.FORWARD,
                MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD, MoveDirection.FORWARD});

        p = s.parse(new String[] {"Przód","Tył","F","f","r","W lewo","Prawo","right"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.RIGHT});

        p = s.parse(new String[] {">","^","<","v","lewo","prawo","przód","tył"});
        assertArrayEquals(p,new MoveDirection[] {});

        p = s.parse(new String[] {"go","backward","then","right","and","go","forward","there","end"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.BACKWARD,MoveDirection.RIGHT,
                MoveDirection.FORWARD});

        p = s.parse(new String[] {"r","i","g","h","t"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.RIGHT});

        p = s.parse(new String[] {"g","o","forward"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD});

        p = s.parse(new String[] {"f","b","to","r","l"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.BACKWARD,
                MoveDirection.RIGHT,MoveDirection.LEFT});

        p = s.parse(new String[] {"l","o","l","x","d"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.LEFT,MoveDirection.LEFT});

        p = s.parse(new String[] {"L","L","R","B","F"});
        assertArrayEquals(p,new MoveDirection[] {});

        p = s.parse(new String[] {"f","FoRwArD","LeFt","RIGHT","tbfsa"});
        assertArrayEquals(p,new MoveDirection[] {MoveDirection.FORWARD});



    }
}