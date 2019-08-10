package game.stats;

import java.util.List;

public class Creation {

    private int timeDelay;
    private String unitCreatedFrom;
    private boolean willReturnUnit;
    private List<String> technologyRequired;

    // default constructor for reading JSON
    private Creation() {}

    public Creation(int timeDelay, String unitCreatedFrom, boolean willReturnUnit, List<String> technologyRequired) {
        this.timeDelay = timeDelay;
        this.unitCreatedFrom = unitCreatedFrom;
        this.willReturnUnit = willReturnUnit;
        this.technologyRequired = technologyRequired;
    }

    public int getTimeDelay() {
        return timeDelay;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public String getUnitCreatedFrom() {
        return unitCreatedFrom;
    }

    public void setUnitCreatedFrom(String unitCreatedFrom) {
        this.unitCreatedFrom = unitCreatedFrom;
    }

    public boolean isWillReturnUnit() {
        return willReturnUnit;
    }

    public void setWillReturnUnit(boolean willReturnUnit) {
        this.willReturnUnit = willReturnUnit;
    }

    public List<String> getTechnologyRequired() {
        return technologyRequired;
    }

    public void setTechnologyRequired(List<String> technologyRequired) {
        this.technologyRequired = technologyRequired;
    }

    @Override
    public String toString() {
        return "Creation{" +
                "timeDelay=" + timeDelay +
                ", unitCreatedFrom='" + unitCreatedFrom + '\'' +
                ", willReturnUnit=" + willReturnUnit +
                '}';
    }
}
