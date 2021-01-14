package Other;

public class Match
{
    public int p1;
    public int p2;
    public int winner=0;

    public Match(int p1, int p2)
    {
        winner=0;
        this.p1=p1;
        this.p2=p2;
    }

    public void setWinner(int w)
    {
        if(w==p1)
            winner=p1;
        else if(w==p2)
            winner=p2;
    }

}
