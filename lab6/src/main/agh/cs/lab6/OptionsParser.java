package agh.cs.lab6;

import java.util.LinkedList;
import java.util.List;
class OptionsParser {

    public MoveDirection[] parse(String[] s)
    {
        int n = s.length;
        List<MoveDirection> list = new LinkedList<MoveDirection>();
        for(int i = 0; i < n; i++)
        {
            if(s[i].equals("f") || s[i].equals("forward"))
            {
                list.add(MoveDirection.FORWARD);
            }
            else if(s[i].equals("b") || s[i].equals("backward"))
            {
                list.add(MoveDirection.BACKWARD);
            }
            else if(s[i].equals("l") || s[i].equals("left"))
            {
                list.add(MoveDirection.LEFT);
            }
            else if (s[i].equals("r") || s[i].equals("right"))
            {
                list.add(MoveDirection.RIGHT);
            }
            else
                throw new IllegalArgumentException(s[i]+ " is not legal move specification");
        }
        int l = list.size();
        MoveDirection[] directions = new MoveDirection[l];
        for(int i =0; i<l ;i++)
        {
            directions[i] = list.get(i);
        }

        return directions;
    }
}


