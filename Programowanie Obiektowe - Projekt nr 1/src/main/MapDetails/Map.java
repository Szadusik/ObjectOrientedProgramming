package MapDetails;

import Enums.MoveDirection;
import Interfaces.IMapElement;
import Interfaces.IPositionChangeObserver;
import Interfaces.IWorldMap;
import Objects.Animal;
import Objects.Grass;
import Objects.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Map implements IWorldMap, IPositionChangeObserver{
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final Vector2d lowerLeftJungle;
    private final Vector2d upperRightJungle;
    private final int jungleWidth;
    private final int jungleHeight;
    private final int width;
    private final int height;
    private final int grassProfit;
    private final int dayCost;
    private final int startAnimalsEnergy;
    private final int energyLimitToCopulation;
    private final java.util.Map<Vector2d, Grass> grass;
    private final java.util.Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    private final LinkedList<Animal> animalsList;
    private final LinkedList<Grass> grassList;
    private final LinkedList<Animal> deadAnimalsList;

    public Map(int width, int height, int jungleWidth, int jungleHeight, int grassProfit, int dayCost, int startAnimalsEnergy){
        //Constructor
        this.grass = new HashMap<>();
        this.startAnimalsEnergy = startAnimalsEnergy;
        this.grassList = new LinkedList<>();
        this.animalsList = new LinkedList<>();
        this.energyLimitToCopulation = startAnimalsEnergy/2;
        this.grassProfit = grassProfit;
        this.dayCost = (-1) * dayCost;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.width = width;
        this.height = height;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.deadAnimalsList = new LinkedList<>();

        //default cords of jungle's corners
        int lljx = 0,lljy = 0,urjx = width - 1,urjy = height - 1;

        //calculating jungle's position
        for (int i = 0; i < (width - jungleWidth); i++) {
            if (i % 2 == 0) {
                lljx++;
            } else {
                urjx--;
            }
        }

        for (int i = 0; i < (height - jungleHeight); i++) {
            if (i % 2 == 0) {
                lljy++;
            } else {
                urjy--;
            }
        }
        this.lowerLeftJungle = new Vector2d(lljx, lljy);
        this.upperRightJungle = new Vector2d(urjx, urjy);
    }

    //Get safe position in case animal has passed a border
    public Vector2d toNoBoundedPosition(Vector2d position) {
        int newX,newY;

        if (position.x < this.lowerLeft.x) {
            newX = (this.width - Math.abs(position.x % this.width)) % this.width;
        } else {
            newX = Math.abs(position.x % this.width);
        }
        if (position.y < this.lowerLeft.y) {
            newY = (this.height - Math.abs(position.y % this.height)) % this.height;
        } else {
            newY = Math.abs(position.y % this.height);
        }

        return new Vector2d(newX, newY);
    }

    //Place grass or Animal on map
    public boolean place(IMapElement e) {
        Vector2d position = toNoBoundedPosition(e.getPosition());

        if (!canPlace(position)) {
            throw new IllegalArgumentException("Field " + e.getPosition() + " is full");

        } else {
            if (e instanceof Grass) {
                this.grass.computeIfAbsent(position, k -> (Grass) e);
                this.grassList.add((Grass) e);
            }
            if (e instanceof Animal) {
                addAnimal((Animal) e, position);
                this.animalsList.add((Animal) e);
                e.addObserver(this);
            }
        }
        return true;
    }
    //Remove animal from the map on a current position
    private void removeAnimal(Animal a, Vector2d position2) {
        Vector2d position = toNoBoundedPosition(position2);
        LinkedList<Animal> l = this.animals.get(position);
        if (l == null)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " -> " + position + " already not exist in the map");
        else if (l.size() == 0)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " already not exist in the map empty list");
        else {
            l.remove(a);
            if (l.size() == 0) {
                this.animals.remove(position);
            }
        }
    }

    //Add animal to the map on a current position
    private void addAnimal(Animal a, Vector2d p) {
        if (a == null) return;
        Vector2d pos = toNoBoundedPosition(p);
        LinkedList<Animal> l = this.animals.get(pos);
        if (l == null) {
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(a);
            this.animals.put(pos, tmp);

        } else {
            l.add(a);
        }

    }

    //Getting dominating gen on the map
    public int getDominatingGen(){
        int i,index=0,maxcounter=0,d;
        int[] gens = new int[8];
        for(i=0;i<8;i++)
            gens[i]=0;
        for(Animal a : this.animalsList){
            d = a.getDominantFromGenotype();
            gens[d]++;
        }
        for(i=0;i<gens.length;i++){
            if(gens[i]>maxcounter){
                maxcounter = gens[i];
                index = i;
            }
        }
        return index;
    }
    //Check if some position is occupied
    @Override
    public boolean isOccupied(Vector2d position2) {
        return objectAt(position2) != null;
    }

    //return object which stays on some position. Return null if nothing stays there
    @Override
    public Object objectAt(Vector2d position2) {
        Vector2d position = toNoBoundedPosition(position2);
        LinkedList<Animal> l = animals.get(position);
        if (l == null) return grass.get(position);
        else if (l.size() == 0) return grass.get(position);
        else return l.getFirst();
    }

    //Check if object can be moved to position
    @Override
    public boolean canMoveTo(Vector2d position2) {
        Vector2d position = toNoBoundedPosition(position2);
        if (this.animals.get(position) == null) return true;
        return this.animals.get(position).size() < 2;
    }

    public boolean canPlace(Vector2d position2) {
        //checking place have there 3 animals already. Value for maximum amount of animals/place can be swapped. Default = 3
        Vector2d position = toNoBoundedPosition(position2);
        if (this.animals.get(position) == null) return true;
        return this.animals.get(position).size() < 3;
    }
    //Get average children count between all animals existing on that map
    public float getAverageChildrens(){
        Animal father,mother;
        float average=0;
        if(this.animalsList.size()==0)
            return 0;
        for(Animal a : this.animalsList){
            ArrayList<Animal> children = a.getChildren();
            for(Animal b : children){
                if(!b.isDead()){
                    father = b.getFather();
                    mother = b.getMother();
                    if(father.isDead() || mother.isDead()){
                        average+=1;
                    }
                    else
                        average+=0.5;
                }
            }
        }
        return average/this.animalsList.size();
    }
    //Get average energy of animals existing on map
    public float getAverageEnergy(){
        float average = 0;
        if(this.animalsList.size()==0)
            return 0;
        for(Animal a : this.animalsList){
            average+=a.getEnergy();
        }
        return average/this.animalsList.size();
    }
    //Get average lifestan of dead animals
    public float getAverageLifespanDead(){
        float average = 0;
        if(this.deadAnimalsList.size()==0)
            return 0;
        for(Animal a : this.deadAnimalsList){
            average+=a.getDayssurvived();
        }
        return average/this.deadAnimalsList.size();
    }
    //Function which forces animals to eat grass on fields
    public void eating() {
        LinkedList<Grass> toRemoveAfterEating = new LinkedList<>();
        for (Grass food : this.grass.values()) {
            LinkedList<Animal> l = this.animals.get(food.getPosition());
            if (l != null) {
                if (l.size() > 0) {
                    for (Animal a : l) {
                        a.changeEnergy(this.grassProfit / l.size());
                        toRemoveAfterEating.add(food);
                    }
                }
            }
        }
        for (Grass g : toRemoveAfterEating) {
            this.grass.remove(g.getPosition());
            this.grassList.remove(g);
        }
    }
    //Move animals on random positions
    public void moveRandomAllAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            this.animalsList.get(i).rotate();
            this.animalsList.get(i).move(MoveDirection.FORWARD);
        }
    }
    //Create new animals if some animals are on the same position
    public void copulation() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() == 2) {
                    Animal mother = animalList.get(0);
                    Animal father = animalList.get(1);
                    if (mother.getEnergy() >= this.energyLimitToCopulation)
                        if (father.getEnergy() >= this.energyLimitToCopulation) {
                            Animal child = father.copulation(mother);
                            place(child);
                        }
                }
            }
        }
    }
    //Switch to next day so change energy of animals
    public void nextDay() {
        for (LinkedList<Animal> animalList : this.animals.values()) {
            if (animalList != null) {
                if (animalList.size() > 0) {
                    for (Animal a : animalList) {
                        a.changeEnergy(this.dayCost);
                        a.modifyDayssurvived();
                    }
                }
            }
        }
    }
    //Remove dead animals from map
    public void removeDeadAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            Animal a = this.animalsList.get(i);
            if (a.isDead()) {
                this.deadAnimalsList.add(a);
                removeAnimal(a, a.getPosition());
                a.removeObserver(this);
                this.animalsList.remove(a);
            }
        }
    }
    //Add animal on random field on the map
    public void addAnimalOnRandomField() {
        int toMuchTimes = 0;
        while (toMuchTimes < this.width * this.height * 2) {
            Vector2d position = new Vector2d((int) (Math.random() * (width) + lowerLeft.x), (int) (Math.random() * (height) + lowerLeft.y));
            if (canPlace(position)) {
                place(new Animal(this, position, this.startAnimalsEnergy));
                return;
            }
            toMuchTimes++;
        }
    }

    //Add animal on random field on the map in jungle
    public void placeAnimalToRandomFieldInJungle() {
        int jungleSize = this.jungleWidth * this.jungleHeight;
        int mapSize = this.height * this.width;
        int steppeSize = mapSize - jungleSize;
        int toMuchTimes = 0;
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2d position = new Vector2d((int) (Math.random() * (this.jungleWidth) + this.lowerLeftJungle.x), (int) (Math.random() * (jungleHeight) + lowerLeftJungle.y));
            if (canPlace(position)) {
                place(new Animal(this, position, this.startAnimalsEnergy));
                return;
            }
            toMuchTimes++;
        }
    }
    //Event which is registered by observer
    public boolean positionChanged(Vector2d oldPosition2, Vector2d newPosition2, Object a) {
        Vector2d oldPosition = toNoBoundedPosition(oldPosition2);
        Vector2d newPosition = toNoBoundedPosition(newPosition2);
        if (canMoveTo(newPosition)) {
            removeAnimal((Animal) a, oldPosition);
            addAnimal((Animal) a, newPosition);
            return true;
        }
        return false;
    }
    //Spawn grass in jungle and map
    public void spawnGrass() {
        int jungleSize = this.jungleWidth * this.jungleHeight;
        int mapSize = this.height * this.width;
        int steppeSize = mapSize - jungleSize;
        int toMuchTimes = 0;
        while (toMuchTimes < 2 * jungleSize) {

            Vector2d newGrass = new Vector2d((int) (Math.random() * (this.jungleWidth) + this.lowerLeftJungle.x), (int) (Math.random() * (this.jungleHeight) + this.lowerLeftJungle.y));
            if (this.grass.get(newGrass) == null && canPlace(newGrass)) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }

        toMuchTimes = 0;
        while ((double) toMuchTimes < (double) 2 * ((double) jungleSize / (double) steppeSize) * mapSize) {

            Vector2d newGrass = new Vector2d((int) (Math.random() * (this.width) + this.lowerLeft.x), (int) (Math.random() * (this.height) + this.lowerLeft.y));
            if (this.grass.get(newGrass) == null && canPlace(newGrass) && !(newGrass.follows(this.lowerLeftJungle) && newGrass.precedes(this.upperRightJungle))) {
                place(new Grass(newGrass));
                break;
            }
            toMuchTimes++;
        }
    }
    //Getters and utility

    @Override
    public String toString() {
        return "Map";
    }

    public LinkedList<Animal> getAnimals() {
        return this.animalsList;
    }

    public LinkedList<Grass> getGrass() {
        return this.grassList;
    }

    public LinkedList<Animal> getDeadAnimalsList() {
        return this.deadAnimalsList;
    }

    public Vector2d getJungleLowerLeft() {
        return lowerLeftJungle;
    }

    public int getJungleWidth(){
        return this.jungleWidth;
    }

    public int getJungleHeight(){
        return this.jungleHeight;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

}

