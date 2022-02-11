package Objects;

import Enums.MoveDirection;
import Interfaces.IMapElement;
import Interfaces.IPositionChangeObserver;

import java.awt.*;

public class Grass implements IMapElement{
    protected Vector2d position;
    public Grass(Vector2d pos){
        this.position = pos;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }

    public boolean canBeMoved(){
        return false;
    }

    public void move(MoveDirection direction){

    }

    public void addObserver(IPositionChangeObserver observer){

    }

    @Override
    public Color toColor() {
        return new Color(203, 222, 31, 252);
    }

}
