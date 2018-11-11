package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.appenders;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Appender;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.File;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.files.LogFile;

public class FileAppender implements Appender {
    private static ReportLevel DEFAULT_LEVEL_INFO = ReportLevel.INFO;

    private File file;
    private ReportLevel reportLevel;
    private Layout layout;
    private int appendedMessagesCount;

    public FileAppender(Layout layout) {
        this.layout = layout;
        this.reportLevel = DEFAULT_LEVEL_INFO;
        this.file = new LogFile();
        this.appendedMessagesCount = 0;
    }
    @Override
    public void append(String datetime, ReportLevel reportLevel, String message) {
        if (reportLevel.ordinal() >= this.reportLevel.ordinal()) {
            String layoutMessage = this.layout.format(datetime, reportLevel, message);

            this.file.write(layoutMessage);
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
            "Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d, File size: %d",
            this.getClass().getSimpleName(),
            this.layout.getClass().getSimpleName(),
            this.reportLevel.name(),
            this.appendedMessagesCount,
            this.file.getSize()
        );
    }
}
