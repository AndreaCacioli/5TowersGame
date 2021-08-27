public class Move 
{
    public State s;
    public int flippedTower;

    Move()
    {
        s = null;
        flippedTower = -1;
    }
    Move(State state, int ft)
    {
        s = state;
        flippedTower = ft;
    }
}
