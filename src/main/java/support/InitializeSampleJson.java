package support;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.state.Supply;
import game.stats.Creation;
import game.stats.GameUnit;
import game.stats.Cost;
import game.stats.Income;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InitializeSampleJson {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        // drone
        Cost cost = new Cost(50, 0, 1, "larva", false);
        Income income = new Income(0, 5, 0);
        Supply supply = new Supply(1, 0);
        Creation creation = new Creation(5, "larva", false, Collections.EMPTY_LIST);

        GameUnit drone = new GameUnit("drone", cost, income, supply, creation);

        // overlord
        Cost overlordCost = new Cost(100, 0, -10, null, false);
        Income overlordIncome = new Income(0, 0, 0);
        Supply overlordSupply = new Supply(0, 8);
        Creation overlordCreation = new Creation(7, "larva", false, Collections.EMPTY_LIST);
        GameUnit overlord = new GameUnit("overlord", overlordCost, overlordIncome, overlordSupply, overlordCreation);

        List<GameUnit> units = Arrays.asList(drone, overlord);

        JsonUtils.writeUnits(units);
        List<GameUnit> output = JsonUtils.readUnits();
        System.out.println(output);
    }
}
