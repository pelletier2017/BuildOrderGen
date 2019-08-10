package report.format;

import buildorder.BuildOrder;

public interface Formatter {
    String format(BuildOrder gameState);
}
