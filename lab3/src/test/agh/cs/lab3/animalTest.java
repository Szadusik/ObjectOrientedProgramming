package agh.cs.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class animalTest extends animal{

    @Test
    void testMove()
    {
        animal zwierz = new animal(); //Utworzenie zwierzęcia

        /* Testy sprawdzające, czy zwierzę poprawnie zmienia kierunki oraz czy porusza się
        prawidłowo w każdym kierunku czyli czy poprawnie idzie do przodu, cofa się czy skręca
         */
        assertEquals(zwierz.ToStr(),"(2,2) NORTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(2,3) NORTH");
        zwierz.move(MoveDirection.BACKWARD);
        assertEquals(zwierz.ToStr(),"(2,2) NORTH");

        zwierz.move(MoveDirection.RIGHT);
        assertEquals(zwierz.ToStr(),"(2,2) EAST");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(3,2) EAST");
        zwierz.move(MoveDirection.BACKWARD);
        assertEquals(zwierz.ToStr(),"(2,2) EAST");

        zwierz.move(MoveDirection.RIGHT);
        assertEquals(zwierz.ToStr(),"(2,2) SOUTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(2,1) SOUTH");
        zwierz.move(MoveDirection.BACKWARD);
        assertEquals(zwierz.ToStr(),"(2,2) SOUTH");

        zwierz.move(MoveDirection.RIGHT);
        assertEquals(zwierz.ToStr(),"(2,2) WEST");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(1,2) WEST");
        zwierz.move(MoveDirection.BACKWARD);
        assertEquals(zwierz.ToStr(),"(2,2) WEST");

        zwierz.move(MoveDirection.LEFT);
        assertEquals(zwierz.ToStr(),"(2,2) SOUTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(2,1) SOUTH");
        zwierz.move(MoveDirection.BACKWARD);
        assertEquals(zwierz.ToStr(),"(2,2) SOUTH");

        zwierz.move(MoveDirection.LEFT);
        assertEquals(zwierz.ToStr(),"(2,2) EAST");
        zwierz.move(MoveDirection.LEFT);
        assertEquals(zwierz.ToStr(),"(2,2) NORTH");

        //Sprawdzenie, czy zwierze nie wychodzi poza mapę oraz poprawności ruchów pośrednio

        /*to_rightdown_corner zawiera wartości przesuwające zwierzę z pozycji (2,2)
        na pozycję (4,0)
         */

        MoveDirection[] to_rightdown_corner = {MoveDirection.RIGHT,MoveDirection.FORWARD,
                MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.FORWARD};

        /*to_rightup_corner zawiera wartości przesuwające zwierzę z pozycji (0,4)
        na pozycję (4,4)
         */
        MoveDirection[] to_rightup_corner = {MoveDirection.LEFT,MoveDirection.FORWARD,
                MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD};

        /*toleftup_corner zawiera wartości przesuwające zwierzę z pozycji (4,4)
        na pozycję (0,4)
         */

        MoveDirection[] to_leftup_corner = {MoveDirection.BACKWARD,MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT};

        /*toleftdown_corner zawiera wartości przesuwające zwierzę z pozycji (0,4)
        na pozycję (0,0)
         */

        MoveDirection[] to_leftdown_corner = {MoveDirection.LEFT,MoveDirection.FORWARD,
                MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD};

        for(int i=0; i< to_rightdown_corner.length;i++)
            zwierz.move(to_rightdown_corner[i]);
        //Sprawdzenie, czy zwierze nie wychodzi poza mapę w prawo lub w dół
        assertEquals(zwierz.ToStr(),"(4,0) SOUTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(4,0) SOUTH");
        zwierz.move(MoveDirection.LEFT);
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(4,0) EAST");

        for(int i=0; i< to_rightup_corner.length;i++)
            zwierz.move(to_rightup_corner[i]);
        //Sprawdzenie, czy zwierze nie wychodzi poza mapę w prawo lub w górę
        assertEquals(zwierz.ToStr(),"(4,4) NORTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(4,4) NORTH");
        zwierz.move(MoveDirection.RIGHT);
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(4,4) EAST");

        for(int i=0; i< to_leftup_corner.length;i++)
            zwierz.move(to_leftup_corner[i]);
        //Sprawdzenie, czy zwierze nie wychodzi poza mapę w lewo lub w górę
        assertEquals(zwierz.ToStr(),"(0,4) NORTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(0,4) NORTH");
        zwierz.move(MoveDirection.LEFT);
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(0,4) WEST");

        for(int i=0; i< to_leftdown_corner.length;i++)
            zwierz.move(to_leftdown_corner[i]);
        //Sprawdzenie, czy zwierze nie wychodzi poza mapę w lewo lub w dół
        assertEquals(zwierz.ToStr(),"(0,0) SOUTH");
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(0,0) SOUTH");
        zwierz.move(MoveDirection.RIGHT);
        zwierz.move(MoveDirection.FORWARD);
        assertEquals(zwierz.ToStr(),"(0,0) WEST");
    }
}