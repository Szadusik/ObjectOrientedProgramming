package agh.cs.lab5;

import agh.cs.oop.IEngine;
import agh.cs.oop.IWorldMap;

import java.util.ArrayList;


public class World {
    public static void main(String[] args){
        String[] arg = {"f" ,"b" ,"r", "l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionsParser().parse(arg);
        IWorldMap map = new GrassField(10,new ArrayList<>());
        //Vector2d a = new Vector2d(1,1);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
       /*
       Zadanie 9
       Można byłoby rozważyć zaimplementowanie takiego interfejsu IMapElement, który
       zawierałby metody toString oraz getPosition zwracające odpowiednio graficzną reprezentację klasy
       oraz pozycje obiektu na mapie, ponieważ są one obie wykorzystywane
       przez klasy Grass oraz animal. Metoda move klasy animal nie należałaby do interfejsu również ze względu na to,
       że klasa Grass nie potrzebuje w żaden sytuacji metody move do przemieszczania się po mapie. Zatem
       interfejs IMapElement mógłby być przydatny w celu otrzymywania informacji o obiektach takich jak jego pozycja czy jego prezentacja graficzna.
        */
         /*
       Zadanie 10
       Dodanie AbstractWorldMapElement nie byłoby aż tak wymagane, ponieważ jedynie metody getPosition dla klas Grass oraz animal
       mają wspólny kod, gdzie metoda getPosition składa się jedynie ze zwrócenia wektora oznaczającego pozycję zwierzęca. Zatem
       dodanie wyżej wymienionej klasy abstrakcyjnej nie jest potrzebne przy tak małej ilości wspólnego kodu między klasami
       Grass oraz animal.
        */
    }
}
