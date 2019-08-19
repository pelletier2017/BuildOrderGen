package game.state;

import game.stats.GameUnit;
import game.stats.Income;

import java.util.*;

/*
Keeps track of how many units you have of each type
Can quickly get stats important to the game state like income, supply
those stats should be kept track of with additions/substractions so no iterating
 */
public class UnitCollection {

    private Map<GameUnit, Integer> unitMap = new HashMap<>();

    private Supply supply = new Supply();
    private Income income = new Income();

    public void add(GameUnit gameUnit, int count) {
        for (int i = 0; i < count; i++) {
            add(gameUnit);
        }
    }

    public void add(GameUnit gameUnit) {
        verifyCanAdd(gameUnit);
        doAdd(gameUnit);
    }

    public void removeUnit(GameUnit gameUnit) {
        int originalCount = unitMap.getOrDefault(gameUnit, 0);
        if (originalCount <= 0) {
            throw new IllegalArgumentException("cannot remove unit, there are none");
        }

        supply.remove(gameUnit.getSupply());
        income.remove(gameUnit.getIncome());
        unitMap.put(gameUnit, originalCount - 1);
    }

    private void doAdd(GameUnit gameUnit) {
        supply.add(gameUnit.getSupply());
        income.add(gameUnit.getIncome());

        int newCount = unitMap.getOrDefault(gameUnit, 0) + 1;
        unitMap.put(gameUnit, newCount);
    }

    private void verifyCanAdd(GameUnit gameUnit) {
        if (!supply.canAdd(gameUnit.getSupply())) {
            throw new IllegalArgumentException("cant add unit, not enough supply");
        }
    }

    public Income getIncome() {
        return income;
    }

    public List<GameUnit> unitList() {
        List<GameUnit> units = new ArrayList<>();
        for (Map.Entry<GameUnit, Integer> entry : unitMap.entrySet()) {
            GameUnit unit = entry.getKey();
            int count = entry.getValue();
            if (count > 0) {
                units.add(unit);
            }
        }
        Collections.sort(units);
        return units;
    }

    public boolean contains(UnitCollection other) {
        for (Map.Entry<GameUnit, Integer> entry : unitMap.entrySet()) {
            GameUnit unit = entry.getKey();
            int thisCount = entry.getValue();
            int otherCount = other.unitMap.get(unit);

            if (thisCount < otherCount) {
                return false;
            }
        }
        return true;
    }

    // TODO this should be getSupply and getSupplyCost maybe
    public Supply getSupply() {
        return supply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitCollection that = (UnitCollection) o;
        return Objects.equals(unitMap, that.unitMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitMap);
    }
}
