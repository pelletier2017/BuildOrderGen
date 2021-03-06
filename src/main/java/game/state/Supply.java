package game.state;

public class Supply {

    private int cost;
    private int cap;

    public Supply() {
        this.cost = 0;
        this.cap = 0;
    }

    public Supply(int cost, int cap) {
        if (cost > cap) {
            throw new IllegalArgumentException("Cannot have supply greater than cap");
        }

        this.cost = cost;
        this.cap = cap;
    }

    public int getCost() {
        return cost;
    }
    public int getCap() {
        return cap;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void add(Supply other) {
        if (!canAdd(other)) {
            throw new IllegalArgumentException("Supply cannot pass cap: " + toString() + " adding " + other);
        }
        this.cost += other.cost;
        this.cap += other.cap;
    }

    public void remove(Supply other) {
        this.cost -= other.cost;
        this.cap -= other.cap;
    }

    public boolean canAdd(Supply other) {
        return this.cost + other.cost <= this.cap + other.cap;
    }
}
