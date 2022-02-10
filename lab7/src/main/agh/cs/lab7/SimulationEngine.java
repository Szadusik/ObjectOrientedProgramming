package agh.cs.lab7;

import agh.cs.oop.IEngine;
import agh.cs.oop.IPositionChangeObserver;
import agh.cs.oop.IWorldMap;

import java.util.*;

class SimulationEngine implements IEngine {
    private MoveDirection[] moves;
    private IWorldMap map;
    private List<animal> animals = new ArrayList<>();
    public SimulationEngine(MoveDirection[] moves,IWorldMap map,Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        for(Vector2d pos : positions){
            animal a = new animal(this.map,pos);
            this.map.place(a);
            this.animals.add(a);
        }
    }
    public void run(){
        Vector2d previous;
        animal p;
        System.out.println("Pozycje startowe zwierząt na mapie");
        System.out.println(this.map.toString());
        int n = this.moves.length;
        int k = this.animals.size();
        for(animal a : this.animals)
            a.addObserver((IPositionChangeObserver) this.map);
        for(int i=0; i<n;i++){
            p = this.animals.get(i%k);
            previous = p.getPosition();
            p.move(this.moves[i]);
            p.positionChanged(previous,p.getPosition());
        }
        for(animal a : this.animals)
            a.removeObserver((IPositionChangeObserver) this.map);
        System.out.println("Pozycje końcowe zwierząt na mapie po wykonaniu ruchów");
        System.out.println(this.map.toString());
    }
}


