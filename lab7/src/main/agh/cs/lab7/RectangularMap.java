package agh.cs.lab7;

import java.util.Map;


class RectangularMap extends AbstractWorldMap{
    /*
    W RectangularMap nie potrzebujemy używać klasy MapBoundary, ponieważ granicę rysowania naszej mapy
    definiują otrzymane wymiary width i height z góry oraz prawa oraz punkt(0,0) z dołu i lewa. Zatem w RectangularMap
    używanie MapBoundary nie jest potrzebne
     */
    private final int width;
    private final int height;
    public RectangularMap(int width, int height, Map<Vector2d,animal> animals)
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
            throw new IllegalArgumentException(animal.getPosition().toString() + " znajduje się poza granicami mapy");

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
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(new Vector2d(0,0),new Vector2d(this.width,this.height));
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition,newPosition);
    }
}
