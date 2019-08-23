package report;

import buildorder.BuildOrder;

import java.io.OutputStream;
import java.io.PrintWriter;

public class BuildOrderReporter {

    private Formatter formatter;
    private PrintWriter printWriter;

    private BuildOrder lastBuildOrder;

    public BuildOrderReporter(Formatter formatter, OutputStream outputStream) {
        this.formatter = formatter;
        this.printWriter = new PrintWriter(outputStream);
    }

    public void report(BuildOrder buildOrder) {
        String message = formatter.format(buildOrder);
        printWriter.print(message);
        printWriter.flush();
        lastBuildOrder = buildOrder;
    }

    public BuildOrder lastBuildOrder() {
        return lastBuildOrder;
    }
}
