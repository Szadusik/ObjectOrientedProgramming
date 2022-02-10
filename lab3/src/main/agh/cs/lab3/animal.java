package agh.cs.lab3;
class animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String ToStr(){
        String s = "", t = "";
        s = s + this.position.toString() + " ";
        t = t + this.orientation.toString();
        switch(t)
        {
            case "Wschód":s = s + "EAST";
                break;
            case "Północ":s = s + "NORTH";
                break;
            case "Południe":s = s + "SOUTH";
                break;
            case "Zachód":s = s + "WEST";
                break;

        }
        return (s);
    }

    public animal move(MoveDirection direction)
    {
        if(direction.equals(MoveDirection.LEFT))
            this.orientation = this.orientation.previous();
        else if(direction.equals(MoveDirection.RIGHT))
            this.orientation = this.orientation.next();
        else if(direction.equals(MoveDirection.FORWARD))
        {
            switch(this.orientation){
                case EAST:
                    this.position = this.position.add(new Vector2d (1,0));
                    break;
                case WEST:
                    this.position = this.position.add(new Vector2d (-1,0));
                    break;
                case NORTH:
                    this.position = this.position.add(new Vector2d (0,1));
                    break;
                case SOUTH:
                    this.position = this.position.add(new Vector2d (0,-1));
                    break;
            }
        }
        else
        {
            switch(this.orientation){
                case EAST:
                    this.position = this.position.add(new Vector2d (-1,0));
                    break;
                case WEST:
                    this.position = this.position.add(new Vector2d (1,0));
                    break;
                case NORTH:
                    this.position = this.position.add(new Vector2d (0,-1));
                    break;
                case SOUTH:
                    this.position = this.position.add(new Vector2d(0,1));
                    break;
            }
        }
        if(this.position.x > 4)
            this.position = this.position.add(new Vector2d (-1,0));

        if(this.position.x < 0)
            this.position = this.position.add(new Vector2d (1,0));

        if(this.position.y > 4)
            this.position = this.position.add(new Vector2d (0,-1));

        if(this.position.y < 0 )
            this.position = this.position.add(new Vector2d (0,1));

        return this;
    }
}
