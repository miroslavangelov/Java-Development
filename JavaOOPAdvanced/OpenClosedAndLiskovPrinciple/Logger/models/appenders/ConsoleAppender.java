package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.appenders;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Appender;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Writer;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.io.ConsoleOutputWriter;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public class ConsoleAppender implements Appender {
    private static ReportLevel DEFAULT_LEVEL_INFO = ReportLevel.INFO;

    private Writer writer;
    private ReportLevel reportLevel;
    private Layout layout;
    private int appendedMessagesCount;

    public ConsoleAppender(Layout layout) {
        this.layout = layout;
        this.reportLevel = DEFAULT_LEVEL_INFO;
        this.writer = new ConsoleOutputWriter();
        this.appendedMessagesCount = 0;
    }

    @Override
    public void append(String datetime, ReportLevel reportLevel, String message) {
        if (reportLevel.ordinal() >= this.reportLevel.ordinal()) {
            String layoutMessage = this.layout.format(datetime, reportLevel, message);

            this.writer.writeLine(layoutMessage);
            this.appendedMessagesCount++;
        }
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public String toString() {
        return String.format(
            "Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
            this.getClass().getSimpleName(),
            this.layout.getClass().getSimpleName(),
            this.reportLevel.name(),
            this.appendedMessagesCount
        );
    }
}
