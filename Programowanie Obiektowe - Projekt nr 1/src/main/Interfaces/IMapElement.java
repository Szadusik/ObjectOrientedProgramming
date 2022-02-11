package Interfaces;

import Enums.MoveDirection;
import Objects.Vector2d;

import java.awt.*;

public interface IMapElement {
    /**
     * standard 2D position of element
     * position param should const every class implements IMapElement
     */
    Vector2d getPosition();


    /**
     * Its
     *
     * @return true if elements will use move function to move, else should return false.
     */
    boolean canBeMoved();


    /**
     * If element is not movable you should implement empty move function.
     *
     * @param d to specialize direction to move
     */
    void move(MoveDirection d);

    void addObserver(IPositionChangeObserver observer);

    Color toColor();

}