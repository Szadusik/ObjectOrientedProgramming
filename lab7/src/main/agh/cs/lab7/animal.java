package agh.cs.lab7;

import agh.cs.oop.IPositionChangeObserver;
import agh.cs.oop.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class animal {
    private MapDirection orientation;
    private Vector2d initialPosition;
    private List<IPositionChangeObserver> observers;
    private IWorldMap map;

    public animal (IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.initialPosition = initialPosition;
        this.orientation = MapDirection.NORTH;
        this.observers = new ArrayList<>();
    }
    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.observers.forEach(observer -> observer.positionChanged(oldPosition,newPosition));
    }
    public String toString(){
        String t = "";
        switch(orientation)
        {
            case EAST:t = "E";
                break;
            case NORTH:t = "N";
                break;
            case SOUTH:t = "S";
                break;
            case WEST:t = "W";
                break;
        }
        return (t);
    }

    public Vector2d getPosition(){
        return this.initialPosition;
    }

    public animal move(MoveDirection direction)
    {
        Vector2d a = new Vector2d(this.initialPosition.x,this.initialPosition.y);
        if(direction.equals(MoveDirection.LEFT))
            this.orientation = this.orientation.previous();
        else if(direction.equals(MoveDirection.RIGHT))
            this.orientation = this.orientation.next();
        else if(direction.equals(MoveDirection.FORWARD))
        {
            switch(this.orientation){
                case EAST:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (1,0))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (1,0));
                    break;
                case WEST:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (-1,0))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (-1,0));
                    break;
                case NORTH:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (0,1))))
                        //System.out.println(map.isOccupied(this.initialPosition.add(new Vector2d (0,1))));
                        this.initialPosition = this.initialPosition.add(new Vector2d (0,1));
                    break;
                case SOUTH:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (0,-1))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (0,-1));
                    break;
                default:
                    break;
            }
        }
        else
        {
            switch(this.orientation){
                case EAST:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (-1,0))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (-1,0));
                    break;
                case WEST:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (1,0))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (1,0));
                    break;
                case NORTH:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (0,-1))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (0,-1));
                    break;
                case SOUTH:
                    if(map.canMoveTo(this.initialPosition.add(new Vector2d (0,1))))
                        this.initialPosition = this.initialPosition.add(new Vector2d (0,1));
                    break;
                default:
                    break;

            }
        }
        return this;
    }
}
