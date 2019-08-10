package report;

import buildorder.BuildOrder;
import report.format.Formatter;

import java.io.OutputStream;
import java.io.PrintWriter;


public class BuildOrderReporter {

    private Formatter formatter;
    private PrintWriter printWriter;

    BuildOrderReporter(Formatter formatter, OutputStream outputStream) {
        this.formatter = formatter;
        this.printWriter = new PrintWriter(outputStream);
    }

    public void report(BuildOrder buildOrder) {
        String message = formatter.format(buildOrder);
        printWriter.println(message);
    }
}
