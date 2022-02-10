package agh.cs.lab4;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;

public class World {
    public static void main(String[] args){
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        /* Punkt 3 polecenie 3 czyli zastanowienie się nad bezparametrowym konstruktorem.
        Konstruktor bez parametrowów jest wywoływany, kiedy sami nie zdefiniujemy konstruktora,
        ale można go samemu stworzyć w samej klasie. Może on być stosowany w przypadkach, kiedy
        dane pola klasy są zdefiniowane jako pewne stałe wartości albo chcemy im przypisać jakąś
        wartość defaultową i nie potrzebujemy w klasie dodatkowych parametrów.
         */
    }
}
