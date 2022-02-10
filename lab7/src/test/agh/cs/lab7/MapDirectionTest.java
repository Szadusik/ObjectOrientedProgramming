package agh.cs.lab7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {
    MapDirection N = MapDirection.NORTH;
    MapDirection S = MapDirection.SOUTH;
    MapDirection W = MapDirection.WEST;
    MapDirection E = MapDirection.EAST;

    @Test
    void testNext() {
        assertEquals(W.next(),N);
        assertEquals(S.next(),W);
        assertEquals(E.next(),S);
        assertEquals(N.next(),E);
    }
    @Test
    void testPrevious() {
        assertEquals(W.previous(),S);
        assertEquals(S.previous(),E);
        assertEquals(E.previous(),N);
        assertEquals(N.previous(),W);
    }

}