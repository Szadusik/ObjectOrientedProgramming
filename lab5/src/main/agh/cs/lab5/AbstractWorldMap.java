package agh.cs.lab5;

import agh.cs.oop.IWorldMap;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<animal> animals;

    public AbstractWorldMap(List<animal> animals) {
        this.animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position){
        if(position.x <0 || position.y <0)
            return false;

        for(animal a: this.animals){
            if (a.getPosition().toString().equals(position.toString()))
                return false;
        }
        return true;
    }

    public boolean place(animal animal){
        if(animal.getPosition().x <0 || animal.getPosition().y <0)
            return false;

        for(animal a : this.animals)
        {
            if(a.getPosition().toString().equals(animal.getPosition().toString()))
                return false;
        }
        this.animals.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position){
        for(animal a : this.animals){
            if(a.getPosition().toString().equals(position.toString()))
                return true;
        }
        return false;
    }


    public Object objectAt(Vector2d position){
        for(animal a : this.animals)
        {
            if(a.getPosition().toString().equals(position.toString()))
                return a;
        }
        return null;
    }
    public String toString(){
        int maxx=0,maxy=0;
        for(animal a : this.animals){
            Vector2d pos = a.getPosition();
            if(pos.x > maxx)
                maxx = pos.x;
            if(pos.y > maxy)
                maxy = pos.y;
        }
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(maxx,maxy));
    }
}
