package agh.cs.lab7;

import agh.cs.oop.IPositionChangeObserver;
import agh.cs.oop.IWorldMap;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
    /*
    MapBoundary nie jest potrzebne w klasie AbstractWorldMap, ponieważ jedynie klasa GrassField korzysta z
    możliwości wyznaczania granic za pomocą klasy MapBoundary zatem nie potrzebujemy tworzyć w tym miejscu
    instancji klasy MapBoundary
     */
    protected Map<Vector2d,animal> animals;
    public AbstractWorldMap(Map<Vector2d,animal> animals) {
        this.animals = new HashMap<>();
    }

    public boolean canMoveTo(Vector2d position){
        if(position.x <0 || position.y <0)
            return false;

        animal a = this.animals.get(position);
        if(a!=null)
            return false;

        return true;
    }

    public boolean place(animal animal){
        if(animal.getPosition().x <0 || animal.getPosition().y <0)
            throw new IllegalArgumentException(animal.getPosition().toString() + " to pole poza granicami dolnymi mapy");

        animal a = this.animals.get(animal.getPosition());
        if(a!=null)
            throw new IllegalArgumentException(animal.getPosition().toString() + " to pole zajmowane przez zwierzę");

        this.animals.put(animal.getPosition(),animal);
        return true;
    }

    public boolean isOccupied(Vector2d position){
        animal a = this.animals.get(position);
        if(a!=null)
            return true;

        return false;
    }

    public Object objectAt(Vector2d position){
        return this.animals.get(position);
    }

    public String toString(){
        int maxx=0,maxy=0;
        for(animal a : this.animals.values()){
            Vector2d pos = a.getPosition();
            if(pos.x > maxx)
                maxx = pos.x;
            if(pos.y > maxy)
                maxy = pos.y;
        }
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(maxx,maxy));
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        animal a = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition,a);
    }
}
