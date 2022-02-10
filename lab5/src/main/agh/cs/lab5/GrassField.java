package agh.cs.lab5;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class GrassField extends AbstractWorldMap {
    Random rand = new Random();
    private List<grass> grasses = new ArrayList<>();
    private final int n;
    public GrassField(int n,List<animal> animals){
        super(animals);
        int i;
        int border = (int) Math.sqrt(10*n);
        this.n = n;
        for(i=0; i<n;i++){
            Vector2d p = new Vector2d(rand.nextInt(border+1),rand.nextInt(border+1));
            while(isOccupied(p)){
                p = new Vector2d(rand.nextInt(border+1),rand.nextInt(border+1));
            }
            grasses.add(new grass(p));
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position);
    }
    @Override
    public boolean place(animal animal){
        return super.place(animal);
    }
    @Override
    public boolean isOccupied(Vector2d position){
        boolean f = super.isOccupied(position);
        if(!f){
            for(grass g : this.grasses){
                if(g.getPosition().toString().equals(position.toString()))
                    return true;
            }
            return false;
        }
        else
            return true;
    }
    @Override
    public Object objectAt(Vector2d position){
        Object ob = super.objectAt(position);
        if(ob==null){
            for(grass g : this.grasses)
            {
                if(g.getPosition().toString().equals(position.toString()))
                    return g;
            }
            return null;
        }
        else
            return ob;
    }
    @Override
    public String toString(){
        //Implementacja przy wyświetlaniu wszystkich obiektów na mapie(każdego zwierzaka oraz trawy)
        int maxx=0,maxy=0;
        for(animal a : this.animals){
            for(grass g: this.grasses){
                if(g.getPosition().toString().equals(a.getPosition().toString())){
                    this.grasses.remove(g);
                    break;
                }
            }
        }
        for(grass g: this.grasses){
            if(g.getPosition().x > maxx)
                maxx = g.getPosition().x;
            if(g.getPosition().y > maxy)
                maxy = g.getPosition().y;
        }
        for(animal a: this.animals){
            if(a.getPosition().x > maxx)
                maxx = a.getPosition().x;
            if(a.getPosition().y > maxy)
                maxy = a.getPosition().y;
        }
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(maxx,maxy));
    /*
    Implementacja metody toString jeśli uznajemy skrajne pozycje jedynie jako pozycje zwierząt
        for(animal a : this.animals){
            for(grass g: this.grasses){
                if(g.getPosition().toString().equals(a.getPosition().toString())){
                    this.grasses.remove(g);
                    break;
                }
            }
        }
        return super.toString();

     */




    }
}
