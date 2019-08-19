package report;

import buildorder.BuildOrder;
import buildorder.BuildOrderStep;
import support.TimeFormat;

import java.util.List;

public class TimeAndSupplyFormatter implements Formatter {

    @Override
    public String format(BuildOrder buildOrder) {
        StringBuilder builder = new StringBuilder();
        builder.append("Build Order:\n");
        List<BuildOrderStep> steps = buildOrder.steps();

        for (BuildOrderStep step : steps) {
            String message = String.format("%s %s/%s %s\n",
                    TimeFormat.formattedSeconds(step.getTime()),
                    step.getSupply(),
                    step.getSupplyCap(),
                    step.getGameUnit().getName());
            builder.append(message);
        }
        builder.append("-----------------------\n");
        return builder.toString();
    }

}
