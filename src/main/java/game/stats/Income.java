package game.stats;

public class Income {
    private int larvaPerSec;
    private int mineralPerSec;
    private int gasPerSec;

    public Income() {
        this(0,0,0);
    }

    public Income(int larvaPerSec, int mineralPerSec, int gasPerSec) {
        this.larvaPerSec = larvaPerSec;
        this.mineralPerSec = mineralPerSec;
        this.gasPerSec = gasPerSec;
    }

    public void add(Income other) {
        this.larvaPerSec += other.larvaPerSec;
        this.mineralPerSec += other.mineralPerSec;
        this.gasPerSec += other.gasPerSec;
    }

    public void remove(Income other) {
        this.larvaPerSec -= other.larvaPerSec;
        this.mineralPerSec -= other.mineralPerSec;
        this.gasPerSec -= other.gasPerSec;
    }

    public void assertNonNegative() {
        if (larvaPerSec < 0 || mineralPerSec < 0 || gasPerSec < 0) {
            throw new IllegalStateException("Income cannot be negative");
        }
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
