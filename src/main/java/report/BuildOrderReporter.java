package report;

import buildorder.ListBuildOrder;

import java.io.OutputStream;
import java.io.PrintWriter;


public class BuildOrderReporter {

    private Formatter formatter;
    private PrintWriter printWriter;

    public BuildOrderReporter(Formatter formatter, OutputStream outputStream) {
        this.formatter = formatter;
        this.printWriter = new PrintWriter(outputStream);
    }

    public void report(ListBuildOrder buildOrder) {
        String message = formatter.format(buildOrder);
        printWriter.println(message);
    }
}
