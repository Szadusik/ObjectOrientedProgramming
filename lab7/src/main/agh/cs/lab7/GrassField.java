package agh.cs.lab7;

import java.util.*;

class GrassField extends AbstractWorldMap{
    Random rand = new Random();
    private final MapBoundary boundary = new MapBoundary();
    private final Map<Vector2d,grass> grasses = new HashMap<>();
    private final int n;
    public GrassField(int n, Map<Vector2d,animal> animals){
        super(animals);
        int i;
        int border = (int) Math.sqrt(10*n);
        this.n = n;
        for(i=0; i<n;i++){
            Vector2d p = new Vector2d(rand.nextInt(border+1),rand.nextInt(border+1));
            while(isOccupied(p)){
                p = new Vector2d(rand.nextInt(border+1),rand.nextInt(border+1));
            }
            this.grasses.put(p,new grass(p));
            this.boundary.addtoset(p);
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
            return this.grasses.get(position) != null;
        }
        else
            return true;
    }
    @Override
    public Object objectAt(Vector2d position){
        Object ob = super.objectAt(position);
        if(ob==null){
            return this.grasses.get(position);
        }
        else
            return ob;
    }
    @Override
    public String toString(){
        //Implementacja przy wyświetlaniu wszystkich obiektów na mapie(każdego zwierzaka oraz trawy)
        Vector2d[] bounds = this.boundary.getboundaries();

        for(Vector2d a : bounds){
            if(this.grasses.get(a)!=null && this.animals.get(a)!=null)
                this.grasses.remove(a);
        }
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(bounds[0].x,bounds[1].y),new Vector2d(bounds[2].x,bounds[3].y));

    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition,newPosition);
        this.boundary.positionChanged(oldPosition,newPosition);
    }
}
