package game.stats;

public class Cost {
    private int minerals;
    private int gas;
    private int supply;
    private String unitCreating;

    // drones dont return after building
    // scs do return after building
    // spire -> greater spire doesnt
    private boolean willReturnUnitAfter;

    // default constructor for reading JSON
    private Cost() {}

    public Cost(int minerals, int gas, int supply, String unitCreating, boolean willReturnUnitAfter) {
        this.minerals = minerals;
        this.gas = gas;
        this.unitCreating = unitCreating;
        this.willReturnUnitAfter = willReturnUnitAfter;
    }

    public int getMinerals() {
        return minerals;
    }

    public void setMinerals(int minerals) {
        this.minerals = minerals;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public String getUnitCreating() {
        return unitCreating;
    }

    public void setUnitCreating(String unitCreating) {
        this.unitCreating = unitCreating;
    }

    public boolean isWillReturnUnitAfter() {
        return willReturnUnitAfter;
    }

    public void setWillReturnUnitAfter(boolean willReturnUnitAfter) {
        this.willReturnUnitAfter = willReturnUnitAfter;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "minerals=" + minerals +
                ", gas=" + gas +
                ", supply=" + supply +
                ", unitCreating='" + unitCreating + '\'' +
                ", willReturnUnitAfter=" + willReturnUnitAfter +
                '}';
    }
}
