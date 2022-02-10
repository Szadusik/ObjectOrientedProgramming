package agh.cs.lab7;

class grass{
    private final Vector2d pos;
    public grass(Vector2d pos){
        this.pos = pos;
    }

    public Vector2d getPosition(){
        return this.pos;
    }

    public String toString(){
        return "*";
    }
}
