package agh.cs.lab5;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    public String toString(){
        switch(this){
            case EAST: return "Wschód";
            case WEST: return "Zachód";
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
        }
        return "Błąd";
    }
    public MapDirection next(){
        switch(this){
            case EAST: return SOUTH;
            case WEST: return NORTH;
            case NORTH: return EAST;
            case SOUTH: return WEST;
        }
        return NORTH;
    }
    public MapDirection previous(){
        switch(this){
            case EAST: return NORTH;
            case WEST: return SOUTH;
            case NORTH: return WEST;
            case SOUTH: return EAST;
        }
        return NORTH;
    }
    public Vector2d toUnitVector(){
        switch(this){
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
        }
        return new Vector2d(0,0);
    }

}