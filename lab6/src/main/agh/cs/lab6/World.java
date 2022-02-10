package agh.cs.lab6;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;
import java.util.HashMap;

public class World {
    public static void main(String[] args){
        // Jest to nowsza wersja programu lab6 dodatkowo z zaimplementowanym mechanizmem wyjątków ze starej wersji lab6
        try{
        String[] arg = {"f" ,"b" ,"r", "l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(arg);
        IWorldMap map = new GrassField(10,new HashMap<>());
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        }
        catch (IllegalArgumentException ex){
            System.out.println("Coś poszło nie tak");
        }

    }
}
