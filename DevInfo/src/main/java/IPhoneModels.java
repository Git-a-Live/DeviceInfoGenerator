public class IPhoneModels {
    private int sw;
    private int sh;
    private int ds;
    private int tsp;
    private int tmr;
    private int ntc;
    private String in;
    private String mod;

    public IPhoneModels(int sw, int sh, int ds, int tsp, int tmr, int ntc, String in, String mod){
        this.sw = sw;
        this.sh = sh;
        this.ds = ds;
        this.tsp = tsp;
        this.tmr = tmr;
        this.ntc = ntc;
        this.in = in;
        this.mod = mod;
    }

    public int getSw() {
        return sw;
    }

    public int getSh() {
        return sh;
    }

    public int getDs() {
        return ds;
    }

    public int getTsp() {
        return tsp;
    }

    public int getTmr() {
        return tmr;
    }

    public int getNtc() {
        return ntc;
    }

    public String getIn() {
        return in;
    }

    public String getMod() {
        return mod;
    }
}
