package game.stats;

public class Income {
    private int larvaPerSec;
    private int mineralPerSec;
    private int gasPerSec;

    // default constructor for reading JSON
    private Income() {}

    public Income(int larvaPerSec, int mineralPerSec, int gasPerSec) {
        this.larvaPerSec = larvaPerSec;
        this.mineralPerSec = mineralPerSec;
        this.gasPerSec = gasPerSec;
    }

    public int getLarvaPerSec() {
        return larvaPerSec;
    }

    public void setLarvaPerSec(int larvaPerSec) {
        this.larvaPerSec = larvaPerSec;
    }

    public int getMineralPerSec() {
        return mineralPerSec;
    }

    public void setMineralPerSec(int mineralPerSec) {
        this.mineralPerSec = mineralPerSec;
    }

    public int getGasPerSec() {
        return gasPerSec;
    }

    public void setGasPerSec(int gasPerSec) {
        this.gasPerSec = gasPerSec;
    }

    @Override
    public String toString() {
        return "Income{" +
                "larvaPerSec=" + larvaPerSec +
                ", mineralPerSec=" + mineralPerSec +
                ", gasPerSec=" + gasPerSec +
                '}';
    }
}
