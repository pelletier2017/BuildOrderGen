package report;

import buildorder.ListBuildOrder;

public interface Formatter {
    String format(ListBuildOrder gameState);
}
