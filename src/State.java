import java.util.Random;

public class State 
{
    public boolean towers[];
    //True = On
    //False = Off
    State()
    {
        towers = new boolean[5];
        for(int i = 0; i < 5 ; i++)
        {
            towers[i] = false;
        }
    }

    public boolean[] getTowers()
    {
        boolean[] a = new boolean[5];
        for (int i = 0 ; i < 5; i++)
        {
            a[i] = towers[i];
        }
        return a;
    }

    public void flip(int index)
    {
        if(!towers[index])
        {
            towers[index] = !towers[index];
            towers[(index + 1) % 5] = !towers[(index + 1) % 5];
            if(index - 1 >= 0) towers[index - 1] = !towers[index - 1];
            else towers[5 + (index - 1)] = !towers[5 + (index - 1)];
        }
        
    }

    public boolean isCompleted()
    {
        for(int i = 0; i< 5; i ++)
        {
            if(!towers[i])
            {
                return false;
            }
        }
        return true;
    }
    public State clone()
    {
        State s = new State();
        s.towers = towers.clone();
        return s;
    }
    public String toString()
    {
        String s = "[";
        for(int i = 0; i< 4; i ++)
        {
            s += towers[i] + ", ";
        }
        s += towers[4] + "]";
        return s;
    }

    public static State randomState()
    {
        State s = new State();
        Random r = new Random();
        int turnedOnTowers = r.nextInt(4) + 1;
        int i = 0;
        while( i < turnedOnTowers)
        {
            int pos = r.nextInt(5);
            if(!s.towers[pos])
            {
                s.towers[pos] = !s.towers[pos];
                i++;
            }
        }
        return s;
    }
}
