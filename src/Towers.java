import java.util.LinkedList;

class Towers 
{

    public static LinkedList<Move> calc(State s)
    {
       return calc(s,0,-1);
    }

    private static LinkedList<Move> calc(State s, int depth, int flippedTower)
    {
        if(s.isCompleted() || depth >= 10)
        {
            LinkedList<Move> l = new LinkedList<Move>();
            return l;
        } 
        else
        {
            LinkedList<Move> min = null;
            for(int i = 0; i < 5; i++)
            {
                if(!s.getTowers()[i]) //If it is white (Off)
                {
                    State p = s.clone();
                    p.flip(i);
                    LinkedList<Move> l = new LinkedList<Move>();
                    l.add(new Move(p,i));
                    l.addAll(calc(p, depth + 1, i));
                    if(min == null || min.size() > l.size()) min = l;
                }
            }
            return min;
        }
    }
}