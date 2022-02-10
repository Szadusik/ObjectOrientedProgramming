package agh.cs.lab5;
import java.util.List;


class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;
    public RectangularMap(int width, int height, List<animal> animals)
    {
        super(animals);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        if(position.x > this.width || position.y > this.height)
            return false;

        return super.canMoveTo(position);
    }

    @Override
    public boolean place(animal animal)
    {
        if(animal.getPosition().x > this.width || animal.getPosition().y > this.height)
            return false;

        return super.place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position)
    {
        return super.objectAt(position);
    }

    @Override
    public String toString(){
        for(animal a : this.animals){
            if(a.getPosition().toString().equals(new Vector2d(this.width,this.height).toString()))
                return super.toString();
        }
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(this.width,this.height));
    }
}
