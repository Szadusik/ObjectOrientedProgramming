package agh.cs.lab2;

public class World {

    public static void main(String[] args)
    {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection sample = MapDirection.NORTH;
        System.out.println(sample.toString());
        System.out.println(sample.next());
        System.out.println(sample.previous());
        System.out.println(sample.toUnitVector());

        MapDirection sample2 = MapDirection.EAST;
        System.out.println(sample2.toString());
        System.out.println(sample2.next());
        System.out.println(sample2.previous());
        System.out.println(sample2.toUnitVector());

        MapDirection sample3 = MapDirection.SOUTH;
        System.out.println(sample3.toString());
        System.out.println(sample3.next());
        System.out.println(sample3.previous());
        System.out.println(sample3.toUnitVector());

        MapDirection sample4 = MapDirection.WEST;
        System.out.println(sample4.toString());
        System.out.println(sample4.next());
        System.out.println(sample4.previous());
        System.out.println(sample4.toUnitVector());

    }

}


