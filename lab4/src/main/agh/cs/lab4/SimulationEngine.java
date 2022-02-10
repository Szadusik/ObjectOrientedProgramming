package agh.cs.lab4;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
        System.out.println("Pozycje startowe zwierząt na mapie");
        System.out.println(this.map.toString());
        int n = this.moves.length;
        int k = this.animals.size();
        animal p;
        for(int i=0; i<n;i++){
            p = this.animals.get(i%k);
            p.move(this.moves[i]);
        }
        System.out.println("Pozycje końcowe zwierząt na mapie po wykonaniu ruchów");
        System.out.println(this.map.toString());
    }
}


