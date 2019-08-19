package game.stats;

import game.state.Supply;

/*
Goal is to read this from JSON
 */
public class GameUnit implements Comparable<GameUnit> {

    private String name;
    private Cost cost;
    private Income income;
    private Supply supply;
    private Creation creation;

    // default constructor for reading JSON
    private GameUnit() {}

    public GameUnit(String name, Cost cost, Income income, Supply supply, Creation creation) {
        this.name = name;
        this.cost = cost;
        this.income = income;
        this.supply = supply;
        this.creation = creation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Creation getCreation() {
        return creation;
    }

    public void setCreation(Creation creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(GameUnit other) {
        return Integer.compare(this.getCost().getMinerals(), other.getCost().getMinerals());
    }
}
