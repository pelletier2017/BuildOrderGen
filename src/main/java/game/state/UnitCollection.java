package game.state;

import game.stats.GameUnit;
import game.stats.Income;
import game.stats.Race;

import java.util.*;

/*
Keeps track of how many units you have of each type
Can quickly get stats important to the game state like income, supply
those stats should be kept track of with additions/substractions so no iterating
 */
public class UnitCollection {

    private Map<GameUnit, Integer> unitMap;

    private Supply supply = new Supply();
    private Income income;

    public UnitCollection() {
        this.unitMap = new HashMap<>();
    }

    public void addUnits(GameUnit gameUnit, int count) {
        for (int i = 0; i < count; i++) {
            addUnit(gameUnit);
        }
    }

    public void addUnit(GameUnit gameUnit) {
        // TODO this is wordy, gameUnit.getSupply()
        verifyCanAdd(gameUnit);
        doAdd(gameUnit);
    }

    public void removeUnit(GameUnit gameUnit) {
        supply.remove(gameUnit.getSupply());

        int originalCount = unitMap.getOrDefault(gameUnit, 0);
        if (originalCount <= 0) {
            throw new IllegalArgumentException("cannot remove unit, there are none");
        }

        unitMap.put(gameUnit, originalCount - 1);
    }

    private void doAdd(GameUnit gameUnit) {
        supply.add(gameUnit.getSupply());

        // TODO add to income

        int newCount = unitMap.getOrDefault(gameUnit, 0) + 1;
        unitMap.put(gameUnit, newCount);
    }

    private void verifyCanAdd(GameUnit gameUnit) {
        if (!supply.canAdd(gameUnit.getSupply())) {
            throw new IllegalArgumentException("cant add unit, not enough supply");
        }
    }

}
