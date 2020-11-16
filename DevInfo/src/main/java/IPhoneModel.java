import java.util.ArrayList;

public class IPhoneModel {
    private int SW;
    private int SH;
    private int DS;
    private ArrayList<Integer> TSP;
    private long TMR;
    private String NTC;
    private String IN;

    public IPhoneModel(int sw, int sh, int ds, ArrayList<Integer> tsp, long tmr, String ntc, String in)
    {
        this.SW = sw;
        this.SH = sh;
        this.DS = ds;
        this.TSP = tsp;
        this.TMR = tmr;
        this.NTC = (ntc != null?ntc:"0");
        this.IN = (in != null?in:"in");
    }

    public int getSW()
    {
        return SW;
    }

    public int getSH()
    {
        return SH;
    }

    public int getDS()
    {
        return DS;
    }

    public ArrayList<Integer> getTSP()
    {
        return TSP;
    }

    public long getTMR()
    {
        return TMR;
    }

    public String getNTC()
    {
        return NTC;
    }

    public String getIN()
    {
        return IN;
    }


}
