package agh.cs.lab4;

import agh.cs.oop.IWorldMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private List<animal> animals;

    public RectangularMap(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position)
    {
        // Czy mamy wyjście poza mapę
        if(position.x <0|| position.x > this.width || position.y > this.height || position.y <0)
            return false;
        // // Czy pozycja jest zajęta przez zwierzę
        if(isOccupied(position))
            return false;
        else
            return true;
    }
    public boolean place(animal animal)
    {
        if(animal.getPosition().x > this.width || animal.getPosition().x < 0 || animal.getPosition().y > this.height || animal.getPosition().y < 0)
            return false;

        for(animal a : this.animals)
        {
            if(a.getPosition().toString().equals(animal.getPosition().toString()))
                return false;
        }
        this.animals.add(animal);
        return true;
    }

    public boolean isOccupied(Vector2d position)
    {
        for(animal a : this.animals){
            if(a.getPosition().toString().equals(position.toString()))
                return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position)
    {
        for(animal a : this.animals)
        {
            if(a.getPosition().toString().equals(position.toString()))
                return a;
        }
        return null;
    }

    public String toString(){
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(this.width,this.height));
    }
}
