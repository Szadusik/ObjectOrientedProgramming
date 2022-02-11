package Objects;

import Enums.MapDirection;
import Enums.MoveDirection;
import Interfaces.IMapElement;
import Interfaces.IPositionChangeObserver;
import Interfaces.IWorldMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


public class Animal implements IMapElement{
    private MapDirection direction;
    private final IWorldMap map;
    private int energy;
    private final int startEnergy;
    private int dayssurvived;
    private final ArrayList<IPositionChangeObserver> observerlist = new ArrayList<>();
    private int numberofchildren;
    private Genotype genes;
    protected Vector2d position;
    private final ArrayList<Animal> children;
    private Animal father;
    private Animal mother;

    public Animal(IWorldMap map, Vector2d initialPosition, int energy) {
        int choose = (int) (Math.random() * (8));
        this.genes = new Genotype(8,32);
        this.map = map;
        this.position = initialPosition;
        this.energy = energy;
        this.startEnergy = energy;
        this.numberofchildren = 0;
        this.dayssurvived = 0;
        this.children = new ArrayList<>();
        this.father = null;
        this.mother = null;
        //Choose direction
        switch (choose) {
            case 0 -> this.direction = MapDirection.NORTH;
            case 1 -> this.direction = MapDirection.NORTHEAST;
            case 2 -> this.direction = MapDirection.EAST;
            case 3 -> this.direction = MapDirection.SOUTHEAST;
            case 4 -> this.direction = MapDirection.SOUTH;
            case 5 -> this.direction = MapDirection.SOUTHWEST;
            case 6 -> this.direction = MapDirection.WEST;
            case 7 -> this.direction = MapDirection.NORTHWEST;
        }
    }

    public boolean isDead() {
        return this.energy <= 0;
    }
    public void changeEnergy(int value) {
        this.energy +=value;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void move(MoveDirection dir) {
        switch (dir) {
            case LEFT:
                this.direction = this.direction.previous();
                return;
            case RIGHT:
                this.direction = this.direction.next();
                return;
            case FORWARD:
                if (map.canMoveTo(position.add(Objects.requireNonNull(direction.toUnitVector())))) {
                    Vector2d old = this.getPosition();
                    this.position = position.add(Objects.requireNonNull(direction.toUnitVector()));
                    this.positionChanged(old, this.position, this);
                }
                return;
            case BACKWARD:
                if (map.canMoveTo(position.subtract(Objects.requireNonNull(direction.toUnitVector())))) {
                    Vector2d old = this.getPosition();
                    position = position.subtract(Objects.requireNonNull(direction.toUnitVector()));
                    this.positionChanged(old, this.position, this);
                }
        }
    }

    public void rotate() {
        int numOfRotation = genes.returnRandomGen();
        for (int i = 0; i < numOfRotation; i++) {
            this.move(MoveDirection.RIGHT);
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean canBeMoved() {
        return true;
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observerlist.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observerlist.remove(observer);
    }

    private void positionChanged(Vector2d old, Vector2d n, Object a) {
        for (IPositionChangeObserver o : observerlist) {
            o.positionChanged(old, n, a);
        }
    }

    @Override
    public String toString() {
        return energy == 0 ? "Dead" : this.direction.toString();
    }

    //COPULATION
    public void addChildren(Animal child){
        this.children.add(child);
    }

    public void removeChildren(Animal child){
        this.children.remove(child);
    }

    public void setMother(Animal mother){
        this.mother = mother;
    }

    public void setFather(Animal father){
        this.father = father;
    }

    public Animal getMother(){
        return this.mother;
    }

    public Animal getFather(){
        return this.father;
    }

    public ArrayList<Animal> getChildren(){
        return this.children;
    }

    public Animal copulation(Animal another) {
        Animal child;
        int childEnergy;
        childEnergy = (int) (0.25 * another.energy) + (int) (this.energy * 0.25);
        another.changeEnergy((int) -(0.25 * another.energy));
        this.changeEnergy((int) -(this.energy * 0.25));

        child = new Animal(map, another.getPosition(), childEnergy);
        child.genes = new Genotype(this.genes, another.genes);

        this.addChildren(child);
        another.addChildren(child);
        child.setFather(this);
        child.setMother(another);
        this.numberofchildren++;
        return child;
    }

    public Genotype getAnimalGenes(){
        return this.genes;
    }

    public int getNumberofchildren(){
        return this.numberofchildren;
    }

    public int getEnergy(){
        return this.energy;
    }

    public int getDayssurvived(){
        return this.dayssurvived;
    }

    public void modifyDayssurvived(){
       this.dayssurvived++;
    }

    public int getDominantFromGenotype(){
        return this.genes.getDominantGene();
    }


    @Override
    public Color toColor() {
        if (energy == 0) return new Color(222, 221, 224);
        if (energy < 0.2 * startEnergy) return new Color(224, 179, 173);
        if (energy < 0.4 * startEnergy) return new Color(224, 142, 127);
        if (energy < 0.6 * startEnergy) return new Color(201, 124, 110);
        if (energy < 0.8 * startEnergy) return new Color(182, 105, 91);
        if (energy < startEnergy) return new Color(164, 92, 82);
        if (energy < 2 * startEnergy) return new Color(146, 82, 73);
        if (energy < 4 * startEnergy) return new Color(128, 72, 64);
        if (energy < 6 * startEnergy) return new Color(119, 67, 59);
        if (energy < 8 * startEnergy) return new Color(88, 50, 44);
        if (energy < 10 * startEnergy) return new Color(74, 42, 37);
        return new Color(55, 31, 27);
    }

}
